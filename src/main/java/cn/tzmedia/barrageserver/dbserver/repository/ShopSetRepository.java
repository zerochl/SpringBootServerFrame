package cn.tzmedia.barrageserver.dbserver.repository;

import cn.tzmedia.barrageserver.dbserver.model.ShopSetTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zero大神 on 2017/12/22.
 */
@Repository
public interface ShopSetRepository extends MongoRepository<ShopSetTable,String>{
    @Query(fields = "{'key':1,'shop_id':1,'val':1}")
    ShopSetTable findByShopIdAndSetName(String shopId,String setName);
}
