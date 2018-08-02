package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.HotTotalTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Repository
public interface HotTotalRepository extends MongoRepository<HotTotalTable,String>{
    @Query(fields = "{'_id':1,'activityid':1,'teamCode':1,'total':1}")
    List<HotTotalTable> findAllByActivityIdOrderByTeamCodeAsc(String activityId);
}
