package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.utils.UrlUtils;
import cn.tzmedia.barrageserver.server.model.ArtistTable;
import cn.tzmedia.barrageserver.server.model.RestImageEntity;
import com.google.gson.annotations.SerializedName;
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
