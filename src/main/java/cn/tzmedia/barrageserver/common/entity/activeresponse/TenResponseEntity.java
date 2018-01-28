package cn.tzmedia.barrageserver.common.entity.activeresponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/1.
 */
@Data
@AllArgsConstructor
public class TenResponseEntity implements Serializable{
    private String ActionStatus;
    private int ErrorCode;
    private String GroupId;
    private String ErrorInfo;
    private int MsgSeq;
    public TenResponseEntity(){}
    public TenResponseEntity(int ErrorCode){
        this.ErrorCode = ErrorCode;
    }

    public void copy(TenResponseEntity tenGroupResponse){
        this.ActionStatus = tenGroupResponse.getActionStatus();
        this.ErrorCode = tenGroupResponse.getErrorCode();
        this.GroupId = tenGroupResponse.getGroupId();
        this.ErrorInfo = tenGroupResponse.getErrorInfo();
        this.MsgSeq = tenGroupResponse.getMsgSeq();
    }
}
