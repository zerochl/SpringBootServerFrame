package com.zero.barrageserver.common.entity.servermsg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestNormalHotMsg extends BaseRequestMsgEntity{
    private int total;
    private List<RequestHotPointEntity> points;
}
