package cn.tzmedia.barrageserver.activeapi.mapper;

import cn.tzmedia.barrageserver.common.entity.activeresponse.BaseActiveResponseEntity;
import cn.tzmedia.barrageserver.common.entity.activerequest.HomePageBaseEntity;
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
