# key键操作

keys * 查看当前库所有key

existst key 判断某个key是否存在

type key 查看key的类型

del key 删除指定的key的数据

unlink key 根据value选择非阻塞删除

expire key 10 为指定的key设置过期时间

ttl key 查看指定的key还有多少秒过期，-1表示永不过期，-2表示已过期

---



select 切换数据库

dbsize 查看当前数据库key的数量

flushdb 清空当前库

flushall 通杀所有库

---



# String字符串

## 常用命令

get <key> 查询对应键值

append <key><value> 将给定的<value>值追加到原始值的末尾

strlen <key>获取值的长度

setnx <key> <value> 只有在key不存在时，设置key的值

setex <key> <timeout> <value> 为指定的 key 设置值及其过期时间。如果 key 已经存在， setex 命令将会替换旧的值。

---



incr <key> 将key中储存的值加一，只能对数字值操作，如果为空，新增值为 1

decr <key> 将key中储存的值减一，只能对数字值操作，如果为空，新增值为 -1



incrby/decrby <key> <步长> 将key中储存的值增减，自定义步长

原子性

---



mset <key1> <value1> <key2> <value2> ......

同时设置一个或多个key-value对

mget <key1> <key> <key3>......

同时获取一个或多个value

msetnx <key1> <value1> <key2> <value2> ......

同时设置一个或多个key-value值，当且仅当所有给定的key都不存在

**原子性，有一个失败则都失败**



---



getrange <key> 起始位置 结束位置 获得值的范围

```redis
127.0.0.1:6379> set name abcdefghijk
OK
127.0.0.1:6379> getrange name 0 3
"abcd"
```

---

setrange <key> 起始位置 <value>

用<value>覆写<key>所储存的的字符串值，从起始位置开始

~~~redis
127.0.0.1:6379> get name
"abcdefghijk"
127.0.0.1:6379> setrange name 3 123
(integer) 11
127.0.0.1:6379> get name
"abc123ghijk"
~~~

---

setex <key> 过期时间 <value>

设置键值的同时设置过期时间，单位秒

~~~
127.0.0.1:6379> setex age 10 30
OK
127.0.0.1:6379> ttl age
(integer) 4
127.0.0.1:6379> ttl age
(integer) -2
~~~

---

getset <key><value>

以旧换新，设置新值的同时获得旧值

~~~redis
127.0.0.1:6379> getset name 123
"abc123ghijk"
127.0.0.1:6379> 
~~~

---

## 数据结构

String的数据结构为简单动态字符串(Simple Dynamic String，缩写SDS)。是可以
修改的字符串，内部结构实现上类似于Java的ArrayList，采用预分配冗余空间的方式
来减少内存的频繁分配..

![image-20220111152149703](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220111152149703.png)

如图中所示，内部为当前字符串实际分配的空间capacity一般要高于实际字符串长度
len。当字符串长度小于1M时，扩容都是加倍现有的空间，如果超过1M，扩容时一次
只会多扩1M的空间。需要注意的是字符串最大长度为512M。

# list集合

单键多值。
Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头
部(左边)或者尾部(右边)。
它的底层实际是个双向链表，对两端的操作性能很高，通过索引下标的操作中间的节
点性能会较差。

![image-20220111152433204](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220111152433204.png)

## 常用命令

lpshu/rpush <key> <value1> <value2> <value3>...从左边/右边插入一个或多个值

~~~redis
127.0.0.1:6379> lpush k1 v1 v2 v3
(integer) 3
127.0.0.1:6379> lrange k1 0 -1
1) "v3"
2) "v2"
3) "v1"
127.0.0.1:6379> rpush k2 v1 v2 v3
(integer) 3
127.0.0.1:6379> lrange k2 0 -1
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> 
~~~

k1 v3 v2 v1

k2 v3 v2 v1

---

lpop/rpop <key> 从左边/右边吐出一个值，**值在键在，值光键亡**

---

rpoplpush <key1> <key2> 从 <key1> 列表右边吐出一个值，插到<key2>列表左边

~~~redis
127.0.0.1:6379> lpush k1 v1 v2 v3
(integer) 3
127.0.0.1:6379> rpush k2 11 22 33
(integer) 3
127.0.0.1:6379> rpoplpush k1 k2
"v1"
127.0.0.1:6379> lrange k2 0 -1
1) "v1"
2) "11"
3) "22"
4) "33"
127.0.0.1:6379> 
~~~

---

lrange <key> <start> <stop>按照索引下标获取元素（从左到右）

lrange mylist 0 -1 (0 表示左边第一个，-1 表示右边第一个) 获取所有

~~~redis
127.0.0.1:6379> lrange k2 0 -1
1) "v1"
2) "11"
3) "22"
4) "33"
127.0.0.1:6379> 
~~~



---

lindex <key><index>按照索引下标获得元素(从左到右)

~~~redis
127.0.0.1:6379> lindex k1 0
"v3"
~~~



---

llen <key>获得列表长度。

~~~redis
127.0.0.1:6379> llen k2
(integer) 4
~~~



---

linsert <key> before <value><newvalue>在<value>的后面插入<newvalue>插入值。

~~~redis
127.0.0.1:6379> linsert k2 before "33" "new33"
(integer) 5
127.0.0.1:6379> lrange k2 0 -1
1) "v1"
2) "11"
3) "22"
4) "new33"
5) "33"
~~~



---

lrem <key><n><value>从左边删除n个value(从左到右)

~~~redis
127.0.0.1:6379> lrange k2 0 -1
1) "v1"
2) "11"
3) "22"
4) "new33"
5) "33"
127.0.0.1:6379> lrem k2 1 22
(integer) 1
127.0.0.1:6379> lrange k2 0 -1
1) "v1"
2) "11"
3) "new33"
4) "33"
127.0.0.1:6379> 
~~~



---

lset<key><index><value>将列表 key下标为index的值替换成value

~~~redis
127.0.0.1:6379> lset k2 0 vv1
OK
127.0.0.1:6379> lrange
(error) ERR wrong number of arguments for 'lrange' command
127.0.0.1:6379> lrange k2 0 -1
1) "vv1"
2) "11"
3) "new33"
4) "33"
127.0.0.1:6379> 
~~~



---

## 数据结构

List的数据结构为快速链表quickList。
首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是ziplist，也即是压缩列表。
它将所有的元素紧挨着一起存储，分配的是一块连续的内存。
当数据量比较多的时候才会改成quickist. IF
因为普通的链表需要的附加指针空间太大，会比较浪费空间。比如这个列表里存的只是int类型的数据，结构上还需要两个额外的指针prev和next。

![image-20220111155349719](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220111155349719.png)

Redis将链表和ziplist结合起来组成了quicklist。也就是将多个ziplist使用双向指
针串起来使用。这样既满足了快速的插入删除性能，又不会出现太大的空间冗余

---

# Set集合

​	Redis set对外提供的功能与list类似是一个列表的功能，特殊之处在于set是可以自动
排重的，当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。
​	Redis的 Set是string类型的无序集合。它底层其实是一个value为null的hash表，所以添加，删除，查找的复杂度都是O(1)。

​	一个算法，随着数据的增加，执行时间的长短，如果是O(1)，数据增加，查找数据的时间不变

---

## 常用命令

---

sadd <key><value1><value2>.....
将一个或多个nember元素加入到集合key中，已经存在的 member元素将被忽略。

---

smembers<key>取出该集合的所有值。

~~~redis
127.0.0.1:6379> sadd k1 v1 v2 v3
(integer) 3
127.0.0.1:6379> smembers k1
1) "v3"
2) "v1"
3) "v2"
~~~



---

sismember <key><value>判断集合<key>是否为含有该<value＞值，有1，没有0.

~~~redis
127.0.0.1:6379> sismember k1 v1
(integer) 1
127.0.0.1:6379> sismember k1 v
(integer) 0
~~~



---

scard<key>返回该集合的元素个数。

---

srem <key><value1><value2>....删除集合中的某个元素。

---

spop <key>随机从该集合中吐出一个值。

---

srandmember<key><n>随机从该集合中取出n个值。不会从集合中删除。

---

~~~redis
127.0.0.1:6379> scard k1
(integer) 3
127.0.0.1:6379> srem k1 v1
(integer) 1
127.0.0.1:6379> smembers k1
1) "v3"
2) "v2"
127.0.0.1:6379> spop k1
"v2"
127.0.0.1:6379> smembers k1
1) "v3"
127.0.0.1:6379> srandmember k1 1
1) "v3"
127.0.0.1:6379> smembers k1
1) "v3"
~~~

---

smove <source><destination>value 把集合中一个值从一个集合移动到另一个集合

~~~redis
127.0.0.1:6379> sadd k1 v1 v2 v3
(integer) 3
127.0.0.1:6379> sadd k2 v4 v5 v6
(integer) 3
127.0.0.1:6379> smove k1 k3 v3
(integer) 1
127.0.0.1:6379> smembers k1
1) "v1"
2) "v2"
127.0.0.1:6379> smembers k2
1) "v4"
2) "v6"
3) "v5"
~~~



---

sinter <key1><key2>返回两个集合的交集元素。

---

sunion <key1><key2>返回两个集合的并集元素。

---

sdiff<key1><key2>返回两个集合的差集元素(key1中的，不包含key2中的)

---

~~~redis
127.0.0.1:6379> sadd k4 v4 v7 v8
(integer) 3
127.0.0.1:6379> sinter k2 k4
1) "v4"
127.0.0.1:6379> sunion k1 k2
1) "v4"
2) "v1"
3) "v6"
4) "v2"
5) "v5"
127.0.0.1:6379> sdiff k1 k2
1) "v1"
~~~

---

## 数据结构

Set数据结构时dict字典,字典是哈希表实现的。

Java中HashSet的内部实现使用的是HashMap，只不过所有的value都指向同一个对象。

Redis的set结构也是一样，它的内部结构也使用hash结构，所有的value都指向同一个内部值。

---

# Hash

Redis hash是一个键值对集合。
Redis hash是一个 string类型的field和value的映射表，hash 特别适合用于存储对象。类似Java里面的 Map<String,Object>
用户ID为查找的 key，存储的value用户对象包含姓名，年龄，生日等信息，如果用普通的 key/value结构来存储。
主要有以下2种存储方式：

![image-20220112101216937](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112101216937.png)

![image-20220112101228244](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112101228244.png)

![image-20220112101236291](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112101236291.png)

## 常用命令

hset <key><field><value>给<key>集合中的 <field>键赋值<value>

---

hget<keyl><field>从<key1>集合<field>取出value

---

hmset <key1><field1><value1><field2><value2>...批量设置hash的值

---

hexists<key1><field>查看哈希表key中，给定域field是否存在。

---

hkeys<key>列出该hash集合的所有field

---

hvals<key>列出该hash集合的所有value

---

hincrby <key><field><increment>为哈希表 key 中的域 field的值加上增量 1  -1

---

hsetnx <key><field><value>将哈希表 key 中的域field的值设置为value，当且仅当域field不存在.

---

~~~redis
127.0.0.1:6379> hset user:1001 id 1
(integer) 1
127.0.0.1:6379> hset user:1001 name zhangsan
(integer) 1
127.0.0.1:6379> hget user:1001 name
"zhangsan"
127.0.0.1:6379> hmset user:1002 id 2 name lisi age 20
OK
127.0.0.1:6379> hexists user:1002 age
(integer) 1
127.0.0.1:6379> hkeys user:1002
1) "id"
2) "name"
3) "age"
127.0.0.1:6379> hvals user:1002
1) "2"
2) "llisi"
3) "20"
127.0.0.1:6379> hincrby user:1002 age 1
(integer) 21
127.0.0.1:6379> hincrby user:1002 age -1
(integer) 20
127.0.0.1:6379> hsetnx user:1002 gender 1
(integer) 1
127.0.0.1:6379> hkeys user:1002
1) "id"
2) "name"
3) "age"
4) "gender"
127.0.0.1:6379> hsetnx user:1002 gender 1
(integer) 0
~~~

## 数据结构

​	Hash类型对应的数据结构是两种：ziplist(压缩列表)，hashtable(哈希表)。当
field-value长度较短且个数较少时，使用ziplist，否则使用hashtable。

---

# Zset(sorted set)

​	Redis有序集合 zset与普通集合 set非常相似，是一个没有重复元素的字符串集合。
不同之处是有序集合的每个成员都关联了一个评分(score)，这个评分(score)被用来按照从最低分到最高分的方式排序集合中的成员。集合的成员是唯一的，但是评分可以是重复了。
因为元素是有序的，所以你也可以很快的根据评分(score)或者次序(position)来获取一个范围的元素。
​	访问有序集合的中间元素也是非常快的，因此你能够使用有序集合作为一个没有重复成员的智能列表。

## 常用命令

zadd <key> <score1> <value1> <score2> <value2> 

将一个或多个member元素及其 score值加入到有序集key当中。

~~~redis
127.0.0.1:6379> zadd topn 200 java 300 c++ 400 c 500 python 600 vue
(integer) 5
127.0.0.1:6379> zrange topn 0 -1
1) "java"
2) "c++"
3) "c"
4) "python"
5) "vue"
~~~



---

zrange <key><start><stop>[WITHSCORES]
返回有序集 key中，下标在<start><stop>之间的元素。
带WITHSCORES，可以让分数一起和值返回到结果集。

~~~redis
127.0.0.1:6379> zrangebyscore topn 200 600
1) "java"
2) "c++"
3) "c"
4) "python"
5) "vue"
127.0.0.1:6379> zrangebyscore topn 200 600 withscores
 1) "java"
 2) "200"
 3) "c++"
 4) "300"
 5) "c"
 6) "400"
 7) "python"
 8) "500"
 9) "vue"
10) "600"
~~~



---

zrangebyscore key min max [withscores] [limit offset count]
返回有序集 key中，所有score值介于min和max之间(包括等于min或max)的成员。有序集成员按score值递增(从小到大)次序排列。

~~~redis
127.0.0.1:6379> zrangebyscore topn 200 600 withscores
 1) "java"
 2) "200"
 3) "c++"
 4) "300"
 5) "c"
 6) "400"
 7) "python"
 8) "500"
 9) "vue"
10) "600"
~~~



---

zrevrangebyscore key max min [withscores] [limit offset count]
同上，改为从大到小排列

~~~redis
127.0.0.1:6379> zrevrangebyscore topn 600 200 withscores
 1) "vue"
 2) "600"
 3) "python"
 4) "500"
 5) "c"
 6) "400"
 7) "c++"
 8) "300"
 9) "java"
10) "200"
~~~

---

zincrby <key><increment><value> 为元素的score加上增量。

---

zrem <key><value>删除该集合下，指定值的元素

~~~redis
127.0.0.1:6379> zincrby topn 1 java
"201"
127.0.0.1:6379> zrem topn java
(integer) 1
127.0.0.1:6379> zrange topn 0 -1
1) "c++"
2) "c"
3) "python"
4) "vue"

~~~



---

zcount<key><min><max>统计该集合，分数区间内的元素个数

~~~redis
127.0.0.1:6379> zcount topn 300 500
(integer) 3
~~~



---

zrank <key><value>返回该值在集合中的排名，从0开始。

~~~redis
127.0.0.1:6379> zrank topn python
(integer) 2
~~~

---

## 数据结构

​	SortedSet(zset)是Redis提供的一个非常特别的数据结构，一方面它等价于Java的数据结构Map<String,Double>，可以给每一个元素value赋予一个权重score，另一方面它又类似于TreeSet，内部的元素会按照权重score进行排序，可以得到每个元素的名次，还可以通过score的范围来获取元素的列表。
zset底层使用了两个数据结构。
​	(1)hash，hash的作用就是关联元素value和权重score，保障元素value的唯一性，可以通过元素value找到相应的score值。
​	(2)跳跃表，跳跃表的目的在于给元素value排序，根据score的范围获取元素列表。

### 跳跃表（跳表）

1、简介
	有序集合在生活中比较常见，例如根据成绩对学生排名，根据得分对玩家排名等。对于有序集合的底层实现，可以用数组、平衡树、链表等。数组不便元素的插入、删除；平衡树或红黑树虽然效率高但结构复杂；链表查询需要遍历所有效率低。Redis采用的是跳跃表。跳跃表效率堪比红黑树，实现远比红黑树简单。
2、实例
	对比有序链表和跳跃表，从链表中查询出51

(1)有序列表

![image-20220112105623180](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112105623180.png)

要查找值为51的元素，需要从第一个元素开始依次查找、比较才能找到。共
需要6次比较。

(2)跳跃表

![image-20220112105809044](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112105809044.png)

从第2层开始，1节点比51节点小，向后比较。。
21节点比51节点小，继续向后比较，后面就是NULL了，所以从21节点向下到第1层

在第1层，41节点比51节点小，继续向后，61节点比51节点大，所以从41向下。

在第0层，51节点为要查找的节点，节点被找到，共查找4次。

 从此可以看出跳跃表比有序链表效率要高



# 发布与订阅

![image-20220112132006495](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112132006495.png)

![image-20220112132426924](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112132426924.png)

SUBSCRIBE channel1

publish channel1 hello

---

# Redis新数据类型

## Bitmaps

​	现代计算机用二进制(位)作为信息的基础单位，1个字节等于8位，例如“abc”字符串是由3个字节组成，但实际在计算机存储时将其用二进制表示，“abc”分别对应的 ASCII码分别是 97、98、99，对应的二进制分别是01100001、01100010和01100011，如下图

![image-20220112133124245](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112133124245.png)

	合理地使用操作位能够有效地提高内存使用率和开发效率。
Redis提供了Bitmaps这个“数据类型”可以实现对位的操作：
	(1)Bitmaps 本身不是一种数据类型，实际上它就是字符串(key-value)，
但是它可以对字符串的位进行操作。
	(2)Bitmaps单独提供了一套命令，所以在Redis中使用 Bitmaps和使用字符串的方法不太相同。可以把 Bitmaps想象成一个以位为单位的数组，数组的每个单元只能存储0和1，数组的下标在Bitmaps中叫做偏移量。

![image-20220112133328638](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112133328638.png)

### 常用命令

setbit <key> <offset> <value> 设置Bitmaps中某个偏移量的值(0或1)

~~~redis
127.0.0.1:6379> setbit user:2022 1 1
(integer) 0
127.0.0.1:6379> setbit user:2022 6 1
(integer) 0
127.0.0.1:6379> setbit user:2022 11 1
(integer) 0
127.0.0.1:6379> setbit user:2022 15 1
(integer) 0
127.0.0.1:6379> setbit user:2022 19 1
(integer) 0
~~~

---

getbit <key> <offset>获取Bitmaps中某个偏移量的值

~~~redis
127.0.0.1:6379> getbit user:2022 1
(integer) 1
127.0.0.1:6379> getbit user:2022 6
(integer) 1
127.0.0.1:6379> getbit user:2022 8
(integer) 0
~~~

bitcount
	统计字符串被设置为1的bit数。一般情况下，给定的整个字符串都会被进行计数，通过指定额外的start 或end参数，可以让计数只在特定的位上进行。start 和end参数的设置，都可以使用负数值：比如-1表示最后一个位，而-2表示倒数第二个位，start、end是指bit组的字节的下标数，二者皆包含。

---

bitcount <key> [start end]统计字符串从start字节到end字节bit值为1的数量

![image-20220112134827605](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112134827605.png)

![image-20220112134833392](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220112134833392.png)

---

## HyperLogLog

### 简介

	在工作当中，我们经常会遇到与统计相关的功能需求，比如统计网站PV(PageView页面访问量)，可以使用Redis的incr、incrby轻松实现。
	但像 UV(UniqueVisitor，独立访客)、独立IP数、搜索记录数等需要去重和计数的问题如何解决？这种求集合中不重复元素个数的问题称为基数问题。
解决基数问题有很多种方案：
	(1)数据存储在 MySQL表中，使用distinct count计算不重复个数
	(2)使用Redis提供的hash、set、bitmaps等数据结构来处理。
以上的方案结果精确，但随着数据不断增加，导致占用空间越来越大，对于非常大的数据集是不切实际的。
能否能够降低一定的精度来平衡存储空间？Redis推出了HyperLogLog
	Redis HyperLogLog是用来做基数统计的算法，HyperLogLog的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定的、并且是很小的。
	在 Redis 里面，每个 HyperLogLog 键只需要花费**12 KB**内存，就可以计算接近**2^64**个不同元素的基数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。.
	但是，因为 HyperLogLog只会根据输入元素来计算基数，而不会储存输入元素本身，所以HyperLogLog不能像集合那样，返回输入的各个元素。
	什么是基数？
	比如数据集(1，3，5，7，5，7，8，那么这个数据集的基数集为(1，3，5，7，8，基数(不重复元素)为5。基数估计就是在误差可接受的范围内，快速计算基数。

### 常用命令

pfadd <key> element> [element...] 添加指定元素到HyperLogLog中

~~~redis
127.0.0.1:6379> pfadd program "java"
(integer) 1
127.0.0.1:6379> pfadd program "php"
(integer) 1
127.0.0.1:6379> pfadd program "java"
(integer) 0
127.0.0.1:6379> pfadd program "c++" "python"
(integer) 1
~~~

将所有元素添加到指定HyperLogLog数据结构中。如果执行命令后HLL估计的
近似基数发生变化，则返回1，否则返回0。

---

pfcount <key> [key...] 计算HLL的近似基数，可以计算多个HLL，比如用HLL存储每
天的UV，计算一周的UV可以使用7天的UV合并计算即可。

~~~~redis
127.0.0.1:6379> pfadd program "java"
(integer) 1
127.0.0.1:6379> pfcount program
(integer) 1
127.0.0.1:6379> pfadd program "java"
(integer) 0
127.0.0.1:6379> pfcount program
(integer) 1
~~~~

---

pfmerge<destkey><sourcekey>[sourcekey...] 将一个或多个HLL合并后的结果存
储在另一个HLL中，比如每月活跃用户可以使用每天的活跃用户来合并计算可得。

~~~redis
127.0.0.1:6379> pfadd program "java"
(integer) 1
127.0.0.1:6379> pfcount program
(integer) 1
127.0.0.1:6379> pfadd program "java"
(integer) 0
127.0.0.1:6379> pfcount program
(integer) 1
127.0.0.1:6379> pfadd k1 "a"
(integer) 1
127.0.0.1:6379> pfadd k1 "b"
(integer) 1
127.0.0.1:6379> pfmerge k100 k1 program
OK
127.0.0.1:6379> pfcount k100
(integer) 3
~~~

---

## Geospatial

​	Redis 3.2 中增加了对 GEO 类型的支持。GEO，Geographic，地理信息的缩写。
该类型，就是元素的2维坐标，在地图上就是经纬度。redis基于该类型，提供了经纬
度设置，查询，范围查询，距离查询，经纬度Hash等常见操作。



### 常用命令

geoadd<key>< longitude><latitude><member> [longitude latitude member...] 添
加地理位置(经度，纬度，名称)。

~~~redis
127.0.0.1:6379> geoadd china:city 121.47 31.23 shanghai
(integer) 1
127.0.0.1:6379> geoadd china:city 106.50 29.53 chongqing 114.65 22.52 shenzhen 116.38 39.90 beijing
(integer) 3
~~~

​	两极无法直接添加，一般会下载城市数据，直接通过Java程序一次性导入。有效的经度从-180 度到 180 度。有效的纬度从 -85.05112878 度到85.05112878度。
当坐标位置超出指定范围时，该命令将会返回一个错误。已经添加的数据，是无法再次往里面添加的。

---

geopos<key><member>[member...]获得指定地区的坐标值。

~~~redis
127.0.0.1:6379> geopos china:city beijing
1) 1) "116.38000041246414185"
   2) "39.90000009167092543"
~~~

---

geodist<key><member1><member2 [m|km|ft|mi ] 获取两个位置之间的直线距
离

单位：
m表示单位为米[默认值]。
km表示单位为千米。
mi表示单位为英里。
ft表示单位为英尺。
如果用户没有显式地指定单位参数，那么GEODIST默认使用米作为单位

~~~redis
127.0.0.1:6379> geodist china:city beijing shanghai
"1068153.5181"
127.0.0.1:6379> geodist china:city beijing shanghai km
"1068.1535"
~~~

---

georadius<key>< longitude><latitude>radius m |km |ft |mi 以给定的经纬度为中心，
找出某一半径内的元素

~~~redis
127.0.0.1:6379> georadius china:city 110 30 1000 km
1) "chongqing"
2) "shenzhen"
~~~

---

# Redis事务和锁机制

## 事务的定义

​	Redis 事务是一个单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事
务在执行的过程中，不会被其他客户端发送来的命令请求所打断。
​	Redis 事务的主要作用就是串联多个命令防止别的命令插队。

## Multi、Exec、discard.

​	从输入 **Multi** 命令开始，输入的命令都会依次进入命令队列中，但不会执行，直到输入**Exec**后，Redis 会将之前的命令队列中的命令依次执行。组队的过程中可以通过**discard**来放弃组队。

![image-20220113131245508](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113131245508.png)

~~~redis
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> set k1 v1
QUEUED
127.0.0.1:6379(TX)> set k2 v2
QUEUED
127.0.0.1:6379(TX)> exec
1) OK
2) OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> set a1 v1
QUEUED
127.0.0.1:6379(TX)> set a2 v2
QUEUED
127.0.0.1:6379(TX)> discard
OK
~~~

### 事务的错误处理

组队中某个命令出现了报告错误，执行时整个的所有队列都会被取消。

![image-20220113131934836](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113131934836.png)

~~~redis
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> set b1 v1
QUEUED
127.0.0.1:6379(TX)> set b2 v2
QUEUED
127.0.0.1:6379(TX)> set b3
(error) ERR wrong number of arguments for 'set' command
127.0.0.1:6379(TX)> exec
(error) EXECABORT Transaction discarded because of previous errors.
~~~



如果执行阶段某个命令报出了错误，则只有报错的命令不会被执行，而其他的命令都会执行，不会回滚

![image-20220113132043657](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113132043657.png)

~~~redis
127.0.0.1:6379> multi
OK
127.0.0.1:6379(TX)> set c1 v1
QUEUED
127.0.0.1:6379(TX)> incr c1
QUEUED
127.0.0.1:6379(TX)> set c2 v2
QUEUED
127.0.0.1:6379(TX)> exec
1) OK
2) (error) ERR value is not an integer or out of range
3) OK
~~~

---

## 锁

![image-20220113134016188](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113134016188.png)

## 悲观锁

​	**悲观锁(Pessimistic Lock)**，顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会block直到它拿到锁。**传统的关系型数据库里边就用到了很多这种锁机制**，比如**行锁，表锁**等，**读锁，写锁**等，都是在做操作之前先上锁。

![image-20220113133338942](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113133338942.png)

## 乐观锁

**乐观锁(Optimistic Lock)，**顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。**乐观锁适用于多读的应用类型，这样可以提高吞吐量。** ***Redis就是利用这种check-and-set机制实现事务的。***

![image-20220113133608352](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113133608352.png)

---

## WATCH key [key...]

​	在执行multi之前，先执行watch key1[key2]，可以监视一个(或多个)key，**如果在事务**
**执行之前这个(或这些)key 被其他命令所改动，那么事务将被打断**

![image-20220113134832695](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220113134832695.png)

## unwatch

​	取消WATCH 命令对所有key的监视。
​	如果在执行 WATCH 命令之后，EXEC 命令或DISCARD 命令先被执行了的话，那么就不需要再执行 UNWATCH 了

## Redis 事务三特性

### 单独的隔离操作

​	事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会
被其他客户端发送来的命令请求所打断。

### 没有隔离级别的概念 

​	队列中的命令没有提交之前都不会实际被执行，因为事务提交前任何指令都
不会被实际执行。

### 不保证原子性。

​	事务中如果有一条命令执行失败，其后的命令仍然会被执行，没有回滚。

---

# 持久化操作

## ROB(Redis DataBase)

### 是什么

​	在指定的**时间间隔**内将内存中的**数据集快照**写入磁盘，也就是行话讲的 Snapshot快
照，它恢复时是将快照文件直接读到内存里

---

### 备份是如何执行的

​	Redis.会单独创建(fork)一个子进程来进行持久化，会先将数据写入到一个临时文件中，待持久化过程都结束了，再用这个临时文件昔换上次持久化好的文件。整个过程中，主进程是不进行任何IO操作的，这就确保了极高的性能 如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。**RDB的缺点是最后一次持久化后的数据可能丢失。**

---

####  Fork

- Fork的作用是复制一个与当前进程**一样的进程**。新进程的所有数据(变量、环境变量、程序计数器等)数值都和原进程一致，但是是一个全新的进程，并作为**原进程的子进程**
- 在Linux程序中，fork会产生一个和父进程完全相同的子进程，但子进程在此后会被exec系统调用，出于效率考虑，Linux中引入了“**写时复制技术**”
- 一般情况**父进程和子进程会共用同一段物理内存，**只有进程空间的各段的内容要发生变化时，才会将父进程的内容复制一份给子进程。

#### 持久化流程

![image-20220114100930717](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114100930717.png)

---

#### dump.rdb文件

在redis.conf中配置文件名称，默认为dump.rdb

---

### 优势

- 适合大规模的数据恢复
- 对数据完整性和一致性要求不高更适合使用
- 节省磁盘空间
- 恢复速度快

---



###  劣势

- Fork的时候，内存中的数据被克隆了一份，大致2倍的膨胀性需要考虑
- 虽然 Redis在fork时使用了写时拷贝技术，但是如果数据庞大时还是比较消耗性能。
- 在备份周期在一定间隔时间做一次备份，所以如果Redis意外down掉的话，就会丢失最后一次快照后的所有修改

---



## AOF(Append Only File)

### 是什么。

​	**以日志的形式来记录每个写操作(增量保存)**，将Redis执行过的所有写指令记录下来(**读操作不记录**)，**只许追加文件但不可以改写文件**，redis启动之初会读取该文件重新构建数据，换言之，redis重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作

---

### AOF持久化流程

- (1)客户端的请求写命令会被 append追加到AOF缓冲区内；
- (2)AOF缓冲区根据AOF持久化策略[always，everysec，no]将操作sync同步到磁盘的AOF文件中；
- (3)AOF文件大小超过重写策略或手动重写时，会对AOF文件 rewrite重写，压缩AOF文件容量；
- (4）Redis服务重启时，会重新 load加载AOF 文件中的写操作达到数据恢复的目的

---

### AOF默认不开启

可以在redis.conf中配置文件名称，默认为 appendonly.aof.
AOF文件的保存路径，同RDB的路径一致。

![image-20220114103553927](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114103553927.png)

![image-20220114103525131](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114103525131.png)

![image-20220114103827978](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114103827978.png)



###  AOF和RDB同时开启，redis听谁的?

AOF和RDB同时开启，系统默认取AOF的数据(数据不会存在丢失)。

### 异常恢复

- 修改默认的 appendonly no，改为yesu
- 如遇到**AOF文件损坏**，通过/usr/local/bin/redis-check-aof--fix appendonly.aof进行恢复。
- 备份被写坏的AOF文件。
- 恢复：重启redis，然后重新加载。

###  AOF同步频率设置

#### appendfsync always

​	始终同步，每次Redis的写入都会立刻记入日志；性能较差但数据完整性比较好。

#### appendfsync everysec

​	每秒同步，每秒记入日志一次，如果宕机，本秒的数据可能丢失。

#### appendfsync no

​	redis不主动进行同步，把同步时机交给操作系统。

---

###  Rewrite 压缩

#### 是什么

- AOF采用文件追加方式，文件会越来越大为避免出现此种情况，新增了重写机制，当AOF文件的大小超过所设定的國值时，Redis就会启动AOF文件的内容压缩，只保留可以恢复数据的最小指令集，可以使用命令bgrewriteaof.
- 重写原理，如何实现重写。
  AOF文件持续增长而过大时，会fork出一条新进程来将文件重写(也是先写临时文件最后再rename)，**redis4.0版本后的重写，是指把rdb 的快照，以二进制的形式附在新的aof头部，作为已有的历史数据，替换掉原来的流水账操作**
  **no-appendfsync-on-rewrite:**

#### 何时重写?

- Redis 会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍且文件大于64M时触发。
- 重写虽然可以节约大量磁盘空间，减少恢复时间。但是每次重写还是有一定的负担的，因此设定 Redis 要满足一定条件才会进行重写。
- auto-aof-rewrite-percentage：设置重写的基准值，文件达到100%时开始重写(文件是原来重写后文件的2倍时触发)
- auto-aof-rewrite-min-size：设置重写的基准值，最小文件64MB。达到这个值开始重写。
- 例如：文件达到70MB开始重写，降到50MB，下次什么时候开始重写？100MB系统载入时或者上次重写完毕时，Redis会记录此时AOF大小，设为base_size，如果Redis的AOF 当前大小>=base_size +base_size*100%(默认)且当前大小>=64Mb(默认)的情况下，Redis会对AOF重写

#### 重写流程

- (1)bgrewriteaof触发重写，判断是否当前有bgsave或bgrewriteaof在运行，如果有，则等待该命令结束后再继续执行。
- (2)主进程fqck出子进程执行重写操作，保证主进程不会阻塞。
- (3)子进程遍历redis内存中数据到临时文件，客户端的写请求同时写入aof_buf缓冲区和 aof_rewrite_buf重写缓冲区保证原AOF文件完整以及新AOF文件生成期间的新的数据修改动作不会丢失。
- (4)
  - 子进程写完新的AOF文件后，向主进程发信号，父进程更新统计信息。
  - 主进程把aof_rewrite_buf中的数据写入到新的AOF文件。
- (5)使用新的AOF文件覆盖旧的AOF文件，完成AOF重写。

---

### 优势

![image-20220114110256249](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114110256249.png)

- 备份机制更稳健，丢失数据概率更低。
- 可读的日志文本，通过操作AOF稳健，可以处理误操作。



---

### 劣势

- 比起RDB 占用更多的磁盘空间。
- 恢复备份速度要慢。
- 每次读写都同步的话，有一定的性能压力。
- 存在个别Bug，造成恢复不能。

---



## 用哪个好

官方推荐两个都启用。

- 如果对数据不敏感，可以选单独用RDB。
- 不建议单独用AOF，因为可能会出现Bug。
- 如果只是做纯内存缓存，可以都不用。

---

- RDB持久化方式能够在指定的时间间隔能对你的数据进行快照存储。
- AOF持久化方式记录每次对服务器写的操作，当服务器重启的时候会重新执行这些命令来恢复原始的数据，AOF命令以redis协议追加保存每次写的操作到文件末尾.Redis 还能对AOF文件进行后台重写，使得AOF文件的体积不至于过大
- 只做缓存：如果你只希望你的数据在服务器运行的时候存在，你也可以不使用任何持久化方式...
- 同时开启两种持久化方式
- 在这种情况下，当redis重启的时候会优先载入AOF文件来恢复原始的数据，因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整.RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件。那要不要只使用AOF呢？
- 建议不要，因为RDB更适合用于备份数据库(AOF在不断变化不好备份)，快速重启，而且不会有AOF可能潜在的bug，留着作为一个万一的手段。
- 性能建议。
  - 因为RDB文件只用作后备用途，建议只在Slave上持久化RDB文件，而且只要15分钟备份一次就够了，只保留save 9001这条规则。
  - 如果使用AOF，好处是在最恶劣情况下也只会丢失不超过两秒数据，启动脚本较简单只load自己的AOF文件就可以了。
  - 代价，一是带来了持续的IO，二是AOF rewrite的最后将rewrite过程中产生的新数据写到新文件造成的阻塞几乎是不可避免的。
  - 只要硬盘许可，应该尽量减少 AOF rewrite的频率，AOF重写的基础大小默认值64M太小了，可以设到5G以上。
  - 默认超过原大小100%大小时重写可以改到适当的数值。

---

# Redis_主从复制

## 是什么

​	主机数据更新后根据配置和策略，自动同步到备机的**master/slaver机制，Master以写为主，Slave以读为主**

## 能干嘛

- 读写分离，性能扩展。
- 容灾快速恢复。

![image-20220114130129256](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114130129256.png)

## 怎么用

关闭AOF

include /myredis/redis.conf

pidfile /var/run/redis_6379.pid

port 6379

dbfilename dump6379.rdb

![image-20220114131232218](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114131232218.png)

启动

![image-20220114132333205](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114132333205.png)

### 查看三台主机运行情况。

info replication
打印主从复制的相关信息

![image-20220114132732975](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114132732975.png)



### 配从(库)不配主(库)

slaveof <ip><port>
成为某个实例的从服务器
在6380和6381上执行：slaveof 127.0.0.1 6379

![image-20220114133056577](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114133056577.png)

## 原理

1. 当从连接上主服务器之后，从服务器 向主服务发送进行数据同步消息
2. 主服务器接到从服务器发送过来同步消息，把主服务器数据进行持久化
   rdb文件，把rdb文件发送给从服务器，从服务器拿到rdb进行读取
3. 每次主服务器进行写操作之后，和从服务器进行数据同步

- Slave启动成功连接到master后会发送一个sync命令。
- Master 接到命令启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令，在后台进程执行完毕之后，master 将传送整个数据文件到slave，以完成一次完全同步 
- 全量复制：而slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。
- 增量复制：Master 继续将新的所有收集到的修改命令依次传给slave，完成同步。但是只要是重新连接master，一次完全同步(全量复制)将被自动执行

### 薪火相传

上一个 Slave可以是下一个slave的Master，Slave同样可以接收其他 slaves的连接和同步请求，那s么该slave作为了链条中下一个的 master，可以有效减轻master的写压力，去中心化降低风险。
用slaveof <ip><port>
中途变更转向：会清除之前的数据，重新建立拷贝最新的
风险是一旦某个 slave宕机，后面的 slave都没法备份。
主机挂了，从机还是从机，无法写数据了。

![image-20220114134616352](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114134616352.png)

### 反客为主

当一个master宕机后，后面的 slave可以立刻升为master，其后面的slave不用做任何修改。

用slaveof no one 将从机变为主机

## 哨兵模式

### 是什么

反客为主的自动版，能够后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库

![image-20220114163728916](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114163728916.png)

### 怎么玩

---



#### 调整为一主二仆模式，6379带着6380.6381

---



#### 自定义的/myredis目录下新建sentinel.conf,名字绝不能错

---



#### 配置哨兵，填写内容

sentinel monitor mymaster 127.0.0.1 6379 1
其中 mymaster为监控对象起的服务器名称，1为至少有多少个哨兵同意迁移的数量。

---



#### 启动哨兵

/usr/local/bin

redis作压测可以使用自带的**redis-benchmark**工具

执行**redis-sentinel /myredis/sentinel.conf**

---



#### 当主机挂掉，从机选举中产生新的主机。

​	(大概10秒左右可以看到哨兵窗口日志，切换了新的主机)
哪个**从机**会被选举为**主机**呢？根据优先级别：**slave-priority**,**原主机**重启后会变为**从机**。

---



#### 复制延时。

由于所有的写操作都是先在Master上操作，然后同步更新到Slave上，所以从Master同步到 Slave机器有一定的延迟，当系统很繁忙的时候，延迟问题会更加严重，Slave机器数量的增加也会使这个问题更加严重。

---

#### 故障恢复

![image-20220114170145113](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114170145113.png)

优先级在redis.conf中默认：slave-priority100，值越小优先级越高
偏移量是指获得原主机数据最全的。
每个redis实例启动后都会随机生成一个40位的runid

![image-20220114170329342](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220114170329342.png)

---

# Redis集群

## 问题

- 容量不够，redis如何进行扩容？
- 并发写操作，redis如何分摊？
- 另外，主从模式，薪火相传模式，主机宕机，导致ip地址发生变化，应用程序中配置需要修改对应的主机地址、端口等信息。
- 之前通过代理主机来解决，但是redis3.0中提供了解决方案。就是**无中心化集群配置**。

## 什么是集群.

- Redis 集群实现了对Redis的水平扩容，即启动N个redis节点，将整个数据库分布存储在这N个节点中，每个节点存储总数据的1/N。
- Redis集群通过分区(partition)来提供一定程度的可用性(availability)：即使集群中有一部分节点失效或者无法进行通讯，集群也可以继续处理命令请求。

## 删除持久化数据

将rdb,aof文件都删除掉。

## 制作 6个实例，6379,6380,6381,6389,6390,6391

###  配置基本信息

- 开启 daemonize yes
- Pid文件名字
  指定端口
- Log文件名字

- Dump.edb名字

- Appendonly关掉或换名字

### redis cluster配置修改

- cluster-enabled yes 打开集群模式
- cluster-config-file nodes-6379.conf设定节点配置文件名
- cluster-node-timeout 15000设定节点失联时间，超过该时间(毫秒)，集群自动进行主从切换。

~~~redis
include /myredis/redis.conf
pidfile "/var/run/redis_6379.pid"
port 6379
dbfilename "dump6379.rdb"
cluster-enabled yes
cluster-config-file node-6379.conf
cluster-node-timeout 15000
~~~

### 使用查找替换修改另外5个文件。

例如：:%s/6379/6380 

### 启动6个redis服务

![image-20220115103130012](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220115103130012.png)

## 将六个节点合成一个集群

cd /opt/redis-6.2.6/src

~~~redis
redis-cli --cluster create --cluster-replicas 1 192.168.229.131:6379 192.168.229.131:6380 192.168.229.131:6381 192.168.229.131:6389 192.168.229.131:6390 192.168.229.131:6391

16384 slots covered
~~~

## -C 采用集群策略连接，设置数据会自动切换到相应的写主机 

~~~redis
redis-cli -c -p 6379
~~~

## 通过cluster nodes命令查看集群信息

~~~redis
[root@Redis src]# redis-cli -c -p 6379
127.0.0.1:6379> cluster nodes
10a697d520cf219a1a620f7713d6f1b4988d51aa 192.168.229.131:6379@16379 myself,master - 0 1642216613000 1 connected 0-5460
06a60f0041c25b36acb3a1bac693d473983f8b4c 192.168.229.131:6381@16381 master - 0 1642216616837 3 connected 10923-16383
ea9f702aa5d94f6bbb662d7fde70fdc1fb36f8d1 192.168.229.131:6391@16391 slave 10a697d520cf219a1a620f7713d6f1b4988d51aa 0 1642216615000 1 connected
bff1d00ad15f13a62b739c2fa86aadbfe1101365 192.168.229.131:6389@16389 slave e2e377ad33561337cbd2a10b6f64b79356b827c9 0 1642216615000 2 connected
ea1907029f292ce61dc884a8cbf24c48f35c6ccf 192.168.229.131:6390@16390 slave 06a60f0041c25b36acb3a1bac693d473983f8b4c 0 1642216615831 3 connected
e2e377ad33561337cbd2a10b6f64b79356b827c9 192.168.229.131:6380@16380 master - 0 1642216615000 2 connected 5461-10922
127.0.0.1:6379> 
~~~

## redis cluster如何分配这六个节点

一个集群至少要有**三个主节点**。

- 选项 --cluster-replicas 1表示我们希望为集群中的每个主节点创建一个从节点。

- 分配原则尽量保证每个主数据库运行在不同的IP地址，每个从库和主库不在一个IP地址上。

## 什么是slots

~~~redis
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
~~~



- 一个 Redis 集群包含16384个插槽(hashslot)，数据库中的每个键都属于这16384个插槽的其中一个。
- 集群使用公式 CRC16(key)% 16384 来计算键 key属于哪个槽，其中CRC16(key)语句用于计算键key的CRC16校验和集群中的每个节点负责处理一部分插槽。
- 举个例子，如果一个集群可以有主节点，其中：
  - 节点A负责处理0号至5460号插槽。
  - 节点B负责处理5461号至10922号插槽。
  - 节点c负责处理10923号至16383号插槽。

~~~redis
[root@Redis src]# redis-cli -c -p 6379
127.0.0.1:6379> set k1 v1
-> Redirected to slot [12706] located at 192.168.229.131:6381
OK
192.168.229.131:6381> set k2 v2
-> Redirected to slot [449] located at 192.168.229.131:6379
OK
192.168.229.131:6379> 
~~~



不在一个slot下的键值，是**不能使用mget，mset**等多键操作。

~~~redis
127.0.0.1:6379> mset a1 a b1 b c1 c
(error) CROSSSLOT Keys in request don't hash to the same slot
127.0.0.1:6379> 
~~~



可以通过**{}**来定义组的概念，从而使key中内相同内容的键值对放到一个slot中去。

~~~redis
192.168.229.131:6380> mset name{user} lucy age{user} 18
OK
192.168.229.131:6380> 
~~~

---

## 查询集群中的值

CLUSTER GETKEYSINSLOT<slot><count>返回count个slot槽中的键。

~~~redis
192.168.229.131:6380> cluster keyslot user
(integer) 5474
192.168.229.131:6380> cluster countkeysinslot 5474
(integer) 2
192.168.229.131:6380> cluster getkeysinslot 5474 10
1) "age{user}"
2) "name{user}"
192.168.229.131:6380> 
~~~

## 故障恢复

- 如果主节点下线？从节点能否自动升为主节点？注意：15秒超时
- 主节点恢复后，主从关系会如何？主节点回来变成从机。
- 如果所有某一段插槽的主从节点都宕掉，redis服务是否还能继续? 
- - 如果某一段插槽的主从都挂掉，而cluster-regluire-full-coverage为yes，那么，整个集群都挂掉
  - 如果某一段插槽的主从都挂掉，而cluster-require-full-coverage为no，那么，该插槽数据全都不能使用，也无法存储。
  - redis.conf中的参数 cluster-require-full-coverage.

## Redis集群提供了以下好处。

- 实现扩容。
- 分摊压力。
- 无中心配置相对简单

## Redis 集群的不足

- 多键操作是不被支持的。
- 多键的 Redis事务是不被支持的。lua脚本不被支持。
- 由于集群方案出现较晚，很多公司已经采用了其他的集群方案，而代理或者客户端分片的方案想要迁移至redis cluster，需要整体迁移而不是逐步过渡，复杂度较大。

---

# Redis应用问题解决

## 缓存穿透

### 问题描述

key对应的数据在数据源并不存在，每次针对此key的请求从缓存获取不到，请求都会压到数据源，从而可能压垮数据源。比如用一个不存在的用户 id 获取用户信息，不论缓存还是数据库都没有，若黑客利用此漏洞进行攻击可能压垮数据库。



![image-20220115131958049](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220115131958049.png)

### 解决方案

​	一个一定不存在缓存及查询不到的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。
解决方案：

- (1)**对空值缓存：**如果一个查询返回的数据为空(不管是数据是否不存在)，我们仍然把这个空结果(null)进行缓存，设置空结果的过期时间会很短，最长不超过五分钟。
- (2)**设置可访问的名单(白名单)：**
  使用**bitmaps**类型定义一个可以访问的名单，名单id作为**bitmaps**的偏移量，每次访问和 bitmap里面的id进行比较，如果访问id不在**bitmaps**里面，进行拦截，不允许访问。
- (3)**采用布隆过滤器：**(布隆过滤器(Bloom Filter)是1970年由布隆提出的。它实际上是一个很长的二进制向量(位图)和一系列随机映射函数(哈希函数)。布隆过滤器可以用于检索一个元素是否在一个集合中。它的**优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。**
  将所有可能存在的数据哈希到一个足够大的 bitmaps 中，一个一定不存在的数据会被 这个bitmaps拦截掉，从而避免了对底层存储系统的查询压力。
- (4)**进行实时监控：**当发现 Redis的命中率开始急速降低，需要排查访问对象和访问的数据，和运维人员配合，可以设置黑名单限制服务。

---

## 缓存击穿

### 问题描述

​	key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

![image-20220115132901473](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220115132901473.png)

### 解决方案

​	key可能会在某些时间点被超高并发地访问，是一种非常“热点”的数据。这个时候，需要考虑一个问题：缓存被“击穿”的问题。
解决问题：
(1)**预先设置热门数据：**在redis 高峰访问之前，把一些热门数据提前存入到redis里面，加大这些热门数据key的时长
(2)**实时调整：**现场监控哪些数据热门，实时调整key的过期时长。
(3)**使用锁：**
(1)就是在缓存失效的时候(判断拿出来的值为空)，不是立即去load db。
(2)先使用缓存工具的某些带成功操作返回值的操作(比如 Redis的SETNX)去set一个mutex key

(3)当操作返回成功时，再进行load db的操作，并回设缓存，最后删除mutex key;
(4)当操作返回失败，证明有线程在load db，当前线程睡眠一段时间再重试整个get缓存的方法。

![image-20220115133410400](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220115133410400.png)

---



## 缓存雪崩

### 问题描述

​	key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。
​	缓存雪崩与缓存击穿的区别在于**这里针对很多key缓存**，击穿则是某一个key过期

![image-20220115133709007](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220115133709007.png)

### 解决方案

缓存失效时的雪崩效应对底层系统的冲击非常可怕！
解决方案：
(1)**构建多级缓存架构：**nginx缓存+ redis缓存+其他缓存(ehcache等)
(2)**使用锁或队列：**
用加锁或者队列的方式保证来保证不会有大量的线程对数据库一次性进行读写，从而避免失效时大量的并发请求落到底层存储系统上。**不适用高并发情况**

(3)**设置过期标志更新缓存：**
记录缓存数据是否过期(设置提前量)，如果过期会触发通知另外的线程在后台去更新实际key的缓存
(4)**将缓存失效时间分散开：**
比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5 分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。

---

## 分布式锁

### 问题描述

​	随着业务发展的需要，原单体单机部署的系统被演化成分布式集群系统后，由于分布式系统多线程、多进程并且分布在不同机器上，这将使原单机部署情况下的并发控制锁策略失效，单纯的Java API并不能提供分布式锁的能力。为了解决这个问题就需要一种跨JVM的互斥机制来控制共享资源的访问，这就是分布式锁要解决的问题！。分布式锁主流的实现方案：
1.基于数据库实现分布式锁。
2.基于缓存(Redis等)
3.基于 Zookeeper-
每一种分布式锁解决方案都有各自的优缺点：

1.性能：redis最高
2.可靠性：zookeeper最高
这里，我们就基于redis实现分布式锁

### 解决方案：使用Redis实现分布式锁

redis：命令
\#set sku:1:info "OK” NX PX 10000
EX second:设置键的过期时间为 second 秒。 SET key value EX second效果等同于SETEX key second value。

~~~redis
127.0.0.1:6379> set user 10 nx ex 10
OK
127.0.0.1:6379> ttl user
(integer) 7
127.0.0.1:6379> setex key 10 hello
OK
127.0.0.1:6379> ttl key
(integer) 5
~~~



PX millisecond：设置键的过期时间为 millisecond 毫秒。 SET key value PXmillisecond效果等同于 PSETEX key millisecond value。
NX：只在键不存在时，才对键进行设置操作。SET key value NX 效果等同于 SETNX key value。
XX：只在键已经存在时，才对键进行设置操作。

---

### 为了确保分布式锁可用，我们至少要确保锁的实现同时满足以下四个条件：

- 互斥性。在任意时刻，只有一个客户端能持有锁。
- 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
- 解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
- 加锁和解锁必须具有原子性。

---

# Redis6新特性

## ACL

​	Redis ACL是Access Control List(访问控制列表)的缩写，该功能允许根据可以执行的命令和可以访问的键来限制某些连接。
​	在 Redis 5版本之前，Redis 安全规则只有密码控制 还有通过 rename 来调整高危命令比如flushdb，KEYS*，shutdown等。Redis 6则提供ACL的功能对用户进行更细粒度的权限控制：
(1)接入权限：用户名和密码。
(2)可以执行的命令
(3)可以操作的KEY.
参考官网：https://redis.io/topics/acl

### 命令

1. 使用 acl list命令展现用户权限列表

![image-20220115155211275](https://gitee.com/dengwangqi/typora/raw/master/redis/image-20220115155211275.png)

2. 使用acl cat命令
   1. 查看添加权限指令类型

~~~redis
127.0.0.1:6379> acl cat
 1) "keyspace"
 2) "read"
 3) "write"
 4) "set"
 5) "sortedset"
 6) "list"
 7) "hash"
 8) "string"
 9) "bitmap"
10) "hyperloglog"
11) "geo"
12) "stream"
13) "pubsub"
14) "admin"
15) "fast"
16) "slow"
17) "blocking"
18) "dangerous"
19) "connection"
20) "transaction"
21) "scripting"
127.0.0.1:6379> 
~~~

3. 使用acl whoami命令查看当前用户

~~~redis
127.0.0.1:6379> acl whoami
"default"
~~~

4. 使用 acl setuser 命令创建和编辑用户 

---



## IO多线程

###  简介

​	Redis6终于支撑多线程了，告别单线程了吗？
​	IO 多线程其实指客户端交互部分的网络IO 交互处理模块多线程，而非执行命令多线程。Redis6执行命令依然是单线程。

### 原理架构

​	Redis 6 加入多线程，但跟Memcached这种从IO处理到数据访问多线程的实现模式有些差异。Redis 的多线程部分只是用来处理网络数据的读写和协议解析，执行命令仍然是单线程。之所以这么设计是不想因为多线程而变得复杂，需要去控制key、lua、事务，LPUSH/LPOP等等的并发问题。整体的设计大体如下：

另外，多线程IO默认也是不开启的，需要再配置文件中配置。
io-threads-do-reads yes
io-threads 4
