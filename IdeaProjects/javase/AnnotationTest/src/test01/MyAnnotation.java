package test01;

public @interface MyAnnotation {
//    指定一个属性
    String name() default "abc";
}
