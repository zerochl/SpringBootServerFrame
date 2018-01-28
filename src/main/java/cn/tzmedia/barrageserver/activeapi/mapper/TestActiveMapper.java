package cn.tzmedia.barrageserver.activeapi.mapper;

import cn.tzmedia.barrageserver.common.constant.ApiSupportConstant;
import cn.tzmedia.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zero大神 on 2017/12/12.
 */
public interface TestActiveMapper {
    @POST(ApiSupportConstant.API_SERVER_PREFIX + ApiSupportConstant.PUSH_MESSAGE)
    Call<BaseResponseEntity> sendMessage(@Body BaseRequestRootMsgEntity requestEntity);
}
