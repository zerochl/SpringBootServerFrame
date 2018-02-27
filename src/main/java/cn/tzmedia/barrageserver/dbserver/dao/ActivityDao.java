package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.constant.CacheConstant;
import cn.tzmedia.barrageserver.dbserver.model.ActivityTable;
import cn.tzmedia.barrageserver.dbserver.repository.ActivityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
/**
 * Created by zero大神 on 2017/12/12.
 */
@Component
@Log4j2
public class ActivityDao {
    @Autowired
    private ActivityRepository activityRepository;
    @Cacheable(value = CacheConstant.CACHE_TABLE_ACTIVITY_SHOW_FOR_TODAY,key = "#shopId + #startTime.getYear() + #startTime.getMonth() + #startTime.getDate()")
    public List<ActivityTable> getListByShopIdAndTimeSlot(String shopId, Date startTime, Date endTime){
        log.info("get from db");
        return activityRepository.findByShopIdAndAndStartTimeAfterAndAndEndTimeBeforeOrderByStartTime(shopId,startTime,endTime);
    }
    @Cacheable(value = CacheConstant.CACHE_TABLE_ACTIVITY_ID,key = "#activityId")
    public ActivityTable getActivityById(String activityId){
        log.info("get from db");
        return activityRepository.findById(activityId);
    }
}
