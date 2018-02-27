package cn.tzmedia.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Data
public class TeamEntity implements Serializable {
    @Field("team_code")
    private int teamCode;
    @Field("team_name")
    private String teamName;
    @Field("artists")
    private List<String> artistIdList;
}
