package cn.tzmedia.barrageserver.common.entity.response;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.entity.activeresponse.TenMemberStatusEntity;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Data
public class MembersOnLineResponse implements Serializable{
    private List<String> sn_list;
    public MembersOnLineResponse(){
        sn_list = new ArrayList<>();
    }

    public void setOnlineMembers(List<TenMemberStatusEntity> memberStatusList){
        if(null == memberStatusList){
            return;
        }
        for(TenMemberStatusEntity memberStatusEntity:memberStatusList){
            if(Constant.IM_MEMBER_STATUS_ONLINE.equals(memberStatusEntity.getState())){
                sn_list.add(memberStatusEntity.getTo_Account());
            }
        }
    }
}
