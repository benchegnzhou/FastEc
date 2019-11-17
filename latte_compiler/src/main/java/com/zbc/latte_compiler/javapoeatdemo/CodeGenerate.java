package com.zbc.latte_compiler.javapoeatdemo;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;

import javax.lang.model.element.Modifier;

/**
 * Created by benchengzhou on 2019/11/18 0:06.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： CodeGenerate
 * 备    注： 参考https://www.jianshu.com/p/3b3bd53ee36a
 */
public class CodeGenerate {


    public static void main(String[] args) {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addMethod(main)
                .addJavadoc("注释注解代码块")
                .build();

        try {
            JavaFile javaFile = JavaFile.builder("com.zbc.latte_compiler.javapoeatdemo", helloWorld)
                    .build();
            /**
             * 代码写入控制台
             */
            javaFile.writeTo(System.out);
            /**
             * 代码写入文件 \com\zbc\latte_compiler
             */
            File file = new File("E:\\FastEc\\latte_compiler\\src\\main\\java");
            System.out.println("___" + file.getAbsolutePath());
            javaFile.writeTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
