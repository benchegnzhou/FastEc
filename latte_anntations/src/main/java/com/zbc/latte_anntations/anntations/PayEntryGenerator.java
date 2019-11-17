package com.zbc.latte_anntations.anntations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by benchengzhou on 2019/11/9 22:21.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： PayEntryGenerator
 * 备    注：
 */


@Target(ElementType.TYPE) //标明注解是加在类上面的
@Retention(RetentionPolicy.SOURCE) //处理阶段标注 处理在源码阶段，在编译打包之前
public @interface PayEntryGenerator {

    //声明该注解所要生成的包名规则
    String packageName();
    //声明该注解所要生成的java类需要继承哪个父类
    Class<?> entryTemplete();

}
