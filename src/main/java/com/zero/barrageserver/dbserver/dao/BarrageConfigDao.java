package com.zero.barrageserver.dbserver.dao;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.dbserver.model.BarrageConfigTable;
import com.zero.barrageserver.dbserver.repository.BarrageConfigRepository;
import com.zero.barrageserver.dbserver.model.BarrageConfigTable;
import com.zero.barrageserver.dbserver.repository.BarrageConfigRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Component
@Log4j2
public class BarrageConfigDao {
    @Autowired
    private BarrageConfigRepository barrageSetRepository;

    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_CONFIG,key = "#shopId")
    public BarrageConfigTable getShopBarrageConfig(String shopId){
        log.info("get shop barrage config form db");
        return barrageSetRepository.findByShopId(shopId);
    }
    @CachePut(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_CONFIG,key = "#barrageConfigTable.shopId")
    public BarrageConfigTable insert(BarrageConfigTable barrageConfigTable){
        log.info("insert shop barrage config form db");
        return barrageSetRepository.insert(barrageConfigTable);
    }
    @CachePut(value = CacheConstant.CACHE_TABLE_SHOP_BARRAGE_CONFIG,key = "#barrageConfigTable.shopId")
    public BarrageConfigTable save(BarrageConfigTable barrageConfigTable){
        log.info("save shop barrage config form db");
        return barrageSetRepository.save(barrageConfigTable);
    }

}
