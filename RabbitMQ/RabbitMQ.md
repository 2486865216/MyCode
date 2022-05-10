# 1.消息队列

## 1.1. MQ的相关概念

### 1.1.1. 什么是MQ

MQ(message queue),从字面意思上看，本质是个队列，FIF0先入先出，只不过队列中存放的内容是message而已，还是一种跨进程的通信机制，用于上下游传递消息。在互联网架构中，MQ是一种非常常见的上下游“逻辑解耦+物理解耦”的消息通信服务。使用了MQ之后，消息发送上游只需要依赖MQ,不用依赖其他服务。

### 1.1.2. 为什么要用MQ

#### 1.流量消峰

举个例子，如果订单系统最多能处理一万次订单，这个处理能力应付正常时段的下单时绰绰有余, 正常时段我们下单一秒后就能返回结果。但是在高峰期，如果有两万次下单操作系统是处理不了的，只能限制订单超过一万后不允许用户下单。使用消息队列做缓冲，我们可以取消这个限制，把一秒内下的订单分散成一段时间来处理，这时有些用户可能在下单十几秒后才能收到下单成功的操作，但是比不能下单的体验要好。

#### 2.应用解耦

以电商应用为例，应用中有订单系统、库存系统、物流系统、支付系统。用户创建订单后，如果耦合调用库存系统、物流系统、支付系统，任何一个子系统出了故障，都会造成下单操作异常。当转变成基于消息队列的方式后，系统间调用的问题会减少很多，比如物流系统因为发生故障，需要几分钟来修复。在这几分钟的时间里，物流系统要处理的内存被缓存在消息队列中，用户的下单操作可以正常完成。当物流系统恢复后，继续处理订单信息即可，订单用户感受不到物流系统的故障，提升系统的可用性。

![image-20220223202643514](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223202643514.png)

#### 3.异步处理

有些服务间调用是异步的，例如A调用B,B需要花费很长时间执行，但是A需要知道B什么时候可以执行完，以前一般有两种方式，A过一段时间去调用B的查询api查询。或者A提供一个callback api,B执行完之后调用api通知A服务。这两种方式都不是很优雅，使用消息总线，可以很方便解决这个问题，A调用B服务后，只需要监听B处理完成的消息，当B处理完成后，会发送一条消息给MQ,MQ会将此消息转发给A服务。这样A服务既不用循环调用B的查询api,也不用提供callback api。同样B服务也不用做这些操作。A服务还能及时的得到异步处理成功的消息。

![image-20220223203215266](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223203215266.png)

### 1.1.3. MQ的分类

#### 1.ActiveMQ

优点：单机吞吐量万级，时效性ms级，可用性高，基于主从架构实现高可用性，消息可靠性较低的概率丢失数据
缺点：官方社区现在对ActiveMQ 5.x维护越来越少，高吞吐量场景较少使用。

#### 2.Kafka

​	大数据的杀手锏，淡到大数据领域内的消息传输，则绕不开Kafka，这款为大数据而生的消息中间件，以其**百万级TPS**的吞吐量名声大噪，迅速成为大数据领域的宠儿，在数据采集、传输、存储的过程中发挥着举足轻重的作用。目前已经被LinkedIn,Uber,Twitter,Netfix等大公司所采纳。

​	优点：性能卓越，单机写入TPS约在百万条/秒，最大的优点，就是**吞吐量高**。时效性ms级可用性非常高，kafka是分布式的，一个数据多个副本，少数机器宕机，不会丢失数据，不会导致不可用，消费者采用Pull方式获取消息，消息有序，通过控制能够保证所有消息被消费且仅被消费一次：有优秀的第三方Kafka Web管理界面Kafka-Manager;在日志领域比较成熟，被多家公司和多个开源项目使用；功能支持：功能较为简单，主要支持简单的MQ功能，在大数据领域的实时计算以及日志采集被大规模使用

​	缺点：Kafka单机超过64个队列/分区，Load会发生明显的飙高现象，队列越多，load越高，发送消息响应时间变长，使用短轮询方式，实时性取决于轮询间隔时间，消费失败不支持重试；支持消息顺序，但是一台代理宕机后，就会产生消息乱序，社区更新较慢；

#### 3.RocketMQ

​	RocketMQ出自阿里巴巴的开源产品，用Java语言实现，在设计时参考了Kafka,并做出了自己的一些改进。被阿里巴巴广泛应用在订单，交易，充值，流计算，消息推送，日志流式处理，binglog.分发等场景。	

​	优点：**单机吞吐量十万级**，可用性非常高，分布式架构，**消息可以做到0丢失**，MQ功能较为完善，还是分布式的，扩展性好，**支持10亿级别的消息堆积**，不会因为堆积导致性能下降，源码是java我们可以自己阅
读源码，定制自己公司的MQ

​	缺点：**支持的客户端语言不多**，目前是java及c++,其中c++不成熟；社区活跃度一般，没有在MQ核心中去实现JMS等接口，有些系统要迁移需要修改大量代码

#### 4.RabbitMQ

2007年发布，是一个在AMQP(高级消息队列协议)基础上完成的，可复用的企业消息系统，是当前最主流的消息中间件之一。

​	优点：由于erlang语言的**高并发特性**，性能较好；吞吐量到万级，MQ功能比较完备，健壮、稳定、易用、跨平台、支持多种语言如：Python、.Ruby、.NET、Java、JMS、C、PHP、ActionScript、XMPP、STOMP等，支持A]AX文档齐全；开源提供的管理界面非常棒，用起来很好用，社区活跃度高；更新频率相当高https://www.rabbitmq.com/news.html

​	缺点：商业版需要收费，学习成本较高

### 1.1.4.MQ的选择

#### 1.Kafka

Kafka主要特点是基于Pull的模式来处理消息消费，追求高吞吐量，一开始的目的就是用于**日志收集和传输**，适合产生**大量数据**的互联网服务的数据收集业务。大型公司建议可以选用，如果有日志采集功能肯定是首选kafka了。

#### 2.RocketMQ

天生为**金融互联网**领域而生，对于可靠性要求很高的场景，尤其是电商里面的订单扣款，以及业务削峰，在大量交易涌入时，后端可能无法及时处理的情况。RoketMQ在稳定性上可能更值得信赖，这些业务场景在阿里双11已经经历了多次考验，如果你的业务有上述并发场景，建议可以选择RocketMQ。

#### 3.RabbitMQ

结合erlang语言本身的并发优势，**性能好时效性微秒级，社区活跃度也比较高**，管理界面用起来十分方便，如果你的**数据量没有那么大**，中小型公司优先选择功能比较完备的RabbitMQ

## 1.2.RabbitMQ

### 1.2.1.RabbitMQ的概念

RabbitMQ是一个消息中间件：它接受并转发消息。你可以把它当做一个快递站点，当你要发送一个包裹时，你把你的包裹放到快递站，快递员最终会把你的快递送到收件人那里，按照这种逻辑RabbitMQ是一个快递站，一个快递员帮你传递快件。RabbitMQ与快递站的主要区别在于，它不处理快件而是接收，存储和转发消息数据。

![image-20220223205037168](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223205037168.png)

### 1.2.2.四大核心概念

#### 生产者

​	产生数据发送消息的程序是生产者

#### 交换机

​	交换机是RabbitMQ非常重要的一个部件，一方面它接收来自生产者的消息，另一方面它将消息推送到队列中。交换机必须确切知道如何处理它接收到的消息，是将这些消息推送到特定队列还是推送到多个队列，亦或者是把消息丢弃，这个得有交换机类型决定

#### 队列

​	队列是RabbitMQ内部使用的一种数据结构，尽管消息流经RabbitMQ和应用程序，但它们只能存储在队列中。队列仅受主机的内存和磁盘限制的约束，本质上是一个大的消息缓冲区。许多生产者可以将消息发送到一个队列，许多消费者可以尝试从一个队列接收数据。这就是我们使用队列的方式

#### 消费者

​	消费与接收具有相以的含义。消费者大多时候是一个等待接收消息的程序。请注意生产者，消费者和消息中间件很多时候并不在同一机器上。同一个应用程序既可以是生产者又可以是消费者。

### 1.2.3. RabbitMQ核心部分

![image-20220223205345104](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223205345104.png)

### 1.2.4. 各个名词介绍

![image-20220223205419395](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223205419395.png)

**Broker**:接收和分发消息的应用，RabbitMQ Server就是Message Broker

**Virtual host**:出于多租户和安全因素设计的，把AMQP的基本组件划分到一个虚拟的分组中，类似于网络中的namespace概念。当多个不同的用户使用同一个RabbitMQ server提供的服务时，可以划分出多个vhost,每个用户在自己的vhost创建exchange/queue等

**Connection**:publisher/consumer和broker之间的TCP连接

**Channe**:如果每一次访问RabbitMQ都建立一个Connection,在消息量大的时候建立TCP,Connection的开销将是巨大的，效率也较低。Channel是在connection内部建立的逻辑连接，如果应用程序支持多线程，通常每个thread创建单独的channel进行通讯，AMQP method包含了channel id帮助客户端和message broker识别channel,所以channel之间是完全隔离的。Channel作为轻量级的**Connection极大减少了操作系统建立TCP connection的开销**

**Exchange**:message到达broker的第一站，根据分发规则，匹配查询表中的routing key,.分发消息到queue中去。常用的类型有：direct(point--to-point),topic(publish-subscribe) and fanout(multicast)

### 1.2.5.安装

1. 官网地址
   https://www.rabbitmq.com/download.html

2. 文件上传
   上传到/usr/local/software目录下（如果没有software需要自己创建）

   ![image-20220223211553497](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223211553497.png)

3. 安装文件（分别按照以下顺序安装）
   
   ```shell
   rpm -ivh erlang-21.3-1.el7.x86_64.rpm
   yum install socat -y
   rpm -ivh rabbitmq-server-3.8.8-1.el7.noarch.rpm
   ```
   
   
   
4. 常用命令（按照以下顺序执行）

   - 添加开机启动RabbitMQ服务
     - chkconfig rabbitmq-server on
     
   - 启动服务
     - /sbin/service rabbitmq-server start
     
   - 查看服务状态
     - /sbin/service rabbitmq-server status
     
   - ![image-20220223221836964](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223221836964.png)
   
   - 停止服务（选择执行）
     /sbin/service rabbitmq-server stop
     
   - 开启web管理插件(先停止服务)[Management Plugin — RabbitMQ](https://www.rabbitmq.com/management.html)
     
     ```shell
     rabbitmq-plugins enable rabbitmq_management
     #安装不成功
     vim /etc/hosts
     ```
     
     
     
     用默认账号密码(guest)访问地址{hostname}:15672/出现权限问题
     
   - ![image-20220223231419974](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223231419974.png)
   
   - 创建用户设置角色
   
   	```shell
   	rabbitmqctl add_user admin 123
   	rabbitmqctl set_user_tags admin administrator
   	rabbitmqctl list_users
   	rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*"
   	```
   
   	
   
   - ![image-20220223231644157](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223231644157.png)
   
   - ![image-20220223231826173](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223231826173.png)
   
   - 设置用户权限
   
   - ![image-20220223232007341](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220223232007341.png)
   
   - set_permissions [-p <vhostpath>]<user><conf><write><read>
     用户user_admin具有/vhost这个virtual host中所有资源的配置、写、读权限
     
   - 当前用户和角色
     rabbitmqctl list_users

```
$ sudo firewall-cmd --permanent --add-port=5672/tcp
$ sudo firewall-cmd --permanent --add-port=15672/tcp
$ sudo firewall-cmd --reload
$ sudo firewall-cmd --list-all

```



# 2. Hello Word

## 2.1.依赖

```java
<!--    指定JDK编译版本-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <dependencies>
        <!-- RabbitMQ依赖  https://mvnrepository.com/artifact/com.rabbitmq/amqp-client -->
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>5.8.0</version>
        </dependency>
        <!-- 操作文件流 https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>

    </dependencies>
```



## 2.2.消息生产者

```java
package com.example.rabbitMQ.first;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author ye
 * createDate 2022/2/24  11:40
 * 生产者
 */
public class Producer {
    //队列
    public static final String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args){
        //创建一个连接工厂
        ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        //工厂的IP
        factory.setHost("192.168.229.130");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        ///创建连接
        try {
            Connection connection = factory.newConnection();

            //获取信道
            Channel channel = connection.createChannel();

            /**
             * 生成一个队列
             * String queue,                    队列名
             * boolean durable,                 是否持久化(默认内存)
             * boolean exclusive,               该队列是否只供一个消费者进行消费是否进行消息共享，true可以多个消费者消贵
             * boolean autoDelete,              是否自动删除最后一个消费者端开连接以后该队一句是否自动删除true自动删除false不自动删除
             * Map<String, Object> arguments    其它参数
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //发消息
            String message = "Hello Word";

            /**
             * 发送一个消费
             * String var1,             发送到哪个交换机
             * String var2,             路由的Key值是哪个,本次是队列的名称
             * BasicProperties var3,    其它参数信息
             * byte[] var4              发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
```



## 2.3.消息消费者

```java
package com.example.rabbitMQ.first;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author ye
 * createDate 2022/2/24  12:07
 * 消费者
 */
public class Consumer {
    //队列名
    public static final String QUEUE_NAME = "hello";
    //接受消息
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        //工厂的IP
        factory.setHost("192.168.229.130");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //消费者成功消费的回调
            DeliverCallback deliverCallback = (consumer, message) -> {
                System.out.println(consumer);
                System.out.println(new String(message.getBody()));
            };

            //消费者取消消费的回调
            CancelCallback cancelCallback = var1 -> {
                System.out.println("消息消费被中断");
            };
            /**
             * 消费者消费消息
             * String var1,          消费哪个队列
             * boolean var2,         消费成功之后是否要自动应答,true代表的自动应答,false代表手动应答
             * DeliverCallback var3,  消费者成功消费的回调
             * CancelCallback var4   消费者取消消费的回调
             */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
```

# 3. Work Queues

​	工作队列（又称任务队列）的主要思想是避免立即执行资源密集型任务，而不得不等待它完成。
相反我们安排任务在之后执行。我们把任务封装为消息并将其发送到队列。在后台运行的工作进
程将弹出任务并最终执行作业。当有多个工作线程时，这些工作线程将一起处理这些任务。

## 3.1.轮训分发消息

![image-20220224165250583](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224165250583.png)

### 3.1.1.抽取工具类

```java
package com.example.rabbitMQ.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author ye
 * createDate 2022/2/24  16:56
 */
public class RabbitMQUtil {
    public static Channel getChannel(){
        //创建连接工厂
        ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        //工厂的IP
        factory.setHost("192.168.229.130");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
```

### 3.1.2.启动两个工作线程

![image-20220224174611423](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224174611423.png)

### 3.1.3.启动一个发送线程

![image-20220224174634558](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224174634558.png)

### 3.1.4.结果展示

![image-20220224174654710](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224174654710.png)

![image-20220224174659861](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224174659861.png)

## 3.2.消息应答

### 3.2.1.概念

​	消费者完成一个任务可能需要一段时间，如果其中一个消费者处理一个长的任务并仅只完成了部分突然它挂掉了，会发生什么情况。RabbitMQ一旦向消费者传递了一条消息，便立即将该消息标记为删除。在这种情况下，突然有个消费者挂掉了，我们将丢失正在处理的消息。以及后续发送给该消费这的消息，因为它无法接收到。为了保证消息在发送过程中不丢失，rabbitmq引入消息应答机制，消息应答就是：**消费者在接收到消息并且处理该消息之后，告诉rabbitmq.它已经处理了，rabbitmq可以把该消息删除了。**

### 3.2.2.自动应答

​	消息发送后立即被认为已经传送成功，这种模式需要在**高吞吐量和数据传输安全性方面做权衡，**因为这种模式如果消息在接收到之前，消费者那边出现连接或者channel关闭，那么消息就丢失了，当然另一方面这种模式消费者那边可以传递过载的消息，**没有对传递的消息数量进行限制，**当然这样有可能使得消费者这边由于接收太多还来不及处理的消息，导致这些消息的积压，最终使得内存耗尽，最终这些消费者线程被操作系统杀死，**所以这种模式仅适用在消费者可以高效并以某种速率能够处理这些消息的情况下使用。**

### 3.2.3.消息应答的方法

1. Channel.basicAck(用于肯定确认)
   RabbitMQ已知道该消息并且成功的处理消息，可以将其丢弃了
2. Channel.basicNack(用于否定确认)
3. Channel.basicReject(用于否定确认)
   与Channel.basicNack相比少一个参数，不处理该消息了直接拒绝，可以将其丢弃了

### 3.2.4. Multiple的解释

手动应答的好处是可以批量应答并且减少网络拥堵

multiple的true和false代表不同意思

- true代表批量应答channel上未应答的消息
  比如说channel上有传送tag的消息5,6,7,8当前tag是8那么此时5-8的这些还未应答的消息都会被确认收到消息应答
- false同上面相比
  只会应答tag=8的消息,5,6,7这三个消息依然不会被确认收到消息应答

![image-20220224175218341](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224175218341.png)

![image-20220224175258116](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224175258116.png)

### 3.2.5.消息自动重新入队

​	如果消费者由于某些原因失去连接（其通道已关闭，连接己关闭或TCP连接丢失），导致消息未发送ACK确认，RabbitMQ将了解到消息未完全处理，并将对其重新排队。如果此时其他消费者可以处理，它将很快将其重新分发给另一个消费者。这样，即使某个消费者偶尔死亡，也可以确保不会丢失任何消息。

![image-20220224175412800](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224175412800.png)

### 3.2.6.消息手动应答代码(消息在手动应答时是不丢失、放回队列中重新消费)

​	默认消息采用的是自动应答，所以我们要想实现消息消费过程中不丢失，需要把自动应答改为手动应答，消费者在上面代码的基础上增加下面画红色部分代码。

![image-20220224175821790](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224175821790.png)

### 3.2.7效果展示

![image-20220224204223671](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224204223671.png)

## 3.3. RabbitMQ持久化

### 3.3.1.概念

​	刚刚我们已经看到了如何处理任务不丢失的情况，但是如何保障当RabbitMQ服务停掉以后消息生产者发送过来的消息不丢失。默认情况下RabbitMQ退出或由于某种原因崩溃时，它忽视队列和消息，除非告知它不要这样做。确保消息不会丢失需要做两件事：我们需要将队列和消息都标记为持久化。

### 3.3.2.队列如何实现持久化

​	之前我们创建的队列都是非持久化的，rabbitmg如果重启的话，该队列就会被删除掉，如果要队列实现持久化需要在声明队列的时候把durable参数设置为持久化

![image-20220224212100949](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224212100949.png)

但是需要注意的就是如果之前声明的队列不是持久化的，需要把原先队列先删除，或者重新创建一个持久化的队列，不然就会出现错误

以下为控制台中持久化与非持久化队列的UI显示区

![image-20220224212541999](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224212541999.png)

这个时候即使重启rabbitmq队列也依然存在

### 3.3.3.消息实现持久化

​	要想让消息实现持久化需要在消息生产者修改代码，MessageProperties.PERSISTENT_TEXT_PLAIN添
加这个属性。

![image-20220224212726280](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224212726280.png)

​	将消息标记为持久化并不能完全保证不会丢失消息。尽管它告诉RabbitMQ将消息保存到磁盘，但是这里依然存在当消息刚准备存储在磁盘的时候但是还没有存储完，消息还在缓存的一个间隔点。此时并没有真正写入磁盘。持久性保证并不强，但是对于我们的简单任务队列而言，这已经绰绰有余了。如果需要更强有力的持久化策略，参考后边课件发布确认章节。

### 3.3.4.不公平分发

​	在最开始的时候我们学习到RabbitMQ分发消息采用的轮训分发，但是在某种场景下这种策略并不是很好，比方说有两个消费者在处理任务，其中有个消费者1处理任务的速度非常快，而另外一个消费者2处理速度却很慢，这个时候我们还是采用轮训分发的化就会到这处理速度快的这个消费者很大一部分时间处于空闲状态，而处理慢的那个消费者一直在干活，这种分配方式在这种情况下其实就不太好，但是RabbitMQ并不知道这种情况它依然很公平的进行分发。

​	为了避免这种情况，我们可以设置参数channel.basicQos(1):

![image-20220224213029700](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224213029700.png)

![image-20220224215221616](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224215221616.png)

![image-20220224213615491](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224213615491.png)

​	意思就是如果这个任务我还没有处理完或者我还没有应答你，你先别分配给我，我目前只能处理一个任务，然后rabbitmq就会把该任务分配给没有那么忙的那个空闲消费者，当然如果所有的消费者都没有完成手上任务，队列还在不停的添加新任务，队列有可能就会遇到队列被撑满的情况，这个时候就只能添加新的worker或者改变其他存储任务的策略。

### 3.3.5.预取值

​	本身消息的发送就是异步发送的，所以在任何时候，channel上肯定不止只有一个消息,另外来自消费者的手动确认本质上也是异步的。因此这里就存在一个未确认的消息缓冲区，因此希望开发人员能**限制此缓冲区的大小，以避免缓冲区里面无限制的未确认消息问题。**这个时候就可以通过使用bsic.qos方法设置“预取计数”值来完成的。该值定义**通道上允许的未确认消息的最大数量。**一旦数量达到配置的数量，RabbitMQ将停止在通道上传递更多消息，除非至少有一个未处理的消息被确认。

​	例如，假设在通道上有未确认的消息5、6、7、8，并且通道的预取计数设置为4，此时RabbitMQ将不会在该通道上再传递任何消息，除非至少有一个未应答的消息被ack。比方说tag=6这个消息刚刚被确认ACK,RabbitMQ将会感知这个情况到并再发送一条消息。消息应答和QoS预取值对用户吞吐量有重大影响。通常，增加预取将提高向消费者传递消息的速度。**虽然自动应答传输消息速率是最佳的，但是，在这种情况下已传递但尚未处理的消息的数量也会增加，从而增加了消费者的RAM消耗（随机存取存储器）**

​	应该小心使用具有无限预处理的自动确认模式或手动确认模式，消费者消费了大量的消息如果没有确认的话，会导致消费者连接节点的
内存消耗变大，所以找到合适的预取值是一个反复试验的过程，不同的负载该值取值也不同100到300范围内的值通常可提供最佳的吞吐量，并且不会给消费者带来太大的风险。预取值为1是最保守的。当然这将使吞吐量变得很低，特别是消费者连接延迟很严重的情况下，特别是在消费者连接等待时间较长的环境中。对于大多数应用来说，稍微高一点的值将是最佳的。

![image-20220224215932881](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224215932881.png)

![image-20220224220003608](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224220003608.png)

# 4.发布确认

## 4.1.发布确认原理

![image-20220224221224200](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224221224200.png)

​	生产者将信道设置成confirm模式，一旦信道进入confirm模式，所有在该信道上面发布的消息都将会被指派一个唯一的ID(从1开始)，一旦消息被投递到所有匹配的队列之后，broker就会发送一个确认给生产者（包含消息的唯一ID),这就使得生产者知道消息已经正确到达目的队列了，如果消息和队列是可持久化的，那么确认消息会在将消息写入磁盘之后发出，broker回传给生产者的确认消息中delivery-tag域包含了确认消息的序列号，此外broker也可以设置basic.ack的multiple域，表示到这个序列号之前的所有消息都已经得到了处理。
confirm模式最大的好处在于他是异步的，一旦发布一条消息，生产者应用程序就可以在等信道返回确认的同时继续发送下一条消息，当消息最终得到确认之后，生产者应用便可以通过回调方法来处理该确认消息，如果RabbitMQ因为自身内部错误导致消息丢失，就会发送一条nack消息，生产者应用程序同样可以在回调方法中处理该nack消息。

## 4.2.发布确认的策略

### 4.2.1.开启发布确认的方法

​	发布确认默认是没有开启的，如果要开启需要调用方法confirmSelect,每当你要想使用发布确认，都需要在channel上调用该方法

![image-20220224221159719](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220224221159719.png)

### 4.2.2.单个确认发布

​	这是一种简单的确认方式，它是一种**同步确认发布**的方式，也就是发布一个消息之后只有它被确认发布，后续的消息才能继续发布，**waitForConfirmsOrDie(long)**这个方法只有在消息被确认的时候才返回，如果在指定时间范围内这个消息没有被确认那么它将抛出异常。

​	这种确认方式有一个最大的缺点就是：**发布速度特别的慢**，因为如果没有确认发布的消息就会阻塞所有后续消息的发布，这种方式最多提供每秒不超过数百条发布消息的吞吐量。当然对于某些应用程序来说这可能已经足够了。

```java
//批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());
            //单个确认
            boolean flag = channel.waitForConfirms();
            if (flag){
                System.out.println("消息发布成功!");
            }
        }
//singlePublishConfirmMessage();//发布1000条数据的时间:  2192ms
```



### 4.2.3.批量确认发布

​	上面那种方式非常慢，与单个等待确认消息相比，先发布一批消息然后一起确认可以极大地提高吞吐量，当然这种方式的缺点就是：当发生故障导致发布出现问题时，不知道是哪个消息出现问题了，我们必须将整个批处理保存在内存中，以记录重要的信息而后重新发布消息。当然这种方案仍然是同步的，也一样阻塞消息的发布。

```java
//批量确认消息大小
        int batchSize = 100;
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());

            if (i % batchSize == 0){
                boolean flag = channel.waitForConfirms();
                if (flag){
                    System.out.println("消息发布成功!");
                }
            }
        }
//batchPublishConfirmMessage();//发布1000条数据的时间:  110ms
```



### 4.2.4.异步确认发布

​	异步确认虽然编程逻辑比上两个要复杂，但是性价比最高，无论是可靠性还是效率都没得说，他是利用回调函数来达到消息可靠性传递的，这个中间件也是通过函数回调来保证是否投递成功，下面就让我们来详细讲解异步确认是怎么实现的。

![image-20220225112051795](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225112051795.png)

```java
//准备消息的监听器
        //消息确认成功
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("确认的消息:" + deliveryTag);
        };
        //消息确认失败
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("未确认的消息:" + deliveryTag);
        };
        channel.addConfirmListener(ackCallback, nackCallback);

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());
        }
//System.out.println("未确认的消息:" + deliveryTag);
```

### 4.2.5.如何处理异步未确认消息

​	最好的解决的解决方案就是把未确认的消息放到一个基于内存的能被发布线程访问的队列，比如说用ConcurrentLinkedQueue这个队列在confirm callbacks与发布线程之间进行消息的传递。

```java
/**
         * 线程安全有序的一个哈希表适用于高并发的情沉下
         * 轻松的将序号与消息进行关联
         * 轻松批量删除条目只要给到序号
         * 支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long, String> outStandingConfirm = new ConcurrentSkipListMap<>();
        //准备消息的监听器
        //消息确认成功
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            //删除已确认的消息,剩下的就是未确认的消息
            if (multiple){
                ConcurrentNavigableMap<Long, String> confirmed = outStandingConfirm.headMap(deliveryTag);
                confirmed.clear();
            }else {
                outStandingConfirm.remove(deliveryTag);
            }
            System.out.println("确认的消息:" + deliveryTag);
        };
        //消息确认失败
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String message = outStandingConfirm.get(deliveryTag);
            System.out.println("未确认消息："+message+"::::::未确认的消息Tag:" + deliveryTag);
        };
        channel.addConfirmListener(ackCallback, nackCallback);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", name, null, message.getBytes());
            //记录所有发送的消息
            outStandingConfirm.put(channel.getNextPublishSeqNo(), message);
        }
```



### 4.2.6.以上3种发布确认速度对比

单独发布消息

​	同步等待确认，简单，但吞吐量非常有限。

批量发布消息

​	批量同步等待确认，简单，合理的吞吐量，一旦出现问题但很难推断出是那条消息出现了问题。

异步处理：

​	最佳性能和资源使用，在出现错误的情况下可以很好地控制，但是实现起来稍微难些

# 5.交换机

​	在上一节中，我们创建了一个工作队列。我们假设的是工作队列背后，每个任务都恰好交付给一个消费者（工作进程）。在这一部分中，我们将做一些完全不同的事情-我们将消息传达给多个消费者。这种模式称为"发布/订阅”.

​	为了说明这种模式，我们将构建一个简单的日志系统。它将由两个程序组成：第一个程序将发出日志消息，第二个程序是消费者。其中我们会启动两个消费者，其中一个消费者接收到消息后把日志存储在磁盘，另外一个消费者接收到消息后把消息打印在屏幕上，事实上第一个程序发出的日志消息将广播给所有消费者者

## 5.1. Exchanges

### 5.1.1. Exchanges概念

​	RabbitMQ消息传递模型的核心思想是：生产者生产的消息从不会直接发送到队列。实际上，通常生产者甚至都不知道这些消息传递传递到了哪些队列中。

​	相反，生产者只能将消息发送到交换机(exchange),交换机工作的内容非常简单，一方面它接收来自生产者的消息，另一方面将它们推入队列。交换机必须确切知道如何处理收到的消息。是应该把这些消息放到特定队列还是说把他们到许多队列中还是说应该丢弃它们。这就的由交换机的类型来决定。

![image-20220225213825838](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225213825838.png)

### 5.1.2. Exchanges的类型

总共有以下类型：
直接(direct),主题(topic),标题(headers),扇出(fanout)

### 5.1.3.无名 exchange

​	在本教程的前面部分我们对exchange一无所知，但仍然能够将消息发送到队列。之前能实现的原因是因为我们使用的是默认交换，我们通过空字符串("")进行标识。

![image-20220225214032464](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225214032464.png)

​	第一个参数是交换机的名称。空字符串表示默认或无名称交换机：消息能路由发送到队列中其实是由routingKey(bindingkey)绑定key指定的，如果它存在的话

## 5.2.临时队列

​	之前的章节我们使用的是具有特定名称的队列（还记得hello和ack_queue吗？）。队列的名称对我们来说至关重要-我们需要指定我们的消费者去消费哪个队列的消息。每当我们连接到Rabbit时，我们都需要一个全新的空队列，为此我们可以创建一个具有随机名称的队列，或者能让服务器为我们选择一个随机队列名称那就更好了。其次一旦我们断开了**消费者的连接，队列将被自动删除。**

创建临时队列的方式如下：s

```java
String queueName = channel.queueDeclare().getQueue();
```

创建出来之后长成这样：

![image-20220225214638522](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225214638522.png)

## 5.3.绑定(bindings)

​	什么是bingding呢，binding其实是exchange和queue之间的桥梁，它告诉我们exchange和那个队列进行了绑定关系。比如说下面这张图告诉我们的就是X与Q1和Q2进行了绑定

![image-20220225214914182](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225214914182.png)

![image-20220225215028408](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225215028408.png)

## 5.4.Fanout(扇出)

### 5.4.1. Fanout(扇出) 介绍

​	Fanout这种类型非常简单。正如从名称中猜到的那样，它是将接收到的所有消息广播到它知道的**所有队列中**。系统中默认有些exchange类型

![image-20220225215239629](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225215239629.png)

### 5.4.2. Fanout实战

![image-20220225215423249](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225215423249.png)

Logs和临时队列的绑定关系如下图

![image-20220225215452167](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225215452167.png)

## 5.5. Direct (直接) exchange

### 5.5.1.回顾

​	在上一节中，我们构建了一个简单的日志记录系统。我们能够向许多接收者广播日志消息。在本节我们将向其中添加一些特别的功能比方说我们只让某个消费者订阅发布的部分消息。例如我们只把严重错误消息定向存储到日志文件（以节省磁盘空间），同时仍然能够在控制台上打印所有日志消息。

​	我们再次来回顾一下什么是bindings,绑定是交换机和队列之间的桥梁关系。也可以这么理解：**队列只对它绑定的交换机的消息感兴趣。**绑定用参数：routing Key来表示也可称该参数为binding key,创建绑定我们用代码：channel.queueBind(queueName, EXCHANGE_NAME, "routingKey");**绑定之后的意义由其交换类型决定。**

### 5.5.2.Direct exchange介绍

​	上一节中的我们的日志系统将所有消息广播给所有消费者，对此我们想做一些改变，例如我们希望将日志消息写入磁盘的程序仅接收严重错误(errors),而不存储哪些警告(warning)或信息(info)日志 消息避免浪费磁盘空间。Fanout这种交换类型并不能给我们带来很大的灵活性-它只能进行无意识的广播，在这里我们将使用direct这种类型来进行替换，这种类型的工作方式是，消息只去到它绑定的routingKey队列中去。

![image-20220226212952056](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220226212952056.png)

在上面这张图中，我们可以看到X绑定了两个队列，绑定类型是direct。队列Q1绑定键为orange,队列Q2绑定键有两个：一个绑定键为black,.另一个绑定键为green.

在这种绑定情况下，生产者发布消息到exchange.上，绑定键为orange的消息会被发布到队列Q1。绑定键为black,green和的消息会被发布到队列Q2,其他消息类型的消息将被丢弃。

### 5.5.3.多重绑定

![image-20220226213111299](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220226213111299.png)

当然如果exchange的绑定类型是direct,但是**它绑定的多个队列的key如果都相同，**在这种情况下虽然绑定类型是direct但是它**表现的就和fanout有点类似了，**就跟广播差不多，如上图所示。

### 5.5.4.实战

![image-20220226213229874](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220226213229874.png)

![image-20220226213240339](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220226213240339.png)

## 5.6. Topics (主题)

### 5.6.1.之前类型的问题

​	在上一个小节中，我们改进了日志记录系统。我们没有使用只能进行随意广播的fanout交换机，而是使用了direct交换机，从而有能实现有选择性地接收日志。

​	尽管使用diect交换机改进了们的系统，但是它仍然存在局限性-比方说我们想接收的日志类型有info.base和info.advantage,某个队列只想info.base的消息，那这个时候direct就办不到了。这个时候就只能使用topic类型

### 5.6.2. Topic的要求

​	发送到类型是topic交换机的消息的routing_key不能随意写，必须满足一定的要求，它必须是**一个单词列表，以点号分隔开**。这些单词可以是任意单词，比如说："stock.usd.nyse","nyse.vmw",'quick.orange.rabbit".这种类型的。当然这个单词列表最多不能超过255个字节。
​	在这个规则列表中，其中有两个替换符是大家需要注意的

- ***(星号)可以代替一个单词**
- **#(井号)可以替代零个或多个单词**

### 5.6.3 .Topic匹配案例

下图绑定关系如下

Q1->绑定的是
	中间带orange带3个单词的字符串(* .orange. *)
Q2->绑定的是
	最后一个单词是rabbit的3个单词( * . *.rabbit)
	第一个单词是lazy的多个单词(lazy.#)

![image-20220226215802142](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220226215802142.png)

上图是一个队列绑定关系图，我们来看看他们之间数据接收情况是怎么样的

|                          |                                            |
| :----------------------: | :----------------------------------------: |
|   quick.orange.rabbit    |             被队列Q1、Q2接收到             |
|   lazy.orange.elephant   |             被队列Q1、Q2接收到             |
|     quick.orange.fox     |               被队列Q1接收到               |
|      lazy.brown.fox      |               被队列Q2接收到               |
|     lazy.pink.rabbit     |    虽然满足两个绑定但只被队列Q2接收一次    |
|     quick.brown.fox      | 不匹配任何绑定不会被任何队列接收到会被丢弃 |
| quick.orange.male.rabbit |      是四个单词不匹配任何绑定会被丢弃      |
|  lazy.range.male.rabbit  |             是四个单词但匹配Q2             |

### 5.6.4.实战

![image-20220226220533528](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220226220533528.png)

# 6.死信队列

## 6.1.死信的概念

​	先从概念解释上搞清楚这个定义，死信，顾名思义就是无法被消费的消息，字面意思可以这样理解，一般来说，producer将消息投递到broker或者直接到queue里了，consumer从queue取出消息进行消费，但某些时候由于特定的原因**导致queue中的某些消息无法被消费**，这样的消息如果没有后续的处理，就变成了死信，有死信自然就有了死信队列。

​	应用场景：为了保证订单业务的消息数据不丢失，需要使用到RabbitMQ的死信队列机制，当消息消费发生异常时，将消息投入死信队列中还有比如说：用户在商城下单成功并点击去支付后在指定时间未支付时自动失效

## 6.2.死信的来源

消息TTL过期

队列达到最大长度（队列满了，无法再添加数据到mq中）

消息被拒绝(basic.reject或basic.nack)并且requeue=false

## 6.3.死信实战

### 6.3.1代码架构图

![image-20220301213110899](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220301213110899.png)

```java
//声明队列
        //普通
        Map<String, Object> arguments = new HashMap<>();
        //过期时间,10s=10000ms
        //arguments.put("x-message-ttl",10000);
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信RoutKey
        arguments.put("x-dead-letter-routing-key","lisi");

        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, arguments);
        //死信
        channel.queueDeclare(DEAD_QUEUE_NAME, false, false, false, null);
```

![image-20220301222909758](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220301222909758.png)

### 6.3.2消息TTL过期

```java
Channel channel = RabbitMQUtil.getChannel();
        //设置TTL time to live
        AMQP.BasicProperties properties =
                new AMQP.BasicProperties()
                        .builder()
                        .expiration("10000")
                        .build();

        for (int i = 0; i < 10; i++) {
            String info = "info : " + i;
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties, info.getBytes());
        }
```

### 6.3.3.队列达到最大长度

```java
 Map<String, Object> arguments = new HashMap<>();
        //过期时间,10s=10000ms
        //arguments.put("x-message-ttl",10000);
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信RoutKey
        arguments.put("x-dead-letter-routing-key","lisi");
        //设置队列最大长度
        arguments.put("x-max-length",5);

        channel.queueDeclare(NORMAL_QUEUE_NAME, false, false, false, arguments);
```

### 6.3.4.消悬被拒

```java
DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody());
            if (msg.contains("5")){
                System.out.println(msg + " : 被拒绝的消息");
                channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
            }else {
                System.out.println("Consumer01接受的消息：   "+msg);
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }
        };
```

# 7延迟队列

## 7.1延迟队列慨念

延时队列队列内部是有序的，最重要的特性就体现在它的延时属性上，延时队列中的元素是希望在指定时间到了以后或之前取出和处理，简单来说，延时队列就是用来存放需要在指定时间被处理的元素的队列。

## 7.2延迟队列使用场景

1. 订单在十分钟之内未支付则自动取消
2. 新创建的店铺，如果在十天内都没有上传过商品，则自动发送消息提醒。
3. 用户注册成功后，如果三天内没有登陆则进行短信提醒。
4. 用户发起退款，如果三天内没有得到处理则通知相关运营人员。
5. 预定会议后，需要在预定的时间点前十分钟通知各个与会人员参加会议

这些场景都有一个特点，需要在某个事件发生之后或者之前的指定时间点完成某一项任务，如：发生订单生成事件，在十分钟之后检查该订单支付状态，然后将未支付的订单进行关闭；看起来似乎使用定时任务，一直轮询数据，每秒查一次，取出需要被处理的数据，然后处理不就完事了吗？如果数据量比较少，确实可以这样做，比如：对于“如果账单一周内未支付则进行自动结算”这样的需求，如果对于时间不是严格限制，而是宽松意义上的一周，那么每天晚上跑个定时任务检查一下所有未支付的账单，确实也是一个可行的方案。但对于数据量比较大，并且时效性较强的场景，如：“订单十分钟内未支付则关闭“，短期内未支付的订单数据可能会有很多，活动期间甚至会达到百万甚至干万级别，对这么庞大的数据量仍旧使用轮询的方式显然是不可取的，很可能在一秒内无法完成所有订单的检查，同时会给数据库带来很大压力，无法满足业务要求而且性能低下。

![image-20220302213446556](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220302213446556.png)

![image-20220302213634522](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220302213634522.png)

## 7.3. RabbitMQ中的TTL

### 7.3.1.消息设道TTL

### 7.3.2.队列设置TTL

## 7.4.整合springboot

### 7.4.1.创建项目

### 7.4.2.添动加依赖

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
<!--        RabbitMq依赖-->
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
            <version>2.6.4</version>
        </dependency>
<!--        JSON转换-->
        <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.79</version>
        </dependency>
<!--    测试-->
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>3.0.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>3.0.0</version>
        </dependency>
<!--        RabbitMQ测试依赖-->
        <!-- https://mvnrepository.com/artifact/org.springframework.amqp/spring-rabbit-test -->
        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit-test</artifactId>
            <version>2.4.2</version>
            <scope>test</scope>
        </dependency>

    </dependencies>
```

### 7.4.3.修改配置文件

```yaml
spring:
  rabbitmq:
    host: 192.168.229.130
    username: "admin"
    password: "123"
    port: 5672
```

### 7.4.4.添加Swagger配置类

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author ye
 * createDate 2022/3/2  22:01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();
    }


    public ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("rabbitMQ接口文档")
                .description("本文档描述了RabbitMQ微服务接口定义")
                .version("1.0")
                .contact(new Contact("HelloWord","www.atguigu.com", "18577787964@sina.cn"))
                .build();
    }
}
```



## 7.5.队列TTL

### 7.5.1.代码架构图

创建两个队列QA和QB,两者队列TTL分别设置为10S和40S,然后在创建一个交换机X和死信交换机Y,它们的类型都是direct,创建一个死信队列QD,它们的绑定关系如下：

![image-20220303212007884](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220303212007884.png)

### 7.5.2.配置文件类代码

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/3  21:22
 */
@Configuration
public class TTLQueue {
    //普通交换机名称
    public static final String X_QUEUE = "X";
    //死信交换机名称
    public static final String Y_QUEUE = "Y";
    //普通队列名称
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    //死信队列名称
    public static final String QUEUE_D = "QD";

    //声明X交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_QUEUE);
    }
    //声明Y交换机
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_QUEUE);
    }

    //声明普通队列TTL为10s
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> arguments = new HashMap<>();
        //设置TTL  单位ms
        arguments.put("x-message-ttl",10000);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_QUEUE);
        //设置死信routing key
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_A).withArguments(arguments).build();
    }
    //声明普通队列TTL为40s
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> arguments = new HashMap<>();
        //设置TTL  单位ms
        arguments.put("x-message-ttl",40000);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_QUEUE);
        //设置死信routing key
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }
    //声明死信队列
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(QUEUE_D).build();
    }

    //绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    //绑定
    @Bean
    public Binding queueBBindingY(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    //绑定
    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }
}
```

### 7.5.3.消息生产者代码

```java
package com.example.rabbitmq.springbootrabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * author ye
 * createDate 2022/3/3  21:49
 * 发送延迟消息
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class sendMessageController {
    //开始发消息
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable("message") String message) {
        log.info("当前时间:{}, 发送一条信息给了两个队列:{}",new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl为10s的队列");
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl为40s的队列");
    }
}
```



### 7.5.4.消息消费者代码

```java
package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * author ye
 * createDate 2022/3/3  22:03
 * 消费者
 */
@Slf4j
@Component
public class deadLetterQueueConsumer {
    //接受消息
    @RabbitListener(queues = "QD")
    public void receiceD(Message message, Channel channel) throws Exception{
        String msg = new String(message.getBody());
        log.info("当前时间：{}，接受到死信队列消息:{}",new Date(), msg);
    }
}
```

发送一个请求https://localhost:8080/ttl/sendMessage/helloword

![image-20220303224608720](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220303224608720.png)

第一条消息在10S后变成了死信消息，然后被消费者消费掉，第二条消息在40S之后变成了死信消息，然后被消费掉，这样一个延时队列就打造完成了。

不过，如果这样使用的话，岂不是**每增加一个新的时间需求，就要新增一个队列**，这里只有10S和40S两个时间选项，如果需要一个小时后处理，那么就需要增加TTL为一个小时的队列，如果是预定会议室然后提前通知这样的场景，岂不是要增加无数个队列才能满足需求？

## 7.6.延时队列优化

在这里新增了一个队列QC,绑定关系如下，该队列不设置TL时间在这里新增了一个队列QC,绑定关系如下，该队列不设置TL时间

### 7.6.1.代码架构图

![image-20220304212026582](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304212026582.png)

### 7.6.2.配置文件类代码

```java
public static final String QUEUE_C = "QC";

    @Bean("queueC")
    public Queue queueC() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_EXCHANGE);
        //设置死信routing key
        arguments.put("x-dead-letter-routing-key","YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }

    @Bean
    public Binding queueCBinding(@Qualifier("queueC") Queue queueC,
                                 @Qualifier("xExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueC).to(exchange).with("XC");
    }
```

### 7.6.3消息生产者代码

```java
//发消息和TTL
    @GetMapping("/sendExpirationMessage/{message}/{ttltime}")
    public void sendExpirationMessage(@PathVariable("message") String message, @PathVariable("ttltime") String ttltime) {
        log.info("当前时间:{}, 发送一条时长为:{}ms,信息给了一个队列:{}",new Date(), ttltime, message);
        rabbitTemplate.convertAndSend("X", "XC", message ,msg -> {
            //设置TTL
            msg.getMessageProperties().setExpiration(ttltime);
            return msg;
        });
    }
```

http://localhost:8080/ttl/sendExpirationMessage/hello/10000

http://localhost:8080/ttl/sendExpirationMessage/word/1000

![image-20220304214437994](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304214437994.png)

看起来似乎没什么问题，但是在最开始的时候，就介绍过如果使用在消息属性上设置TTL的方式，消息可能并不会按时“死亡“，因为RabbitMQ**只会检查第一个消息是否过期**，如果过期则丢到死信队列，**如果第一个消息的延时时长很长，而第二个消息的延时时长很短，第二个消息并不会优先得到执行。**

## 7.7.Rabbitmg插件实现延迟队列

​	上文中提到的问题，确实是一个问题，如果不能实现在消息粒度上的TL,并使其在设置的TTL时间及时死亡，就无法设计成一个通用的延时队列。那如何解决呢，接下来我们就去解决该问题。

### 7.7.1.安装延时队列插件

官网下载https://rabbitmq.com/community-plugins.html

下载**rabbitmq_delayed_message_exchange**插件

![image-20220304220508437](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304220508437.png)

然后解压放置到RabbitMQ的插件目录。进入RabbitMQ的安装目录下的plgins目录，执行下面命冷让该插件生效，然后重启RabbitMQ

```shell
/usr/lib/rabbitmq/lib/rabbitmq_server-3.8.8/plugins

rabbitmq-plugins enable rabbitmq_delayed_message_exchange
```

![image-20220304220647295](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304220647295.png)

![image-20220304220806608](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304220806608.png)

![image-20220304220946867](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304220946867.png)

### 7.7.2.代码架构图

在这里新增了一个队列delayed.queue,一个自定义交换机delayed.exchange,绑定关系如下：

![image-20220304221110913](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304221110913.png)

### 7.7.3.配置文件类代码

在我们自定义的交换机中，这是一种新的交换类型，该类型消息支持延迟投递机制消息传递后并不会立即投递到目标队列中，而是存储在nesia(一个分布式数据系统)表中，**当达到投递时间时，才投递到目标队列中**。

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/4  22:12
 */
@Configuration
public class DelayedQueueConfig {
    //队列
    public static final String DELAYED_QUEUE = "delayed_queue";
    //交换机
    public static final String DELAYED_EXCHANGE = "delayed_exchange";
    //routingKey
    public static final String DELAYED_ROUTINGKEY = "delayed_routing";

    @Bean
    public Queue queue(){
        return new Queue(DELAYED_QUEUE);
    }

    //声明交换机
    @Bean
    public CustomExchange delayedExchange(){
        /**
         * String name,         交换机的名称
         * String type,         交换机的类型
         * boolean durable,     是否需要持久化
         * boolean autoDelete,  是否需要自动删除
         * Map<String, Object>   其它参数
         */
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", false, false, arguments);
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue,
                           @Qualifier("delayedExchange") CustomExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAYED_ROUTINGKEY).noargs();

    }
}
```



### 7.7.4消息生产者代码

```java
public void sendDelayedMessage(@PathVariable("message") String message, @PathVariable("delayedTime") Integer delayedTime) {
        log.info("当前时间:{}, 发送一条时长为:{}ms,信息给了一个delayed队列:{}",new Date(), delayedTime, message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE, DelayedQueueConfig.DELAYED_ROUTINGKEY, message , msg -> {
            //设置TTL
            msg.getMessageProperties().setDelay(delayedTime);
            return msg;
        });
    }
```



### 7.7.5消息消费者代码

```java
package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.example.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * author ye
 * createDate 2022/3/4  22:26
 * 基于插件的延迟
 */
@Slf4j
@Component
public class DelayedConsumer {
    //监听消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void receiveDelayedMessage(Message message){
        String msg = new String(message.getBody());
        log.info("当前时间：{}，接受到delayed死信队列消息:{}",new Date(), msg);
    }
}
```



http://localhost:8080/ttl/sendDelayedMessage/hello/20000

http://localhost:8080/ttl/sendDelayedMessage/word/1000

![image-20220304224129379](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220304224129379.png)

## 总结:

延时队列在需要延时处理的场景下非常有用，使用RabbitMQ来实现延时队列可以很好的利用RabbitMQ的特性，如：消息可靠发送、消息可靠投递、死信队列来保障消息至少被消费一次以及未被正确处理的消息不会被丢弃。另外，通过RabbitMQ集群的特性，可以很好的解决单点故障问题，不会因为单个节点挂掉导致延时队列不可用或者消息丢失。

当然，延时队列还有很多其它选择，比如利用Java的DelayQueue,利用Redis的zset,利用Quartz或者利用kafka的时间轮，这些方式各有特点，看需要适用的场景

# 8.发布确认高级

在生产环境中由于一些不明原因，导致rabbitmq重启，在RabbitMQ重启期间生产者消息投递失败导致消息丢失，需要手动处理和恢复。于是，我们开始思考，如何才能进行RabbitMQ的消息可靠投递呢？特别是在这样比较极端的情况，RabbitMQ集群不可用的时候，无法投递的消息该如何处理呢：

## 8.1.发布确认springboot版本(交换机出问题)

### 8.1.1确认机制方案

![image-20220305211411657](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220305211411657.png)

### 8.1.2.代码架构图

![image-20220305211453070](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220305211453070.png)

### 8.1.3.配置文件

在配置文件当中需要添加

```properties
spring.rabbitmq.publisher-confirm-type=correlated
```



```yaml
spring:
  rabbitmq:
    host: 192.168.229.130
    username: "admin"
    password: "123"
    port: 5672
    publisher-confirm-type: correlated
```

- NONE

	禁用发布确认模式，是默认值

- CORRELATED

	发布消息成功到交换器后会触发回调方法

- SIMPLE

	经测试有两种效果，其一效果和CORRELATED值一样会触发回调方法，

	其二在发布消息成功后使用rabbitTemplate调用waitForConfirms或waitForConfirmsOrDie方法等待broker节点返回发送结果，根据返回结果来判定下一步的逻辑，要注意的点是waitForConfirmsOrDie方法如果返回false则会关闭channel,则接下来无法发送消息到broker

### 8.1.4添加配置类

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/3/5  21:21
 * 发布确认高级
 */
@Configuration
public class ConfirmConfig {
    //交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    //队列
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    //RoutingKey
    public static final String CONFIRM_ROUTING_KEY = "confirm_routing_key";

    //声明交换机
    @Bean
    public DirectExchange confirmExchange(){
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    //声明队列
    @Bean
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding bindingConfirm(@Qualifier("confirmQueue") Queue queue,
                           @Qualifier("confirmExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY);
    }
}
```



### 8.1.5.消息生产者

```java
package com.example.rabbitmq.springbootrabbitmq.controller;

import com.example.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/3/5  21:29
 * 发布确认高级,测试确认
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //发消息
    @GetMapping("/sendMessage/{message}/{message1}")
    public void sendMessage(@PathVariable("message") String message1, @PathVariable("message1") String message2) {
        CorrelationData correlationData = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY, message1, correlationData);
        log.info("发送的消息为:{}",message1);

        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME + "123",
                ConfirmConfig.CONFIRM_ROUTING_KEY, message2, correlationData2);
        log.info("发送的消息为:{}",message2);
    }
}
```



### 8.1.6.回调接口

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author ye
 * createDate 2022/3/5  21:41
 * 交换机确认回调方法
 *  1.发消息交换机接收到了回调
 *      1.1 correlationData保存回调消息的ID及相关信息
 *      1.2交换机收到消息ack=true
 *      1.3 cause,nu11
 *  2.发消息交换机接收失败了回调
 *      2.1 correlationData保存回调消息的ID及相关信息
 *      2.2交换机收到消息ack=fa1se
 *      2.3 cause失败的原因
 */
@Component
@Slf4j
public class MyConfirmCallBack implements RabbitTemplate.ConfirmCallback{

    //注入
    @Autowired
    RabbitTemplate rabbitTemplate;

    //注入
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack){
            log.info("交换机已收到ID为：{}的消息",id);
        }
        else {
            log.info("交换机未收到ID为：{}消息，原因是：{}", id, cause);
        }
    }
```



### 8.1.7.消息消费者

```java
package com.example.rabbitmq.springbootrabbitmq.consumer;

import com.example.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/5  21:34
 */
@Slf4j
@Component
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveMessage(Message message){
        log.info("接收到的消息:{}", new String(message.getBody()));
    }
}
```



### 8.1.8结果分析

http://localhost:8080/confirm/sendMessage/hello/hello2

![image-20220313203201944](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220313203201944.png)

## 8.2.回退消息(路由或队列出问题)

**在仅开启了生产者确认机制的情况下，交换机接收到消息后，会直接给消息生产者发送确认消息，如果发现该消息不可路由，那么消息会被直接丢弃，此时生产者是不知道消息被丢弃这个事件的。**那么如何让无法被路由的消息帮我想办法处理一下？最起码通知我一声，我好自己处理啊。通过设置mandatory参数可以在当消息传递过程中不可达目的地时将消息返回给生产者。

### 8.2.1. Mandatory参数

```yaml
spring:
  rabbitmq:
    publisher-returns: true
```

### 8.2.2.消息生产者代码

```java
package com.example.rabbitmq.springbootrabbitmq.controller;

import com.example.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/3/5  21:29
 * 发布确认高级,测试确认
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //发消息
    @GetMapping("/sendMessage/{message}/{message1}")
    public void sendMessage(@PathVariable("message") String message1, @PathVariable("message1") String message2) {
        CorrelationData correlationData = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY, message1, correlationData);
        log.info("发送的消息为:{}",message1);

        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY+"123", message2, correlationData2);
        log.info("发送的消息为:{}",message2);
    }
}
```

### 8.2.3.回调接口

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author ye
 * createDate 2022/3/5  21:41
 * 交换机确认回调方法
 *  1.发消息交换机接收到了回调
 *      1.1 correlationData保存回调消息的ID及相关信息
 *      1.2交换机收到消息ack=true
 *      1.3 cause,nu11
 *  2.发消息交换机接收失败了回调
 *      2.1 correlationData保存回调消息的ID及相关信息
 *      2.2交换机收到消息ack=fa1se
 *      2.3 cause失败的原因
 */
@Component
@Slf4j
public class MyConfirmCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{

    //注入
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack){
            log.info("交换机已收到ID为：{}的消息",id);
        }
        else {
            log.info("交换机未收到ID为：{}消息，原因是：{}", id, cause);
        }
    }
    //可以在当消息传递过程中不可达目的地时将消息返回给生产者
    //只有失败的才回退
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息{},被交换机{},回退了，原因是{}",returnedMessage.getReplyText(),returnedMessage.getExchange(), returnedMessage.getMessage());
    }
}
```

### 8.2.4结果分析

http://localhost:8080/confirm/sendMessage/hello/hello2

![image-20220313204111506](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220313204111506.png)

## 8.3.备份交换机

​	有了mandatory参数和回退消息，我们获得了对无法投递消息的感知能力，有机会在生产者的消息无法被投递时发现并处理。但有时候，我们并不知道该如何处理这些无法路由的消息，最多打个日志，然后触发报警，再来手动处理。而通过日志来处理这些无法路由的消息是很不优雅的做法，特别是当生产者所在的服务有多台机器的时候，手动复制日志会更加麻烦而且容易出错。而且设置mandatory参数会增加生产者的复杂性，需要添加处理这些被退回的消息的逻辑。如果既不想丢失消息，又不想增加生产者的复杂性，该怎么做呢？前面在设置死信队列的文章中，我们提到，可以为队列设置死信交换机来存储那些处理失败的消息，可是这些不可路由消息根本没有机会进入到队列，因此无法使用死信队列来保存消息。在RabbitMQ中，有一种备份交换机的机制存在，可以很好的应对这个问题。什么是备份交换机呢？备份交换机可以理解为RabbitMQ中交换机的“备胎”，当我们为某一个交换机声明一个对应的备份交换机时，就是为它创建一个备胎，当交换机接收到一条不可路由消息时，将会把这条消息转发到备份交换机中，由备份交换机来进行转发和处理，通常备份交换机的类型为Fanout，这样就能把所有消息都投递到与其绑定的队列中，然后我们在备份交换机下绑定一个队列，这样所有那些原交换机无法被路由的消息，就会都进入这个队列了。当然，我们还可以建立一个报警队列，用独立的消费者来进行监测和报警。

### 8.3.1.代码架构图

![image-20220313085622604](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220313085622604.png)

### 8.3.2.修改配置类

```java
package com.example.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/3/5  21:21
 * 发布确认高级
 */
@Configuration
public class ConfirmConfig {
    //交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    //队列
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    //RoutingKey
    public static final String CONFIRM_ROUTING_KEY = "confirm_routing_key";
    //备份交换机
    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";
    //备份队列
    public static final String BACKUP_QUEUE_NAME = "backup_queue";
    //报警
    public static final String WARNING_QUEUE_NAME = "warning_queue";

    //声明交换机
    @Bean
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME)
                .durable(true)
                .withArgument("alternate-exchange",BACKUP_EXCHANGE_NAME)
                .build();
    }

    //声明队列
    @Bean
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding bindingConfirm(@Qualifier("confirmQueue") Queue queue,
                           @Qualifier("confirmExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY);
    }

    //备份交换机
    @Bean
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean
    public Queue backupQueue(){
        return new Queue(BACKUP_QUEUE_NAME);
    }

    @Bean
    public Queue warnQueue(){
        return new Queue(WARNING_QUEUE_NAME);
    }

    //绑定备份队列
    @Bean
    public Binding backupBinding(@Qualifier("backupQueue") Queue queue, @Qualifier("backupExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    //绑定警告队列
    @Bean
    public Binding warnBinding(@Qualifier("warnQueue") Queue queue, @Qualifier("backupExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
```



### 8.3.3报警消费者

http://localhost:8080/confirm/sendMessage/hello/hello2

![image-20220313195706276](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220313195706276.png)

mandatory参数与备份交换机可以一起使用的时候，如果两者同时开启，消息究竟何去何从？谁优先级高，经过上面结果显示答案是**备份交换机优先级高。**

# 9.RabbitMQ其他知识点

## 9.1.幂等性

### 9.1.1概念

​	用户对于同一操作发起的一次请求或者多次请求的结果是一致的，不会因为多次点击而产生了副作用。举个最简单的例子，那就是支付，用户购买商品后支付，支付扣款成功，但是返回结果的时候网络异常，此时钱已经扣了，用户再次点击按钮，此时会进行第二次扣款，返回结果成功，用户查询余额发现多扣钱了，流水记录也变成了两条。在以前的单应用系统中，我们只需要把数据操作放入事务中即可，发生错误立即回滚，但是再响应客户端的时候也有可能出现网络中断或者异常等等

### 9.1.2.消息重复消费

​	消费者在消费MQ中的消息时，MQ已把消息发送给消费者，消费者在给MQ返回ack时网络中断，故MQ未收到确认信息，该条消息会重新发给其他的消费者，或者在网络重连后再次发送给该消费者，但实际上该消费者已成功消费了该条消息，造成消费者消费了重复的消息。

### 9.1.3解决思路

​	MQ消费者的幂等性的解决一般使用全局ID或者写个唯一标识比如时间戳或者UUID或者订单消费者消费MQ中的消息也可利用MQ的该id来判断，或者可按自己的规则生成一个全局唯一id,每次消费消息时用该id先判断该消息是否已消费过。

### 9.1.4消费端的幂等性保障

​	在海量订单生成的业务高峰期，生产端有可能就会重复发生了消息，这时候消费端就要实现幂等性，这就意味着我们的消息永远不会被消费多次，即使我们收到了一样的消息。业界主流的幂等性有两种操作：a:唯一ID+指纹码机制，利用数据库主键去重，b:利用redis的原子性去实现

### 9.1.5.唯一ID+指纹码机制

​	指纹码：我们的一些规则或者时间戳加别的服务给到的唯一信息码，它并不一定是我们系统生成的，基本都是由我们的业务规则拼接而来，但是一定要保证唯一性，然后就利用查询语句进行判断这个id是否存在数据库中，优势就是实现简单就一个拼接，然后查询判断是否重复；劣势就是在高并发时，如果是单个数据库就会有写入性能瓶颈当然也可以采用分库分表提升性能，但也不是我们最推荐的方式。

### 9.1.6.Redis原子性

利用redis执行setnx命令，天然具有幂等性。从而实现不重复消费

## 9.2.优先级队列

### 9.2.1.使用场景

​	在我们系统中有一个**订单催付**的场景，我们的客户在天猫下的订单，淘宝会及时将订单推送给我们，如果在用户设定的时间内未付款那就会给用户推送一条短信提醒，很简单的一个功能对吧，但是，tmall商家对我们来说，肯定是要分大客户和小客户的对吧，比如像苹果，小米这样大商家一年起码能给我们创造很大的利润，所以理应当然，他们的订单必须得到优先处理，而曾经我们的后端系统是使用redis来存放的定时轮询，大家都知道redis只能用List做一个简简单单的消息队列，并不能实现一个优先级的场景，所以订单量大了后采用RabbitMQ进行改造和优化，如果发现是大客户的订单给一个相对比较高的优先级，否则就是默认优先级。

### 9.2.2如何添加

页面中添加：

![image-20220314084033914](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314084033914.png)

或：

代码中添加：

````java
//官方允许是0-255之间，此处设置10允许优化级范围为0-10，不要设置过大浪费CPU与内存
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", 10);
        channel.queueDeclare(QUEUE_NAME, false, false, true, arguments);
````

![image-20220314084555925](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314084555925.png)

![image-20220314090059133](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314090059133.png)

设置消息优先级：

```java
AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();
```



<font color='red'>注意事项：</font>

`要让队列实现优先级需要做的事情有如下事情：队列需要设置为优先级队列，消息需要设置消息的优先级，消费者需要等待消息已经发送到队列中才去消费因为，这样才有机会对消息进行排序

### 9.2.3实战

```java
package com.example.G09Test;

import com.example.Utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/14  8:48
 */
public class Producer {
    public static final String QUEUE_NAME = "hello-world-09";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtil.getChannel();
        //官方允许是0-255之间，此处设置10允许优化级范围为0-10，不要设置过大浪费CPU与内存
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", 10);
        channel.queueDeclare(QUEUE_NAME, false, false, true, arguments);

        for (int i = 0; i < 10; i++) {
            String message = "info:" + i;
            if (i == 5){
                AMQP.BasicProperties properties =
                        new AMQP.BasicProperties().builder().priority(5).build();
                channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
            }else {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
        }
        System.out.println("消息发送完毕！");
    }
}
```



![image-20220314090724722](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314090724722.png)

## 9.3.惰性队列

惰性队列：消息保存在内存中还是在磁盘上

正常情况：消息是保存在内存中

惰性队列：消息是保存在磁盘中

### 9.3.1.使用场景

​	RabbitMQ从3.6.0版本开始引入了惰性队列的概念。惰性队列会尽可能的将消息存入磁盘中，而在消费者消费到相应的消息时才会被加载到内存中，它的一个重要的设甘目标是能够支持更长的队列，即支持更多的消息存储。当消费者由于各种各样的原因（比如消费者下线、宕机亦或者是由于维护而关闭等）而致使长时间内不能消费消息造成堆积时，惰性队列就很有必要了。

​	默认情况下，当生产者将消息发送到RabbitMQ的时候，队列中的消息会尽可能的存储在内存之中，这样可以更加快速的将消息发送给消费者。即使是持久化的消息，在被写入磁盘的同时也会在内存中驻留一份备份。当RabbitMQ需要释放内存的时候，会将内存中的消息换页至磁盘中，这个操作会耗费较长的时间，也会阻塞队列的操作，进而无法接收新的消息。虽然RabbitMQ的开发者们一直在升级相关的算法，但是效果始终不太理想，尤其是在消息量特别大的时候。

### 9.3.2两种模式

​	队列具备两种模式：default和lazy。默认的为default模式，在3.6.0之前的版本无需做任何变更。lazy模式即为惰性队列的模式，可以通过调用channel..queue Declare方法的时候在参数中设置，也可以通过Policy的方式设置，如果一个队列同时使用这两种方式设置的话，那么**Policy的方式具备更高的优先级**。如果要通过声明的方式改变已有队列的模式的话，那么只能先删除队列，然后再重新声明一个新的。

![image-20220314091144430](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314091144430.png)

```java
Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-queue-mode", "lazy");
        channel.queueDeclare(QUEUE_NAME, false, false, true, arguments);
```



### 9.3.3.内存开销对比

![image-20220314091204540](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314091204540.png)

在发送1百万第消息，每条消息大概占1KB的情况下，普通队列占用内存是1.2GB，惰性队列仅仅用1.5MB

# 10.RabbitMQ集群

## 10.1. clustering

### 10.1.1.使用集群的原因

最开始我们介绍了如何安装及运行RabbitMQ服务，不过这些是单机版的，无法满足目前真实应用的要求。如果RabbitMQ服务器遇到内存崩溃、机器掉电或者主板故障等情况，该怎么办？单台RabbitMQ服务器可以满足每秒1000条消息的吞吐量，那么如果应用需要RabbitMQ服务满足每秒10万条消息的吞吐量呢？购买昂贵的服务器来增强单机RabbitMQ务的性能显得捉襟见肘，搭建一个RabbitMQ集群才是解决实际问题的关键.

### 10.1.2搭建步骤

1.修改3台机器的主机名称

`vim /etc/hostname`

2.配置各个节点的hosts文件，让各个节点都能互相识别对方

`vim /etc/hosts`

![image-20220314095527736](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314095527736.png)

3.以确保各个节点的cookie文件使用的是同一个值

在node上执行远程操作命令

```shell
scp /var/lib/rabbitmq/.erlang.cookie root@node1:/var/lib/rabbitmq/.erlang.cookie
scp /var/lib/rabbitmq/.erlang.cookie root@node2:/var/lib/rabbitmq/.erlang.cookie
```

![image-20220314095951280](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314095951280.png)

4.启动RabbitMQ服务，顺带启动Erlang虚拟机和RbbitMQ应用服务（在三台节点上分别执行以下命令)

```shell
rabbitmq-server -detached
```

5.在节点2执行

```shell
rabbitmqctl stop_app
#(rabbitmgctl stop会将Erlang虚拟机关闭，rabbitmgctl stop_app只关闭RabbitMQ服务)
rabbitmqctl reset
rabbitmqctl join_cluster rabbit@node
rabbitmqctl start_app #(只启动应用服务)
```

6.在节点3执行

```shell
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl join_cluster rabbit@node1
rabbitmqctl start_app
```

7.集群状态

```shell
rabbitmqctl cluster_status
```

![image-20220314111641172](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314111641172.png)

8.需要重新设置用户

创建账号

```shell
rabbitmqctl add_user admin 123
```

设置用户角色

```shell
rabbitmqctl set_user_tags admin administrator
```

设置用户权限

```shell
rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*"
```

![image-20220314111932377](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314111932377.png)

9.解除集群节点(node2和node3机器分别执行)

```shell
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app
rabbitmqctl cluster_status
rabbitmqctl forget_cluster_node rabbit@node2(node1机器上执行) # 让node1忘掉node2
```



## 10.2.镜像队列

### 10.2.1.使用镜像的原因

​	如果RabbitMQ集群中只有一个Broker节点，那么该节点的失效将导致整体服务的l临时性不可用，并且也可能会导致消息的丢失。可以将所有消息都设置为持久化，并且对应队列的durable属性也设置为true,但是这样仍然无法避免由于缓存导致的问题：因为消息在发送之后和被写入磁盘井执行刷盘动作之间存在一个短暂却会产生问题的时间窗。通过publisher confirm机制能够确保客户端知道哪些消息己经存入磁盘，尽管如此，一般不希望遇到因单点故障导致的服务不可用。

​	引入镜像队列(Mirror Queue)的机制，可以将队列镜像到集群中的其他Broker节点之上，如果集群中的一个节点失效了，队列能自动地切换到镜像中的另一个节点上以保证服务的可用性。

### 10.2.2搭建步骤

1.启动三台集群节点

2.随便找一个节点添加policy

![image-20220314112918462](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314112918462.png)

![image-20220314113403706](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314113403706.png)

**就算整个集群只剩下一台机器了依然能消费队列里面的消息说明队列里面的消息被镜像队列传递到相应机器里面了**

## 10.3. Haproxy+Keepalive实现高可用、负载均衡

### 10.3.1.整体架构图

![image-20220314164905881](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314164905881.png)

### 10.3.2. Haproxy实现负载均衡

​	HAProxy提供高可用性、负载均衡及基于TCPHTTP应用的代理，支持虚拟主机，它是免费、快速并且可靠的一种解决方案，包括Twitter,Reddit,StackOverflow,GitHub在内的多家知名互联网公司在使用。HAProxy实现了一种事件驱动、单一进程模型，此模型支持非常大的井发连接数。

### 10.3.3.搭建步骤

### 10.3.4. Keepalived实现双机（主从）

### 10.3.5搭建步骤

## 10.4. Federation Exchange

### 10.4.1.使用它的原因

​	(broker北京)，(broker深圳)彼此之间相距甚远，网络延迟是一个不得不面对的问题。有一个在北凉的业务(Client北京)需要连接(broker北京)，向其中的交换器exchangeA发送消息，此时的网络延迟很小，(Client:北京)可以迅速将消息发送至exchangeA中，就算在开启了publisherconfirm机制或者事务机制的情况下，也可以讯迅速收到确认信息。此时又有个在深圳的业务(Client深圳)需要向exchangeA发送消息，那么(Client深圳)(broker北京)之间有很大的网终延迟，(Client深圳)将发送消息至exchangeA会经历一定的诞迟，尤其是在开启了publisherconfirm机制或者事务机制的情况下，(Client深圳)会等待很长的延迟时间来接收(bokr北京)的确认信息，进而必然造成这条发送线程的性能降低，甚至造成一定程度上的阻塞。

​	将业务(Client深圳部署到北京的机房可以解决这个问题，但是如果(Client深圳)调用的另些服务都部署在深圳，那么又会引发新的时延问题，总不见得将所有业务全部部署在一个机房，那么容灾又何以实现？这里使用Federation插件就可以很好地解决这个问题

### 10.4.2搭建步骤

1.需要保证每台节点单独运行

2.在每台机器上开启federation相关插件

```shell
rabbitmq-plugins enable rabbitmg_federation
rabbitmq-plugins enable rabbitmq_federation_management
```

![image-20220314171651895](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314171651895.png)

3.原理图（先运行consumer在node 2创建fed_exchange)

![image-20220314165927136](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314165927136.png)

4. 在downstream(node 2)配置upstream(node 1)

	![image-20220314171255719](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314171255719.png)

	![image-20220314171552864](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314171552864.png)

5. 成功的标志

	![image-20220314171842397](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314171842397.png)

## 10.5. Federation Queue

### 10.5.1.使用它的原因

​	联邦队列可以在多个Broker节点（或者集群）之间为单个队列提供均衡负载的功能。一个联邦队列可以连接一个或者多个上游队列(upstream queue),并从这些上游队列中获取消息以满足本地消费者消费消息的需求。

### 10.5.2搭建步骤

1.原理图

![image-20220314203004497](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314203004497.png)

2.添加upstream(同上)

3.添加policy

![image-20220314203746593](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314203746593.png)

## 10.6. Shovel

### 10.6.1.使用它的原因

​	Federation具备的数据转发功能类以，Shovel够可靠、持续地从一个Broker中的队列（作为源端，即source)拉取数据并转发至另一个Broker中的交换器（作为目的端，即destination)。作为源端的队列和作为目的端的交换器可以同时位于同一个Broker,也可以位于不同的Broker上。Shovel可以翻译为"铲子"，是一种比较形象的比喻，这个"铲子"可以将消息从一方"铲子"另一方。Shovel行为就像优秀的客户端应用程序能够负责连接源和目的地、负责消息的读写及负责连接失败问题的处理。

### 10.6.2搭建步骤

1.开启插件（需要的机器都开启）

```shell
rabbitmq-plugins enable rabbitmg_shovel
rabbitmq-plugins enable rabbitmq_shovel_management
```

![image-20220314204149584](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314204149584.png)

2.原理图（在源头发送的消息直接回进入到目的地队列）

![image-20220314203945420](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314203945420.png)

3.添加shovel源和目的地

![image-20220314204331605](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220314204331605.png)

