package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.manager.RedisManager;
import com.zero.barrageserver.common.utils.MD5Util;
import com.zero.barrageserver.dbwrite.DBWriteProducer;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.utils.MD5Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Service
@Log4j2
public abstract class BaseDBWriteService<T> {

    @Autowired
    private RedisManager redisManager;
    @Autowired
    private DBWriteProducer dbWriteProducer;

    @Deprecated
    public BaseResponseEntity updateWithQueue(T barrageDevice){
        if(null == barrageDevice){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        BaseDBWriteEntity dbWriteEntity = new BaseDBWriteEntity("barrage.device",Constant.DBWRITE_TYPE_BARRAGE,JSONObject.toJSONString(barrageDevice),
                Constant.DBWRITE_ACTION_UPDATE,Constant.QUEUE_LEVEL_ONE);
        dbWriteProducer.receiveExecute(dbWriteEntity,true);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    /**
     * 执行队列写入或者update操作
     * @param dbWriteEntity
     * @return
     */
    @Deprecated
    public BaseResponseEntity doQueueHandle(BaseDBWriteEntity dbWriteEntity){
        T barrageDevice = getEntity(dbWriteEntity);
        BaseResponseEntity responseEntity = null;
        switch (dbWriteEntity.getAction()){
            case Constant.DBWRITE_ACTION_INSERT:
            case Constant.DBWRITE_ACTION_UPDATE:
                responseEntity = updateAndInsert(barrageDevice);
                break;
        }
        if(null != responseEntity && responseEntity.getErrorCode() == ErrorConstant.ERROR_CODE_OK){
            redisManager.deleteHash(CacheConstant.CAHCE_DB_WRITE_ASYNC, MD5Util.getMD5String(JSONObject.toJSONString(dbWriteEntity)));
        }else if(dbWriteEntity.getRetryCount() < Constant.DB_WRITE_RETRY_MAX_COUNT){
            dbWriteEntity.setRetryCount(dbWriteEntity.getRetryCount() + 1);
            dbWriteEntity.setWeight(Constant.QUEUE_LEVEL_TWO);
            log.error("retry write in onQueueHandle");
            //此处因为不在意写入顺序，所以可以这么做
            dbWriteProducer.receiveExecute(dbWriteEntity,false);
        }else{
            dbWriteEntity.setWeight(Constant.QUEUE_LEVEL_ZERO);
            log.error("retry error in onQueueHandle,降低消息等级，等待继续写入");
            //此处因为不在意写入顺序，所以可以这么做
            dbWriteProducer.receiveExecute(dbWriteEntity,false);
        }
        return responseEntity;
    }

    public abstract BaseResponseEntity updateAndInsert(T t);

    public abstract BaseResponseEntity delete(T t);

    public abstract T getEntity(BaseDBWriteEntity dbWriteEntity);
}
