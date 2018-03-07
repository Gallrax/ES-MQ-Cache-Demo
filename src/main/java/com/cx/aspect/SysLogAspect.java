package com.cx.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cx.entity.Logger;
import com.cx.mq.provider.ActiveMQProvider;
import com.cx.util.HttpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Date: Created on 2018/3/7
 * @Version: 1.0
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private ActiveMQProvider activeMQProvider;

    @Pointcut("@annotation(com.cx.annotation.SysLog)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = getLogger(joinPoint);
        long begin = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            logger.setTime((end - begin));
            logger.setResult(JSONObject.toJSONString(result));
            activeMQProvider.sendLogger(logger);
        }
        return result;
    }

    public Logger getLogger(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String params = JSONObject.toJSONString(joinPoint.getArgs());
        String ip = HttpUtil.getIp(request);
        String url = HttpUtil.getUrl(request);
        Logger logger = new Logger();
        logger.setId(UUID.randomUUID().toString());
        logger.setIp(ip);
        logger.setUrl(url);
        logger.setMethod(method);
        logger.setParams(params);
        logger.setInsertTime(new Date());
        return logger;
    }

}
