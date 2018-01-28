package cn.tzmedia.barrageserver.common.entity.servermsg;

import lombok.Data;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
public class RequestHotPointListEntity {
    private int total;
    private List<RequestHotPointEntity> points;
}
