package com.zero.barrageserver.common.entity.activerequest;

import lombok.Data;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Data
public class TenGroupMembersRequest {
    private String GroupId;
    public TenGroupMembersRequest(String groupId){
        this.GroupId = groupId;
    }
}
