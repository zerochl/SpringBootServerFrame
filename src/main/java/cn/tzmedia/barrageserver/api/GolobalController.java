package cn.tzmedia.barrageserver.api;

import cn.tzmedia.barrageserver.common.annotation.SerializedField;
import cn.tzmedia.barrageserver.common.constant.ApiSupportConstant;
import cn.tzmedia.barrageserver.common.constant.ErrorConstant;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zero大神 on 2018/1/15.
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping(ApiSupportConstant.API_GOLOBAL_PREFIX)
public class GolobalController {
    @RequestMapping(value = ApiSupportConstant.GOLOBAL_ERROR)
    @SerializedField(enable = false)
    public BaseResponseEntity<String> handlerError(){
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OTHER,"");
    }
}
