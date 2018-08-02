package com.zero.barrageserver.common.manager;

import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.service.ActivityService;
import com.zero.barrageserver.dbserver.model.ActivityTable;
import com.zero.barrageserver.dbserver.service.ActivityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理节目消息轮训推送
 * Created by zero大神 on 2017/12/15.
 */
@Component
@Log4j2
public class ProgramCountDownSendManager {
    @Autowired
    private TimerManager timerManager;
    @Autowired
    private ActivityService activityService;
    private ConcurrentHashMap<String,Runnable> taskMap = new ConcurrentHashMap<>();
    public void onSendProgramMsg(String shopId, ActivityTable nowActivity, ActivityTable nextActivity){
        if(null == nowActivity && null == nextActivity){
            log.info("no need addCountDown");
            removeTask(shopId);
            return;
        }
        Date deadLine;
        if(null != nowActivity){
            deadLine = nowActivity.getEndTime();
        }else{
            deadLine = nextActivity.getStartTime();
        }
        long delayTime = deadLine.getTime() - new Date().getTime() + 1000;//相差1秒间隔，防止误差
        log.info("next push time:" + delayTime);
        addCountDown(shopId,delayTime);
    }

    private void addCountDown(String shopId,long delay){
        Runnable task = getTask(shopId);
        timerManager.removeFaster(task);
        timerManager.postFaster(task,delay);
    }

    private void removeTask(String shopId){
        Runnable task = getTask(shopId);
        timerManager.removeFaster(task);
    }

    private Runnable getTask(String shopId){
        if(taskMap.containsKey(shopId)){
            return taskMap.get(shopId);
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //节目切换，必须拉取历史数据
                activityService.getNowActivityByShopId(shopId,true);
            }
        };
        taskMap.put(shopId,task);
        return task;
    }
}
