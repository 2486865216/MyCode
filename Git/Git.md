# Git概述

## Git工作机制

![image-20220222213057130](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222213057130.png)

## Git和代码托管中心

代码托管中心是基于网络服务器的远程代码仓库，一般我们简单称为远程库。

- 局域网
  GitLab
- 互联网
  GitHub（外网)
  Gitee码云（国内网站）

# 第2章Git安装

官网地址：https://git-scm.com/

查看GNU协议，可以直接点击下一步。

# 第3章Git常用命令

| 命令名称                             | 作用           |
| ------------------------------------ | -------------- |
| git config --global user.name 用户名 | 设置用户签名   |
| git config --global user.email 邮箱  | 设置用户签名   |
| git init                             | 初始化本地库   |
| git status                           | 查看本地库状态 |
| git add文件名                        | 添加到暂存区   |
| git commit -m"日志信息" 文件名       | 提交到本地库   |
| git reflog                           | 查看历史记录   |
| git reset-hard 版本号                | 版本穿梭       |

## 3.1设置用户签名

1)基本语法

```shell
git config --global user.name 用户名
git config --global user.email 邮箱
```

说明：

签名的作用是区分不同操作者身份。用户的签名信息在每一个版本的提交信息中能够看到，以此确认本次提交是谁做的。**Git首次安装必须设置一下用户签名，否则无法提交代码。**

※注意：

这里设置用户签名和将来登录GtHb(或其他代码托管中心)的账号没有任何关系。

## 3.2初始化本地库

1)基本语法

```shell
git init
```

![image-20220311094443363](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311094443363.png)

![image-20220311094524877](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311094524877.png)

## 3.3查看本地库状态

1)基本语法

```shell
git status
```

![image-20220311095242126](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311095242126.png)



## 3.4添加暂存区

1)基本语法

```shell
git add 文件名
```

![image-20220311095526113](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311095526113.png)

## 3.5提交本地库

1)基本语法

```shell
git commit -m "版本信息" 文件名
```

![image-20220311095735918](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311095735918.png)



## 3.6修改文件(hello.txt)

![image-20220311095840062](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311095840062.png)

![image-20220311095855660](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311095855660.png)

![image-20220311100106739](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311100106739.png)

## 3.7历史版本

### 3.7.1查看历史版本

1)基本语法

```shell
git reflog   #查看版本信息
git log      #查看版本详细信息
```

![image-20220311100531667](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311100531667.png)

![image-20220311100306618](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311100306618.png)

### 3.7.2版本穿梭

1)基本语法

```shell
git reset --hard 版本号
```

![image-20220311101314749](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311101314749.png)

Git切换版本，底层其实是移动的HEAD指针，具体原理如下图所示

![image-20220311100807080](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311100807080.png)	

![image-20220311100909091](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311100909091.png)

# 第4章Git分支操作

![image-20220311101357680](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311101357680.png)

## 4.1什么是分支

在版本控制过程中，同时推进多个任务，为每廷务，我们就可以创建每个任务的单独分支。使用分支意味着程序员可以把自己的工作从开发主线上分离开来，开发自己分支的时候，不会影响主线分支的运行。对于初学者而言，分支可以简单理解为副本，一个分支就是一个单独的副本。（分支底层其实也是指针的引用）

![image-20220311101622953](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311101622953.png)

## 4.2分支的好处

同时并行推进多个功能开发，提高开发效率。各个分支在开发过程中，如果某一个分支开发失败，不会对其他分支有任何影响。失败
的分支删除重新开始即可。

## 4.3分支的操作

| 命令名称            | 作用                         |
| ------------------- | ---------------------------- |
| git branch分支名    | 创建分支                     |
| git branch -v       | 查看分支                     |
| git checkout 分支名 | 切换分支                     |
| git merge 分支名    | 把指定的分支合并到当前分支上 |

### 4.3.1查看分支

1)基本语法

```shell
git branch -v
```

![image-20220311101917221](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311101917221.png)

### 4.3.2创建分支

1）基本语法

```shell
git branch 分支名
```

![image-20220311102045826](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311102045826.png)

### 4.3.3.切换与修改分支

1）基本语法

```shell
git checkout 分支名 #切换分支
```

![image-20220311102639289](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311102639289.png)

### 4.3.4.合并分支

1)基本语法

```shell
git merge 要合并的分支名   #把分支合并到当前分支
```

![image-20220311102947741](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311102947741.png)

#### 合并冲突

冲突产生的原因：

合并分支时，两个分支在**同一个文件的同一个位置**有两套完全不同的修改。Git无法替我们决定使用哪一个。必须**人为决定**新代码内容。

![image-20220311103810635](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311103810635.png)

1. vim 编辑有冲突的文件

	![image-20220311103854778](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311103854778.png)

	![image-20220311103932972](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311103932972.png)

2. git add 将文件提交到暂存区

	![image-20220311104040420](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311104040420.png)

3. git commit 提交文件，**不要带文件名，否则报错**

	![image-20220311104155264](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311104155264.png)

master、second其实都是指向具体版本记录的指针。当前所在的分支，其实是由HEAD决定的。所以创建分支的本质就是多创建一个指针。

HEAD如果指向master,.那么我们现在就在master分支上。

HEAD如果执行second,那么我们现在就在second分支上。

所以切换分支的本质就是移动HEAD指针。

# 第5章Git团队协作机制

## 5.1团队内协作

![image-20220311104901752](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311104901752.png)

## 5.2.跨团队合作

![image-20220311104914661](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311104914661.png)

# 第6章GitHub操作

## 6.1创建仓库

![image-20220311115452843](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311115452843.png)

![image-20220311120605116](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311120605116.png)

## 6.2远程仓库操作

| 命令名称                          | 作用                                                     |
| --------------------------------- | -------------------------------------------------------- |
| git remote -v                     | 查看当前所有远程地址别名                                 |
| git remote add 别名远程地址       | 起别名                                                   |
| git push 别名分支                 | 推送本地分支上的内容到远程仓库                           |
| git clone 远程地址                | 将远程仓库的内容克隆到本地                               |
| git pull 远程库地址别名远程分支名 | 将远程仓库对于分支最新内容拉下来后与当前本地分支直接合并 |

### 6.2.1创建远程仓库别名

1)基本语法

```shell
git remote -v   #查看当前所有远程地址别名
git remote add  #别名远程地址
```

![image-20220311120648826](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311120648826.png)

### 6.2.2推送本地分支到远程仓库

1)基本语法

```shell
git push 别名分支
```

![image-20220311122811819](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311122811819.png)

![image-20220311122819211](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311122819211.png)

### 6.2.3.拉取远程库到本地

```shell
git pull 别名/链接 分支名  #会自动合并
```

![image-20220311123308634](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311123308634.png)

### 6.2.4克隆远程仓库到本地

1)基本语法

```shell
git clone 远程地址
```

![image-20220311123516844](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311123516844.png)

![image-20220311123532042](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311123532042.png)

小结：clone会做如下操作。1、拉取代码。2、初始化本地仓库。3、创建别名

![image-20220311123604060](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311123604060.png)

### 6.2.5邀请加入团队

1)选择邀请合作者

![image-20220311124017366](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311124017366.png)

![image-20220311124558825](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311124558825.png)

![image-20220311124215597](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311124215597.png)

2.此时被人就可以推送代码到你的Github仓库

## 6.3跨团队协作

![image-20220311125617252](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311125617252.png)

![image-20220311125636995](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311125636995.png)



修改完之后，向别人申请

![image-20220311125720757](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311125720757.png)

![image-20220311125843758](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311125843758.png)

别人的账号

![image-20220311125910266](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311125910266.png)

点击合并就可以合并代码

![image-20220311130009525](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311130009525.png)

## 6.4SSH免密登录

我们可以看到远程仓库中还有一个SSH的地址，因此我们也可以使用SSH进行访问。

![image-20220311130629736](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311130629736.png)

具体操作如下

![image-20220311130654015](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311130654015.png)

![image-20220311131049815](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311131049815.png)

![image-20220311131145294](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311131145294.png)





复制一份公钥的内容

![image-20220311131559941](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311131559941.png)

![image-20220311131748304](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311131748304.png)

![image-20220311131828850](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311131828850.png)

# 第7章IDEA集成Git

## 7.1配置Git忽略文件

![image-20220311132034851](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311132034851.png)

![image-20220311132047309](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311132047309.png)

![image-20220311132115229](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311132115229.png)

问题1：为什么要忽略他们？

答：与顶目的实际功能无关，不参与服务器上部署运行。把它们忽略掉能够屏蔽IDE工具之间的差异。

问题2：怎么忽略？
1)创建忽略规则文件xxxx.ignore(前缀名随便起，建议是git.ignore)

这个文件的存放位置原则上在哪里都可以，为了便于让~/.gitconfig文件引用，建议也放在用户家目录下

git.ignore文件模版内容如下：

```properties
#Compiled class filed
*.class

#Log filed
*.log

#BlueJ files
*.ctxt

#Mobile Tools for Java (J2ME)
.mtj.tmp/

#virtual machine crash logs,see http://www.java.com/en/download/help/error hotspot.xml
hs_err_pid*

#Package Files
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar
.classpath
.project
.settingse
target
.idea
*.iml
```



2)在.gitconfig文件中引用忽略配置文件（此文件在Windows的家目录中）

```properties
[user]
	name = zhang
	email = 18577787964@sina.cn
[core]
	excludesfile = C:/Users/YQ/git.ignore
#注意：这里要使用“正斜线(/)”，不要使用“反斜线（八）”
```



## 7.2定位Git程序

![image-20220311133516803](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311133516803.png)

## 7.3初始化本地库

![image-20220311133719825](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311133719825.png)

![image-20220311133743674](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311133743674.png)

## 7.4添加到暂存区

![image-20220311134054565](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311134054565.png)

## 7.5提交到本地库

![image-20220311134512483](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311134512483.png)

## 7.6切换版本

![image-20220311134828152](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311134828152.png)

## 7.7创建分支

![image-20220311135014071](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311135014071.png)



或者



![image-20220311135126951](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311135126951.png)

![image-20220311135047839](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311135047839.png)



## 7.8切换分支

![image-20220311135236211](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311135236211.png)

## 7.9合并分支

![image-20220311135944846](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311135944846.png)

如果代码没有冲突，分支直接合并成功，分支合并成功以后，代码自动提交，无需手动提交本地库。

## 7.10解决冲突

![image-20220311140921392](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311140921392.png)

![image-20220311141003824](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141003824.png)

# 第8章IDEA集成GitHub

## 8.1设置GitHub账号

![image-20220311141312388](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141312388.png)



获取口令

![image-20220311141445300](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141445300.png)

![image-20220311141709042](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141709042.png)



权限拉满，点击创建

![image-20220311141808561](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141808561.png)

```
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
```

![image-20220311141850024](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141850024.png)

## 8.2分享工程到GitHub

![image-20220311141946827](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311141946827.png)

![image-20220311142213145](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311142213145.png)

## 8.3push推送本地库到远程库

![image-20220311142436374](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311142436374.png)

## 8.4pull拉取远程库到本地库

注意：push是将本地库代码推送到远程库，如果本地库代码跟远程库代码版本不一致，push的操作是会被拒绝的。也就是说，要想push成功，一定要保证本地库的版本要比远程库的版本高！因此一个成熟的程序员在动手改本地代码之前，一定会先检查下远程库跟本地
代码的区别！如果本地的代码版本已经落后，切记要先pu拉取一下远程库的代码，将本地代码更新到最新以后，然后再修改，提交，推送！

注意：pull是拉取远端仓库代码到本地，如果远程库代码和本地库代码不一致，会自动合并，如果自动合并失败，还会涉及到手动解决冲突的问题。

![image-20220311143659397](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311143659397.png)

## 8.5 clone克隆远程库到本地

![image-20220311144007367](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311144007367.png)

![image-20220311144031350](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311144031350.png)

![image-20220311144123373](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311144123373.png)

# 第9章国内代码托管中心-码云

## 9.1简介

众所周知正GitHub服务器在国外，使用GitHub作为项目托管网站，如果网速不好的话，严重影响使用体验，甚至会出现登录不上的情况。针对这个情况，大家也可以使用国内的项目托管网站-码云。

码云是开源中国推出的基于Git的代码托管服务中心，网址是https://gitee.com/,使用方式跟GitHub一样，而且它还是一个中文网站，如果你英文不是很好它是最好的选择。

## 9.2码云创建远程库

![image-20220311144437423](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311144437423.png)

## 9.3.IDEA使用码云

安装码云插件

![image-20220311144556689](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311144556689.png)

![image-20220311144656301](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311144656301.png)

## 9.4.码云导入Github项目

![image-20220311145024247](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311145024247.png)

![image-20220311145420112](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311145420112.png)

# 第10章自建代码托管平台-GitLab

## 10.1 GitLab简介

GitLab是由GitLabInc..开发，使用MIT许可证的基于网络的Git仓库管理工具，且具有wiki和issue跟踪功能。使用Git作为代码管理工具，并在此基础上搭建起来的wb服务。GitLab由乌克兰程序员DmitriyZaporozhets和alerySizov开发，它使用Ruby语言写
成。后来，一些部分用GO语言重写。截止2018年5月，该公司约有290名团队成员，以及2000多名开源贡献者。GitLab被IBM,Sony,JuilichResearchCenter,NASA,Alibaba,Invincea,O'ReillyMedia,Leibniz-Rechenzentrum(LRZ),CERN,SpaceX等组织使用。

## 10.2 GitLab官网地址

官网地址：https://about.gitlab.com

安装说明：[下载安装_极狐GitLab - 极狐GitLab 官方网站](https://about.gitlab.cn/install/)

## 10.3 GitLab安装

### 10.3.1服务器准备

准备一个系统为CentOS7以上版本的服务器，要求内存4G,磁盘50G。:

关闭防火墙，并且配置好主机名和IP,保证服务器可以上网。

此教程使用虚拟机：主机名：Redis IP地址：192.168.229.131

### 10.3.2安装包准备

Yum在线安装gitlab-ce时，需要下载安装文件，非常耗时，所以最好提前把所需RPM包下载到本地，然后使用离线pm的方式安装。

下载地址

https://packages.gitlab.com/gitlab/gitlab-ce/packages/ol/7/gitlab-ce-14.7.5-ce.0.el7.x86_64.rpm

### 10.3.3编写安装脚本

![image-20220311153058241](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311153058241.png)

```shell
cd /opt/git
vim gitlab-install.sh
```



```bash
sudo yum install -y curl policycoreutils-python openssh-server perl

sudo systemctl enable sshd
sudo systemctl start sshd

sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=https
sudo systemctl reload firewalld

sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=https
sudo systemctl reload firewalld

sudo yum install postfix -y
sudo systemctl enable postfix
sudo systemctl start postfix

curl -s https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.rpm.sh | sudo bash

export EXTERNAL_URL=http://gitlab.example.com
sudo rpm -ivh gitlab-ce-14.7.5-ce.0.el7.x86_64.rpm
#yum  -y install gitlab-ce

```

```shell
#可执行权限
chmod +x ditlab-install.sh

#执行
./gitlab-install.sh
```

配置文件

```shell
vim /etc/gitlab/gitlab.rb
```



### 10.3.4初始化GitLab服务

执行以下命令初始化GitLab服务，过程大概需要几分钟，耐心等待

```shell
gitlab-ctl reconfigure
```

### 10.3.5启动GitLab服务

执行以下命令启动GitLab服务，如需停止，执行gitlab-ctl stop

```shell
gitlab-ctl start
gitlab-ctl stop
```

### 10.3.6使用浏览器访问GitLab(192.168.229.131)(yq001215)

![image-20220311163509794](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311163509794.png)

![image-20220311163535709](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311163535709.png)



### 10.3.7 GitLab创建远程库

![image-20220311163814771](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311163814771.png)



### 10.3.81DEA集成GitLab

安装GitLab插件



![image-20220311164005996](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311164005996.png)

![image-20220311164327744](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311164327744.png)

![image-20220311164339237](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311164339237.png)





![image-20220311164437520](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220311164437520.png)

