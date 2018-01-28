package cn.tzmedia.barrageserver.message.queue;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.message.base.MessageQueue;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Log4j2
@Component
public class VpOrderQueue extends MessageQueue{

    @Override
    public void receiveMessage(String queueType, BaseResponseRootMsg msg) {
        if(msg.isProgram()){
            return;
        }
        super.receiveMessage(queueType, msg);
    }
    @Override
    public void registerQueue() {
        super.registerQueue();
        registerQueueType(Constant.BARRAGE_TYPE_VP);
    }
    @Override
    public void startPoll() {
        BaseResponseRootMsg msg = poll();
        if(null == msg){
            log.info("有序礼物消息循环结束");
            return;
        }
        log.info("有序礼物消息发送");
        //调用异步处理，不需要等待消息返回
        sendMsgAsync(msg,true);
        //递归执行
        reStartPoll(100);
    }
}
