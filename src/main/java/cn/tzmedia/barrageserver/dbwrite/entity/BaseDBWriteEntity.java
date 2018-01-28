package cn.tzmedia.barrageserver.dbwrite.entity;

import cn.tzmedia.barrageserver.common.entity.BaseQueueEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zero大神 on 2017/12/19.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDBWriteEntity extends BaseQueueEntity{
    private String tableName;
    private String writeValue;
    private String action;
    private String type;
    private int retryCount;
    public BaseDBWriteEntity(){}
    public BaseDBWriteEntity(String tableName,String type,String writeValue,String action,int weight){
        this.tableName = tableName;
        this.type = type;
        this.writeValue = writeValue;
        this.action = action;
        this.weight = weight;
    }
}
