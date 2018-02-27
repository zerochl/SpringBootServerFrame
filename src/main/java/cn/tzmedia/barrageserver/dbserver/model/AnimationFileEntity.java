package cn.tzmedia.barrageserver.dbserver.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/29.
 */
@Data
public class AnimationFileEntity implements Serializable{
    private String fileUrl;
    private String MD5Str;
}
