package cn.tzmedia.barrageserver.common.entity.apirequest;

import lombok.Data;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Data
public class ApiReqBarrageDevice {
    private String sn;
    private String ip;
    private String version;
    private String shopId;
}
