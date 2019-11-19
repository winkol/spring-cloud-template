package com.springboot.druid.configuration;

import com.alibaba.druid.support.json.JSONUtils;
import com.springboot.druid.util.ObjectMapperUtils;
import com.springboot.druid.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-07-19 16:33
 * @Description: 请求拦截
 */
@Slf4j
@Aspect
@Configuration
public class LogAspectConfig {

    /**
     * Service层节点
     */
    @Pointcut("@annotation(com.springboot.druid.annotation.ServiceLogs)")
    public void serviceAspect() {

    }

    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.springboot.druid.annotation.ControllerLogs)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBeFore(JoinPoint joinPoint) {
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 类名
            String className = joinPoint.getTarget().getClass().getName();
            // 请求方法
            String method = joinPoint.getSignature().getName() + "()";
            // 方法参数
//            String methodParam = JSONUtils.toJSONString(joinPoint.getArgs());
            String methodParam = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                List<Object> args = new ArrayList<>();
                for (Object arg : joinPoint.getArgs()) {
                    if (arg instanceof RequestFacade || arg instanceof MultipartFile || arg instanceof ResponseFacade) {
                        continue;
                    }
                    args.add(arg);
                }
                methodParam = (args.size() > 0 ? ObjectMapperUtils.writeValueAsString(args) : methodParam);
            }
            // 方法描述
            String methodDescription = getControllerMethodDescription(joinPoint);
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n");
            sb.append("***************************************Request请求***************************************");
            sb.append("\n");
            sb.append("ClassName        :").append(className).append("\n");
            sb.append("RequestMethod    :").append(method).append("\n");
            sb.append("RequestParams    :").append(methodParam).append("\n");
            sb.append("RequestType      :").append(request.getMethod()).append("\n");
            sb.append("Description      :").append(methodDescription).append("\n");
            sb.append("serverAddr       :").append(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()).append("\n");
            sb.append("UserAgent        :").append(request.getHeader("User-Agent")).append("\n");
            sb.append("RequestUri       :").append(StringUtils.abbr(request.getRequestURI(), 255)).append("\n");
            log.info(sb.toString());
        } catch (Exception ex) {
            log.error("->> doBeFore error: {}", ex);
        }
    }

    /**
     * @param: [ret]
     * @return: void
     * @Author: Dong.L
     * @Date: 2019/11/19 11:13
     * @Description: 响应拦截处理
     */
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) throws Exception {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = StringUtils.abbr(request.getRequestURI(), 255);
        StringBuilder sb = new StringBuilder(1000);
        // 处理完请求，返回内容
        sb.append("\n");
        sb.append("method       :").append(method).append("\n");
        sb.append("Result       :").append(ret);
        log.info(sb.toString());
    }

    /**
     * 异常通知，用于拦截service层记录异常日志
     * (视情况使用，已存在全局异常拦截'GlobalExceptionHandler')
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 类名
            String className = joinPoint.getTarget().getClass().getName();
            // 请求方法
            String method = joinPoint.getSignature().getName() + "()";
            // 方法参数
//            String methodParam = JSONUtils.toJSONString(joinPoint.getArgs());
            // 方法描述
            String methodDescription = getServiceMethodDescription(joinPoint);
            // 获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += JSONUtils.toJSONString(joinPoint.getArgs()[i]) + ",";
                }
            }
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n");
            sb.append("***************************************Service异常***************************************");
            sb.append("\n");
            sb.append("ClassName        :").append(className).append("\n");
            sb.append("Method           :").append(method).append("\n");
            sb.append("Params           :").append("[" + params + "]").append("\n");
            sb.append("Type             :").append(request.getMethod()).append("\n");
            sb.append("Description      :").append(methodDescription).append("\n");
            sb.append("ExceptionName    :").append(ex.getClass().getName()).append("\n");
            sb.append("ExceptionMessage :").append(ex.getMessage()).append("\n");
            log.info(sb.toString());
        } catch (Exception el) {
            log.error("error: {}", el);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 节点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        log.info("->> getServiceMethodDescription.");
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ApiOperation.class).value();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 节点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        log.info("->> getControllerMethodDescription.");
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ApiOperation.class).value();
                    break;
                }
            }
        }
        return description;
    }
}
