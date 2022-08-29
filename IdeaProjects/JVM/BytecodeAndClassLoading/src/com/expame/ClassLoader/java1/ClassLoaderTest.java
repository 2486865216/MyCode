package com.expame.ClassLoader.java1;

/**
 * author ye
 * createDate 2022/2/10  13:53
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        //系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取其上层：扩展类加载器
        ClassLoader extensionLoader = systemLoader.getParent();
        System.out.println(extensionLoader);//sun.misc.Launcher$ExtClassLoader@1b6d3586

        //获取其上层
        ClassLoader BootstrapLoader = extensionLoader.getParent();
        System.out.println(BootstrapLoader);//null

        //对于用户自定义类来说，默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //string类使用引导类加载器加载--->java的核心类库都是使用引导类加载器加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null�
    }
}
