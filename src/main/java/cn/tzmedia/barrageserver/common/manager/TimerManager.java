package cn.tzmedia.barrageserver.common.manager;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by zero大神 on 2017/12/13.
 */
@Component
public class TimerManager {
    private ScheduledThreadPoolExecutor fasterTimmer = new ScheduledThreadPoolExecutor(1);
    private ScheduledThreadPoolExecutor loopMsgTimer = new ScheduledThreadPoolExecutor(1);
    private ConcurrentHashMap<Runnable,RunnableScheduledFuture> fasterFutureCache = new ConcurrentHashMap<>();
    public void postFaster(Runnable runnable){
        postFaster(runnable,0);
    }

    public void postLoopMsg(Runnable runnable){
        postFaster(runnable,0);
    }

    /**
     * 注意，使用此方法可能存在内存泄露，要么常驻内存，要么用完立即remove
     * @param runnable
     * @param delay
     */
    public void postFaster(Runnable runnable,long delay){
        removeFaster(runnable);
        RunnableScheduledFuture future = (RunnableScheduledFuture)fasterTimmer.schedule(runnable,delay, TimeUnit.MILLISECONDS);
        fasterFutureCache.put(runnable,future);
    }

    public void removeFaster(Runnable runnable){
        RunnableScheduledFuture future = fasterFutureCache.get(runnable);
        if(null != future){
            fasterTimmer.remove(future);
        }
    }

    /**
     * 注意，使用此方法可能存在内存泄露，要么常驻内存，要么用完立即remove
     * @param runnable
     * @param delay
     */
    public void postLoopMsg(Runnable runnable,long delay){
        removeLoopMsg(runnable);
        RunnableScheduledFuture future = (RunnableScheduledFuture)loopMsgTimer.schedule(runnable,delay, TimeUnit.MILLISECONDS);
        fasterFutureCache.put(runnable,future);
    }

    public void removeLoopMsg(Runnable runnable){
        RunnableScheduledFuture future = fasterFutureCache.get(runnable);
        if(null != future){
            future.cancel(false);
            loopMsgTimer.remove(future);
        }
    }

    public ConcurrentHashMap<Runnable,RunnableScheduledFuture> getFasterFutureCache(){
        return fasterFutureCache;
    }

}
