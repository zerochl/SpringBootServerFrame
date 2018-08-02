package com.zero.barrageserver.common.convert;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.entity.servermsg.*;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.entity.servermsg.*;
import org.springframework.stereotype.Component;

/**
 * Created by zero大神 on 2017/12/14.
 */
@Component
public class ServerMsgConvert {
    /**
     * 解析服务端推送过来的消息
     * @param msgEntity
     * @return
     */
    public BaseRequestMsgEntity convertToServerMsg(BaseRequestRootMsgEntity msgEntity){
        if(null == msgEntity){
            return null;
        }
        BaseRequestMsgEntity qqimBaseInfo = null;
        switch (msgEntity.getMsgtype()){
            case Constant.BARRAGE_TYPE_COMMENT:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestCommentMsg.class);
                break;
            case Constant.BARRAGE_TYPE_AWARD:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestAwardMsg.class);
                break;
            case Constant.BARRAGE_TYPE_LIKE:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestLikeMsg.class);
                break;
            case Constant.BARRAGE_TYPE_SONG:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestSongMsg.class);
                break;
            case Constant.BARRAGE_TYPE_VP:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestVPMsg.class);
                break;
            case Constant.BARRAGE_TYPE_SILKBAG:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestSilkBagUseMsg.class);
                break;
            case Constant.BARRAGE_TYPE_PK_HOT:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestPKHotMsg.class);
                break;
            case Constant.BARRAGE_TYPE_NORMAL_HOT:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestNormalHotMsg.class);
                break;
            case Constant.BARRAGE_TYPE_IMAGE:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestImageMsg.class);
                break;
            case Constant.BARRAGE_TYPE_MOVIE:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestMovieMsg.class);
                break;
            case Constant.BARRAGE_TYPE_IMAGE_WALL:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestImageWallMsg.class);
                break;
            case Constant.BARRAGE_TYPE_CONSUME_LIST:
                qqimBaseInfo = JSONObject.parseObject(msgEntity.getValue(), RequestConsumeListMsg.class);
                break;
        }
        if(null != qqimBaseInfo){
            qqimBaseInfo.setMsgType(msgEntity.getMsgtype());
            qqimBaseInfo.setActivityId(msgEntity.getActivityid());
            qqimBaseInfo.setCommentId(msgEntity.getCommentId());
        }
        return qqimBaseInfo;
    }
}
