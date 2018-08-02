package com.zero.barrageserver.activeapi.dao;

import com.zero.barrageserver.activeapi.mapper.TenActiveMapper;
import com.zero.barrageserver.common.constant.CacheConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.activerequest.*;
import com.zero.barrageserver.common.entity.activeresponse.*;
import com.zero.barrageserver.common.utils.BaseUtils;
import com.zero.barrageserver.common.utils.HttpUtil;
import com.zero.barrageserver.common.utils.StringUtil;
import com.zero.barrageserver.activeapi.mapper.TenActiveMapper;
import com.zero.barrageserver.common.entity.activerequest.*;
import com.zero.barrageserver.common.entity.activeresponse.TenGroupJoinResponse;
import com.zero.barrageserver.common.entity.activeresponse.TenResponseEntity;
import com.zero.barrageserver.common.utils.BaseUtils;
import com.zero.barrageserver.common.utils.HttpUtil;
import com.zero.barrageserver.common.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero大神 on 2017/12/1.
 */
@Component
@Log4j2
public class TenActiveDao {
    private TenActiveMapper tenActiveMapper = HttpUtil.getTenService(TenActiveMapper.class);
    @Cacheable(value = CacheConstant.CACHE_REDIS_TEN_GROUP, key = "#groupId")
    public TenResponseEntity createTenGroup(String groupName, String groupId){
        TenGroupRequest tenGroupRequest = new TenGroupRequest(Constant.GROUP_TYPE_BCHAT_ROOM,groupId,groupName,Constant.GROUP_JOIN_OPTION_FREE);
        try {
            Response<TenResponseEntity> response = tenActiveMapper.createGroupId(tenGroupRequest).execute();
            log.info("response:" + response.body().getActionStatus());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TenResponseEntity sendMsg(String groupId,String text){
        TenMsgSendRequest tenMsgSendRequest = new TenMsgSendRequest(groupId, BaseUtils.getRandomIntSign(),
                new ArrayList<TenMsgBodyEntity>(){{add(new TenMsgBodyEntity(Constant.TEN_MSG_TYPE_TEXT,new TenMsgContentEntity(text)));}});
        try {
            Response<TenResponseEntity> response = tenActiveMapper.sendMsg(tenMsgSendRequest).execute();
            log.info("response:" + response.body().getActionStatus());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取群组成员
     * @param groupId
     * @return
     */
    public TenGroupMembersResponse getGroupMembers(String groupId){
        TenGroupMembersRequest groupMembersRequest = new TenGroupMembersRequest(groupId);
        try {
            Response<TenGroupMembersResponse> response = tenActiveMapper.getGroupMember(groupMembersRequest).execute();
            log.info("response:" + response.body().getActionStatus());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户状态
     * @param memberList
     * @return
     */
    public TenMembersStatusResponse getMembersStatus(List<TenMemberEntity> memberList){
        TenMembersStatusRequest membersStatusRequest = new TenMembersStatusRequest(memberList);
        try {
            Response<TenMembersStatusResponse> response = tenActiveMapper.getMembersStatus(membersStatusRequest).execute();
            log.info("response:" + response.body().getActionStatus());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户加入的群组
     * @param userAccount
     * @return
     */
    public TenGroupJoinResponse getGroupJoinInfo(String userAccount){
        if(StringUtil.isEmpty(userAccount)){
            return null;
        }
        TenGroupJoinRequest groupJoinRequest = new TenGroupJoinRequest(userAccount);
        try {
            Response<TenGroupJoinResponse> response = tenActiveMapper.getGroupJoin(groupJoinRequest).execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除群组某个成员
     * @param groupId
     * @param userAccount
     * @return
     */
    public TenResponseEntity delGroupMember(String groupId,String userAccount){
        if(StringUtil.isEmpty(userAccount) || StringUtil.isEmpty(groupId)){
            return null;
        }
        TenGroupDelMemRequest tenGroupDelMemRequest = new TenGroupDelMemRequest(groupId,userAccount);
        try {
            Response<TenResponseEntity> response = tenActiveMapper.delGroupMember(tenGroupDelMemRequest).execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
