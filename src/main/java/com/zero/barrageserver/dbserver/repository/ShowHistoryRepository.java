package com.zero.barrageserver.dbserver.repository;

import com.zero.barrageserver.dbserver.model.ShowHistoryTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Repository
public interface ShowHistoryRepository extends MongoRepository<ShowHistoryTable,String>{
    List<ShowHistoryTable> findAllByActivityIdOrderByCreateTimeAsc(String activityId);
    List<ShowHistoryTable> findAllByActivityIdAndMsgTypeInOrderByCreateTimeAsc(String activityId,List<String> msgTypeList);
}
