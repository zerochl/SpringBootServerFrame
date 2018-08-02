package com.zero.barrageserver.dbserver.model;

import com.zero.barrageserver.common.entity.apirequest.ApiReqShowImage;
import com.zero.barrageserver.common.entity.apirequest.ApiReqShowImage;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/4.
 */
@Data
public class ShowImageEntity implements Serializable,Cloneable {
    private String type;
    private String image;
    private int interval;
    @Field("play_count")
    private int playCount;
    @Field("video_url")
    private String videoUrl;
    @Field("immediateShow")
    private boolean immediateShow;
    public ShowImageEntity(){}
    public ShowImageEntity(ApiReqShowImage apiReqShowImage){
        this.type = apiReqShowImage.getType();
        this.image = apiReqShowImage.getImage();
        this.interval = apiReqShowImage.getInterval();
        this.playCount = apiReqShowImage.getPlay_count();
        this.videoUrl = apiReqShowImage.getVideo_url();
        this.immediateShow = apiReqShowImage.isImmediateShow();
    }
}
