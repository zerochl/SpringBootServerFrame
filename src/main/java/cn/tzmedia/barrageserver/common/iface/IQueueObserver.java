package cn.tzmedia.barrageserver.common.iface;

/**
 * Created by zero大神 on 2017/12/20.
 */
public interface IQueueObserver<T> {
    /**会接收所有消息，需要的才会执行push*/
    void receiveMessage(String queueType,T msg);
}
