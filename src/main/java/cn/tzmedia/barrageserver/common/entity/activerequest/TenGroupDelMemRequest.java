package cn.tzmedia.barrageserver.common.entity.activerequest;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2018/1/2.
 */
@Data
public class TenGroupDelMemRequest implements Serializable{
    private String GroupId;
    private List<String> MemberToDel_Account;
    private int Silence;// 是否静默删除（选填）
    public TenGroupDelMemRequest(String groupId,String userAccount){
        this.GroupId = groupId;
        MemberToDel_Account = new ArrayList<>();
        MemberToDel_Account.add(userAccount);
    }
}
