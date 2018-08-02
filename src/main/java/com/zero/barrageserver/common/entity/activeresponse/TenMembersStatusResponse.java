package com.zero.barrageserver.common.entity.activeresponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenMembersStatusResponse extends TenResponseEntity{
    private List<TenMemberStatusEntity> QueryResult;
    public TenMembersStatusResponse(int errorCode){
        setErrorCode(errorCode);
    }
}
