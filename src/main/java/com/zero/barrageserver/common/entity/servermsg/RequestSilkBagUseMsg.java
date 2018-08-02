package com.zero.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestSilkBagUseMsg extends BaseRequestMsgEntity{
    private String suffix;
    private String teamName;
    private int teamCode;
    private String giftContent;
    @JSONField(name = "kitid")
    private String silkBagId;
    @JSONField(name = "kitimage")
    private String silkBagImage;
    @JSONField(name = "kittype")
    private int silkBagType;
    private int timeout;
    private String ratio;
    @JSONField(name = "kitname")
    private String silkBagName;
    @JSONField(name = "okitimage")
    private String bigSilkBagImage;
    @JSONField(name = "pktype")
    private String pkType;
}
