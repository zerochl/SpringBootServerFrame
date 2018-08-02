package com.zero.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestAwardMsg extends BaseRequestMsgEntity{
    @JSONField(name = "artistid")
    private String artistId;
    @JSONField(name = "awardimage")
    private String awardImage;
    @JSONField(name = "artistname")
    public String artistName;
    private String teamName;
    private int teamCode;
    private int contentType;
    @JSONField(name = "pktype")
    private String pkType;
    private double price;
    private int giftType;
    private int gifCount;
    @JSONField(name = "vpname")
    private String vpName;
    private String gifImage;
    private String gifType;
    private boolean isFullAnimation;
}
