package cn.tzmedia.barrageserver.common.entity.activeresponse;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/21.
 */
@Data
public class TenMemberStatusEntity implements Serializable{
    private String To_Account;
    private String State;
}
