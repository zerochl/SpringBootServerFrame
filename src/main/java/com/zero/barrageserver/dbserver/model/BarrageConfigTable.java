package com.zero.barrageserver.dbserver.model;

import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageConfig;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageConfig;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zero大神 on 2018/1/8.
 * 新店铺弹幕设置表，一个店铺的所有设置都存在一条记录中
 */
@Document(collection = "barrage.config")
@Data
public class BarrageConfigTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    @Field("shop_id")
    private String shopId;
    @Field("loop_interval_minute")
    private int loopIntervalMinute;
    @Field("normal_scroll_time")
    private int normalScrollTime;
    @Field("vp_scroll_time")
    private int vpScrollTime;
    @Field("update_time")
    private Date updateTime;
    @Field("create_time")
    private Date createTime;
    @Field("consume_continue_time")
    private int consumeContinueTime;
    @Field("image_wall_continue_time")
    private int imageWallContinueTime;
    @Field("song_continue_time")
    private int songContinueTime;
    @Field("image_wall_switch_time")
    private int imageWallSwitchTime;
    @Field("consume_switch_time")
    private int consumeSwitchTime;
    @Field("consume_big_continue_time")
    private int consumeBigContinueTime;

    public BarrageConfigTable(){}
    public BarrageConfigTable(ApiReqBarrageConfig barrageConfig){
        this.shopId = barrageConfig.getShopId();
        this.loopIntervalMinute = barrageConfig.getLoopIntervalMinute();
        this.normalScrollTime = barrageConfig.getNormalScrollTime();
        this.vpScrollTime = barrageConfig.getVpScrollTime();
        this.consumeContinueTime = barrageConfig.getConsumeContinueTime();
        this.imageWallContinueTime = barrageConfig.getImageWallContinueTime();
        this.songContinueTime = barrageConfig.getSongContinueTime();
        this.imageWallSwitchTime = barrageConfig.getImageWallSwitchTime();
        this.consumeSwitchTime = barrageConfig.getConsumeSwitchTime();
        this.consumeBigContinueTime = barrageConfig.getConsumeBigContinueTime();
    }

    public void reSetData(BarrageConfigTable barrageConfigTable){
        this.loopIntervalMinute = barrageConfigTable.getLoopIntervalMinute();
        this.normalScrollTime = barrageConfigTable.getNormalScrollTime();
        this.vpScrollTime = barrageConfigTable.getVpScrollTime();
        this.consumeContinueTime = barrageConfigTable.getConsumeContinueTime();
        this.imageWallContinueTime = barrageConfigTable.getImageWallContinueTime();
        this.songContinueTime = barrageConfigTable.getSongContinueTime();
        this.imageWallSwitchTime = barrageConfigTable.getImageWallSwitchTime();
        this.consumeSwitchTime = barrageConfigTable.getConsumeSwitchTime();
        this.consumeBigContinueTime = barrageConfigTable.getConsumeBigContinueTime();
        if(this.createTime == null){
            this.createTime = new Date();
        }
        this.updateTime = new Date();
    }

    public void prepareInsert(){
        this.createTime = new Date();
    }
}
