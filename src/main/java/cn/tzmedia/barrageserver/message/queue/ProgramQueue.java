package cn.tzmedia.barrageserver.message.queue;

import cn.tzmedia.barrageserver.activeapi.service.TenActiveService;
import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.manager.TimerManager;
import cn.tzmedia.barrageserver.message.MessageProducer;
import cn.tzmedia.barrageserver.message.base.MessageQueue;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Component
@Log4j2
public class ProgramQueue extends MessageQueue {

    @Override
    public void receiveMessage(String queueType, BaseResponseRootMsg msg) {
        if(!msg.isProgram()){
            return;
        }
        super.receiveMessage(queueType, msg);
    }
    @Override
    public void registerQueue() {
        super.registerQueue();
        registerQueueType(Constant.BARRAGE_TYPE_PROGRAME);
        registerQueueType(Constant.BARRAGE_TYPE_HOTPOINTS);
        //历史消息
        registerQueueType(Constant.BARRAGE_TYPE_COMMENT);
        registerQueueType(Constant.BARRAGE_TYPE_LIKE);
        registerQueueType(Constant.BARRAGE_TYPE_SONG);
        registerQueueType(Constant.BARRAGE_TYPE_AWARD);
        registerQueueType(Constant.BARRAGE_TYPE_VP);
    }
    @Override
    public void startPoll() {
        BaseResponseRootMsg msg = poll();
        if(null == msg){
            log.info("节目消息循环结束");
            return;
        }
        log.info("执行节目消息发送");
        //调用异步处理，但是需要单线程队列
        sendMsgAsync(msg,getSingleExecutorKey(msg.getShopId()),true);
        //递归执行
        reStartPoll(50);
    }

    private String getSingleExecutorKey(String shopId){
        return "Program" + shopId;
    }

}
