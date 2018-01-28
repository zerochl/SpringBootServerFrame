package cn.tzmedia.barrageserver.common.exception;

/**
 * Created by Administrator on 2017/10/12.
 */
import cn.tzmedia.barrageserver.common.constant.ApiSupportConstant;
import cn.tzmedia.barrageserver.common.constant.ErrorConstant;
import cn.tzmedia.barrageserver.common.entity.response.BaseResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class AnyExceptionHandler {

    /**
     * 处理全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponseEntity<String> handlerGlobalException(Exception exception, Model model){
        exception.printStackTrace();
        model.addAttribute("message", exception.getMessage());
        return new BaseResponseEntity<>(ErrorConstant.ERROR_CODE_OTHER,exception.getMessage());
    }

}
