package com.zero.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Document(collection = "showHistory")
@Data
public class ShowHistoryTable implements Serializable{
    @Field("_id")
    private String id;
    @Field("msgtype")
    private String msgType;
    @Field("activityid")
    private String activityId;
    private String value;
    @Field("createdate")
    private Date createTime;
    private int totalCount;
    private double totalPrice;
}
