package com.zero.barrageserver.activeapi.mapper;

import com.zero.barrageserver.common.entity.activeresponse.BaseActiveResponseEntity;
import com.zero.barrageserver.common.entity.activerequest.DownloadImageEntity;
import com.zero.barrageserver.common.entity.VersionEntity;
import com.zero.barrageserver.common.entity.VersionEntity;
import com.zero.barrageserver.common.entity.activerequest.DownloadImageEntity;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by zero大神 on 2017/11/30.
 */
public interface CMSActiveMapper {
    @GET("appHome/barrageAnimation/")
    Call<BaseActiveResponseEntity<List<DownloadImageEntity>>> getDownloadAnimList();
    @GET("version")
    Call<BaseActiveResponseEntity<VersionEntity>> getVersion();
}
