package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageConfig;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.dbwrite.entity.BaseDBWriteEntity;
import com.zero.barrageserver.dbserver.dao.BarrageConfigDao;
import com.zero.barrageserver.dbserver.model.BarrageConfigTable;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.entity.apirequest.ApiReqBarrageConfig;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Service
public class BarrageConfigService extends BaseDBWriteService<BarrageConfigTable>{
    @Autowired
    private BarrageConfigDao barrageConfigDao;

    /**
     * 更新或者创建记录
     * @param barrageConfig
     * @return
     */
    public BaseResponseEntity updateOrInsertBarrageConfig(ApiReqBarrageConfig barrageConfig){
        if(null == barrageConfig || StringUtil.isEmpty(barrageConfig.getShopId())){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        //写入需要立即获取到数据，所以，此处不使用队列
        return updateAndInsert(new BarrageConfigTable(barrageConfig));
    }

    public BaseResponseEntity<ApiReqBarrageConfig> getBarrageConfigForRequest(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        BarrageConfigTable barrageConfigTable = getBarrageConfig(shopId);
        if(null == barrageConfigTable){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK);
        }else{
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,new ApiReqBarrageConfig(barrageConfigTable));
        }
    }

    public BarrageConfigTable getBarrageConfig(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return null;
        }
        return barrageConfigDao.getShopBarrageConfig(shopId);
    }

    @Override
    public BaseResponseEntity updateAndInsert(BarrageConfigTable barrageConfigTable) {
        if(null == barrageConfigTable){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
//        long startTime = System.nanoTime();
        BarrageConfigTable barrageOldConfigTable = barrageConfigDao.getShopBarrageConfig(barrageConfigTable.getShopId());
        BarrageConfigTable result = null;
        //此处消息不需要保持一致性，所以可以使用队列插入
        if(null == barrageOldConfigTable){
            //需要执行insert操作
            barrageConfigTable.prepareInsert();
            result = barrageConfigDao.insert(barrageConfigTable);
        }else{
            //需要执行update操作
            barrageOldConfigTable.reSetData(barrageConfigTable);
            result = barrageConfigDao.save(barrageOldConfigTable);
        }
//        log.error("更新设备结果:" + (System.nanoTime() - startTime) / 1000000);
        if(null == result){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OTHER);
        }else{
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
        }
    }

    @Override
    public BaseResponseEntity delete(BarrageConfigTable barrageConfigTable) {
        return null;
    }

    @Override
    public BarrageConfigTable getEntity(BaseDBWriteEntity dbWriteEntity) {
        return JSONObject.parseObject(dbWriteEntity.getWriteValue(),BarrageConfigTable.class);
    }

    public int getBarrageLoopIntervalTime(String shopId){
        if(StringUtil.isEmpty(shopId)){
            return 0;
        }
        BarrageConfigTable shopSetTable = barrageConfigDao.getShopBarrageConfig(shopId);
        if(null == shopSetTable || 0 == shopSetTable.getLoopIntervalMinute()){
            return 0;
        }
        try{
            return shopSetTable.getLoopIntervalMinute() * 60 * 1000;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
