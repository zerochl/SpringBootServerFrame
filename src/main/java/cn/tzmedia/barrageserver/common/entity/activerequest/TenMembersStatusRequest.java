package cn.tzmedia.barrageserver.common.entity.activerequest;

import cn.tzmedia.barrageserver.common.entity.activeresponse.TenMemberEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Data
public class TenMembersStatusRequest {
    private List<String> To_Account;
    public TenMembersStatusRequest(List<TenMemberEntity> memberList){
        if(null == memberList){
            return;
        }
        To_Account = new ArrayList<>();
        for(TenMemberEntity memberEntity:memberList){
            To_Account.add(memberEntity.getMember_Account());
        }
    }
}
