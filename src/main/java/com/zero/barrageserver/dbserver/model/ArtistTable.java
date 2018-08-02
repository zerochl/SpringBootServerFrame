package com.zero.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Document(collection = "artist")
@Data
public class ArtistTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    @Field("dmimage")
    private List<String> showBarrageImage;
    @Field("image")
    private List<String> imageList;
    private String introduce;
    private String experience;
    private String name;
    private String sign;
    private List<String> tag;
}
