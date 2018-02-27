package cn.tzmedia.barrageserver.common.entity.apirequest;

import cn.tzmedia.barrageserver.dbserver.model.BarrageMovieTable;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Data
public class ApiReqBarrageMovie implements Serializable{
    private String id;
    private String thumbnail;
    private String videoUrl;
    private String videoResId;
    private String name;
    public ApiReqBarrageMovie(){}
    public ApiReqBarrageMovie(BarrageMovieTable barrageMovieTable){
        this.id = barrageMovieTable.getId();
        this.thumbnail = barrageMovieTable.getThumbnail();
        this.videoUrl = barrageMovieTable.getVideoUrl();
        this.videoResId = barrageMovieTable.getVideoResId();
        this.name = barrageMovieTable.getName();
    }
}
