package com.lsj.settlement.sys.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * class_name: Log
 * package: com.gemii.lizcloud.core.label.component
 * describe: TODO
 *
 * @author liusijia
 * @Date 2018/12/28
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String desc() default "";

    boolean printArgs() default true; // 默认打印参数

    boolean printResult() default false;

    boolean printSize() default false; // 针对返回的数据是集合类型

}
