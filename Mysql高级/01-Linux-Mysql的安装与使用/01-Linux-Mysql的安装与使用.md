# 第01章Liux下MySQL的安装与使用

## 1.安装前说明

### 1.1 Linux系统及工具的准备

- 安装并启动好两台虚拟机：Cent0S7

- 掌握克隆虚拟机的操作

  - mac地址

  - 主机名

  - ![image-20220227134444785](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227134444785.png)

  - ip地址

  - ```
    vim /etc/sysconfig/network-scripts/ifcfg-ens33
    
    
    systemctl restart network
    ```

  - UUID

- 安装有Xshell和Xftp等访问CentoS系统的工具

- CentOS6和CentOS7在MySQL的使用中的区别

- ```
  1.防火墙：6是iptables,7是firewalld
  2.启动服务的命令：6是service,7是systemctl
  ```

### 1.2查看是否安装过MySQL

```
mysql --version
```

如果你是用rpm安装，检查一下RPM PACKAGE:

```
rpm -qa | grep -i mysql # -i忽略大小写
```

检查mysql service:

```
systemctl status mysqld.service
```

![image-20220227135014270](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227135014270.png)

### 1.3 MySQL的卸载

**1.关闭mysql服务**

```
systemctl stop mysqld.service
```

**2.查看当前mysql安装状况**

```
rpm -qa | grep -i mysql
#或
yum list installed | grep mysql
```

**3.卸载上述命令查询出的已安装程序**

```
yum remove mysq1-xxx mysq1-xxx mysq1-xxx mysql-xxxx
```

务必卸载干净，反复执行rpm -qa | grep -i mysql确认是否有卸载残留

**4.删除mysql相关文件**

- 查找相关文件
  `find /-name mysql`
- 删除上述命令查找出的相关文件
  `rm -rf xxx`

**5.删除my.cnf**
`rm -rf /etc/my.cnf`

## 2.MySQL的Linux版安装

### 2.1 MySQL的4大版本

>- MySQL Community Server社区版本，开源免费，自由下载，但不提供官方技术支持，适用于大多数普通用户。
>- MySQL Enterprise Edition企业版本，需付费，不能在线下载，可以试用30天。提供了更多的功能和更完备的技术支持，更适合于对数据库的功能和可靠性要求较高的企业客户。
>- MySQL Cluster集群版，开源免费。用于架设集群服务器，可将几个MySQL Server封装成一个Server。需要在社区版或企业版的基础上使用。
>- MySQL Cluster CGE高级集群版，需付费。

此外，官方还提供了MySQL Workbench(GUITOOL)一款专为MySQL设计的ER/数据库建模工具。它是著名的数据库设计工具OBDesigner4的继任者。MySQLWorkbench又分为两个版本，分别是**社区版**(MySQL Workbench OSS)、**商用版**(MySQL WorkbenchSE)。

### 2.2下载MySQL指定版本

**1.下载地址**

官网：https://www.mysql.com

**2.打开官网，点击DOWNLOADS**

然后，点击MySQL Community(GPL) Dowrloads

![image-20220227140830677](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227140830677.png)

**4,在General Availability(GA)Releasest中选择适合的版本**

- 如果安装Windows系统下MySQL,推荐下载MSI安装程序；点击Go to Download Page进行下载即可
- Windows下的MVSOL安装有两种安装程序
  - mysql-installer-web-community-8.0.25.0.msi下载程序大小：2.4M;安装时需要联网安装组件。
  - mysql-installer-community-8.0.25.0.msi下载程序大小：435.7M;安装时离线安装即可。**推荐**。

**5.Linux系统下安装MySQL的几种方式**

**5.1Linux系统下安装软件的常用三种方式：**

**方式1：rpm命令**

使用rpm命令安装扩展名为".rpm"的软件包。

rpm包的一般格式：

![image-20220227141116415](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227141116415.png)

**方式2：yum命令**

需联网，从互联网获取的yum源，直接使用yum命令安装。

**方式3：编译安装源码包**

针对tar.gz这样的压缩格式，要用tar命令来解压,如果是其它压缩格式，就使用其它命令。

| 安装方式       | 特点                                                 |
| -------------- | ---------------------------------------------------- |
| rpm            | 安装简单，灵活性差，无法灵活选择版本、升级           |
| rpm repository | 安装包极小，版本安装简单灵活，升级方便，需要联网安装 |
| 通用二进制包   | 安装比较复杂，灵活性高，平台通用性好                 |
| 源码包         | 安装最复杂，时间长，参数设置灵活，性能好             |

### 2.3Cent0S7下检查MySQL依赖

#### 1.检查/tmp临时目录权限（必不可少)

```shell
chmod -R 777 /tmp

drwxrwxrwx.  20 root root 4096 2月  27 14:05 tmp
```

#### 2.安装前，检查依赖

```shell
rpm -qa | grep libaio
```

- 如果存在libaio包如下：

  ![image-20220227142622613](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227142622613.png)

- 如果存在net-tools包如下：

  ![image-20220227142702686](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227142702686.png)

- 如果不存在需要到centos:安装盘里进行rpm安装。安装linux如果带图形化界面，这些都是安装好的。

### 2.4 CentoS7下MySQL安装过程

#### 1.将安装程序拷贝到/opt目录下

在mysql的安装文件目录下执行：（必须按照顺序执行）

```shell
rpm -ivh mysql-community-common-8.0.28-1.el7.x86_64.rpm 

rpm -ivh mysql-community-client-plugins-8.0.28-1.el7.x86_64.rpm 

rpm -ivh mysql-community-libs-8.0.28-1.el7.x86_64.rpm

rpm -ivh mysql-community-client-8.0.28-1.el7.x86_64.rpm 

rpm -ivh mysql-community-icu-data-files-8.0.28-1.el7.x86_64.rpm 

rpm -ivh mysql-community-server-8.0.28-1.el7.x86_64.rpm 
```

- -i,-install安装软件包
- -v,-verbose提供更多的详细信息输出
- -h,--hash软件包安装的时候列出哈希标记（和-V一起使用效果更好），展示进度条

![image-20220227144212617](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227144212617.png)

#### 2.安装过程截图

![image-20220227143407478](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227143407478.png)

>  一个命令：yum remove mysql-libs解决，清除之前安装过的依赖即可

#### 3.查看MySQL版本

执行如下命令，如果成功表示安装mysql成功。类似java-version如果打出版本等信息

```shell
[root@localhost opt]# mysql --version
mysql  Ver 8.0.28 for Linux on x86_64 (MySQL Community Server - GPL)
[root@localhost opt]# mysqladmin --version
mysqladmin  Ver 8.0.28 for Linux on x86_64 (MySQL Community Server - GPL)
[root@localhost opt]# 
```

![image-20220227144339335](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227144339335.png)

#### 4.服务的初始化

为了保证数据库目录与文件的所有者为mysql登录用户，如果你是以root身份运行mysql服务，需要执行下面的命令初始化：

```shell
mysqld --initialize --user=mysql
```

说明：-initialize选项默认以“安全”模式来初始化，则会为root用户生成一个密码并将该**密码标记为过期**，登录后你需要设置一个新的密码。生成的临时密码会往日志中记录一份。

查看密码：

```shell
cat /var/log/mysqld.log
```



![image-20220227144537329](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227144537329.png)

#### 5.启动MySQL,查看状态

```shell
#加不加.service后缀都可以
启动：systemctl start mysqld.service
关闭：systemctl stop mysqld.service
重启：systemctl restart mysqld.service
查看状态：systemctl status mysqld.service

# mysq1d这个可执行文件就代表着MySQL服务器程序，运行这个可执行文件就可以直接启动一个服务器进程。
```

![image-20220227144828268](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227144828268.png)

#### 6.查看MySQL服务是否自启动

```shell
systemctl list-unit-files | grep mysqld.service
```

![image-20220227144938063](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227144938063.png)

默认是enabled,

- 如不是enabled可以运行如下命令设置自启动
  `systemctl enable mysqld.service`

- 开机不自启

  `systemctl disable mysqld.service`

## 3.MySQL登录

### 3.1首次登录

```shell
[root@localhost opt]# mysql -uroot -p
Enter password: [初始密码]
```

### 3.2修改密码

```shell
ALTER USER 'root'@'localhost' IDENTIFIED BY '123456'
```

### 3.3设置远程登录

#### 1.当前问题

在用SQLyog或Navicat中配置远程连接Mysql数据库时遇到如下报错信息，这是由于Mysql配置了不支持远程连接起的。

![image-20220227145927992](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227145927992.png)

#### 2.确认网络

1.在远程机器上使用ping ip地址保证网络畅通

2.在远程机器上使用telnet命令保证端口号开放访问

```shell
telnet ip地址 端口号
```

![image-20220227151119691](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227151119691.png)

#### 3.关闭防火墙或开放端口

centOS6

```shell
service iptables stop
```



![image-20220227151141441](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220227151141441.png)

**开放端口**

- 查看开放的端口号
  `firewall-cmd --list-all`
- 设置开放的端口号
  `firewall-cmd --add-service=http --permanent`
  `firewall-cmd --add-port=3306/tcp --permanent`
- 重启防火墙
  `firewall-cmd --reload`

#### 4.Linux下修改配置

```mysql
mysql> use mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables;
+------------------------------------------------------+
| Tables_in_mysql                                      |
+------------------------------------------------------+
| columns_priv                                         |
| component                                            |
| db                                                   |
| default_roles                                        |
| engine_cost                                          |
| func                                                 |
| general_log                                          |
| global_grants                                        |
| gtid_executed                                        |
| help_category                                        |
| help_keyword                                         |
| help_relation                                        |
| help_topic                                           |
| innodb_index_stats                                   |
| innodb_table_stats                                   |
| password_history                                     |
| plugin                                               |
| procs_priv                                           |
| proxies_priv                                         |
| replication_asynchronous_connection_failover         |
| replication_asynchronous_connection_failover_managed |
| replication_group_configuration_version              |
| replication_group_member_actions                     |
| role_edges                                           |
| server_cost                                          |
| servers                                              |
| slave_master_info                                    |
| slave_relay_log_info                                 |
| slave_worker_info                                    |
| slow_log                                             |
| tables_priv                                          |
| time_zone                                            |
| time_zone_leap_second                                |
| time_zone_name                                       |
| time_zone_transition                                 |
| time_zone_transition_type                            |
| user                                                 |
+------------------------------------------------------+
37 rows in set (0.01 sec)

mysql> select host,user from user;
+-----------+------------------+
| host      | user             |
+-----------+------------------+
| localhost | mysql.infoschema |
| localhost | mysql.session    |
| localhost | mysql.sys        |
| localhost | root             |
+-----------+------------------+
4 rows in set (0.00 sec)

mysql> update user set host = '%' where user = 'root';
Query OK, 1 row affected (0.06 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> flush privileges;
Query OK, 0 rows affected (0.04 sec)

```

## 4.MySQL8的密码强度评估

默认安装Validate_password。由validate_password实现的默认密码策略要求密码至少包含一个大写字母、一个小写字母、一个数字和一个特殊字符，并且密码总长度至少为8个字符。

https://dev.mysql.com/doc/refman/8.0/en/validate-password.html

## 5.字符集的相关操作

### 5.1查看字符集

**1.修改步骤**

​	在MySQL8.0版本之前，默认字符集为`latinl`,utf8字符集指向的是`utf8mb3`。网站开发人员在数据库设计的时候往往会将编码修改为ut8字符集。如果遗忘修改默认的编码，就会出现乱码的问题。从MySQL8.0开始，数据库的默认编码将改为`utf8mb4`,从而避免上述乱码的问题。

**操作1：查看默认使用的字符集**

```mysql
show variables like 'character%';
#或者
show variables like '%char%';

mysql> show variables like 'character%';
+--------------------------+--------------------------------+
| Variable_name            | Value                          |
+--------------------------+--------------------------------+
| character_set_client     | utf8mb4                        |
| character_set_connection | utf8mb4                        |
| character_set_database   | utf8mb4                        |
| character_set_filesystem | binary                         |
| character_set_results    | utf8mb4                        |
| character_set_server     | utf8mb4                        |
| character_set_system     | utf8mb3                        |
| character_sets_dir       | /usr/share/mysql-8.0/charsets/ |
+--------------------------+--------------------------------+
8 rows in set (0.02 sec)
```

### 5.2修改字符集

MySQL5.7默认的客户端和服务器都用了1atin1,不支持中文，保存中文会报错。

```shell
vim /etc/my.cnf
```

在MySQL5.7或之前的版本中，在文件最后加上中文字符集配置：

```shell
character_set_server=utf8
```

```shell
systemctl restart mysqld
```

### 5.3已有库&表字符集的变更

MySQL5.7版本中，以前创建的库，创建的表字符集还是latinl

修改已创建数据库的字符集

```mysql
alter database dbtest1 character set 'utf8';
```

修改已创建数据表的字符集

```mysql
alter table t_emp convert to character set 'utf8';
```

### 5.4各级别的字符集

MySQL有4个级别的字符集和比较规则，分别是：

- 服务器级别
- 数据库级别
- 表级别
- 列级别

```mysql
mysql> show variables like 'character%';
+--------------------------+--------------------------------+
| Variable_name            | Value                          |
+--------------------------+--------------------------------+
| character_set_client     | utf8mb4                        |
| character_set_connection | utf8mb4                        |
| character_set_database   | utf8mb3                        |
| character_set_filesystem | binary                         |
| character_set_results    | utf8mb4                        |
| character_set_server     | utf8mb3                        |
| character_set_system     | utf8mb3                        |
| character_sets_dir       | /usr/share/mysql-8.0/charsets/ |
+--------------------------+--------------------------------+
8 rows in set (0.02 sec)
```

- character_set_server:服务器级别的字符集
- character_set_database:当前数据库的字符集
- character_set_client:服务器解码请求时使用的字符集
- character_set_connection:服务器处理请求时会把请求字符串从character_.set_client转为
  character_set_connection
- character_set_results:服务器向客户端返回数据时使用的字符集

#### 1.服务器级别

- character_set_server:服务器级别的字符集

我们可以在启动服务器程序时通过启动选项或者在服务器程序运行过程中使用SET语句修改这两个变量的值。比如我们可以在配置文件中这样写：

```mysql
[server]
character_set_server=gbk#默认字符集
collation_server=gbk_chinese_ci #对应的默认的比较规则
```

当服务器启动的时候读取这个配置文件后这两个系统变量的值便修改了。

#### 2.数据库级别

- character_set_database:当前数据库的字符集

我们在创建和修改数据库的时候可以指定该数据库的字符集和比较规则，具体语法如下：

```mysql
CREATE DATABASE 数据库名
[[DEFAULT] CHARACTER SET字符集名称]
[[DEFAULT] COLLATE 比较规则名称]；

ALTER DATABASE 数据库名
[[DEFAULT] CHARACTER SET 字符集名称]
[[DEFAULT] COLLATE 比较规则名称]；
```

其中的DEFAULT可以省略，并不影响语句的语义。比如：

```mysql
mysql->CREATE DATAEASE charset_demo_db
	 ->CHARACTER SET gb2312
	 ->COLLATE gb2312_chinese_ci;
Query OK,1 row affected (0.01 sec)
```

数据库的创建语句中也可以不指定字符集和比较规侧则，比如这样：

```mysql
CREATE DATABASE 数据库名；
```

**这样的话将使用服务器级别的字符集和比较规侧作为数据库的字符集和比较规侧。**

#### 3.表级别

我们也可以在创建和修改表的时候指定表的字符集和比较规则，语法如下：

```mysql
CREATE TABLE表名（列的信息)
[[DEFAULT] CHARACTER SET字符集名称]
[COLLATE 比较规则名称]]

ALTER TABLE 表名
[[DEFAULT] CHARACTER SET字符集名称]
[COLLATE 比较规则名称]
```

如果创建和修改表的语句中没有指明字符集和止比较规则，将使用该表所在数据库的字符集和止比较规则作为该表的字符集和比较规则。

#### 4.列级别

对于存储字符串的列，同一个表中的不同的列也可以有不同的字符集和比较规侧。我们在创建和修改列定义的时候可以指定该列的字符集和比较规侧，语法如下：

```mysql
CREATE TABLE 表名(
	列名 字符串类型 [CHARACTER SET字符集名称] [COLLATE比较规则名称],
	其他列.··
);
ALTER TABLE 表名 MODIFY 列名 字符串类型 [CHARACTER SET 字符集名称] [COLLATE 比较规则名称];
```

对于某个列来说，如果在创建和修改的语句中没有指明字符集和止比较规则，将使用该列所在表的字符集和比较规则作为该列的字符集和止比较规则。

> 提示
> 在转换列的字符集时需要注意，如果袋换前列中存储的数据不能用转换后的字符集进行表示会发生错误。比方说原先列使用的字符集是utf8,列中存储了一些汉字，现在把列的字符集转换为ascii的话就会出错，因为ascii字符集并不能表示汉字字符

### 5.5.字符集与比较规则（了解）

#### 1.utf8与utf8mb4

utf8字符集表示一个字符需要使用1~4个字节，但是我们常用的一些字符使用1~3个字节就可以表示了。而字符集表示一个字符所用的最大字节长度，在某些方面会影响系统的存储和性能，所以设计MySQL的设计者偷偷的定义了两个概念

- utf8mb3:阉割过的utf8字符集，只使用1~3个字节表示字符。
- utf8mb4:正宗的utf8字符集，使用1~4个字节表示字符。

在MySQL中**utf8是utf8mb3的别名**，所以之后在MySQL中提到utf8就意味着使用1~3个字节来表示一个字符。如果大家有使用4字节编码一个字符的情况，比如存储一些emoji表情，那请使用utf8mb4。此外，通过如下指令可以查看MySQL支持的字符集：

```mysql
SHOW CHARSET;

#或者

SHOW CHARACTER SET;

mysql> show charset;
+----------+---------------------------------+---------------------+--------+
| Charset  | Description                     | Default collation   | Maxlen |
+----------+---------------------------------+---------------------+--------+
| armscii8 | ARMSCII-8 Armenian              | armscii8_general_ci |      1 |
| ascii    | US ASCII                        | ascii_general_ci    |      1 |
| big5     | Big5 Traditional Chinese        | big5_chinese_ci     |      2 |
| binary   | Binary pseudo charset           | binary              |      1 |
| cp1250   | Windows Central European        | cp1250_general_ci   |      1 |
| cp1251   | Windows Cyrillic                | cp1251_general_ci   |      1 |
| cp1256   | Windows Arabic                  | cp1256_general_ci   |      1 |
| cp1257   | Windows Baltic                  | cp1257_general_ci   |      1 |
| cp850    | DOS West European               | cp850_general_ci    |      1 |
| cp852    | DOS Central European            | cp852_general_ci    |      1 |
| cp866    | DOS Russian                     | cp866_general_ci    |      1 |
| cp932    | SJIS for Windows Japanese       | cp932_japanese_ci   |      2 |
| dec8     | DEC West European               | dec8_swedish_ci     |      1 |
| eucjpms  | UJIS for Windows Japanese       | eucjpms_japanese_ci |      3 |
| euckr    | EUC-KR Korean                   | euckr_korean_ci     |      2 |
| gb18030  | China National Standard GB18030 | gb18030_chinese_ci  |      4 |
| gb2312   | GB2312 Simplified Chinese       | gb2312_chinese_ci   |      2 |
| gbk      | GBK Simplified Chinese          | gbk_chinese_ci      |      2 |
| geostd8  | GEOSTD8 Georgian                | geostd8_general_ci  |      1 |
| greek    | ISO 8859-7 Greek                | greek_general_ci    |      1 |
| hebrew   | ISO 8859-8 Hebrew               | hebrew_general_ci   |      1 |
| hp8      | HP West European                | hp8_english_ci      |      1 |
| keybcs2  | DOS Kamenicky Czech-Slovak      | keybcs2_general_ci  |      1 |
| koi8r    | KOI8-R Relcom Russian           | koi8r_general_ci    |      1 |
| koi8u    | KOI8-U Ukrainian                | koi8u_general_ci    |      1 |
| latin1   | cp1252 West European            | latin1_swedish_ci   |      1 |
| latin2   | ISO 8859-2 Central European     | latin2_general_ci   |      1 |
| latin5   | ISO 8859-9 Turkish              | latin5_turkish_ci   |      1 |
| latin7   | ISO 8859-13 Baltic              | latin7_general_ci   |      1 |
| macce    | Mac Central European            | macce_general_ci    |      1 |
| macroman | Mac West European               | macroman_general_ci |      1 |
| sjis     | Shift-JIS Japanese              | sjis_japanese_ci    |      2 |
| swe7     | 7bit Swedish                    | swe7_swedish_ci     |      1 |
| tis620   | TIS620 Thai                     | tis620_thai_ci      |      1 |
| ucs2     | UCS-2 Unicode                   | ucs2_general_ci     |      2 |
| ujis     | EUC-JP Japanese                 | ujis_japanese_ci    |      3 |
| utf16    | UTF-16 Unicode                  | utf16_general_ci    |      4 |
| utf16le  | UTF-16LE Unicode                | utf16le_general_ci  |      4 |
| utf32    | UTF-32 Unicode                  | utf32_general_ci    |      4 |
| utf8     | UTF-8 Unicode                   | utf8_general_ci     |      3 |
| utf8mb4  | UTF-8 Unicode                   | utf8mb4_0900_ai_ci  |      4 |
+----------+---------------------------------+---------------------+--------+
41 rows in set (0.14 sec)
```

#### 2.比较规则

上表中，MySQL版本一共支持41种字符集，其中的Default collation列表示这种字符集中一种默认的比较规则，里面包含着该比较规侧主要作用于哪种语言，比如utf8_polish_ci表示以波兰语的规则比较，utf8_spanish_ci是以西班牙语的规则比较，utf8_general_ci是一种通用的比较规则.后缀表示该比较规侧是否区分语言中的重音、大小写。具体如下：

| 后缀 | 英文释义           | 描述             |
| ---- | ------------------ | ---------------- |
| _ai  | accent insensitive | 不区分重音       |
| _as  | accent sensitive   | 区分重音         |
| _ci  | case insensitive   | 不区分大小写     |
| _cs  | case sensitive     | 区分大小写       |
| _bin | binary             | 以二进制方式比较 |

最后一列Maxlen,它代表该种字符集表示一个字符最多需要几个字节。

常用操作1：

```mysql
#查看GBK字符集的比较规则
SHOW COLLATION LIKE 'gbk%';

#查看UTF-8字符集的比较规则
SHOW COLLATION LIKE 'utf8%';
```

常用操作2：

```mysql
#查看服务器的字符集和比较规则
SHOW VARIABLES LIKE '%_server';

#查看数据库的字符集和比较规则
SHOW VARIABLES LIKE '%_database';

#查看具体数据库的字符集
SHOW CREATE DATABASE dbtest1;

#修改具体数据库的字符集
ALTER DATABASE dbtest1 DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
```

常用操作3：

```mysql
#查看表的字符集
show create table employees;

#查看表的比较规则
show table status from atguigudb like 'employees';

#修改表的字符集和比较规则
ALTER TABLE emp1 DEFAULT CHARACTER SET 'utf8'COLLATE 'utf8_general_ci';
```

### 5.6.请求到响应过程中字符集的变化

我们知道从客户端发往服务器的请求本质上就是一个字符串，服务器向客户端返回的结果本质上也是一个字符串，而字符串其实是使用某种字符集编码的二进制数据。这个字符串可不是使用一种字符集的编码方式一条道走到黑的，从发送请求到返回结果这个过程中伴随着多次字符集的转换，在这个过程中会用到3个系统变量，我们先把它们写出来看一下：

| 系统变量                 | 描述                                                         |
| ------------------------ | ------------------------------------------------------------ |
| character_set_client     | 服务器解码请求时使用的字符集<                                |
| character_set_connection | 服务器处理请求时会把请求字符串从character_set_client转为character_set_connection |
| character_set_results    | 服务器向客户端返回数据时使用的字符集                         |

![image-20220228144602153](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220228144602153.png)

经验：
开发中通常把character_set_client、character_.set_connection、character_.set_results这三个系统变量设置成和客户端使用的字符集一致的情况，这样减少了很多无谓的字符集转换。为了方便我们设置，MySQL提供了一条非常简便的语句：

```mysql
SET NAMES 字符集名;
```

这一条语句产生的效果和我们执行这3条的效果是一样的：

```mysql
SET character_set_client=字符集名;
SET character-set_connection=字符集名;
SET character_set_results=字符集名;
```

## 6.SQL大小写规范

### 6.1 Windows和Linux平台区别

在SQL中，关键字和函数名是不用区分字母大小写的，比如SELECT、WHERE、ORDER、GROUP BY等关键字，以
及ABS、MOD、ROUND、MAX等函数名。

不过在SQL中，你还是要确定大小写的规范，因为在Linux和Windows环境下，你可能会遇到不同的大小写问题。**windows系统默认大小写不敏感，但是linux系统是大小写敏感的。**

通过如下命令查看：

```mysql
SHOW VARIABLES LIKE '%lower_case_table_names%';
```

windows:

```mysql
mysql> SHOW VARIABLES LIKE '%lower_case_table_names%';
+------------------------+-------+
| Variable_name          | Value |
+------------------------+-------+
| lower_case_table_names | 1     |
+------------------------+-------+
1 row in set, 1 warning (0.09 sec)
```

linux:

```mysql
mysql> SHOW VARIABLES LIKE '%lower_case_table_names%';
+------------------------+-------+
| Variable_name          | Value |
+------------------------+-------+
| lower_case_table_names | 0     |
+------------------------+-------+
1 row in set (0.04 sec)
```

lower_case_table_names参数值的设置：

- 默认为0，大小写敏感。
- 设置1，大小写不敏感。创建的表，数据库都是以小写形式存放在磁盘上，对于sql语句都是转换为小写对表和数据库进行查找。
- 设置2，创建的表和数据库依据语句上格式存放，凡是查找都是转换为小写进行。

两个平台上SQL大小写的区别具体来说：

> MySQL在Linux下数据库名、表名、列名、别名大小写规则是这样的：
>
> ​	1、数据库名、表名、表的别名、变量名是严格区分大小写的：
>
> ​    2、关键字、函数名称在SQL中不区分大小写；
>
> ​	3、列名（或字段名）与列的别名（或字段别名）在所有的情况下均是忽略大小写的：
>
> **MySQL在Windows的环境下全部不区分大小写**

### 6.2 Linux下大小写规则设置

当想设置为大小写不敏感时，要在my.cnf这个配置文件[mysqld]中加入`lower_case_table_names=1`,然后重启服务器。

- 但是要在重启数据库实例之前就需要将原来的数据库和表转换为小写，否则将找不到数据库名。
- 此参数适用于MySQL5.7。在MySQL8下禁止在重新启动MySQL服务时将lower-case_table_names设置成不同于初始化MySQL服务时设置的lower_case_table_names值。如果非要将MySQL8设置为大小写不每敏感，具体步骤为：

```
1、停止ySQL服务
2、删除数据目录，即删除/var/1ib/mysql目录
3、在MySQL配置文件(/etc/my.cnf)中添加lower_case_table_names=1
4、启动MySQL服务
```

> 注意：在进行数据库参数设置之前，需要掌握这个参数带来的影响，切不可盲目设置。

### 6.3.SQL编写建议

如果你的变量名命名规范没有统一，就可能产生错误。这里有一个有关命名规范的建议：

> 1.关键字和函数名称全部大写：
> 2.数据库名、表名、表别名、字段名、字段别名等全部小写；
> 3,SQL语句必须以分号结尾。

数据库名、表名和字段名在Linux MySQL环境下是区分大小写的，因此建议你统一这些字段的命名规则，比如全部采用小写的方式。

虽然关键字和函数名称在SQL中不区分大小写，也就是如果小写的话同样可以执行。但是同时将关键词和函数名称全部大写，以便于区分数据库名、表名、字段名。

## 7.sql_mode的合理设置

### 7.1介绍

sq_mode会影响MySQL支持的SQL语法以及它执行的**数据验证检查**。通过设置sal_mode,可以完成不同严格程度的数据校验，有效地保障数据准确性。

MySQL服务器可以在不同的SQL模式下运行，并且可以针对不同的客户端以不同的方式应用这些模式，具体取决于sql_mode系统变量的值。

MySQL5.6和MySQL5.7默认的sql_mode模式参数是不一样的：

- 5.6的mode默认值为空（即：**NO_ENGINE_SUBSTITUTION**),其实表示的是一个空值，相当于没有什么模式设置，可以理解为**宽松模式**。在这种设置下是可以允许一些非法操作的，比如允许一些非法数据的插入。
- 5.7的mode是**STRICT_TRANS_TABLES**,也就是**严格模式**。用于进行数据的严格校验，错误数据不能插入，报error(错误)，并且事务回滚。

### 7.2宽松模式Vs严格模式

**宽松模式：**

如果设置的是宽松模式，那么我们在插入数据的时候，即便是给了一个错误的数据，也可能会被接受，并且不报错。

应用场景：通过设置sql mode为宽松模式，来保证大多数sql符合标准的sql语法，这样应用在不同数据库之间进行**迁移**时，则不需要对业务sql进行较大的修改。

**严格模式：**

出现上面宽松模式的错误，应该报错才对，所以MySQL5.7版本就将sql_mode默认值改为了严格模式。所以在**生产等环境中**，我们必须采用的是严格模式，进而**开发、测试环境**的数据库也必须要设置，这样在开发测试阶段就可以发现问题。并且我们即便是用的MySQL5.6,也应该自行将其改为严格模式。

**开发经验**：MySQL等数据库总想把关于数据的所有操作都自己包揽下来，包括数据的校验，其实开发中，我们应该在自己**开发的项目程序级别将这些校验给做了**，虽然写项目的时候麻烦了一些步骤，但是这样做之后，我们在进行数据库迁移或者在项目的迁移时，就会方便很多。

改为严格模式后可能会存在的问题

若设置模式中包含了**NO_ZERO_DATE**,那么MySQL数据库不允许插入零日期，插入零日期会抛出错误而不是警告。例如，表中含字段TIMESTAMPZ列（如果未声明为NULL或显示DEFAULT子句）将自动分配DEFAULT'O000-00-O000:00:00'(零时间戳)，这显然是不满足sqL_mode中的NO_ZERO_DATE而报错。

### 7.3模式查看和设置

- **查看当前的sql_mode**

```mysql
select @@session.sql_mode

select @@global.sql_mode

#或者
show variables like 'sq1_mode';

mysql> select @@session.sql_mode;
+--------------------------------------------+
| @@session.sql_mode                         |
+--------------------------------------------+
| STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION |
+--------------------------------------------+
1 row in set (0.00 sec)

mysql> select @@global.sql_mode;
+--------------------------------------------+
| @@global.sql_mode                          |
+--------------------------------------------+
| STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION |
+--------------------------------------------+
1 row in set (0.00 sec)
```

- **临时设置方式：设置当前窗口中设置sql_mode**

  ```mysql
  SET GLOBAL sq1_mode='modes...';#全局
  SET SESSION sq1_mode='modes...';#当前会话
  ```

  举例：

  ```mysql
  #改为严格模式。此方法只在当前会话中生效，关闭当前会话就不生效了。
  SET SESSION sq1_mode='STRICT_TRANS_TABLES';
  
  #改为严格模式。此方法在当前服务中生效，重启MySQL服务后失效。
  SET GLOBAL sql_mode='STRICT_TRANS_TABLES';
  ```

- **永久设置方式：在/etc/my.cnf中配置sql_mode**

  在my.cnf文件(windows系统是my.ini文件)，新增：

  ```shell
  [mysqld]
  sq1_mode=ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
  ```

  然后重启MySQL。

  当然生产环境上是禁止重启MySQL服务的，所以采用临时设置方式+永久设置方式来解决线上的问题，那么即便是有一天真的重启了MySQL服务，也会永久生效了。

