package cn.tzmedia.barrageserver.dbserver.dao;

import cn.tzmedia.barrageserver.dbserver.model.ShowHistoryTable;
import cn.tzmedia.barrageserver.dbserver.repository.ShowHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Component
public class ShowHistoryDao {
    @Autowired
    private ShowHistoryRepository showHistoryRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ShowHistoryTable> getAllHistoryByActivityId(String activityId){
        return showHistoryRepository.findAllByActivityIdOrderByCreateTimeAsc(activityId);
    }

    /**
     * 根据消息类型，聚合获取数据库中记录，根据各种参数做整合，累计count与price
     * @param activityId
     * @param msgTypeList
     * @return
     */
    public List<ShowHistoryTable> getGroupHistoryByType(String activityId,List<String> msgTypeList){
        Aggregation aggregation = newAggregation(
                match(Criteria.where("activityid").is(activityId).andOperator(Criteria.where("msgtype").in(msgTypeList))),
                group(Fields.from(
                            Fields.field("msgtype","msgtype"),
                            Fields.field("usertoken","value.usertoken"),
                            Fields.field("vpId","value.vpid"),
                            Fields.field("vpName","value.vpname"),
                            Fields.field("teamCode","value.teamCode"),
                            Fields.field("artistId","value.artistid"),
                            Fields.field("songName","value.songname"),
                            Fields.field("resourceId","value.object.id"),
                            Fields.field("comment","value.content")
                        ))
                        .last("_id").as("id")//取最近的一条记录的id，用以排序以及与以前获取的记录做区别标明是新纪录（Pad已读未读）
                        .last("value").as("value")
                        .count().as("totalCount")//累计所有count
                        .sum("value.price").as("totalPrice")//累计所有value下的price
                        .last("activityid").as("activityid"),
                sort(Sort.Direction.DESC,"id"),//根据ID降序排序，其实就是根据插入数据库的时间降序排序
                project(Fields.from(
                        Fields.field("msgtype","_id.msgtype"),
                        Fields.field("_id","id"),
                        Fields.field("activityid","activityid"),
                        Fields.field("value","value"),
                        Fields.field("totalCount","totalCount"),
                        Fields.field("totalPrice","totalPrice")//此处的totalPrice与totalCount并不处于value中，如果需要放到value中需要在service里for循环设置
                ))
//                ,limit(10)
        );
        AggregationResults<ShowHistoryTable> result = mongoTemplate.aggregate(aggregation,"showHistory", ShowHistoryTable.class);
        List<ShowHistoryTable> historyTableList = result.getMappedResults();
        if(null == historyTableList){
            return null;
        }
        return historyTableList;
//        return showHistoryRepository.findAllByActivityIdAndMsgTypeInOrderByCreateTimeAsc(activityId,msgTypeList);
    }
}
