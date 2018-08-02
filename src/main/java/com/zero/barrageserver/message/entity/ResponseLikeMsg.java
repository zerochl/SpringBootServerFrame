package com.zero.barrageserver.message.entity;

import com.zero.barrageserver.common.entity.servermsg.RequestLikeMsg;
import com.zero.barrageserver.common.entity.servermsg.RequestLikeMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseLikeMsg extends BaseResponseMsg{
    public ResponseLikeMsg(){}
    public ResponseLikeMsg(RequestLikeMsg requestLikeMsg){
        this.activityId = requestLikeMsg.getActivityId();
        this.setType(requestLikeMsg.getMsgType());

        setUser(requestLikeMsg);
    }
}
