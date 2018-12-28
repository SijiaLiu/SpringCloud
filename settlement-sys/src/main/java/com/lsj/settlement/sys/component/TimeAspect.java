package com.lsj.settlement.sys.component;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * class_name: TimeAspect
 * package: com.gemii.lizcloud.core.label.component
 * describe: 对方法进行监控
 *
 * @author liusijia
 * @Date 2018/12/28
 **/

@Aspect
@Slf4j
@Component
public class TimeAspect {

    private final static ThreadLocal<Map<String, Long>> serviceStartTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.lsj.settlement.sys.component.Log)")
    public void servicePointCut() {
    }

    @Around("servicePointCut() && @annotation(logAnnotation)")
    public void enteringService(ProceedingJoinPoint joinPoint, Log logAnnotation) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);

        String methodName = method.getName();
        Map<String, Long> timeMap;

        if (serviceStartTime.get() == null) {
            timeMap = new HashMap<>();
        } else {
            timeMap = serviceStartTime.get();
        }
        timeMap.put(methodName, System.currentTimeMillis());
        serviceStartTime.set(timeMap);

        // 打印参数
        Object[] args = joinPoint.getArgs();
        if (annotation != null) {
            log.info("Entering {} function, desc: {}, args: {}", methodName, logAnnotation.desc(),
                    logAnnotation.printArgs() ? JSON.toJSONString(args) : null);
        }
        joinPoint.proceed();
    }

    @AfterReturning(returning = "result", pointcut = "servicePointCut() && @annotation(logAnnotation)")
    public void endService(JoinPoint joinPoint, Object result, Log logAnnotation) {
        String methodName = joinPoint.getSignature().getName();
        if (logAnnotation.printResult()) {
            log.info("End {} function, result: {}", methodName, JSON.toJSONString(result));
        } else {
            log.info("End {} function", methodName);
        }
        if (result instanceof Collection && logAnnotation.printSize()) {
            log.info("End {} function, size of result: {}", methodName, ((Collection) result).size());
        }

        Map<String, Long> timeMap = serviceStartTime.get();
        Long startTime = timeMap.get(methodName);

        if (startTime == null) {
            log.info("can not compute processing time, because startTime has been removed");
        } else {
            log.info("Process {} spend {} msecs", methodName, System.currentTimeMillis() - startTime);
        }
        timeMap.remove(methodName);
        if (timeMap.isEmpty()) {
            serviceStartTime.remove();
        }
    }
}
