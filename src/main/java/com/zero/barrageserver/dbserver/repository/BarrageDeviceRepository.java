package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.BarrageDeviceTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Repository
public interface BarrageDeviceRepository extends MongoRepository<BarrageDeviceTable,String>{
    @Query(fields = "{'_id':1,'shop_id':1,'sn':1,'ip':1,'version':1}")
    BarrageDeviceTable findByShopIdAndSn(String shopId,String sn);

//    @Query(value = "update({'':})")
//    void updateDevice();
}
