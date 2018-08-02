package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import com.zero.barrageserver.common.utils.UrlUtils;
import com.zero.barrageserver.dbserver.model.ArtistTable;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import lombok.Data;

/**
 * Created by Administrator on 2017/12/12.
 */
@Data
public class MsgItemBarrageRestInfo {
    private String artist;
    private String experience;
    private String image;
    private int interval;
    private String introduce;
    private String type;
    private String name;
    private String movieUrl;
    private int movieLoopCount;
    private boolean immediateShow;

    public MsgItemBarrageRestInfo(RestImageEntity restImageEntity){
        this.artist = restImageEntity.getArtist();
        this.experience = restImageEntity.getExperience();
        this.image = UrlUtils.getRealUrl(restImageEntity.getImage());
        this.interval = restImageEntity.getInterval();
        this.introduce = restImageEntity.getIntroduce();
        this.type = restImageEntity.getType();
        this.name = restImageEntity.getName();
        this.movieUrl = restImageEntity.getVideoUrl();
        this.movieLoopCount = restImageEntity.getPlayCount();
        this.immediateShow = restImageEntity.isImmediateShow();
    }

    public MsgItemBarrageRestInfo(RestImageEntity restImageEntity, ArtistTable artistTable){
//        this.artist = restImageEntity.getArtist();
        this.experience = artistTable.getExperience();
        this.image = UrlUtils.getRealUrl(null != artistTable.getImageList() && artistTable.getImageList().size() > 0?artistTable.getImageList().get(0):"");
        this.interval = restImageEntity.getInterval();
        this.introduce = artistTable.getIntroduce();
        this.type = restImageEntity.getType();
        this.name = artistTable.getName();
        this.immediateShow = restImageEntity.isImmediateShow();
//        this.movieUrl = restImageEntity.getVideoUrl();
//        this.movieLoopCount = restImageEntity.getPlayCount();
    }
}
