package com.zhou.jianzhi.exception;

import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.common.enums.ErrorCodeEnum;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult exception(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<fieldErrors.size();i++){
            if(i!=fieldErrors.size()-1){
                builder.append(fieldErrors.get(i).getDefaultMessage()+",");
            }else{
                builder.append(fieldErrors.get(i).getDefaultMessage()+";");
            }
        }
        return AjaxResult.error(ErrorCodeEnum.VALIDATE_ERROR.getCode(),builder.toString());
    }
    /**
     * 处理请求单个参数不满足校验规则的异常信息
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public AjaxResult constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        logger.error(exception.getMessage());
        return new AjaxResult(ErrorCodeEnum.VALIDATE_ERROR.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = MyException.class)
    public AjaxResult myLoginExceptionHandler(HttpServletRequest request, MyException exception) {
        exception.printStackTrace();
        logger.error(exception.getMessage());
        return new AjaxResult(exception.getCode(),exception.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public AjaxResult allExceptionHandler(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        logger.error(exception.getMessage());
        return new AjaxResult(ErrorCodeEnum.SYS_ERROR.getCode(), StringUtils.isEmpty(exception.getMessage()) ?ErrorCodeEnum.SYS_ERROR.getMsg():exception.getMessage());
    }
    @ExceptionHandler(value = AuthorizationException.class)
    public AjaxResult shiroExceptionHandler(HttpServletRequest request, Exception exception) {
        logger.error("shiro异常=>{}",exception.getMessage());
        return AjaxResult.error(ErrorCodeEnum.SYS_PERMISSION.getCode(),ErrorCodeEnum.SYS_PERMISSION.getMsg());
    }

}
