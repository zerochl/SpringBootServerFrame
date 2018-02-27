package cn.tzmedia.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/18.
 */
@Document(collection = "hotPoints")
@Data
public class HotCreatorTable implements Serializable{
    @Field("_id")
    @Id
    private String id;
    @Field("activityid")
    private String activityId;
    private String usertoken;
    @Field("points")
    private int hot;
    private UserTable user;
    private int teamCode;
}
