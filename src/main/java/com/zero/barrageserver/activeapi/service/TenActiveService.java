package com.zero.barrageserver.activeapi.service;

import com.zero.barrageserver.activeapi.dao.TenActiveDao;
import com.zero.barrageserver.common.constant.ConfigConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.constant.ErrorConstant;
import com.zero.barrageserver.common.entity.TenGroupEntity;
import com.zero.barrageserver.common.entity.activeresponse.TenGroupJoinResponse;
import com.zero.barrageserver.common.entity.activeresponse.TenGroupMembersResponse;
import com.zero.barrageserver.common.entity.activeresponse.TenMembersStatusResponse;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.activeresponse.TenResponseEntity;
import com.zero.barrageserver.common.entity.response.MembersOnLineResponse;
import com.zero.barrageserver.common.manager.ExecutorManager;
import com.zero.barrageserver.common.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.entity.TenGroupEntity;
import com.zero.barrageserver.common.entity.activeresponse.TenGroupJoinResponse;
import com.zero.barrageserver.common.entity.activeresponse.TenResponseEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.response.MembersOnLineResponse;
import com.zero.barrageserver.common.utils.StringUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zero大神 on 2017/12/1.
 */
@Log4j2
@Service
public class TenActiveService {
    @Autowired
    private TenActiveDao tenActiveDao;
    @Autowired
    private ConfigConstant configConstant;

    /**
     * 创建或者从redis中获取缓存的群ID
     * @param shopId
     * @return
     */
    public BaseResponseEntity<TenResponseEntity> checkAndReturnGroupId(String shopId){
        if(StringUtil.isEmpty(shopId)){
            log.error("in checkAndReturnGroupId,shop name or shop id is null.");
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        //保存Redis
        TenResponseEntity tenGroupResponse = tenActiveDao.createTenGroup(getBarrageGroupName(shopId),getBarrageGroupId(shopId));
        if(null == tenGroupResponse){
            log.error("create ten group can not get response error.shopId:" + shopId);
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_RESPONSE_NULL);
        }
        if(!StringUtil.isEmpty(tenGroupResponse.getGroupId())){
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,tenGroupResponse);
        }
        //未获取到GroupId，可能已经创建过了
        if(tenGroupResponse.getErrorCode() == ErrorConstant.TEN_IM_ERROR_CODE_GROUP_ID_CREATED){
            tenGroupResponse.setGroupId(getBarrageGroupId(shopId));
            return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OK,tenGroupResponse);
        }
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OTHER);
    }

    /**
     * 同步发送消息
     * @param shopId
     * @param obj
     * @param <T>
     * @return
     */
    public <T>int sendMsg(String shopId,T obj){
        if(StringUtil.isEmpty(shopId) || null == obj){
            log.error("in send msg shop id is empty or obj is null");
            return ErrorConstant.ERROR_CODE_PARAM_ERROR;
        }
        String text = JSONObject.toJSONString(obj);
        String groupId = getBarrageGroupId(shopId);
        TenResponseEntity tenResponse = tenActiveDao.sendMsg(groupId,text);
        if(null == tenResponse){
            log.error("send ten Msg can not get response error.groupId:" + groupId + ";text:" + text);
            return ErrorConstant.ERROR_CODE_RESPONSE_NULL;
        }
        if(tenResponse.getErrorCode() != ErrorConstant.TEN_IM_ERROR_CODE_OK){
            log.error("send ten Msg error.groupId:" + groupId + ";text:" + text + ";error code:" + tenResponse.getErrorCode() + ";error msg:" + tenResponse.getErrorInfo());
            return tenResponse.getErrorCode();
        }
        return tenResponse.getErrorCode();
    }

    /**
     * 异步发送消息
     * @param shopId
     * @param obj
     * @param needSingleExecutor 如果是需要顺序发送的消息必须为true
     * @param <T>
     */
    public <T>void sendMsgAsync(final String shopId, final T obj,final boolean needSingleExecutor,final String threadKey,int retryCount){
        //lamda格式，请注意
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            log.info("on subscribe:" + ";thread:" + Thread.currentThread().getId());
            int errorCode = sendMsg(shopId,obj);
            if(errorCode != ErrorConstant.TEN_IM_ERROR_CODE_OK && retryCount < Constant.IM_MSG_RETRY_MAX_COUNT){
                //执行异步重试
                log.error("执行异步重试errorCode:" + errorCode + ";shopId:" + shopId + ";obj:" + obj.toString());
                sendMsgAsync(shopId,obj,needSingleExecutor,threadKey,retryCount + 1);
            }
        })
        //单线程使用key去获取相对应的单线程池，如果不需要则使用并发线程池
        .subscribeOn(Schedulers.from(needSingleExecutor ? ExecutorManager.getSingleExecutorByKey(threadKey) : ExecutorManager.getNormalMsgExecutor()))
        .subscribe();
    }

    public MembersOnLineResponse getOnLineMembers(String shopId){
        MembersOnLineResponse membersOnLineResponse = new MembersOnLineResponse();
        if(StringUtil.isEmpty(shopId)){
            return membersOnLineResponse;
        }
        String groupId = getBarrageGroupId(shopId);
        TenGroupMembersResponse groupMembersResponse = tenActiveDao.getGroupMembers(groupId);
        if(null == groupMembersResponse || null == groupMembersResponse.getMemberList() || groupMembersResponse.getMemberList().size() == 0){
            return membersOnLineResponse;
        }
        TenMembersStatusResponse membersStatusResponse = tenActiveDao.getMembersStatus(groupMembersResponse.getMemberList());
        if(null == membersStatusResponse || membersStatusResponse.getQueryResult() == null || membersStatusResponse.getQueryResult().size() == 0){
            return membersOnLineResponse;
        }
        membersOnLineResponse.setOnlineMembers(membersStatusResponse.getQueryResult());
        return membersOnLineResponse;
    }

    /**
     * 删除出给出的GroupId之外的所有用户已加入群组
     * @param groupId
     * @param userId
     * @return
     */
    public BaseResponseEntity leaveOtherGroup(String groupId,String userId){
        if(StringUtil.isEmpty(userId)){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_PARAM_ERROR);
        }
        TenGroupJoinResponse groupJoinResponse = tenActiveDao.getGroupJoinInfo(userId);
        if(null == groupJoinResponse || groupJoinResponse.getGroupIdList() == null){
            return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
        }
        for(TenGroupEntity groupEntity:groupJoinResponse.getGroupIdList()){
            if(groupEntity.getGroupId().equals(groupId)){
                //除了传入的GroupId，其他全部退出
                continue;
            }
            //执行退出操作
            tenActiveDao.delGroupMember(groupEntity.getGroupId(),userId);
        }
        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
    }

    private String getBarrageGroupId(String shopId){
        return "Bg" + configConstant.getBuildType() + "" + shopId;
    }

    private String getBarrageGroupName(String shopId){
        return "Bg" + configConstant.getBuildType() + "" + shopId;
    }

}
