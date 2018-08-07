package com.lsj.settlement.component;

import java.lang.annotation.*;

/**
 * class_name: PrintLog
 * package: com.lsj.settlement.component
 * describe: TODO
 * @author liusijia
 * @Date 2018/7/27
**/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintLog {

    String desc() default "";

    boolean printArgs() default true; // 默认打印参数

    boolean printResult() default false;

    boolean printSize() default false; // 针对返回的数据是集合类型

}
