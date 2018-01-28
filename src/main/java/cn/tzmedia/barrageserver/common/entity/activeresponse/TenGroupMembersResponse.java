package cn.tzmedia.barrageserver.common.entity.activeresponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenGroupMembersResponse extends TenResponseEntity{
    private int MemberNum;
    private List<TenMemberEntity> MemberList;
    public TenGroupMembersResponse(int errorCode){
        setErrorCode(errorCode);
    }
}
