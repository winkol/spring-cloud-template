/*
* **************************************************************
* Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
* RIGHTS RESERVED.
* **************************************************************
* PROJECT INFORMATION:
* 项目名称：ValidateEntUtils
* 文件名称：ValidateEntUtils.java
* 代码说明：属性注解验证工具类
* **************************************************************
* CHANGE HISTORY:
* Author Date Version Reason
* Dong.L 2020/1/13 15:23 v1.0.0 初始创建
*
* **************************************************************
*/
package com.dongl.utils.util;

import com.dongl.utils.common.ApiCode;
import com.dongl.utils.exception.ParamIncorrectException;
import com.dongl.utils.model.vo.ResponseVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: 属性注解验证工具类
 * @Project: ValidateEntUtils
 * @CreateDate: Created in 2020/1/13 15:23
 * @Author: Dong.L
 */
public class ValidateEntUtils {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 实体属性验证
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResponseVO validateEnt(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                return ResponseVO.newResult(ApiCode.MISSING_PARAMETER, constraintViolation.getMessage());
            }
        }
        return null;
    }

    /**
     * 实体属性验证不通过抛出异常(Exception-可自定义异常)
     *
     * @param t
     * @param <T>
     * @throws Exception
     */
    public static <T> void validateEntThrow(T t) throws Exception {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                throw new Exception(constraintViolation.getMessage());
            }
        }
    }

    /**
     * 实体属性验证不通过返回多个错误信息
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> validateEntErrorList(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            List<String> messageList = new ArrayList<>();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                messageList.add(constraintViolation.getMessage());
            }
            return messageList;
        }
        return null;
    }

    /**
     * 通过注解 @Valid 验证参数
     *
     * @param bindingResult-s
     */
    public static void validationBind(BindingResult bindingResult) {
        // 请求参数检查结果处理
        // 1.
        /*if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            throw new ParamIncorrectException(ResultCode.PARAM_ERROR, fieldError.getDefaultMessage());
        }*/

        // 2.
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                throw new ParamIncorrectException(ApiCode.MISSING_PARAMETER, objectError.getDefaultMessage());
            }
        }
    }
}
