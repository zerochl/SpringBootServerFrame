package com.zero.barrageserver.common.entity.response;

import com.zero.barrageserver.dbserver.model.AnimationFileEntity;
import com.zero.barrageserver.dbserver.model.AnimationTable;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/29.
 */
@Data
public class DownloadFileCategoryEntity implements Serializable{
    private String categoryName;
    private List<DownloadFileEntity> fileCategory;
    private String id;
    public DownloadFileCategoryEntity(AnimationTable animationTable){
        if(null == animationTable){
            return;
        }
        this.categoryName = animationTable.getName();
        this.id = animationTable.getId();
        if(null != animationTable.getFileList()){
            fileCategory = new ArrayList<>();
            DownloadFileEntity downloadFile;
            for(AnimationFileEntity animationFile:animationTable.getFileList()){
                downloadFile = new DownloadFileEntity(animationFile);
                fileCategory.add(downloadFile);
            }
        }
    }

    public DownloadFileCategoryEntity(RestImageEntity restImage){
        if(null == restImage){
            return;
        }
        this.categoryName = restImage.getType();
        this.id = restImage.getVideoUrl();
        fileCategory = new ArrayList<>();
        DownloadFileEntity downloadFile = new DownloadFileEntity(restImage);
        fileCategory.add(downloadFile);
    }

    public DownloadFileCategoryEntity(ShowImageEntity showImage){
        if(null == showImage){
            return;
        }
        this.categoryName = showImage.getType();
        this.id = showImage.getVideoUrl();
        fileCategory = new ArrayList<>();
        DownloadFileEntity downloadFile = new DownloadFileEntity(showImage);
        fileCategory.add(downloadFile);
    }
}
