# 1.Gradel入门

## 1.1、安装说明

其中SpringBoot与Gradle存在版本兼容问题，Gradle与Idea也存在兼容问题，所以考虑到java程序员会使用SpringBoot,
所以要选择6.8版本及傐于6.8版本的gradle,那么相应的idea版本也要升级，不能太老哦。

## 1.2、下载安装

[Gradle | Installation](https://gradle.org/install/)

添加环境变量

检查

配置本地仓库

![image-20220805190823376](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805190823376.png)

## 1.3、Gradle目录结构

![image-20220805190841876](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805190841876.png)

## 1.4、构建Gradle项目

命令行方式

![image-20220805191717744](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805191717744.png)

## 1.5、常用命令

| 常用Gradle指令       | 作用                       |
| -------------------- | -------------------------- |
| gradle clean         | 清空build目录              |
| gradle classes       | 编译业务代码和配置文件     |
| gradle test          | 编译测试代码，生成测试报告 |
| gradle build         | 构建项目                   |
| gradle build -x test | 跳过测试构建构建           |

> **<font color='blue'>需要注意的是：gradle的指令要在含有build.gradle的目录执行。</font>**

## 1.6、修改maven下载源

Gradle自带的Maven源地址是国外的，该Maven源在国内的访问速度是很慢的，除非使用了特别的手段。一般情况下，我们建议使用国内的第三方开放的Maven源或企业内部自建Maven源。

我们可以在gradle的init.d目录下创建以gradle结尾的文件，gradle文件可以实现在build开始之前执行，所以你可以在
这个文件配置一些你想预先加载的操作

```
buildscript {
    repositories {
        //先从本地加载
        mavenLocal()
        maven { url 'https://maven.aliyun.com/repository/public/' }
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter'}
    }     
}
allprojects {
    repositories {
        mavenLocal() //需要配置环境变量M2_HOME  C:\Users\YQ\IdeaProjects\apache-maven-3.8.4
        maven { url 'https://maven.aliyun.com/repository/public/' }
        mavenCentral()
    }
}
```

拓展1：启用init.gradle文件的方法有：

1. 在命令行指定文件，例如：gradle --init-script yourdir/init.gradle -q taskName·你可以多次输入此命令来指定多个init文件
2. 把init.gradle文件放到USER_HOME/.gradle/目录下
3. 把以.gradle结尾的文件放到USER_HOME/.gradle/init.d/目录下
4. 把以.gradle结尾的文件放到GRADLE_HOME/init.d/目录下

如果存在上面的4种方式的2种以上gradle会按上面的1-4序号依次执行这些文件，如果给定目录下存在多个init脚本，会按拼音a-z顺序执行这些脚本，每个init脚本都存在一个对应的gradle实例，你在这个文件中调用的所有方法和属性，都会委托给这个既gradle实例，每个init脚本都实现了Script接口。

拓展2：仓库地址说明

mavenLocal():指定使用naven本地仓库，而本地仓库在配置maven时settings.xml文件指定的仓库位置如E:/repository,即gradle查找jar包顺序如下：USER_HOME/.m2/settings.xml > M2_HOME/conf/settings.xml > USER_HOME/.m2/repository

maven{url 地址}，指定maven仓库，一般用私有仓库地址或其它的第三方库【比如阿里镜像仓库地址】。

mavenCentral():这是Maven的中央仓库，无需配置，直接声明就可以使用。

jcenter():Center中央仓库，实际也是是用的naven搭建的，但相比Mavent仓库更友好，通过CDN分发，并且支持https访
问，在新版本中已经废弃了，替换为了mavenCentral()

总之，gradle可以通过指定仓库地址为本地maven仓库地址和远程仓库地址相结合的方式，避免每次都会去远程仓库下载依赖库。这种方式也有一定的问题，如果本地maven仓库有这个依赖，就会从直接加载本地依赖，如果本地仓库没有该依赖，那么还是会从远程下载。但是下载的jar不是存储在本地maven仓库中，而是放在自己的缓存目录中，默认在USER_HOME/.gradle/caches目录，当然如果我们配置过GRADLE_USER_HOME环境变量，则会放在GRADLE_USER_HOME/caches目录，那么可不可以将gradle/caches指向maven repository。我们说这是不行的，caches"下载文件不是按照maven仓库中存放的方式

拓展3：阿里云中心仓库[仓库服务 (aliyun.com)](https://developer.aliyun.com/mvn/guide)

## 1.7、Gradle的Wrapper包装器

Gradle Wrapper实际上就是对Gradle的一层包装，用于解决实际开发中可能会遇到的不同的项目需要不同版本的Gradle
问题。例如：把自己的代码共享给其他人使用，可能出现如下情况：

1. 对方电脑没有安装gradle
2. 对方电脑安装过ade,但是版本太旧了

这时候，我们就可以考虑使用Gradle Wrapper了。这也是官方建议使用Gradle Wrapper的原因。实际上有了Gradle
Wrapper之后，我们本地是可以不配置Gradle的，下载Gradle项目后，使用gradle项目自带的wrapper操作也是可以的。

那如何使用Gradle Wrapper呢？

项目中的radlew gradlew.cmd脚本用的就是wrapper中规定的gradle版本。参见源码而我们上面提到的gradle指令用的是本地gradle,所以gradle指令和gradlew指令所使用的gradle版本有可能是不一样的

gradlew ` gradlew.cmd的使用方式与gradle使用方式完全一致，只不过把gradle指令换成了gradlew指令。

当然，我们也可在终端执行gradlew指令时，指定一些参数来控制Wrapper的生成，比如依赖的版本等，如下：

| 参数名                    | 说明                              |
| ------------------------- | --------------------------------- |
| --gradle-version          | 用于指定使用的Gradle版本          |
| --gradle-distribution-url | 用于指定下载Gradle发行版的url地址 |

```
gradle wrapper --gradle-version=4.4:升级wrapper版本号，只是修改gradle.properties中wrapper版本，未实际下载
```

GradleWrapper的执行流程：

1. 当我们第一次执行./gradlew build命令的时候，gradlew会读取gradle wrapper properties文件的配置信息
2. 准确的将指定版本的gradle下载并解压到指定的位置(GRADLE_USER_HOME目录下的wrapper/dists目录中)
3. 并构建本地缓存(GRADLE_USER_HOME目录下的caches目录中)，下载再使用相同版本的gradle就不用下载了
4. 之后执行的./gradlew所有命令都是使用指定的gradle版本·如下图所示

![image-20220712132548033](../AppData/Roaming/Typora/typora-user-images/image-20220712132548033.png)

注意：前面提到的GRALE USER HOME环境变量用于这里的Gradle Wrapper下载的特定版本的gradle存储目录。如果我们没有配置过GRALE USER HOME环境变量，默认在当前用户家目录下的.gradle文件夹中。

那什么时候选择使用gradle wrapper、什么时候选择使用本地gradle?

下载别人的项目或者使用操作以前自己写的不同版本的xade项目时：用Gradle wrapper,也即：gradlew

什么时候使用本地gradle?新建一个项目时：使用gradle指令即可。

# 2.Gradle与IDEA整合

## 2.1、Groovy简介

在某种程度上，Groovy可以被视为Java的一种脚本化改良版，Groovy也是运行在JVM上，它可以很好地与Java代码及其相关库进行交互操作。它是一种成熟的面向对象编程语言，既可以面向对象编程，又可以用作纯粹的脚本语言。大多数有效的Java代码也可以转换为有效的Groovy代码，Groovy和Java语言的主要区别是：完成同样的任务所需的Groovy代码比Java代码更少。其特点为：
>功能强大，例如提供了动态类型转换、闭包和元编程(meta programming)支持
>
>支持函数式编程，不需要main函数
>
>默认导入常用的包
>
>类不支持default作用域，且默认作用域为public。.
>
>Groovy中基本类型也是对象，可以直接调用对象的方法。
>
>支持DSL(Domain Specific Languages领域特定语言)和其它简洁的语法，让代码变得易于阅读和维护。
>
>Groovy是基于Java语言的，所以完全兼容Java语法，所以对于java程序员学习成本较低。
>
>详细了解请参考：[The Apache Groovy programming language - Documentation (groovy-lang.org)](http://www.groovy-lang.org/documentation.html)

## 2.2、安装Groovy并配置环境变量

## 2.3、Groovy基本语法

![image-20220805192053302](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805192053302.png)

类型转换当需要时，类型之间会自动发生类型转换：字符串(String)、基本类型（如int)和类型的包装类（如Integer)

类说明：如果在个groovy文件中没有任何类定义，它将被当做script来处理，也就意味着这个文件将被透明的转换为一个Script类型的类，这个自动转换得到的类将使用原始的groovy文件名作为类的名字。groovy文件的内容被打包进run方法，另外在新产生的类中被加入一个main方法以进行外部执行该脚本。

# 3.进阶说明

## 3.1、项目的生命周期

![image-20220805194631244](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805194631244.png)

**Initialization阶段**主要目的是初始化构建，它又分为两个子过程，一个是执行Init Script,另一个是执行Setting Script。

init gradle文件会在每个项目build之前被调用，用于做一些初始化的操作，它主要有如下作用：



> 配置内部的仓库信息（如公司的maven仓库信息）
> 配置一些全局属性
> 配置用户名及密码信息（如公司仓库的用户名和密码信息）

Setting Script则更重要，它初始化了一次构建所参与的所有模块。

**Configuration阶段**：这个阶段开始加载项目中所有模块的Build Script。所谓"加载"就是执行build.gradle中的语句，根据脚本代码创建对应的task,最终根据所有task生成由Task组成的有向无环图(Directed Acyclic Graphs)),如下：

![image-20220805194752388](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805194752388.png)

**Execution阶段**：这个阶段会根据上个阶段构建好的有向无环图，按着顺序执行Task【Action动作】

## 3.2、setting文件

1、作用：主要是在项目初始化阶段确定一下引入哪些工程需要加入到项目构建中，为构建项目工程树做准备。

2、工程树：gradle中有**工程树**的概念，类似于maven中的**project与module。**

3、内容：里面主要定义了当前gradle项目及子project的项目名称

4、位置：必须放在根工程目录下。

5、名字：为settings.gradle文件，不能发生变化

6、对应实例：与org.gradle.api.initialization.Settings实例是一对应的关系。每个项目只有一个settings文件。

7、关注：作为开发者我们只需要关注该文件中的include方法即可。使用相对路径【:】引入子工程。

8.**一个子工程只有在setting文件中配置了才会被gradle识别，这样在构建的时候才会被包含进去。**案例如下所示：

```
//根工程名
rootProject.name = 'com.exampl'
include 'subProject01'
include 'subProject02'
include 'subProject03'

//包含的子工程下的子工程名称
include 'subProject01:subProject04'
```

项目名称中":"代表项目的分隔符，类似路径中的"/".如果以":"开头则表示相对于root project。然后Gradle会为每个带有build.gradle脚本文件的工程构建一个与之对应的Project对象。

## 3.3、Task

**项目实质上是Task对象的集合。**一个Task表示一个逻辑上较为独立的执行过程，比如编译Java源代码，拷贝文件，打包Jar文件，甚至可以是执行一个系统命令。另外，一个Task可以读取和设置Project的Property以完成特定的操作。

### 3.3.1、任务入门

[官网Task - Gradle DSL Version 7.5](https://docs.gradle.org/current/dsl/org.gradle.api.Task.html#org.gradle.api.Task)

![image-20220805200122952](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805200122952.png)

在文件所在的目录执行命令：gradle A

提示1：task的配置段是在配置阶段完成

提示2：task的doFirst、doLast方法是执行阶段完成，并且doFirst在doLast执行之前执行。

提示3：区分任务的配置段和任务的行为，任务的配置段在**配置阶段执行**，任务的行为在**执行阶段执行**

### 3.3.2、任务的行为

```
task("task1", {
    //任务的配置段：在配置阶段执行
    println "这是最简单的任务"

    //任务的行为：在执行阶段执行，doFirst会在doLast执行之前执行
    doFirst({
        println "task1 doFirst"
    })
    doLast({
        println "task1 doLast"
    })
})
//外部定义
task1.doFirst({
    println "task1 doFirst outer"
})
task1.doLast({
    println "task1 doLast outer"
})

task task2 {
    dependsOn=['task1']
}

task task3 {
    dependsOn(':subProject01:A')
    println "task3"
}
```



拓展1：当一个Task依赖多个Task的时候，被依赖的Task之间如果没有依赖关系，那么它们的执行顺序是随机的，并无影响。

拓展2：重复依赖的任务只会执行一次，比如：

A-→B、C

B→C

任务A依赖任务B和任务C、任务B依赖C任务。执行任务A的时候，显然任务C被重复依赖了，C只会执行一次。

### 3.3.4、任务执行

任务执行语法：gradle 【taskName】【-option-name.】

 

| 分类                                                         | 解释                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 常见的任务                                                   | gradle build:构建项目编译、测试、打包等操作<br/>gradle run运行一个服务，需要application插件支持，并且指定了主启动类才能运行<br/>![image-20220805202232110](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805202232110.png)<br/>gradle clean:请求当前项目的build目录<br/>gradle init:初始化gradle项目使用<br/>gradle wrapper:生成wrapper文件夹的。<br/>gradle wrapper升级wrapper版本号：gradle wrapper --gradle-version=4.4<br/>gradle wrapper --gradle-version5.2.1 -distribution-type all:关联源码用 |
| 项目报告相关                                                 | gradle projects:列出所选项目及子项目列表，以层次结构的形式显示<br/>radle tasks::列出所选项目【当前project,,不包含父、子】的已分配给任务组的那些任务。<br/>gradle tasks -all列出所选项目的所有任务。<br/>gradle tasks --group="build setup":列出所选项目中指定分组中的任务。<br/>gradle help -task someTask:显示某个任务的详细信息<br/>gradle dependencies:查看整个项目的依赖信息，以依赖树的方式显示<br/>gradle properties列出所选项目的属性列表 |
| 任务调试相关选项                                             | -h,-help:查看帮助信息<br/>-v,-version打印Gradle、Groovy、Ant、JVM和操作系统版本信息。<br/>-S,--full-stacktrace:打印出所有异常的完整（非常详细）堆栈跟踪信息。<br/>-s,--stacktrace:打印出用户异常的堆栈跟踪（例如编译错误）。<br/>-Dorg.gradle.daemon.debug=true:调试Gradle守护进程。<br/>-Dorg.gradle.debug=true:调试Gradle客户端（非daemon)进程。<br/>-Dorg.gradle.debug.port=(port number)指定启用调试时要侦听的端口号。默认值为5oo5。 |
| 性能选项：【备注：在gradle properties中指定这些选项中的许多选项，因此不需要命令行标志】 | --build-cache,--no-build-cache:尝试重用先前版本的输出。默认关闭(off)的。<br/>--max-workers:设置Gradle可以使用的woker数。默认值是处理器数。<br/>-parallel,--no-parallel:并行执行项目。有关此选项的限制，请参阅并行项目执行。默认设置为关闭(off) |
| 守护进程选项                                                 | --daemon,--no-daemon:使用Gradle守护进程运行构建。默认是on<br/>--foreground:在前台进程中启动Gradle守护进程。<br/>-Dorg.gradle.daemon.idletimeout=(number of milliseconds):<br/>Gradle Daemon将在这个空闲时间的毫秒数之后停止自己。默认值为10800000(3小时)。 |
| 日志选项                                                     | -Dorg.gradle.logging.level=(quiet,warn,lifecycle,info,debug):<br/>通过Gradle属性设置日志记录级别。<br/>-q,--quiet:只能记录错误信息<br/>-w,--warn:设置日志级别为warn<br/>-i,--info:将日志级别设置为info<br/>-d,--debug:登录调试模式（包括正常的堆栈跟踪） |
| 其它                                                         | -x:-x等价于：-exclude-task:常见gradle -x test clean build<br/>--rerun-tasks:强制执行任务，忽略up-to-date,常见gradle build --rerun-tasks<br/> --continue::忽略前面失败的任务，继续执行，而不是在遇到第一个失败时立即停止执行。每个遇到的故障都将在构建结束时报告，常见：gradle build --continue。<br/>gradle init -type pom将maven项目转换为gradle项目（根目录执行)<br/>gradle 【taskName】执行自定义任务 |

[Command-Line Interface (gradle.org)](https://docs.gradle.org/current/userguide/command_line_interface.html#common_tasks)

gradle任务名是缩写：任务名支持驼峰式命名风格的任务名缩写，如：connectTask简写为：cT，执行任务gradle cT.

前面提到的Gradle指令本质：一个个的task任务，Gradle中所有操作都是基于任务完成的。

### 3.3.5、任务定义方式

任务定义方式，总体分为两大类：一种是通过Project中的task()方法，另一种是通过tasks对象的create或者register方法。

```
task('A', {//任务名称，闭包都作为参数
    println "taskA..."
})

task('B') {//闭包作为最后一个参数可以直接从括号中拿出来
    println "taskB..."
}
task C {//groovy语法支持省略方法括号上面三种本质是一种
    println "taskC..."
}
def map = new HashMap<String, Object>()
map.put("action") { println "task" } //actionh属性可以设置为闭包
task(map, "D")
tasks.create('E') {//使用tasks的create方法
            println "taskE.."
}
tasks.register('f'){//注：register执行的是延迟创建。也即只有当task被需要使用的时候才会被创建。
     println "taskE...."
}
```

在定义任务时也可以给任务分配属性：定义任务的时候可以直接指定任务属性，也可以给已有的任务动态分配属性：

```
//①.F是任务名，前面通过具名参数给map的属性赋值，以参数方式指定任务的属性信息
task(group: "atguigu", description: "this is task B", "F")
//@.H是任务名，定义任务的同时，在内部直接指定属性信息
task("H") {
    group("atguigu")
    description("this is the task H")
}
//③.Y是任务名，给已有的任务在外部直接指定属性信息
task "y" {}
y.group = "atguigu"
clean.group("atguigu")//案例：给已有的clean任务重新指定组信息
```

![image-20220805205240449](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220805205240449.png)

### 3.3.6、任务类型

前面我们定义的task都是Default工ask类型的，如果要完成某些具体的操作完全需要我们自己去编写xade脚本，势必有些麻烦，那有没有一些现成的任务类型可以使用呢？有的，Grde官网给出了一些现成的任务类型帮助我们快速完成想要的任务，我们只需要在创建任务的时候，指定当前任务的类型即可，然后即可使用这种类型中的属性和API方法了。

[Gradle DSL Version 7.5.1](https://docs.gradle.org/current/dsl/#N104C2)

| 常见任务类型             | 该类型任务的作用                                             |
| ------------------------ | ------------------------------------------------------------ |
| Delete                   | 删除文件或目录                                               |
| Copy                     | 将文件复制到目标目录中。此任务还可以在复制时重命名和筛选文件。 |
| CreateStartScripts       | 创建启动脚本                                                 |
| Exec                     | 执行命令行进程                                               |
| GenerateMavenPom         | 生成Maven模块描述符POM)文件。                                |
| GradleBuild              | 执行Gradle构建                                               |
| Jar                      | 组装JAR归档文件                                              |
| JavaCompile              | 编译Java源文件                                               |
| Javadoc                  | 为Java类生成HTML API文档                                     |
| PublishToMavenRepository | 将MavenPublication发布到mavenartifactrepostal.               |
| Tar                      | 组装TAR存档文件                                              |
| Test                     | 执行JUnit(3.8.x、4.x或5.x)或TestNG测试。                     |
| Upload                   | 将Configuration的构件上传到一组存储库。                      |
| War                      | 组装WAR档案。                                                |
| Zip                      | 组装ZIP归档文件默认是压缩ZIP的内容。                         |

![image-20220806201758470](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220806201758470.png)

![image-20220806201655935](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220806201655935.png)

当然除了gradle自带的task类型，我们也可以自定义task类型，

![image-20220806202056418](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220806202056418.png)

### 3.3.7、任务的执行顺序

在Gradle中，有三种方式可以指定Task执行顺序：

1、dependsOn强依赖方式

2、通过Task输入输出

3、通过API指定执行顺序

### 3.3.8、动态分配任务

agradle的强大功能不仅仅用于定义任务的功能。例如，可以使用它在循环中注册同一类型的多个任务

```groovy
4.times{counter ->
    tasks.register("task$counter"){
        doLast{
            println "I'm stack$counter"
        }
    }
}
```

### 3.3.9、任务的开启与关闭

每个任务都有一个enabled默认为的标志true。将其设置为false阻止执行任何任务动作。禁用的任务将标记为"跳过”。

```groovy
task("H") {
    group("atguigu")
    description("this is the task H")
    enabled(false)
}
```

### 3.3.10、任务的超时

每个任务都有一个timeout可用于限制其执行时间的属性。当任务达到超时时，其任务执行线程将被中断。该任务将被标记为失败。终结器任务仍将运行。如果--continue使用，其他任务可以在此之后继续运行。不响应中断的任务无法超时。Gradle的所有内置任务均会及时响应超时

```groovy
task a () {
	timeout = Duration.ofMillis(500)
}
```



### 3.3.11、任务的查找

常用的任务查找方法有：

```groovy
task atguigu
{
    doLast {
        println "让天下没有难学的技术：尚硅谷"
    }
}
//根据任务名查找
tasks.findByName("atguigu").doFirst({ println "尚硅谷校区1：北京" })
tasks.getByName("atguigu").doFirst({ println "尚硅谷校区2：深圳" })
//根据任务路径查找【相对璐径】
tasks.findByPath(":atguigu").doFirst({ println "尚硅谷校区s:上海" })
tasks.getByPath(":atguigu").doFirst({ println "尚硅谷校区4：武汉" })
```

![image-20220806204455218](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220806204455218.png)



### 3.3.12、任务的规则

当我们执行、依赖一个不存在的任务时，Gad山e会执行失败，失败信息是任务不存在。我们可以使用规则对其进行改进，的任务时，不是报错而是打印提示信息呢？

```groovy
task hello {
    doLast {
        println "hello world"
    }
}

tasks.addRule("对规则的一个描述，便于调试", {
    String taskName -> task(taskName) {
            println "the ${taskName} is not exist"
    }
})
```

![image-20220806210118866](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220806210118866.png)

### 3.3.13、任务的onlylf断言

断言就是一个条件表达式。Task有一个yIf方法。它接受一个闭包作为参数，如果该**闭包返回true则该任务执行，否则跳过。**这有很多用途，比如控制程序哪些情况下打什么包，什么时候执行单元测试，什么情况下执行单元测试的时候不执行网络测试等。

### 3.3.14、默认任务

Gradle允许您定义一个或多个在**没有指定其他任务时执行**的默认任务。

```groovy
defaultTasks("A")
```

![image-20220806212912960](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220806212912960.png)

## 3.4、Gradle中的文件操作

### 3.4.1、本地文件

使用Project.file(java.lang.Object)方法，通过指定文件的相对路径或绝对路径来对文件的操作，其中相对路径为相对当前project根project或者子project的目录其实使用Project.file(java.lang.Object)方法创建的File对象就是Java中的File对象，我们可以使用它就像在Java中使用一样。示例代码如下：

```groovy
//使用相对路径
File configFile = file('src/conf.xml')
configFile.createNewFile()
//使用绝对路径
configFile = file('C:\\Users\\YQ\\Gradle\\demo\\com.exampl\\conf.xml')
configFile.createNewFile()
//使用一个文件对象
configFile = new File('src/config.xml')
println(configFile.exists())
```

### 3.4.2、文件集合

文件集合就是一组文件的列表，在Gradle中，文件集合用FileCollection接口表示。我们可以使用Project.files(java.lang,Object)方法来获得一个文件集合对象，如下代码创建一个FileCollection实例：

```groovy
FileCollection collection = files('src/conf.xml', ['src/test.txt', 'src/test1.txt'])
collection.forEach(item -> {
//    println item.name
    item.createNewFile()
})

//转换为Set集合
//Set set = collection.files
Set set = collection as Set
//List list = collection as List
for (final def i in set) {
    println i.exists()
}

//添加或删除一个
def union = collection + files('src/test2.txt')
def minus = collection - files('src/test.txt')

union.forEach(item -> {
    println item.name
})
println ""
minus.forEach(item -> {
    println item.name
})
```

### 3.4.3、文件树

文件树是有层级结构的文件集合，一个文件树它可以代表一个目录结构或ZIP压缩包中的内容结构。文件树是从文件集合继承过来的，所以文件树具有文件集合所有的功能。我们可以使用Project.fileTree(java.uti从Map)方法来创建文件树对象，还可以使用过虑条件来包含或排除相关文件。示例代码如下：

```groovy
//文件树
def tree = fileTree('src/main')
tree.include('**/*.java').forEach(item -> {
    println item.name
})
tree.exclude('**/*.java').forEach(item -> {
    println item.name
})
```

### 3.4.4、文件拷贝

我们可以使用Copy任务来拷贝文件，通过它可以过虑指定拷贝内容，还能对文件进行重命名操作等。Copy任务必须指定一组需要拷贝的文件和拷贝到的目录，这里使用CopySpec.from(java.lag.Object)方法指定原文件；使用CopySpec.into(java.lag.Object)方法指定目标目录。示例代码如下

```groovy
task copyTask(type:Copy){
    from 'src/main/java'
    into 'src/test'
}
```

from()方法接受的参数和文件集合时files()一样。当参数为一个目录时，该目录下所有的文件都会被拷贝到指定目录下（目录自身不会被拷贝)；当参数为一个文件时，该文件会被拷贝到指定目录；如果参数指定的文件不存在，就会被忽略；当参数为一个Zip即压缩文件，该压缩文件的内容会被拷贝到指定目录。into()方法接受的参数与本地文件时file()一样。示例代码如下

```groovy
task copyTask(type:Copy) {
    //拷贝src/main/java目录下所有的文件
    from 'sre/main/java'
    include('**/*.java')
    rename{String fileName ->
        fileName.replace('string', '')
    }
    //拷贝单独的一个文件
    from 'src/staging/index.html'
    //从Zip压缩文件中拷贝内容
    from zipTree('src/main/assets.zip')
    //拷贝到的目标目录
    into 'build/explodedWar'
}
```

或者使用project对象的copy方法：

```groovy
copy {
    //相对路径或者绝对路径
    from file('src/main/resources/ddd.txt')//file也可以换成new File()
    into this.buildDir.absolutePath
}
```

### 3.4.5、归档文件

通常一个项目会有很多的Jar包，我们希望把项目打包成一个WAR,ZIP或TAR包进行发布，这时我们就可以使用Zip,Tar,Jar,War和Tar任务来实现，不过它们的用法都一样，所以在这里我只介绍Zip任务的示例。

首先，创建一个Zip压缩文件，并指定压缩文件名称，如下代码所示：

![image-20220807101043408](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807101043408.png)

## 3.5、Dependencies

### 3.5.1、依赖的方式

Gradle中的依赖分别为直接依赖，项目依赖，本地jar依赖。

本地依赖：依赖本地的某个jax包，具体可通过文件集合、文件树的方式指定

项目依赖：依赖某个project

直接依赖：依赖的类型 依赖的组名 依赖的名称 依赖的版本号

```groovy
//本地依赖的方式：文件集合和文件树完成
    implementation files('lib/mysql.jar', 'lib/log4j.jar')
    implementation fileTree('dir': 'lib', includes: ['*.jar'], excludes: [''])
    //项目依赖：
    implementation project('subproject01')
    //直接依赖：
    implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.27'
//    implementation 'mysql:mysql-connector-java:8.0.27'
```

### 3.5.2、依赖的下载

当执行build命令时，gradle就会去配置的依赖仓库中下载对应的Jar,并应用到项目中。

### 3.5.3、依赖的类型

|                    |                                                              |
| ------------------ | ------------------------------------------------------------ |
| compileOnly        | 由java插件提供，曾短暂的叫provided,,后续版本已经改成了compileOnly,适用于编译期需要而不需要打包的情况 |
| runtimeOnly        | 由java插件提供，只在运行期有效，编译时不需要，比如mysq驱动包。取代老版本中被移除的runtime |
| implementation     | 由java插件提供，针对源码(src/main目录)，在编译、运行时都有效，取代老版本中被移除的compile |
| testCompileOnly    | 由java插件提供，用于编译测试的依赖项，运行时不需要           |
| testRuntimeOnly    | 由java插件提供，只在测试运行时需要，而不是在测试编译时需要，取代老版本中被移除的testRuntime |
| testImplementation | 由java插件提供，针对测试代码(src/test目录】取代老版本中被移除的testCompile |
| providedCompile    | war插件提供支持，编译、测试阶段代码需要依赖此类jar包，而运行阶段容器已经提供了相应的支持，所以无需将这些文件打入到war包中了；例如servlet-api.jar、p-api.jar |
| compile            | 编译范围依赖在所有的classpath中可用，同时它们也会被打包。在gradle7.0已经移除 |
| runtime            | runtime依赖在运行和测试系统的时候需要，在编译的时候不需要，比如mysql驱动包。在gradle7.0已经移除 |
| api                | **java--library**插件提供支持，这些依赖项可以传递性地导出给使用者，用于编译时和运行时。取代老版本中被移除的compile |
| compileOnlyApi     | **java-library**插件提供支持，在声明模块和使用者在编译时需要的依赖项，但在运行时不需要。 |

官方文档[The Java Library Plugin (gradle.org)](https://docs.gradle.org/current/userguide/java_library_plugin.html#header)

### 3.5.4、api与implementation的区别

|          | api                                            | implementation                                       |
| -------- | ---------------------------------------------- | ---------------------------------------------------- |
| 编译时   | 能进行依赖传递，底层变，全部都要变、编译速度慢 | 不能进行依赖传递，底层变，不用全部都要变、编译速度快 |
| 运行时   | 运行时会加载，所有模块的class都要被加载        | 运行时会加载，所有模块的class都要被加载              |
| 应用场景 | 适用于多模块依赖，避免重复依赖模块             | 多数情况下使用implementation                         |

有ABCD四个模块：

- A implemetation B,B implemetation C,则A不能使用C。
- A implemetation B,B api C,则A可以使用C。
- A implemetation B,B implemetation C,C api D,则B可以使用D,但A不能使用D。
- A implemetation B,B api C,C api D,这样A可以使用D
- 不管ABCD在何处被添加到类路径都一样，在运行时这些模块中的clss都是要被加载的。

总之，除非涉及到多模块依赖，为了避免重复依赖，咱们会使用api,其它情况我们优先选择implementation,拥有大量的ai依赖项会显著增加构建时间。

### 3.5.5、依赖冲突及解决方法

依赖冲突是指"在编译过程中，如果存在某个依赖的多个版本，构建系统应该**选择哪个进行构建的问题**，如下所示：

默认下，Gradle**会使用最新版本的jar包**【考虑到新版本的jar包一般都是向下兼容的】，实际开发中，还是建议使用官方自带的这种解决方案。当然除此之外，Gradle也为我们提供了一系列的解决依赖冲突的方法：exclude移除一个依赖，不允许依赖传递，强制使用某个版本。

exclude移除一个依赖

```groovy
implementation('org.hibernate:hibernate-core:3.6.3.Final'){
//        exclude('group': 'org.slf4j')
//        exclude('module': 'slf4j-api')
        exclude('group': 'org.slf4j','module': 'slf4j-api')
    }
implementation 'org.slf4j:slf4j-api:1.4.0'
```

不允许依赖传递

```groovy
implementation('org.hibernate:hibernate-core:3.6.3.Final'){
        transitive(false) //不建议使用
    }
implementation 'org.slf4j:slf4j-api:1.4.0'
```

强制使用某个版本

```groovy
implementation 'org.hibernate:hibernate-core:3.6.3.Final'
implementation 'org.slf4j:slf4j-api:1.4.0!!' //!!表示强制使用
//或者
implementation ('org.slf4j:slf4j-api:1.4.0') {
        version {
            strictly('1.4.0')
        }
    }
```

```groovy
//下面我们配置，当Grad1e构建遇到依赖冲突时，就立即构建失败
configurations.all() {
    Configuration configuration ->
        //当遇到版本冲突时直接构建失败
        configuration.resolutionStrategy.failOnVersionConflict()
}
```

## 3.6、Gradle插件

### 3.6.1、使用插件的原因

简单的说，通过应用插件我们可以：

1.促进代码重用、减少功能类似代码编写、提升工作效率

2.促进项目更高程度的模块化、自动化、便捷化

3.可插拔式的的扩展项目的功能

### 3.6.2、插件的作用

在项目构建过程中做很多事情，把插件应用到项目中，通常可以完成：

1、可以添加任务【task】到项目中，从而帮助完成测试、编译、打包等。

2、可以添加依赖配置到项目中。

3、可以向项目中拓展新的扩展属性、方法等。

4、可以对项目进行一些约定，如应用Java插件后，约定src/main/java目录是我们的源代码存在位置，编译时编译这个目录下的Java源代码文件。

### 3.6.3、插件的分类和使用

![image-20220807191130677](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807191130677.png)

**第一种：脚本插件**

脚本插件的本质就是一个脚本文件，使用脚本插件时通过apply from:将脚本加载进来就可以了，后面的脚本文件可以是本地的也可以引网络上的脚本文件。

意义：脚本文件模块化的基础，可按功能把我们的脚本进行拆分一个个公用、职责分明的文件，然后在主脚本文件引用，比如：将很多共有的库版本号一起管理、应用构建版本一起管理等。

**第二种：对象插件之内部插件核心插件**

[Gradle Plugin Reference](https://docs.gradle.org/current/userguide/plugin_reference.html#header)

二进制插件对象插件就是实现了org.gradle.api.Plugin接口的插件，每个Java Gradle插件都有一个plugin id。

![image-20220807191529293](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807191529293.png)

```groovy
apply plugin: 'java'
或
apply{
    plugin: 'java'
}
```

通过上述代码就将Java插件应用到我们的项目中了，对于Gradle自带的核心插件都有唯一的plugin id,其中java是Java插件的plugin id,这个plugin id必须是唯一的，可使用应用包名来保证plugin id的唯一性。这里的java对应的具体类型是org.gradle.api.plugins.JavaPlugin,所以可以使用如下方式使用Java插件：

```groovy
apply plugin:org.gradle.api.plugins.JavaPlugin

apply plugin:JavaPlugin
```

**第二种：对象插件之第三方插件**

如果是使用第三方发布的二进制插件，一般需要配置对应的仓库和类路径，

[Gradle - Plugins](https://plugins.gradle.org/)

注意：

1. 如果使用老式插件方式buildscript{要放在build.gradle文件的最前面，而新式plugins{}没有该限制。
2. 托管在网站gradle插件官网的第三方插件有两种使用方式，一是传统的buildscript方式，一种是plugins DSL方式。

**第二种：对象插件之用户自定义插件**

[Developing Custom Gradle Plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#header)

这种方式实现的插件我们一般不使用，因为这种方式局限性太强，只能本Project,而其他的Project不能使用。

### 3.6.4、buildSrc项目

buildSre是Gradle默认的插件目录，编译Gradle的时候会自动识别这个目录，将其中的代码编译为插件。

首先先建立一个名为buildSrc的java Module,将buildSrc从included modules移除，重新构建，然后只保留build.gradle和src/main目录，其他全部删掉，注意名字一定是buildSrc,不然会找不到插件。

然后修改Gradle中的内容

![image-20220807195205212](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807195205212.png)

```groovy
apply plugin: 'groovy' //必须
apply plugin: 'maven-publish'


dependencies {
    implementation gradleApi()//必须
    implementation localGroovy()//必须
}

repositories {
    google()
    mavenCentral() //必须
}

//把项目入口设置为src/main/groovy
sourceSets {
    main {
        groovy {
            srcDir 'src/main/groovy'
        }
    }
}
```

![image-20220807195619343](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807195619343.png)

```groovy
package com.example;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * author ye
 * createDate 2022/8/7  19:53
 */
class Text implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task("test") {
            doLast {
                println("自定义的插件")
            }
        }
    }
}
```

接下来在main目录下创建resources目录，在resources目录下创建META-INF目录，在META-INF目录下创建gradle-plugins目录，在gradle-plugins目录下创建properties文件

properties文件可以自己命名，但是要以properties结尾，比如com.atguigu.plugin-properties,其com.atguigu.plugin就是定义的包名路径

![image-20220807195919799](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807195919799.png)

最后需要在properties文件中指明我们实现插件的全类名implementation-class=com.example.Text

![image-20220807200011347](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807200011347.png)

到目前为止我们的插件项目已经写完了，在module引入我们写的插件apply plugin:'com.exampleplugin',然后执行插件的Task

![image-20220807200649329](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807200649329.png)

这种形式的写法，在我们整个工程的module都可以使用，但也只是限制在本工程，其他工程不能使用。

改进：

第二种写插件的方式他只能在本工程中使用，而其他的项目工程不能使用，有时候我们需要一个插件在多个工程中使用，这时候我们就需要把插件上传maven中。

第一步：首先将上述buildSrc目录复制一份，修改文件夹名，然后在settings.gradle文件中使用include引入

第二步：修改build.grale文件，发布到maven仓库中

```groovy
apply plugin: 'groovy' //必须
apply plugin: 'maven-publish'


dependencies {
    implementation gradleApi()//必须
    implementation localGroovy()//必须
}

repositories {
    google()
    mavenCentral() //必须
}

//把项目入口设置为src/main/groovy
sourceSets {
    main {
        groovy {
            srcDir 'src/main/groovy'
        }
    }
}

publishing {
    publications {
        myLibrary (MavenPublication) {
            groupId = 'com.example.plugin'//指定GAV坐标信息
            artifactId = 'library'
            version = '1.1'
            from components.java //发布jar包
            //from components.web//引入war插件，发布war包
        }
    }
    repositories {
        maven {
            url "$rootDir/lib/releasse"
        }
    }
    //发布项目到私服中
//    maven {
//        name = 'myRepo'//name属性可选，表示仓库名称，url必填
//        //发布地址：可以是本地仓库或者maven私服
//        url = layout.buildDirectory.dir("repo")
//        url = 'https://my.org/repo'
//        //chages URLs to point to your repos, e.g. https://my.org/repo
//        //认证信息：用户名和密码
//        credentials {
//            username 'joe'
//            password 'secret'
//        }
//    }
}
```

第三步：执行publish指令，发布到根project或者maven私服仓库。

![image-20220807202441055](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220807202441055.png)

第四步：使用插件，在项目级build.gradle文件中将插件添加到classpath:

```groovy
buildscript {
    repositories {
        maven {
            url "$rootDir/lib/releasse"
        }
    }
    dependencies {
        classpath "com.example.plugin:library:1.1"
    }
}
```

第五步：执行gradle build指令就会在控制台看到自定义插件的输出，说明自定义插件就已经生效了。

最后，至于如何写一个插件，能帮助项目更加自动化或者便捷化，是值得大家未来需要长期思考、关注、努力的点。

### 3.6.5、插件的关注点

**第一点：插件的引用**

`apply plugin:'插件名'`

**第二点：主要的功能任务**

当我们在工程中引入插件后，插件会自动的为我们的工程添加一些额外的任务来完成相应的功能。以Jav插件为例，当我们加入java插件之后，就加入了如下功能：

![image-20220808205246508](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220808205246508.png)

说明：Gradle中的任务依赖关系是很重要的，它们之间的依赖关系就形成了构建的基本流程。

**第三点：工程目录结构**

些插件对工程目结构有约定，所以我们一般遵循它的约定结构来创建工程，这也是Gradle的“约定优于配置”原则。例如java插件规定的项目源集目录结构如下所示：

![image-20220808205431128](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220808205431128.png)

如果要使用某个插件就应该按照它约定的目录结构设置，这样能大大提高我们的效率，当然各目录结构也可以自己定义。

**第四点：依赖管理**

比如前面我们提到的依赖的类型依赖管理部分，不同的插件提供了不同的依赖管理。

**弟五点：常用的属性**

例如：Java插件会为工程添加一些常用的属性，我们可以直接在编译脚本中直接使用。

## 3.7、build.gradle文件

- build.gradle是一个gradle的构建脚本文件，支持java、groovy等语言。
- 每个project都会有一个build.gradle文件，该文件是项目构建的入口，可配置版本、插件、依赖库等信息。
- 每个build文件都有一个对应的Project实例，对build.gradle文件配置，本质就是设置Project实例的属性和方法。
- 由于每个project都会有一个build文件，那么Root Project也不列外.Root Project可以获取到所有Child Project,,所以在Root Project的build文件中我们可以对Child Project统一配置，比如应用的插件、依赖的maven中心仓库等。
- build文件中常见风的属性和方法如下所示：

![image-20220808210259470](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220808210259470.png)

### 3.7.1、常见属性代码

```groovy
//指定使用什么版本的JDK语法编译源代码，跟编译环境有关，在有java插件时才能用
sourceCompatibility=1.8
//指定生成特定于某个JDK版本的class文件：跟运行环境有关，在有java插件时才能用
targetCompatibility =1.8
//业务编码字符集，注意这是指定源码解码的字符集[编译器]
compileJava.options.encoding "UTF-8"
//测试编码字符集，注意这是指定源码解码的字符集[编译器]
compileTestJava.options.encoding "UTF-8"
//编译JAVA文件时采用UTF-8:注意这是指定源码编码的字符集【源文件】
tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}
//编译JAVA文件时采用UTF-8:注意这是指定文档编码的字符集【源文件】
tasks.withType(Javadoc){
    options.encoding ="UTF-8"
}
```

提示1：group+name+version类似于maven的group+artifactId+version

提示2：encoding解决业务代码与测试代码中文乱码问题

### 3.7.2、Repositories

```groovy
	//gradle中会按着仓库配置的顺序，从上往下依次去对应的仓库中找所需要的jar包：
    //如果找到，则停止向下搜索，如果找不到，继续在下面的仓库中查找
    ///指定去本地某个磁盘目录中查找：使用本地le文件协议：一般不用这种方式
    maven {url 'file:///D:/repos/mavenrepos3.5.4'}
    maven {url "srootDir/lib/release"}
    //指定去maven的本地仓库查找
     mavenLocal()
    //指定去maven的私服或者第三方镜像仓库查找
    maven {name "Alibaba";url "https://maven.aliyun.com/repository/public"}
    maven{name "Bstek";url "https://nexus.bsdn.org/content/groups/public/"}
    //指定去maven的远程仓库查找：即htps:/epo.maven.apache.org/maven2/
    mavenCentral()
    //去google仓库查找
    google()
```

因为Gradle没有自己的远程仓库，而是使用Maven、jcenter、iy、google这些远程仓库。

### 3.7.3、Subprojects与Allprojects

projects是对所有project**(包括Root Project-+child Project当前工程和所有子工程)**的进行统一配置，而subprojects是对所有Child Project的进行统一配置。

拓展1：如果是直接在根project配置repositories和dependencies则只针对根工程有效。

拓展：我们也可以在对单个Project进行单独配置：

### 3.7.4、Ext用户自定义属性

Project和Task都允许用户添加额外的自定义属性，要添加额外的属性，通过应用所属对象的ext属性即可实现(可以在子项目使用)。添加之后可以通过xt属性对自定义属性读取和设置，如果要同时添加多个自定义属性，可以通过ext代码块：

```groovy
//自定义一个Project的属性
ext.age=18
//通过代码块同时自定义多个属性
ext {
	phone=19292883833
	address="北京尚硅谷"
}
//通过${name}引用 println "${age}"
```

拓展1：ext配置的是用户自定义属性，而gradle.properties中一般定义系统属性、环境变量、项目属性、JVM相关配置信息。

[gradle.properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties)

### 3.7.5、Buildscript

buildscript里是rradle脚本执行所需依赖，分别是对应的maven库和插件。

1.buildseript{}必须在build.gradle文件的最前端。

2.对于多项目构建，项目的buildscript{}方法声明的依赖关系可用于其所有子项目的构建脚本。

3.构建脚本依赖可能是Gradle插件。

## 3.8、publishing项目发布

接下来，将咱们写好的模块发布发布到公司的私服以供别人使用，如下所示：

![image-20220808212807754](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220808212807754.png)

### 3.8.1、引入maven发布的插件

```groovy
plugins{
    id 'java-library'//如果发布war包，需要war插件，java-library支持带源码、文档发布
    id 'maven-publish'
}
```

### 3.8.2、设置发布代码

```groovy
plugins {
    id 'maven-publish'
}
//带源码和javadoc的发布：需要'java-library'插件支持：它是java的升级版，java插件的功能java-library都有
//jayadoc.options.encoding="UTF-8"
//java {
//    withJavadocJar()
//    withSourcesJar()
//}
publishing {
    publications {
        myLibrary(MavenPublication) {
            groupId = 'org.gradle.sample' // 指定GAV坐标信息
            artifactId = 'library'
            version = '1.1'
            from components.java // 发布jar包
            //from components.web // 引入war插件 发布war包
        }
    }
    repositories {
        //本地仓库位于USER_HOME/.m2/repository
        mavenLocal()
        //发布项目到私服中
        maven {
            name = 'myRepo'//name属性可选，表示仓库名称, url必填
            //发布地址：可以是本地仓库或者maven私服
            //url=layout.buildDirectory.dir("repo")
            //change URLs to point to your repos,e.g.http://my.org/repo
            def releasesRepoUrl = layout.buildDirectory.dir('repos/releases')
            def snapshotsRepoUrl = layout.buildDirectory.dir('repos/snapshots')
            url = version.endsWith('SNAPSHOT') ? snapshotsRepoUrl : releasesRepoUrl
            //认证信息：用户名和密码
//            credentials {
//                username = 'joe'
//                password = 'secret'
//            }
        }
    }
}
```

### 3.8.3、执行发布指令

执行发布命令，将项目发布到本地仓库或者远程仓库。常见的发布指令有：

- generatePomFileForPubNamePublication:生成pom文件
- publishPubNamePublicationToRepoNameRepository:发布项目到指定仓库如果没有仓库名，默认为maven
- publishPubNamePublicationToMavenLocal:将pubName发布复制到本地Maven仓库中包括POM文件和其他元数据。
- **publish:发布到repositories中指定的仓库（为比如Maven私服）**
- publishToMavenLocal:执行所有发布任务中的操作发布到本地maven仓库【默认在用户家目录下的.m2/repository】。

## 3.9、生命周期中Hook

生命周期中的这些钩子函数都是由gradle自动回调完成的，利用这些钩子函数可以帮助我们实现一些我们想要的功能。

![image-20220809192213278](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220809192213278.png)

Gradle在生命周期各个阶段都提供了用于回调的钩子函数：

**Gradle初始化阶段：**

>在settings.gradle执行完后，会回调Gradle对象的settingsEvaluated方法
>
>在构建所有工程build.gradle对应的Project对象后，也既初始化阶段完毕，会回调Gradle对象的projectsLoaded方法

**Gradle配置阶段**

>Gradle会循环执行每个工程的build.gradle脚本文件
>
>在执行当前工程build.gradle前，会回调Gradle对象的beforeProject方法和当前Project对象的beforeEvaluate方法，虽然beforeEvalute属于project的生命周期，但是此时build script尚未被加载，所以beforeEvaluate的设置依然要在init script或setting script中进行，不要在build script中使用project.beforeEvaluate方法。
>
>在执行当前工程build.gradle后，会回调Gradle对象的afterProject方法和当前Project对象的afterEvaluate方法
>
>在所有工程的build.gradle执行完毕后，会回调Gradle对象的projectsEvaluated方法
>
>在构建Task依赖有向无环图后，也就是配置阶段完毕，会回调TaskExecutionGraph对象的whenReady方法

**Gradle执行阶段：**

>Gradle会循环执行Task及其依赖的Task
>
>在当前Task执行之前，会回调TaskExecutionGraph对象的beforeTask方法
>
>在当前Task执行之后，会回调TaskExecutionGraph对象的afterTask方法
>
>**当所有的Task执行完毕后，会回调Gradle对象的buildFinish方法。**

提示：Gradle执行脚本文件的时候会生成对应的实例，主要有如下几种对象：

1. Gradle对象：在项目初始化时构建，全局单例存在，只有这一个对象
2. Project对象：每一个build.gradle文件都会转换成一个Project对象，类似于maven中的pom.xml文件
3. Settings对象：settings.gradle会转变成一个settings对象，和整个项目是一对一的关系，一般只用到include方法
4. Task对象：从前面的有向无环图中，我们也可以看出，gradle最终是基于Task的，一个项目可以有一个或者多个Task

[构建生命周期 (gradle.org)](https://docs.gradle.org/current/userguide/build_lifecycle.html#header)

## 3.10、创建SpringBoot项目

[Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/gradle-plugin/reference/htmlsingle/)

### 3.10.1、引入springboot插件

```groovy
plugins {
    id 'org.springframework.boot' version '2.7.2' //维护springboot版本号，不单独使用，和下面两个插件一起用
}
apply plugin: 'java'
//类似于maven中的<dependencyManagement>标签，只做依赖的管理，不做实际依赖
apply plugin: 'io.spring.dependency-management' //进行依赖管理，在引入其它boot依赖时省略版本号、解决jar包冲突问题
```

![image-20220809195549450](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220809195549450.png)

### 3.10.2、引入所需要的依赖

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```





## 另一种依赖引入方式

```groovy
apply from: 'version.gradle'

dependencies {
    implementation rootProject.ext.dependencies["mysqlJava"]
}
```

![image-20220809205936703](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220809205936703.png)
