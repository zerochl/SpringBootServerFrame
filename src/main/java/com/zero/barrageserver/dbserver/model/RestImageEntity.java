package com.zero.barrageserver.dbserver.model;

import com.zero.barrageserver.common.entity.apirequest.ApiReqRestImage;
import com.zero.barrageserver.common.entity.apirequest.ApiReqRestImage;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/4.
 */
@Data
public class RestImageEntity implements Serializable {
    private String type;
    private String artist;
    private String name;
    private String introduce;
    private String experience;
    private String image;
    private int interval;
    @Field("video_url")
    private String videoUrl;
    @Field("play_count")
    private int playCount;
    @Field("immediateShow")
    private boolean immediateShow;
    public RestImageEntity(){}
    public RestImageEntity(ApiReqRestImage apiReqRestImage){
        this.type = apiReqRestImage.getType();
        this.artist = apiReqRestImage.getArtist();
        this.name = apiReqRestImage.getName();
        this.introduce = apiReqRestImage.getIntroduce();
        this.experience = apiReqRestImage.getExperience();
        this.image = apiReqRestImage.getImage();
        this.interval = apiReqRestImage.getInterval();
        this.videoUrl = apiReqRestImage.getVideo_url();
        this.playCount = apiReqRestImage.getPlay_count();
        this.immediateShow = apiReqRestImage.isImmediateShow();
    }
}
