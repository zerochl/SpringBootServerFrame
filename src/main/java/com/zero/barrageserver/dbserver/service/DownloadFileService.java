package com.zero.barrageserver.dbserver.service;

import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.DownloadFileCategoryEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.dbserver.model.AnimationTable;
import com.zero.barrageserver.dbserver.model.RestImageEntity;
import com.zero.barrageserver.dbserver.model.ShowImageEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.DownloadFileCategoryEntity;
import com.zero.barrageserver.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/29.
 */
@Service
public class DownloadFileService {
    @Autowired
    private AnimationService animationService;
    @Autowired
    private ShopService shopService;

    /**
     * 获取所有开机下载的文件列表
     * @param shopId
     * @return
     */
    public BaseResponseEntity<List<DownloadFileCategoryEntity>> getBootStrapFileForRequest(String shopId){
        List<DownloadFileCategoryEntity> downloadFileList = new ArrayList<>();
        List<AnimationTable> animFileList = animationService.getDownloadAnimFileList();
        addAnimListToDownloadList(downloadFileList,animFileList);
        if(StringUtil.isEmpty(shopId)){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,downloadFileList);
        }
        addRestListToDownloadList(downloadFileList,shopService.getRestRemoteMovieList(shopId));
        addShowListToDownloadList(downloadFileList,shopService.getShowRemoteMovieList(shopId));
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,downloadFileList);
    }

    private void addAnimListToDownloadList(List<DownloadFileCategoryEntity> downloadFileList,List<AnimationTable> animFileList){
        if(null == animFileList){
            return;
        }
        DownloadFileCategoryEntity downloadFileCategory;
        for(AnimationTable animationTable:animFileList){
            downloadFileCategory = new DownloadFileCategoryEntity(animationTable);
            downloadFileList.add(downloadFileCategory);
        }
    }

    private void addRestListToDownloadList(List<DownloadFileCategoryEntity> downloadFileList, List<RestImageEntity> restList){
        if(null == restList){
            return;
        }
        DownloadFileCategoryEntity downloadFileCategory;
        for(RestImageEntity restImage:restList){
            downloadFileCategory = new DownloadFileCategoryEntity(restImage);
            downloadFileList.add(downloadFileCategory);
        }
    }

    private void addShowListToDownloadList(List<DownloadFileCategoryEntity> downloadFileList, List<ShowImageEntity> showList){
        if(null == showList){
            return;
        }
        DownloadFileCategoryEntity downloadFileCategory;
        for(ShowImageEntity restImage:showList){
            downloadFileCategory = new DownloadFileCategoryEntity(restImage);
            downloadFileList.add(downloadFileCategory);
        }
    }

}
