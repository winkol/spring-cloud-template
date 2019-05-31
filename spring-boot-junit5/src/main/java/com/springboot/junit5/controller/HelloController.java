package com.springboot.junit5.controller;

import com.springboot.junit5.mode.RequestDataVO;
import com.springboot.junit5.mode.ResponseDataVO;
import com.springboot.junit5.util.ProposalInfoValidate;
import com.springboot.junit5.util.ValidateEntUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @Author: Dong.L
 * @Create: 2019-05-27 11:29
 * @Description: java类描述
 */
@Slf4j
@RestController
public class HelloController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProposalInfoValidate());
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseDataVO hello(@RequestBody RequestDataVO requestDataVO){
        log.info("->> HelloController.hello: {}", requestDataVO.toString());
        ResponseDataVO responseDataVO = initData();
        log.info("->> {}", responseDataVO.toString());
        return responseDataVO;
    }

    @RequestMapping(value = "/test/hi", method = RequestMethod.POST)
    public ResponseDataVO testHi(@Valid @RequestBody RequestDataVO requestDataVO){
        log.info("->> HelloController.hello: {}", requestDataVO.toString());
        ResponseDataVO responseDataVO = initData();
        log.info("->> {}", responseDataVO.toString());
        return responseDataVO;
    }

    @RequestMapping(value = "/test/hi2", method = RequestMethod.POST)
    public ResponseDataVO testHi2(@RequestBody RequestDataVO requestDataVO){
        log.info("->> HelloController.hello: {}", requestDataVO.toString());
        ResponseDataVO responseDataVO = ValidateEntUtils.validateEnt(requestDataVO);
        log.info("->> {}", responseDataVO.toString());
        return responseDataVO;
    }

    /**
     * ValidateEntUtils.validationBind等参数处理
     * @param requestDataVO
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/test/hi3", method = RequestMethod.POST)
    public ResponseDataVO testHi3(@Valid @RequestBody RequestDataVO requestDataVO, BindingResult bindingResult){
        log.info("->> HelloController.hello: {}", requestDataVO.toString());

        // 参数错误抛出
        ValidateEntUtils.validationBind(bindingResult);
        ResponseDataVO responseDataVO = initData();
        log.info("->> {}", responseDataVO.toString());
        return responseDataVO;
    }

    private ResponseDataVO initData(){
        return new ResponseDataVO(){{
            setCode("000000");
            setMessage("SUCCESS");
            setData(new HashMap<String, Object>(1){{
                put("age", 18);
            }});
        }};
    }
}
