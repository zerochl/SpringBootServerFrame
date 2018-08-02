package com.zero.barrageserver.common.entity.apirequest;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zero大神 on 2018/1/10.
 */
@Data
public class ApiReqShop implements Serializable{
    private String shopId;
    private List<ApiReqShowImage> showImageList;
    private List<ApiReqRestImage> restImageList;
}
