package cn.tzmedia.barrageserver.common.entity.response;

import cn.tzmedia.barrageserver.dbserver.model.AnimationFileEntity;
import cn.tzmedia.barrageserver.dbserver.model.RestImageEntity;
import cn.tzmedia.barrageserver.dbserver.model.ShowImageEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zero大神 on 2017/12/29.
 */
@Data
public class DownloadFileEntity implements Serializable{
    private String fileUrl;
    private String MD5Str;
    public DownloadFileEntity(AnimationFileEntity animationFile){
        if(null == animationFile){
            return;
        }
        this.fileUrl = animationFile.getFileUrl();
        this.MD5Str = animationFile.getMD5Str();
    }

    public DownloadFileEntity(RestImageEntity restImage){
        if(null == restImage){
            return;
        }
        this.fileUrl = restImage.getVideoUrl();
    }
    public DownloadFileEntity(ShowImageEntity showImage){
        if(null == showImage){
            return;
        }
        this.fileUrl = showImage.getVideoUrl();
    }
}
