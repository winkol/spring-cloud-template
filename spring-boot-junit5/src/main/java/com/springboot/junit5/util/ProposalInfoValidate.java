package com.springboot.junit5.util;

import com.springboot.junit5.exception.ParamIncorrectException;
import com.springboot.junit5.mode.RequestDataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Map;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 14:47
 * @Description: 接口接口参数验证
 */
@Slf4j
public class ProposalInfoValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RequestDataVO.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        RequestDataVO requestDataVO = (RequestDataVO) object;
        Map<String, Object> data = (Map<String, Object>) requestDataVO.getData();
        log.info("->> ProposalInfoValidate.validate: {}", requestDataVO.toString());
        Assert.notNull(requestDataVO.getCode(), "参数code,不能为Null");
//        rejectIfEmpty(errors, "data.name", "参数code,不能为Null");
//        rejectValue(errors, "data.name", "参数name,不能为Null");
    }

    private void rejectIfEmpty(Errors errors, String property, String errorMsg) {
        ValidationUtils.rejectIfEmpty(errors, property, ResultCode.PARAM_ERROR.getCode(), errorMsg);
        if (!StringUtils.isEmpty(errors) && errors.getErrorCount() >= 1) {
            throw new ParamIncorrectException(ResultCode.PARAM_ERROR, errorMsg);
        }
    }
    private void rejectValue(Errors errors, String property, String errorMsg) {
        errors.rejectValue(property, ResultCode.PARAM_ERROR.getCode(), errorMsg);
        if (!StringUtils.isEmpty(errors) && errors.getErrorCount() >= 1) {
            throw new ParamIncorrectException(ResultCode.PARAM_ERROR, errorMsg);
        }
    }
}
