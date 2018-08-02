package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageDevice;
import com.zero.barrageserver.common.manager.RedisManager;
import com.zero.barrageserver.common.utils.MD5Util;
import com.zero.barrageserver.dbwrite.DBWriteProducer;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import com.zero.barrageserver.dbserver.dao.BarrageDeviceDao;
import com.zero.barrageserver.dbserver.model.BarrageDeviceTable;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageDevice;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.utils.MD5Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Service
@Log4j2
public class BarrageDeviceService {
    @Autowired
    private BarrageDeviceDao barrageDeviceDao;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private DBWriteProducer dbWriteProducer;

    public BaseResponseEntity onQueueHandle(BaseDBWriteEntity dbWriteEntity){
        ApiReqBarrageDevice barrageDevice = JSONObject.parseObject(dbWriteEntity.getWriteValue(),ApiReqBarrageDevice.class);
        BaseResponseEntity responseEntity = null;
        switch (dbWriteEntity.getAction()){
            case Constant.DBWRITE_ACTION_INSERT:
            case Constant.DBWRITE_ACTION_UPDATE:
                responseEntity = updateBarrageDevice(barrageDevice);
                break;
        }
        if(null != responseEntity && responseEntity.getErrorCode() == ErrorConstant.ERROR_CODE_OK){
            redisManager.deleteHash(CacheConstant.CAHCE_DB_WRITE_ASYNC, MD5Util.getMD5String(JSONObject.toJSONString(dbWriteEntity)));
        }else if(dbWriteEntity.getRetryCount() < Constant.DB_WRITE_RETRY_MAX_COUNT){
            dbWriteEntity.setRetryCount(dbWriteEntity.getRetryCount() + 1);
            dbWriteEntity.setWeight(Constant.QUEUE_LEVEL_TWO);
            log.error("retry write in BarrageDeviceService");
            //此处因为不在意写入顺序，所以可以这么做
            dbWriteProducer.receiveExecute(dbWriteEntity,false);
        }else{
            dbWriteEntity.setWeight(Constant.QUEUE_LEVEL_ZERO);
            log.error("retry error in BarrageDeviceService,降低消息等级，等待继续写入");
            //此处因为不在意写入顺序，所以可以这么做
            dbWriteProducer.receiveExecute(dbWriteEntity,false);
        }
        return responseEntity;
    }

    public BaseResponseEntity updateBarrageDeviceForApi(ApiReqBarrageDevice barrageDevice){
        if(null == barrageDevice){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        BaseDBWriteEntity dbWriteEntity = new BaseDBWriteEntity("barrage.device",Constant.DBWRITE_TYPE_BARRAGE,JSONObject.toJSONString(barrageDevice),
                Constant.DBWRITE_ACTION_UPDATE,Constant.QUEUE_LEVEL_ONE);
        dbWriteProducer.receiveExecute(dbWriteEntity,true);
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    public BaseResponseEntity updateBarrageDevice(ApiReqBarrageDevice barrageDevice){
        if(null == barrageDevice){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        long startTime = System.nanoTime();
        BarrageDeviceTable barrageDeviceTable = barrageDeviceDao.getBarrageDevice(barrageDevice.getShopId(),barrageDevice.getSn());
        BarrageDeviceTable result = null;
        //此处消息不需要保持一致性，所以可以使用队列插入
        if(null == barrageDeviceTable){
            //需要执行insert操作
            result = barrageDeviceDao.insert(new BarrageDeviceTable(barrageDevice));
        }else{
            //需要执行update操作
            barrageDeviceTable.reSetData(barrageDevice);
            result = barrageDeviceDao.save(barrageDeviceTable);
        }
        log.error("更新设备结果:" + (System.nanoTime() - startTime) / 1000000);
        if(null == result){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OTHER);
        }else{
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
        }
    }
}
