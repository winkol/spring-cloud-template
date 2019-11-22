package com.springboot.druid.exception;

import com.springboot.druid.util.ApiCode;
import com.springboot.druid.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Dong.L
 * @Date: 2019/11/19 15:22
 * @Description: 异常拦截类
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
    public ResponseVO handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ResponseVO responseVO = ResponseVO.newFailResult(ex.getMessage());
        exceptionHandler(request, responseVO, ex);
        return responseVO;
    }

    /**
     * 参数错误异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ParamIncorrectException.class)
    public ResponseVO paramExceptionHandler(ParamIncorrectException ex) {
        log.error("{}", ex.getMessage(), ex);
        ApiCode apiCode = ex.getApiCode();
        ResponseVO responseVO = ResponseVO.newResult(
                apiCode.getCode(), apiCode.getMessage() + ":" + ex.getMessage());
        exceptionHandler(request, responseVO, ex);
        return responseVO;
    }

    /**
     * 业务逻辑异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseVO serviceExceptionHandler(ServiceException ex) {
        log.error(ex.getMessage(), ex);
        ApiCode apiCode = ex.getApiCode();
        ResponseVO responseVO = ResponseVO.newResult(
                apiCode.getCode(), apiCode.getMessage() + ":" + ex.getMessage());
        exceptionHandler(request, responseVO, ex);
        return responseVO;

    }

    /**
     * 工具或转换异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InnerException.class)
    public ResponseVO innerExceptionHandler(InnerException ex) {
        log.error(ex.getMessage(), ex);
        ResponseVO responseVO = ResponseVO.newFailResult(
                StringUtils.isEmpty(ex.getMessage()) ? "系统存在错误，请联系管理员" : ex.getMessage());
        exceptionHandler(request, responseVO, ex);
        return responseVO;
    }

    /**
     * 系统异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseVO runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        ResponseVO responseVO = ResponseVO.newFailResult("系统后台异常，请联系管理员");
        exceptionHandler(request, responseVO, ex);
        return responseVO;
    }

    /**
     * 异常时需要处理的业务逻辑
     *
     * @param request
     * @param responseVO
     * @param ex
     */
    private void exceptionHandler(HttpServletRequest request, ResponseVO responseVO, Exception ex) {
        log.info("->> exceptionHandler");
        // 获取存入可能存在的数据
//        request.getAttribute("");
        // ..... 异常需要处理的逻辑
    }

}
