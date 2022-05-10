# 1.Docker复杂安装详说

```shell
docker 运行centos显示连不上网络
centos 7 docker 启动了一个web服务 但是启动时报

WARNING: IPv4 forwarding is disabled. Networking will not work.

网上查询了下 需要做如下配置

解决办法：

vi /etc/sysctl.conf

或者

vi /usr/lib/sysctl.d/00-system.conf

添加如下代码：

net.ipv4.ip_forward=1

重启network服务

systemctl restart network

查看是否修改成功

sysctl net.ipv4.ip_forward

如果返回为“net.ipv4.ip_forward = 1”则表示成功了
```



## 1.1.安装mysql主从复制

### 1.1.1主从复制原理======默认你懂

### 1.1.2主从搭建步骤

#### 1.1.2.1新建主服务器容器实例3307

```shell
docker run --name mysql-master -p 3307:3306 --privileged=true -v /mysqldata/mysql-master/log:/var/log/mysql -v /mysqldata/mysql-master/data:/var/lib/mysql -v /mysqldata/mysql-master/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```

#### 1.1.2.2进入/mysqldata/mysql-master//conf目录下新建my.cnf

```properties
[mysqld]
##设置server_id,同一局域网中需要唯一
server_id=101
##指定不需要同步的数据库名称
binlog-ignore-db=mysql
##开启二进制日志功能
log-bin=mall-mysql-bin
##设置二进制日志使用内存大小（事务）
binlog_cache_size=1M
##设置使用的二进制日志格式(mixed,statement,row)
binlog_format=mixed
##二进制日志过期清理时间。默认值为0，表示不自动清理。
expire_logs_days=7
##跳过主从复制中遇到的所有错误或指定类型的错误，避免slave端复制中断。
##如：1062错误是指一些主键重复，1032错误是因为主从数据库数据不一致
slave_skip_errors=1062
```

#### 1.1.2.3修改完配置后重启master实例

```shell
docker restart mysql-master
```

#### 1.1.2.4进入mysql--master容器

![image-20220309102543261](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309102543261.png)

#### 1.1.2.5master容器实例内创建数据同步用户

```mysql
#创建用户
mysql> CREATE USER 'slave'@'%' IDENTIFIED BY '123456';

#授权
mysql> GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';
```

#### 1.1.2.6新建从服务器容器实例3308

```shell
docker run --name mysql-slave -p 3308:3306 --privileged=true -v /mysqldata/mysql-slave/log:/var/log/mysql -v /mysqldata/mysql-slave/data:/var/lib/mysql -v /mysqldata/mysql-slave/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```

#### 1.1.2.7进入/mysqldata/mysql--slave/conf目录下新建my.cnf

````properties
[mysqld]
##设置server_id,同一局域网中需要唯一
server_id=102
##指定不需要同步的数据库名称
binlog-ignore-db=mysql
##开启二进制日志功能，以备Slave作为其它数据库实例的Master时使用
log-bin=mall-mysql-slave1-bin
#设置二进制日志使用内存大小（事务）
binlog_cache_size=1M
##设置使用的二进制日志格式(mixed,statement,row)
binlog_format=mixed
##二进制日志过期清理时间。默认值为0，表示不自动清理。
expire_logs_days=7
##跳过主从复制中遇到的所有错误或指定类型的错误，避免slav端复制中断。
##如：1062错误是指一些主键重复，1032错误是因为主从数据库数据不一致
slave_skip_errors=1062
##relay_log配置中继日志
relay_log=mall-mysql-relay-bin
##log_slave_updates表示slave将复制事件写进自己的二进制日志
log_slave_updates=1
##slave设置为只读（具有super权限的用户除外）
read_only=1
````

#### 1.1.2.8修改完配置后重启slave实例

```shell
docker restart mysql-slave
```

#### 1.1.2.9在主数据库中查看主从同步状态

```mysql
mysql> show master status;
+-----------------------+----------+--------------+------------------+-------------------+
| File                  | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+-----------------------+----------+--------------+------------------+-------------------+
| mall-mysql-bin.000001 |      617 |              | mysql            |                   |
+-----------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

```

#### 1.1.2.10进入mysql-slave容器

```shell
docker exec -it mysql-slave /bin/bash
```

#### 1.1.2.11在从数据库中配置主从复制

```
master_host:主数据库的iP地址：
master_port:主数据库的运行端口；
master_user:在主数据库创建的用于同步数据的用户账号；
master_password:在主数据库创建的用于同步数据的用户密码；
master_log_fle:指定从数据库要复制数据的日志文件，通过查看主数据的状态，获取File参数；
master_log_pos:指定从数据库从哪个位置开始复制数据，通过查看主数据的状态，获取Position参数；
master_connect_retry:连接失败重试的时间间隔，单位为秒。
```

```mysql
change master to master_host='宿主机ip',master_user='slave',master_password='123456',master_port=3307,master_log_file='mall-mysql-bin.000001',master_log_pos=617,master_connect_retry=30;

#master_log_pos为主数据库状态中的Position
change master to master_host='192.168.229.129',master_user='slave',master_password='123456',master_port=3307,master_log_file='mall-mysql-bin.000001',master_log_pos=617,master_connect_retry=30;
```

#### 1.1.2.12在从数据库中查看主从同步状态

```mysql
show slave status \G;

mysql> show slave status \G;
*************************** 1. row ***************************
               Slave_IO_State: 
                  Master_Host: 192.168.229.129
                  Master_User: slave
                  Master_Port: 3307
                Connect_Retry: 30
              Master_Log_File: mall-mysql-bin.000001
          Read_Master_Log_Pos: 617
               Relay_Log_File: mall-mysql-relay-bin.000001
                Relay_Log_Pos: 4
        Relay_Master_Log_File: mall-mysql-bin.000001
             Slave_IO_Running: No
            Slave_SQL_Running: No
              Replicate_Do_DB: 
          Replicate_Ignore_DB: 
           Replicate_Do_Table: 
       Replicate_Ignore_Table: 
      Replicate_Wild_Do_Table: 
  Replicate_Wild_Ignore_Table: 
                   Last_Errno: 0
                   Last_Error: 
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 617
              Relay_Log_Space: 154
              Until_Condition: None
               Until_Log_File: 
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File: 
           Master_SSL_CA_Path: 
              Master_SSL_Cert: 
            Master_SSL_Cipher: 
               Master_SSL_Key: 
        Seconds_Behind_Master: NULL
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error: 
               Last_SQL_Errno: 0
               Last_SQL_Error: 
  Replicate_Ignore_Server_Ids: 
             Master_Server_Id: 0
                  Master_UUID: 
             Master_Info_File: /var/lib/mysql/master.info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: 
           Master_Retry_Count: 86400
                  Master_Bind: 
      Last_IO_Error_Timestamp: 
     Last_SQL_Error_Timestamp: 
               Master_SSL_Crl: 
           Master_SSL_Crlpath: 
           Retrieved_Gtid_Set: 
            Executed_Gtid_Set: 
                Auto_Position: 0
         Replicate_Rewrite_DB: 
                 Channel_Name: 
           Master_TLS_Version: 
1 row in set (0.00 sec)

ERROR: 
No query specified
```

![image-20220309104454802](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309104454802.png)

#### 1.1.2.13在从数据库中开启主从同步

```mysql
mysql> start slave;
Query OK, 0 rows affected (0.01 sec)
```

#### 1.1.2.14查看从数据库状态发现已经同步

![image-20220309110818542](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309110818542.png)

#### 1.1.2.15主从复制测试

在主服务器新建表，看从服务器是否同步

![image-20220309111204979](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309111204979.png)

![image-20220309111216171](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309111216171.png)

## 1.2.安装redis集群（大厂面试题第4季.分布式存储案例真题）

### 1.2.1. cluster(集群)模式-docker版哈希槽分区进行亿级数据存储

#### 1.2.1.1面试题

1~2亿条数据需要缓存，请问如何设计这个存储案例

回答

单机单台100%不可能，肯定是分布式存储，用redis?如何落地？

上述问题阿里P6~P7工程案例和场景设计类必考题目，一般业界有3种解决方案

- 哈希取余分区

	![image-20220309125456578](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309125456578.png)

	2亿条记录就是2亿个k,V,我们单机不行必须要分布式多机，假设有3台机器南成一个集群，用户每次读写操作都是根据公式：

	hash(key)%N个机器台数，计算出哈希值，用来决定数据映射到哪一个节点上。

	优点：

	简单粗暴，直接有效，只需要预估好数据规划好节点，例如3台、8台、10台，就能保证一段时间的数据支撑。使用Hash算法让固定的一部分请求落到同一台服务器上，这样每台服务器固定处理一部分请求（并维护这些请求的信息），起到负载均衡+分而治之的作用。

	缺点：

	原来规划好的节点，进行扩容或者缩容就比较麻烦了额，不管扩缩，每次数据变动导致节点有变动，映射关系需要重新进行计算，在服务器个数固定不变时没有问题，如果需要弹性扩容或故障停机的情况下，原来的取模公式就会发生变化：Hash(key/3会变成Hash(key)?。此时地址经过取余运算的结果将发生很大变化，根据公式获取的服务器也会变得不可控。

	某个redis机器宕机了，由于台数数量变化，会导致hash取余全部数据重新洗牌。

- 一致性哈希算法分区

	一致性Hash算法背景

	一致性哈希算法在1997年由麻省理工学院中提出的，设计目标是为了解决分布式缓存数据变动和映射问题，某个机器宕机了，分母数量改变了，自然取余数不OK了

	3大步骤

	- 算法构建一致性哈希环

		一致性哈希算法必然有个hash函数并按照算法产生hash值，这个算法的所有可能哈希值会构成一个全量集，这个集合可以成为一个hash空间[0, 2^32 - 1],这个是一个线性空间，但是在算法中，我们通过适当的逻辑控制将它首尾相连(0=2^32)，这样让它逻辑上形成了一个环形空间。

		它也是按照使用取模的方法，前面笔记介绍的节点取模法是对节点（服务器）的数量进行取模。而一致性Hash算法是对2^32取模，**简单来说，致性Hash算法将整个哈希值空间组织成一个虚拟的圆环**，如假设某哈希函数H的值空间为0-2^32-1（即哈希值是一个32位无符号整形），整个哈希环如下图：整个空间按顺时针方向组织，圆环的正上方的点代表0,0点右侧的第一个点代表1，以此类推，2、3、4、…直到2^32-1，也就是说0点左则的第一个点代表2^32-1,0和2^32-1在零点中方向重合，我们把这个由2^32个点组成的圆环称为Hash环。

		![image-20220309130106195](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309130106195.png)

	- 服务器IP节点映射

		将集群中各个IP节点映射到环上的某一个位置。

		将各个服务器使用Hash进行一个哈希，具体可以选择服务器的IP或主机名作为关键字进行哈希，这样每台机器就能确定其在哈希环上的位置。假如4个节点NodeA、B、C、D,经过IP地址的哈希函数计算(hash(ip),使用IP地址哈希后在环空间的位置如下：

		![image-20220309130242676](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309130242676.png)

	- key落到服务器的落键规则

		当我们需要存储一个kv键值对时，首先计算key的hash值，hash(key),将这个key使用相同的函数Hash计算出哈希值并确定此数据在环上的位置，**从此位置沿环顺时针“行走”，**第一台遇到的服务器就是其应该定位到的服务器，并将该键值对存储在该节点上
		如我们有Object A、Object B、Object C、Object D四个数据对象，经过哈希计算后，在环空间上的位置如下：根据一致性Hash算法，数据A会被定为到Node A上，B被定为到Node B上，C被定为到Node C上，D被定为到Node D上

		![image-20220309130437244](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309130437244.png)

	- 优点

		一致性哈希算法的容错性

		容错性

		假设Node C:宕机，可以看到此时对象A、B、D不会受到影响，只有C对象被重定位到Node D。一般的，在一致性Hash算法中，如果一台服务器不可用，则受影响的数据仅仅是此服务器到其环空间中前一台服务器（即沿着逆时针方向行走遇到的第一台服务器）之间数据，其它不会受到影响。简单说，就是C挂了，受到影响的只是B、C之间的数据，并且这些数据会转移到D进行存储。

		![image-20220309130640601](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309130640601.png)

	- 致性哈希算法的扩展性

		扩展性

		数据量增加了，需要增加一台节点NodeX,X的位置在A和B之间，那收到影响的也就是A到X之间的数据，重新把A到X的数据录入到X上即可，不会导致hash取余全部数据重新洗牌。

		![image-20220309130827295](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309130827295.png)

	- 缺点

		致性哈希算法的数据倾斜问题

		Hash环的数据倾斜问题

		一致性Hash算法在服务**节点太少**时，容易因为节点分布不均匀而造成**数据倾斜**（被缓存的对象大部分集中缓存在某一台服务器上）问题，例如系统中只有两台服务器：

		![image-20220309131121375](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309131121375.png)

	- 小总结

		为了在节点数目发生改变时尽可能少的迁移数据

		将所有的存储节点排列在收尾相接的Hash环上，每个key在计算Hash后会顺时针找到临近的存储节点存放。而当有节点加入或退出时仅影响该节点在Hash环上顺时针相邻的后续节点。

		优点

		加入和删除节点只影响哈希环中顺时针方向的相邻的节点，对其他节点无影响。

		缺点

		数据的分布和节点的位置有关，因为这些节点不是均匀的分布在哈希环上的，所以数据在进行存储时达不到均匀分布的效果。

- 哈希槽分区

	1. 为什么出现

		一致性哈希算法的数据倾斜问题

		哈希槽实质就是一个数组，数组[0,2^14-1]形成hash slot空间。

	2. 能干什么

		解决均匀分配的问题，**在数据和节点之间又加入了一层，把这层称为哈希槽(slot)**，用于管理数据和节点之间的关系，现在就相当于节点上放的是槽，槽里放的是数据。

		![image-20220309131526547](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309131526547.png)

		槽解决的是粒度问题，相当于把粒度变大了，这样便于数据移动。

		哈希解决的是映射问题，使用ky的哈希值来计算所在的槽，便于数据分配。

	3. 多少个hash槽
		一个集群只能有16384个槽，编号0-16383(0-2^14-1)。这些槽会分配给集群中的所有主节点，分配策略没有要求。可以指定哪些编号的槽分配给哪个主节点。集群会记录节点和槽的对应关系。解决了节点和槽的关系后，接下来就需要对key求哈希值，然后对16384取余，余数是几key就落入对应的槽里。slot=CRC16(key)%16384。以槽为单位移动数据，因为槽的数目是固定的，处理起来比较容易，这样数据移动问题就解决了。

	4. 哈希槽计算
		Redis集群中内置了16384个哈希槽，redis会根据节点数量大致均等的将哈希槽映射到不同的节点。当需要在Redis集群中放置一个key-valuel时redis先对key使用crc16算法算出一个结果，然后把结果对16384求余数，这样每个key都会对应一个编号在0-16383之间的哈希槽，也就是映射到某个节点上。如下代码，key之A、B在Node2,key之C落在Node3上

		![image-20220309131722338](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309131722338.png)

		![image-20220309131730273](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309131730273.png)

### 1.2.2. 3主3从redis集群扩缩容配置案例架构说明

![image-20220309135727072](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309135727072.png)



#### 3主3从redis集群配置

**关闭防火墙+启动docker后台服务**

```shell
systemctl stop firewalld
```

**新建6个docker容器实例**

```shell
docker run -d --name redis-node-1 --net host --privileged=true -v /data/redis/share/redis-node-1:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6381

docker run -d --name redis-node-2 --net host --privileged=true -v /data/redis/share/redis-node-2:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6382

docker run -d --name redis-node-3 --net host --privileged=true -v /data/redis/share/redis-node-3:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6383

docker run -d --name redis-node-4 --net host --privileged=true -v /data/redis/share/redis-node-4:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6384

docker run -d --name redis-node-5 --net host --privileged=true -v /data/redis/share/redis-node-5:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6385

docker run -d --name redis-node-6 --net host --privileged=true -v /data/redis/share/redis-node-6:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6386
```

![image-20220309132738180](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309132738180.png)

**命令分步解释**

docker run 创建并运行docker?容器实例

--name redis-node-6 容器名字

--net host使用宿主机的P和端口，默认

--privileged=true 获取宿主机root用户权限

-v /data/redis/share/redis-node-6:/data 容器卷，宿主机地址：docker内部地址

redis:6.2.6-bullseye   redis镜像和版本号

--cluster-enabled yes开启redis集群

--appendonly yes 开启持久化

port 6386 redis.端口号

**进入容器redis-node-1并为6台机器构建集群关系**

```shell
#注意，进入docker:容器后才能执行一下命令，且注意自己的真实IP地址
redis-cli --cluster create 192.168.229.129:6381 192.168.229.129:6382 192.168.229.129:6383 192.168.229.129:6384 192.168.229.129:6385 192.168.229.129:6386 --cluster-replicas 1
#--cluste--replicas 1表示为每个master创建一个slave节点
```

![image-20220309134113153](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309134113153.png)

![image-20220309134201331](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309134201331.png)

**链接进入6381作为切入点，查看集群状态**

```shell
redis-cli -p 6381
cluster info
```

![image-20220309134530325](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309134530325.png)

```shell
cluster nodes
```

![image-20220309134611147](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309134611147.png)

#### 主从容错切换迁移案例

**数据读写存储**

启动6机构成的集群并通过exec进入

对6381新增两个key

![image-20220309135833749](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309135833749.png)

防止路由失效加参数-c并新增两个key

![image-20220309135956339](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309135956339.png)

查看集群信息

```shell
redis-cli --cluster chexk 192.168.229.129:6381
```

![image-20220309140158447](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309140158447.png)

**容错切换迁移**

![image-20220309140405125](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309140405125.png)

主6381和从机切换，先停止主机6381

再次查看集群信息

![image-20220309140650335](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309140650335.png)

先还原之前的3主3从

![image-20220309141107118](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309141107118.png)

redis-node-1恢复之后，会成为从机，而redis-node-6作为主机，要想恢复之前的状态，必须先停掉redis-node-6，在启动

![image-20220309141457153](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309141457153.png)

![image-20220309141524984](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309141524984.png)

查看集群状态

![image-20220309141617212](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309141617212.png)

#### 主从扩容案例

![image-20220309142116116](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309142116116.png)

新建6387、6388两个节点+新建后启动+查看是否8节点

```shell
docker run -d --name redis-node-7 --net host --privileged=true -v /data/redis/share/redis-node-7:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6387

docker run -d --name redis-node-8 --net host --privileged=true -v /data/redis/share/redis-node-8:/data redis:6.2.6-bullseye --cluster-enabled yes --appendonly yes --port 6388
```

进入6387容器实例内部 `docker exec -it redis-node-7 /bin/bash`

将新增的6387节点（空槽号）作为master节点加入原集群

- 将新增的6387作为master节点加入集群
- `redis-cli --cluster add-node 自己实际lP地址:6387 自己实际iP地址:6381`
- 6387就是将要作为master新增节点
- 6381就是原来集群节点里面的领路人，相当于6387拜拜6381的码头从而找到组织加入集群

```shell
redis-cli --cluster add-node 192.168.229.129:6387 192.168.229.129:6381
```

![image-20220309142840994](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309142840994.png)

检查集群情况第1次

![image-20220309143013576](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309143013576.png)

重新分派槽号

`命令redis-cli --cluster reshard IP地址:端口号`

![image-20220309143739150](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309143739150.png)

检查集群情况第2次

![image-20220309143850110](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309143850110.png)

为什么6387是3个新的区间，以前的还是连续？

重新分配成本太高，所以前3家各自匀出来一部分，从6381/6382/6383三个旧节点分别匀出1364个坑位给新节点6387



为主节点6387分配从节点6388

`命令：redis-cli --cluster add-node ip:新slave端口 ip:新master端口 --cluster-slave --cluster-master-id 新主机节点ID`

```shell
redis-cli --cluster add-node 192.168.229.129:6388 192.168.229.129:6387 --cluster-slave --cluster-master-id 31b06d5ba9b755e45b494ffceb34d59df38d48ba
#id为6387的id
```

检查集群情况第3次

![image-20220309144459149](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309144459149.png)

#### 主从缩容案例(删除6387，6388)

检查集群情况1获得6388的节点ID

> 399c0cae5103d5851e84b1298f3bd7237327385f 192.168.229.129:6388
>
> 31b06d5ba9b755e45b494ffceb34d59df38d48ba 192.168.229.129:6387

将6388删除,从集群中将4号从节点6388删除

```shell
redis-cli --cluster del-node 192.168.229.129:6388 399c0cae5103d5851e84b1298f3bd7237327385f
```

![image-20220309144914863](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309144914863.png)

将6387的槽号清空，重新分配，本例将清出来的槽号都给6381

```shell
redis-cli --cluster reshard IP地址:端口号
redis-cli --cluster reshard 192.168.229.129:6381
```

![image-20220309145112899](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309145112899.png)

检查集群情况第二次

![image-20220309145418843](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309145418843.png)

将6387删除

![image-20220309145509684](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309145509684.png)

检查集群情况第三次

![image-20220309145542926](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309145542926.png)

# 2. DockerFile解析

## 是什么

Dockerfile是用来构建Docker镜像的文本文件，是由一条条构建镜像所需的指令和参数构成的脚本。

### 概述

![image-20220309154729562](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309154729562.png)

### 官网

[Dockerfile reference | Docker Documentation](https://docs.docker.com/engine/reference/builder/)

### 构建三步骤

- 编写Dockerfile文件
- docker build命令构建镜像
- docker run依镜像运行容器实例

## DockerFile构建过程解析

### Dockerfile内容基础知识

- 每条保留字指令都必须为**大写字母**且后面要跟随至少一个参数
- 指令按照从上到下，顺序执行
- #表示注释
- 每条指令都会创建一个新的镜像层并对镜像进行提交

### Docker执行Dockerfile的大致流程

- docker从基础镜像运行一个容器
- 执行一条指令并对容器作出修改
- 执行类似docker commit的操作提交一个新的镜像层
- docker再基于刚提交的镜像运行一个新容器
- 执行dockerfile中的下一条指令直到所有指令都执行完成

### 小总结

从应用软件的角度来看，Dockerfile、Docker镜像与Docker容器分别代表软件的三个不同阶段，

- Dockerfile是软件的原材料
- Docker镜像是软件的交付品
- Docker容器则可以认为是软件镜像的运行态，也即依照镜像运行的容器实例
- Dockerfile面向开发，Docker镜像成为交付标准，Docker容器则涉及部署与运维，三者缺一不可，合力充当Docker体系的基石。

![image-20220309155327053](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309155327053.png)

1. Dockerfile,需要定义一个Dockerfile,Dockerfile定义了进程需要的一切东西。Dockerfile涉及的内容包括执行代码或者是文件、环境
	变量、依赖包、运行时环境、动态链接库、操作系统的发行版、服务进程和内核进程（当应用进程需要和系统服务和内核进程打交道，这时需要考虑如何设计namespace的权限控制)等等；
2. Docker镜像，在用Dockerfile定义一个文件之后，docker buildl时会产生一个Docker镜像，当运行Docker镜像时会真正开始提供服务；
3. Docker容器，容器是直接提供服务的。

## DockerFile常用保留字指令

参考tomcata8的dockerfile入门

[tomcat/Dockerfile at cf87b7c5e92939c351a0fe3082f51c5f366ab4e8 · docker-library/tomcat (github.com)](https://github.com/docker-library/tomcat/blob/cf87b7c5e92939c351a0fe3082f51c5f366ab4e8/10.1/jdk17/openjdk-bullseye/Dockerfile)

FROM

- 基础镜像，当前新镜像是基于哪个镜像的，指定一个已经存在的镜像作为模板，第一条必须是from

MAINTAINER （中文翻译：维护人员）

- 镜像维护者的姓名和邮箱地址

RUN

- 容器构建时需要运行的命令

- 两种格式
	- shell格式

	RUN<命令行命令>
	#<命令行命令>等同于，在终端操作的shell命令。

	`RUN yum install vim -y`

	- exec格式

	RUN [""可执行文件"，“参数1"，“参数2"]
	#例如：
	RUN ["./test.php","dev","offline"] 等价于 RUN ./test.php dev offline

- RUN是在docker build时运行

EXPOSE

- 当前容器对外暴露出的端口

WORKDIR

- 指定在创建容器后，终端默认登陆的进来工作目录，一个落脚点

USER

- 指定该镜像以什么样的用户去执行，如果都不指定，默认是root

ENV

- ENV MY_PATH /usr/mytest
- 这个环境变量可以在后续的任何RUN指令中使用，这就如同在命令前面指定了环境变量前缀一样；也可以在其它指令中直接使用这些环境变量，
- 比如：![image-20220309160623807](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309160623807.png)

ADD

- 将宿主机目录下的文件拷贝进镜像且会自动处理URL和解压tar压缩包

COPY

- 类似ADD,拷贝文件和目录到镜像中。
- 将从构建上下文目录中<源路径>的文件/目录复制到新的一层的镜像内的<目标路径>位置
- COPY src dest
- COPY ["src","dest"]
- <src 源路径>：源文件或者源目录
- <dest目标路径>：容器内的指定路径，该路径不用事先建好，路径不存在的话，会自动创建

VOLUME

- 容器数据卷，用于数据保存和持久化工作

CMD

- 指定容器启动后的要干的事情

	CMD指令的格式和RUN相似，也是两种格式：

	- shell

		格式：CMD <命令>

	- exec

		格式：CMD["可执行文件"，"参数1"，"参数2”]

	- 参数列表格式：CMD["参数1"，"参数2"]。在指定了ENTRYPOINT指令后，用CMD指定具体的参数。

- 注意

	- Dockerfile中可以有多个CMD指令，但只有最后一个生效，**CMD会被docker run之后的参数替换**

	- 参考官网Tomcat的dockerfile演示讲解

		![image-20220309161218009](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309161218009.png)

		![image-20220309161837211](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309161837211.png)

- 它和前面RUN命令的区别

	- CMD是在docker run时运行
	- RUN是在docker build!时运行

ENTRYPOINT

- 也是用来指定一个容器启动时要运行的命令

- 类似于CMD指令，但是ENTRYPOINT**不会被docker run,后面的命令覆盖**，而且这些**命令行参数会被当作参数送给ENTRYPOINT指令指定的程序**

- 命令格式和案例说明

	- 命令格式：`ENTRYPPOINT ["executeable", "<param1>", "<param2>",........]`

	- ENTRYPOINT可以和CMD一起用，一般是**变参**才会使用CMD,这里的CMD等于是在给ENTRYPOINT传参。

	- 当指定了ENTRYPOIN如后，CMD的含义就发生了变化，不再是直接运行其命令而是将CMD的内容作为参数传递给ENTRYPOINT:指令，他两个组合会变成`<ENTRYPPOINT> "<CMD>"`

	- 案例如下：假设已通过Dockerfile构建了nginx::test镜像：

		![image-20220309162349642](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309162349642.png)

		![image-20220309162401109](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309162401109.png)

![image-20220309162555916](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309162555916.png)

## 案例

### 自定义镜像mycentosjava8

要求：Centos7镜像具备vim+ifconfig+jdk8

JDK的下载镜像地址https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html

编写

准备编写Dockerfile文件

![image-20220309164638809](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309164638809.png)

```properties
FROM centos:7
#维护人员
MAINTAINER zhangyuye<18577787964@sina.cn   zzyybs@126.com>

ENV MYPATH /usr/local
WORKDIR $MYPATH

#安装vim编辑器
RUN yum -y install vim

#安装ifconfig命令查看网络IP
RUN yum -y install net-tools

#安装java8及lib库
RUN yum -y install glibc.i686
RUN mkdir /usr/local/java

#ADD是相对路径jar,把jdk-8u311-linux-x64.tar.gz添加到容器中，安装包必须要和Dockerfile文件在同一位置
ADD jdk-8u311-linux-x64.tar.gz /usr/local/java/

#配置java环境变量
ENV JAVA_HOME /usr/local/java/jdk1.8.0_311
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH
EXPOSE 80

CMD echo $MYPATH
CMD echo "success---------ok"
CMD /bin/bash
```

构建

docker build -t 新镜像名字:TAG .

注意，上面TAG后面有个空格，有个点

```shell
docker build -t mycentosjava:1.0.0 .
```

### 虚悬镜像

是什么

仓库名、标签都是<none>的镜像，俗称dangling image

![image-20220309211737069](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309211737069.png)

查看

```shell
docker image ls -f dangling=true
```



![image-20220309212054846](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309212054846.png)

删除

```shell
docker image prune
```



![image-20220309212150075](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309212150075.png)

# 3. Docker微服务实战

## 通过IDEA新建一个普通微服务模块

建Module

![image-20220310110744164](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310110744164.png)

改POM

```properties
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>docker-boot</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>docker-boot</name>
    <description>docker-boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
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
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

写YML

```yaml
server:
  port: 6001
```

主启动

```java
package com.example.dockerboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerBootApplication.class, args);
    }

}
```

业务类

```java
package com.example.dockerboot.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/3/9  21:29
 */
@RestController
public class HelloWorld {
    @Value("${server.port}")
    String port;

    @GetMapping("/order/docker")
    public String helloServer(){
        return "服务已启动：" + port + UUID.randomUUID().toString();
    }
}
```



## 通过Dockerfile发布微服务部署到docker容器

**IDEA工具里面搞定微服务jar包**

![image-20220309213604782](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309213604782.png)

![image-20220309213614936](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309213614936.png)

**编写Dockerfile**

将微服务jar包和Dockerfile文件上传到同一个目录下/mydocker

```properties
#基础镜像使用java
FROM java:8
#作者
MAINTAINER zhangyuye
#VOLUME指定临时文件目录为/tmp,在主机var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
#将jar包添加到容器中并更名
ADD docker-boot-0.0.1-SNAPSHOT.jar zzyy_docker.jar
#运行jar包
RUN bash -c 'touch /zzyy_docker.jar'
ENTRYPOINT ["java","-jar","/zzyy_docker.jar"]
#暴露6001端口作为微服务
EXPOSE 6001
```

**构建镜像**

`docker build -t docker-boot:1.0 .`

![image-20220309214530222](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309214530222.png)

**运行容器**

```shell
#关闭防火墙
systemctl stop firewalld
#重启docker服务
systenctl restart docker

docker run -d -p 6001:6001 docker-boot:1.0
```

**访问测试**

![image-20220309215103892](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309215103892.png)

或者

![image-20220309215135188](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309215135188.png)

# 4.Docker网络

## 是什么

### docker不启动，默认网络情况

在CentOS7的安装过程中如果有**选择相关虚拟化的的服务安装系统后**，启动网卡时会发现有一个以网桥连接的私网地址的virbrOl网卡(virbrO网卡：它还有一个固定的默认IP地址192.168.122.1)，是做虚拟机网桥的使用的，其作用是为连接其上的虚机网卡提供NAT访问外网的功能。

我们之前学习Linux安装，勾选安装系统的时候附带了libvirt服务才会生成的一个东西，如果不需要可以直接将libvirtd服务卸载，

yum remove libvirt-libs.x86_64

### docker启动后，网络情况

会产生一个名为dockero的虚拟网桥

![image-20220309215806818](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309215806818.png)



默认创建3大网络模式

![image-20220309215905550](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309215905550.png)

## 常用基本命令

![image-20220309215946366](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309215946366.png)



查看网络

docker network Is

查看网络源数据

docker network inspect XXX网络名字

删除网络

docker network rm XXX网络名字

## 能干嘛

### 容器间的互联和通信以及端口映射

### 容器IP变动时候可以通过服务名直接网络通信而不受到影响

## 网络模式

### 总体介绍

| 网络模式  | 简介                                                         |
| --------- | ------------------------------------------------------------ |
| bridge    | 为每一个容器分配、设置IP等，并将容器连接到一个docker0虚拟网桥，默认为该模式 |
| host      | 容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口 |
| none      | 容器有独立的Network namespace,但并没有对其进行任何网络设置，如分配veth pair和网桥连接，IP等。 |
| container | 新创建的容器不会创建自己的网卡和配置自己的IP,而是和一个指定的容器共享IP、瑞口范围等。 |

- bridge模式：使用--network bridge指定，默认使用docker0
- host模式：使用--network host指定
- none模式：使用--network none指定
- container模式：使用--network container:NAME或者容器ID指定

### 容器实例内默认网络IP生产规则

#### 说明

![image-20220309221025104](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309221025104.png)

![image-20220309221037800](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309221037800.png)

![image-20220309221046384](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220309221046384.png)





#### 结论 docker容器内部的ip是有可能会发生改变的

## 案例说明

### bridge

是什么

Docker服务默认会创建一个docker0网桥（其上有一个docker0内部接口），该桥接网络的名称为docker0,它在**内核层**连通了其他的物理或虚拟网卡，这就将所有容器和本地主机都放到**同一个物理网络**。Docker默认指定了docker0接口的IP地址和子网掩码，让主机和容器之间可以通过**网桥相互通信**。

![image-20220310094957598](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310094957598.png)

案例

1. Docker使用Linux桥接，在宿主机虚拟一个Docker容器网桥(docker0),Docker)启动一个容器时会根据Dockerl网桥的网段分配给容器一个IP地址，称为Container-lP,同时Docker网桥是每个容器的默认网关。因为在同一宿主机内的容器都接入同一个网桥，这样容器之间就能够通过容器的Container-lP直接通信。
2. docker run的时候，没有指定network的话默认使用的网桥模式就是bridge,使用的就是docker0。在宿主机.ifconfig,就可以看到docker0.和自己create的network(后面讲)eth0,eth1,eth2…代表网卡一，网卡二，网卡三…，lo代表127.0.0.1，即localhost,inet addr用来表示网卡的IP地址
3. 网桥docker0创建一对对等虚拟设备接口一个叫veth,另一个叫eth0,成对匹配。
	1. 整个宿主机的网桥模式都是docker(0,类似一个交换机有一堆接口，每个接口叫veth,在本地主机和容器内分别创建一个虚拟接口，并让他们彼此联通（这样一对接口叫veth pair);
	2. 每个容器实例内部也有一块网卡，每个接口叫eth0:
	3. docker0上面的每个veth匹配某个容器实例内部的eth0,两两配对，一一匹配。

通过上述，将宿主机上的所有容器都连接到这个内部网络上，两个容器在同一个网络下，会从这个网关下各自拿到分配的，此时两个容器的网络是互通的。

![image-20220310095133578](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310095133578.png)

![image-20220310095925474](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310095925474.png)

### host

直接使用宿主机的IP地址与外界进行通信，不再需要额外进行NAT转换。

容器将不会获得一个独立的Network Namespace而是和宿主机共用一个Network Namespace。**容器将不会虚拟出自己的网卡而是使用宿主机的IP和端口。**

![image-20220310100257826](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310100257826.png)

```shell
#有警告
docker run -d --network host -p 8083:8080 billygoo/tomcat8-jdk8

#无警告
docker run -d                -p 8083:8080 billygoo/tomcat8-jdk8
```



![image-20220310100135507](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310100135507.png)



**无之前的配对显示了，看容器实例内部**

![image-20220310100644139](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310100644139.png)



**没有设置-p的端口映射了，如何访问启动的tomcat83？？**

http:/宿年机IP:8080/

在CentOS里面用默认的火狐浏览器访问容器内的tomcat83看到访问成功，因为此时容器的IP借用主机的，所以容器共享宿主机网络P,这样的好处是外部主机与容器可以直接通信。**重复时则递增。**

### none

禁用网络功能，只有lo标识（就是127.0.0.1表示本地回环）

在none模式下，并不为Docker容器进行任何网络配置。也就是说，这个Docker容器没有网卡、IP、路由等信息，只有一个Io，需要我们自己为Docker容器添加网卡、配置IP等。

### container

新建的容器和己经存在的一个容器共享一个网络配置而不是和宿主机共享。新创建的容器不会创建自己的网卡，配置自己的1P,而是和一个指定的容器共享IP、端口范围等。同样，两个容器除了网络方面，其他的如文件系统、进程列表等还是隔离的。

![image-20220310101053437](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310101053437.png)

```shell
docker run -d -p 8085:8080 --name tomcat85 billygoo/tomcat8-jdk8

docker run -d -p 8086:8080 --network container:tomcat85 --name tomcat86 billygoo/tomcat8-jdk8
```

![image-20220310101310200](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310101310200.png)

相当于tomcata86和tomcata85公用同一个ip同一个端口，导致端口冲突

本案例用tomcat演示不合适。。。演示坑。。。。。。o(T一T)o

换一个镜像给大家演示，

**Alpine?操作系统是一个面向安全的轻型Linux发行版**

Alpine Linux是一款独立的、非商业的通用Liux发行版，专为追求安全性、简单性和资源效率的用户而设计。可能很多人没听说过这个Linux发行版本，但是经常用Docker的朋友可能都用过，因为他小，简单，安全而著称，所以作为基础镜像是非常好的一个选择，可谓是麻雀虽小但五脏俱全，镜像非常小巧，不到6M的大小，所以特别适合容器打包。

```shell
docker run -it                             --name alpine1 alpine /bin/sh
docker run -it --network container:alpine1 --name alpine2 alpine /bin/sh
```

![image-20220310101509727](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310101509727.png)



**关闭alpine1，查看alpine2**

![image-20220310101558149](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310101558149.png)

### 自定义网络

**Before**

```shell
docker run -d -p 8081:8080 --name tomcat81 billygoo/tomcat8-jdk8
docker run -d -p 8082:8080 --name tomcat82 billygoo/tomcat8-jdk8

上述成功启动并用docker exec进入各自容器实例内部
```

按照IP地址ping是OK的

![image-20220310102348582](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310102348582.png)

按照服务名ping结果

![image-20220310102439786](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310102439786.png)



**After**

自定义桥接网络，自定义网络默认使用的是桥接网络bridge

新建自定义网络

![image-20220310102644581](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310102644581.png)

新建容器加入上一步新建的自定义网络

```shell
docker run -d -p 8081:8080 --network test_network --name tomcat81 billygoo/tomcat8-jdk8
docker run -d -p 8082:8080 --network test_network --name tomcat82 billygoo/tomcat8-jdk8
```

互相ping测试

![image-20220310103002763](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310103002763.png)

# 5.Docker-compose容器编排

## 是什么

Docker--Compose是Docker'官方的开源项目，负责实现对Docker2容器集群的快速编排。

Compose是Docker公司推出的一个工具款件，可以管理多个Docker容器组成一个应用。你需要定义一个YAML格式的配置文件docker-compose.yml,**写好多个容器之间的调用关系**。然后，只要一个命令，就能同时启动/关闭这些容器

## 能干嘛

docker建议我们每一个容器中只运行一个服务，因为docker?容器本身占用资源极少，所以最好是将每个服务单独的分割开来但是这样我们又面临了一个问题？

如果我需要同时部署好多个服务，难道要每个服务单独写Dockerfile:然后在构建镜像，构建容器，这样累都累死了，所以docker'官方给我们提供了docker-compose多服务部署的工具

列如要实现一个Web微服务项目，除了Web服务容器本身，往往还需要再加上后端的数据库mysql服务容器，redis服务器，注册中心eureka,甚至还包括负载均衡容器等等。。。。。。

Compose允许用户通过**一个单独的docker-.compose.yml模板文件**(YAML格式)来**定义一组相关联的应用容器为一个项目(project).**。

可以很容易地用一个配置文件定义一个多容器的应用，然后使用一条指令安装这个应用的所有依赖，完成构建。Docker-Compose解决了容器与容器之间如何管理编排的问题。

## 去哪下

### 官网[Compose file version 3 reference | Docker Documentation](https://docs.docker.com/compose/compose-file/compose-file-v3/)

### 官网下载[Install Docker Compose | Docker Documentation](https://docs.docker.com/compose/install/)

### 安装步骤

```shell
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

docker-compose --version
```

### 卸载步骠

```shell
#To uninstall Docker Compose if you installed using curl:

sudo rm /usr/local/bin/docker-compose
```

## Compose核心概念

### 一文件

docker-compose.yml

### 两要素

服务(service)

一个个应用容器实例，比如订单微服务、库存微服务、mysql容器、nginx容器或者redis容器

工程(project)

一组关联的应用容器组成的**一个完整业务单元**，在docker-compose.yml文件中定义。

## Compose使用的三个步骤

1. 编写Dockerfile定义各个微服务应用并构建出对应的镜像文件
2. 使用docker-compose.yml定义一个完整业务单元，安排好整体应用中的各个容器服务。
3. 最后，执行docker--compose up命令来启动并运行整个应用程序，完成一键部署上线

## Compose常用命令

docker-compose -h                                      #查看帮助
docker-compose up                                     #启动所有docker--compose服务
docker-compose up -d                                #启动所有docker-compose服务并后台运行
docker-compose down                                #停止并删除容器、网络、卷、镜像。
docker-compose exec yml里面的服务id   #进入容器实例内部docker-compose exec docker-compose.yml文件中写的服务id /bin/bash
docker-compose ps	                                #展示当前docker--compose编排过的运行的所有容器
docker-compose top                                   #展示当前docker-compose编排过的容器进程
docker-compose logs yml里面的服务id   #查看容器输出日志
dokcer-compose config                             #检查配置
dokcer-compose config -q                         #检查配置，有问题才有输出
docker-compose restart                            #重启服务
docker-compose star                                 #启动服务
docker-compose stop                                #停止服务

## Compose编排微服务

### 改造升级微服务工程docker boot

以前的基础版

![image-20220310110953276](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310110953276.png)

SQL建表建库

```mysql
CREATE TABLE `t_user`(
    id INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码',
    `sex` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '性别O=女,1=男',
    `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认O不册删除，I册别除',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
)
ENGINE=INNODB AUTO_INCREMENT=1114 DEFAULT CHARSET=utf8 COMMENT='用户表';
```

改POM



写YML

主启动

业务类

maven package命令将微服务形成新的jar包并上传到Linux服务器/mydocker目录下

编写Dockerfile

```properties
#基础镜像使用java
FROM java:8
#作者
MAINTAINER zhangyuye
#VOLUME指定临时文件目录为/tmp,在主机/Nar/Iib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
#将jar包添加到容器中并更名为zzyy_docker.jar
ADD docker-boot-0.0.1-SNAPSHOT.jar docker_compose.jar
#运行jar包
RUN bash -c 'touch /docker_compose.jar'
ENTRYPOINT ["java","-jar","/docker_compose.jar"]
#暴露6001端口作为微服务
EXPOSE 6001
```

构建镜像

```shell
docker build -t dockercompose:1.0 .
```

### 不用Compose

单独的mysql容器实例

```shell
docker run -d -p 3306:3306 --privileged=true -v /zhangyuye/mysql/log:/var/log/mysql -v /zhangyuye/mysql/data:/var/lib/mysql -v /zhangyuye/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=123456 --name mysql5.7 mysql:5.7
```



```mysql
create database docker;

use docker;

CREATE TABLE `t_user`(
    id INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码',
    `sex` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '性别O=女,1=男',
    `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认O不册删除，I册别除',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
)
ENGINE=INNODB AUTO_INCREMENT=1114 DEFAULT CHARSET=utf8 COMMENT='用户表';
```

单独的redis容器实例

```shell
docker run -d --name redis -p 6379:6379 --privileged=true -v /app/redis/redis.conf:/etc/redis/redis.conf -v /app/redis/data:/data redis:6.2.6-bullseye redis-server /etc/redis/redis.conf
```

微服务工程

```shell
docker run -d -p 6001:6001 dockercompose:1.0
```

上面三个容器实例依次顺序启动成功

### swagger测试

### 上面成功了，有哪些问题？

先后顺序要求固定，先mysql+redis才能微服务访问成功

多个run命令…

容器间的启停或宕机，有可能导致IP地址对应的容器实例变化，映射出错，要么生产IP写死（可以但是不推荐），要么通过服务调用

### 使用Compose

服务编排，一套带走，安排

编写docker-compose.yml文件

```yaml
version: "3"

services:
  microService:
    image: dockercompose:1.1
    container_name: compose
    ports:
      - "6001:6001"
    volumes:
      - /app/microServices:/data
    networks:
      - mynetwork
    depends_on:
      - redis
      - mysql

  redis:
    image: redis:6.2.6-bullseye
    container_name: redis
    ports:
      - "6379:6379"
    volumes:
      - /app/redis/redis.conf:/etc/redis/redis.conf
      - /app/redis/data:/data
    networks:
      - mynetwork
    command: redis-server /etc/redis/redis.conf
    
  mysql:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: '123456'
      MYSQL_ALLOW_EMPTY_PASSWORD: 'no'
      MYSQL_DATABASE: 'docker'
      MYSQL_USER: 'root'
      MYSQL_PASSWORD: '123456'
    ports:
     - "3306:3306"
    volumes:
      - /zhangyuye/mysql/log:/var/log/mysql
      - /zhangyuye/mysql/data:/var/lib/mysql
      - /zhangyuye/mysql/conf:/etc/mysql/conf.d
      - /app/mysql/init:/docker-entrypoint-initdb.d
    networks:
      - mynetwork
    command: --default-authentication-plugin=mysql_native_password #解决外部无法访问

networks:
  mynetwork:
```

第二次修改微服务工程docker boot

- 写YML=========通过服务名访问，IP无关
- maven package命令将微服务形成新的jar包并上传到Linux服务器/mydocker目录下
- 编写Dockerfile
- 构建镜像  `docker build -t dockercompose:1.1 .`

执行`docker-compose up` 或者 执行`docker-compose up -d`

![image-20220310165353831](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310165353831.png)

进入mysql容器实例并新建库docker+新建表t_user

测试通过

![image-20220310200053589](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310200053589.png)

![image-20220310200117269](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310200117269.png)

关闭

![image-20220310165650991](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310165650991.png)

# 6.Docker轻量级可视化工具Portainer

## 是什么

Portainer是一款轻量级的应用，它提供了图形化界面，用于方便地管理Docker环境，包括单机环境和集群环境。

## 安装

官网[Container Management | Kubernetes GUI | Docker Swarm GUI | Portainer](https://www.portainer.io/)

[Install Portainer with Docker on Linux - Portainer Documentation](https://docs.portainer.io/v/ce-2.11/start/install/server/docker/linux)

**步骤**

命令

```shell
docker run -d -p 8000:8000 -p 9000:9000 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
```

![image-20220310212236864](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310212236864.png)

第一次登录需创建admin,访问地址：xxx.xxx.xxx.xxx:9000

admin  yq001215

设置admin用户和密码后首次登陆

选择local选项卡后本地docker详细信息展示

上一步的图形展示，能想得起对应命令吗？

```shell
docker system df
```

![image-20220310212547195](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310212547195.png)

安装Nginx

![image-20220310212747834](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310212747834.png)

![image-20220310212911922](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310212911922.png)

![image-20220310212935679](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310212935679.png)

# 7.Docker容器监控之CAdvisor+InfluxDB+Granfana

## 原生命令

通过docker stats命令可以很方便的看到当前宿主机上所有容器的CPU,内存以及网络流量等数据，一般小公司够用了。。。。

但是，docker stats统计结果只能是当前宿主机的全部容器，数据资料是实时的，没有地方存储、没有健康指标过线预警等功能

## 是什么

### 容器监控3剑客

#### 一句话

CAdvisor监控收集+InfluxDB存储数据+Granfana展示图表

![image-20220310213419784](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310213419784.png)

#### CAdvisor

CAdvⅵsor是一个容器资源监控工具，包括容器的内存，CPU,网络1O,磁盘IO等监控，同时提供了一个WEB页面用于查看容器的实时运行状态。CAdvⅵso默认存储2分钟的数据，而且只是针对单物理机。不过，CAdvisort提供了很多数据集成接口，变持InfluxDB,Redis,Kafka,Elasticsearch等集成，可以加上对应配置将监控数据发往这些数据库存储起来。

CAdvisor功能主要有两点：

- 展示Host和容器两个层次的监控数据。
- 展示历史变化数据。

#### InfluxDB

InfluxDB是用Go语言编写的一个开源分布式时序、事件和指标数据库，无需外部依赖。CAdviso默认只在本机保存最近2分钟的数据，为了持久化存储数据和统一收集展示监控数据，需要将数据存储到InfluxDB中。InfluxDB是一个时序数据库，专门用于存储时序相关数据，很适合存储CAdvisor的数据。而且，CAdvisor本身已经提供了InfluxDB的集成方法，丰启动容器时指定配置即可。

InfluxDB主要功能：

- 基于时间序列，支持与时间有关的相关函数（如最大、最小、求和等）：
- 可度量性你可以实时对大量数据进行计算：
- 基于事件：它支持任意的事件数据：

#### Granfana

Grafana是一个开源的数据监控分析可视化平台，支持多种数据源配置支持的数据源包括InfluxDB,MySQL,Elasticsearch,OpenTSDB,Graphite等)和丰富的插件及模板功能，支持图表权限控制和报警。

Grafan主要特性：

- 灵活丰富的图形化选项
- 可以混合多种风格
- 支持白天和夜间模式
- 多个数据源

#### 总结

![image-20220310213557927](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310213557927.png)

- cAdvisor-Collects,aggregates,processes,and exports information about running containers
- InfluxDB-Time Series Database stores all the metrics
- Grafana-Metrics Dashboard

## compose容器编排，一套带走

## 新建目录

![image-20220310213833592](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310213833592.png)

## 新建3件套组合的docker-compose.yml

```yaml
version: "3.1"

volumes:
  grafana_data: {}

services:
  influxdb:
    image: tutum/influxdb:0.9
    restart: always
    environment:  
      - PRE_CREATE_DB=cadvisor
    ports:
      -  "8083:8083"
      -  "8086:8086"
    volumes:
      - ./data/influxdb:/data

  cadvisor:
    image: google/cadvisor
    links:
      - influxdb:influxsrv
    command: -storage_driver=influxdb  -storage_driver_db=cadvisor -storage_driver_host=influxsrv:8086
    restart: always
    ports:
      - "8080:8080"
    volumes:
      - /:/rootfs:ro
      - /var/run:/var/run:rw
      - /sys:/sys:ro
      - /var/lib/docker/:/var/lib/docker:ro

  grafana:
    user: "104"
    image: grafana/grafana
    user: "104"
    restart: always
    links:
      - influxdb:influxsrv
    ports:
      - "3000:3000"
    volumes:
      - grafana_data:/var/lib/grafana
    environment:
      - HTTP_USER=admin
      - HTTP_PASS=admin
      - INFLUXDB_HOST=influxsrv
      - INFLUXDB_PORT=8086
```



## 启动docker-compose文件

```shell
docker-compose up
```

## 查看三个服务容器是否启动

![image-20220310221448766](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310221448766.png)

## 测试

浏览cAdvisor收集服务，http:/ip:8080

![image-20220310221612093](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310221612093.png)

浏览influxdb存储服务，http:/ip:8083

![image-20220310221627419](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310221627419.png)

浏览grafana展现服务，http:/ip:3000

默认账号密码 admin admin

![image-20220310221651057](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310221651057.png)

![image-20220310221734685](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310221734685.png)





**配置步骤**

配置数据源

![image-20220310221927016](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310221927016.png)

选择influxdb数据源

![image-20220310222056559](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222056559.png)

配置细节

![image-20220310222313742](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222313742.png)

![image-20220310222352915](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222352915.png)



配置面板panel

![image-20220310222440782](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222440782.png)

![image-20220310222533739](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222533739.png)

![image-20220310222633802](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222633802.png)

![image-20220310222726862](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222726862.png)

![image-20220310222948463](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310222948463.png)

![image-20220310223103418](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220310223103418.png)

到这里cAdvisor+-InfluxDB+Grafana容器监控系统

# 8.终章总结

