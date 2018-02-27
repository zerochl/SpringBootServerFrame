package cn.tzmedia.barrageserver.message.queue;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.message.base.MessageQueue;
import cn.tzmedia.barrageserver.message.entity.BaseResponseRootMsg;
import cn.tzmedia.barrageserver.dbserver.service.VPService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Log4j2
@Component
public class VpMultiHitQueue extends MessageQueue{
    @Autowired
    private VPService vpService;

    @Override
    public void registerQueue() {
        super.registerQueue();
        registerQueueType(Constant.BARRAGE_TYPE_VP);
    }

    @Override
    public void startPoll() {
        BaseResponseRootMsg msg = poll();
        if(null == msg){
            log.info("连击礼物消息循环结束");
            return;
        }
        //此处之所有使用clone，因为内部修改此对象，如果此对象同样发送到了别的队列，那么就会产生干扰
        List<BaseResponseRootMsg> mulhitRootMsgList = vpService.getMultiHitMsgList((BaseResponseRootMsg)msg.clone());
        sendMsgAsync(mulhitRootMsgList,false);
        //递归执行
        reStartPoll(100);
    }
}
