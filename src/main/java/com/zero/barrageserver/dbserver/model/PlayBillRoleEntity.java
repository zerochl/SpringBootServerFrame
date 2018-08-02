package com.zero.barrageserver.dbserver.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/4.
 */
@Data
public class PlayBillRoleEntity implements Serializable ,Cloneable{
//    @Field("songtime")
//    private String songTime;
//    @Field("songendtime")
//    private int songEndTime;
//    @Field("songstarttime")
//    private int songStartTime;
    private boolean enabled;
}
