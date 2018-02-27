package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.common.constant.CacheConstant;
import cn.tzmedia.barrageserver.dbserver.model.ShopSetTable;
import cn.tzmedia.barrageserver.dbserver.repository.ShopSetRepository;
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
