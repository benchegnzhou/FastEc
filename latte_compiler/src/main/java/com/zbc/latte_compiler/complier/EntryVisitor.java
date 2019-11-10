package com.zbc.latte_compiler.complier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by benchengzhou on 2019/11/10 19:04.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： EntryVisitor
 * 备    注：
 */
public class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private Filer mFiler = null;
    private TypeMirror mTypeMirrorirror = null;
    private String mPacageName = null;

    public Filer getmFiler() {
        return mFiler;
    }

    public void setmFiler(Filer mFiler) {
        this.mFiler = mFiler;
    }

    public TypeMirror getmTypeMirrorirror() {
        return mTypeMirrorirror;
    }

    public void setmTypeMirrorirror(TypeMirror mTypeMirrorirror) {
        this.mTypeMirrorirror = mTypeMirrorirror;
    }

    public String getmPacageName() {
        return mPacageName;
    }

    public void setmPacageName(String mPacageName) {
        this.mPacageName = mPacageName;
    }


    @Override
    public Void visitString(String var1, Void var2) {
        mPacageName = var1;
        generateJavaCode();
        return var2;
    }

    /**
     * 写一个生成java代码的方法
     */
    private void generateJavaCode() {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(mTypeMirrorirror))
                        .build();
        //生成代码
        JavaFile javaFile = JavaFile.builder(mPacageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
