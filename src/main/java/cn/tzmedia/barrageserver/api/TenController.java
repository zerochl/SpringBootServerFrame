package cn.tzmedia.barrageserver.api;

import cn.tzmedia.barrageserver.activeapi.service.TenActiveService;
import cn.tzmedia.barrageserver.common.annotation.SerializedField;
import cn.tzmedia.barrageserver.common.constant.ApiSupportConstant;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import cn.tzmedia.barrageserver.common.entity.activeresponse.TenResponseEntity;
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
