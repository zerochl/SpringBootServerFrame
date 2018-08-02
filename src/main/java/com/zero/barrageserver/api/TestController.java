package com.zero.barrageserver.api;

import com.zero.barrageserver.activeapi.service.TestActiveService;
import com.zero.barrageserver.common.annotation.SerializedField;
import com.zero.barrageserver.common.constant.ApiSupportConstant;
import com.zero.barrageserver.common.constant.Constant;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.RequestCommentMsg;
import com.alibaba.fastjson.JSONObject;
import com.zero.barrageserver.common.entity.response.BaseResponseEntity;
import com.zero.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import com.zero.barrageserver.common.entity.servermsg.RequestCommentMsg;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zero大神 on 2017/12/12.
 */
@RestController
@Log4j2
@RequestMapping(ApiSupportConstant.API_TEST_PREFIX)
public class TestController {
    @Autowired
    private TestActiveService testActiveService;

    @RequestMapping(method = RequestMethod.GET,value = ApiSupportConstant.TEST_PUSH_MESSAGE)
    @SerializedField(encode = false)
    public BaseResponseEntity sendMsg(){
        BaseRequestRootMsgEntity baseRequestMsgEntity = new BaseRequestRootMsgEntity();
        baseRequestMsgEntity.setMsgtype("COMMENT");
        baseRequestMsgEntity.setActivityid("5a31e51f5b685cd45c4840b7");
        RequestCommentMsg requestCommentMsg = new RequestCommentMsg();
        requestCommentMsg.setContent("测试数据");
        requestCommentMsg.setLevelRange(3);
        requestCommentMsg.setUserName("zero");
        requestCommentMsg.setUserImage("http://olaxmae4w.bkt.clouddn.com/user/201711/FqIaa3oLdmT5pF125AIFujAIDuM8.jpg");
        requestCommentMsg.setMsgType(Constant.BARRAGE_TYPE_COMMENT);
        requestCommentMsg.setUserLevel(8);
        requestCommentMsg.setUserRole("NORMAL");
        requestCommentMsg.setUsertoken("6efb84f1-c0a9-44f7-8e52-039d5cb373b5");
        baseRequestMsgEntity.setValue(JSONObject.toJSONString(requestCommentMsg));
        return testActiveService.sendMsg(baseRequestMsgEntity);
    }
}
