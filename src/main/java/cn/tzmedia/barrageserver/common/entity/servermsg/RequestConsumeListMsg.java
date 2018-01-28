package cn.tzmedia.barrageserver.common.entity.servermsg;

import lombok.Data;

import java.util.List;

/**
 * Created by zero大神 on 2018/1/22.
 */
@Data
public class RequestConsumeListMsg extends BaseRequestMsgEntity{
    private int total;
    private List<RequestConsumeEntity> consumeList;
}
