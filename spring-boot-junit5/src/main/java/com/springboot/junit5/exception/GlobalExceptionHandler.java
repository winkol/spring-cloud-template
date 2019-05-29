package com.springboot.junit5.exception;

import com.springboot.junit5.mode.ResponseDataVO;
import com.springboot.junit5.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 15:43
 * @Description: java类描述
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private HttpServletRequest request;

    /**
     * Controller逻辑代码Exception异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseDataVO handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ResponseDataVO responseDataVO = ResponseDataVO.newFailResult(ex.getMessage());
        exceptionHandler(request, responseDataVO, ex);
        return responseDataVO;
    }

    /**
     * 参数错误异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ParamIncorrectException.class)
    public ResponseDataVO paramExceptionHandler(ParamIncorrectException ex) {
        log.error("{}", ex.getMessage(), ex);
        ResultCode resultCode = ex.getResultCode();
        ResponseDataVO responseDataVO = ResponseDataVO.newResult(
                resultCode.getCode(), resultCode.getMsg() + ":" + ex.getMessage());
        exceptionHandler(request, responseDataVO, ex);
        return responseDataVO;
    }

    /**
     * 业务逻辑异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseDataVO serviceExceptionHandler(ServiceException ex) {
        log.error(ex.getMessage(), ex);
        ResultCode resultCode = ex.getResultCode();
        ResponseDataVO responseDataVO = ResponseDataVO.newResult(
                resultCode.getCode(), resultCode.getMsg() + ":" + ex.getMessage());
        exceptionHandler(request, responseDataVO, ex);
        return responseDataVO;

    }

    /**
     * 工具或转换异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InnerException.class)
    public ResponseDataVO innerExceptionHandler(InnerException ex) {
        log.error(ex.getMessage(), ex);
        ResponseDataVO responseDataVO = ResponseDataVO.newFailResult(
                StringUtils.isEmpty(ex.getMessage()) ? "系统存在错误，请联系管理员" : ex.getMessage());
        exceptionHandler(request, responseDataVO, ex);
        return responseDataVO;
    }

    /**
     * 系统异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseDataVO runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        ResponseDataVO responseDataVO = ResponseDataVO.newFailResult("系统后台异常，请联系管理员");
        exceptionHandler(request, responseDataVO, ex);
        return responseDataVO;
    }

    /**
     * 异常时需要处理的业务逻辑
     * @param request
     * @param responseDataVO
     * @param ex
     */
    private void exceptionHandler(HttpServletRequest request, ResponseDataVO responseDataVO, Exception ex){
        log.info("->> exceptionHandler");
        // 获取存入可能存在的数据
//        request.getAttribute("");
        // ..... 异常需要处理的逻辑
    }

}
