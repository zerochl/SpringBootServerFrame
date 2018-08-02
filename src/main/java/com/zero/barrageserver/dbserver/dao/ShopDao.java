package com.zero.barrageserver.dbserver.dao;

import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import com.zero.barrageserver.dbserver.model.ShopTable;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import com.zero.barrageserver.dbserver.repository.ShopRepository;
import com.mongodb.WriteResult;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import com.zero.barrageserver.dbserver.model.ShopTable;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import com.zero.barrageserver.dbserver.repository.ShopRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/4.
 */
@Component
@Log4j2
public class ShopDao {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP, key = "'playbill-' +  #playBillEnable")
    public List<ShopTable> getAllShopForChoose(boolean playBillEnable){
        log.info("get from db");
        return shopRepository.findAllByPlayBillRole_EnabledOrderByOrderDesc(playBillEnable);
    }
    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_NAME, key = "#shopName")
    public ShopTable getShopByName(String shopName){
        log.info("get from db");
        return shopRepository.findByName(shopName);
    }
    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_ID, key = "#shopId")
    public ShopTable getShopById(String shopId){
        log.info("get from db");
        return shopRepository.findById(shopId);
    }

    @Cacheable(value = CacheConstant.CACHE_TABLE_SHOP_FOR_ACTIVITY_ID, key = "#shopId")
    public ShopTable getShopByIdForActivity(String shopId){
        log.info("get from db");
        return shopRepository.findByIdAndNameIsNotNull (shopId);
    }

    public int updateShowImage(String shopId, List<ShowImageEntity> showImageList){
        return updateOneField(shopId,"barragerole.barrageShowImage",showImageList);
    }

    public int updateRestImage(String shopId, List<RestImageEntity> restImageList){
        return updateOneField(shopId,"barragerole.barrageRestImage",restImageList);
    }

    /**
     * 此方法仅用于更新一个字段
     * @param shopId
     * @param key
     * @param value
     * @return
     */
    private int updateOneField(String shopId,String key,Object value){
        Query query = new Query(Criteria.where("_id").is(shopId));
        Update update = new Update();
        update.set(key, value);

        WriteResult result = mongoTemplate.updateFirst(query, update, ShopTable.class);

        if(result!=null)
            return result.getN();
        else
            return 0;
    }
}
