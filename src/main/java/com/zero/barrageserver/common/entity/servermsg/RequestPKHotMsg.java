package com.zero.barrageserver.common.entity.servermsg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestPKHotMsg extends BaseRequestMsgEntity{
    private List<RequestHotPointListEntity> hotPoints;
}
