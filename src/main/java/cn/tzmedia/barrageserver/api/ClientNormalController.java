package cn.tzmedia.barrageserver.api;

import cn.tzmedia.barrageserver.activeapi.service.TenActiveService;
import cn.tzmedia.barrageserver.common.annotation.SerializedField;
import cn.tzmedia.barrageserver.common.constant.ApiSupportConstant;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import cn.tzmedia.barrageserver.common.entity.response.ShopResponseEntity;
import cn.tzmedia.barrageserver.common.entity.apirequest.ApiReqBarrageDevice;
import cn.tzmedia.barrageserver.message.entity.ResponseProgramMsg;
import cn.tzmedia.barrageserver.server.service.ActivityService;
import cn.tzmedia.barrageserver.server.service.BarrageDeviceService;
import cn.tzmedia.barrageserver.server.service.DownloadFileService;
import cn.tzmedia.barrageserver.server.service.ShopService;
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
