package com.zero.barrageserver.dbwrite;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.iface.IQueueObserver;
import com.zero.barrageserver.common.manager.ExecutorManager;
import com.zero.barrageserver.common.manager.RedisManager;
import com.zero.barrageserver.common.manager.TimerManager;
import com.zero.barrageserver.common.utils.MD5Util;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import com.zero.barrageserver.dbwrite.iface.DBWriteObserver;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.iface.IQueueObserver;
import com.zero.barrageserver.common.utils.MD5Util;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Vector;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Component
@Log4j2
public class DBWriteProducer implements ServletContextAware{
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private TimerManager timerManager;

    private Vector<IQueueObserver<BaseDBWriteEntity>> writeObserverList = new Vector<>();

    public void registerObserver(IQueueObserver<BaseDBWriteEntity> msgObserver){
        writeObserverList.add(msgObserver);
    }

    public void unregisterObserver(IQueueObserver<BaseDBWriteEntity> msgObserver){
        writeObserverList.remove(msgObserver);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        //系统启动，从redis中获取未完成的任务继续执行
        //延时启动，此操作必须等项目完全启动OK
        timerManager.postFaster(new Runnable() {
            @Override
            public void run() {
                collectCacheExecute();
            }
        },10 * 1000);
    }

    private void collectCacheExecute(){
        List<String> cacheWriteList = redisManager.getCacheHashTable(CacheConstant.CAHCE_DB_WRITE_ASYNC);
        for(String cacheWrite:cacheWriteList){
            receiveExecute(JSONObject.parseObject(cacheWrite,BaseDBWriteEntity.class),false);
        }
    }

    /**
     * 注意，不支持事务，如果需要事务千万不要使用此方式，且暂时只支持单表写入，如果某个操作需要多表插入，可能会造成同步问题，
     * 风险太大，后来者如果要做支持，请在使用时多加考虑
     * @param dbWriteEntity
     * @param needAddToCache
     */
    public void receiveExecute(BaseDBWriteEntity dbWriteEntity, boolean needAddToCache){
        //因为就算在请求中添加到redis，也没有返回值，此处最好能提前添加至redis并知道添加失败还是成功
//        if(needAddToCache){
//            String saveStr = JSONObject.toJSONString(dbWriteEntity);
//            String md5 = MD5Util.getMD5String(saveStr);
//            log.error("md5:" + md5);
//            redisManager.setCacheWithHash(CacheConstant.CAHCE_DB_WRITE_ASYNC, md5, saveStr);
//        }
        //lamda格式，请注意
        Observable.create(observableEmitter -> dispatchMessage(dbWriteEntity,needAddToCache))
                //单线程池接收，保证有序消息可以有序插入队列（消息队列插入并不耗时）
                .subscribeOn(Schedulers.from(ExecutorManager.getSingleExecutorByKey(Constant.SINGLE_THREAD_EXECUTOR_TYPE_DBWRITE)))
//                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void dispatchMessage(BaseDBWriteEntity dbWriteEntity,boolean needAddToCache){
        //write to redis
        if(needAddToCache){
            String saveStr = JSONObject.toJSONString(dbWriteEntity);
            String md5 = MD5Util.getMD5String(saveStr);
            log.error("md5:" + md5);
            redisManager.setCacheWithHash(CacheConstant.CAHCE_DB_WRITE_ASYNC, md5, saveStr);
        }
        for(IQueueObserver<BaseDBWriteEntity> msgObserver:writeObserverList){
            msgObserver.receiveMessage(dbWriteEntity.getType(),dbWriteEntity);
        }
    }
}
