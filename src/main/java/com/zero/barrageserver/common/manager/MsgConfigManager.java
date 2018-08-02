package com.zero.barrageserver.common.manager;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.message.entity.*;
import com.zero.barrageserver.dbserver.model.BarrageConfigTable;
import com.zero.barrageserver.dbserver.service.BarrageConfigService;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.dbserver.model.BarrageConfigTable;
import com.zero.barrageserver.dbserver.service.BarrageConfigService;
import com.zero.barrageserver.message.entity.BaseResponseRootMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2018/1/10.
 */
@Component
public class MsgConfigManager {
    @Autowired
    private BarrageConfigService barrageConfigService;
    public BaseResponseRootMsg equipMsgConfig(BaseResponseRootMsg rootMsg){
        BarrageConfigTable barrageConfigTable = barrageConfigService.getBarrageConfig(rootMsg.getShopId());
        switch (rootMsg.getType()){
            case Constant.BARRAGE_TYPE_COMMENT:
            case Constant.BARRAGE_TYPE_LIKE:
            case Constant.BARRAGE_TYPE_MULTI_HIT:
                rootMsg.setScrollTime(getNormalMsgScrollTime(barrageConfigTable));
                break;
            case Constant.BARRAGE_TYPE_AWARD:
            case Constant.BARRAGE_TYPE_SONG:
            case Constant.BARRAGE_TYPE_VP:
                rootMsg.setScrollTime(getVPMsgScrollTime(barrageConfigTable));
                break;
            default:
                rootMsg.setScrollTime(Constant.BARRAGE_SCROLL_TIME_NORMAL);
        }
        return rootMsg;
    }

    private int getNormalMsgScrollTime(BarrageConfigTable barrageConfigTable){
        if(null == barrageConfigTable || barrageConfigTable.getNormalScrollTime() == 0){
            return Constant.BARRAGE_SCROLL_TIME_NORMAL;
        }
        return barrageConfigTable.getNormalScrollTime();
    }

    private int getVPMsgScrollTime(BarrageConfigTable barrageConfigTable){
        if(null == barrageConfigTable || barrageConfigTable.getVpScrollTime() == 0){
            return Constant.BARRAGE_SCROLL_TIME_VP;
        }
        return barrageConfigTable.getVpScrollTime();
    }
}
