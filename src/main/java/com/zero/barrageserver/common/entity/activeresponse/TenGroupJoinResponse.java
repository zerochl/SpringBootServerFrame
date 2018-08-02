package com.zero.barrageserver.common.entity.activeresponse;


import com.zero.barrageserver.common.entity.TenGroupEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by zero大神 on 2018/1/2.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenGroupJoinResponse extends TenResponseEntity{
    private int TotalCount;
    private List<TenGroupEntity> GroupIdList;
    public TenGroupJoinResponse(int ErrorCode){
        setErrorCode(ErrorCode);
    }
}
