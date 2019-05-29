package com.springboot.junit5.util;

import com.springboot.junit5.mode.ResponseDataVO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 14:32
 * @Description: 属性注解验证工具类
 */
public class ValidateEntUtils {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 实体属性验证
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResponseDataVO validateEnt(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                return ResponseDataVO.newResult("100000", constraintViolation.getMessage());
            }
        }
        return null;
    }

    /**
     * 实体属性验证不通过抛出异常(Exception-可自定义异常)
     * @param t
     * @param <T>
     * @throws Exception
     */
    public static <T> void validateEntThrow(T t) throws Exception {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                throw  new Exception(constraintViolation.getMessage());
            }
        }
    }

    /**
     * 实体属性验证不通过返回多个错误信息
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
}
