package cn.tzmedia.barrageserver.common.entity.apirequest;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.server.model.BarrageConfigTable;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Data
public class ApiReqBarrageConfig implements Serializable{
    private String shopId;
    private int loopIntervalMinute;
    private int normalScrollTime;
    private int vpScrollTime;
    private int consumeContinueTime;
    private int imageWallContinueTime;
    private int songContinueTime;
    private int imageWallSwitchTime;
    private int consumeSwitchTime;
    private int consumeBigContinueTime;
    public ApiReqBarrageConfig(){}
    public ApiReqBarrageConfig(BarrageConfigTable barrageConfigTable){
        this.shopId = barrageConfigTable.getShopId();
        this.loopIntervalMinute = barrageConfigTable.getLoopIntervalMinute();
        this.normalScrollTime = barrageConfigTable.getNormalScrollTime();
        this.vpScrollTime = barrageConfigTable.getVpScrollTime();
        this.consumeContinueTime = Constant.CONSUME_CONTINUE_TIME;
        this.imageWallContinueTime = Constant.IMAGE_WALL_CONTINUE_TIME;
        this.songContinueTime = Constant.SONG_CONTINUE_TIME;
        this.imageWallSwitchTime = Constant.IMAGE_WALL_SWITCH_TIME;
        this.consumeSwitchTime = Constant.CONSUME_SWITCH_TIME;
        this.consumeBigContinueTime = Constant.CONSUME_BIG_SWITCH_TIME;
    }
}
