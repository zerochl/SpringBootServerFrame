package com.zero.barrageserver.common.entity;

import lombok.Data;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
public class VersionEntity {
    private String _id;
    private String barrage;
    private int barragecode;
    private String barragecontent;
    private boolean barrageisupdate;
    private boolean barrageshowupdate;
    private String barragesize;
    private String barrageurl;
    private String barragewebcode;
    private boolean barrageForceAnimUpdate;//强制动画更新
}
