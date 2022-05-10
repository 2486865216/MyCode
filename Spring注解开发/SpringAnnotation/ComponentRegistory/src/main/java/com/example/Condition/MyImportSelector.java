package com.example.Condition;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * author ye
 * createDate 2022/3/14  16:14
 */
//自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {
    /**
     * 返回值，就是到导入到容器中的组件全类名
     * AnnotationMetadata:当前标注@Import注解的类的所有注解信息
     *
     * @param importingClassMetadata
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //方法不要返回nu11值

        return new String[]{"com.example.Bean.TestImport001", "com.example.Bean.TestImport002"};
    }
}
