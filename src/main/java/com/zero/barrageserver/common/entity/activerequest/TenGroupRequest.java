package com.zero.barrageserver.common.entity.activerequest;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/1.
 */
@Data
public class TenGroupRequest implements Serializable {
    private String Type;
    private String GroupId;
    private String Name;
    private String ApplyJoinOption;
    public TenGroupRequest(String type, String groupId, String name, String ApplyJoinOption){
        this.Type = type;
        this.GroupId = groupId;
        this.Name = name;
        this.ApplyJoinOption = ApplyJoinOption;
    }
}
