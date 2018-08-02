package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Data
public class MsgItemBarrageShowInfo implements Serializable{
    private String type;
    private int interval;
    private String movieUrl;
    private String imageUrl;
    private int movieLoopCount;
    private boolean immediateShow;

    public MsgItemBarrageShowInfo(String type,String movieUrl){
        this.type = type;
        this.movieUrl = movieUrl;
    }

    public MsgItemBarrageShowInfo(String type,String imageUrl,int interval,boolean immediateShow){
        this.type = type;
        this.imageUrl = UrlUtils.getRealUrl(imageUrl);
        this.interval = interval;
        this.immediateShow = immediateShow;
    }

    public MsgItemBarrageShowInfo(ShowImageEntity showImageEntity){
        if(null == showImageEntity){
            return;
        }
        this.type = showImageEntity.getType();
        this.interval = showImageEntity.getInterval();
        this.movieUrl = showImageEntity.getVideoUrl();
        this.imageUrl = UrlUtils.getRealUrl(showImageEntity.getImage());
        this.movieLoopCount = showImageEntity.getPlayCount();
        this.immediateShow = showImageEntity.isImmediateShow();
    }
}
