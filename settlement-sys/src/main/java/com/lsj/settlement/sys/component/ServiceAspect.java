package com.lsj.settlement.sys.component;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * class_name: ServiceAspect
 * package: com.lsj.settlement.component
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Aspect
@Slf4j
@Component
public class ServiceAspect {

    private final static ThreadLocal<Map<String, Long>> serviceStartTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.lsj.settlement.sys.component.PrintLog)")
    public void servicePointCut(){}

    @Before("servicePointCut() && @annotation(logAnnotation)")
    public void enteringService(JoinPoint joinPoint, PrintLog logAnnotation){
        String methodName = joinPoint.getSignature().getName();
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Long> timeMap ;
        if(serviceStartTime.get() == null){
            timeMap = new HashMap<>();
        }else{
            timeMap = serviceStartTime.get();
        }
        timeMap.put(methodName, System.currentTimeMillis());
        serviceStartTime.set(timeMap);

        // 打印参数
        Object[] args = joinPoint.getArgs();
        log.info("Entering {} function, desc: {}, args: {}", methodName, logAnnotation.desc(),
                logAnnotation.printArgs()?JSON.toJSONString(args):null);
        log.info("url : {}", request.getRequestURL());
    }

    @AfterReturning(returning = "result", pointcut = "servicePointCut() && @annotation(logAnnotation)")
    public void endService(JoinPoint joinPoint, Object result, PrintLog logAnnotation){
        String methodName = joinPoint.getSignature().getName();
        if(logAnnotation.printResult()){
            log.info("End {} function, result: {}", methodName, JSON.toJSONString(result));
        }else{
            log.info("End {} function", methodName);
        }
        if(result instanceof Collection && logAnnotation.printSize()){
            log.info("End {} function, size of result: {}", methodName, ((Collection) result).size());
        }

        Map<String, Long> timeMap = serviceStartTime.get();
        Long startTime = timeMap.get(methodName);

        if(startTime == null){
            log.info("can not compute processing time, because startTime has been removed");
        }else{
            log.info("Process {} spend {} msecs", methodName, System.currentTimeMillis() - startTime);
        }
        timeMap.remove(methodName);
        if(timeMap.isEmpty()){
            serviceStartTime.remove();
        }
    }

}
