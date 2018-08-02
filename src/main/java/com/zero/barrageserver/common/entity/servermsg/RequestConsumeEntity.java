package com.zero.barrageserver.common.entity.servermsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by zero大神 on 2018/1/22.
 */
@Data
public class RequestConsumeEntity {
    private double consumeAmount;
    private String userRole;
    private String usertoken;
    private String userImage;
    private String userName;
    private String userHeadBg;
    private String userHeadGifBg;
    private int levelRange;
    private int level;
}
