package com.zero.barrageserver.dbwrite.queue;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.dbwrite.base.BaseDBWriteQueue;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import com.zero.barrageserver.dbserver.service.BarrageDeviceService;
import com.zero.barrageserver.dbserver.service.BarrageDeviceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/12/20.
 */
@Component
@Log4j2
public class BarrageDeviceDBQueue extends BaseDBWriteQueue{
    @Autowired
    private BarrageDeviceService barrageDeviceService;
    @Override
    public void registerQueue() {
        super.registerQueue();
        //产生两个高等级队列
        addChildQueue();
        addChildQueue();
        registerQueueType(Constant.DBWRITE_TYPE_BARRAGE);
    }

    @Override
    public void startPoll() {
        BaseDBWriteEntity dbWriteEntity = poll();
        if(null == dbWriteEntity){
            return;
        }
        log.error("当前队列个数：" + getAllQueueSize());
        handleAsync(dbWriteEntity);
        //递归执行
        reStartPoll(0);
    }

    @Override
    public void doExecute(BaseDBWriteEntity dbWriteEntity) {
        barrageDeviceService.onQueueHandle(dbWriteEntity);
    }
}
