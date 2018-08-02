package com.zero.barrageserver.dbserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by zero大神 on 2017/12/15.
 */
@Document(collection = "barrageAnimation")
@Data
public class AnimationTable {
    @Field("_id")
    @Id
    private String id;
    private String name;
    private boolean isAward;
    @Field("rule")
    private int awardRule;
    @Field("image")
    private List<AnimationFileEntity> fileList;
}
