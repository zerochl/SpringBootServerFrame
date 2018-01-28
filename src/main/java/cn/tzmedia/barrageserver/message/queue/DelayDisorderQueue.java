package cn.tzmedia.barrageserver.message.queue;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.manager.DelayMsgManager;
import cn.tzmedia.barrageserver.message.base.MessageQueue;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2018/1/4.
 */
@Component
@Log4j2
public class DelayDisorderQueue extends MessageQueue {
    @Autowired
    private DelayMsgManager delayMsgManager;
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
        registerQueueType(Constant.BARRAGE_TYPE_HOTPOINTS);
        registerQueueType(Constant.BARRAGE_TYPE_CONSUME_LIST);
    }
    @Override
    public void startPoll() {
        BaseResponseRootMsg msg = poll();
        if(null == msg){
            log.info("延迟循环结束");
            return;
        }
        log.info("延迟消息发送");
//        hotMsgManager.addHotMsg((BaseResponseRootMsg)msg.clone());
        //热度消息只有此处使用，不需要克隆
        delayMsgManager.addDelayMsg(msg);
        //递归执行
        reStartPoll(100);
    }
}
