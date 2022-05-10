# 1. Docker简介

## 1.1. Docker是什么

### 1.1.1.为什么有Docker

假定您在开发一个尚硅谷的谷粒商城，您使用的是一台笔记本电脑而且您的开发环境具有特定的配置。其他开发人员身处的环境配置也各有不同。您正在开发的应用依赖于您当前的配置且还要依赖于某些配置文件。此外，您的企业还拥有标准化的测试和生产环境且具有自身的配置和一系列支持文件。您希望尽可能多在本地模拟这些环境而不产生重新创建服务器环境的开销。请问？您要如何确保应用能够在这些环境中运行和通过质量检测？并且在部署过程中不出现令人头疼的版本、配置问题，也无需重新编写代码和进行故障修复？

答案就是使用容器。Docker.之所以发展如此迅速，也是因为它对此给出了一个标准化的解决方案——**系统平滑移植，容器虚拟化技术。**

环境配置相当麻烦，换一台机器，就要重来一次，费力费时。很多人想到，能不能从根本上解决问题，**软件可以带环境安装**？也就是说，**安装的时候，把原始环境一模一样地复制过来。开发人员利用Docker可以消除协作编码时“在我的机器上可正常工作”的问题。**

![image-20220307094537789](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307094537789.png)

之前在服务器配置一个应用的运行环境，要安装各种软件，就拿尚硅谷电商项目的环境来说，Java/RabbitMQ/MySQL/JDBC驱动包等。安装和配置这些东西有多麻烦就不说了，它还不能跨平台。假如我们是在Windows上安装的这些环境，到了Linux又得重新装。况且就算不跨操作系统，换另一台同样操作系统的服务器，要**移植**应用也是非常麻烦的。

传统上认为，软件编码开发/测试结束后，所产出的成果即是程序或是能够编译执行的二进制字节码等(java为例)。而为了让这些程序可以顺利执行，开发团队也得准备完整的部署文件，让维运团队得以部署应用程式，开发需要清楚的告诉运维部署团队，用的全部配置文件+所有软件环境。不过，即便如此，仍然常常发生部署失败的状况。**Dockerf的出现使得Docker得以打破过去「程序即应用」的观念。透过镜像(images)将作业系统核心除外，运作应用程式所需要的系统环境，由下而上打包，达到应用程式跨平台间的无缝接轨运作。**

### 1.1.2.Docker是什么

**Docker是基于Go语言实现的云开源项目。**
Docker的主要目标是“Build Ship and Run Any App,Anywhere”,也就是通过对应用组件的封装、分发、部署、运行等生命周期的管理，使用户的APP(可以是一个WEB应用或数据库应用等等)及其运行环境能够做到“一次镜像，处处运行“

![image-20220307131002921](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307094537789.png)

**Linux容器技术的出现就解决了这样一个问题，而Docker就是在它的基础上发展过来的**。将应用打成镜像，通过镜像成为运行在Docker容器上面的实例，而Docker容器在任何操作系统上都是一致的，这就实现了跨平台、跨服务器。**只需要一次配置好环境，换到别的机子上就可以一键部署好，大大简化了操作。**

**解决了运行环境和配置问题的软件容器，方便做持续集成并有助于整体发布的容器虚拟化技术。**

## 1.2.容器与虚拟机比较

虚拟机(virtual machine)就是带环境安装的一种解决方案。

它可以在一种操作系统里面运行另一种操作系统，比如在Windows 10系统里面运行Linux系统CentOS7。应用程序对此毫无感知，因为虚拟机看上去跟真实系统一模一样，而对于底层系统来说，虚拟机就是一个普通文件，不需要了就删掉，对其他部分毫无影响。这类虚拟机完美的运行了另一套系统，能够使应用程序，操作系统和硬件三者之间的逻辑不变。

传统虚拟机技术基于安装在主操作系统上的虚拟机管理系统（如：VirtualBox和VMWare等)，创建虚拟机（虚拟出各种硬件），在虚拟机上安装从操作系统，在从操作系统中安装部署各种应用。

虚拟机的缺点：

1. 资源占用多
2. 冗余步骤多
3. 启动慢

由于前面虚拟机存在某些缺点，Linux发展出了另一种虚拟化技术：linux容器(Linux Containers,缩写为LXC)

Linux容器是与系统其他部分隔离开的一系列进程，从另一个镜像运行，并由该镜像提供支持进程所需的全部文件。容器提供的镜像包含了应用的所有依赖项，因而在从开发到测试再到生产的整个过程中，它都具有可移植性和一致性。

**Linux容器不是模拟一个完整的操作系统而是对进程进行隔离**。有了容器，就可以将软件运行所需的所有资源打包到一个隔离的容器中。**容器与虚拟机不同，不需要捆绑一整套操作系统**，只需要软件工作所需的库资源和设置。系统因此而变得高效轻量并保证部署在任何环境中的软件都能始终如一地运行。

Docker容器是在操作系统层面上实现虚拟化，直接复用本地主机的操作系统，而传统虚拟机则是在硬件层面实现虚拟化。与传统的虚拟机相比，Docker优势体现为启动速度快、占用体积小。

![image-20220307131811737](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307131811737.png)

比较了Docker和传统虚拟化方式的不同之处：

- 传统虚拟机技术是虚拟出一套硬件后，在其上运行一个完整操作系统，在该系统上再运行所需应用进程：
- 容器内的应用进程直接运行于宿主的内核，容器内没有自己的内核且也**没有进行硬件虚拟**。因此容器要比传统虚拟机更为轻便。
- 每个容器之间互相隔离，每个容器有自己的文件系统，容器之间进程不会相互影响，能区分计算资源。

## 1.3.去哪下

官网    docker'官网：http://www.docker.com

仓库    Docker Hub官网：https://hub.docker.com

# 2.CentOS Docker安装

Docker并非是一个通用的容器工具，它依赖于已存在并运行的Linux内核环境。Docker实质上是在已经运行的Linux下制造了一个隔离的文件环境，因此它执行的效率几乎等同于所部署的Linux主机。因此，**Docker必须部署在Linux内核的系统上**。如果其他系统想部Docker就必须安装一个虚拟Linux环境。

在Windows上部署Docker的方法都是先安装一个虚拟机，并在安装Linux系统的的虚拟机中运行Docker。

**前提条件**

目前，CentOS仅发行版本中的内核支持Docker。Docker运行在CentOS7(64-bit)上，要求系统为64位、Linux系统内核版本为3.8以上，这里选用Centos7.x

**查看自己的内核**

uname命令用于打印当前系统相关信息（内核版本号、硬件架构、主机名称和操作系统类型等）

![image-20220307132650063](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307132650063.png)

## 2.1. Docker的基本组成

### 2.1.1 镜像(image)

Docker镜像(Image)就是一个**只读**的模板。镜像可以用来创建Docker容器，**一个镜像可以创建很多容器**。它也相当于是一个root文件系统。比如官方镜像centos7就包含了完整的一套centos7最小系统的root文件系统。相当于容器的“源代码”，**docker镜像文件类似于Java的类模板，而docker?容器实例类似于java中new出来的实例对象。**

![image-20220307132947894](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307132947894.png)

### 2.1.2. 容器(container)

从面向对象角度

Docker利用容器(Container)独立运行的一个或一组应用，应用程序或服务运行在容器里面，容器就类似于一个虚拟化的运行环境，**容器是用镜像创建的运行实例**。就像是Java中的类和实例对象一样，镜像是静态的定义，容器是镜像运行时的实体。容器为镜像提供了一个标准的和隔离的运行环境，它可以被启动、开始、停止、删除。每个容器都是相互隔离的、保证安全的平台

从镜像容器角度

**可以把容器看做是一个简易版的Linux环境**（包括root用户权限、进程空间、用户空间和网络空间等）和运行在其中的应用程序。

### 2.1.3 仓库(repository)

仓库(Repository)是集中存放镜像文件的场所

类似于

Maven仓库，存放各种jar包的地方；

github仓库，存放各种git项目的地方；

Docker公司提供的官方registry被称为Docker Hub,存放各种镜像模板的地方。

仓库分为公开仓库(Public)和私有仓库(Private)两种形式。最大的公开仓库是Docker Hub(https://hub.docker.com),存放了数量庞大的镜像供用户下载。国内的公开仓库包括阿里云、网易云等

### 2.1.4. 总结

需要正确的理解仓库/镜像/容器这几个概念：

Docker本身是一个容器运行载体或称之为管理引擎。我们把应用程序和配置依赖打包好形成一个可交付的运行环境，这个打包好的运行环境就是image镜像文件。只有通过这个镜像文件才能生成Docker?容器实例（类似Java中new出来一个对象）。image文件可以看作是容器的模板。Docker根据image文件生成容器的实例。同一个image文件，可以生成多个同时运行的容器实例。

镜像文件

image文件生成的容器实例，本身也是一个文件，称为镜像文件。

容器实例

一个容器运行一种服务，当我们需要的时候，就可以通过docker客户端创建一个对应的运行实例，也就是我们的容器

仓库

就是放一堆镜像的地方，我们可以把镜像发布到仓库中，需要的时候再从仓库中拉下来就可以了。

## 2.2. Docker平台架构图解(入门版)

![image-20220307133551402](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307133551402.png)

运行原理

Docker是一个Client--Server结构的系统，Docker守护进程运行在主机上，然后通过Socket连接从客户端访问，守护进程从客户端接受命令并管理运行在主机上的容器。**容器，是一个运行时环境，就是我们前面说到的集装箱。可以对比mysql演示对比讲解**

![image-20220307133816419](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307133816419.png)

## 2.3.Docker平台架构图解(架构版)

Docker是一个C/S模式的架构，后端是一个松耦合架构，众多模块各司其职。

Docker运行的基本流程为：

1. 用户是使用Docker Client.与Docker Daemon建立通信，并发送请求给后者。
2. Docker Daemon作为Docker架构中的主体部分，首先提供Docker Server的功能使其可以接受Docker Client的请求。
3. Docker Engine执行Docker内部的一系列工作，每一项工作都是以一个Job的形式的存在。
4. Job的运行过程中，当需要容器镜像时，则从Docker Registry中下载镜像，并通过镜像管理驱动Graph driver将下载镜像以Graph的形式存储。
5. 当需要为Docker创建网铬环境时，通过网铬管理驱动Network driver创建并配置Docker:容器网铬环境。
6. 当需要限制Docker容器运行资源或执行用户指令等操作时，则通过Exec driver来完成。
7. Libcontainer是一项独立的容器管理包，Network driver以及Exec driver都是通过Libcontainer来实现具体对容器进行的操作。

![image-20220307134338937](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307134338937.png)

![image-20220307134511660](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307134511660.png)

![image-20220307134629283](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307134629283.png)

## 2.4. 安装步骤

[Install Docker Engine on CentOS | Docker Documentation](https://docs.docker.com/engine/install/centos/)

1. 确定是centOS7以上的版本

	```hsell
	cat /etc/redhat-release 
	```

2. 卸载旧版本

	```shell
	sudo yum remove docker \
	                  docker-client \
	                  docker-client-latest \
	                  docker-common \
	                  docker-latest \
	                  docker-latest-logrotate \
	                  docker-logrotate \
	                  docker-engine
	```

3. yum安装gcc相关

	```shell
	yum install gcc -y
	
	yum install gcc-c++ -y
	```

4. 安装需要的软件包

	```shell
	sudo yum install -y yum-utils
	```

5. 设置stable镜像仓库

	![image-20220307135832852](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307135832852.png)

	```shell
	sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
	```

6. 更新yum软件包索引

	```shell
	yum makecache fast
	```

7. 安装DOCKER CE

	```shell
	sudo yum install docker-ce docker-ce-cli containerd.io 
	```

8. 启动docker

	```shell
	systemctl start docker
	```

9. 测试

	```shell
	docker run hello-world
	```

10. 卸载

	```shell
	systemctl stop docker
	
	yum remove docker-ce docker-ce-cli containerd.io
	
	rm -rf /var/lib/docker
	
	rm -rf /var/lib/containerd
	```

	![image-20220307141315051](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307141315051.png)

## 2.5.阿里云镜像加速

[阿里云-上云就上阿里云 (aliyun.com)](https://www.aliyun.com/?spm=5176.12818093.top-nav.dlogo.5adc16d06Pzh2O)

![image-20220307142214681](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307142214681.png)

![image-20220307142312470](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307142312470.png)

![image-20220307142349363](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307142349363.png)

![image-20220307142358856](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307142358856.png)

![image-20220307142722708](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220307142722708.png)

```shell
[root@localhost ~]# mkdir -p /etc/docker
[root@localhost ~]# tee /etc/docker/daemon.json <<-'EOF'
> {
>   "registry-mirrors": ["https://0gj8no3j.mirror.aliyuncs.com"]
> }
> EOF
{
  "registry-mirrors": ["https://0gj8no3j.mirror.aliyuncs.com"]
}
[root@localhost ~]# sudo systemctl daemon-reload
[root@localhost ~]# sudo systemctl restart docker
```

## 2.6. Docker run执行流程

![image-20220308090931869](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308090931869.png)

## 2.7. Docker为什么比VM虚拟机快

(1)**docker有着比虚拟机更少的抽象层**

由于docker不需要Hypervisor(虚拟机)实现硬件资源虚拟化，运行在docker容器上的程序直接使用的都是实际物理机的硬件资源。因此在CPU、内存利用率上docker将会在效率上有明显优势。

(2)**docker利用的是宿主机的内核，而不需要加载操作系统OS内核**

当新建一个容器时，docker不需要和虚拟机一样重新加载一个操作系统内核。进而避免引寻、加载操作系统内核返回等比较费时费资源的过程，当新建一个虚拟机时，虚拟机软件需要加载OS,返回新建过程是分钟级别的。而docker由于直接利用宿主机的操作系统，则省略了返回过程，因此新建一个docker?容器只需要几秒钟。

![image-20220308091343256](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308091343256.png)

![image-20220308091420170](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308091420170.png)

# 3. Docker常用命令

## 3.1. 帮助启动类命令

- 启动docker：`systemctl start docker`
- 停止docker：`systemctl stop docker`
- 重启docker：`systemctl restart docker`
- 查看docker状态：`systemctl status docker`
- 开机启动：`systemctl enable docker`
- 查看docker概要信息：`docker info`
- 查看docker.总体帮助文档：`docker --help`
- 查看docker命令帮助文档：`docker 具体命令 --help`

## 3.2. 镜像命令

### docker images

列出本地主机上的镜像

![image-20220308092141270](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308092141270.png)

各个选项说明：

- REPOSITORY:表示镜像的仓库源
- TAG:镜像的标签
- IMAGE ID:镜像ID
- CREATED:镜像创建时间
- SIZE:镜像大小

同一仓库源可以有多个TAG版本，代表这个仓库源的不同个版本，我们使用REPOSITORY:TAG来定义不同的镜像。如果你不指定一个镜像的版本标签，例如你只使用ubuntu，docker将默认使用ubuntu:latest镜像

常用选项

-a:列出本地所有的镜像（含历史映像层）
-g:只显示镜像ID。

```shell
[root@localhost ~]# docker images -q
feb5d9fea6a5
[root@localhost ~]# docker images -a
REPOSITORY    TAG       IMAGE ID       CREATED        SIZE
hello-world   latest    feb5d9fea6a5   5 months ago   13.3kB
```

### docker search 镜像名称

搜索镜像

![image-20220308092558647](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308092558647.png)

|    参数     |       说明       |
| :---------: | :--------------: |
|    NAME     |     镜像名称     |
| DESCRIPTION |     镜像描述     |
|    STARS    |     点赞数量     |
|  OFFICIAL   |   是否是官方的   |
|    AUTO     | 是否是自动构建的 |

常用选项

--limit:只列出N个镜像，默认25个

```shell
[root@localhost ~]# docker search redis --limit 5
NAME                     DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
redis                    Redis is an open source key-value store that…   10630     [OK]       
bitnami/redis            Bitnami Redis Docker Image                      211                  [OK]
bitnami/redis-sentinel   Bitnami Docker Image for Redis Sentinel         34                   [OK]
circleci/redis           CircleCI images for Redis                       12                   [OK]
bitnami/redis-exporter   
```

### docker pull 镜像名称

下载镜像

带版本号

```shell
docker pull 镜像名称 [:TAG]
```

不带版本号

```shell
docker pull 镜像名称 
```

没有TAG就是最新版 等价于 docker pull 镜像名字 :latest

```shell
[root@localhost ~]# docker pull hello-world
Using default tag: latest
latest: Pulling from library/hello-world
Digest: sha256:2498fce14358aa50ead0cc6c19990fc6ff866ce72aeb5546e1d59caac3d0d60f
Status: Image is up to date for hello-world:latest
docker.io/library/hello-world:latest
```

```shell
docker pull ubuntu
```

```shell
[root@localhost ~]# docker pull ubuntu
Using default tag: latest
latest: Pulling from library/ubuntu
7b1a6ab2e44d: Pull complete 
Digest: sha256:626ffe58f6e7566e00254b638eb7e0f3b11d4da9675088f4781a50ae288f3322
Status: Downloaded newer image for ubuntu:latest
docker.io/library/ubuntu:latest
[root@localhost ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED        SIZE
ubuntu        latest    ba6acccedd29   4 months ago   72.8MB
hello-world   latest    feb5d9fea6a5   5 months ago   13.3kB
```

### docker system df

查看镜像/容器/数据卷所占的空间

```shell
[root@localhost ~]# docker system df
TYPE            TOTAL     ACTIVE    SIZE      RECLAIMABLE
Images          2         1         72.79MB   72.78MB (99%)
Containers      2         0         0B        0B
Local Volumes   0         0         0B        0B
Build Cache     0         0         0B        0B
```

### docker rmi 某个XXX镜像名字/ID

删除镜像

删除单个

```shell
docker rmi -f 镜像ID   (-f: force:强制删除)
```

```shell
[root@localhost ~]# docker rmi hello-world 
Error response from daemon: conflict: unable to remove repository reference "hello-world" (must force) - container 5fa02f16d0a8 is using its referenced image feb5d9fea6a5
[root@localhost ~]# docker rmi -f hello-world 
Untagged: hello-world:latest
Untagged: hello-world@sha256:2498fce14358aa50ead0cc6c19990fc6ff866ce72aeb5546e1d59caac3d0d60f
Untagged: hello-world@sha256:97a379f4f88575512824f3b352bc03cd75e239179eea0fecc38e597b2209f49a
Deleted: sha256:feb5d9fea6a5e9606aa995e879d862b825965ba48de054caab5ef356dc6b3412
```

删除多个

```shell
docker rmi -f 镜像名1:TAG 镜像名2:TAG
```

删除全部

```shell
docker rmi-f $(docker images -qa)
```

### 面试题：谈谈docker)虚悬镜像是什么？

仓库名、标签都是<none>的镜像，俗称虚悬镜像dangling image

后续Docker file章节再介绍

![image-20220308094310809](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308094310809.png)

## 3.3. 容器命令

**<font color='red'>有镜像才能创建容器，这是根本前提（下载一个CentOS.或者ubuntu镜像演示）</font>**

说明

![image-20220308095158187](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308095158187.png)

本次演示用ubuntu:演示

### 新建+启动容器

```shell
docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
```

OPTIONS说明（常用）：有些是一个减号，有些是两个减号

- --name="容器新名字" 为容器指定一个名称；
- -d:后台运行容器并返回容器D,也即启动守护式容器（后台运行）：
- -i:以交互模式运行容器，通常与-t同时使用：
- -t:为容器重新分配一个伪输入终端，通常与-i同时使用：也即启动交互式容器（前台有伪终端，等待交互）：
- -P:随机端口映射，大写P
- -p:指定端口映射，小写p

| 参数                          | 说明                               |
| :---------------------------- | ---------------------------------- |
| -p hostPort:containerPort     | 端口映射 -p 8080:80                |
| -p ip:hostPort:containerPort  | 配置监听地址 -p 10.0.0.100:8080:80 |
| -p ip:containerPort           | 随机分配端口 -p 10.0.0.100:80      |
| -p hostPort:containerPort:udp | 指定协议-p 8080:80:tcp             |
| -p 81:80 -p 443:443           | 指定多个                           |

**启动交互式容器（前台命令行）**

![image-20220308100256893](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308100256893.png)

#使用镜像ubuntu:latest以交互模式启动一个容器，在容器内执行bin/bash命令。

```shell
docker run -it ubuntu /bin/bash
```

参数说明：

-i:交互式操作。

-t:终端。

ubuntu:ubuntu镜像。

bin/bash:放在镜像名后的是命令，这里我们希望有个交互式Shell,,因此用的是bin/bash。

要退出终端，直接输入exit:

### 列出当前所有正在运行的容器

```shell
docker ps [OPTIONS]
```

![image-20220308100517852](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308100517852.png)

-a:列出当前所有正在运行的容器+历史上运行过的

-l:显示最近创建的容器。

-n:显示最近n个创建的容器

-q:静默模式，只显示容器编号。（正在运行的容器）

```shell
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS         PORTS     NAMES
dad23b98130f   ubuntu    "/bin/bash"   3 minutes ago   Up 3 minutes             compassionate_grothendieck

[root@localhost ~]# docker ps -a
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS                      PORTS     NAMES
bd6b4c11c476   ubuntu         "/bin/bash"   2 minutes ago   Exited (0) 2 minutes ago              stoic_torvalds
dad23b98130f   ubuntu         "/bin/bash"   3 minutes ago   Exited (0) 14 seconds ago             compassionate_grothendieck
e502c8cbc7d4   ubuntu         "/bin/bash"   7 minutes ago   Exited (0) 6 minutes ago              jovial_galois
5fa02f16d0a8   feb5d9fea6a5   "/hello"      20 hours ago    Exited (0) 20 hours ago               boring_chaum
2e11a7dd3c54   feb5d9fea6a5   "/hello"      20 hours ago    Exited (0) 20 hours ago               pedantic_knuth

[root@localhost ~]# docker ps -l
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS                     PORTS     NAMES
bd6b4c11c476   ubuntu    "/bin/bash"   2 minutes ago   Exited (0) 2 minutes ago             stoic_torvalds

[root@localhost ~]# docker ps -n 5
CONTAINER ID   IMAGE          COMMAND       CREATED         STATUS                      PORTS     NAMES
bd6b4c11c476   ubuntu         "/bin/bash"   3 minutes ago   Exited (0) 3 minutes ago              stoic_torvalds
dad23b98130f   ubuntu         "/bin/bash"   4 minutes ago   Exited (0) 54 seconds ago             compassionate_grothendieck
e502c8cbc7d4   ubuntu         "/bin/bash"   7 minutes ago   Exited (0) 7 minutes ago              jovial_galois
5fa02f16d0a8   feb5d9fea6a5   "/hello"      20 hours ago    Exited (0) 20 hours ago               boring_chaum
2e11a7dd3c54   feb5d9fea6a5   "/hello"      20 hours ago    Exited (0) 20 hours ago               pedantic_knuth

[root@localhost ~]# docker ps -q
e96636a2ef82
[root@localhost ~]# 
```

### 退出容器

两种退出方式

- exit
	run进去容器，exit退出，容器停止

- ctrl+p+q

	run进去容器，ctrl+p+q退出，容器不停止

### 启动己停止运行的容器

```shell
docker start 容器ID或容器名
```

### 重启容器

```shell
docker restart 容器ID或容器名
```

### 停止容器

```shell
docker stop 容器ID或者容器名
```

### 强制停止容器

```shell
docker kill 容器ID或者容器名
```

### 删除己停止的容器

```shell
docker rm 容器ID或者容器名
```

一次性删除多个容器实例

```shell
docker rm -f $(docker ps -a -q)

[root@localhost ~]# docker rm -f $(docker ps -a -q)
147a77ef1ee0
e96636a2ef82
bd6b4c11c476
dad23b98130f
e502c8cbc7d4
5fa02f16d0a8
2e11a7dd3c54
[root@localhost ~]# 

docker ps -a -q | xargs docker rm
```

### <font color='red'>重要</font>

**<font color='red'>有镜像才能创建容器，这是根本前提（下载一个Redis6.0.8镜像演示）</font>**

![image-20220308102804205](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308102804205.png)

#### 启动守护式容器（后台服务器）

在大部分的场景下，我们希望docker的服务是在后台运行的，我们可以过-d指定容器的后台运行模式。

`docker run -d 容器名`

#使用镜像ubuntu:latest以后台模式启动一个容器

`docker run -d ubuntu`

问题：然后docker ps -a进行查看，会发现**容器已经退出**

很重要的要说明的一点：Docker容器后台运行，**就必须有一个前台进程**

容器运行的命令**如果不是那些一直挂起的命令**（比如运行top,tail),就是会自动退出的。这个是docker的机制问题，比如你的web容器，我们以nginx为例，正常情况下，我们配置启动服务只需要启动响应的service即可。例如service nginx start但是，这样做，nginx为后台进程模式运行，就导致docker前台没有运行的应用，这样的容器后台启动后，会立即自杀因为他觉得他没事可做了.

**所以，最佳的解决方案是，将你要运行的程序以前台进程的形式运行，常见就是命令行模式，表示我还有交互操作，别中断，O(∩_∩)O哈哈~**

**redis前后台启动演示case**

- 前台交互式启动

	`docker run -it redis:6.0.8`

- 后台守护式启动

	`docker run -d redis:6.0.8`

#### 查看容器日志

```shell
docker logs 容器ID
```

#### 查看容器内运行的进程

```shell
docker top 容器ID
```

#### 查看容器内部细节

```shell
docker inspect 容器ID
```

#### 进入正在运行的容器并以命令行交互

```shell
docker exec -it 容器ID /bin/bash
```

```shell
#重新进入
docker attach 容器ID
```

上述两个区别

- attach直接进入**容器启动命令的终端**，**不会启动新的进程**，用exit退出，会导致容器的停止。

- exec是在容器中**打开新的终端**，并且**可以启动新的进程**，用exit退出，不会导致容器的停止。

推荐大家使用docker exec命令，因为退出容器终端，不会导致容器的停止。

**进入redis服务**

```shell
docker exec-it容器ID /bin/bash

[root@localhost ~]# docker exec -it 99f5b2d15bd4 /bin/bash
#redis-cli连接redis（类似与连接MySQL）
root@99f5b2d15bd4:/data# redis-cli
127.0.0.1:6379> exit
root@99f5b2d15bd4:/data# exit
exit
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE         COMMAND                  CREATED          STATUS          PORTS      NAMES
99f5b2d15bd4   redis:6.0.8   "docker-entrypoint.s…"   15 minutes ago   Up 15 minutes   6379/tcp   vigorous_chandrasekhar

docker exec-it容器ID redis-cli
[root@localhost ~]# docker exec -it 99f5b2d15bd4 redis-cli
127.0.0.1:6379>

```

一般用-d后台启动的程序，再用exec进入对应容器实例

#### 从容器内拷贝文件到主机上

容器→主机

```shell
docker cp 容器ID:容器内路径 目的主机路径

[root@localhost ~]# docker ps 
CONTAINER ID   IMAGE         COMMAND                  CREATED          STATUS          PORTS      NAMES
ad1eaa2a86f6   ubuntu        "/bin/bash"              7 minutes ago    Up 45 seconds              quizzical_aryabhata
99f5b2d15bd4   redis:6.0.8   "docker-entrypoint.s…"   20 minutes ago   Up 20 minutes   6379/tcp   vigorous_chandrasekhar
[root@localhost ~]# docker exec -it ad1eaa2a86f6 /bin/bash
root@ad1eaa2a86f6:/# cd /home
root@ad1eaa2a86f6:/home# touch a.txt
root@ad1eaa2a86f6:/home# ll
total 0
drwxr-xr-x. 1 root root 19 Mar  8 02:55 ./
drwxr-xr-x. 1 root root 30 Mar  8 02:47 ../
-rw-r--r--. 1 root root  0 Mar  8 02:55 a.txt
root@ad1eaa2a86f6:/home# exit
exit
[root@localhost ~]# docker cp ad1eaa2a86f6:/home/a.txt /home/a.txt
[root@localhost ~]# cd /home
[root@localhost home]# ll
总用量 0
-rw-r--r--. 1 root root 0 3月   8 10:55 a.txt
```

#### 导入和导出容器

export导出容器的内容留作为一个tar归档文件[对应import命令]

import从tar包中的内容创建一个新的文件系统再导入为镜像[对应export]

```shell
docker export 容器ID > 文件名.tar

[root@localhost home]# docker export ad1eaa2a86f6 > /home/ubuntu.tar

[root@localhost home]# ll
总用量 73400
-rw-r--r--. 1 root root        0 3月   8 10:55 a.txt
-rw-r--r--. 1 root root 75159040 3月   8 11:03 ubuntu.tar




cat 文件名.tar | docker import - 镜像用户/镜像名:镜像版本号

[root@localhost home]# docker rm -f ad1eaa2a86f6
ad1eaa2a86f6
[root@localhost home]# docker ps
CONTAINER ID   IMAGE         COMMAND                  CREATED          STATUS          PORTS      NAMES
99f5b2d15bd4   redis:6.0.8   "docker-entrypoint.s…"   30 minutes ago   Up 30 minutes   6379/tcp   vigorous_chandrasekhar

[root@localhost home]# cat /home/ubuntu.tar | docker import - local/test:1.1

sha256:60bdba75523bf3ad818a84dd8f7b18d7b9fb2e7516e35eeb5bd5631b47800cd0
[root@localhost home]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
local/test   1.1       60bdba75523b   6 seconds ago   72.8MB
ubuntu       latest    ba6acccedd29   4 months ago    72.8MB
redis        6.0.8     16ecd2772934   16 months ago   104MB
[root@localhost home]# docker run -it local/test:1.1 /bin/bash
root@db198b5dce5d:/# cd /home
root@db198b5dce5d:/home# ll
total 0
drwxr-xr-x. 2 root root 19 Mar  8 02:55 ./
drwxr-xr-x. 1 root root 18 Mar  8 03:06 ../
-rw-r--r--. 1 root root  0 Mar  8 02:55 a.txt
```

## 3.4. 小总结

![image-20220308121305496](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308121305496.png)

![image-20220308121332418](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308121332418.png)

attach 	Attach to a running container    															 #当前shell下attach连接指定运行镜像

build 	  Build an image from a Dockerfile  														 #通过Dockerfile定制镜像

commit  Create a new image from a container changes  								  #提交当前容器为新的镜像

cp		   Copy files/folders from the containers filesystem to the host path  #从容器中拷贝指定文件或者目录到宿主机中

create    Create a new container  																		   #创建一个新的容器，同run,但不启动容器

diff          Inspect changes on a container's filesystem										#查看docker容器变化

events    Get real time events from the server													 #从docker服务获取容器实时事件

exec 	   Run a command in an existing container											 #在已存在的容器上运行命令

export	Stream the contents of a container as a tar archive					       #导出容器的内容流作为一个tar归档文件[对应import]

history   Show the history of an image															     #展示一个镜像形成历史

images   List images																								 #列出系统当前镜像

import   Create a new filesystem image from the contents of a tarball         #从tar包中的内容创建一个新的文件系统映像[对应export]

info        Display system-wide information                                                          #显示系统相关信息

inspect  Return low-level information on a container                                       #查看容器详细信息

kill          Kill a running container                                                                           #kill指定docker容器

load       Load an image from a tar archive														  #从一个tar包中加载一个镜像对应save

login	 Register or Login to the docker registry server								    #注册或者登陆一个docker源服务器

logout  Log out from a Docker registry server                                                   #从当前Docker registry退出

logs      Fetch the logs of a container                                                                   #输出当前容器日志信息

port      Lookup the public-facing port which is NAT-ed to PRIVATE PORT    #查看映射端口对应的容器内部源端口

pause  Pause all processes within a container                                                  #暂停容器

ps         List containers                                                                                           #列出容器列表

pull      Pull an image or a repository from the docker registry server         #从docker镜像源服务器拉取指定镜像或者库镜像

push    Push an image or a repository to the docker registry server            #推送指定镜像或者库镜像至docker源服务器

restart Restart a running container                                                                    #重启运行的容器

rm	   Remove one or more containers														    #移除一个或者多个容器

rmi      Remove one or more images                                                                  #移除一个或多个镜像[无容器使用该镜像才可删除，否则需删除相关容器才可继续或千强制删除]

run      Run a command in a new container												      #创建一个新的容器并运行一个命令

save	Save an image to a tar archive                                                                #保存一个镜像为一个tar包[对应load]

search Search for an image on the Docker Hub                                              #在docker hub中搜索镜像

start    Start a stopped containers                                                                     #启动容器

stop    Stop a running containers																	   #停止容器

tag      Tag an image into a repository                                                               #给源中镜像打标签

top      Lookup the running processes of a container                                     #查看容器中运行的进程信息

unpause  Unpause a paused container                                                            #取消暂停容器
version Show the docker version information                                                #查看docker版本号
wait 	Block until a container stops,then print its exit code                         #截取容器停止时的退出状态值

# 4. Docker镜像

镜像

是一种轻量级、可执行的独立软件包，它包含运行某个软件所需的所有内容，我们把应用程序和配置依赖打包好形成一个可交付的运行环境（包括代码、运行时需要的库、环境变量和配置文件等），这个打包好的运行环境就是image镜像文件。

只有通过这个镜像文件才能生成Docker容器实例（类似Java中new出来一个对象）.

## 是什么

### UnionFS(联合文件系统)

UnionFS(联合文件系统)：Union文件系统(UnionFS)是一种分层、轻量级并且高性能的文件系统，它支持**对文件系统的修改作为一次提交来一层层的叠加**，同时可以将不同目录挂载到同一个虚拟文件系统下(unite several directories into a single virtual file system)。Union文件系统是Docker镜像的基础。**镜像可以通过分层来进行继承**，基于基础镜像（没有父镜像），可以制作各种具体的应用镜像。

特性：一次同时加载多个文件系统，但从外面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录

### Docker镜像加载原理

docker的镜像实际上由一层一层的文件系统组成，这种层级的文件系统UnionFS。

bootfs(boot file system)主要包含bootloader和kernel,bootloader主要是引导加载kernel,Linux刚启动时会加载bootfs文件系统，在**Docker镜像的最底层是引导文件系统bootfs**。这一层与我们典型的Linux/Unix.系统是一样的，包含boot加载器和内核。当boot加载完
成之后整个内核就都在内存中了，此时内存的使用权己由bootfs转交给内核，此时系统也会卸载bootfs。

rootfs(root file system),在bootfs之上。包含的就是典型Linux系统中的/dev，/proc，/bin，/etc等标准目录和文件。rootfs就是各种不同的操作系统发行版，比如Ubuntu,Centos等等。

![image-20220308123840381](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308123840381.png)

平时我们安装进虚拟机的CentOS都是好几个G,为什么docker这才200M？?

对于一个精简的OS,rootfs可以很小，**只需要包括最基本的命令、工具和程序库就可以了**，因为底层直接用Host的kernel,自己只需要提供rootfs就行了。由此可见对于不同的Iinux发行版，bootfs基本是一致的，rootfs会有差别，因此不同的发行版可以公用bootfs

### 为什么Docker镜像要采用这种分层结构呢

镜像分层最大的一个好处就是共享资源，方便复制迁移，就是为了复用。

比如说有多个镜像都从相同的base镜像构建而来，那么Docker Host只需在磁盘上保存一份base镜像；同时内存中也只需加载一份base镜像，就可以为所有容器服务了。而且镜像的每一层都可以被共享。

## 重点理解

**Docker镜像层都是只读的，容器层是可写的当容器启动时**，一个新的可写层被加载到镜像的顶部。这一层通常被称作“容器层”，“容器层”之下的都叫“镜像层”。

当容器启动时，一个新的可写层被加载到镜像的顶部。这一层通常被称作“容器层”，“容器层”之下的都叫“镜像层”。所有对容器的改动·无论添加、删除、还是修改文件都只会发生在容器层中。**只有容器层是可写的，容器层下面的所有镜像层都是只读的。**

![image-20220308123626580](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308123626580.png)



## Docker镜像commit操作案例

**docker commit 提交容器副本使之成为一个新的镜像**

```shell
docker commit -m="提交的描述信息" -a="作者" 容器ID 要创建的目标镜像名:[标签名]
```

案例演示ubuntu安装vim

从Hub上下载ubuntu镜像到本地并成功运行

原始的默认Ubuntu镜像是不带着vim命令的![image-20220308124227148](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308124227148.png)



外网连通的情况下，安装vim

```shell
#docker运行的Ubuntu上
#更新包管理器
apt-get update

#安装vim
apt-get -y install vim
```

![image-20220308124907492](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308124907492.png)

安装完成后，commit我们自己的新镜像

```shell
docker commit -m="提交的描述信息" -a="作者" 容器ID 要创建的目标镜像名:[标签名]
```

![image-20220308125414382](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308125414382.png)

![image-20220308125451333](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308125451333.png)

启动我们的新镜像并和原来的对比

新镜像:

![image-20220308125654517](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308125654517.png)

旧镜像:

![image-20220308125710748](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308125710748.png)

**总结:**

Docker中的镜像分层，**支持通过扩展现有镜像，创建新的镜像**。类似Java继承于一个Base基础类，自己再按需扩展。新镜像是从base镜像一层一层叠加生成的。每安装一个软件，就在现有镜像的基础上增加一层

![image-20220308125753129](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308125753129.png)

# 5. 本地镜像发布到阿里云

## 本地镜像发布到阿里云流程

![image-20220308130122583](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308130122583.png)

## 镜像的生成方法

上一讲已经介绍过基于当前容器创建一个新的镜像，新功能增强

```shell
docker commit [OPTIONS] ID [REPOSITORY[:TAG]]
```

后面的DockerFile章节，第2种方法

## 将本地镜像推送到阿里云

### 本地镜像素材原型

![image-20220308130324422](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308130324422.png)

### 阿里云开发者平台[阿里云-上云就上阿里云 (aliyun.com)](https://www.aliyun.com/?accounttraceid=030eb56d79bb45ed9619719c843c59fedhqt)

### 创建仓库镜像

#### 选择控制台，进入容器镜像服务

![image-20220308130739846](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308130739846.png)

![image-20220308130818190](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308130818190.png)

![image-20220308130857653](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308130857653.png)

#### 进入个人实例

![image-20220308130937553](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308130937553.png)

![image-20220308131234127](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308131234127.png)

![image-20220308131319542](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308131319542.png)

![image-20220308131353023](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308131353023.png)

#### 进入管理界面获得脚本

![image-20220308131829970](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308131829970.png)

### 将镜像推送到阿里云

#### 管理脚本界面

![image-20220308131920789](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308131920789.png)

#### 推送到阿里云

登录之前要设置密码

![image-20220308132106077](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308132106077.png)

按照管理脚本界面的第三步执行

![image-20220308132726345](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308132726345.png)

![image-20220308132902344](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308132902344.png)

## 将阿里云上的镜像下载到本地

![image-20220308133615640](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308133615640.png)

![image-20220308133917288](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308133917288.png)



## 将本地镜像推送到私有库

1官方Docker Hub地址：https://hub.docker.com,中国大陆访问太慢了且准备被阿里云取代的趋势，不太主流。

2 Dockerhub、阿里云这样的公共镜像仓库可能不太方便，涉及机密的公司不可能提供镜像给公网，所以需要创建一个本地私人仓库供团队使用，基于公司内部项目构建镜像。

Docker Registry是官方提供的工具，可以用于构建私有镜像仓库

### 1. 下载镜像Docker Registry

![image-20220308134855055](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308134855055.png)

### 2.运行私有库registry,相当于本地有个私有Docker hub

```shell
docker run -d -p 5000:5000 -v /zhangyuye/myregistry/:/tmp/registry --privileged=true registry
```

默认情况，仓库被创建在容器的var/lib/registry目录下，建议自行用容器卷映射，方便于宿主机联调

![image-20220308140828486](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308140828486.png)

### 3.案例演示创建一个新镜像，ubuntu安装ifconfig命令(同上安装vim)

从Hub上下载buntu镜像到本地并成功运行

原始的Ubuntu镜像是不带着ifconfig命令的

外网连通的情况下，安装ifconfig命令并测试通过

```shell
apt-get update
apt-get install neet-tools
```

![image-20220308140304843](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308140304843.png)

安装完成后，commit自己的新镜像

```shell
[root@localhost home]# docker commit -m="idconfig" -a="zhangyuye" 286d7c3ae46b ifconfig/ubuntu:1.0.0
```

启动我们的新镜像并和原来的对比

### 4.curl验证私服库上有什么镜像

```shell
#ip地址更换为自己的
curl -XGET http://192.168.229.129:5000/v2/_catalog
```

![image-20220308141616392](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308141616392.png)

### 5.将新镜像ifconfig/ubuntu:1.0.0修改符合私服规范的Tag

```shell
按照公式：docker tag 镜像:Tag Host:Port/Repository:Tag
自己host主机IP地址，填写你们自己的，不要粘贴错误，O(∩_∩)O
使用命令docker tag将ifcongif/ubuntu:1.0.0这个镜像修改为192.168.229.129:5000/zhangyuye:1.0.0
```

![image-20220308142654811](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308142654811.png)

### 6.修改配置文件使之支持http

vim命令新增如下红色内容：`vim /etc/docker/daemon.json`

```properties
#注意自己主机的ip
{
    "registry-mirrors":["https://aa25jngu.mirror.aliyuncs.com"],
    "insecure-registries":["192.168.229.129:5000"]
}
```

上述理由：docker默认不允许http方式推送筑像，通过配置选项来取消这个限制。===>修改完后如果不生效，建议重启docker

```shell
[root@localhost home]# systemctl restart docker
[root@localhost home]# docker run -d -p 5000:5000 -v /zhangyuye/myregistry/:/tmp/registry --privileged=true registry
```

### 7. push推送到私服库

![image-20220308143006095](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308143006095.png)

### 8.curl验证私服库上有什么镜像

```shell
|curl -XGET http://192.168.229.129:5000/v2/_catalog
```

![image-20220308143035399](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308143035399.png)

### 9. pull到本地并运行

![image-20220308143319759](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308143319759.png)

![image-20220308143415076](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308143415076.png)

# 7. Docker容器数据卷

## 坑：容器卷记得加入

Docker挂载主机目录访问如果出现cannot open directory.:Permission denied

解决办法：在挂载目录后多加一个-privileged=true参数即可

如果是CentOS7安全模块会比之前系统版本加强，不安全的会先禁止，所以目录挂载的情况被默认为不安全的行为，在SELinux.里面挂载目录被禁止掉了，如果要开启，我们一般使用-privileged=true命令，扩大容器的权限解决挂载目录没有权限的问题，也即使用该参数container内的root拥有真正的root权限，否则，container内的root只是外部的一个普通用户权限。

## 回顾下上一讲的知识点，参数V

```shell
docker run -d -p 5000:5000 -v /zhangyuye/myregistry/:/tmp/registry --privileged=true registry
```

![image-20220308144039570](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308144039570.png)

![image-20220308143838776](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308143838776.png)



## 是什么

一句话：有点类似我们Redis.里面的rdb和aof文件将docker容器内的数据保存进宿主机的磁盘中

卷就是目录或文件，存在于一个或多个容器中，由docker挂载到容器，但不属于联合文件系统，因此能够绕过Union File System提供一些用于持续存储或共享数据的特性：

卷的设计目的就是**数据的持久化**，完全独立于容器的生存周期，因此Docker不会在容器删除时删除其挂载的数据卷

运行一个带有容器卷存储功能的容器实例

```shell
docker run -it --privileged:=true -v/宿主机绝对路径目录:/容器内目录 镜像名
#目录不存在的话，docker回自动新建一个
```

## 能干嘛

将运用与运行的环境打包镜像，run后形成容器实例运行，但是我们对数据的要求希望是**持久化的**

Docker容器产生的数据，如果不备份，那么当容器实例删除后，容器内的数据自然也就没有了。为了能保存数据在docker中我们使用卷。

特点：

1:数据卷可在容器之间共享或重用数据

2:卷中的更改可以直接实时生效，爽

3:数据卷中的更改不会包含在镜像的更新中

4:数据卷的生命周期一直持续到没有容器使用它为止

## 数据卷案例

### 宿主vs容器之间映射添加容器卷

![image-20220308145732792](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308145732792.png)

#### 查看挂载情况

```shell
docker inspect 容器ID
```



![image-20220308145634661](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308145634661.png)

#### 容器和宿主机之间的数据据共享

共享：在宿主机的、docker/ubuntu/目录下新建test.txt，容器中所绑定的目录中也会有

![image-20220308150118120](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308150118120.png)

![image-20220308145834364](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308145834364.png)

docker修改，主机同步获得
主机修改，docker同步获得

docker容器stop,主机修改，docker容器重启看数捱是否同步。

![image-20220308150652515](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308150652515.png)

![image-20220308150640492](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308150640492.png)

### 映射添加说明

#### 读写：默认

```shell
docker run -it --privileged=:true -v /宿主机绝对路径目录:/容器内目录:rw 镜像名
```

默认同上案例，默认就是rw

#### 只读

容器实例内部被限制，只能读取不能写

```shell
docker run -it --privileged=:true -v /宿主机绝对路径目录:/容器内目录:ro 镜像名
```

### 卷的继承和共享

#### 容器1完成和宿主机的映射

#### 容器2继承容器1的卷规则

```shell
docker run -it --privileged=true --volumes-from 父类 --name u2 ubuntu
```

![image-20220308203524054](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308203524054.png)

# 8. Docker'常规安装简介

## 总体步骤

### 搜索镜像

https://hub.docker.com

### 拉取镜像

### 查看镜像

### 启动镜像 =========>服务端口映射

### 停止容器

### 移除容器

## 安装tomcat

### docker hub.上面查找tomcat镜像

### 从docker hub上拉取tomcat镜像到本地（之前设置过阿里云镜像加速的会下载的快一些）

### docker images查看是否有拉取到的tomcat

### 使用tomcat镜像创建容器实例（也叫运行镜像）

```shell
docker run -it -p 8080:8080 tomcat
docker run -d -p 8080:8080 tomcat
```

-p小写，主机端口:docker容器端口
-P大写，随机分配端口
-i:交互
-t:终端
-d:后台

### 访问猫首页

#### 报错

![image-20220308204756297](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308204756297.png)

#### 原因

- 可能没有映射端口或者没有关闭防火墙

- 把webapps.dist目录换成webapps

	```shell
	root@eceb4df7d798:/usr/local/tomcat# rm -rf webapps
	root@eceb4df7d798:/usr/local/tomcat# mv webapps.dist webapps
	```

	

![image-20220308205057769](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308205057769.png)

- 正常访问

	![image-20220308205306275](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308205306275.png)

### 免修改版说明

```shell
#最新版版本太高，可能会有版本不兼容，所以安装tomcat8，tomcat8不用修改，直接可以访问
docker pull billygoo/tomcat8-jdk8

docker run -d -p 8080:8080 --name mytomcat8 billygoo/tomcat8-jdk8
```

## 安装mysql

### 命令出处

![image-20220308211310233](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308211310233.png)

### 简易版

#### 使用mysql5.7镜像

```shell
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
```

![image-20220308211811007](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308211811007.png)

#### 建库建表插入数据

```mysql
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.06 sec)

mysql> create database test;
Query OK, 1 row affected (0.02 sec)

mysql> use test;
Database changed
mysql> create table employee(id int, name varchar(10));
Query OK, 0 rows affected (0.03 sec)

mysql> insert into employee(id,name) values (1,'hello'),(2,'world');
Query OK, 2 rows affected (0.02 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> select * from employee;
+------+-------+
| id   | name  |
+------+-------+
|    1 | hello |
|    2 | world |
+------+-------+
2 rows in set (0.01 sec)
```

#### 问题

插入中文数据试试一为什么报错？-----------字符集问题
删除容器后，里面的mysql数据如何办------数据卷映射

### 实战版

#### 新建mysql实例

```shell
docker run -d -p 3306:3306 --privileged=true -v /zhangyuye/mysql/log:/var/log/mysql -v /zhangyuye/mysql/data:/var/lib/mysql -v /zhangyuye/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 --name mysql5.7 mysql:5.7 
```

#### 新建my.cnf

```properties
#-v /zhangyuye/mysql/conf:/etc/mysql/conf.d
#在宿主机的指定目录下创建my.cnf
#将下面代码添加到my.cnf
[client]
default_character_set=utf8
[mysqld]
collation_server=utf8_general_ci
character_set_server=utf8
```

通过容器卷同步给mysql容器实例

#### 重新启动mysq容器实例再重新进入并查看字符编码

![image-20220308214155313](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308214155313.png)

```mysql
mysql> show variables like 'character%';
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | utf8                       |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+
8 rows in set (0.00 sec)
```

#### 结论

docker安装完MySQL并run出容器后，建议请先修改完字符集编码后再新建mysql/库-表-插数据

#### 假如将当前容器实例删除，再重新来一次，之前建的实例还有吗

## 安装redis

**<font color='red'>命令提醒：容器卷记得加入--privileged=true</font>**

### 在CentOS宿主机下新建目录/app/redis

mkdir -p /app/redis

### 将一个redis.conf文件模板拷贝进/app/redis目录下

/app/redis目录下修改redis.conf文件

默认出厂的原始redis..conf

```properties
# 版本6.2.6

# Redis configuration file example.
#
# Note that in order to read the configuration file, Redis must be
# started with the file path as first argument:
#
# ./redis-server /path/to/redis.conf

# Note on units: when memory size is needed, it is possible to specify
# it in the usual form of 1k 5GB 4M and so forth:
#
# 1k => 1000 bytes
# 1kb => 1024 bytes
# 1m => 1000000 bytes
# 1mb => 1024*1024 bytes
# 1g => 1000000000 bytes
# 1gb => 1024*1024*1024 bytes
#
# units are case insensitive so 1GB 1Gb 1gB are all the same.

################################## INCLUDES ###################################

# Include one or more other config files here.  This is useful if you
# have a standard template that goes to all Redis servers but also need
# to customize a few per-server settings.  Include files can include
# other files, so use this wisely.
#
# Note that option "include" won't be rewritten by command "CONFIG REWRITE"
# from admin or Redis Sentinel. Since Redis always uses the last processed
# line as value of a configuration directive, you'd better put includes
# at the beginning of this file to avoid overwriting config change at runtime.
#
# If instead you are interested in using includes to override configuration
# options, it is better to use include as the last line.
#
# include /path/to/local.conf
# include /path/to/other.conf

################################## MODULES #####################################

# Load modules at startup. If the server is not able to load modules
# it will abort. It is possible to use multiple loadmodule directives.
#
# loadmodule /path/to/my_module.so
# loadmodule /path/to/other_module.so

################################## NETWORK #####################################

# By default, if no "bind" configuration directive is specified, Redis listens
# for connections from all available network interfaces on the host machine.
# It is possible to listen to just one or multiple selected interfaces using
# the "bind" configuration directive, followed by one or more IP addresses.
# Each address can be prefixed by "-", which means that redis will not fail to
# start if the address is not available. Being not available only refers to
# addresses that does not correspond to any network interfece. Addresses that
# are already in use will always fail, and unsupported protocols will always BE
# silently skipped.
#
# Examples:
#
# bind 192.168.1.100 10.0.0.1     # listens on two specific IPv4 addresses
# bind 127.0.0.1 ::1              # listens on loopback IPv4 and IPv6
# bind * -::*                     # like the default, all available interfaces
#
# ~~~ WARNING ~~~ If the computer running Redis is directly exposed to the
# internet, binding to all the interfaces is dangerous and will expose the
# instance to everybody on the internet. So by default we uncomment the
# following bind directive, that will force Redis to listen only on the
# IPv4 and IPv6 (if available) loopback interface addresses (this means Redis
# will only be able to accept client connections from the same host that it is
# running on).
#
# IF YOU ARE SURE YOU WANT YOUR INSTANCE TO LISTEN TO ALL THE INTERFACES
# JUST COMMENT OUT THE FOLLOWING LINE.
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
bind 127.0.0.1 -::1

# Protected mode is a layer of security protection, in order to avoid that
# Redis instances left open on the internet are accessed and exploited.
#
# When protected mode is on and if:
#
# 1) The server is not binding explicitly to a set of addresses using the
#    "bind" directive.
# 2) No password is configured.
#
# The server only accepts connections from clients connecting from the
# IPv4 and IPv6 loopback addresses 127.0.0.1 and ::1, and from Unix domain
# sockets.
#
# By default protected mode is enabled. You should disable it only if
# you are sure you want clients from other hosts to connect to Redis
# even if no authentication is configured, nor a specific set of interfaces
# are explicitly listed using the "bind" directive.
protected-mode yes

# Accept connections on the specified port, default is 6379 (IANA #815344).
# If port 0 is specified Redis will not listen on a TCP socket.
port 6379

# TCP listen() backlog.
#
# In high requests-per-second environments you need a high backlog in order
# to avoid slow clients connection issues. Note that the Linux kernel
# will silently truncate it to the value of /proc/sys/net/core/somaxconn so
# make sure to raise both the value of somaxconn and tcp_max_syn_backlog
# in order to get the desired effect.
tcp-backlog 511

# Unix socket.
#
# Specify the path for the Unix socket that will be used to listen for
# incoming connections. There is no default, so Redis will not listen
# on a unix socket when not specified.
#
# unixsocket /run/redis.sock
# unixsocketperm 700

# Close the connection after a client is idle for N seconds (0 to disable)
timeout 0

# TCP keepalive.
#
# If non-zero, use SO_KEEPALIVE to send TCP ACKs to clients in absence
# of communication. This is useful for two reasons:
#
# 1) Detect dead peers.
# 2) Force network equipment in the middle to consider the connection to be
#    alive.
#
# On Linux, the specified value (in seconds) is the period used to send ACKs.
# Note that to close the connection the double of the time is needed.
# On other kernels the period depends on the kernel configuration.
#
# A reasonable value for this option is 300 seconds, which is the new
# Redis default starting with Redis 3.2.1.
tcp-keepalive 300

################################# TLS/SSL #####################################

# By default, TLS/SSL is disabled. To enable it, the "tls-port" configuration
# directive can be used to define TLS-listening ports. To enable TLS on the
# default port, use:
#
# port 0
# tls-port 6379

# Configure a X.509 certificate and private key to use for authenticating the
# server to connected clients, masters or cluster peers.  These files should be
# PEM formatted.
#
# tls-cert-file redis.crt 
# tls-key-file redis.key
#
# If the key file is encrypted using a passphrase, it can be included here
# as well.
#
# tls-key-file-pass secret

# Normally Redis uses the same certificate for both server functions (accepting
# connections) and client functions (replicating from a master, establishing
# cluster bus connections, etc.).
#
# Sometimes certificates are issued with attributes that designate them as
# client-only or server-only certificates. In that case it may be desired to use
# different certificates for incoming (server) and outgoing (client)
# connections. To do that, use the following directives:
#
# tls-client-cert-file client.crt
# tls-client-key-file client.key
#
# If the key file is encrypted using a passphrase, it can be included here
# as well.
#
# tls-client-key-file-pass secret

# Configure a DH parameters file to enable Diffie-Hellman (DH) key exchange:
#
# tls-dh-params-file redis.dh

# Configure a CA certificate(s) bundle or directory to authenticate TLS/SSL
# clients and peers.  Redis requires an explicit configuration of at least one
# of these, and will not implicitly use the system wide configuration.
#
# tls-ca-cert-file ca.crt
# tls-ca-cert-dir /etc/ssl/certs

# By default, clients (including replica servers) on a TLS port are required
# to authenticate using valid client side certificates.
#
# If "no" is specified, client certificates are not required and not accepted.
# If "optional" is specified, client certificates are accepted and must be
# valid if provided, but are not required.
#
# tls-auth-clients no
# tls-auth-clients optional

# By default, a Redis replica does not attempt to establish a TLS connection
# with its master.
#
# Use the following directive to enable TLS on replication links.
#
# tls-replication yes

# By default, the Redis Cluster bus uses a plain TCP connection. To enable
# TLS for the bus protocol, use the following directive:
#
# tls-cluster yes

# By default, only TLSv1.2 and TLSv1.3 are enabled and it is highly recommended
# that older formally deprecated versions are kept disabled to reduce the attack surface.
# You can explicitly specify TLS versions to support.
# Allowed values are case insensitive and include "TLSv1", "TLSv1.1", "TLSv1.2",
# "TLSv1.3" (OpenSSL >= 1.1.1) or any combination.
# To enable only TLSv1.2 and TLSv1.3, use:
#
# tls-protocols "TLSv1.2 TLSv1.3"

# Configure allowed ciphers.  See the ciphers(1ssl) manpage for more information
# about the syntax of this string.
#
# Note: this configuration applies only to <= TLSv1.2.
#
# tls-ciphers DEFAULT:!MEDIUM

# Configure allowed TLSv1.3 ciphersuites.  See the ciphers(1ssl) manpage for more
# information about the syntax of this string, and specifically for TLSv1.3
# ciphersuites.
#
# tls-ciphersuites TLS_CHACHA20_POLY1305_SHA256

# When choosing a cipher, use the server's preference instead of the client
# preference. By default, the server follows the client's preference.
#
# tls-prefer-server-ciphers yes

# By default, TLS session caching is enabled to allow faster and less expensive
# reconnections by clients that support it. Use the following directive to disable
# caching.
#
# tls-session-caching no

# Change the default number of TLS sessions cached. A zero value sets the cache
# to unlimited size. The default size is 20480.
#
# tls-session-cache-size 5000

# Change the default timeout of cached TLS sessions. The default timeout is 300
# seconds.
#
# tls-session-cache-timeout 60

################################# GENERAL #####################################

# By default Redis does not run as a daemon. Use 'yes' if you need it.
# Note that Redis will write a pid file in /var/run/redis.pid when daemonized.
# When Redis is supervised by upstart or systemd, this parameter has no impact.
daemonize no

# If you run Redis from upstart or systemd, Redis can interact with your
# supervision tree. Options:
#   supervised no      - no supervision interaction
#   supervised upstart - signal upstart by putting Redis into SIGSTOP mode
#                        requires "expect stop" in your upstart job config
#   supervised systemd - signal systemd by writing READY=1 to $NOTIFY_SOCKET
#                        on startup, and updating Redis status on a regular
#                        basis.
#   supervised auto    - detect upstart or systemd method based on
#                        UPSTART_JOB or NOTIFY_SOCKET environment variables
# Note: these supervision methods only signal "process is ready."
#       They do not enable continuous pings back to your supervisor.
#
# The default is "no". To run under upstart/systemd, you can simply uncomment
# the line below:
#
# supervised auto

# If a pid file is specified, Redis writes it where specified at startup
# and removes it at exit.
#
# When the server runs non daemonized, no pid file is created if none is
# specified in the configuration. When the server is daemonized, the pid file
# is used even if not specified, defaulting to "/var/run/redis.pid".
#
# Creating a pid file is best effort: if Redis is not able to create it
# nothing bad happens, the server will start and run normally.
#
# Note that on modern Linux systems "/run/redis.pid" is more conforming
# and should be used instead.
pidfile /var/run/redis_6379.pid

# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)
loglevel notice

# Specify the log file name. Also the empty string can be used to force
# Redis to log on the standard output. Note that if you use standard
# output for logging but daemonize, logs will be sent to /dev/null
logfile ""

# To enable logging to the system logger, just set 'syslog-enabled' to yes,
# and optionally update the other syslog parameters to suit your needs.
# syslog-enabled no

# Specify the syslog identity.
# syslog-ident redis

# Specify the syslog facility. Must be USER or between LOCAL0-LOCAL7.
# syslog-facility local0

# To disable the built in crash log, which will possibly produce cleaner core
# dumps when they are needed, uncomment the following:
#
# crash-log-enabled no

# To disable the fast memory check that's run as part of the crash log, which
# will possibly let redis terminate sooner, uncomment the following:
#
# crash-memcheck-enabled no

# Set the number of databases. The default database is DB 0, you can select
# a different one on a per-connection basis using SELECT <dbid> where
# dbid is a number between 0 and 'databases'-1
databases 16

# By default Redis shows an ASCII art logo only when started to log to the
# standard output and if the standard output is a TTY and syslog logging is
# disabled. Basically this means that normally a logo is displayed only in
# interactive sessions.
#
# However it is possible to force the pre-4.0 behavior and always show a
# ASCII art logo in startup logs by setting the following option to yes.
always-show-logo no

# By default, Redis modifies the process title (as seen in 'top' and 'ps') to
# provide some runtime information. It is possible to disable this and leave
# the process name as executed by setting the following to no.
set-proc-title yes

# When changing the process title, Redis uses the following template to construct
# the modified title.
#
# Template variables are specified in curly brackets. The following variables are
# supported:
#
# {title}           Name of process as executed if parent, or type of child process.
# {listen-addr}     Bind address or '*' followed by TCP or TLS port listening on, or
#                   Unix socket if only that's available.
# {server-mode}     Special mode, i.e. "[sentinel]" or "[cluster]".
# {port}            TCP port listening on, or 0.
# {tls-port}        TLS port listening on, or 0.
# {unixsocket}      Unix domain socket listening on, or "".
# {config-file}     Name of configuration file used.
#
proc-title-template "{title} {listen-addr} {server-mode}"

################################ SNAPSHOTTING  ################################

# Save the DB to disk.
#
# save <seconds> <changes>
#
# Redis will save the DB if both the given number of seconds and the given
# number of write operations against the DB occurred.
#
# Snapshotting can be completely disabled with a single empty string argument
# as in following example:
#
# save ""
#
# Unless specified otherwise, by default Redis will save the DB:
#   * After 3600 seconds (an hour) if at least 1 key changed
#   * After 300 seconds (5 minutes) if at least 100 keys changed
#   * After 60 seconds if at least 10000 keys changed
#
# You can set these explicitly by uncommenting the three following lines.
#
# save 3600 1
# save 300 100
# save 60 10000

# By default Redis will stop accepting writes if RDB snapshots are enabled
# (at least one save point) and the latest background save failed.
# This will make the user aware (in a hard way) that data is not persisting
# on disk properly, otherwise chances are that no one will notice and some
# disaster will happen.
#
# If the background saving process will start working again Redis will
# automatically allow writes again.
#
# However if you have setup your proper monitoring of the Redis server
# and persistence, you may want to disable this feature so that Redis will
# continue to work as usual even if there are problems with disk,
# permissions, and so forth.
stop-writes-on-bgsave-error yes

# Compress string objects using LZF when dump .rdb databases?
# By default compression is enabled as it's almost always a win.
# If you want to save some CPU in the saving child set it to 'no' but
# the dataset will likely be bigger if you have compressible values or keys.
rdbcompression yes

# Since version 5 of RDB a CRC64 checksum is placed at the end of the file.
# This makes the format more resistant to corruption but there is a performance
# hit to pay (around 10%) when saving and loading RDB files, so you can disable it
# for maximum performances.
#
# RDB files created with checksum disabled have a checksum of zero that will
# tell the loading code to skip the check.
rdbchecksum yes

# Enables or disables full sanitation checks for ziplist and listpack etc when
# loading an RDB or RESTORE payload. This reduces the chances of a assertion or
# crash later on while processing commands.
# Options:
#   no         - Never perform full sanitation
#   yes        - Always perform full sanitation
#   clients    - Perform full sanitation only for user connections.
#                Excludes: RDB files, RESTORE commands received from the master
#                connection, and client connections which have the
#                skip-sanitize-payload ACL flag.
# The default should be 'clients' but since it currently affects cluster
# resharding via MIGRATE, it is temporarily set to 'no' by default.
#
# sanitize-dump-payload no

# The filename where to dump the DB
dbfilename dump.rdb

# Remove RDB files used by replication in instances without persistence
# enabled. By default this option is disabled, however there are environments
# where for regulations or other security concerns, RDB files persisted on
# disk by masters in order to feed replicas, or stored on disk by replicas
# in order to load them for the initial synchronization, should be deleted
# ASAP. Note that this option ONLY WORKS in instances that have both AOF
# and RDB persistence disabled, otherwise is completely ignored.
#
# An alternative (and sometimes better) way to obtain the same effect is
# to use diskless replication on both master and replicas instances. However
# in the case of replicas, diskless is not always an option.
rdb-del-sync-files no

# The working directory.
#
# The DB will be written inside this directory, with the filename specified
# above using the 'dbfilename' configuration directive.
#
# The Append Only File will also be created inside this directory.
#
# Note that you must specify a directory here, not a file name.
dir ./

################################# REPLICATION #################################

# Master-Replica replication. Use replicaof to make a Redis instance a copy of
# another Redis server. A few things to understand ASAP about Redis replication.
#
#   +------------------+      +---------------+
#   |      Master      | ---> |    Replica    |
#   | (receive writes) |      |  (exact copy) |
#   +------------------+      +---------------+
#
# 1) Redis replication is asynchronous, but you can configure a master to
#    stop accepting writes if it appears to be not connected with at least
#    a given number of replicas.
# 2) Redis replicas are able to perform a partial resynchronization with the
#    master if the replication link is lost for a relatively small amount of
#    time. You may want to configure the replication backlog size (see the next
#    sections of this file) with a sensible value depending on your needs.
# 3) Replication is automatic and does not need user intervention. After a
#    network partition replicas automatically try to reconnect to masters
#    and resynchronize with them.
#
# replicaof <masterip> <masterport>

# If the master is password protected (using the "requirepass" configuration
# directive below) it is possible to tell the replica to authenticate before
# starting the replication synchronization process, otherwise the master will
# refuse the replica request.
#
# masterauth <master-password>
#
# However this is not enough if you are using Redis ACLs (for Redis version
# 6 or greater), and the default user is not capable of running the PSYNC
# command and/or other commands needed for replication. In this case it's
# better to configure a special user to use with replication, and specify the
# masteruser configuration as such:
#
# masteruser <username>
#
# When masteruser is specified, the replica will authenticate against its
# master using the new AUTH form: AUTH <username> <password>.

# When a replica loses its connection with the master, or when the replication
# is still in progress, the replica can act in two different ways:
#
# 1) if replica-serve-stale-data is set to 'yes' (the default) the replica will
#    still reply to client requests, possibly with out of date data, or the
#    data set may just be empty if this is the first synchronization.
#
# 2) If replica-serve-stale-data is set to 'no' the replica will reply with
#    an error "SYNC with master in progress" to all commands except:
#    INFO, REPLICAOF, AUTH, PING, SHUTDOWN, REPLCONF, ROLE, CONFIG, SUBSCRIBE,
#    UNSUBSCRIBE, PSUBSCRIBE, PUNSUBSCRIBE, PUBLISH, PUBSUB, COMMAND, POST,
#    HOST and LATENCY.
#
replica-serve-stale-data yes

# You can configure a replica instance to accept writes or not. Writing against
# a replica instance may be useful to store some ephemeral data (because data
# written on a replica will be easily deleted after resync with the master) but
# may also cause problems if clients are writing to it because of a
# misconfiguration.
#
# Since Redis 2.6 by default replicas are read-only.
#
# Note: read only replicas are not designed to be exposed to untrusted clients
# on the internet. It's just a protection layer against misuse of the instance.
# Still a read only replica exports by default all the administrative commands
# such as CONFIG, DEBUG, and so forth. To a limited extent you can improve
# security of read only replicas using 'rename-command' to shadow all the
# administrative / dangerous commands.
replica-read-only yes

# Replication SYNC strategy: disk or socket.
#
# New replicas and reconnecting replicas that are not able to continue the
# replication process just receiving differences, need to do what is called a
# "full synchronization". An RDB file is transmitted from the master to the
# replicas.
#
# The transmission can happen in two different ways:
#
# 1) Disk-backed: The Redis master creates a new process that writes the RDB
#                 file on disk. Later the file is transferred by the parent
#                 process to the replicas incrementally.
# 2) Diskless: The Redis master creates a new process that directly writes the
#              RDB file to replica sockets, without touching the disk at all.
#
# With disk-backed replication, while the RDB file is generated, more replicas
# can be queued and served with the RDB file as soon as the current child
# producing the RDB file finishes its work. With diskless replication instead
# once the transfer starts, new replicas arriving will be queued and a new
# transfer will start when the current one terminates.
#
# When diskless replication is used, the master waits a configurable amount of
# time (in seconds) before starting the transfer in the hope that multiple
# replicas will arrive and the transfer can be parallelized.
#
# With slow disks and fast (large bandwidth) networks, diskless replication
# works better.
repl-diskless-sync no

# When diskless replication is enabled, it is possible to configure the delay
# the server waits in order to spawn the child that transfers the RDB via socket
# to the replicas.
#
# This is important since once the transfer starts, it is not possible to serve
# new replicas arriving, that will be queued for the next RDB transfer, so the
# server waits a delay in order to let more replicas arrive.
#
# The delay is specified in seconds, and by default is 5 seconds. To disable
# it entirely just set it to 0 seconds and the transfer will start ASAP.
repl-diskless-sync-delay 5

# -----------------------------------------------------------------------------
# WARNING: RDB diskless load is experimental. Since in this setup the replica
# does not immediately store an RDB on disk, it may cause data loss during
# failovers. RDB diskless load + Redis modules not handling I/O reads may also
# cause Redis to abort in case of I/O errors during the initial synchronization
# stage with the master. Use only if you know what you are doing.
# -----------------------------------------------------------------------------
#
# Replica can load the RDB it reads from the replication link directly from the
# socket, or store the RDB to a file and read that file after it was completely
# received from the master.
#
# In many cases the disk is slower than the network, and storing and loading
# the RDB file may increase replication time (and even increase the master's
# Copy on Write memory and salve buffers).
# However, parsing the RDB file directly from the socket may mean that we have
# to flush the contents of the current database before the full rdb was
# received. For this reason we have the following options:
#
# "disabled"    - Don't use diskless load (store the rdb file to the disk first)
# "on-empty-db" - Use diskless load only when it is completely safe.
# "swapdb"      - Keep a copy of the current db contents in RAM while parsing
#                 the data directly from the socket. note that this requires
#                 sufficient memory, if you don't have it, you risk an OOM kill.
repl-diskless-load disabled

# Replicas send PINGs to server in a predefined interval. It's possible to
# change this interval with the repl_ping_replica_period option. The default
# value is 10 seconds.
#
# repl-ping-replica-period 10

# The following option sets the replication timeout for:
#
# 1) Bulk transfer I/O during SYNC, from the point of view of replica.
# 2) Master timeout from the point of view of replicas (data, pings).
# 3) Replica timeout from the point of view of masters (REPLCONF ACK pings).
#
# It is important to make sure that this value is greater than the value
# specified for repl-ping-replica-period otherwise a timeout will be detected
# every time there is low traffic between the master and the replica. The default
# value is 60 seconds.
#
# repl-timeout 60

# Disable TCP_NODELAY on the replica socket after SYNC?
#
# If you select "yes" Redis will use a smaller number of TCP packets and
# less bandwidth to send data to replicas. But this can add a delay for
# the data to appear on the replica side, up to 40 milliseconds with
# Linux kernels using a default configuration.
#
# If you select "no" the delay for data to appear on the replica side will
# be reduced but more bandwidth will be used for replication.
#
# By default we optimize for low latency, but in very high traffic conditions
# or when the master and replicas are many hops away, turning this to "yes" may
# be a good idea.
repl-disable-tcp-nodelay no

# Set the replication backlog size. The backlog is a buffer that accumulates
# replica data when replicas are disconnected for some time, so that when a
# replica wants to reconnect again, often a full resync is not needed, but a
# partial resync is enough, just passing the portion of data the replica
# missed while disconnected.
#
# The bigger the replication backlog, the longer the replica can endure the
# disconnect and later be able to perform a partial resynchronization.
#
# The backlog is only allocated if there is at least one replica connected.
#
# repl-backlog-size 1mb

# After a master has no connected replicas for some time, the backlog will be
# freed. The following option configures the amount of seconds that need to
# elapse, starting from the time the last replica disconnected, for the backlog
# buffer to be freed.
#
# Note that replicas never free the backlog for timeout, since they may be
# promoted to masters later, and should be able to correctly "partially
# resynchronize" with other replicas: hence they should always accumulate backlog.
#
# A value of 0 means to never release the backlog.
#
# repl-backlog-ttl 3600

# The replica priority is an integer number published by Redis in the INFO
# output. It is used by Redis Sentinel in order to select a replica to promote
# into a master if the master is no longer working correctly.
#
# A replica with a low priority number is considered better for promotion, so
# for instance if there are three replicas with priority 10, 100, 25 Sentinel
# will pick the one with priority 10, that is the lowest.
#
# However a special priority of 0 marks the replica as not able to perform the
# role of master, so a replica with priority of 0 will never be selected by
# Redis Sentinel for promotion.
#
# By default the priority is 100.
replica-priority 100

# -----------------------------------------------------------------------------
# By default, Redis Sentinel includes all replicas in its reports. A replica
# can be excluded from Redis Sentinel's announcements. An unannounced replica
# will be ignored by the 'sentinel replicas <master>' command and won't be
# exposed to Redis Sentinel's clients.
#
# This option does not change the behavior of replica-priority. Even with
# replica-announced set to 'no', the replica can be promoted to master. To
# prevent this behavior, set replica-priority to 0.
#
# replica-announced yes

# It is possible for a master to stop accepting writes if there are less than
# N replicas connected, having a lag less or equal than M seconds.
#
# The N replicas need to be in "online" state.
#
# The lag in seconds, that must be <= the specified value, is calculated from
# the last ping received from the replica, that is usually sent every second.
#
# This option does not GUARANTEE that N replicas will accept the write, but
# will limit the window of exposure for lost writes in case not enough replicas
# are available, to the specified number of seconds.
#
# For example to require at least 3 replicas with a lag <= 10 seconds use:
#
# min-replicas-to-write 3
# min-replicas-max-lag 10
#
# Setting one or the other to 0 disables the feature.
#
# By default min-replicas-to-write is set to 0 (feature disabled) and
# min-replicas-max-lag is set to 10.

# A Redis master is able to list the address and port of the attached
# replicas in different ways. For example the "INFO replication" section
# offers this information, which is used, among other tools, by
# Redis Sentinel in order to discover replica instances.
# Another place where this info is available is in the output of the
# "ROLE" command of a master.
#
# The listed IP address and port normally reported by a replica is
# obtained in the following way:
#
#   IP: The address is auto detected by checking the peer address
#   of the socket used by the replica to connect with the master.
#
#   Port: The port is communicated by the replica during the replication
#   handshake, and is normally the port that the replica is using to
#   listen for connections.
#
# However when port forwarding or Network Address Translation (NAT) is
# used, the replica may actually be reachable via different IP and port
# pairs. The following two options can be used by a replica in order to
# report to its master a specific set of IP and port, so that both INFO
# and ROLE will report those values.
#
# There is no need to use both the options if you need to override just
# the port or the IP address.
#
# replica-announce-ip 5.5.5.5
# replica-announce-port 1234

############################### KEYS TRACKING #################################

# Redis implements server assisted support for client side caching of values.
# This is implemented using an invalidation table that remembers, using
# a radix key indexed by key name, what clients have which keys. In turn
# this is used in order to send invalidation messages to clients. Please
# check this page to understand more about the feature:
#
#   https://redis.io/topics/client-side-caching
#
# When tracking is enabled for a client, all the read only queries are assumed
# to be cached: this will force Redis to store information in the invalidation
# table. When keys are modified, such information is flushed away, and
# invalidation messages are sent to the clients. However if the workload is
# heavily dominated by reads, Redis could use more and more memory in order
# to track the keys fetched by many clients.
#
# For this reason it is possible to configure a maximum fill value for the
# invalidation table. By default it is set to 1M of keys, and once this limit
# is reached, Redis will start to evict keys in the invalidation table
# even if they were not modified, just to reclaim memory: this will in turn
# force the clients to invalidate the cached values. Basically the table
# maximum size is a trade off between the memory you want to spend server
# side to track information about who cached what, and the ability of clients
# to retain cached objects in memory.
#
# If you set the value to 0, it means there are no limits, and Redis will
# retain as many keys as needed in the invalidation table.
# In the "stats" INFO section, you can find information about the number of
# keys in the invalidation table at every given moment.
#
# Note: when key tracking is used in broadcasting mode, no memory is used
# in the server side so this setting is useless.
#
# tracking-table-max-keys 1000000

################################## SECURITY ###################################

# Warning: since Redis is pretty fast, an outside user can try up to
# 1 million passwords per second against a modern box. This means that you
# should use very strong passwords, otherwise they will be very easy to break.
# Note that because the password is really a shared secret between the client
# and the server, and should not be memorized by any human, the password
# can be easily a long string from /dev/urandom or whatever, so by using a
# long and unguessable password no brute force attack will be possible.

# Redis ACL users are defined in the following format:
#
#   user <username> ... acl rules ...
#
# For example:
#
#   user worker +@list +@connection ~jobs:* on >ffa9203c493aa99
#
# The special username "default" is used for new connections. If this user
# has the "nopass" rule, then new connections will be immediately authenticated
# as the "default" user without the need of any password provided via the
# AUTH command. Otherwise if the "default" user is not flagged with "nopass"
# the connections will start in not authenticated state, and will require
# AUTH (or the HELLO command AUTH option) in order to be authenticated and
# start to work.
#
# The ACL rules that describe what a user can do are the following:
#
#  on           Enable the user: it is possible to authenticate as this user.
#  off          Disable the user: it's no longer possible to authenticate
#               with this user, however the already authenticated connections
#               will still work.
#  skip-sanitize-payload    RESTORE dump-payload sanitation is skipped.
#  sanitize-payload         RESTORE dump-payload is sanitized (default).
#  +<command>   Allow the execution of that command
#  -<command>   Disallow the execution of that command
#  +@<category> Allow the execution of all the commands in such category
#               with valid categories are like @admin, @set, @sortedset, ...
#               and so forth, see the full list in the server.c file where
#               the Redis command table is described and defined.
#               The special category @all means all the commands, but currently
#               present in the server, and that will be loaded in the future
#               via modules.
#  +<command>|subcommand    Allow a specific subcommand of an otherwise
#                           disabled command. Note that this form is not
#                           allowed as negative like -DEBUG|SEGFAULT, but
#                           only additive starting with "+".
#  allcommands  Alias for +@all. Note that it implies the ability to execute
#               all the future commands loaded via the modules system.
#  nocommands   Alias for -@all.
#  ~<pattern>   Add a pattern of keys that can be mentioned as part of
#               commands. For instance ~* allows all the keys. The pattern
#               is a glob-style pattern like the one of KEYS.
#               It is possible to specify multiple patterns.
#  allkeys      Alias for ~*
#  resetkeys    Flush the list of allowed keys patterns.
#  &<pattern>   Add a glob-style pattern of Pub/Sub channels that can be
#               accessed by the user. It is possible to specify multiple channel
#               patterns.
#  allchannels  Alias for &*
#  resetchannels            Flush the list of allowed channel patterns.
#  ><password>  Add this password to the list of valid password for the user.
#               For example >mypass will add "mypass" to the list.
#               This directive clears the "nopass" flag (see later).
#  <<password>  Remove this password from the list of valid passwords.
#  nopass       All the set passwords of the user are removed, and the user
#               is flagged as requiring no password: it means that every
#               password will work against this user. If this directive is
#               used for the default user, every new connection will be
#               immediately authenticated with the default user without
#               any explicit AUTH command required. Note that the "resetpass"
#               directive will clear this condition.
#  resetpass    Flush the list of allowed passwords. Moreover removes the
#               "nopass" status. After "resetpass" the user has no associated
#               passwords and there is no way to authenticate without adding
#               some password (or setting it as "nopass" later).
#  reset        Performs the following actions: resetpass, resetkeys, off,
#               -@all. The user returns to the same state it has immediately
#               after its creation.
#
# ACL rules can be specified in any order: for instance you can start with
# passwords, then flags, or key patterns. However note that the additive
# and subtractive rules will CHANGE MEANING depending on the ordering.
# For instance see the following example:
#
#   user alice on +@all -DEBUG ~* >somepassword
#
# This will allow "alice" to use all the commands with the exception of the
# DEBUG command, since +@all added all the commands to the set of the commands
# alice can use, and later DEBUG was removed. However if we invert the order
# of two ACL rules the result will be different:
#
#   user alice on -DEBUG +@all ~* >somepassword
#
# Now DEBUG was removed when alice had yet no commands in the set of allowed
# commands, later all the commands are added, so the user will be able to
# execute everything.
#
# Basically ACL rules are processed left-to-right.
#
# For more information about ACL configuration please refer to
# the Redis web site at https://redis.io/topics/acl

# ACL LOG
#
# The ACL Log tracks failed commands and authentication events associated
# with ACLs. The ACL Log is useful to troubleshoot failed commands blocked 
# by ACLs. The ACL Log is stored in memory. You can reclaim memory with 
# ACL LOG RESET. Define the maximum entry length of the ACL Log below.
acllog-max-len 128

# Using an external ACL file
#
# Instead of configuring users here in this file, it is possible to use
# a stand-alone file just listing users. The two methods cannot be mixed:
# if you configure users here and at the same time you activate the external
# ACL file, the server will refuse to start.
#
# The format of the external ACL user file is exactly the same as the
# format that is used inside redis.conf to describe users.
#
# aclfile /etc/redis/users.acl

# IMPORTANT NOTE: starting with Redis 6 "requirepass" is just a compatibility
# layer on top of the new ACL system. The option effect will be just setting
# the password for the default user. Clients will still authenticate using
# AUTH <password> as usually, or more explicitly with AUTH default <password>
# if they follow the new protocol: both will work.
#
# The requirepass is not compatable with aclfile option and the ACL LOAD
# command, these will cause requirepass to be ignored.
#
# requirepass foobared

# New users are initialized with restrictive permissions by default, via the
# equivalent of this ACL rule 'off resetkeys -@all'. Starting with Redis 6.2, it
# is possible to manage access to Pub/Sub channels with ACL rules as well. The
# default Pub/Sub channels permission if new users is controlled by the 
# acl-pubsub-default configuration directive, which accepts one of these values:
#
# allchannels: grants access to all Pub/Sub channels
# resetchannels: revokes access to all Pub/Sub channels
#
# To ensure backward compatibility while upgrading Redis 6.0, acl-pubsub-default
# defaults to the 'allchannels' permission.
#
# Future compatibility note: it is very likely that in a future version of Redis
# the directive's default of 'allchannels' will be changed to 'resetchannels' in
# order to provide better out-of-the-box Pub/Sub security. Therefore, it is
# recommended that you explicitly define Pub/Sub permissions for all users
# rather then rely on implicit default values. Once you've set explicit
# Pub/Sub for all existing users, you should uncomment the following line.
#
# acl-pubsub-default resetchannels

# Command renaming (DEPRECATED).
#
# ------------------------------------------------------------------------
# WARNING: avoid using this option if possible. Instead use ACLs to remove
# commands from the default user, and put them only in some admin user you
# create for administrative purposes.
# ------------------------------------------------------------------------
#
# It is possible to change the name of dangerous commands in a shared
# environment. For instance the CONFIG command may be renamed into something
# hard to guess so that it will still be available for internal-use tools
# but not available for general clients.
#
# Example:
#
# rename-command CONFIG b840fc02d524045429941cc15f59e41cb7be6c52
#
# It is also possible to completely kill a command by renaming it into
# an empty string:
#
# rename-command CONFIG ""
#
# Please note that changing the name of commands that are logged into the
# AOF file or transmitted to replicas may cause problems.

################################### CLIENTS ####################################

# Set the max number of connected clients at the same time. By default
# this limit is set to 10000 clients, however if the Redis server is not
# able to configure the process file limit to allow for the specified limit
# the max number of allowed clients is set to the current file limit
# minus 32 (as Redis reserves a few file descriptors for internal uses).
#
# Once the limit is reached Redis will close all the new connections sending
# an error 'max number of clients reached'.
#
# IMPORTANT: When Redis Cluster is used, the max number of connections is also
# shared with the cluster bus: every node in the cluster will use two
# connections, one incoming and another outgoing. It is important to size the
# limit accordingly in case of very large clusters.
#
# maxclients 10000

############################## MEMORY MANAGEMENT ################################

# Set a memory usage limit to the specified amount of bytes.
# When the memory limit is reached Redis will try to remove keys
# according to the eviction policy selected (see maxmemory-policy).
#
# If Redis can't remove keys according to the policy, or if the policy is
# set to 'noeviction', Redis will start to reply with errors to commands
# that would use more memory, like SET, LPUSH, and so on, and will continue
# to reply to read-only commands like GET.
#
# This option is usually useful when using Redis as an LRU or LFU cache, or to
# set a hard memory limit for an instance (using the 'noeviction' policy).
#
# WARNING: If you have replicas attached to an instance with maxmemory on,
# the size of the output buffers needed to feed the replicas are subtracted
# from the used memory count, so that network problems / resyncs will
# not trigger a loop where keys are evicted, and in turn the output
# buffer of replicas is full with DELs of keys evicted triggering the deletion
# of more keys, and so forth until the database is completely emptied.
#
# In short... if you have replicas attached it is suggested that you set a lower
# limit for maxmemory so that there is some free RAM on the system for replica
# output buffers (but this is not needed if the policy is 'noeviction').
#
# maxmemory <bytes>

# MAXMEMORY POLICY: how Redis will select what to remove when maxmemory
# is reached. You can select one from the following behaviors:
#
# volatile-lru -> Evict using approximated LRU, only keys with an expire set.
# allkeys-lru -> Evict any key using approximated LRU.
# volatile-lfu -> Evict using approximated LFU, only keys with an expire set.
# allkeys-lfu -> Evict any key using approximated LFU.
# volatile-random -> Remove a random key having an expire set.
# allkeys-random -> Remove a random key, any key.
# volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
# noeviction -> Don't evict anything, just return an error on write operations.
#
# LRU means Least Recently Used
# LFU means Least Frequently Used
#
# Both LRU, LFU and volatile-ttl are implemented using approximated
# randomized algorithms.
#
# Note: with any of the above policies, when there are no suitable keys for
# eviction, Redis will return an error on write operations that require
# more memory. These are usually commands that create new keys, add data or
# modify existing keys. A few examples are: SET, INCR, HSET, LPUSH, SUNIONSTORE,
# SORT (due to the STORE argument), and EXEC (if the transaction includes any
# command that requires memory).
#
# The default is:
#
# maxmemory-policy noeviction

# LRU, LFU and minimal TTL algorithms are not precise algorithms but approximated
# algorithms (in order to save memory), so you can tune it for speed or
# accuracy. By default Redis will check five keys and pick the one that was
# used least recently, you can change the sample size using the following
# configuration directive.
#
# The default of 5 produces good enough results. 10 Approximates very closely
# true LRU but costs more CPU. 3 is faster but not very accurate.
#
# maxmemory-samples 5

# Eviction processing is designed to function well with the default setting.
# If there is an unusually large amount of write traffic, this value may need to
# be increased.  Decreasing this value may reduce latency at the risk of 
# eviction processing effectiveness
#   0 = minimum latency, 10 = default, 100 = process without regard to latency
#
# maxmemory-eviction-tenacity 10

# Starting from Redis 5, by default a replica will ignore its maxmemory setting
# (unless it is promoted to master after a failover or manually). It means
# that the eviction of keys will be just handled by the master, sending the
# DEL commands to the replica as keys evict in the master side.
#
# This behavior ensures that masters and replicas stay consistent, and is usually
# what you want, however if your replica is writable, or you want the replica
# to have a different memory setting, and you are sure all the writes performed
# to the replica are idempotent, then you may change this default (but be sure
# to understand what you are doing).
#
# Note that since the replica by default does not evict, it may end using more
# memory than the one set via maxmemory (there are certain buffers that may
# be larger on the replica, or data structures may sometimes take more memory
# and so forth). So make sure you monitor your replicas and make sure they
# have enough memory to never hit a real out-of-memory condition before the
# master hits the configured maxmemory setting.
#
# replica-ignore-maxmemory yes

# Redis reclaims expired keys in two ways: upon access when those keys are
# found to be expired, and also in background, in what is called the
# "active expire key". The key space is slowly and interactively scanned
# looking for expired keys to reclaim, so that it is possible to free memory
# of keys that are expired and will never be accessed again in a short time.
#
# The default effort of the expire cycle will try to avoid having more than
# ten percent of expired keys still in memory, and will try to avoid consuming
# more than 25% of total memory and to add latency to the system. However
# it is possible to increase the expire "effort" that is normally set to
# "1", to a greater value, up to the value "10". At its maximum value the
# system will use more CPU, longer cycles (and technically may introduce
# more latency), and will tolerate less already expired keys still present
# in the system. It's a tradeoff between memory, CPU and latency.
#
# active-expire-effort 1

############################# LAZY FREEING ####################################

# Redis has two primitives to delete keys. One is called DEL and is a blocking
# deletion of the object. It means that the server stops processing new commands
# in order to reclaim all the memory associated with an object in a synchronous
# way. If the key deleted is associated with a small object, the time needed
# in order to execute the DEL command is very small and comparable to most other
# O(1) or O(log_N) commands in Redis. However if the key is associated with an
# aggregated value containing millions of elements, the server can block for
# a long time (even seconds) in order to complete the operation.
#
# For the above reasons Redis also offers non blocking deletion primitives
# such as UNLINK (non blocking DEL) and the ASYNC option of FLUSHALL and
# FLUSHDB commands, in order to reclaim memory in background. Those commands
# are executed in constant time. Another thread will incrementally free the
# object in the background as fast as possible.
#
# DEL, UNLINK and ASYNC option of FLUSHALL and FLUSHDB are user-controlled.
# It's up to the design of the application to understand when it is a good
# idea to use one or the other. However the Redis server sometimes has to
# delete keys or flush the whole database as a side effect of other operations.
# Specifically Redis deletes objects independently of a user call in the
# following scenarios:
#
# 1) On eviction, because of the maxmemory and maxmemory policy configurations,
#    in order to make room for new data, without going over the specified
#    memory limit.
# 2) Because of expire: when a key with an associated time to live (see the
#    EXPIRE command) must be deleted from memory.
# 3) Because of a side effect of a command that stores data on a key that may
#    already exist. For example the RENAME command may delete the old key
#    content when it is replaced with another one. Similarly SUNIONSTORE
#    or SORT with STORE option may delete existing keys. The SET command
#    itself removes any old content of the specified key in order to replace
#    it with the specified string.
# 4) During replication, when a replica performs a full resynchronization with
#    its master, the content of the whole database is removed in order to
#    load the RDB file just transferred.
#
# In all the above cases the default is to delete objects in a blocking way,
# like if DEL was called. However you can configure each case specifically
# in order to instead release memory in a non-blocking way like if UNLINK
# was called, using the following configuration directives.

lazyfree-lazy-eviction no
lazyfree-lazy-expire no
lazyfree-lazy-server-del no
replica-lazy-flush no

# It is also possible, for the case when to replace the user code DEL calls
# with UNLINK calls is not easy, to modify the default behavior of the DEL
# command to act exactly like UNLINK, using the following configuration
# directive:

lazyfree-lazy-user-del no

# FLUSHDB, FLUSHALL, and SCRIPT FLUSH support both asynchronous and synchronous
# deletion, which can be controlled by passing the [SYNC|ASYNC] flags into the
# commands. When neither flag is passed, this directive will be used to determine
# if the data should be deleted asynchronously.

lazyfree-lazy-user-flush no

################################ THREADED I/O #################################

# Redis is mostly single threaded, however there are certain threaded
# operations such as UNLINK, slow I/O accesses and other things that are
# performed on side threads.
#
# Now it is also possible to handle Redis clients socket reads and writes
# in different I/O threads. Since especially writing is so slow, normally
# Redis users use pipelining in order to speed up the Redis performances per
# core, and spawn multiple instances in order to scale more. Using I/O
# threads it is possible to easily speedup two times Redis without resorting
# to pipelining nor sharding of the instance.
#
# By default threading is disabled, we suggest enabling it only in machines
# that have at least 4 or more cores, leaving at least one spare core.
# Using more than 8 threads is unlikely to help much. We also recommend using
# threaded I/O only if you actually have performance problems, with Redis
# instances being able to use a quite big percentage of CPU time, otherwise
# there is no point in using this feature.
#
# So for instance if you have a four cores boxes, try to use 2 or 3 I/O
# threads, if you have a 8 cores, try to use 6 threads. In order to
# enable I/O threads use the following configuration directive:
#
# io-threads 4
#
# Setting io-threads to 1 will just use the main thread as usual.
# When I/O threads are enabled, we only use threads for writes, that is
# to thread the write(2) syscall and transfer the client buffers to the
# socket. However it is also possible to enable threading of reads and
# protocol parsing using the following configuration directive, by setting
# it to yes:
#
# io-threads-do-reads no
#
# Usually threading reads doesn't help much.
#
# NOTE 1: This configuration directive cannot be changed at runtime via
# CONFIG SET. Aso this feature currently does not work when SSL is
# enabled.
#
# NOTE 2: If you want to test the Redis speedup using redis-benchmark, make
# sure you also run the benchmark itself in threaded mode, using the
# --threads option to match the number of Redis threads, otherwise you'll not
# be able to notice the improvements.

############################ KERNEL OOM CONTROL ##############################

# On Linux, it is possible to hint the kernel OOM killer on what processes
# should be killed first when out of memory.
#
# Enabling this feature makes Redis actively control the oom_score_adj value
# for all its processes, depending on their role. The default scores will
# attempt to have background child processes killed before all others, and
# replicas killed before masters.
#
# Redis supports three options:
#
# no:       Don't make changes to oom-score-adj (default).
# yes:      Alias to "relative" see below.
# absolute: Values in oom-score-adj-values are written as is to the kernel.
# relative: Values are used relative to the initial value of oom_score_adj when
#           the server starts and are then clamped to a range of -1000 to 1000.
#           Because typically the initial value is 0, they will often match the
#           absolute values.
oom-score-adj no

# When oom-score-adj is used, this directive controls the specific values used
# for master, replica and background child processes. Values range -2000 to
# 2000 (higher means more likely to be killed).
#
# Unprivileged processes (not root, and without CAP_SYS_RESOURCE capabilities)
# can freely increase their value, but not decrease it below its initial
# settings. This means that setting oom-score-adj to "relative" and setting the
# oom-score-adj-values to positive values will always succeed.
oom-score-adj-values 0 200 800


#################### KERNEL transparent hugepage CONTROL ######################

# Usually the kernel Transparent Huge Pages control is set to "madvise" or
# or "never" by default (/sys/kernel/mm/transparent_hugepage/enabled), in which
# case this config has no effect. On systems in which it is set to "always",
# redis will attempt to disable it specifically for the redis process in order
# to avoid latency problems specifically with fork(2) and CoW.
# If for some reason you prefer to keep it enabled, you can set this config to
# "no" and the kernel global to "always".

disable-thp yes

############################## APPEND ONLY MODE ###############################

# By default Redis asynchronously dumps the dataset on disk. This mode is
# good enough in many applications, but an issue with the Redis process or
# a power outage may result into a few minutes of writes lost (depending on
# the configured save points).
#
# The Append Only File is an alternative persistence mode that provides
# much better durability. For instance using the default data fsync policy
# (see later in the config file) Redis can lose just one second of writes in a
# dramatic event like a server power outage, or a single write if something
# wrong with the Redis process itself happens, but the operating system is
# still running correctly.
#
# AOF and RDB persistence can be enabled at the same time without problems.
# If the AOF is enabled on startup Redis will load the AOF, that is the file
# with the better durability guarantees.
#
# Please check https://redis.io/topics/persistence for more information.

appendonly no

# The name of the append only file (default: "appendonly.aof")

appendfilename "appendonly.aof"

# The fsync() call tells the Operating System to actually write data on disk
# instead of waiting for more data in the output buffer. Some OS will really flush
# data on disk, some other OS will just try to do it ASAP.
#
# Redis supports three different modes:
#
# no: don't fsync, just let the OS flush the data when it wants. Faster.
# always: fsync after every write to the append only log. Slow, Safest.
# everysec: fsync only one time every second. Compromise.
#
# The default is "everysec", as that's usually the right compromise between
# speed and data safety. It's up to you to understand if you can relax this to
# "no" that will let the operating system flush the output buffer when
# it wants, for better performances (but if you can live with the idea of
# some data loss consider the default persistence mode that's snapshotting),
# or on the contrary, use "always" that's very slow but a bit safer than
# everysec.
#
# More details please check the following article:
# http://antirez.com/post/redis-persistence-demystified.html
#
# If unsure, use "everysec".

# appendfsync always
appendfsync everysec
# appendfsync no

# When the AOF fsync policy is set to always or everysec, and a background
# saving process (a background save or AOF log background rewriting) is
# performing a lot of I/O against the disk, in some Linux configurations
# Redis may block too long on the fsync() call. Note that there is no fix for
# this currently, as even performing fsync in a different thread will block
# our synchronous write(2) call.
#
# In order to mitigate this problem it's possible to use the following option
# that will prevent fsync() from being called in the main process while a
# BGSAVE or BGREWRITEAOF is in progress.
#
# This means that while another child is saving, the durability of Redis is
# the same as "appendfsync none". In practical terms, this means that it is
# possible to lose up to 30 seconds of log in the worst scenario (with the
# default Linux settings).
#
# If you have latency problems turn this to "yes". Otherwise leave it as
# "no" that is the safest pick from the point of view of durability.

no-appendfsync-on-rewrite no

# Automatic rewrite of the append only file.
# Redis is able to automatically rewrite the log file implicitly calling
# BGREWRITEAOF when the AOF log size grows by the specified percentage.
#
# This is how it works: Redis remembers the size of the AOF file after the
# latest rewrite (if no rewrite has happened since the restart, the size of
# the AOF at startup is used).
#
# This base size is compared to the current size. If the current size is
# bigger than the specified percentage, the rewrite is triggered. Also
# you need to specify a minimal size for the AOF file to be rewritten, this
# is useful to avoid rewriting the AOF file even if the percentage increase
# is reached but it is still pretty small.
#
# Specify a percentage of zero in order to disable the automatic AOF
# rewrite feature.

auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb

# An AOF file may be found to be truncated at the end during the Redis
# startup process, when the AOF data gets loaded back into memory.
# This may happen when the system where Redis is running
# crashes, especially when an ext4 filesystem is mounted without the
# data=ordered option (however this can't happen when Redis itself
# crashes or aborts but the operating system still works correctly).
#
# Redis can either exit with an error when this happens, or load as much
# data as possible (the default now) and start if the AOF file is found
# to be truncated at the end. The following option controls this behavior.
#
# If aof-load-truncated is set to yes, a truncated AOF file is loaded and
# the Redis server starts emitting a log to inform the user of the event.
# Otherwise if the option is set to no, the server aborts with an error
# and refuses to start. When the option is set to no, the user requires
# to fix the AOF file using the "redis-check-aof" utility before to restart
# the server.
#
# Note that if the AOF file will be found to be corrupted in the middle
# the server will still exit with an error. This option only applies when
# Redis will try to read more data from the AOF file but not enough bytes
# will be found.
aof-load-truncated yes

# When rewriting the AOF file, Redis is able to use an RDB preamble in the
# AOF file for faster rewrites and recoveries. When this option is turned
# on the rewritten AOF file is composed of two different stanzas:
#
#   [RDB file][AOF tail]
#
# When loading, Redis recognizes that the AOF file starts with the "REDIS"
# string and loads the prefixed RDB file, then continues loading the AOF
# tail.
aof-use-rdb-preamble yes

################################ LUA SCRIPTING  ###############################

# Max execution time of a Lua script in milliseconds.
#
# If the maximum execution time is reached Redis will log that a script is
# still in execution after the maximum allowed time and will start to
# reply to queries with an error.
#
# When a long running script exceeds the maximum execution time only the
# SCRIPT KILL and SHUTDOWN NOSAVE commands are available. The first can be
# used to stop a script that did not yet call any write commands. The second
# is the only way to shut down the server in the case a write command was
# already issued by the script but the user doesn't want to wait for the natural
# termination of the script.
#
# Set it to 0 or a negative value for unlimited execution without warnings.
lua-time-limit 5000

################################ REDIS CLUSTER  ###############################

# Normal Redis instances can't be part of a Redis Cluster; only nodes that are
# started as cluster nodes can. In order to start a Redis instance as a
# cluster node enable the cluster support uncommenting the following:
#
# cluster-enabled yes

# Every cluster node has a cluster configuration file. This file is not
# intended to be edited by hand. It is created and updated by Redis nodes.
# Every Redis Cluster node requires a different cluster configuration file.
# Make sure that instances running in the same system do not have
# overlapping cluster configuration file names.
#
# cluster-config-file nodes-6379.conf

# Cluster node timeout is the amount of milliseconds a node must be unreachable
# for it to be considered in failure state.
# Most other internal time limits are a multiple of the node timeout.
#
# cluster-node-timeout 15000

# A replica of a failing master will avoid to start a failover if its data
# looks too old.
#
# There is no simple way for a replica to actually have an exact measure of
# its "data age", so the following two checks are performed:
#
# 1) If there are multiple replicas able to failover, they exchange messages
#    in order to try to give an advantage to the replica with the best
#    replication offset (more data from the master processed).
#    Replicas will try to get their rank by offset, and apply to the start
#    of the failover a delay proportional to their rank.
#
# 2) Every single replica computes the time of the last interaction with
#    its master. This can be the last ping or command received (if the master
#    is still in the "connected" state), or the time that elapsed since the
#    disconnection with the master (if the replication link is currently down).
#    If the last interaction is too old, the replica will not try to failover
#    at all.
#
# The point "2" can be tuned by user. Specifically a replica will not perform
# the failover if, since the last interaction with the master, the time
# elapsed is greater than:
#
#   (node-timeout * cluster-replica-validity-factor) + repl-ping-replica-period
#
# So for example if node-timeout is 30 seconds, and the cluster-replica-validity-factor
# is 10, and assuming a default repl-ping-replica-period of 10 seconds, the
# replica will not try to failover if it was not able to talk with the master
# for longer than 310 seconds.
#
# A large cluster-replica-validity-factor may allow replicas with too old data to failover
# a master, while a too small value may prevent the cluster from being able to
# elect a replica at all.
#
# For maximum availability, it is possible to set the cluster-replica-validity-factor
# to a value of 0, which means, that replicas will always try to failover the
# master regardless of the last time they interacted with the master.
# (However they'll always try to apply a delay proportional to their
# offset rank).
#
# Zero is the only value able to guarantee that when all the partitions heal
# the cluster will always be able to continue.
#
# cluster-replica-validity-factor 10

# Cluster replicas are able to migrate to orphaned masters, that are masters
# that are left without working replicas. This improves the cluster ability
# to resist to failures as otherwise an orphaned master can't be failed over
# in case of failure if it has no working replicas.
#
# Replicas migrate to orphaned masters only if there are still at least a
# given number of other working replicas for their old master. This number
# is the "migration barrier". A migration barrier of 1 means that a replica
# will migrate only if there is at least 1 other working replica for its master
# and so forth. It usually reflects the number of replicas you want for every
# master in your cluster.
#
# Default is 1 (replicas migrate only if their masters remain with at least
# one replica). To disable migration just set it to a very large value or
# set cluster-allow-replica-migration to 'no'.
# A value of 0 can be set but is useful only for debugging and dangerous
# in production.
#
# cluster-migration-barrier 1

# Turning off this option allows to use less automatic cluster configuration.
# It both disables migration to orphaned masters and migration from masters
# that became empty.
#
# Default is 'yes' (allow automatic migrations).
#
# cluster-allow-replica-migration yes

# By default Redis Cluster nodes stop accepting queries if they detect there
# is at least a hash slot uncovered (no available node is serving it).
# This way if the cluster is partially down (for example a range of hash slots
# are no longer covered) all the cluster becomes, eventually, unavailable.
# It automatically returns available as soon as all the slots are covered again.
#
# However sometimes you want the subset of the cluster which is working,
# to continue to accept queries for the part of the key space that is still
# covered. In order to do so, just set the cluster-require-full-coverage
# option to no.
#
# cluster-require-full-coverage yes

# This option, when set to yes, prevents replicas from trying to failover its
# master during master failures. However the replica can still perform a
# manual failover, if forced to do so.
#
# This is useful in different scenarios, especially in the case of multiple
# data center operations, where we want one side to never be promoted if not
# in the case of a total DC failure.
#
# cluster-replica-no-failover no

# This option, when set to yes, allows nodes to serve read traffic while the
# the cluster is in a down state, as long as it believes it owns the slots. 
#
# This is useful for two cases.  The first case is for when an application 
# doesn't require consistency of data during node failures or network partitions.
# One example of this is a cache, where as long as the node has the data it
# should be able to serve it. 
#
# The second use case is for configurations that don't meet the recommended  
# three shards but want to enable cluster mode and scale later. A 
# master outage in a 1 or 2 shard configuration causes a read/write outage to the
# entire cluster without this option set, with it set there is only a write outage.
# Without a quorum of masters, slot ownership will not change automatically. 
#
# cluster-allow-reads-when-down no

# In order to setup your cluster make sure to read the documentation
# available at https://redis.io web site.

########################## CLUSTER DOCKER/NAT support  ########################

# In certain deployments, Redis Cluster nodes address discovery fails, because
# addresses are NAT-ted or because ports are forwarded (the typical case is
# Docker and other containers).
#
# In order to make Redis Cluster working in such environments, a static
# configuration where each node knows its public address is needed. The
# following four options are used for this scope, and are:
#
# * cluster-announce-ip
# * cluster-announce-port
# * cluster-announce-tls-port
# * cluster-announce-bus-port
#
# Each instructs the node about its address, client ports (for connections
# without and with TLS) and cluster message bus port. The information is then
# published in the header of the bus packets so that other nodes will be able to
# correctly map the address of the node publishing the information.
#
# If cluster-tls is set to yes and cluster-announce-tls-port is omitted or set
# to zero, then cluster-announce-port refers to the TLS port. Note also that
# cluster-announce-tls-port has no effect if cluster-tls is set to no.
#
# If the above options are not used, the normal Redis Cluster auto-detection
# will be used instead.
#
# Note that when remapped, the bus port may not be at the fixed offset of
# clients port + 10000, so you can specify any port and bus-port depending
# on how they get remapped. If the bus-port is not set, a fixed offset of
# 10000 will be used as usual.
#
# Example:
#
# cluster-announce-ip 10.1.1.5
# cluster-announce-tls-port 6379
# cluster-announce-port 0
# cluster-announce-bus-port 6380

################################## SLOW LOG ###################################

# The Redis Slow Log is a system to log queries that exceeded a specified
# execution time. The execution time does not include the I/O operations
# like talking with the client, sending the reply and so forth,
# but just the time needed to actually execute the command (this is the only
# stage of command execution where the thread is blocked and can not serve
# other requests in the meantime).
#
# You can configure the slow log with two parameters: one tells Redis
# what is the execution time, in microseconds, to exceed in order for the
# command to get logged, and the other parameter is the length of the
# slow log. When a new command is logged the oldest one is removed from the
# queue of logged commands.

# The following time is expressed in microseconds, so 1000000 is equivalent
# to one second. Note that a negative number disables the slow log, while
# a value of zero forces the logging of every command.
slowlog-log-slower-than 10000

# There is no limit to this length. Just be aware that it will consume memory.
# You can reclaim memory used by the slow log with SLOWLOG RESET.
slowlog-max-len 128

################################ LATENCY MONITOR ##############################

# The Redis latency monitoring subsystem samples different operations
# at runtime in order to collect data related to possible sources of
# latency of a Redis instance.
#
# Via the LATENCY command this information is available to the user that can
# print graphs and obtain reports.
#
# The system only logs operations that were performed in a time equal or
# greater than the amount of milliseconds specified via the
# latency-monitor-threshold configuration directive. When its value is set
# to zero, the latency monitor is turned off.
#
# By default latency monitoring is disabled since it is mostly not needed
# if you don't have latency issues, and collecting data has a performance
# impact, that while very small, can be measured under big load. Latency
# monitoring can easily be enabled at runtime using the command
# "CONFIG SET latency-monitor-threshold <milliseconds>" if needed.
latency-monitor-threshold 0

############################# EVENT NOTIFICATION ##############################

# Redis can notify Pub/Sub clients about events happening in the key space.
# This feature is documented at https://redis.io/topics/notifications
#
# For instance if keyspace events notification is enabled, and a client
# performs a DEL operation on key "foo" stored in the Database 0, two
# messages will be published via Pub/Sub:
#
# PUBLISH __keyspace@0__:foo del
# PUBLISH __keyevent@0__:del foo
#
# It is possible to select the events that Redis will notify among a set
# of classes. Every class is identified by a single character:
#
#  K     Keyspace events, published with __keyspace@<db>__ prefix.
#  E     Keyevent events, published with __keyevent@<db>__ prefix.
#  g     Generic commands (non-type specific) like DEL, EXPIRE, RENAME, ...
#  $     String commands
#  l     List commands
#  s     Set commands
#  h     Hash commands
#  z     Sorted set commands
#  x     Expired events (events generated every time a key expires)
#  e     Evicted events (events generated when a key is evicted for maxmemory)
#  t     Stream commands
#  d     Module key type events
#  m     Key-miss events (Note: It is not included in the 'A' class)
#  A     Alias for g$lshzxetd, so that the "AKE" string means all the events
#        (Except key-miss events which are excluded from 'A' due to their
#         unique nature).
#
#  The "notify-keyspace-events" takes as argument a string that is composed
#  of zero or multiple characters. The empty string means that notifications
#  are disabled.
#
#  Example: to enable list and generic events, from the point of view of the
#           event name, use:
#
#  notify-keyspace-events Elg
#
#  Example 2: to get the stream of the expired keys subscribing to channel
#             name __keyevent@0__:expired use:
#
#  notify-keyspace-events Ex
#
#  By default all notifications are disabled because most users don't need
#  this feature and the feature has some overhead. Note that if you don't
#  specify at least one of K or E, no events will be delivered.
notify-keyspace-events ""

############################### GOPHER SERVER #################################

# Redis contains an implementation of the Gopher protocol, as specified in
# the RFC 1436 (https://www.ietf.org/rfc/rfc1436.txt).
#
# The Gopher protocol was very popular in the late '90s. It is an alternative
# to the web, and the implementation both server and client side is so simple
# that the Redis server has just 100 lines of code in order to implement this
# support.
#
# What do you do with Gopher nowadays? Well Gopher never *really* died, and
# lately there is a movement in order for the Gopher more hierarchical content
# composed of just plain text documents to be resurrected. Some want a simpler
# internet, others believe that the mainstream internet became too much
# controlled, and it's cool to create an alternative space for people that
# want a bit of fresh air.
#
# Anyway for the 10nth birthday of the Redis, we gave it the Gopher protocol
# as a gift.
#
# --- HOW IT WORKS? ---
#
# The Redis Gopher support uses the inline protocol of Redis, and specifically
# two kind of inline requests that were anyway illegal: an empty request
# or any request that starts with "/" (there are no Redis commands starting
# with such a slash). Normal RESP2/RESP3 requests are completely out of the
# path of the Gopher protocol implementation and are served as usual as well.
#
# If you open a connection to Redis when Gopher is enabled and send it
# a string like "/foo", if there is a key named "/foo" it is served via the
# Gopher protocol.
#
# In order to create a real Gopher "hole" (the name of a Gopher site in Gopher
# talking), you likely need a script like the following:
#
#   https://github.com/antirez/gopher2redis
#
# --- SECURITY WARNING ---
#
# If you plan to put Redis on the internet in a publicly accessible address
# to server Gopher pages MAKE SURE TO SET A PASSWORD to the instance.
# Once a password is set:
#
#   1. The Gopher server (when enabled, not by default) will still serve
#      content via Gopher.
#   2. However other commands cannot be called before the client will
#      authenticate.
#
# So use the 'requirepass' option to protect your instance.
#
# Note that Gopher is not currently supported when 'io-threads-do-reads'
# is enabled.
#
# To enable Gopher support, uncomment the following line and set the option
# from no (the default) to yes.
#
# gopher-enabled no

############################### ADVANCED CONFIG ###############################

# Hashes are encoded using a memory efficient data structure when they have a
# small number of entries, and the biggest entry does not exceed a given
# threshold. These thresholds can be configured using the following directives.
hash-max-ziplist-entries 512
hash-max-ziplist-value 64

# Lists are also encoded in a special way to save a lot of space.
# The number of entries allowed per internal list node can be specified
# as a fixed maximum size or a maximum number of elements.
# For a fixed maximum size, use -5 through -1, meaning:
# -5: max size: 64 Kb  <-- not recommended for normal workloads
# -4: max size: 32 Kb  <-- not recommended
# -3: max size: 16 Kb  <-- probably not recommended
# -2: max size: 8 Kb   <-- good
# -1: max size: 4 Kb   <-- good
# Positive numbers mean store up to _exactly_ that number of elements
# per list node.
# The highest performing option is usually -2 (8 Kb size) or -1 (4 Kb size),
# but if your use case is unique, adjust the settings as necessary.
list-max-ziplist-size -2

# Lists may also be compressed.
# Compress depth is the number of quicklist ziplist nodes from *each* side of
# the list to *exclude* from compression.  The head and tail of the list
# are always uncompressed for fast push/pop operations.  Settings are:
# 0: disable all list compression
# 1: depth 1 means "don't start compressing until after 1 node into the list,
#    going from either the head or tail"
#    So: [head]->node->node->...->node->[tail]
#    [head], [tail] will always be uncompressed; inner nodes will compress.
# 2: [head]->[next]->node->node->...->node->[prev]->[tail]
#    2 here means: don't compress head or head->next or tail->prev or tail,
#    but compress all nodes between them.
# 3: [head]->[next]->[next]->node->node->...->node->[prev]->[prev]->[tail]
# etc.
list-compress-depth 0

# Sets have a special encoding in just one case: when a set is composed
# of just strings that happen to be integers in radix 10 in the range
# of 64 bit signed integers.
# The following configuration setting sets the limit in the size of the
# set in order to use this special memory saving encoding.
set-max-intset-entries 512

# Similarly to hashes and lists, sorted sets are also specially encoded in
# order to save a lot of space. This encoding is only used when the length and
# elements of a sorted set are below the following limits:
zset-max-ziplist-entries 128
zset-max-ziplist-value 64

# HyperLogLog sparse representation bytes limit. The limit includes the
# 16 bytes header. When an HyperLogLog using the sparse representation crosses
# this limit, it is converted into the dense representation.
#
# A value greater than 16000 is totally useless, since at that point the
# dense representation is more memory efficient.
#
# The suggested value is ~ 3000 in order to have the benefits of
# the space efficient encoding without slowing down too much PFADD,
# which is O(N) with the sparse encoding. The value can be raised to
# ~ 10000 when CPU is not a concern, but space is, and the data set is
# composed of many HyperLogLogs with cardinality in the 0 - 15000 range.
hll-sparse-max-bytes 3000

# Streams macro node max size / items. The stream data structure is a radix
# tree of big nodes that encode multiple items inside. Using this configuration
# it is possible to configure how big a single node can be in bytes, and the
# maximum number of items it may contain before switching to a new node when
# appending new stream entries. If any of the following settings are set to
# zero, the limit is ignored, so for instance it is possible to set just a
# max entries limit by setting max-bytes to 0 and max-entries to the desired
# value.
stream-node-max-bytes 4096
stream-node-max-entries 100

# Active rehashing uses 1 millisecond every 100 milliseconds of CPU time in
# order to help rehashing the main Redis hash table (the one mapping top-level
# keys to values). The hash table implementation Redis uses (see dict.c)
# performs a lazy rehashing: the more operation you run into a hash table
# that is rehashing, the more rehashing "steps" are performed, so if the
# server is idle the rehashing is never complete and some more memory is used
# by the hash table.
#
# The default is to use this millisecond 10 times every second in order to
# actively rehash the main dictionaries, freeing memory when possible.
#
# If unsure:
# use "activerehashing no" if you have hard latency requirements and it is
# not a good thing in your environment that Redis can reply from time to time
# to queries with 2 milliseconds delay.
#
# use "activerehashing yes" if you don't have such hard requirements but
# want to free memory asap when possible.
activerehashing yes

# The client output buffer limits can be used to force disconnection of clients
# that are not reading data from the server fast enough for some reason (a
# common reason is that a Pub/Sub client can't consume messages as fast as the
# publisher can produce them).
#
# The limit can be set differently for the three different classes of clients:
#
# normal -> normal clients including MONITOR clients
# replica  -> replica clients
# pubsub -> clients subscribed to at least one pubsub channel or pattern
#
# The syntax of every client-output-buffer-limit directive is the following:
#
# client-output-buffer-limit <class> <hard limit> <soft limit> <soft seconds>
#
# A client is immediately disconnected once the hard limit is reached, or if
# the soft limit is reached and remains reached for the specified number of
# seconds (continuously).
# So for instance if the hard limit is 32 megabytes and the soft limit is
# 16 megabytes / 10 seconds, the client will get disconnected immediately
# if the size of the output buffers reach 32 megabytes, but will also get
# disconnected if the client reaches 16 megabytes and continuously overcomes
# the limit for 10 seconds.
#
# By default normal clients are not limited because they don't receive data
# without asking (in a push way), but just after a request, so only
# asynchronous clients may create a scenario where data is requested faster
# than it can read.
#
# Instead there is a default limit for pubsub and replica clients, since
# subscribers and replicas receive data in a push fashion.
#
# Both the hard or the soft limit can be disabled by setting them to zero.
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit replica 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60

# Client query buffers accumulate new commands. They are limited to a fixed
# amount by default in order to avoid that a protocol desynchronization (for
# instance due to a bug in the client) will lead to unbound memory usage in
# the query buffer. However you can configure it here if you have very special
# needs, such us huge multi/exec requests or alike.
#
# client-query-buffer-limit 1gb

# In the Redis protocol, bulk requests, that are, elements representing single
# strings, are normally limited to 512 mb. However you can change this limit
# here, but must be 1mb or greater
#
# proto-max-bulk-len 512mb

# Redis calls an internal function to perform many background tasks, like
# closing connections of clients in timeout, purging expired keys that are
# never requested, and so forth.
#
# Not all tasks are performed with the same frequency, but Redis checks for
# tasks to perform according to the specified "hz" value.
#
# By default "hz" is set to 10. Raising the value will use more CPU when
# Redis is idle, but at the same time will make Redis more responsive when
# there are many keys expiring at the same time, and timeouts may be
# handled with more precision.
#
# The range is between 1 and 500, however a value over 100 is usually not
# a good idea. Most users should use the default of 10 and raise this up to
# 100 only in environments where very low latency is required.
hz 10

# Normally it is useful to have an HZ value which is proportional to the
# number of clients connected. This is useful in order, for instance, to
# avoid too many clients are processed for each background task invocation
# in order to avoid latency spikes.
#
# Since the default HZ value by default is conservatively set to 10, Redis
# offers, and enables by default, the ability to use an adaptive HZ value
# which will temporarily raise when there are many connected clients.
#
# When dynamic HZ is enabled, the actual configured HZ will be used
# as a baseline, but multiples of the configured HZ value will be actually
# used as needed once more clients are connected. In this way an idle
# instance will use very little CPU time while a busy instance will be
# more responsive.
dynamic-hz yes

# When a child rewrites the AOF file, if the following option is enabled
# the file will be fsync-ed every 32 MB of data generated. This is useful
# in order to commit the file to the disk more incrementally and avoid
# big latency spikes.
aof-rewrite-incremental-fsync yes

# When redis saves RDB file, if the following option is enabled
# the file will be fsync-ed every 32 MB of data generated. This is useful
# in order to commit the file to the disk more incrementally and avoid
# big latency spikes.
rdb-save-incremental-fsync yes

# Redis LFU eviction (see maxmemory setting) can be tuned. However it is a good
# idea to start with the default settings and only change them after investigating
# how to improve the performances and how the keys LFU change over time, which
# is possible to inspect via the OBJECT FREQ command.
#
# There are two tunable parameters in the Redis LFU implementation: the
# counter logarithm factor and the counter decay time. It is important to
# understand what the two parameters mean before changing them.
#
# The LFU counter is just 8 bits per key, it's maximum value is 255, so Redis
# uses a probabilistic increment with logarithmic behavior. Given the value
# of the old counter, when a key is accessed, the counter is incremented in
# this way:
#
# 1. A random number R between 0 and 1 is extracted.
# 2. A probability P is calculated as 1/(old_value*lfu_log_factor+1).
# 3. The counter is incremented only if R < P.
#
# The default lfu-log-factor is 10. This is a table of how the frequency
# counter changes with a different number of accesses with different
# logarithmic factors:
#
# +--------+------------+------------+------------+------------+------------+
# | factor | 100 hits   | 1000 hits  | 100K hits  | 1M hits    | 10M hits   |
# +--------+------------+------------+------------+------------+------------+
# | 0      | 104        | 255        | 255        | 255        | 255        |
# +--------+------------+------------+------------+------------+------------+
# | 1      | 18         | 49         | 255        | 255        | 255        |
# +--------+------------+------------+------------+------------+------------+
# | 10     | 10         | 18         | 142        | 255        | 255        |
# +--------+------------+------------+------------+------------+------------+
# | 100    | 8          | 11         | 49         | 143        | 255        |
# +--------+------------+------------+------------+------------+------------+
#
# NOTE: The above table was obtained by running the following commands:
#
#   redis-benchmark -n 1000000 incr foo
#   redis-cli object freq foo
#
# NOTE 2: The counter initial value is 5 in order to give new objects a chance
# to accumulate hits.
#
# The counter decay time is the time, in minutes, that must elapse in order
# for the key counter to be divided by two (or decremented if it has a value
# less <= 10).
#
# The default value for the lfu-decay-time is 1. A special value of 0 means to
# decay the counter every time it happens to be scanned.
#
# lfu-log-factor 10
# lfu-decay-time 1

########################### ACTIVE DEFRAGMENTATION #######################
#
# What is active defragmentation?
# -------------------------------
#
# Active (online) defragmentation allows a Redis server to compact the
# spaces left between small allocations and deallocations of data in memory,
# thus allowing to reclaim back memory.
#
# Fragmentation is a natural process that happens with every allocator (but
# less so with Jemalloc, fortunately) and certain workloads. Normally a server
# restart is needed in order to lower the fragmentation, or at least to flush
# away all the data and create it again. However thanks to this feature
# implemented by Oran Agra for Redis 4.0 this process can happen at runtime
# in a "hot" way, while the server is running.
#
# Basically when the fragmentation is over a certain level (see the
# configuration options below) Redis will start to create new copies of the
# values in contiguous memory regions by exploiting certain specific Jemalloc
# features (in order to understand if an allocation is causing fragmentation
# and to allocate it in a better place), and at the same time, will release the
# old copies of the data. This process, repeated incrementally for all the keys
# will cause the fragmentation to drop back to normal values.
#
# Important things to understand:
#
# 1. This feature is disabled by default, and only works if you compiled Redis
#    to use the copy of Jemalloc we ship with the source code of Redis.
#    This is the default with Linux builds.
#
# 2. You never need to enable this feature if you don't have fragmentation
#    issues.
#
# 3. Once you experience fragmentation, you can enable this feature when
#    needed with the command "CONFIG SET activedefrag yes".
#
# The configuration parameters are able to fine tune the behavior of the
# defragmentation process. If you are not sure about what they mean it is
# a good idea to leave the defaults untouched.

# Enabled active defragmentation
# activedefrag no

# Minimum amount of fragmentation waste to start active defrag
# active-defrag-ignore-bytes 100mb

# Minimum percentage of fragmentation to start active defrag
# active-defrag-threshold-lower 10

# Maximum percentage of fragmentation at which we use maximum effort
# active-defrag-threshold-upper 100

# Minimal effort for defrag in CPU percentage, to be used when the lower
# threshold is reached
# active-defrag-cycle-min 1

# Maximal effort for defrag in CPU percentage, to be used when the upper
# threshold is reached
# active-defrag-cycle-max 25

# Maximum number of set/hash/zset/list fields that will be processed from
# the main dictionary scan
# active-defrag-max-scan-fields 1000

# Jemalloc background thread for purging will be enabled by default
jemalloc-bg-thread yes

# It is possible to pin different threads and processes of Redis to specific
# CPUs in your system, in order to maximize the performances of the server.
# This is useful both in order to pin different Redis threads in different
# CPUs, but also in order to make sure that multiple Redis instances running
# in the same host will be pinned to different CPUs.
#
# Normally you can do this using the "taskset" command, however it is also
# possible to this via Redis configuration directly, both in Linux and FreeBSD.
#
# You can pin the server/IO threads, bio threads, aof rewrite child process, and
# the bgsave child process. The syntax to specify the cpu list is the same as
# the taskset command:
#
# Set redis server/io threads to cpu affinity 0,2,4,6:
# server_cpulist 0-7:2
#
# Set bio threads to cpu affinity 1,3:
# bio_cpulist 1,3
#
# Set aof rewrite child process to cpu affinity 8,9,10,11:
# aof_rewrite_cpulist 8-11
#
# Set bgsave child process to cpu affinity 1,10,11
# bgsave_cpulist 1,10-11

# In some cases redis will emit warnings and even refuse to start if it detects
# that the system is in bad state, it is possible to suppress these warnings
# by setting the following config which takes a space delimited list of warnings
# to suppress
#
# ignore-warnings ARM64-COW-BUG
```

修改配置文件redis.conf

允许redis外地连接必须

注释掉#bind127.0.0.1

![image-20220308221600532](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308221600532.png)

daemonize=no
将daemonize=yes注释起来或者daemonize=no设置，因为该配置和docker run中 -d参数冲突，会导致容器一直启动失败

![image-20220308221741417](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308221741417.png)

关闭保护模式

![image-20220502140201373](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220502140201373.png)

### 使用redis6.0.8镜像创建容器（也叫运行镜像）

```shell
docker run -d --name redis -p 6379:6379 --privileged=true -v /app/redis/redis.conf:/etc/redis/redis.conf -v /app/redis/data:/data redis:6.2.6-bullseye redis-server /etc/redis/redis.conf
```

### 测试redis-cli连接上来

![image-20220308222428957](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220308222428957.png)

## 安装Nginx

见高级篇->Portainer

