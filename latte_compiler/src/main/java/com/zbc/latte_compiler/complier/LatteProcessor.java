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

@AutoService(Process.class)
public class LatteProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> type = new LinkedHashSet<>();
        System.out.println("打印了——————————");
        final Set<Class<? extends Annotation>> supportannotation = getSupportedAnnotations();
        for (Class<? extends Annotation> typeItem : supportannotation) {
            type.add(typeItem.getCanonicalName());
            System.out.println("打印了——————————");
        }
        return type;
    }

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
//        final Set<Class<? extends Annotation> >
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);

        return true;
    }

    /**
     * 扫描
     *
     * @param environment
     * @param annotation
     * @param valueVisitor
     */
    private void scan(RoundEnvironment environment, Class<? extends Annotation> annotation, AnnotationValueVisitor valueVisitor) {
        for (Element element : environment.getElementsAnnotatedWith(annotation)) {
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
            for (AnnotationMirror mirror : annotationMirrors) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> mirrorElementValues
                        = mirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirrorElementValues.entrySet()) {
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
