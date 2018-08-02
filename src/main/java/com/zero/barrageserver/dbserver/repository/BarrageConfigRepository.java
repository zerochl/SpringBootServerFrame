package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.BarrageConfigTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zero大神 on 2018/1/8.
 */
@Repository
public interface BarrageConfigRepository extends MongoRepository<BarrageConfigTable,String>{
    @Query(fields = "{'_id':1,'loop_interval_minute':1,'normal_scroll_time':1,'vp_scroll_time':1,'shop_id':1,'create_time':1,'update_time':1,'consume_continue_time':1,'image_wall_continue_time':1,'song_continue_time':1,'image_wall_switch_time':1,'consume_switch_time':1}")
    BarrageConfigTable findByShopId(String shopId);
}
