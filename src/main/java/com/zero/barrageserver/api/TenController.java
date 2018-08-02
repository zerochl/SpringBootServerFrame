package com.zero.barrageserver.api;

import com.zero.barrageserver.activeapi.service.TenActiveService;
import com.zero.barrageserver.common.annotation.SerializedField;
import com.zero.barrageserver.common.constant.ApiSupportConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.activeresponse.TenResponseEntity;
import com.zero.barrageserver.common.entity.activeresponse.TenResponseEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zero大神 on 2017/12/1.
 */
@RestController
@Log4j2
@RequestMapping(ApiSupportConstant.API_TEN_PREFIX)
public class TenController {
    @Autowired
    private TenActiveService tenActiveService;
    @RequestMapping(method = RequestMethod.GET, value = ApiSupportConstant.GET_GROUP_ID)
    @SerializedField(excludes = {"ActionStatus","MsgSeq","ErrorInfo","ErrorCode"},encode = false)
    public BaseResponseEntity<TenResponseEntity> getGroupId(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
//        String shopName = request.getParameter("shopName");
        log.info("shopId:" + shopId);
        BaseResponseEntity<TenResponseEntity> response = tenActiveService.checkAndReturnGroupId(shopId);
        return response;
    }
}
