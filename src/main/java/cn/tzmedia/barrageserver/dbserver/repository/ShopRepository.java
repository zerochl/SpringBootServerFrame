package cn.tzmedia.barrageserver.dbserver.repository;

import cn.tzmedia.barrageserver.dbserver.model.ShopTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/4.
 */
@Repository
public interface ShopRepository extends MongoRepository<ShopTable,String> {
    @Query(fields = "{ '_id' : 1, 'name' : 1,'barragerole.barrageAlpha':1,'city':1}")
    List<ShopTable> findAllByPlayBillRole_EnabledOrderByOrderDesc(boolean enabled);
    @Query(fields = "{ '_id' : 1, 'name' : 1,'barragerole.barrageAlpha':1}")
    ShopTable findByName(String name);
    @Query(fields = "{ '_id' : 1, 'name' : 1,'barragerole.barrageAlpha':1}")
    ShopTable findById(String shopId);
    @Query(fields = "{ '_id' : 1, 'name' : 1,'barragerole':1,'endTime':1,'startTime':1}")
    ShopTable findByIdAndNameIsNotNull(String shopId);
//    @Query(value = "'Query':{\"address\":\"上海镇宁路525号一楼(近万航渡路)\"}")
//    ShopTable updateShowImage(String shopId,List<ShowImageEntity> showImageList);//,update:{"barragerole.live_url":'33333333'}
}
