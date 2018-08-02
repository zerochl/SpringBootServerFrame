package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.manager.RedisManager;
import com.zero.barrageserver.common.utils.DateUtils;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.message.entity.MsgItemActivity;
import com.zero.barrageserver.message.entity.ResponseProgramMsg;
import com.zero.barrageserver.dbserver.dao.ActivityDao;
import com.zero.barrageserver.dbserver.dao.ShopDao;
import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.model.ShopTable;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.utils.DateUtils;
import com.zero.barrageserver.common.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 活动管理
 * Created by zero大神 on 2017/12/12.
 */
@Service
@Log4j2
public class ActivityService {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private ProgramService programService;
    @Autowired
    private ArtistService artistService;

    public BaseResponseEntity clearActivityCache(String shopId, String activityId){
        if(StringUtil.isEmpty(shopId) || StringUtil.isEmpty(activityId)){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        //删除与店铺相关的活动缓存
        redisManager.deletePrefix(CacheConstant.CACHE_TABLE_ACTIVITY_SHOW_FOR_TODAY,shopId);
        //删除ActivityID保存的缓存
        redisManager.delete(CacheConstant.CACHE_TABLE_ACTIVITY_ID,activityId);

        //活动改变需触发节目重新推送,清理数据，暂时默认设置为不拉取历史数据
        pushActivityMsg(shopId,false);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    public void pushActivityMsg(String shopId,boolean needPullHistory){
        getNowActivityByShopId(shopId,needPullHistory);
    }

    /**
     * 获取当前店铺应该显示的节目信息，不会立即返回，会通过IM形式发送
     * @param shopId
     * @return
     */
    public BaseResponseEntity<ResponseProgramMsg> getNowActivityByShopId(String shopId,boolean needPullHistory){
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OTHER);
        }
        ShopTable shopTable = shopDao.getShopByIdForActivity(shopId);
        if(null == shopTable){
            log.info("shop table is null");
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_RESPONSE_SHOP_NULL);
        }
        log.info("shop name:" + shopTable.getName());
        int startHours = getStartHours(DateUtils.getUtcTime(shopTable.getStartTime()),DateUtils.getUtcTime(shopTable.getEndTime()));
        List<ActivityTable> activityTableList = activityDao.getListByShopIdAndTimeSlot(shopId,
                getTodayStartTime(startHours),getTodayEndTime(startHours));
        if(null == activityTableList){
            log.info("activity table is null");
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_RESPONSE_ACTIVITY_NULL);
        }
        log.info("activity table size:" + activityTableList.size());
        ActivityTable nowActivity = getNowActivity(activityTableList);
        ActivityTable nextActivity = getNextActivity(activityTableList);
        programService.sendProgramMsg(nowActivity,nextActivity,shopTable,needPullHistory);
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK);
    }

    public ActivityTable getNowActivity(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return null;
        }
        ShopTable shopTable = shopDao.getShopByIdForActivity(shopId);
        if(null == shopTable){
            return null;
        }
        int startHours = getStartHours(DateUtils.getUtcTime(shopTable.getStartTime()),DateUtils.getUtcTime(shopTable.getEndTime()));
        List<ActivityTable> activityTableList = activityDao.getListByShopIdAndTimeSlot(shopId,
                getTodayStartTime(startHours),getTodayEndTime(startHours));
        if(null == activityTableList){
            return null;
        }
        return getNowActivity(activityTableList);
    }

    public String getShopIdByActivityId(String activityId){
        if(StringUtil.isEmpty(activityId)){
            return "";
        }
        ActivityTable activityTable = activityDao.getActivityById(activityId);
        if(null == activityTable){
            return "";
        }
        return activityTable.getShopId();
    }

    /**
     * 根据当前节目数据，获取节目消息体
     * @param nowActivity
     * @return
     */
    public MsgItemActivity getActivityForMsg(ActivityTable nowActivity){
        if(null == nowActivity){
            return null;
        }
        MsgItemActivity msgItemActivity = new MsgItemActivity(nowActivity);
        msgItemActivity = artistService.equipActivity(nowActivity,msgItemActivity);
        return msgItemActivity;
    }

    private ActivityTable getNowActivity(List<ActivityTable> activityTableList){
        if(null == activityTableList || activityTableList.size() == 0){
            return null;
        }
        Date nowDate = new Date();
        for(ActivityTable activityTable:activityTableList){
            if(activityTable.getStartTime().getTime() < nowDate.getTime() && activityTable.getEndTime().getTime() > nowDate.getTime()){
                return activityTable;
            }
        }
        return null;
    }

    private ActivityTable getNextActivity(List<ActivityTable> activityTableList){
        if(null == activityTableList || activityTableList.size() == 0){
            return null;
        }
        Date nowDate = new Date();
        for(ActivityTable activityTable:activityTableList){
            if(activityTable.getStartTime().getTime() > nowDate.getTime()){
                return activityTable;
            }
        }
        return null;
    }

    /**
     * 酒吧特殊性，开始时间与结束时间非零点*/
    public Date getTodayStartTime(int hours){
        Calendar calendar = Calendar.getInstance();
        Date newDate = new Date();
        if(newDate.getHours() < hours){
            //如果当前时间的小时数小于店铺的开始时间数，那么说明当天还没结束，需要减去24小时
            newDate.setHours(newDate.getHours() - 24);
        }
        calendar.setTime(newDate);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 酒吧特殊性，开始时间与结束时间非零点*/
    public Date getTodayEndTime(int hours){
        Calendar calendar = Calendar.getInstance();

        Date newDate = new Date();
        if(newDate.getHours() < hours){
            //如果当前时间的小时数小于店铺的开始时间数，那么说明当天还没结束，需要减去24小时
            newDate.setHours(newDate.getHours() - 24);
        }
        calendar.setTime(newDate);
        calendar.set(Calendar.HOUR_OF_DAY, hours + 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 如果开始时间小于结束时间那结束时间就属于当天，如果开始时间大于结束时间则结束时间属于下一天
     * @param startTime
     * @param endTime
     * @return 此时间为当天店铺开始的时间，如果店铺结束时间在当天，那么当天开始时间就为0点
     */
    private int getStartHours(Date startTime,Date endTime){
        int hours;
        if(null == startTime || endTime == null){
            hours = 3;
        }else if(startTime.getHours() < endTime.getHours()){
            //结束时间为当天
            hours = 0;
        }else{
            //结束时间为下一天
            hours = endTime.getHours();
        }
        return hours;
    }
}
