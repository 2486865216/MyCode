package com.example.springbootlast.Bean;


public interface Person {
/*    1、外部配置源
            常用：Java属性文件、YAML文件、环境变量、命令行参数；
      2、配置文件查找位置
            (1) classpath 根路径
            (2) classpath 根路径下config目录
            (3) jar包当前目录
            (4) jar包当前目录的config目录
            (5) /config子目录的直接子目录
        3、配置文件加载顺序：
            1. 当前jar包内部的application.properties和application.yml
            2. 当前jar包内部的application-(profile).properties和 application-(profile).yml
            3.引用的外部jar包的application.properties和application.yml
            4. 引用的外部jar包的application-(profile).properties和application-(profile).yml
        4、后面的可以覆盖前面的同名配置项*/
}
