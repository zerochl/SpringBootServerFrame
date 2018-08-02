package com.zero.barrageserver.activeapi.mapper;

import com.zero.barrageserver.common.entity.activeresponse.BaseActiveResponseEntity;
import com.zero.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import com.zero.barrageserver.common.entity.activerequest.HomePageBaseEntity;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by zero大神 on 2017/11/30.
 */
public interface ServerActiveMapper {
    @GET("appHome/recommend")
    Call<BaseActiveResponseEntity<List<HomePageBaseEntity>>> getHomeSelectInfo();
}
