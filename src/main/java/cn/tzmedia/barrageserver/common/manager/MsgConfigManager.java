package cn.tzmedia.barrageserver.common.manager;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.message.entity.*;
import cn.tzmedia.barrageserver.dbserver.model.BarrageConfigTable;
import cn.tzmedia.barrageserver.dbserver.service.BarrageConfigService;
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
