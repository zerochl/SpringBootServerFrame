package cn.tzmedia.barrageserver.activeapi.mapper;

import cn.tzmedia.barrageserver.common.constant.ApiConstant;
import cn.tzmedia.barrageserver.common.entity.activerequest.*;
import cn.tzmedia.barrageserver.common.entity.activeresponse.TenGroupJoinResponse;
import cn.tzmedia.barrageserver.common.entity.activeresponse.TenResponseEntity;
import cn.tzmedia.barrageserver.common.entity.activeresponse.TenGroupMembersResponse;
import cn.tzmedia.barrageserver.common.entity.activeresponse.TenMembersStatusResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zero大神 on 2017/12/1.
 */
public interface TenActiveMapper {
    @POST(ApiConstant.TEN_CREATE_GROUP)
    Call<TenResponseEntity> createGroupId(@Body TenGroupRequest groupRequest);
    @POST(ApiConstant.TEN_SEND_MSG)
    Call<TenResponseEntity> sendMsg(@Body TenMsgSendRequest msgSendRequest);
    @POST(ApiConstant.TEN_GET_GROUP_MEMBER)
    Call<TenGroupMembersResponse> getGroupMember(@Body TenGroupMembersRequest groupMemberRequest);
    @POST(ApiConstant.TEN_GET_MEMBERS_STATUS)
    Call<TenMembersStatusResponse> getMembersStatus(@Body TenMembersStatusRequest memberStatusRequest);
    @POST(ApiConstant.TEN_GET_GROUP_JOIN)
    Call<TenGroupJoinResponse> getGroupJoin(@Body TenGroupJoinRequest groupJoinRequest);
    @POST(ApiConstant.TEN_DEL_GROUP_MEMBER)
    Call<TenResponseEntity> delGroupMember(@Body TenGroupDelMemRequest delMemRequest);
}
