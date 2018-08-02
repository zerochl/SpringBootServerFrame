package com.zero.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Document(collection = "hotPointsTotal")
@Data
public class HotTotalTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    @Field("activityid")
    private String activityId;
    private int teamCode;
    private long total;
}
