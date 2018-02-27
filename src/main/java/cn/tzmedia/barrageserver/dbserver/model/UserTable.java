package cn.tzmedia.barrageserver.dbserver.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */
@Document(collection = "user")
@Data
public class UserTable implements Serializable,Cloneable{
    @Id
    @Field("_id")
    private ObjectId id;
    @Field("userid")
    private int userId;
    @Field("userrole")
    private String userRole;
    @Field("nickname")
    private String nickName;
    private String sign;
    private int sex;
    @Field("nicecount")
    private int niceCount;
    @Field("image")
    private List<String> imageList;
    private String usertoken;
    private int levelRange;
    private int level;
    @Field("artistid")
    private String artistId;
}
