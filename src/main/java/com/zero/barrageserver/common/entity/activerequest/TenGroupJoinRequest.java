package com.zero.barrageserver.common.entity.activerequest;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2018/1/2.
 */
@Data
public class TenGroupJoinRequest implements Serializable{
    private String Member_Account;
    public TenGroupJoinRequest(){}
    public TenGroupJoinRequest(String userAccount){
        this.Member_Account = userAccount;
    }
}
