# 第02章MySQL的数据目录

## 1.MySQL8的主要目录结构

```shell
[root@localhost ~]# find / -name mysql
```

安装好MySQL8之后，我们查看如下的目录结构：

```shell
[root@localhost ~]# find / -name mysql
/etc/logrotate.d/mysql
/etc/selinux/targeted/active/modules/100/mysql
/etc/selinux/targeted/tmp/modules/100/mysql
/root/.local/share/akonadi/db_data/mysql
/var/lib/mysql
/var/lib/mysql/mysql
/usr/bin/mysql
/usr/lib64/mysql
[root@localhost ~]# 
```

### 1.1数据库文件的存放路径

**MySQL数据库文件的存放路径：var/lib/mysql/**

MySQL服务器程序在启动时会到文件系统的某个目录下加载一些文件，之后在运行过程中产生的数据也都会存储到这个目录下的某些文件中，这个目录就称为数据目录。

MySQL把数据都存到哪个路径下呢？其实数据目录对应着一个系统变量datadir,我们在使用客户端与服务器建立连接之后查看这个系统变量的值就可以了：

```mysql
mysql> show variables like 'datadir';
+---------------+-----------------+
| Variable_name | Value           |
+---------------+-----------------+
| datadir       | /var/lib/mysql/ |
+---------------+-----------------+
1 row in set (0.13 sec)
```

#### 1.2相关命令目录

**相关命令目录：/usr/bin(mysqladmin、mysqlbinlog、mysqldump等命令)和/usr/sbin**

安装目录下非常重要的**bin目录**，它里边存储了许多关于控制客户端程序和服务器程序的命令（许多可执行文件，比如**mysql,mysqld,mysqld_safe**等)。而**数据目录**是用来存储MySQL在运行过程中产生的数据，注意区分开二者。

#### 1.3配置文件目录

**配置文件目录：/usr/share/mysql-8.0(命令及配置文件)，/etc/mysql(如my.cnf)**

```shell
[root@localhost mysql-8.0]# ll
总用量 1004
drwxr-xr-x. 2 root root     24 2月  27 14:28 bulgarian
drwxr-xr-x. 2 root root   4096 2月  27 14:28 charsets
drwxr-xr-x. 2 root root     24 2月  27 14:28 czech
drwxr-xr-x. 2 root root     24 2月  27 14:28 danish
-rw-r--r--. 1 root root  25575 12月 18 00:07 dictionary.txt
drwxr-xr-x. 2 root root     24 2月  27 14:28 dutch
drwxr-xr-x. 2 root root     24 2月  27 14:28 english
drwxr-xr-x. 2 root root     24 2月  27 14:28 estonian
drwxr-xr-x. 2 root root     24 2月  27 14:28 french
drwxr-xr-x. 2 root root     24 2月  27 14:28 german
drwxr-xr-x. 2 root root     24 2月  27 14:28 greek
drwxr-xr-x. 2 root root     24 2月  27 14:28 hungarian
-rw-r--r--. 1 root root   3999 12月 18 00:07 innodb_memcached_config.sql
-rw-r--r--. 1 root root   2216 12月 18 00:58 install_rewriter.sql
drwxr-xr-x. 2 root root     24 2月  27 14:28 italian
drwxr-xr-x. 2 root root     24 2月  27 14:28 japanese
drwxr-xr-x. 2 root root     24 2月  27 14:28 korean
-rw-r--r--. 1 root root 616528 12月 18 00:07 messages_to_clients.txt
-rw-r--r--. 1 root root 359072 12月 18 00:07 messages_to_error_log.txt
-rw-r--r--. 1 root root   1977 12月 18 00:58 mysql-log-rotate
drwxr-xr-x. 2 root root     24 2月  27 14:28 norwegian
drwxr-xr-x. 2 root root     24 2月  27 14:28 norwegian-ny
drwxr-xr-x. 2 root root     24 2月  27 14:28 polish
drwxr-xr-x. 2 root root     24 2月  27 14:28 portuguese
drwxr-xr-x. 2 root root     24 2月  27 14:28 romanian
drwxr-xr-x. 2 root root     24 2月  27 14:28 russian
drwxr-xr-x. 2 root root     24 2月  27 14:28 serbian
drwxr-xr-x. 2 root root     24 2月  27 14:28 slovak
drwxr-xr-x. 2 root root     24 2月  27 14:28 spanish
drwxr-xr-x. 2 root root     24 2月  27 14:28 swedish
drwxr-xr-x. 2 root root     24 2月  27 14:28 ukrainian
-rw-r--r--. 1 root root   1248 12月 18 00:58 uninstall_rewriter.sql
[root@localhost mysql-8.0]# 
```

## 2.数据库和文件系统的关系

像**IonoDB、MYISAM**这样的存储引擎都是把表存储在磁盘上的，操作系统用来管理磁盘的结构被称为**文件系统**，所以用专业一点的话来表述就是：像**InnoDB、MYISAM**这样的存储引擎都是把表存储在文何系统上的。当我们想读取数据的时候，这些存储引擎会从文件系统中把数据读出来返回给我们，当我们想写入数据的时候，这些存储引擎会把这些数据又写回文件系统。本章学习一下**InnoDB**和**MYISAM**这两个存储引擎的数据如何在文件系统中存储。

### 2.1查看默认数据库

查看一下在我的计算机上当前有哪些数据库：

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
4 rows in set (0.18 sec)
```

可以看到有4个数据库是属于MySQL自带的系统数据库。

- mysql
  MySQL系统自带的核心数据库，它存储了MySQL的用户账户和权限信息，一些存储过程、事件的定义信息，一些运行过程中产生的日志信息，一些帮助信息以及时区信息等。

- information_schema
  MySQL系统自带的数据库，这个数据库保存着MySQL服务器**维护的所有其他数据库的信息**，比如有哪些表、哪些视图、哪些触发器、哪些列、哪些索引。这些信息并不是真实的用户数据，而是一些描述性信息，有时候也称之为**元数据**。在系统数据库information_.schema中提供了一些以innodb_sys开头的表，用于表示内部系统表。

  ```mysql
  mysql> use information_schema;
  Reading table information for completion of table and column names
  You can turn off this feature to get a quicker startup with -A
  
  Database changed
  mysql> show tables;
  +---------------------------------------+
  | Tables_in_information_schema          |
  +---------------------------------------+
  | ADMINISTRABLE_ROLE_AUTHORIZATIONS     |
  | APPLICABLE_ROLES                      |
  | CHARACTER_SETS                        |
  | CHECK_CONSTRAINTS                     |
  | COLLATIONS                            |
  | COLLATION_CHARACTER_SET_APPLICABILITY |
  | COLUMNS                               |
  | COLUMNS_EXTENSIONS                    |
  | COLUMN_PRIVILEGES                     |
  | COLUMN_STATISTICS                     |
  | ENABLED_ROLES                         |
  | ENGINES                               |
  | EVENTS                                |
  | FILES                                 |
  | INNODB_BUFFER_PAGE                    |
  | INNODB_BUFFER_PAGE_LRU                |
  | INNODB_BUFFER_POOL_STATS              |
  | INNODB_CACHED_INDEXES                 |
  | INNODB_CMP                            |
  | INNODB_CMPMEM                         |
  | INNODB_CMPMEM_RESET                   |
  | INNODB_CMP_PER_INDEX                  |
  | INNODB_CMP_PER_INDEX_RESET            |
  | INNODB_CMP_RESET                      |
  | INNODB_COLUMNS                        |
  | INNODB_DATAFILES                      |
  | INNODB_FIELDS                         |
  | INNODB_FOREIGN                        |
  | INNODB_FOREIGN_COLS                   |
  | INNODB_FT_BEING_DELETED               |
  | INNODB_FT_CONFIG                      |
  | INNODB_FT_DEFAULT_STOPWORD            |
  | INNODB_FT_DELETED                     |
  | INNODB_FT_INDEX_CACHE                 |
  | INNODB_FT_INDEX_TABLE                 |
  | INNODB_INDEXES                        |
  | INNODB_METRICS                        |
  | INNODB_SESSION_TEMP_TABLESPACES       |
  | INNODB_TABLES                         |
  | INNODB_TABLESPACES                    |
  | INNODB_TABLESPACES_BRIEF              |
  | INNODB_TABLESTATS                     |
  | INNODB_TEMP_TABLE_INFO                |
  | INNODB_TRX                            |
  | INNODB_VIRTUAL                        |
  | KEYWORDS                              |
  | KEY_COLUMN_USAGE                      |
  | OPTIMIZER_TRACE                       |
  | PARAMETERS                            |
  | PARTITIONS                            |
  | PLUGINS                               |
  | PROCESSLIST                           |
  | PROFILING                             |
  | REFERENTIAL_CONSTRAINTS               |
  | RESOURCE_GROUPS                       |
  | ROLE_COLUMN_GRANTS                    |
  | ROLE_ROUTINE_GRANTS                   |
  | ROLE_TABLE_GRANTS                     |
  | ROUTINES                              |
  | SCHEMATA                              |
  | SCHEMATA_EXTENSIONS                   |
  | SCHEMA_PRIVILEGES                     |
  | STATISTICS                            |
  | ST_GEOMETRY_COLUMNS                   |
  | ST_SPATIAL_REFERENCE_SYSTEMS          |
  | ST_UNITS_OF_MEASURE                   |
  | TABLES                                |
  | TABLESPACES                           |
  | TABLESPACES_EXTENSIONS                |
  | TABLES_EXTENSIONS                     |
  | TABLE_CONSTRAINTS                     |
  | TABLE_CONSTRAINTS_EXTENSIONS          |
  | TABLE_PRIVILEGES                      |
  | TRIGGERS                              |
  | USER_ATTRIBUTES                       |
  | USER_PRIVILEGES                       |
  | VIEWS                                 |
  | VIEW_ROUTINE_USAGE                    |
  | VIEW_TABLE_USAGE                      |
  +---------------------------------------+
  79 rows in set (0.00 sec)
  ```

  

- performance_schema

  MySQL系统自带的数据库，这个数据库里主要保存MySQL服务器运行过程中的一些状态信息，可以用来监控MySQL服务的各类性能指标。包括统计最近执行了哪些语句，在执行过程的每个阶段都花费了多长时间，内存的使用情况等信息。

- sys

  MySQL系统自带的数据库，这个数据库主要是通过视图的形式把information_schema和performance._schema结合起来，帮助系统管理员和开发人员监控MySQL的技术性能。

### 2.2数据库在文件系统中的表示

使用`CREATE DATABASE`数据库名语句创建一个数据库的时候，在文件系统上实际发生了什么呢？其实很简单，每个数据库都对应数据目录下的一个子目录，或者说对应一个文件夹，每当新建一个数据库时，MySQL会帮我们做这两件事儿：

1. 在数据目录下创建一个和数据库名同名的子目录。
2. 在与该数据库名同名的子目录下创建一个名为db.opt的文件（仅限MySQL5.7及之前版本），这个文件中含了该数据库的各种属性，比如该数据库的字符集和比较规

我们再看一下我的计算机上的数据目录下的内容：

```shell
[root@Redis mysql]# cd /var/lib/mysql
[root@Redis mysql]# ll
总用量 122944
-rw-r-----. 1 mysql mysql       56 2月  28 15:19 auto.cnf
-rw-------. 1 mysql mysql     1680 2月  28 15:19 ca-key.pem
-rw-r--r--. 1 mysql mysql     1112 2月  28 15:19 ca.pem
-rw-r--r--. 1 mysql mysql     1112 2月  28 15:19 client-cert.pem
-rw-------. 1 mysql mysql     1680 2月  28 15:19 client-key.pem
-rw-r-----. 1 mysql mysql      332 3月   1 10:35 ib_buffer_pool
-rw-r-----. 1 mysql mysql 12582912 3月   1 11:17 ibdata1
-rw-r-----. 1 mysql mysql 50331648 3月   1 11:17 ib_logfile0
-rw-r-----. 1 mysql mysql 50331648 2月  28 15:19 ib_logfile1
-rw-r-----. 1 mysql mysql 12582912 3月   1 11:17 ibtmp1
drwxr-x---. 2 mysql mysql     4096 2月  28 15:19 mysql
srwxrwxrwx. 1 mysql mysql        0 3月   1 11:17 mysql.sock
-rw-------. 1 mysql mysql        5 3月   1 11:17 mysql.sock.lock
drwxr-x---. 2 mysql mysql     4096 2月  28 15:19 performance_schema
-rw-------. 1 mysql mysql     1676 2月  28 15:19 private_key.pem
-rw-r--r--. 1 mysql mysql      452 2月  28 15:19 public_key.pem
-rw-r--r--. 1 mysql mysql     1112 2月  28 15:19 server-cert.pem
-rw-------. 1 mysql mysql     1676 2月  28 15:19 server-key.pem
drwxr-x---. 2 mysql mysql    12288 2月  28 15:19 sys
[root@Redis mysql]# 
```

这个数据目录下的文件和子目录比较多，除了information_schema这个系统数据库外，其他的数据库在数据目录下都有对应的子目录。

### 2.3表在文件系统中的表示

我们的数据其实都是以记录的形式插入到表中的，每个表的信息其实可以分为两种：

1. 表结构的定义
2. 表中的数据

表结构就是该表的名称，表里边有多少列，每个列的数据类型，约束条件和索引，使用的字符集和比较规则等各种信息，这些信息都体现在了我们的建表语句中了。

#### 2.3.1 InnoDB存储擎模式

**1.表结构**

为了保存表结构，InnoDB在数据目录下对应的数据库子目录下创建了一个专门用于描述表结构的文件，文件名是这样：

```
表名.frm
```

比方说我们在atguigu数据库下创建一个名为test的表：

```mysql
mysql>USE atguigu;
Database changed
mysql>CREATE TABLE test(
->	c1 INT
->);
Query OK,0 rows affected (0.03 sec)
```

那在数据库atguigu对应的子目录下就会创建一个名为test.frm的用于描述表结构的文件。.frm文件的格式在不同的平台上都是相同的。这个后缀名为.r是以**二进制格式**存储的，我们直接打开是乱码的。

**2.表中数据和索引**

> 储备知识：（索引章节会讲到)
>
> - InnoDB.其实是使用页为基本单位来管理存储空间的，默认的页大小为16KB。
> - 对于InnoDB存储引擎来说，每个索引都对应着一棵B+树，该B+树的侮个节点都是一个数据页，数据页之间不必要是物理连续的，因为数据页之间有双向链表来维护着这些页的顺序。
> - IonoDB的聚簇索引的叶子节点存储了完整的用户记录，也就是所谓的索引即数据，数据即索引。

为了更好的管理这些页，InnoDB提出了一个**表空间**或者**文件空间**（英文名：table space或者file space)的概念，这个表空间是一个抽象的概念，它可以对应文件系统上一个或多个真实文件（不同表空间对应的文件数量可能不同)。每一个**表空间**可以被划分为很多个**页**，我们的表数据就存放在某个**表空间**下的某些页里。这里表空间有几种不同的类型：

**①系统表空间(system tables pace)**
默认情况下，InnoDB会在数据目录下创建一个名为**ibdata1**、大小为12M文件，这个文件就是对应的系统表空间在文件系统上的表示。怎么才12M?注意这个文件是自扩展文件，当不够用的时候它会自己增加文件大小。

当然，如果你想让系统表空间对应文件系统上多个实际文件，或者仅仅觉得原来的.bdata1这个文件名难听，那可以在MySQL启动时配置对应的文件路径以及它们的大小，比如我们这样修改一下my.cnf配置文件：

```shell
[server]
innodb_data_file_path=data1:512M;data2:512M:autoextend
```

这样在MySQL启动之后就会创建这两个512M大小的文件作为**系统表空间**，其中的autoextend表明这两个文件如果不够用会自动扩展data2文件的大小。

需要注意的一点是，在**一个MySQL服务器中，系统表空间只有一份**。从MySQL 5.5.7到MySQL 5.6.6之间的各个版本中，我们**表中的数据都会被默认存储到这个系统表空间**。

**②独立表空间(file-per-table table space)**
在MySQL5.6.6以及之后的版本中，InnoDB并不会默认的把各个表的数据存储到系统表空间中，而是为**每一个表建立一个独立表空间**，也就是说我们创建了多少个表，就有多少个独立表空间。使用独立表空间来存储表数据的话，会在该表所属数据库对应的子目录下创建一个表示该独立表空间的文件，文件名和表名相同，只不过添加了一个.ibd的扩展名而已，所以完整的文件名称长这样：

````
表名.ibd
````

比如：我们使用了独立表空间去存储atguigu数据库下的test表的话，那么在该表所在数据库对应的atguigu目录下会为test表创建这两个文件：

```
test.frm
test.ibd
```

其中**test.ibd**文件就用来存储test表中的数据和索引。

**③系统表空间与独立表空间的设置**

我们可以自己指定使用系统表空间还是独立表空间来存储数据，这个功能由启动参数innodb_file_per_table控制，比如说我们想刻意将表数据都存储到系统表空间时，可以在启动MySQL服务器的时候这样配置：

```shell
[server]
innodb_file_per_table=0 #0:代表使用系统表空间；1：代表使用独立表空间
```

默认情况:

```mysql
mysql> show variables like 'innodb_file_per_table';
+-----------------------+-------+
| Variable_name         | Value |
+-----------------------+-------+
| innodb_file_per_table | ON    |
+-----------------------+-------+
1 row in set (0.00 sec)
```

说明：innodb_file_per_table参数的修改只对新建的表起作用，对于已经分配了表空间的表不起作用如果我们想把已经存在系统表空间中的表转移到独立表空间，可以使用下边的语法：

```mysql
ALTER TABLE TABLESPACE [=] innodb_file_per_table;
```

或者把已经存在独立表空间的表转移到系统表空间，可以使用下边的语法：

```mysql
ALTER TABLE 表名 TABLESPACE [=] innodb_system;
```

其中中括号扩起来的=可有可无，比如：我们想把test表从独立表空间移动到系统表空间，可以这么写：

```mysql
ALTER TABLE test TABLESPACE innodb_system;
```

**④其他类型的表空间**

随着MySQL的发展，除了上述两种老牌表空间之外，现在还新提出了一些不同类型的表空间，比如通用表空间[general table space)、临时表空间(temporary table space)等。

**3.疑问**

.frm在MySQL8中不存在了。那去哪里了呢？

这就需要解析ibd文件。Oracle官方将.frm文件的信息及更多信息移动到叫做序列化字典信息(Serialized Dictionary Information,SDl),SDl被写在.ibd文件内部。MySQL8.0属于Oracle旗下，同理

为了从IBD文件中提取SDI信息，Oracle提供了一个应用程序ibd2sdi。

==ibd2sdi官方文档==https://dev.mysql.com/doc/refman/8.0/en/ibd2sdi.html

这个工具不需要下载，MySQL8自带的有，只要你配好环境变量就能到处用。

(1)查看表结构

到存储ibd文件的目录下，执行下面的命令：

```shell
ibd2sdi --dump-file=student.txt student.ibd
```

#### 2.3.2 MyISAM存储擎模式

**1.表结构**

在存储表结构方面，MyISAM和InnoDB一样，也是在数据目录下对应的数据库子目录下创建了一个专门用于描述表结构的文件：
`表名.frm`
**2.表中数据和索引**

在MyISAM中的索引全部都是二级索引，该存储引擎的数据和索引是分开存放的。所以在文件系统中也是使用不同的文件来存储数据文件和索引文件，同时表数据都存放在对应的数据库子目录下。假如test表使用MyISAM存储引擎的话，那么在它所在数据库对应的atguigu目录下会为test表创建这三个文件：

```shell
test.frm   存储表结构
test.MYD   存储数据(MYData)
test.MYI   存储索引
```

其中**test.MYD**代表表的数据文件，也就是我们插入的用户记录。采用独立表存储模式，每个表对应一个MYD文件；

**test.MYI**代表表的索引文件，我们为该表创建的索引都会放到这个文件中。

举例：创建一个**MyISAM**表，使用**ENGINE**选项显式指定引擎。因为**InnoDB**是默认引擎。

```mysql
CREATE TABLE student_myisam(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(64) DEFAULT NULL,
    age int DEFAULT NULL,
    sex varchar(2) DEFAULT NULL,
    PRIMARY KEY (id)
)ENGINE=MYISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;
```

```mysql
MySQL8版本中：

[root@localhost mysql]# cd ./test
[root@localhost test]# ll
总用量 12
-rw-r-----. 1 mysql mysql 4327 3月   1 11:51 student_myisam_362.sdi  -- 存储元数据
-rw-r-----. 1 mysql mysql    0 3月   1 11:51 student_myisam.MYD      -- 存储数据
-rw-r-----. 1 mysql mysql 1024 3月   1 11:51 student_myisam.MYI      -- 存储索引
```

对于InnoDB表，SDI与InnoDB用户表空间中的数据一起存储。对于MyISAM和其他存储引擎，它被写入数据目录中的.sdi文件。

在MySQL8.0中，MyISAM存储引擎不提供分区支持。在以前版本的MySQL中创建的分区MyISAM表不能在MySQL8.0中使用.

```mysql
MySQL5.7版本中：

[root@Redis mysql]# cd ./test
[root@Redis test]# ll
总用量 20
-rw-r-----. 1 mysql mysql   61 3月   1 11:48 db.opt
-rw-r-----. 1 mysql mysql 8642 3月   1 11:48 student_myisam.frm  -- 存储表结构
-rw-r-----. 1 mysql mysql    0 3月   1 11:48 student_myisam.MYD  -- 存储数据
-rw-r-----. 1 mysql mysql 1024 3月   1 11:48 student_myisam.MYI  -- 存储索引
```

可以发现，在之前的数据库版本中，MyISAM引擎已存在frm文件，但是在MySQL8之后也和InnoDBi引擎一样去掉了，放入了sdi文件中。

### 2.4小结

举例：数据库a,表b。
1、如果表b采用InnoDB,data/a中会产生1个或者2个文件：

- b.frm:描述表结构文件，字段长度等
- 如果采用**系统表空间**模式的，数据信息和索引信息都存储在ibdata1中
- 如果采用**独立表空间**存储模式，data/a中还会产生b.ibd文件（存储数据信息和索引信息）

此外：
①MySQL5.7中会在data/a的目录下生成**db.opt**文件用于保存数据库的相关配置。比如：字符集、比较规侧。而
MySQL8.0不再提供db.opt文件。

②MySQL8.0中不再单独提供b.frm,而是合并在b.ibd文件中。

2、如果表b采用MyISAM,data/a中会产生3个文件：

- MySQL5.7中：b.frm:描述表结构文件，字段长度等。
- MySQL8.0中b.xxx.sdi:描述表结构文件，字段长度等
- b.MYD(MYData):数据信息文件，存储数据信息（如果采用独立表存储模式）

### 2.5视图在文件系统中的表示

我们知道MySQL中的视图其实是**虚拟的表**，也就是某个查询语句的一个别名而已，所以在存储视图的时候是不需要存储真实的数据的，只需要把它的结构存储起来就行了。和表一样，描述视图结构的文件也会被存储到所属数据库对应的子目录下边，只会存储一个**视图名.frm**的文件。

### 2.6其他的文件

除了我们上边说的这些用户自己存储的数据以外，数据目录下还包括为了更好运行程序的一些额外文件，主要包括这几种类型的文件：

- 服务器进程文件
  我们知道每运行一个MySQL服务器程序，都意味着启动一个进程。MySQL服务器会把自己的进程ID写入到一个文件中。
- 服务器日志文件
  在服务器运行过程中，会产生各种各样的日志，比如常规的查询日志、错误日志、二进制日志、rdo日志等。这些日志各有各的用途，后面讲解。
- 默认/自动生成的SSL和RSA证书和密钥文件
  主要是为了客户端和服务器安全通信而创建的一些文件。