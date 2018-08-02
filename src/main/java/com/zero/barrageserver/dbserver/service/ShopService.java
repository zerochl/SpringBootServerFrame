package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.apirequest.ApiReqRestImage;
import com.zero.barrageserver.common.entity.apirequest.ApiReqShowImage;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.ShopResponseEntity;
import com.zero.barrageserver.common.manager.RedisManager;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.dbserver.dao.ShopDao;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import com.zero.barrageserver.dbserver.model.ShopTable;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import com.zero.barrageserver.common.entity.apirequest.ApiReqRestImage;
import com.zero.barrageserver.common.entity.apirequest.ApiReqShowImage;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.ShopResponseEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/4.
 */
@Service
@Log4j2
public class ShopService {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private ActivityService activityService;

    public BaseResponseEntity clearShopCache(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        //删除与店铺相关的活动缓存，因为缓存的活动信息有店铺内容
        redisManager.deletePrefix(CacheConstant.CACHE_TABLE_ACTIVITY_SHOW_FOR_TODAY,shopId);
        //删除店铺For活动
        redisManager.delete(CacheConstant.CACHE_TABLE_SHOP_FOR_ACTIVITY_ID,shopId);
        //删除店铺
        redisManager.delete(CacheConstant.CACHE_TABLE_SHOP,"playbill-true");
        //删除店铺For ShopId
        redisManager.delete(CacheConstant.CACHE_TABLE_SHOP_ID,shopId);
        //删除店铺For ShopName
        //懒得起查对应shopname，直接删除所有
        redisManager.deleteFolder(CacheConstant.CACHE_TABLE_SHOP_NAME);
        //删除Shop设置表
        redisManager.deletePrefix(CacheConstant.CACHE_TABLE_SHOP_SET,shopId);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    public BaseResponseEntity<List<ShopResponseEntity>> getAllShopForChoose(){
//        long startTime = System.nanoTime();
        List<ShopTable> shopList = shopDao.getAllShopForChoose(true);
//        log.info("en get one time:" + (System.nanoTime() - startTime) / 1000000);
        BaseResponseEntity<List<ShopResponseEntity>> responseEntity;
        if(null == shopList){
            responseEntity = new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_RESPONSE_NULL,null);
        }else {
            responseEntity = new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,getShopResponseList(shopList));
        }
        return responseEntity;
    }

    public BaseResponseEntity<ShopResponseEntity> getShopForChoose(String shopId){
        ShopTable shopTable = shopDao.getShopById(shopId);
        BaseResponseEntity<ShopResponseEntity> responseEntity;
        if(null == shopTable){
            responseEntity = new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_RESPONSE_NULL,null);
        }else{
            responseEntity = new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,new ShopResponseEntity(shopTable));
        }
        return responseEntity;
    }

    public BaseResponseEntity updateShowImage(String shopId,List<ApiReqShowImage> showImageList){
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        shopDao.updateShowImage(shopId,getShowImageListFromRequest(showImageList));
        //清除redis缓存
        clearShopCache(shopId);
        //重新推送节目消息
        activityService.pushActivityMsg(shopId,false);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    public BaseResponseEntity updateRestImage(String shopId,List<ApiReqRestImage> restImageList){
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        shopDao.updateRestImage(shopId,getRestmageListFromRequest(restImageList));
        //清除redis缓存
        clearShopCache(shopId);
        //重新推送节目消息
        activityService.pushActivityMsg(shopId,false);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    public List<RestImageEntity> getRestRemoteMovieList(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return null;
        }
        ShopTable shopTable = shopDao.getShopByIdForActivity(shopId);
        if(null == shopTable || null == shopTable.getBarrageRole()){
            return null;
        }
        List<RestImageEntity> restList = shopTable.getBarrageRole().getBarrageRestImage();
        if(null == restList || restList.size() == 0){
            return null;
        }
        List<RestImageEntity> restMovieList = new ArrayList<>();
        for(RestImageEntity restImageEntity:restList){
            if(Constant.SHOW_AND_REST_TYPE_REMOTE_MOVIE.equals(restImageEntity.getType())){
                restMovieList.add(restImageEntity);
            }
        }
        return restMovieList;
    }

    public List<ShowImageEntity> getShowRemoteMovieList(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return null;
        }
        ShopTable shopTable = shopDao.getShopByIdForActivity(shopId);
        if(null == shopTable || null == shopTable.getBarrageRole()){
            return null;
        }

        List<ShowImageEntity> showList = shopTable.getBarrageRole().getBarrageShowImage();
        if(null == showList || showList.size() == 0){
            return null;
        }
        List<ShowImageEntity> showMovieList = new ArrayList<>();
        for(ShowImageEntity showImageEntity:showList){
            if(Constant.SHOW_AND_REST_TYPE_REMOTE_MOVIE.equals(showImageEntity.getType())){
                showMovieList.add(showImageEntity);
            }
        }
        return showMovieList;
    }

    private List<ShopResponseEntity> getShopResponseList(List<ShopTable> shopTableList){
        if(null == shopTableList){
            return null;
        }
        ArrayList<ShopResponseEntity> shopResponseList  = new ArrayList<>();
        ShopResponseEntity shopResponse;
        for(ShopTable shopTable:shopTableList){
            shopResponse = new ShopResponseEntity(shopTable);
            shopResponseList.add(shopResponse);
        }
        return shopResponseList;
    }

    private List<ShowImageEntity> getShowImageListFromRequest(List<ApiReqShowImage> apiReqShowImageList){
        List<ShowImageEntity> showImageEntityList = new ArrayList<>();
        if(null == apiReqShowImageList){
            return showImageEntityList;
        }
        for(ApiReqShowImage apiReqShowImage:apiReqShowImageList){
            ShowImageEntity showImageEntity = new ShowImageEntity(apiReqShowImage);
            showImageEntityList.add(showImageEntity);
        }
        return showImageEntityList;
    }

    private List<RestImageEntity> getRestmageListFromRequest(List<ApiReqRestImage> apiReqRestImageList){
        List<RestImageEntity> restImageEntityList = new ArrayList<>();
        if(null == apiReqRestImageList){
            return restImageEntityList;
        }
        for(ApiReqRestImage apiReqRestImage:apiReqRestImageList){
            RestImageEntity showImageEntity = new RestImageEntity(apiReqRestImage);
            restImageEntityList.add(showImageEntity);
        }
        return restImageEntityList;
    }
}
