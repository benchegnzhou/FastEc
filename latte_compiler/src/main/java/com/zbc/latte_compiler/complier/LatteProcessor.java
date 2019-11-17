package com.zbc.latte_compiler.complier;


import com.google.auto.service.AutoService;
import com.zbc.latte_anntations.anntations.AppRegisterGenerator;
import com.zbc.latte_anntations.anntations.EntryGenerator;
import com.zbc.latte_anntations.anntations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by benchengzhou on 2019/11/9 22:33.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： LatteProcessor
 * 备    注：
 */

@AutoService(javax.annotation.processing.Processor.class)
public class LatteProcessor extends AbstractProcessor {

    /**
     * 注册自定义的注解名称
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> type = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportannotation = getSupportedAnnotations();
        for (Class<? extends Annotation> typeItem : supportannotation) {
            type.add(typeItem.getCanonicalName());
        }
        return type;
    }
    /**
     * 构建支持的注解集合
     *
     * @return
     */
    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    /**
     * @param set
     * @param roundEnvironment
     * @return true 自己解析处理
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final Set<String> types = new LinkedHashSet<>();

        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);

        return true;
    }


/*

    @Override
    public boolean process(Set<? extends TypeElement> elements, RoundEnvironment env) {
        Map<TypeElement, BindingSet> bindingMap = findAndParseTargets(env);

        for (Map.Entry<TypeElement, BindingSet> entry : bindingMap.entrySet()) {
            TypeElement typeElement = entry.getKey();
            BindingSet binding = entry.getValue();

            JavaFile javaFile = binding.brewJava(sdk);
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                error(typeElement, "Unable to write binding for type %s: %s", typeElement, e.getMessage());
            }
        }

        return false;
    }*/


    /**
     * 扫描
     *
     * @param environment
     * @param annotation
     * @param valueVisitor
     */
    private void scan(RoundEnvironment environment, Class<? extends Annotation> annotation, AnnotationValueVisitor valueVisitor) {
        //遍历使用了clazz注解的所有元素
        for (Element element : environment.getElementsAnnotatedWith(annotation)) {
            //获取注解内部属性集合
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
            for (AnnotationMirror mirror : annotationMirrors) {
                //获取注解内部属性值
                Map<? extends ExecutableElement, ? extends AnnotationValue> mirrorElementValues
                        = mirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirrorElementValues.entrySet()) {
                    //将注解内部属性值交给注解属性解析器处理(AppRegisterGenerator、PayEntryGenerator,EntryGenerator)
                    entry.getValue().accept(valueVisitor, null);
                }
            }
        }
    }

    /**
     * 生成微信entry的类文件
     *
     * @param environment
     */
    private void generateEntryCode(RoundEnvironment environment) {
        //定义一个注解属性解析器
        EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setmFiler(processingEnv.getFiler());
        scan(environment, EntryGenerator.class, entryVisitor);
    }

    /**
     * 生成微信PayEntry的类文件
     *
     * @param environment
     */
    private void generatePayEntryCode(RoundEnvironment environment) {
        PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setmFiler(processingEnv.getFiler());
        scan(environment, PayEntryGenerator.class, payEntryVisitor);
    }

    /**
     * 生成微信AppRegister的类文件
     *
     * @param environment
     */
    private void generateAppRegisterCode(RoundEnvironment environment) {
        AppRegisterVisitor appRegistrVisitor = new AppRegisterVisitor();
        appRegistrVisitor.setmFiler(processingEnv.getFiler());
        scan(environment, AppRegisterGenerator.class, appRegistrVisitor);
    }

}
