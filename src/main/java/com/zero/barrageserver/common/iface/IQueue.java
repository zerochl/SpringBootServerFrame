package com.zero.barrageserver.common.iface;

/**
 * Created by zero大神 on 2017/12/20.
 */
public interface IQueue<T> extends IQueueObserver<T> {
    void registerQueue();
    void pushMessage(T msg);
    T poll();
    /**新增子队列，与主队列共存，pop时根据weight进行择取*/
    void addChildQueue();
    void endPoll();
    /**具体的Poll*/
    void startPoll();
    /**开始poll链，如果已经在执行poll链则不会调用startPoll*/
    void startPollChain();
    /**注册当前队列需要接收的消息类型*/
    void registerQueueType(String queueType);

    long getAllQueueSize();
}
