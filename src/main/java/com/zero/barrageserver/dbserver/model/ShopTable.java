package com.zero.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/4.
 */
@Document(collection = "shop")
@Data
public class ShopTable implements Serializable,Cloneable {
    @Id
    @Field("_id")
    private String id;
    private String name;
    @Field("barragerole")
    private BarrageRoleEntity barrageRole;
    @Field("playbillrole")
    private PlayBillRoleEntity playBillRole;
    private int order;
    private String endTime;
    private String startTime;
    private String city;
}
