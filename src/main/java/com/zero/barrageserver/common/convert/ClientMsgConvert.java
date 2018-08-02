package com.zero.barrageserver.common.convert;

import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.entity.servermsg.*;
import com.zero.barrageserver.message.entity.*;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.entity.servermsg.*;
import com.zero.barrageserver.message.entity.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从服务端发送来的消息转化为客户端需要的消息
 * Created by zero大神 on 2017/12/14.
 */
@Component
public class ClientMsgConvert {

    public BaseResponseMsg convertToClientMsg(BaseResponseRootMsg responseRootMsg){
        if(null == responseRootMsg){
            return null;
        }
        BaseResponseMsg responseMsg = null;
        switch (responseRootMsg.getType()){
            case Constant.BARRAGE_TYPE_COMMENT:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseCommentMsg.class);
                break;
            case Constant.BARRAGE_TYPE_AWARD:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseAwardMsg.class);
                break;
            case Constant.BARRAGE_TYPE_LIKE:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseLikeMsg.class);
                break;
            case Constant.BARRAGE_TYPE_SONG:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseSongMsg.class);
                break;
            case Constant.BARRAGE_TYPE_VP:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseVPMsg.class);
                break;
            case Constant.BARRAGE_TYPE_SILKBAG:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseSilkBagUseMsg.class);
                break;
            case Constant.BARRAGE_TYPE_PK_HOT:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseHotPointsMsg.class);
                break;
            case Constant.BARRAGE_TYPE_NORMAL_HOT:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseHotPointsMsg.class);
                break;
            case Constant.BARRAGE_TYPE_IMAGE:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseCommentMsg.class);
                break;
            case Constant.BARRAGE_TYPE_MOVIE:
                responseMsg = JSONObject.parseObject(responseRootMsg.getValue(),ResponseCommentMsg.class);
                break;
        }
        return responseMsg;
    }

    public List<BaseResponseMsg> convertToClientMsg(BaseRequestMsgEntity requestMsgEntity){
        if(null == requestMsgEntity){
            return null;
        }
        BaseResponseMsg responseMsg = null;
        List<BaseResponseMsg> responseMsgList = new ArrayList<>();
        switch (requestMsgEntity.getMsgType()){
            case Constant.BARRAGE_TYPE_COMMENT:
                responseMsg = new ResponseCommentMsg((RequestCommentMsg) requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_AWARD:
                responseMsg = new ResponseAwardMsg((RequestAwardMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_LIKE:
                responseMsg = new ResponseLikeMsg((RequestLikeMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_SONG:
                responseMsgList.addAll(convertSong((RequestSongMsg)requestMsgEntity));
                break;
            case Constant.BARRAGE_TYPE_VP:
                responseMsg = new ResponseVPMsg((RequestVPMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_SILKBAG:
                responseMsg = new ResponseSilkBagUseMsg((RequestSilkBagUseMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_PK_HOT:
                responseMsg = new ResponseHotPointsMsg((RequestPKHotMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_NORMAL_HOT:
                responseMsg = new ResponseHotPointsMsg((RequestNormalHotMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_IMAGE:
                responseMsg = new ResponseCommentMsg((RequestImageMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_MOVIE:
                responseMsg = new ResponseCommentMsg((RequestMovieMsg)requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_IMAGE_WALL:
                responseMsg = new ResponseImageWallMsg((RequestImageWallMsg) requestMsgEntity);
                break;
            case Constant.BARRAGE_TYPE_CONSUME_LIST:
                responseMsg = new ResponseConsumeListMsg((RequestConsumeListMsg) requestMsgEntity);
                break;
        }
        if(null != responseMsg){
            responseMsgList.add(responseMsg);
        }
        return responseMsgList;
    }

    public static void main(String[] args) throws Exception {
        Pattern pattern = Pattern.compile("(?<=《)([^》]+)(?=》)");
        Matcher matcher = pattern.matcher("《Can you feel the love tonight》《阴   天》");//《Can you feel the love tonight》
        while (matcher.find()){
            System.out.println("test:" + matcher.group());
        }
        System.out.println("end");
    }

    private List<ResponseSongMsg> convertSong(RequestSongMsg requestSongMsg){
        if(null == requestSongMsg){
            return null;
        }
        Pattern pattern = Pattern.compile("(?<=《)([^》]+)(?=》)");
        Matcher matcher = pattern.matcher(requestSongMsg.getSongName());
        ArrayList<ResponseSongMsg> songMsgList = new ArrayList<>();
        while (matcher.find()){
            requestSongMsg.setSongName(matcher.group());
            ResponseSongMsg responseSongMsg = new ResponseSongMsg(requestSongMsg);
            songMsgList.add(responseSongMsg);
        }
        return songMsgList;
    }

}
