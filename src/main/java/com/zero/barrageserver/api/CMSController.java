package com.zero.barrageserver.api;

import com.zero.barrageserver.activeapi.service.TenActiveService;
import com.zero.barrageserver.common.annotation.SerializedField;
import com.zero.barrageserver.common.constant.ApiSupportConstant;
import com.zero.barrageserver.common.entity.apirequest.*;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.MembersOnLineResponse;
import com.zero.barrageserver.message.entity.ResponseProgramMsg;
import com.zero.barrageserver.dbserver.service.*;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageConfig;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageMovie;
import com.zero.barrageserver.common.entity.apirequest.ApiReqShop;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.MembersOnLineResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zero大神 on 2017/12/13.
 */

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping(ApiSupportConstant.API_CMS_PREFIX)
public class CMSController {
    private ShopService shopService;
    private ActivityService activityService;
    private TenActiveService tenActiveService;
    private BarrageConfigService barrageConfigService;
    private BarrageMovieService barrageMovieService;
    private ShowHistoryService showHistoryService;
    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.SHOP_CHANGE)
    @SerializedField(encode = false)
    public BaseResponseEntity changeShop(@RequestParam(required = false) String shopId){
        log.info("shopId:" + shopId);
        return shopService.clearShopCache(shopId);
    }
    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.ACTIVITY_CHANGE)
    @SerializedField(encode = false)
    public BaseResponseEntity changeActivity(@RequestParam(required = false) String shopId, @RequestParam(required = false) String activityId, HttpServletRequest request){
        log.info("shopId:" + shopId + ";activityId:" + activityId + ";httpServletRequest:" + request);
        return activityService.clearActivityCache(shopId,activityId);
    }
    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.GET_GROUP_ONLINE_MEMBERS)
    @SerializedField(encode = false)
    public MembersOnLineResponse getShopDeviceOnLine(@RequestParam(name = "shop_id",required = false) String shopId){
        log.info("in getShopDeviceOnLine shopId:" + shopId);
        return tenActiveService.getOnLineMembers(shopId);
    }

    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.SHOP_BARRAGE_CONFIG)
    @SerializedField(encode = false)
    public BaseResponseEntity<ApiReqBarrageConfig> getShopBarrageConfig(@RequestParam(required = false)String shopId){
        log.info("in getShopBarrageConfig shopId:" + shopId);
        return barrageConfigService.getBarrageConfigForRequest(shopId);
    }

    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.SHOP_BARRAGE_CONFIG)
    @SerializedField(encode = false)
    public BaseResponseEntity updateShopBarrageConfig(@RequestBody ApiReqBarrageConfig barrageConfig){
        log.info("in updateShopBarrageConfig barrageConfig barrageConfig:" + barrageConfig);
        return barrageConfigService.updateOrInsertBarrageConfig(barrageConfig);
    }

    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.SHOP_BARRAGE_MOVIE)
    @SerializedField(encode = false)
    public BaseResponseEntity<List<ApiReqBarrageMovie>> getShopBarrageMovie(){
        log.info("in getShopBarrageMovie");
        return barrageMovieService.getAllBarrageMovie();
    }

    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.SHOP_BARRAGE_MOVIE)
    @SerializedField(encode = false)
    public BaseResponseEntity updateShopBarrageMovie(@RequestBody ApiReqBarrageMovie barrageMovie){
        log.info("in updateShopBarrageMovie barrageMovie:" + barrageMovie);
        return barrageMovieService.updateOrInsertBarrageMovie(barrageMovie);
    }

    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.SHOP_BARRAGE_SHOW_IMAGE)
    @SerializedField(encode = false)
    public BaseResponseEntity updateBarrageShowImage(@RequestBody ApiReqShop apiReqShop){
        log.info("in updateBarrageShowImage shopId:" + apiReqShop.getShopId());
        return shopService.updateShowImage(apiReqShop.getShopId(),apiReqShop.getShowImageList());
    }

    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.SHOP_BARRAGE_REST_IMAGE)
    @SerializedField(encode = false)
    public BaseResponseEntity updateBarrageRestImage(@RequestBody ApiReqShop apiReqShop){
        log.info("in updateBarrageRestImage shopId:" + apiReqShop.getShopId());
        //@RequestParam(required = false)String shopId, @RequestParam(required = false)List<ApiReqRestImage> restImageList
        return shopService.updateRestImage(apiReqShop.getShopId(),apiReqShop.getRestImageList());
    }

    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.HISTORY_MSG_FOR_PAG)
    @SerializedField(encode = false)
    public BaseResponseEntity<ResponseProgramMsg> getPadHistory(@RequestParam String shopId){
        log.info("in getPadHistory activityId:" + shopId);
        return showHistoryService.getPadHistoryMsgForRequest(shopId);
    }

    /**
     * 输出所有表单提交的参数
     * @param request
     */
    private void printAllParam(HttpServletRequest request){
        if(null == request){
            return;
        }
        Map<String,String[]> params = request.getParameterMap();
        Iterator<Map.Entry<String,String[]>> it = params.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String[]> entry = it.next();
            for(int i = 0;i < entry.getValue().length;i++){
                log.error("name:" + entry.getKey() + ";value:" + entry.getValue()[i]);
            }
        }
    }

}
