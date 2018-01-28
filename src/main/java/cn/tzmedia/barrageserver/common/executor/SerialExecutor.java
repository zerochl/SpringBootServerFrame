package cn.tzmedia.barrageserver.common.executor;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zero大神 on 2017/12/1.
 */
@Component
@Log4j2
public class SerialExecutor implements Executor {
//    @Value("${app.msg-thread-pool.core-pool-size}")
    private int CORE_POOL_SIZE = 1;
//    @Value("${app.msg-thread-pool.max-pool-size}")
    public int MAXIMUM_POOL_SIZE = 10;
//    @Value("${app.msg-thread-pool.keep-alive-second}")
    private int KEEP_ALIVE_SECOND = 10;
    private final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(10);
    private final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    public final Executor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECOND,
            TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

    final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
    Runnable mActive;

    public synchronized void execute(final Runnable r) {
        mTasks.offerFirst(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (mActive == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((mActive = mTasks.poll()) != null) {
            THREAD_POOL_EXECUTOR.execute(mActive);
        }
    }

    public synchronized void remove(Runnable r){
        mTasks.remove(r);
    }
}
