package cn.tzmedia.barrageserver.message.entity;

import cn.tzmedia.barrageserver.common.constant.Constant;
import cn.tzmedia.barrageserver.common.constant.StringConstant;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestCommentMsg;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestImageMsg;
import cn.tzmedia.barrageserver.common.entity.servermsg.RequestMovieMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2017/12/14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseCommentMsg extends BaseResponseMsg {
    private String comment;
    private String themeImage;
    private int leftNum,topNum,rightNum,bottomNum;
    private String fontColor;
    public String usernameColor;
    public ResponseCommentMsg(){}
    public ResponseCommentMsg(RequestCommentMsg requestCommentMsg){
        this.activityId = requestCommentMsg.getActivityId();
        this.comment = requestCommentMsg.getContent();
        this.setType(requestCommentMsg.getMsgType());
        this.themeImage = requestCommentMsg.getThemeImage();
        this.leftNum = requestCommentMsg.getLeftNum();
        this.topNum = requestCommentMsg.getTopNum();
        this.rightNum = requestCommentMsg.getRightNum();
        this.bottomNum = requestCommentMsg.getBottomNum();
        this.fontColor = requestCommentMsg.getFontColor();
        this.usernameColor = requestCommentMsg.getUsernameColor();

        setUser(requestCommentMsg);
    }

    public ResponseCommentMsg(RequestImageMsg requestImageMsg){
        this.activityId = requestImageMsg.getActivityId();
        this.comment = StringConstant.SEND_IMAGE_IN_SHOW;
//        this.setType(requestImageMsg.getMsgType());
        this.setType(Constant.BARRAGE_TYPE_COMMENT);

        setUser(requestImageMsg);
    }

    public ResponseCommentMsg(RequestMovieMsg requestMovieMsg){
        this.activityId = requestMovieMsg.getActivityId();
        this.comment = StringConstant.SEND_MOVIE_IN_SHOW;
//        this.setType(requestMovieMsg.getMsgType());
        this.setType(Constant.BARRAGE_TYPE_COMMENT);

        setUser(requestMovieMsg);
    }
}
