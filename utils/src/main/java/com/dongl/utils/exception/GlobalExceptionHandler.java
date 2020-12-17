package com.dongl.utils.exception;

import com.dongl.utils.common.ApiCode;
import com.dongl.utils.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Dong.L
 * @Date: 2019/11/19 15:22
 * @Description: 异常拦截类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
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

    @ExceptionHandler(BindException.class)
    public ResponseVO bindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (null == fieldError) {
            return exceptionResponseHandler(request, ApiCode.PARAM_ERROR, ex);
        }

        ResponseVO responseVO = ResponseVO.newFailResult(ApiCode.PARAM_ERROR, fieldError.getDefaultMessage());
        return exceptionHandler(request, responseVO, ex);
    }

    /**
     * @param request request
     * @param apiCode apiCode
     * @param ex      ex
     * @method: exceptionResponseHandler
     * @description: 异常返回处理
     * @return: ResponseVO
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:37
     */
    public ResponseVO exceptionResponseHandler(HttpServletRequest request, ApiCode apiCode, Exception ex) {
        ResponseVO responseVO = ResponseVO.newFailResult(apiCode);
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
    private ResponseVO exceptionHandler(HttpServletRequest request, ResponseVO responseVO, Exception ex) {
        log.info("->> exceptionHandler");
        // 获取存入可能存在的数据
//        request.getAttribute("");
        // ..... 异常需要处理的逻辑
        return responseVO;
    }

}
