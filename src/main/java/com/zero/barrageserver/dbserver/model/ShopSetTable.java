package com.zero.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/22.
 */
@Document(collection = "barrage.shop_settings")
@Data
public class ShopSetTable implements Serializable{
    @Field("key")
    private String setName;
    @Field("val")
    private String setValue;
    @Field("shop_id")
    private String shopId;
}
