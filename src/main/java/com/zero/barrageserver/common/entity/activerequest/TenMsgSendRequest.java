package com.zero.barrageserver.common.entity.activerequest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
@Data
@AllArgsConstructor
public class TenMsgSendRequest {
    private String GroupId;
    private long Random;
    private List<TenMsgBodyEntity> MsgBody;
}
