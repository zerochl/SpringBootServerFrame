package com.zero.barrageserver.dbserver.dao;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.dbserver.model.ShopSetTable;
import com.zero.barrageserver.dbserver.repository.ShopSetRepository;
import com.zero.barrageserver.dbserver.model.ShopSetTable;
import com.zero.barrageserver.dbserver.repository.ShopSetRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/12/22.
 */
@Component
@Log4j2
public class ShopSetDao {
    @Autowired
    private ShopSetRepository shopSetRepository;
    private final String LOOP_INTERVAL_TIME = "loop_interval_m";

    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_SET,key = "#shopId + 'loop_interval_m'")
    public ShopSetTable getBarrageLoopIntervalSet(String shopId){
        log.info("get from db in getBarrageLoopIntervalSet.");
        return shopSetRepository.findByShopIdAndSetName(shopId,LOOP_INTERVAL_TIME);
    }
}
