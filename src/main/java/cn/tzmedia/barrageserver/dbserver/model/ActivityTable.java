package cn.tzmedia.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Document(collection = "activity")
@Data
public class ActivityTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    @Field("starttime")
    private Date startTime;
    @Field("endtime")
    private Date endTime;
    @Field("shopid")
    private String shopId;
    @Field("artist")
    private List<String> artistIdList;
    @Field("band")
    private List<String> bandIdList;
    private int type;
    @Field("pktype")
    private String pkType;
    @Field("teams")
    private List<TeamEntity> teamList;
}
