package com.expame.ClassLoader.java1;

import sun.security.ec.SunEC;
import sun.security.util.CurveDB;

import javax.xml.transform.Source;
import java.net.URL;
import java.security.Provider;

/**
 * author ye
 * createDate 2022/2/11  12:17
 */
public class ClassLoaderTest02 {
    public static void main(String[] args) {
        System.out.println("========启动类加载器========");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
        //从上面的路径中随意选择一个类，看看它的类加载器：引导类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println("========扩展类加载器==========");
        String dirs = System.getProperty("java.ext.dirs");
        for (String s : dirs.split(";")) {
            System.out.println(s);
        }

        //从上面的路径中随意选择一个类，看看它的类加载器：扩展类加载器
        ClassLoader classLoader1 = SunEC.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@38af3868
    }
}
