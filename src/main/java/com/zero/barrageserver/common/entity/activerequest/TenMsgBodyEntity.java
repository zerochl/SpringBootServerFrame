package com.zero.barrageserver.common.entity.activerequest;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2017/12/1.
 */
@Data
@AllArgsConstructor
public class TenMsgBodyEntity {
    private String MsgType;
    private TenMsgContentEntity MsgContent;
}
