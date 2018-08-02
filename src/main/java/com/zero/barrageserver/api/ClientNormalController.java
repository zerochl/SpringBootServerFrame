package com.zero.barrageserver.api;

import com.zero.barrageserver.activeapi.service.TenActiveService;
import com.zero.barrageserver.common.annotation.SerializedField;
import com.zero.barrageserver.common.constant.ApiSupportConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.ShopResponseEntity;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageDevice;
import com.zero.barrageserver.message.entity.ResponseProgramMsg;
import com.zero.barrageserver.dbserver.service.ActivityService;
import com.zero.barrageserver.dbserver.service.BarrageDeviceService;
import com.zero.barrageserver.dbserver.service.DownloadFileService;
import com.zero.barrageserver.dbserver.service.ShopService;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageDevice;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.ShopResponseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/4.
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping(ApiSupportConstant.API_CLIENT_PREFIX)
public class ClientNormalController {
    private ShopService shopService;
    private ActivityService activityService;
    private BarrageDeviceService barrageDeviceService;
    private DownloadFileService downloadFileService;
    private TenActiveService tenActiveService;
    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.GET_ALL_SHOP_FOR_CHOOSE)
    @SerializedField(encode = false)
    public BaseResponseEntity<List<ShopResponseEntity>> getAllShopForChoose(){
        log.info("in get all shop for choose");
        return shopService.getAllShopForChoose();
    }

    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.GET_SHOP_FOR_CHOOSE)
    @SerializedField(encode = false)
    public BaseResponseEntity<ShopResponseEntity> getShopForChoose(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
        return shopService.getShopForChoose(shopId);
    }

    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.GET_PROGRAM_INFO)
    @SerializedField(encode = false)
    public BaseResponseEntity<ResponseProgramMsg> pullActivityInfo(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
        //客户端主动请求，必须返回历史数据
        return activityService.getNowActivityByShopId(shopId,true);
    }
    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.POST_BARRAGE_DEVICE_UPDATE)
    @SerializedField(encode = false)
    public BaseResponseEntity updateBarrageDevice(@RequestBody ApiReqBarrageDevice barrageDevice){
        return barrageDeviceService.updateBarrageDeviceForApi(barrageDevice);
    }
    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.GET_BOOT_STRAP_DOWNLOAD_FILE)
    @SerializedField(encode = false)
    public BaseResponseEntity getBootStrapDownloadFile(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
        return downloadFileService.getBootStrapFileForRequest(shopId);
    }
    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.LEAVE_OTHER_GROUP)
    @SerializedField(encode = false)
    public BaseResponseEntity leaveOtherGroup(HttpServletRequest request){
        String groupId = request.getParameter("groupId");
        String userCount = request.getParameter("userId");
        return tenActiveService.leaveOtherGroup(groupId,userCount);
    }

}
