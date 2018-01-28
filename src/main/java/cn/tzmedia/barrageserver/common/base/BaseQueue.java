package cn.tzmedia.barrageserver.common.base;

import cn.tzmedia.barrageserver.common.entity.BaseQueueEntity;
import cn.tzmedia.barrageserver.common.iface.IQueue;
import cn.tzmedia.barrageserver.common.manager.TimerManager;
import cn.tzmedia.barrageserver.message.base.CustomLinkedBlockingQueue;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.ArrayList;

/**
 * Created by zero大神 on 2017/12/20.
 */
@Log4j2
public abstract class BaseQueue<T extends BaseQueueEntity> implements IQueue<T>,ServletContextAware {
    private ArrayList<String> msgSupportList = new ArrayList<>();
    private ArrayList<CustomLinkedBlockingQueue<T>> msgQueueList = new ArrayList<>();
    private int maxWeight = -1;
    private boolean isPoll = false;
    @Autowired
    private TimerManager timerManager;

    public BaseQueue() {
        addChildQueue();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        //程序初始化即运行
        registerQueue();
    }

    @Override
    public void registerQueueType(String msgType) {
        msgSupportList.add(msgType);
    }

    @Override
    public void receiveMessage(String queueType,T msg) {
        if (msgSupportList.contains(queueType)) {
            pushMessage(msg);
            startPollChain();
        }
    }

    @Override
    public long getAllQueueSize() {
        long allSize = 0;
        for(CustomLinkedBlockingQueue<T> linkedQueue:msgQueueList){
            allSize = allSize + linkedQueue.size();
        }
        return allSize;
    }

    @Override
    public void pushMessage(T msg) {
        try {
            for (CustomLinkedBlockingQueue<T> linkedBlockingQueue : msgQueueList) {
                if (linkedBlockingQueue.getWeight() == msg.getWeight()) {
                    //权重等级相当，才能设置进队列
                    linkedBlockingQueue.put(msg);
                    return;
                }
            }
            //找不到对应权重则加入当前权重最大的队列
            msgQueueList.get(msgQueueList.size() - 1).put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T poll() {
        int size = msgQueueList.size();
        for(int i = size - 1;i >= 0;i--){
            if(!msgQueueList.get(i).isEmpty()){
                return msgQueueList.get(i).poll();
            }
        }
        endPoll();
        return null;
    }

    @Override
    public void addChildQueue() {
        msgQueueList.add(new CustomLinkedBlockingQueue<>(++maxWeight));
    }

    @Override
    public void endPoll() {
        isPoll = false;
    }

    @Override
    public void startPollChain() {
        if(!isPoll){
            isPoll = true;
            //调用请求接口
            //此处使用随机IO线程处理，这样保证每个队列最终的处理都在一个单独的线程中
            //与生产者的线程分离，不能占用生产者的线程
            //lamda格式，请注意
            Observable.create(observableEmitter -> startPoll())
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }
    }

    public void reStartPoll(long delay){
        timerManager.postFaster(reRun,delay);
    }


    private Runnable reRun = new Runnable() {
        @Override
        public void run() {
            log.info("开始执行倒计时任务");
            startPoll();
        }
    };
}
