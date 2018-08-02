package com.zero.barrageserver.dbserver.model;

import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageMovie;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageMovie;
import com.zero.barrageserver.common.utils.StringUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zero大神 on 2018/1/8.
 * 因为当初设计动画时并没有考虑创建资源表，导致视频只能新增一个表来存储，如果重构，此处可以节约一个表
 */
@Document(collection = "barrage.movie")
@Data
public class BarrageMovieTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    private String thumbnail;
    @Field("video_url")
    private String videoUrl;
    @Field("video_res_id")
    private String videoResId;
    private String name;
    @Field("update_time")
    private Date updateTime;
    @Field("create_time")
    private Date createTime;
    public BarrageMovieTable(){}
    public BarrageMovieTable(ApiReqBarrageMovie barrageMovie){
        if(!StringUtil.isEmpty(barrageMovie.getId())){
            this.id = barrageMovie.getId();
        }
        this.thumbnail = barrageMovie.getThumbnail();
        this.videoUrl = barrageMovie.getVideoUrl();
        this.videoResId = barrageMovie.getVideoResId();
        this.name = barrageMovie.getName();
    }

    public void reSetData(BarrageMovieTable movieTable){
        this.thumbnail = movieTable.getThumbnail();
        this.videoUrl = movieTable.getVideoUrl();
        this.videoResId = movieTable.getVideoResId();
        this.name = movieTable.getName();
        if(this.createTime == null){
            this.createTime = new Date();
        }
        this.updateTime = new Date();
    }

    public void prepareForInsert(){
        this.createTime = new Date();
    }
}
