# 第1章Nginx简介

## 1.1 Nginx概述

Nginx("engine x")是一个高性能的HTTP和反向代理服务器，特点是占有内存少，并发能力强，事实上nginx的并发能力确实在同类型的网页服务器中表现较好，中国大陆使用nginx网站用户有：百度、京东、新浪、网易、腾讯、淘宝等

## 1.2 Nginx作为web服务器

nginx可以作为静态页面的web服务器，同时还支持CGI协议的动态语言，比如perl、php等。但是不支持java。Java程序只能通过与tomcat配合完成。Nginx专为性能优化而开发，性能是其最重要的考量，实现上非常注重效率，能经受高负载的考验，有报告表明能支持高达50,000个并发连接数。

## 1.3正向代理

Nginx不仅可以做反向代理，实现负载均衡。还能用作正向代理来进行上网等功能。

正向代理：如果把局域网外的Internet想象成一个巨犬的资源库，则局域网中的客户端要访问Internet,则需要通过代理服务器来访问，这种代理服务就称为正向代理。

![image-20220306103410031](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306103410031.png)

## 1.4反向代理

反向代理，其实客户端对代理是无感知的，因为客户端不需要任何配置就可以访问，我们只需要将请求发送到反向代理服务器，由反向代理服务器去选择目标服务器获取数据后，在返回给客户端，此时反向代理服务器和目标服务器对外就是一个服务器，暴露的是代理服务器
地址，隐藏了真实服务器IP地址。

![image-20220306104220319](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306104220319.png)

## 1.5负载均衡

​	客户端发送多个请求到服务器，服务器处理请求，有一坚可能要与数据库进行交互，服务器处理完毕后，再将结果返回给客户端。

​	这种架构模式对于早期的系统相对单一，并发请求相对较少的情况下是比较适合的，成本也低。但是随着信息数量的不断增长，访问量和数据量的飞速增长，以及系统业务的复杂度增加，这种架构会造成服务器相应客户端的请求日益缓慢，并发量特别大的时候，还容易
造成服务器直接崩溃。很明显这是由于服务器性能的瓶颈造成的问题，那么如何解决这种情况呢？

​	我们首先想到的可能是升级服务器的配置，比如提高CPU执行频率，加大内存等提高机器的物理性能来解决此问题，但是我们知道摩尔定律的日益失效，硬件的性能提升已经不能满足日益提升的需求了。最明显的一个例子，天猫双十一当天，某个热销商品的瞬时访问量是极其庞大的，那么类似上面的系统架构，将机器都增加到现有的顶级物理配置，都是不能够满足需求的。那么怎么办呢？

​	上面的分析我们去掉了增加服务器物理配置来解决问题的办法，也就是说纵向解决问题的办法行不通了，那么横向增加服务器的数量呢？这时候集群的概念产生了，单个服务器解决不了，我们增加服务器的数量，然后将请求分发到各不服务器上，将原先请求集中到单个服务器上的情况改为将请求分发到多个服务器上，将负载分发到不同的服务器，也就是我们所说的**负载均衡**

![image-20220306111431559](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306111431559.png)

## 1.6动静分离

为了加快网站的解析速度，可以把动态页面和静态页面由不同的服务器来解析，加快解析速度。降低原来单个服务器的压力。

![image-20220306111646298](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306111646298.png)

# 第2章Nginx安装

## 2.1进入nginx官网，下载

http://nginx.org/

![image-20220306112413305](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306112413305.png)

---



![image-20220306112504079](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306112504079.png)



**nginx相关依赖**

在编译安装之前我们还需要先安装几个别的软件：

GCC/G++编译器：GCC（GNU Compiler Collection）可用来编译C语言程序，如果你还需要使用C++来编写Nginx HTTP模块，这时还需要用到G++编译器了。

PCRE库：PCRE（Perl Compatible Regular Expressions，Perl兼容正则表达式）是由Philip Hazel开发的函数库，目前为很多软件所使用，该库支持正则表达式。实际上在nginx的很多高级配置中都会用到正则表达式，因此我们在编译Nginx时尽量先把PCRE库编译进Nginx。

zlib库：zlib库用于对HTTP包的内容做gzip格式的压缩，我们可以在nginx.conf里配置了gzip on，并指定对于某些类型（content-type）的HTTP响应使用gzip来进行压缩以减少网络传输量。

OpenSSL开发库：HTTPS必备，这个就不用解释了

![image-20220306112556342](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306112556342.png)

## 2.2安装nginx

**1.安装prce依赖**

![image-20220306113258657](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306113258657.png)

```shell
yum install -y pcre pcre-devle
```



要有gcc

```shell
yum install gcc -y
yum install gcc-c++ -y
```

解压

进入prce目录，执行

```shell
./configure
```

![image-20220306114306412](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306114306412.png)

编译并安装

```shell
make && make install
```

查看版本

![image-20220306114902938](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306114902938.png)

**2.安装其它依赖**

```shell
yum -y install make zlib zlib-devel gcc-c++ libtool openssl openssl-devel
```

**3.安装nginx**

解压

进入nginx目录，执行

```shell
./configure
make && make install
```

安装成功之后，在usr多出来一个文件夹local/ginx,在nginx有sbin有启动脚本

![image-20220306115603670](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306115603670.png)

**启动**

```shell
[root@Redis sbin]# pwd
/usr/local/nginx/sbin
[root@Redis sbin]# ./nginx 
```

查看开放的端口号

```shell
firewall-cmd --list-all
```

设置开放的端口号

```shell
#sudo firewall-cmd --add-service=http --permanent 永久开放
sudo firewall-cmd --add-port=80/tcp --permanent
```

重启防火墙

```shell
firewall-cmd --reload
```

# 第3章nginx常用的命令和配置文件

## 3.1 nginx常用的命令：

1、使用nginx操作命令前提条件：必须进入nginx的目录/usr/local/nginx/sbin

2、查看nginx的版本号

```shell
./nginx -v

[root@Redis sbin]# ./nginx -v
nginx version: nginx/1.20.2

```

3、启动nginx

```shell
./nginx
```

4、关闭nginx

```shell
./nginx -s stop
```

5、重新加载nginx

```shell
./nginx -s reload
```

## 3.2 nginx.conf配置文件

![image-20220306121356946](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306121356946.png)

根据上述文件，我们可以很明显的将nginx.conf配置文件分为三部分：

### 第一部分：全局块

从配置文件开始到events块之间的内容，主要会设置一些影响nginx服务器整体运行的配置指纹，主要包括配置运行Nginx服务器的用户（组）、允许生成的worker process数，进程PID存放路径、日志存放路径和类型以及配置文件的引入等。

比如上面第一行配置的：

```properties
worker_processes  1;
```

这是nginx服务器并发处理服务的关键配置，worker_processes值越大，可以支持的并发处理量也越多，但是会受到硬件、软件等设备的制约

### 第二部分events块：

events块涉及的指令主要影响Nginx眼务器与用户的网络连接，常用的设置包括是否开启对多work process下的网络连接进行序列化，是否允许同时接收多个网络连接，选取哪种事件驱动模型来处理连接请求，每个work process可以同时支持的最大连接数等。

```properties
events {
    worker_connections  1024;
}
```

上述例子就表示每个work process支持的最大连接数为1024

这部分的配置对9x的性能影响较大，在实际中应该灵活配置。

### 第三部分http块：

这算是Nginx服务器配置中最频繁的部分，代理、缓存和日志定义等绝大多数功能和第三方模块的配置都在这里。需要注意的是：http块也可以包括**http全局块、server块。**

#### ①、http全局块

http全局块配置的指令包括文件引入、MIME-TYPE定义、日志自定义、连接超时时间、单链接请求数上限等。

#### ②、server块

这和虚拟主机有密切关系，虚拟主机从用户角度看，和一台独立的硬件主机是完全一样的，该技术的产生是为了节省互联网服务器硬件成本。

每个http块可以包括多个server块，而每个server块就相当于一个虚拟主机。

而每个server块也分为全局server块，以及可以同时包含多个locaton块。

##### 1、全局server块

最常见的配置是本虚拟机主机的监听配置和本虚拟主机的名称或IP配置。

##### 2、location块

一个server块可以配置多个location块。

这块的主要作用是基于Nginx服务器接收到的请求字符串（例如server_.name/uri-string),对虚拟主机名称(也可以是IP别名)之外的字符串（例如前面的/uri-string)进行匹配，对特定的请求进行处理。地址定向、数据缓存和应答控制等功能，还有许多第三方模块的配置也在这里进行。

# 第4章nginx配置实例-反向代理

## 4.1反向代理实例一

1、实现效果

打开浏览器，在浏览器地址栏输入地址www.123.com,跳转到liunx系统tomcat主页面中

2、准备工作

(1)、在liunx系统安装tomcat,.使用默认端口8080tomcat安装文件放到iunx系统中，解压

进入tomcat的bin目录中，/startup.sh启动tomcat服务器

(2)、对外开放访问的端口

```shell
firewall-cmd --add-port=8080/tcp --permanent
firewall-cmd --reload
```

3.访问过程分析

![image-20220306131745306](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306131745306.png)

4.具体配置

第一步在windows系统的host文件进行域名和ip对应关系的配置

![image-20220306132002153](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306132002153.png)

```properties
192.168.229.131 www.123.com
```

第二步修改ngnix配置文件

![image-20220306141318031](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306141318031.png)

5.最终效果

![image-20220306134032177](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306134032177.png)

## 4.2反向代理实例二

1、实现效果
使用nginx反向代理，根据访问的路径跳转到不同端口的服务中 nginx监听端口为9001，

访问http:/192.1668.229.131:9001/edu 直接跳转到127.0.0.1:8080

访问http:/192.1668.229.131:9001/vod 直接跳转到127.0.0.1:8081

2、准备工作

(1)准备两个tomcat服务器，一个8080端口，一个8081端口：

![image-20220306134901416](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306134901416.png)

3、具体配置
(1)找到nginx配置文件，进行反向代理配置

![image-20220306140046151](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306140046151.png)

(2)开放9001端口

![image-20220306141422279](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306141422279.png)

![image-20220306141426291](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306141426291.png)

#### location指令说明

该指令用于匹配URL。

语法如下：

![image-20220306140453474](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306140453474.png)

1、= ：用于不含正则表达式的uri前，要求请求字符串与uri严格匹配，如果匹配成功，就停止继续向下搜索并立即处理该请求。

2、~ :   用于表示 uri 包含正则表达式，并且区分大小写。

3、~*  : 用于表示uri包含正则表达式，并且不区分大小写。

4、^~ :用于不含正则表达式的uri前，要求nginx服务器找到标识uri和请求字符串匹配度最高的location后，立即使用此location处理请求，而不再使用location块中的正则和请求字符串做匹配。

注意：如果uri包含正则表达式，则必须要有~或者~*标识。

# 第5章nginx配置实例-负载均衡

1、实现效果

(1)浏览器地址栏输入地址http:/192.168.229.131/edu/a.html,负载均衡效果，平均8080和8081端口中，

2、准备工作

(1)准备两合tomcat服务器，一合8080，一台8081

(2)在两台tomcat里面webapps目录中，创建名称是edu文件夹，在edu文件夹中创建a.html页面，用于测试

![image-20220306143401772](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306143401772.png)



![image-20220306143605431](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306143605431.png)

![image-20220306143615236](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306143615236.png)

随着互联网信息的爆炸性增长，负载均衡(load balance)已经不再是一个很陌生的话题，顾名思义，负载均衡即是将负载分摊到不同的服务单元，既保证服务的可用性，又保证响应足够快，给用户很好的体验.快速增长的访问量和数据流量催生了各式各样的负载均衡产品，
很多专业的负载均衡硬件提供了很好的功能，但却价格不菲，这使得负载均衡软件大受欢迎，nginx就是其中的一个，在linux下有Nginx、LVS、Haproxy等等服务可以提供负载均衡服务，而且Nginx提供了几种分配方式（策略）：

1、轮询（默认）

每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器dow掉，能自动剔除。

2、weight.

weight代表权重，默认为1，权重越高被分配的客户端越多.

指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。例如：

```properties
upstream myserver{
	server 192.168.229.131:8080 weight=5;
	server 192.168.229.131:8081 weight=10;
}
```

3、ip_hash

每个请求按访问ip的hash结果分配，这样每个访客**固定访问一个后端服务器**，可以解决session的问题。例如：

```properties
upstream myserver{
	ip_hash;
	server 192.168.229.131:8080;
	server 192.168.229.131:8081;
}
```

4、fair(第三方)

按后端服务器的响应时间来分配请求，响应时间短的优先分配。

```properties
upstream myserver{
	server 192.168.229.131:8080;
	server 192.168.229.131:8081;
	fair;
}
```

# 第6章nginx配置实例-动静分离

Nginx动静分离简单来说就是把动态跟静态请求分开，不能理解成只是单纯的把动态页面和静态页面物理分离。严格意义上说应该是动态请求跟静态请求分开，可以理解成使用Nginx处理静态页面，Tomcat处理恸态页面。动静分离从目前实现角度来讲大致分为两种，

一种是纯粹把静态文件独立成单独的域名，放在独立的服务器上，也是目前主流推崇的方案：

另外一种方法就是动态跟静态文件混合在一起发布，通过Nginx来分开。

通过location指定不同的后缀名实现不同的请求转发。通过expires参数设置，可以使浏览器缓存过期时间，减少与服务器之前的请求和流量。具体Expires定义：是给一个资源设定一个过期时间，也就是说无需去服务端验证，直接通过浏览器自身确认是否过期即可，所以不会产生额外的流量。此种方法非常适合不经常变动的资源。（如果经常更新的文件，不建议使用Expires来缓存)，我这里设置3d,表示在这3天之内访问这个URL,发送一个请求，比对服务器该文件最后更新时间没有变化，则不会从服务器抓取，返回状态码304,如果有修改，则直接从服务器重新下载，返回状态码200。

## 6.1实验代码

1、准备工作

(1)在iunx系统中准备静态资源，用于进行访问

2、配置文件

![image-20220306150037163](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306150037163.png)

配置文件autoindex on 列出文件目录

4、最终测试，

(1)浏览器中输入地址

htpp:/192.168.229.131/image/01.jpg

![image-20220306150927320](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306150927320.png)

(2)浏览器中输入地址

htpp:/192.168.229.131/www/a.html

![image-20220306151019393](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306151019393.png)

# 第7章nginx搭建高可用集群

![image-20220306153135302](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306153135302.png)

##  Keepalived+Nginx高可用集群

1、配置高可用的准备工作

(1)需要两合服务器

(2)在两合服务器安装nginx.

(3)在两台服务器安装keepalived

```shell
yum install keepalived -y
```

如果安装失败

![image-20220306161626470](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306161626470.png)

2、修改配置文件

![image-20220306200036281](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306200036281.png)

```properties
# 全局配置
global_defs {
    notification_email {
        124143222@qq.com  #设置报警收件人邮件地址，可以设置多个，每行一个。需要开启sendmail服务。
    }
    notification_email_from 124143222@qq.com #发件人
    smtp_server 192.168.229.129  #设置SMTP Server地址
    smtp_connection_timeout 30   #设置SMTP Server的超时时间
    router_id nginx_master       #表示运行Keepalived服务器的一个标识，唯一的
}
#检测脚本
vrrp_script chk_http_port {
#    script "killall -0 nginx" # 这个脚本只做信号检查，功能弱了一些，一般都自定义脚本
    script "/usr/local/src/check_nginx.sh" #心跳执行的脚本，检测nginx是否启动
    interval 2                          #（检测脚本执行的间隔，单位是秒）
    weight -2                            #权重
}
#vrrp 实例定义部分
vrrp_instance VI_1 {
    state MASTER            # 指定keepalived的角色，MASTER为主，BACKUP为备 说明：可以都设置为BACKUP,先
启动为主，然后nopreempt才能有效
    interface ens33         # 当前进行vrrp通讯的网络接口卡(当前centos的网卡) 用ifconfig查看你具体的网>卡
    virtual_router_id 51    # 虚拟路由编号，主从要一致
    priority 100            # 优先级，数值越大，获取处理请求的优先级越高
    advert_int 1            # 检查间隔，默认为1s(vrrp组播周期秒数)
    #授权访问
    authentication {
        auth_type PASS #设置验证类型和密码，MASTER和BACKUP必须使用相同的密码才能正常通信
        auth_pass 1111
    }
    virtual_ipaddress {
        192.168.229.50            # 定义虚拟ip(VIP)，可多设，每行一个
    }
}
```

在/usr/local/src/目录下添加脚本文件nginx_check.sh

```sh
#!/bin/bash
A=`ps -C nginx -no-header |wc -1`
if [ $A -eq 0 ];then
    /usr/local/nginx/sbin/nginx
    sleep 2
    if [ `ps -C nginx --no-header |wc -1` -eq 0 ];then
        killall keepalived
    fi
fi
```

启动服务器

```shell
#启动keepalived
systemctl start keepalived.service

#启动Nginx
/usr/locl/nginx/sbin/nginx
```

**测试**

浏览器输入虚拟IP地址 http://192.168.229.50/

关闭主机

```shell
[root@localhost src]# systemctl stop keepalived.service
[root@localhost src]# /usr/local/nginx/sbin/nginx -s stop
[root@localhost src]# ps -ef | grep nginx
root      34472  29557  0 21:24 pts/2    00:00:00 grep --color=auto nginx
[root@localhost src]# ps -ef | grep keepalived
root      34479  29557  0 21:24 pts/2    00:00:00 grep --color=auto keepalived
```



![image-20220306212543357](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306212543357.png)







# 第8章nginx原理

![image-20220306213156089](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306213156089.png)

![image-20220306213237643](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306213237643.png)

**master-workers的机制的好处**

首先，对于每个worker进程来说，独立的进程，不需要加锁，所以省掉了锁带来的开销，同时在编程以及问题查找时，也会方便很多。其次，采用独立的进程，可以让互相之间不会影响，一个进程退出后，其它进程还在工作，服务不会中断，master进程则很快启动新的
worker进程。当然，worker进程的异常退出，肯定是程序有bug了，异常退出，会导致当前worker上的所有请求失败，不过不会影响到所有请求，所以降低了风险。

**需要设置多少个worker.**

Ninx同edis类似都采用了io多路复用机制，每个worker都是一个独立的进程，但每个进程里只有一个主线程，通过异步非阻塞的方试来处理请求，即使是千上万个请求也不在话下。每个worker的线程可以把一个cpu的性能发挥到极致。所以worker数和服务器的cpu数相等是最为适宜的。设少了会浪费9pu,设多了会造成cpu频繁切换上下文带来的损耗。

**连接数worker connection**

这个值是表示每个worker进程所能建立连接的最大值，所以，一个nginx能建立的最大连接数，应该是worker connections * worker processes。当然，这里说的是最大连接数，对于HTTP请求本地资源来说，能够支持的最大并发数量是worker connections * worker_processes,如果是支持http1.1的浏览器每次访问要占**两个连接**，所以普通的静态访问最大并发数是：worker connections * worker processes/2,而如果是HTTP作为反向代理来说，最大并发数量应该是worker connections * worker processes/4。因为作为反向代理服务器，**每个并发会建立与客户端的连接和与后端服务的连接，会占用两个连接。**

![image-20220306214145568](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220306214145568.png)