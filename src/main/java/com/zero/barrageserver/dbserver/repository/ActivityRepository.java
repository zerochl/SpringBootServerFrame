package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.ActivityTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/12.
 */
@Repository
public interface ActivityRepository extends MongoRepository<ActivityTable,String> {
    @Query(fields = "{ 'starttime' : 1, 'endtime' : 1,'artist':1,'band':1,'type':1,'shopid':1,'_id':1,'pktype':1,'teams':1}")
    List<ActivityTable> findByShopIdAndAndStartTimeAfterAndAndEndTimeBeforeOrderByStartTime(String shopId, Date startTime, Date endTime);
    @Query(fields = "{ 'shopid' : 1,'_id':1}")
    ActivityTable findById(String id);
}
