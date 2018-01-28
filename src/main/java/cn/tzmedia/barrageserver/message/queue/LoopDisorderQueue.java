package cn.tzmedia.barrageserver.message.queue;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.manager.MsgLoopManager;
import cn.tzmedia.barrageserver.message.base.MessageQueue;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Component
@Log4j2
public class LoopDisorderQueue extends MessageQueue{
    @Autowired
    private MsgLoopManager msgLoopManager;
    @Override
    public void receiveMessage(String queueType, BaseResponseRootMsg msg) {
//        if(msg.isProgram()){
//            return;
//        }
        super.receiveMessage(queueType, msg);
    }
    @Override
    public void registerQueue() {
        super.registerQueue();
        registerQueueType(Constant.BARRAGE_TYPE_COMMENT);
//        registerQueueType(Constant.BARRAGE_TYPE_LIKE);
        registerQueueType(Constant.BARRAGE_TYPE_SONG);
        registerQueueType(Constant.BARRAGE_TYPE_AWARD);
        registerQueueType(Constant.BARRAGE_TYPE_VP);
    }

    @Override
    public void startPoll() {
        BaseResponseRootMsg msg = poll();
        if(null == msg){
            log.info("循环消息循环结束");
            return;
        }
        log.info("循环消息开始发送");
        //此处之所有使用clone，因为内部修改此对象，如果此对象同样发送到了别的队列，那么就会产生干扰
        msgLoopManager.addLoopMsg((BaseResponseRootMsg)msg.clone());
        //递归执行,循环消息不是很重要,可以延迟大一些
        reStartPoll(500);
    }

}
