package com.springboot.druid.controller;

import com.github.pagehelper.PageHelper;
import com.springboot.druid.annotation.ControllerLogs;
import com.springboot.druid.dao.UserJpaDao;
import com.springboot.druid.exception.InnerException;
import com.springboot.druid.exception.ParamIncorrectException;
import com.springboot.druid.exception.ServiceException;
import com.springboot.druid.mapper.UserMapper;
import com.springboot.druid.model.base.Paging;
import com.springboot.druid.model.base.Query;
import com.springboot.druid.model.entity.User;
import com.springboot.druid.service.UserService;
import com.springboot.druid.util.ApiCode;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-11-15 16:22
 * @Description: java类描述
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserJpaDao userJpaDao;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    /**
     * @param: [id]
     * @return: java.lang.String
     * @Author:  Dong.L
     * @Date:    2019/11/19 10:28
     * @Description: jpa测试
     */
    @ApiOperation("jpa测试")
    @ControllerLogs
    @ResponseBody
    @RequestMapping(value = "/testJpa/{id}", method = RequestMethod.GET)
    public String jpaUser(@PathVariable("id") Long id) {
        return userJpaDao.findById(id).toString();
    }

    /**
     * @param: [id]
     * @return: java.lang.String
     * @Author:  Dong.L
     * @Date:    2019/11/19 10:30
     * @Description: mapper测试
     */
    @ApiOperation("mapper测试")
    @ControllerLogs
    @ResponseBody
    @RequestMapping(value = "/testMap/{id}", method = RequestMethod.GET)
    public String mapperUser(@PathVariable("id") Long id) {
        return userMapper.info(id).toString();
    }

    /**
     * @param: [query]
     * @return: java.lang.String
     * @Author:  Dong.L
     * @Date:    2019/11/19 10:30
     * @Description: 翻页测试
     */
    @ApiOperation("翻页测试")
    @ControllerLogs
    @ResponseBody
    @RequestMapping(value = "/testPage", method = RequestMethod.POST)
    public List<User> pageUser(@RequestBody Query query) {
        log.info("->> testPage");
        Paging page = query.getPage();
        if (page == null) {
            page = new Paging();
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<User> users = userMapper.pageAll(query.getCondition(), query.getOrderByClause());
        log.info("->> users: {}", users);
        return users;
    }

    /**
     * @Author:  Dong.L
     * @Date:    2019/11/19 15:39
     * @Description: 测试异常
     */
    @ResponseBody
    @ApiOperation("测试异常")
    @ControllerLogs
    @RequestMapping(value = "/testEx/{id}", method = RequestMethod.GET)
    public String testException(@PathVariable("id") Long id){
        log.info("->> testException");
        log.info("->> id: {}", id);
        if (id == 1L) {
            throw new InnerException(ApiCode.ERROR.getMessage());
        } else if (id == 2L) {
            throw new ParamIncorrectException(ApiCode.CONFIGURED_ERROR);
        } else if (id == 3L) {
            throw new ServiceException(ApiCode.STATUS_CODE_ERROR);
        } else if (id == 9L) {
            userService.testException(id);
        }
        return ApiCode.OK.getMessage();
    }

    /**
     * @param:
     * @return:
     * @Author:  Dong.L
     * @Date:    2019/11/19 10:30
     * @Description: 页面访问
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation("页面访问")
    @ControllerLogs
    public String index(){
        return "index";
    }
}
