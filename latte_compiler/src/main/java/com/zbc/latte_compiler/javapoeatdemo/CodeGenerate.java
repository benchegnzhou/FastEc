package com.zbc.latte_compiler.javapoeatdemo;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
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
        //ctreateCodeMain();
//         createInfe();
//        grenerateBaseEntity("BaseEntity");
        //greanerateDataClient();
        greanerateCallMethod();
    }

    private static void greanerateCallMethod() {
        //得到一个不存在的类
        ClassName DataClient = ClassName.get("com.zbc.latte_compiler.javapoeatdemo", "DataClient");
        ClassName BaseCallback = ClassName.get("com.zbc.latte_compiler.javapoeatdemo", "BaseCallback");


        MethodSpec loadData = MethodSpec.methodBuilder("loadData")
                .addJavadoc("loadData 方法")
                .addModifiers(Modifier.PUBLIC)
                //添加代码块
                .addCode(" String url = $S;\n", "http://www.baidu.com")
                .addCode("new $T()\n" +
                                "    .build()\n" +
                                "   .setTag(this.getClass().getCanonicalName())\n" +
                                "   .get(url)\n" +
                                "   .execute(new $T(){\n" +
                                "       @Override\n" +
                                "       public void onStart() {\n" +
                                "\n" +
                                "       }\n" +
                                "       @Override\n" +
                                "       public void onFinish() {\n" +
                                "\n" +
                                "       }\n" +
                                "\n" +
                                "       @Override\n" +
                                "       public void onError(int errorCode, String msg) {\n" +
                                "\n" +
                                "       }\n" +
                                "\n" +
                                "       @Override\n" +
                                "       public void onSuccess(Object o) {\n" +
                                "\n" +
                                "       }\n" +
                                " }); \n",
                        DataClient, BaseCallback
                )
                .build();

        TypeSpec dataClient = TypeSpec.classBuilder("CallMethod")
                // .addField(tag)
                .addMethod(loadData)
                .addJavadoc("网络请求数据的调用")
                .build();

        try {
            JavaFile javaFile = JavaFile.builder("com.zbc.latte_compiler.javapoeatdemo", dataClient)
                    .build();
            /**
             * 代码写入控制台
             */
            javaFile.writeTo(System.out);
            /**
             * 代码写入文件 \com\zbc\latte_compiler
             */

            File file = new File("latte_compiler\\src\\main\\java");
            System.out.println("___" + new File("latte_compiler\\src\\main\\java").getAbsolutePath());

            javaFile.writeTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void greanerateDataClient() {
        FieldSpec tag = FieldSpec.builder(String.class, "tag")
                .addModifiers(Modifier.PRIVATE)
                //$S for Strings
                //当输出的代码包含字符串的时候, 可以使用 $S 表示一个 string。
                .initializer("$S", "default_tag")
                .build();
        FieldSpec url = FieldSpec.builder(String.class, "url")
                .addModifiers(Modifier.PRIVATE)
                .build();
        FieldSpec time = FieldSpec.builder(long.class, "time")
                .addModifiers(Modifier.PRIVATE)
                //$T for Types
                //使用Java内置的类型会使代码比较容易理解。JavaPoet极大的支持这些类型，通过 $T 进行映射，会自动import声明。
                .initializer("$T.currentTimeMillis()", System.class)
                .build();
        //得到一个不存在的类
        ClassName DataClient = ClassName.get("com.zbc.latte_compiler.javapoeatdemo", "DataClient");

        MethodSpec build = MethodSpec.methodBuilder("build")
                .addJavadoc("build方法")
                .addModifiers(Modifier.PUBLIC)
                .returns(DataClient)
                .addStatement("time = $T.currentTimeMillis()", System.class)
                //添加单行代码
                .addStatement("return this")
                .build();

        MethodSpec setTag = MethodSpec.methodBuilder("setTag")
                .addJavadoc("setTag方法")
                .addModifiers(Modifier.PUBLIC)
                .returns(DataClient)
                .addParameter(String.class, "tag")
                .addStatement("this.tag=tag")
                //添加单行代码
                .addStatement("return this")
                .build();
        MethodSpec urlM = MethodSpec.methodBuilder("get")
                .addJavadoc("get方法")
                .addModifiers(Modifier.PUBLIC)
                //设置返回值类型
                .returns(DataClient)
                .addParameter(String.class, "url")
                .addStatement("this.url=url")
                //添加单行代码
                .addStatement("return this")
                .build();

        MethodSpec execute = MethodSpec.methodBuilder("execute")
                .addJavadoc("execute 方法")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(BaseCallback.class, "baseCallback")
                //添加代码块
                .addCode(" if (baseCallback != null) {\n" +
                        "     baseCallback.onStart();\n" +
                        " }\n")
                .addCode("if (baseCallback != null) { \n")
                .addStatement(" $T baseEntity = new $T()", BaseEntity.class, BaseEntity.class)
                .addCode(" baseEntity.setCode(200);\n" +
                        "   BaseEntity.DataBean dataBean = new BaseEntity.DataBean();\n" +
                        "   dataBean.setName(\"请求到的数据_____data\");\n" +
                        "   baseEntity.setData(dataBean);\n" +
                        "   baseCallback.onSuccess(baseEntity);\n" +
                        " }\n" +
                        "if (baseCallback != null) {\n" +
                        "    baseCallback.onFinish();\n" +
                        "}\n")
                .build();

        TypeSpec dataClient = TypeSpec.classBuilder("DataClient")
                .addField(tag)
                .addField(url)
                .addField(time)
                .addMethod(build)
                .addMethod(setTag)
                .addMethod(urlM)
                .addMethod(execute)
                .addJavadoc("网络请求器")
                .build();

        try {
            JavaFile javaFile = JavaFile.builder("com.zbc.latte_compiler.javapoeatdemo", dataClient)
                    .build();
            /**
             * 代码写入控制台
             */
            javaFile.writeTo(System.out);
            /**
             * 代码写入文件 \com\zbc\latte_compiler
             */

            File file = new File("latte_compiler\\src\\main\\java");
            System.out.println("___" + new File("latte_compiler\\src\\main\\java").getAbsolutePath());

            javaFile.writeTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void grenerateBaseEntity(String className) {


        FieldSpec code = FieldSpec.builder(int.class, "code")
                .addModifiers(Modifier.PRIVATE)
                .build();
        FieldSpec msg = FieldSpec.builder(String.class, "msg")
                .addModifiers(Modifier.PRIVATE)
                .build();
        FieldSpec data = FieldSpec.builder(BaseEntity.DataBean.class, "data")
                .addModifiers(Modifier.PRIVATE)
                .build();

        MethodSpec getCode = MethodSpec.methodBuilder("getCode")
                .addModifiers(Modifier.PUBLIC)
                //定义返回值类型
                .returns(int.class)
                //代码方法内添加通用代码
                .addStatement("return this.code")
                .build();

        MethodSpec setCode = MethodSpec.methodBuilder("setCode")
                .addModifiers(Modifier.PUBLIC)
                //参数接收
                .addParameter(int.class, "code")
                .addStatement("this.code = code")
                .build();

        MethodSpec getData = MethodSpec.methodBuilder("getData")
                .addModifiers(Modifier.PUBLIC)
                //定义返回值类型
                .returns(BaseEntity.DataBean.class)
                //代码方法内添加通用代码
                .addStatement("return this.data")
                .build();

        MethodSpec setData = MethodSpec.methodBuilder("setData")
                .addModifiers(Modifier.PUBLIC)
                //参数接收
                .addParameter(BaseEntity.DataBean.class, "data")
                .addStatement("this.data = data")
                .build();

        FieldSpec name = FieldSpec.builder(String.class, "name")
                .addModifiers(Modifier.PUBLIC)
                .build();
        MethodSpec getName = MethodSpec.methodBuilder("getName")
                .addModifiers(Modifier.PUBLIC)
                //定义返回值类型
                .returns(String.class)
                //代码方法内添加通用代码
                .addStatement("return this.name")
                .build();

        MethodSpec setName = MethodSpec.methodBuilder("setName")
                .addModifiers(Modifier.PUBLIC)
                //参数接收
                .addParameter(String.class, "name")
                .addStatement("this.name = name")
                .build();

        //内部类生成
        TypeSpec dataBean = TypeSpec.classBuilder("DataBean")
                .addField(name)
                .addModifiers(Modifier.STATIC)
                .addMethod(getName)
                .addMethod(setName)
                .addJavadoc("内部类生成")
                .build();


        TypeSpec baseAc = TypeSpec.classBuilder(className)
                .addField(code)
                .addField(msg)
                .addField(data)
                .addMethod(getCode)
                .addMethod(setCode)
                .addMethod(getData)
                .addMethod(setData)
                .addType(dataBean)
                .addJavadoc("请求实体Bean")
                .build();

        try {
            JavaFile javaFile = JavaFile.builder("com.zbc.latte_compiler.javapoeatdemo", baseAc)
                    .build();
            /**
             * 代码写入控制台
             */
            javaFile.writeTo(System.out);
            /**
             * 代码写入文件 \com\zbc\latte_compiler
             */

            File file = new File("latte_compiler\\src\\main\\java");
            System.out.println("___" + new File("latte_compiler\\src\\main\\java").getAbsolutePath());

            javaFile.writeTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主接口代码生成
     */
    private static void createInfe() {
        FieldSpec msgSuccess = FieldSpec.builder(String.class, "SUCCESS")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$S", "msgSuccess")
                .build();
        FieldSpec msgFail = FieldSpec.builder(String.class, "FAIL")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$S", "msgFail")
                .build();
        MethodSpec onStart = MethodSpec.methodBuilder("onStart")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .build();
        MethodSpec onFinish = MethodSpec.methodBuilder("onFinish")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .build();
        MethodSpec onError = MethodSpec.methodBuilder("onError")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .addParameter(int.class, "errorCode")
                .addParameter(String.class, "msg")
                .build();
        MethodSpec onSuccess = MethodSpec.methodBuilder("onSuccess")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .addParameter(Object.class, "o")
                .build();

        TypeSpec baseAc = TypeSpec.interfaceBuilder("BaseCallback")
                .addMethod(onStart)
                .addMethod(onFinish)
                .addMethod(onError)
                .addMethod(onSuccess)
                .addField(msgSuccess)
                .addField(msgFail)
                .addJavadoc("网络请求接口")
                .build();

        try {
            JavaFile javaFile = JavaFile.builder("com.zbc.latte_compiler.javapoeatdemo", baseAc)
                    .build();
            /**
             * 代码写入控制台
             */
            javaFile.writeTo(System.out);
            /**
             * 代码写入文件 \com\zbc\latte_compiler
             */

            File file = new File("latte_compiler\\src\\main\\java");
            System.out.println("___" + new File("latte_compiler\\src\\main\\java").getAbsolutePath());

            javaFile.writeTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成main方法
     */
    private static void ctreateCodeMain() {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addAnnotation(Override.class)
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
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

            File file = new File("latte_compiler\\src\\main\\java");
            System.out.println("___" + new File("latte_compiler\\src\\main\\java").getAbsolutePath());

            javaFile.writeTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
