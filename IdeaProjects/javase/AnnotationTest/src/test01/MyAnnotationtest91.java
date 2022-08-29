package test01;

public class MyAnnotationtest91 {
//    有默认值可以不写
//    属性名是value时。并且只有一个属性时，属性名可以省略
    @MyAnnotation(name="abc")
    @MyAnnotation1("abc")
    public void dosome(){

    }
}
