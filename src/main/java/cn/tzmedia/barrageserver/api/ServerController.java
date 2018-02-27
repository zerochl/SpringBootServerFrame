package cn.tzmedia.barrageserver.api;

import cn.tzmedia.barrageserver.common.annotation.SerializedField;
import cn.tzmedia.barrageserver.common.constant.ApiSupportConstant;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import cn.tzmedia.barrageserver.common.entity.servermsg.BaseRequestRootMsgEntity;
import cn.tzmedia.barrageserver.dbserver.service.NormalMsgService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zero大神 on 2017/12/12.
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping(ApiSupportConstant.API_SERVER_PREFIX)
public class ServerController {
    @Autowired
    private NormalMsgService normalMsgService;

    @RequestMapping(method = RequestMethod.POST,value = ApiSupportConstant.PUSH_MESSAGE)
    @SerializedField(encode = false)
    public BaseResponseEntity pushMessage(@RequestBody BaseRequestRootMsgEntity msgEntity){
        log.info("in pushMessage:" + msgEntity.getMsgtype());
        log.info("in pushMessage value:" + msgEntity.getValue());
        log.info("in pushMessage activityid:" + msgEntity.getActivityid());
//        return new BaseResponseEntity(ErrorConstant.ERROR_CODE_OK);
        return normalMsgService.receiveRequest(msgEntity);
    }

}
