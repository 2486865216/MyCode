# 一、Linux目录结构

具体的目录结构:

- /bin[重点] (usr/bin、/usr/local/bin)
  是 Binary的缩写,这个目录存放着最经常使用的命令
- /sbin (/usr/sbin. /usr/local/sbin)
  s就是 Super User的意思,这里存放的是系统管理员使用的系统管理程序。
- /home[重点]
  存放普通用户的主目录,在 Linux中每个用户都有一个自己的目录,一般该目录名是以用户的账号命名的。
- /root[重点]
  该目录为系统管理员,也称作超级权限者的用户主目录。

- /ib
  系统开机所需要最基本的动态连接共享库,其作用类似于 Windows里的DLL文件。几乎所有的应用程序都需要用到这些共享库。

- /lost+found
  室个目录一般情况下是空的,当系统非法关机后,这里就存放了一些文件。

- /etc[重点]
  所有的系统管理所需要的配置文件和子目录

- /usr[重点]
  这是一个非常重要的目录,用户的很多应用程序和文件都放在这个目录下,类似与windows下的programfiles

- /boot[重点]
  存放的是启动Linux时使用的一些核心烹件,包括一些连接文件以及镜像文件

- /proc
  这个目录是一个虚拟的目录,它是系结内存的映射,访问这个目录来获取系统信息

- /srv
  service缩写,该目录存放一些服务启动之后需要提取的数据。

- /sys
  这是Linux2.6内核的一个很大的变化。该目录下安装了2.6内核中新出现的一个文件系统sysfs。

- /tmp
  这个目录是用来存放一些临时文件的。

- /dev

  类似于 windows的设备管理器,把所有的硬件用文件的形式存储

- /meda[重点]
  Linux系统会自动识别一些设备,例如U盘、光驱等等,当识别后,Linux会把识别的设备挂载到这个目录下。
- /mnt[重点
  系统提供该目录是为了让用户临时挂载别的文件系统的,我们可以将外部的存储挂载在/mnt/上,然后进入该目录就可以查看里的内容了。

- /opt

  这是给主机额外安装软件所摆放的目录。如安装 ORACLE数据库就可放到该目录下。默认为空。

- /usr/local[重点]
  这是另一个给主机额外安装软件所安装的目录。一般是通过编译源码方式安装的程序
- /var[重点]
  这个目录中存放着在不断扩充着的东西,习惯将经常被修改的目录放在这个目录下。包括各种日志文件。
- /selinux [security-enhanced linux]
  SELinux是一种安全子系统,它能控制程序只能访问特定文件。

# 二、vi与vim

![image-20220218120459221](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220218120459221.png)

## vi和vim快捷键

1. 拷贝当前行yy,拷贝当前行向下的5行5yy,并粘贴(p)。5yyp
2. 删除当前行dd,删除当前行向下的5行5dd。5dd
3. 在文件中查找某个单词[命令行下 /关键字,回车查找,输入n就是查找下一个  /hello
4. 设置文件的行号,取消文件的行号[命令行下:set nu和:set nonu
5. 编辑/etc/ profile文件,使用快捷键到底文档的最末行[G]和最首行gg
6. 在一个文件中输入"hello"然后又撤销这个动作 u
7. 编辑/etc/ profile文件,并将光标移动到20行 shift+g
   1. 第一步:显示行号 :set nu
   2. 第二步:输入20这个数
   3. 第三步:输入 shift+g

# 三、开机、重启和用户登录注销

- shutdown -h now
  立该进行关机
- shutdown -h 1
  1分钟后会关机了
- shutdown -r now
  现在重新启动计算机
- halt
  关机,作用和上面一样
- reboot
  现在重新启动计算机
- sync
  把内存的数据同步到磁盘

不管是重启系统还是关闭系统,首先要运行sync命令,把内存中的数据写到磁盘中

## 用户登录和注销

基本介绍:

- 登录时尽量少用root帐号登录,因为它是系统管理员,最大的权限,避免操作失误。可以利用普通用户登录,登录后再用”su-用户名命令来切换成系统管理员身份.
- 在提示符下输入 logout即可注销用户

- logout注销指令在图形运行级别无效,在运行级别3下有效

# 四、用户管理

![image-20220218123613029](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220218123613029.png)

## 添加用户

基本语法
    useradd用户名
应用案例
1)案例1:添加一个用户 Xiaoming
细节说明
1)当创建用户成功后,会自动的创建和用户同名的家目录
2)也可以通过 useradd -d 指定目录新的用户名,给新创建的用户指定家目录

```
[root@hadoop1 /]# useradd xm
[root@hadoop1 /]# useradd -d /home/test/ xh
[root@hadoop1 /]# cd home
[root@hadoop1 home]# ll
总用量 0
-rw-r--r--. 1 root root  0 12月 24 18:42 hello
drwx------. 3 xh   xh   78 2月  18 12:49 test
drwx------. 3 xm   xm   78 2月  18 12:48 xm
[root@hadoop1 home]# 


```



## 指定/修改密码

 	基本语法
 	passwd用户名
 	应用案例
 	1)给 Xiaoming指定密码

```
[root@hadoop1 ~]# passwd xm
更改用户 xm 的密码 。
新的 密码：
无效的密码： 密码少于 8 个字符
重新输入新的 密码：
passwd：所有的身份验证令牌已经成功更新。
[root@hadoop1 ~]# 
```

## 删除用户

​	基本语法
​	userdel 用户名
​	应用案例
​	1)删除用户 XIaomIng,但是要保留家目录

![image-20220218124718258](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220218124718258.png)	

```
[root@hadoop1 ~]# userdel xm
[root@hadoop1 ~]# userdel -r xh
```



2)删除用户以及用户主目录
	细节说明
	1)是否保留家目录的讨论?保留

## 查询用户信息

id 用户名

```
[root@hadoop1 home]# id root
uid=0(root) gid=0(root) 组=0(root)
用户id 组id 组名称
```

## 切换用户

su - 切换用户名

高权限到低权限，不需要密码

exit

回退到原来的用户

```
[root@hadoop1 home]# su - test
上一次登录：五 2月 18 12:59:21 CST 2022pts/0 上
[test@hadoop1 ~]$ exit
登出
[root@hadoop1 home]# 
```

## 查看当前用户

whoami

```
[root@hadoop1 home]# whoami
root
```

## 用户组

类似于角色,系统可以对有共性的多个用户进行统一的管理

新增组

groupadd 组名

```
[root@hadoop1 home]# groupadd wudang
[root@hadoop1 home]# 
```

删除组

groupdel 组名

```
[root@hadoop1 home]# groupdel wudang
[root@hadoop1 home]# 
```

增加用户时直接加上组
useradd -g 用户组 用户名

```
[root@hadoop1 home]# groupadd wudang
[root@hadoop1 home]# useradd -g wudang zhangwuji
[root@hadoop1 home]# ll
总用量 0
-rw-r--r--. 1 root      root    0 12月 24 18:42 hello
drwx------. 3 zhangwuji wudang 78 2月  18 13:05 zhangwuji
```

## 修改用户组

usermod -g 新组名 用户名

```
[root@hadoop1 home]# groupadd shaoling
[root@hadoop1 home]# usermod -g shaoling zhangwuji
[root@hadoop1 home]# id zhangwuji
uid=1001(zhangwuji) gid=1002(shaoling) 组=1002(shaoling)
[root@hadoop1 home]# 
```

## 配置文件

### 用户配置文件 /etc/passwd

etc/ passwd 文件
用户(user)的配置文件,记录用户的各种信息
每行的含义:用户名:口令:用户标识号:组标识号注释性描述;主目录:登录 Shell

```
zhangwuji:x:1001:1002::/home/zhangwuji:/bin/bash
用户id：密码(加密)：用户id：组id：家目录：shell
```



### 组配置文件 /etc/group

/etc/ group文件
组( group)的配置文件,记录linux包含的组的信息
每行含义:组名:口令组标识号:组内用户列表

```
wudang:x:1001:
shaoling:x:1002:
```



### 口令配置文件(密码和登录信息，加密的) /etc/shadow

etc/ shadow文件
口令的配置文件
每行的含义:登录名:加密口令最后一次修改时间最小时间间隔最大时间间隔警告时间:不活动时间失效时间:标志

```
zhangwuji:!!:19041:0:99999:7:::
```

# 五、实用指令

## 运行级别说明

0:关机
1:单用户【找回丢失密码】
2:多用户状态没有网络服务
3:多用户状态有网络服务
4:系统未使用保留给用户
5:图形界面
6:系统重启
常用运行级别是3和5,要修改默认的运行级别可改文件
/etc/inittab的id:5:initdefault这一行中的数字
命令:int[012356]

centOS7

```
systemd uses 'targets' instead of runlevels. By default, there are two main targets:

multi-user.target: analogous to runlevel 3

graphical.target: analogous to runlevel 5

To view current default target, run:

systemctl get-default

To set a default target, run:

systemctl set-default TARGET.target
```

## 面试题:找回丢失的密码

### centOS6

开机在引导时输入回车键>看到一个界面输入e→>看到一个新的界面,选中第二行(编辑内核)在输入e>在这行最后输入 1再输入回车键→再次输入b这时就会进入到单用户模式。
这时,我们就进入到单用户模式,使用 passwd指令来修改root密码。

### centOS7

<https://www.cnblogs.com/psc0218/p/12611024.html>

## 帮助指令

### man获得帮助信息

man 命令或配置文件 (功能描述:获得帮助信息)

```
[root@hadoop1 ~]# man ls
LS(1)                                            General Commands Manual                                           LS(1)

NAME
       ls, dir, vdir - 列目录内容

提要
       ls [选项] [文件名...]

       POSIX 标准选项: [-CFRacdilqrtu1]

GNU 选项 (短格式):
       [-1abcdfgiklmnopqrstuxABCDFGLNQRSUX]  [-w cols] [-T cols] [-I pattern] [--full-time] [--format={long,verbose,com‐
       mas,across,vertical,single-column}] [--sort={none,time,size,extension}]  [--time={atime,access,use,ctime,status}]
       [--color[={none,auto,always}]] [--help] [--version] [--]

描述（ DESCRIPTION ）
       程序ls先列出非目录的文件项，然后是每一个目录中的“可显示”文件。如果
       没有选项之外的参数【译注：即文件名部分为空】出现，缺省为        "."        （当前目录）。        选项“         -d
       ”使得目录与非目录项同样对待。除非“ -a ” 选项出现，文 件名以“.”开始的文件不属“可显示”文件。

       以当前目录为准，每一组文件（包括非目录文件项，以及每一内含文件的目录）分     别按文件名比较顺序排序。如果“     -l
       ”选项存在，每组文件前显示一摘要行: 给出该组文件长度之和（以 512 字节为单位）。
```

### help 命令(功能描述:获得shell 内置命令的帮助信息)

```
[root@hadoop1 ~]# help cd
cd: cd [-L|[-P [-e]]] [dir]
    Change the shell working directory.
    
    Change the current directory to DIR.  The default DIR is the value of the
    HOME shell variable.
    
    The variable CDPATH defines the search path for the directory containing
    DIR.  Alternative directory names in CDPATH are separated by a colon (:).
    A null directory name is the same as the current directory.  If DIR begins
    with a slash (/), then CDPATH is not used.
    
    If the directory is not found, and the shell option `cdable_vars' is set,
    the word is assumed to be  a variable name.  If that variable has a value,
    its value is used for DIR.
    
    Options:
        -L	force symbolic links to be followed
        -P	use the physical directory structure without following symbolic
    	links
        -e	if the -P option is supplied, and the current working directory
    	cannot be determined successfully, exit with a non-zero status
    
    The default is to follow symbolic links, as if `-L' were specified.
    
    Exit Status:
    Returns 0 if the directory is changed, and if $PWD is set successfully when
    -P is used; non-zero otherwise.
```

### 百度

## 文件目录类

### pwd指令

基本语法
pwd(功能描述:显示当前工作目录的绝对路径
应用实例
案例:显示当前工作目录的绝对路径

```
[root@hadoop1 ~]# pwd
/root
```



### ls指令

基本语法
选项][目录或是文件
常用选项
-a:显示当前目录所有的文件和目录,包括隐藏的。
-l:以列表的方式显示信息
应用实例
案例查看当前目录的所有内容信息

![image-20220218141116759](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220218141116759.png)

![image-20220218141206276](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220218141206276.png)

### cd指令

基本语法
 cd[参数]
(功能描述:切换到指定目录)
常用参数
绝对路径和相对路径
cd ~或者cd :回到自己的家目录

```
[root@hadoop1 home]# cd ~
[root@hadoop1 ~]# pwd
/root
[root@hadoop1 ~]# cd /home
[root@hadoop1 home]# cd
[root@hadoop1 ~]# pwd
/root
[root@hadoop1 ~]# 
```



cd ..回到当前目录的上一级目录
应用实例
案例1:使用绝对路径切换到root目录
案例2:使用相对路径到/root目录
案例3:表示回到当前目录的上一级目录
案例4:回到家目录

### mkdir指令

mkdir指令用于创建目录 make directory
基本语法
mkdir [选项]要创建的目录
常用选项
-p:创建多级目录
应用实例
案例1创建一个目录 home/dog
案例2创建多级目录 home/animal/tiger

```
[root@hadoop1 ~]# mkdir /home/dog
[root@hadoop1 ~]# mkdir /home/animal/dog
mkdir: 无法创建目录"/home/animal/dog": 没有那个文件或目录
[root@hadoop1 ~]# mkdir -p /home/animal/dog
[root@hadoop1 ~]# cd /home
[root@hadoop1 home]# ls
animal  dog  hello  zhangwuji
[root@hadoop1 home]# 
```

### rmdir指令

rmdir指令删除空目录
基本语法
rmdir [选项]要删除的空目录
应用实例
案例1删除一个目录 home/dog
使用细节
rmdir删除的是空目录,如果目录下有内容时无法删除的。
提示:如果需要删除非空目录,需要使用 rm -rf 要删除的目录

```
[root@hadoop1 home]# cd animal/dog
[root@hadoop1 dog]# vi test.txt
[root@hadoop1 dog]# ls
test.txt
[root@hadoop1 dog]# cd ..
[root@hadoop1 animal]# cd ..
[root@hadoop1 home]# rmdir /animal/dog
rmdir: 删除 "/animal/dog" 失败: 没有那个文件或目录
[root@hadoop1 home]# rm -rf /animal/dog
[root@hadoop1 home]# 
```

### touch指令

touch指令创建空文件
基本语法
touch 文件名称
应用实例
案例1:创建一个空文件 hello.txt

```
[root@hadoop1 home]# ls
hello  zhangwuji
[root@hadoop1 home]# touch hello.txt
[root@hadoop1 home]# ls
hello  hello.txt  zhangwuji
[root@hadoop1 home]# touch test.txt test2.txt
[root@hadoop1 home]# ls
hello  hello.txt  test2.txt  test.txt  zhangwuji
```

### cp指令[重要]

cp指令拷贝文件到指定目录
基本语法
cp [选项] source dest
·常用选项

-r: 递归复制整个文件夹
应用实例
案例1:将 Ahome/aaa.txt拷贝到 home/bbb目录下

```
[root@hadoop1 home]# mkdir test
[root@hadoop1 home]# ls
hello  hello.txt  test  test2.txt  test.txt  zhangwuji
[root@hadoop1 home]# cp hello.txt test/hello2.txt
[root@hadoop1 home]# ls
hello  hello.txt  test  test2.txt  test.txt  zhangwuji
[root@hadoop1 home]# cd test
[root@hadoop1 test]# ls
hello2.txt
[root@hadoop1 test]# 
```

案例2:递归复制整个文件夹,举例

```
[root@hadoop1 home]# cp -r test/ test2/
[root@hadoop1 home]# ls
hello  hello.txt  test  test2  test2.txt  test.txt  zhangwuji
[root@hadoop1 home]# cd test2
[root@hadoop1 test2]# ls
hello2.txt
[root@hadoop1 test2]# 
```

使用细节
强制覆盖不提示的方法:\cp

```
[root@hadoop1 home]# cp -r test/ test2/
cp：是否覆盖"test2/test/hello2.txt"？ 
[root@hadoop1 home]# \cp -r test/ test2/
[root@hadoop1 home]# 
```

### rm指令

rm指令移除文件或目录
基本语法

rm [选项] 要删除的文件或目录

-r:递归删除整个文件夹

-f:强制删际不提示
强制删除不提示的方法:带上-f参数即可

```
[root@hadoop1 home]# ls
hello  hello.txt  test2  test2.txt  test.txt  zhangwuji
[root@hadoop1 home]# rm hello.txt
rm：是否删除普通空文件 "hello.txt"？y
[root@hadoop1 home]# ls
hello  test2  test2.txt  test.txt  zhangwuji
[root@hadoop1 home]# rm -r test2
rm：是否进入目录"test2"? y
rm：是否删除普通空文件 "test2/hello2.txt"？y
rm：是否进入目录"test2/test"? y
rm：是否删除普通空文件 "test2/test/hello2.txt"？y
rm：是否删除目录 "test2/test"？y
rm：是否删除目录 "test2"？y
[root@hadoop1 home]# rm -rf test
[root@hadoop1 home]# ls
hello  test2.txt  test.txt  zhangwuji
```

### mv指令

mv移动文件与目录或重命名
基本语法
mv oldNameFile newNameFile(功能描述:重命名文件)
mv /tempmovefile /target Folder(功能描述:移动文件)
应用实例

```
[root@hadoop1 home]# mv test2.txt xxx.txt
[root@hadoop1 home]# ls
hello  test.txt  xxx.txt  zhangwuji
[root@hadoop1 home]# ls
hello  xxx.txt  zhangwuji
[root@hadoop1 home]# mv xxx.txt /root/
[root@hadoop1 home]# ls
hello  zhangwuji
[root@hadoop1 /]# cd root
[root@hadoop1 ~]# ls
6df9159ce99f48a9be1938af646a80ed.jpg  hello.java            OK.java  公共  视频  文档  音乐
anaconda-ks.cfg                       initial-setup-ks.cfg  xxx.txt  模板  图片  下载  桌面
[root@hadoop1 ~]# 
```

### cat指令

cat查看文件内容,是以只读的方式打开。
基本语法
cat [选项] 要查看的文件
常用选项

- -n:显示行号

```
[root@hadoop1 ~]# cat -n /etc/profile
     1	# /etc/profile
     2	
     3	# System wide environment and startup programs, for login setup
     4	# Functions and aliases go in /etc/bashrc
     5	
     6	# It's NOT a good idea to change this file unless you know what you
     7	# are doing. It's much better to create a custom.sh shell script in
     8	# /etc/profile.d/ to make custom changes to your environment, as this
     9	# will prevent the need for merging in future updates.
    10	
```

```
[root@hadoop1 ~]# cat -n /etc/profile | more
     1	# /etc/profile
     2	
     3	# System wide environment and startup programs, for login setup
     4	# Functions and aliases go in /etc/bashrc
```



### more指令

more指令是一个基于Ⅵ编辑器的文本过滤器,它以全屏幕的方式按页显示文本文件的内容。more指令中内置了若干快捷键,详见操作说明
基本语法
more 要查看的文件
功能说明
操作说明

- 空白键 (space)代表向下翻一页
- Enter 代表向下翻【一行】
- q:代表立刻离开more,不再显示该文件内容
- Ctrl+F向下漆动一屏
- Ctrl+B返回上一屏
- =:输出当前行的行号
- :f 翰出文件名和当前行的行号

```
[root@hadoop1 ~]# more /etc/profile
# /etc/profile

# System wide environment and startup programs, for login setup
# Functions and aliases go in /etc/bashrc

# It's NOT a good idea to change this file unless you know what you
# are doing. It's much better to create a custom.sh shell script in
# /etc/profile.d/ to make custom changes to your environment, as this
# will prevent the need for merging in future updates.

pathmunge () {
    case ":${PATH}:" in
        *:"$1":*)
            ;;
        *)
            if [ "$2" = "after" ] ; then
                PATH=$PATH:$1
            else
                PATH=$1:$PATH
            fi
    esac
}
```

### less指令

less指令用来分屏查看文件内容,它的功能与more指令类似,但是比more指令更加强大,支持各种显示终端。less指令在显示文件内容时,并不是一次将整个文件加载之后才显示,而是根据显示需要加载内容,对于显示大型文件具有较高的效率
基本语法
 less  要查看的文件

- 空白键向下翻动一页
- [pagedown]向下翻动一页
- [pageup]向上翻动一页
- /字串:向下搜寻『字串』的功能:n:向下查找:N:向上查找
- ?字串:向上搜寻『字串』的功能:n:向上查找:N:向下查找
- q : 离开less这个程序

### >指令和>>指令

$       >指令和>>指令

$       >输出重定向和>>追加
基本语法
1)ls -l > 文件
(功能描述:列表显示的内容写入文件a.txt中(覆盖写))
2)ls -al >> 文件
(功能描述:列表的内容追加到文件 a.txt的末尾
3)cat 文件1 > 文件2
(功能描述:将文件1的内容覆盖到文件2
4)echo "内容" >>文件

```
[root@hadoop1 home]# echo "hello.word" > c.txt
[root@hadoop1 home]# more c.txt
hello.word
```

```
[root@hadoop1 home]# ls -l
总用量 0
-rw-r--r--. 1 root root 0 12月 24 18:42 hello
[root@hadoop1 home]# ls -l > a.txt
[root@hadoop1 home]# ls -l
总用量 4
-rw-r--r--. 1 root root 106 2月  19 12:04 a.txt
-rw-r--r--. 1 root root   0 12月 24 18:42 hello
[root@hadoop1 home]# vi a.txt
[root@hadoop1 home]# more a.txt
总用量 0
-rw-r--r--. 1 root root 0 2月  19 12:04 a.txt
-rw-r--r--. 1 root root 0 12月 24 18:42 hello
[root@hadoop1 home]# 
```

```
[root@hadoop1 home]# cal
      二月 2022     
日 一 二 三 四 五 六
       1  2  3  4  5
 6  7  8  9 10 11 12
13 14 15 16 17 18 19
20 21 22 23 24 25 26
27 28

[root@hadoop1 home]# cal >> mycal
[root@hadoop1 home]# cal >> mycal
[root@hadoop1 home]# cal >> mycal
[root@hadoop1 home]# more mycal
      二月 2022     
日 一 二 三 四 五 六
       1  2  3  4  5
 6  7  8  9 10 11 12
13 14 15 16 17 18 19
20 21 22 23 24 25 26
27 28

      二月 2022     
日 一 二 三 四 五 六
       1  2  3  4  5
 6  7  8  9 10 11 12
13 14 15 16 17 18 19
20 21 22 23 24 25 26
27 28
```

### echo指令

ech输出内容到控制台
基本语法
echo [选项] 输出内容
应用实例
案例:使用echo指令输出环境变量

````
[root@hadoop1 home]# echo $PATH
/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin
[root@hadoop1 home]# 
````

案例:使用echo指令输出 hello, world!

```
[root@hadoop1 home]# echo "hello word"
hello word
```



### head指令

head用于显示文件的开头部分内容,默认情况下head指令显示文件的前10行内容
基本语法
head 文件 (功能描述:查看文件头10行内容)
head   -n   5 文件(功能描述:査看文件头5行内容,5可以是任意行数)

```
[root@hadoop1 home]# head -n 5 test.txt
ok
ok
ok
ok
ok
[root@hadoop1 home]# 
```

### tail指令

tail 用于输出文件中尾部的内容,默认情况下tail指令显示文件的后10行内容。
基本语法
1)tail 文件(功能描述:查看文件后10行内容
2)tail -n 5 文件(功能描述:查看文件后5行內容,5可以是任意行数
3)tail -f 文件(功能描述:实时追踪该文档的所有更新

![image-20220219122318093](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220219122318093.png)

### ln指令

软链接也叫符号链接,类似于 windows里的快捷方式,主要存放了链接其他文件的路径
基本语法
ln -s [原文件或目录] [软链接名] (功能描述:给原文件创建一个软链接)
应用实例
案例1:在/home目录下创建一个软连接 linkToRoot,连接到/root目录
案例2:删除软连接 linkToRoot
细节说明
当我们使用pwd指令查看目录时,仍然看到的是软链接所在目录

```
[root@hadoop1 home]# ls
a.txt  c.txt  hello  mycal  mydate.txt
[root@hadoop1 home]# ln -s /root linkToRoot
[root@hadoop1 home]# ls
a.txt  c.txt  hello  linkToRoot  mycal  mydate.txt
[root@hadoop1 home]# cd linkToRoot
[root@hadoop1 linkToRoot]# ls
6df9159ce99f48a9be1938af646a80ed.jpg  hello.java            OK.java  公共  视频  文档  音乐
anaconda-ks.cfg                       initial-setup-ks.cfg  xxx.txt  模板  图片  下载  桌面
[root@hadoop1 linkToRoot]# pwd
/home/linkToRoot
[root@hadoop1 linkToRoot]# 
```

### history指令

查看已轻执行过历史命令也可以执行历史指令
基本语法
history (功能描述:査看已经执行过历史命令)
应用实例
案例1:显示所有的历史命令
案例2:显示最近使用过的10个指令
案例3:执行历史编号为5的指令

```
  261  cd linkToRoot
  262  ls
  263  pwd
  264  history
[root@hadoop1 linkToRoot]# history 10
  256  tail -f mydate.txt
  257  ln ls
  258  ls
  259  ln -s /root linkToRoot
  260  ls
  261  cd linkToRoot
  262  ls
  263  pwd
  264  history
  265  history 10
[root@hadoop1 linkToRoot]# !265
history 10
  256  tail -f mydate.txt
  257  ln ls
  258  ls
  259  ln -s /root linkToRoot
  260  ls
  261  cd linkToRoot
  262  ls
  263  pwd
  264  history
  265  history 10
[root@hadoop1 linkToRoot]# 
```

## 时间日期类

### date指令显示当前日期

基本语法
l)date(功能描述:显示当前时间)
2)date "+%Y"(功能描述:显示当前年份)
3)date "+%m"(功能描述:显示当前月份
4)date "+%d" (功能描述:显示当前是哪一天)
5)date"+%Y-%m-%d %H:%M:%S"(功能描述:显示年月日时分秒)

6)dat -s "2001-01-15" 设置当前系统时间

```
[root@hadoop1 linkToRoot]# date
2022年 02月 19日 星期六 12:34:29 CST
[root@hadoop1 linkToRoot]# date "+%Y"
2022
[root@hadoop1 linkToRoot]# date "+%m"
02
[root@hadoop1 linkToRoot]# date "+%Y-%m-%d %H:%M;%S"
2022-02-19 12:36;15
[root@hadoop1 linkToRoot]# 

```

### cal指令

查看日历指令
基本语法
cal [选项] (功能描述:不加选项,显示本月日历)

```
[root@hadoop1 linkToRoot]# cal
      二月 2022     
日 一 二 三 四 五 六
       1  2  3  4  5
 6  7  8  9 10 11 12
13 14 15 16 17 18 19
20 21 22 23 24 25 26
27 28

[root@hadoop1 linkToRoot]# cal 2020
                               2020                               

        一月                   二月                   三月        
日 一 二 三 四 五 六   日 一 二 三 四 五 六   日 一 二 三 四 五 六
          1  2  3  4                      1    1  2  3  4  5  6  7
 5  6  7  8  9 10 11    2  3  4  5  6  7  8    8  9 10 11 12 13 14
12 13 14 15 16 17 18    9 10 11 12 13 14 15   15 16 17 18 19 20 21
19 20 21 22 23 24 25   16 17 18 19 20 21 22   22 23 24 25 26 27 28
26 27 28 29 30 31      23 24 25 26 27 28 29   29 30 31

        四月                   五月                   六月        
日 一 二 三 四 五 六   日 一 二 三 四 五 六   日 一 二 三 四 五 六
          1  2  3  4                   1  2       1  2  3  4  5  6
 5  6  7  8  9 10 11    3  4  5  6  7  8  9    7  8  9 10 11 12 13
12 13 14 15 16 17 18   10 11 12 13 14 15 16   14 15 16 17 18 19 20
19 20 21 22 23 24 25   17 18 19 20 21 22 23   21 22 23 24 25 26 27
26 27 28 29 30         24 25 26 27 28 29 30   28 29 30
                       31
        七月                   八月                   九月        
日 一 二 三 四 五 六   日 一 二 三 四 五 六   日 一 二 三 四 五 六
          1  2  3  4                      1          1  2  3  4  5
 5  6  7  8  9 10 11    2  3  4  5  6  7  8    6  7  8  9 10 11 12
12 13 14 15 16 17 18    9 10 11 12 13 14 15   13 14 15 16 17 18 19
19 20 21 22 23 24 25   16 17 18 19 20 21 22   20 21 22 23 24 25 26
26 27 28 29 30 31      23 24 25 26 27 28 29   27 28 29 30
                       30 31
        十月                  十一月                 十二月       
日 一 二 三 四 五 六   日 一 二 三 四 五 六   日 一 二 三 四 五 六
             1  2  3    1  2  3  4  5  6  7          1  2  3  4  5
 4  5  6  7  8  9 10    8  9 10 11 12 13 14    6  7  8  9 10 11 12
11 12 13 14 15 16 17   15 16 17 18 19 20 21   13 14 15 16 17 18 19
18 19 20 21 22 23 24   22 23 24 25 26 27 28   20 21 22 23 24 25 26
25 26 27 28 29 30 31   29 30                  27 28 29 30 31


[root@hadoop1 linkToRoot]# 
```

## 搜索查找类

### find指令

fnd指令将从指定目录向下递归地遍历其各个子目录,将满足亲件的文件或者目录显示在终端
基本语法
find [搜索范围] [选项]
选项说明
-name <查询方式> 按照指定的文件名查找模式查找文件
-user <用户名> 查找属于指定用户名所有文件
-size<文件大小> 按照指定的文件大小查找文件

小于 -20M 大于 +20M 等于 20M

```
[root@hadoop1 home]# find /home -name hello
/home/hello
[root@hadoop1 home]# find /home -user xm
find: 用户名 ‘xm’ 未知
[root@hadoop1 home]# find /home -size -1M
/home/hello
[root@hadoop1 home]# 

```

### locate指令

locate指令可以快速定位文件路径。 locate指令利用事先建立的系统中所有文件名称及路径的 locate数据库实现快速定位给定的文件。 Locate指令无需遍历整个文件系统,查询速度较快为了保证查询结果的准确度,管理员必须定期更新 locate
基本语法
locate 搜索文件
特别说明
由于 locate指令基于数据库进行査询,所以第一次运行前,必须使用 updatedb指令创建 locate数据库。

```
[root@hadoop1 home]# updatedb
[root@hadoop1 home]# locate a.txt
/home/a.txt
/usr/lib/firmware/brcm/brcmfmac43340-sdio.pov-tab-p1006w-data.txt
/usr/share/doc/vim-common-7.4.629/README_extra.txt
/usr/share/gnupg/help.ca.txt
/usr/share/gnupg/help.da.txt
/usr/share/gnupg/help.ja.txt
/usr/share/libhangul/hanja/hanja.txt
/usr/share/systemtap/examples/io/eatmydata.txt
/usr/share/vim/vim74/doc/ft_ada.txt.gz
/usr/share/vim/vim74/doc/if_lua.txt.gz
/usr/share/vim/vim74/doc/os_amiga.txt.gz
/usr/share/vim/vim74/doc/uganda.txt.gz
[root@hadoop1 home]# 
```

### grep指令和會道符号

grep过滤查找,管道符,“|",表示将前一个命令的处理结果输出传递给后面的命令处理。
基本语法
grep [选项] 查找内容源文件
常用选项
-n 显示匹配行及行号
-i 忽略字母大小写

```
[root@hadoop1 home]# cat hello.txt | grep yes
yes
yes
[root@hadoop1 home]# cat hello.txt | grep -n yes
1:yes
2:yes
[root@hadoop1 home]# cat hello.txt | grep -ni yes
1:yes
2:yes
6:Yes
7:Yes
[root@hadoop1 home]# 
```

## 压缩和解压缩

gzip/gunzip指令
gzip用于压缩文件, gunzip用于解压的

gzip  文件(功能描述:压缩文件,只能将文件压缩为*gz文件)
gunzip 文件 (功能描述:解压缩文件命令)

```
[root@hadoop1 home]# gzip hello.txt
[root@hadoop1 home]# ll
总用量 20
-rw-r--r--. 1 root root 106 2月  19 12:04 a.txt
-rw-r--r--. 1 root root  11 2月  19 12:07 c.txt
-rw-r--r--. 1 root root   0 12月 24 18:42 hello
-rw-r--r--. 1 root root  64 2月  19 12:55 hello.txt.gz
-rw-r--r--. 1 root root 426 2月  19 12:08 mycal
-rw-r--r--. 1 root root 263 2月  19 12:22 mydate.txt
[root@hadoop1 home]# gunzip hello.txt.gz
[root@hadoop1 home]# ll
总用量 20
-rw-r--r--. 1 root root 106 2月  19 12:04 a.txt
-rw-r--r--. 1 root root  11 2月  19 12:07 c.txt
-rw-r--r--. 1 root root   0 12月 24 18:42 hello
-rw-r--r--. 1 root root  42 2月  19 12:55 hello.txt
-rw-r--r--. 1 root root 426 2月  19 12:08 mycal
-rw-r--r--. 1 root root 263 2月  19 12:22 mydate.txt
```

当我们使用gip对文件进行压缩后,不会保留原来的文件

### zip/unzip指令

zip用于压缩文件, unzp用于解压的,这个在项目打包发布中很有用的
基本语法
	zip [选项] xxx.zip 将要压缩的内容(功能描述:压缩文件和目录的命令
	unzip [选项] xxx.zip (功能描述:解压缩文件)
zip常用选项
-r: 递归压缩,即压缩目录

unzip的常用选项
-d <目录> :指定解压后文件的存放目录

```
[root@hadoop1 home]# zip -r myhome.zip /home/
  adding: home/ (stored 0%)
  adding: home/hello (stored 0%)
  adding: home/a.txt (deflated 30%)
  adding: home/c.txt (stored 0%)
  adding: home/mycal (deflated 73%)
  adding: home/mydate.txt (deflated 56%)
  adding: home/hello.txt (deflated 14%)
[root@hadoop1 home]# ll
总用量 24
-rw-r--r--. 1 root root  106 2月  19 12:04 a.txt
-rw-r--r--. 1 root root   11 2月  19 12:07 c.txt
-rw-r--r--. 1 root root    0 12月 24 18:42 hello
-rw-r--r--. 1 root root   42 2月  19 12:55 hello.txt
-rw-r--r--. 1 root root  426 2月  19 12:08 mycal
-rw-r--r--. 1 root root  263 2月  19 12:22 mydate.txt
-rw-r--r--. 1 root root 1419 2月  19 13:01 myhome.zip
[root@hadoop1 home]# 
```

```
[root@hadoop1 home]# unzip -d /opt/tmp/ myhome.zip
Archive:  myhome.zip
   creating: /opt/tmp/home/
 extracting: /opt/tmp/home/hello     
  inflating: /opt/tmp/home/a.txt     
 extracting: /opt/tmp/home/c.txt     
  inflating: /opt/tmp/home/mycal     
  inflating: /opt/tmp/home/mydate.txt  
  inflating: /opt/tmp/home/hello.txt  
[root@hadoop1 home]# cd /opt/tmp
[root@hadoop1 tmp]# ll
总用量 0
drwxr-xr-x. 2 root root 93 2月  19 13:01 home
```

### tar指令

tar指令是打包指令,最后打包后的文件是 .tar.gz的文件。
基本语法
tar [选项] XXX.tar.gz( -C 指定的目录)打包的内容(功能描述:打包目录,压缩后的文件格式, tar.gz)

- -c 产生tar打包文件
- -v 显示详细信息
- -f  指定压缩后的文件名
- -z 打包同时压缩
- -x 解包tar文件

```
[root@hadoop1 home]# ls
a.txt  c.txt  hello  hello.txt  mycal  mydate.txt  myhome.zip
[root@hadoop1 home]# tar -zcvf a.tar.gz a.txt c.txt
a.txt
c.txt
[root@hadoop1 home]# ls
a.tar.gz  a.txt  c.txt  hello  hello.txt  mycal  mydate.txt  myhome.zip
[root@hadoop1 home]# 
```

```
[root@hadoop1 home]# ls
a.tar.gz  hello  hello.txt  mycal  mydate.txt  myhome.zip
[root@hadoop1 home]# tar -zxvf a.tar.gz
a.txt
c.txt
[root@hadoop1 home]# ls
a.tar.gz  a.txt  c.txt  hello  hello.txt  mycal  mydate.txt  myhome.zip
```

# 六、组管理和权限管理

## 组管理

在 linux中的每个用户必须属于一个组,不能独立于组外。在 linux中每个文件有所有者、所在组、其它组的概念

```
cat /etc/passwd | grep mysql
cat /etc/group | grep mysql
```



### 1)所有者

一般为文件的创建者谁创建了该文件,就自然的成为该文件的所有者。

#### 查看文件的所有者

1)指令: Is -ahl
2)应用实例:创建一个组 police,再创建一个用户tom将tom放在 police组然后使用tom来创建一个文件 ok.txt,看看情况如何

```
[root@hadoop1 home]# groupadd police
[root@hadoop1 home]# useradd -g police tom
[root@hadoop1 home]# passwd tom
更改用户 tom 的密码 。
新的 密码：
无效的密码： 密码少于 8 个字符
重新输入新的 密码：
passwd：所有的身份验证令牌已经成功更新。
[root@hadoop1 home]# 




[tom@hadoop1 ~]$ pwd
/home/tom
[tom@hadoop1 ~]$ touch ok.txt
[tom@hadoop1 ~]$ ls -ahl
总用量 12K
drwx------. 6 tom  police 135 2月  19 13:32 .
drwxr-xr-x. 3 root root   138 2月  19 13:29 ..
-rw-r--r--. 1 tom  police  18 4月   1 2020 .bash_logout
-rw-r--r--. 1 tom  police 193 4月   1 2020 .bash_profile
-rw-r--r--. 1 tom  police 231 4月   1 2020 .bashrc
drwxr-xr-x. 3 tom  police  18 2月  19 13:31 .cache
drwxr-xr-x. 3 tom  police  18 2月  19 13:31 .config
drwxr-xr-x. 3 tom  police  19 2月  19 13:31 .local
drwxr-xr-x. 4 tom  police  39 12月 24 14:23 .mozilla
-rw-r--r--. 1 tom  police   0 2月  19 13:32 ok.txt
```

#### 修改文件所有者

指令: chown 用户名 文件名  (The owner)
应用案例
要求:使用root创建一个文件 apple.txt,然后将其所有者修改成tom

```
[root@hadoop1 home]# touch apple.txt
[root@hadoop1 home]# ls
apple.txt  a.tar.gz  a.txt  c.txt  hello  hello.txt  mycal  mydate.txt  myhome.zip  tom
[root@hadoop1 home]# chown tom apple.txt
[root@hadoop1 home]# ls -l
总用量 28
-rw-r--r--. 1 tom  root      0 2月  19 13:36 apple.txt
```



### 2)所在组

新增组

groupadd 组名

删除组

groupdel 组名

增加用户时直接加上组

useradd -g 用户组 用户名

当某个用户创建了一个文件后,默认这个文件的所在组就是该用户所在的组。
查看文件/目录所在组
Is -ahl
应用实例
修改文件所在的组
基本指令
chgrp 组名 文件名
应用实例
使用root户创建文件 orange. txt,看看当前这个文件属于哪个组,然后将这个文件所在组,修改到 police组。

```
[root@hadoop1 home]# touch orange.txt
[root@hadoop1 home]# ll
-rw-r--r--. 1 root root      0 2月  19 13:40 orange.txt
[root@hadoop1 home]# chgrp police orange.txt
[root@hadoop1 home]# ll
-rw-r--r--. 1 root police    0 2月  19 13:40 orange.txt
```



### 3)其它组

除文件的所有者和所在组的用户外,系统的其它用户都是文件的其它组

### 4)改变用户所在的组

在添加用户时,可以指定将该用户添加到哪个组中,同样的用root的管理权限可以改变某个用户所在的组。
改变用户所在组

1) usermod -g 组名 用户名
2) usermod -d 目录名 用户名   改变该用户登陆的初始目录。
应用实例
创建一个土匪组( bandit)将tom这个用户从原来所在的 police组,修改到 bandit(土匪)组。

```
[root@hadoop1 home]# id tom
uid=1001(tom) gid=1003(police) 组=1003(police)
[root@hadoop1 home]# groupadd bandit
[root@hadoop1 home]# usermod -g bandit tom
[root@hadoop1 home]# id tom
uid=1001(tom) gid=1004(bandit) 组=1004(bandit)
[root@hadoop1 home]# 
```

## 权限管理

![image-20220219135411211](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220219135411211.png)

```
-rw-r--r--. 1 root police    0 2月  19 13:40 orange.txt
文件类型：文件所有者权限：文件所在组用户权限：文件的软链接个数或子目录数量：文件所有者：文件所在组：文件大小：最后修改时间：文件名
```

![image-20220219141340381](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220219141340381.png)



### rwx权限详解

rwx作用到文件
1)[r]代表可读(read:可以读取查看
2)[w]代表可写 write:可以修改但是不代表可以删除该文件删除一个文件的前提条件是对该文件所在的目录有写权限,才能删除该文件
3)[x]代表可执行( execute):可以被执行

rwx作用到目录
1)[r代表可读(read):可以读取,ls查看目录内容
2)[w]代表可写wite:可以修改,目录内创建+删除+重命名目录
3)[x]代表可执行 execute:可以进入该目录

ls -l中显示的内容如下:
-rwx-rw--r--. 1 root police    0  2月  19 13:40 orange.txt
10个字符确定不同用户能对文件干什么
第一个字符代表文件类型:文件(-),目录(d),链接(l)
其余字符每3个一组(rwx)读(r)写(w)执行(x)
第一组rwx:文件拥有者的权限是读、写和执行
第二组rw-:与文件拥有者同一组的用户的权限是读、写但不能执行
第三组r--:不与文件拥有者同组的其他用户的权限是读不能写和执行
可用数字表示为:r=4,W=2,x=1因此rwx=4+2+1=7
1   					  文件:硬连接数 目录:子目录数
root 		 		  用户
police       		  组
0                	     (文件大小字节),如果是文件夹,显示4096字节
2月  19 13:40    最后修改日期
orange.txt        文件名

### 修改权限- chmod

![image-20220219140650891](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220219140650891.png)

通过 chmod指令,可以修改文件或者目录的权限。
第一种方式:+、-、=变更权限
u:所有者 g:所有组 o:其他人 a:所有人{u、g、o的总和)

1. chmod u=rwx g=x o=x 文件目录名

2. chmod o+w  文件目录名

3. chmod a-x   文件目录名

4. 案例演示
    1)给abc文件的所有者读写执行的权限,给所在组读执行权限,给其它组读执行权限。

  ```
  [root@hadoop1 test]# touch abc
  [root@hadoop1 test]# ll
  总用量 0
  -rw-r--r--. 1 root root 0 2月  19 14:19 abc
  [root@hadoop1 test]# chmod u=rwx,g=rx,o=rw abc
  [root@hadoop1 test]# ll
  总用量 0
  -rwxr-xrw-. 1 root root 0 2月  19 14:19 abc
  [root@hadoop1 test]# 
  ```

  

  2)给abc文件的所有者除去执行的权限,增加组写的权限

  ```
  [root@hadoop1 test]# chmod u-x,g+w abc
  [root@hadoop1 test]# ll
  总用量 0
  -rw-rwxrw-. 1 root root 0 2月  19 14:19 abc
  [root@hadoop1 test]# 
  ```

  

  3)给abc文件的所有用户添加读的权限

  ```
  [root@hadoop1 test]# chmod a+r abc
  [root@hadoop1 test]# ll
  总用量 0
  -rw-rwxrw-. 1 root root 0 2月  19 14:19 abc
  [root@hadoop1 test]# 
  ```

  

第二种方式:通过数字变更权限
r=4 w=2 x=1
rwx=4+2+1=7
chmod u=rwx,g=x,o=x文件目录名
相当于 chmod 751 文件目录名

### 修改文件所有者- chown

基本介绍
chown newowner file 改变文件的所有者
chown newnowner:newgroup file 改变用户的所有者和所有组
-R 如果是目录则使其下所有子文件或目录递归生效

```
[root@hadoop1 tom]# ll
总用量 0
-rw-r--r--. 1 root root   0 2月  19 14:31 abc.txt
-rw-r--r--. 1 tom  bandit 0 2月  19 13:32 ok.txt
[root@hadoop1 tom]# chown tom abc.txt
[root@hadoop1 tom]# ll
总用量 0
-rw-r--r--. 1 tom root   0 2月  19 14:31 abc.txt
-rw-r--r--. 1 tom bandit 0 2月  19 13:32 ok.txt

[root@hadoop1 home]# chown -R tom kkk/
[root@hadoop1 home]# ll
drwxr-xr-x. 2 tom  root      6 2月  19 14:33 kkk



[root@hadoop1 tom]# ll
总用量 0
-rw-r--r--. 1 tom root   0 2月  19 14:31 abc.txt
-rw-r--r--. 1 tom bandit 0 2月  19 13:32 ok.txt
-rw-r--r--. 1 tom police 0 2月  19 14:33 test.txt
[root@hadoop1 tom]# chown tom:police abc.txt
[root@hadoop1 tom]# ll
总用量 0
-rw-r--r--. 1 tom police 0 2月  19 14:31 abc.txt
-rw-r--r--. 1 tom bandit 0 2月  19 13:32 ok.txt
-rw-r--r--. 1 tom police 0 2月  19 14:33 test.txt

```

### 修改文件所在组 chgrp

基本介绍
chgrp newgroup file改变文件的所有组

```
[root@hadoop1 home]# chgrp -R police kkk/
[root@hadoop1 home]# ll
drwxr-xr-x. 2 tom  police    6 2月  19 14:33 kkk

```

# 七、任务调度

## crond任务调度

crontab进行定时任务的设置
概述
	任务调度:是指系统在某个时间执行的特定的命令或程序。
	任务调度分类:

1. 系统工作:有些重要的工作必须周而复始地执行。如病毒扫描等
2. 个别用户工作:个别用户可能希望执行某些程序,比如对mysq数据库的备份。

基本语法
crontab [选项]
常用选项

- -e : 编辑 crontab定时任务
- -i : 查询 crontab任务
- -r : 删除当前用户所有的 crontab任务

![image-20220220114200709](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220114200709.png)

任务的要求
	设置任务调度文件:/ etc/crontab
	设置个人任务调度。执行 crontab -e命令。
接着输入任务到调度文件
*/1 * * * * ls -l /etc/ > /tmp/to txt
意思说每小时的每分钟执行ls -l /etc > /mp/totxt命令
步骤如下
1)crontab -e
2)*/1 * * * * ls -l /etc >> /tmp/to.txt
3)当保存退出后就程序
4)在每一分钟都会自动的调用ls -l /etc >> /tmp/tot

```
[root@hadoop1 ~]# crontab -e
no crontab for root - using an empty one
crontab: installing new crontab
[root@hadoop1 ~]# cd /tmp
[root@hadoop1 tmp]# more to.txt
总用量 1412
drwxr-xr-x.  3 root root      101 12月 24 14:25 abrt
-rw-r--r--.  1 root root       16 12月 24 14:31 adjtime
drwxr-xr-x.  2 root root      116 12月 24 14:26 akonadi
-rw-r--r--.  1 root root     1529 4月   1 2020 aliases
-rw-r--r--.  1 root root    12288 12月 24 14:32 aliases.db
drwxr-xr-x.  3 root root       65 12月 24 14:25 alsa
```

参数细节说明
5个占位符的说明
第一个" * " 一个小时当中的第几分钟    0-59
第二个" * " 一天当中的第几小时		    0-23
第三个" * " 一个月当中的第几天			1-31
第四个" * " 一年当中的第几月				1-12
第五个" * " 一周当中的星期几				0-7(0和7都代表星期日)

特殊符号的说明
" * "代表任何时间。比如第一个“ * "就代表一小时中每分钟都执行
一次的意思
" , "代表不连续的时间。比如"0 8,12,16 * * * 命令”,就代表在每天
的8点0分,12点0分,16点0分都执行一次命令
" - ""代表连续的时间范围。比如“5 * * 1-6命令”,代表在周一到周
六的凌晨5点0分执行命令
" */n ""代表每隔多久执行一次。比如“ */10 * * * *命令”,代表每隔
10分钟就执行一遍命令

![image-20220220115713372](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220115713372.png)

### 应用实例

案例1:每隔1分钟,就将当前的日期信息,追加到 /tmp/mydate文件中

```
[root@hadoop1 tmp]# cd /home
[root@hadoop1 home]# vim mytask.sh

date >> /tmp/mydate

[root@hadoop1 home]# chmod u+x mytask.sh

[root@hadoop1 home]# crontab -e

*/1 * * * * /home/mytask.sh

crontab: installing new crontab
[root@hadoop1 home]# cd /tmp
[root@hadoop1 tmp]# ls
akonadi-root.77l9bX                                   to.txt
kde-root                                             vmware-root_626-2697073973
mydate
[root@hadoop1 tmp]# more mydate
2022年 02月 20日 星期日 12:04:02 CST
2022年 02月 20日 星期日 12:05:01 CST
[root@hadoop1 tmp]# 

```



案例2:每隔1分钟,将当前日期和日历都追加到 home/mycal文件中

```
date >> /tmp/mycal
cal >> /home/mycal
```



案例3:每天凌晨200将 mysql数据库 testdb,备份到文件中。

```
[root@hadoop1 home]# vim mytask3.sh

/usr/local/mysql/bin/mysqldump -uroot -proot testdb > /tmp/mydb.bak
```

rond相关指令:
1) conrtab -r:终止任务调度。
2) crontab -i:列出当前有那些任务调度
3) service crond restart[重启任务调度] 

# 八、Linux磁盘分区、挂载

## 分区基础知识

分区的方式:
1)mbr分区
1.最多支持四个主分区
2.系统只能安装在主分区
3.扩展分区要占一个主分区
4.MBR最大只支持2TB,但拥有最好的兼容性
2)gtp分区
1支持无限多个主分区(但操作系统可能限制,比如 windows下最多128个分区)
2最大支持18EB的大容量(EB=1024PB,PB=1024TB
3 windows7 64位以后支持gtp

### windows下的磁盘分区

![image-20220220123725023](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220123725023.png)

### Linux分区

原理介绍

1. Lnux来说无论有几个分区,分给哪一目录使用,它归根结底就只有一个根目录,一个独立且唯一的文件结构, Linux中每个分区都是用来组成整个文件系统的一部分。
2. Linux采用了一种叫“载入”的处理方法,它的整个文件系统中包含了一整套的文件和目录,且将一个分区和一个目录联系起来。这时要载入的一个分区将使它的存储空间在目录下获得。
3. 示意图

硬盘说明
1) Linux硬盘分IDE硬盘和SCSI硬盘,目前基本上是SCSI硬盘
2) 对于DE硬盘,驱动器标识符为“hdx~"其中“hd"表明分区所在设备的类型,这里是指IDE硬盘了。“x"为盘号(a为基本盘,b为基本从属盘,c为辅助主盘,d为辅助从属盘);"~"代表分区,前四个分区用数字1到4表示,它们是主分区或扩展分区,从5开始就是逻辑分区。例,hda3表示为第一个IDE硬盘上的第三个主分区或扩展分区hdb2表示为
    第二个DE硬盘上的第二个主分区或扩展分区
3) 对于SCS硬盘则标识为“sdx~",SCSI硬盘是用“sd"来表示分区所在设备的类型的,其余则和DE硬盘的表示方法一样

```
[root@hadoop1 ~]# lsblk -f
NAME   FSTYPE LABEL UUID                                 MOUNTPOINT
sda                                                      
├─sda1 xfs          e895ddd3-f437-4d68-ac94-a9ae601f7cdb /boot
├─sda2 swap         a1cfb3ff-e6c3-4335-84bf-c116124effab [SWAP]
└─sda3 xfs          44a3cfcb-1ad5-4a39-88dc-472ede7bada4 /
sr0 
```

![image-20220220124719875](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220124719875.png)

![image-20220220124748095](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220124748095.png)

## 挂载的经典案例

1. 虚拟机添加硬盘

   在【虛拟机】菜单中,选择【设置】,然后设备列表里添加硬盘,然后一路【下一步】,中间只有选择磁盘大小的地方需要修改,至到完成。然后重启系统(才能识别)

   ![image-20220220125921507](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220125921507.png)

2. 分区

   分区命令 fdisk /dev/sd0
   开始对/sdb分区
   m显示命令列表
   p显示磁盘分区同 fdisk
   n新增分区
   d删除分区
   W写入并退出
   说明:开始分区后输入n,新增分区,然后选择ρ,分区类型为主分区。两次回车默认剩余全部空间。最后输入W写人公区并退出,若不保存退出输入

3. 格式化

   格式化磁盘
   分区命令:mkfs -t ext4 /dev/sdb1
   其中ext4是分区类型

4. 挂载

   挂载:将一个分区与一个目录联系起来
   mount设备名称挂载目录
   例如: mount /dev/sdb1 /newdisk
   umount 设备名称或者挂载目录

   umount  /dev/sdb1或者 umount /nedisk

   用命令行挂载重启后会失效

5. 设置可以自动挂载(重启后仍然可以挂载)

   永久挂载:通过修改/etc/fstab实现挂载
   添加完成后执行 mount -a即刻生效

   ![image-20220220131541731](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220131541731.png)

## 磁盘情况查询

查询系统整体磁盘使用情况
基本语法
df -h
·应用实例
查询系统整体磁盘使用情况

![image-20220220131856697](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220131856697.png)

du -h 目录
查询指定目录的磁盘占用情况,默认为当前目录
-s指定目录占用大小汇总
-h带计量单位
-a含文件

--max-depth=1子目录深度

-c列出明细的同时,增加汇总值

```
[root@hadoop1 ~]# du -h /opt
0	/opt/rh
20K	/opt/tmp/home
20K	/opt/tmp
20K	/opt
[root@hadoop1 ~]# du -ach --max-depth=1
4.0K	./.bash_logout
4.0K	./.bash_profile
4.0K	./.bashrc
4.0K	./.cshrc
4.0K	./.tcshrc
4.0K	./anaconda-ks.cfg
19M	./.cache
8.0K	./.dbus
148K	./.config
440K	./.kde
4.0K	./initial-setup-ks.cfg
1.3M	./.local
4.0K	./.gtkrc-2.0-kde4
0	./桌面
0	./下载
0	./模板
0	./公共
0	./文档
0	./音乐
0	./图片
0	./视频
4.0K	./.esd_auth
132K	./.thumbnails
576K	./.gstreamer-0.10
59M	./.mozilla
8.0K	./.bash_history
4.0K	./.directory
628K	./6df9159ce99f48a9be1938af646a80ed.jpg
4.0K	./hello.java
4.0K	./OK.java
0	./xxx.txt
4.0K	./.lesshst
4.0K	./.viminfo
80M	.
80M	总用量
[root@hadoop1 ~]# 
```

## 实用命令

1)统计home文件夹下文件的个数

```
[root@hadoop1 ~]# cd /home
[root@hadoop1 home]# ls -l /home | grep "^-" | wc -l
13
[root@hadoop1 home]# 
```



2)统计home文件夹下目录的个数

```
[root@hadoop1 home]# ls -l /home | grep "^d" | wc -l
4
[root@hadoop1 home]# 
```



3)统计home文件夹下文件的个数,包括子文件夹里的

```
[root@hadoop1 home]# ls -lR /home | grep "^-" | wc -l
17
[root@hadoop1 home]# 
```



4)统计文件夹下目录的个数,包括子文件夹里的

```
[root@hadoop1 home]# ls -lR /home | grep "^d" | wc -l
5
[root@hadoop1 home]# 
```

5)以树状显示目录结构

```
yum install tree


已安装:
  tree.x86_64 0:1.6.0-10.el7                                                                                                

完毕！
[root@hadoop1 home]# tree
.
├── abc
├── abc.txt
├── apple.txt
├── a.tar.gz
├── a.txt
├── c.txt
├── hello
├── hello.txt
├── kkk
├── mycal
├── mydate.txt
├── myhome.zip
├── mytask.sh
├── newdisk
│   └── lost+found
├── orange.txt
├── test
│   └── abc
└── tom
    ├── abc.txt
    ├── ok.txt
    └── test.txt

5 directories, 17 files
[root@hadoop1 home]# 

```



# 九、网络配置

## 查看网络IP和网关

## 查看 windows环境的中VMnet8网络配置( ipconfig指令)

![image-20220220135528351](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220135528351.png)



![image-20220220135458140](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220135458140.png)

## ping目的主机(功能描述:测试当前服务器是否可以连接目的主机)

![image-20220220135707237](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220135707237.png)

## linux网络环境配置

第一种方法(自动获取):
说明:登陆后,通过界面的来设置自动获取ip

![image-20220220135850319](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220135850319.png)

缺点:linux启动后会自动获取IP缺点是每次自动获取的地址可能不一样,不适合做服务器

第二种方法(固定的ip)
说明
直接修改配置文件来指定IP,并可以连接到外网(程序员推荐),编辑ⅵ
/etc/sysconfig/network-scripts/ifcfg-eth0

/etc/sysconfig/network-scripts/ifconfig-ens33
[root@hadoop1 home]# vim /etc/sysconfig/network-scripts/ifcfg-ens33

```
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
BOOTPROTO=static
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
IPV6_PRIVACY=no
NAME=ens33
UUID=6b4b1ecd-0ad7-4243-a45a-64ff6f6a5327
ONBOOT=yes
IPADDR=192.168.229.110
NETMASK=255.255.255.0
GATEWAY=192.168.229.2
DNS1=192.168.229.2
```

service network restart

# 十、进程

基本介绍

- 在LINUX中,每个执行的程序(代码)都称为一个进程。每一个进程都分配一个ID号。
- 每一个进程,都会对应一个父进程,而这个父进程可以复制多个子进程。例如window服务器
- 每个进程都可能以两种方式存在的。前台与后台,所谓前台进程就是用户目前的屏幕上可以进行操作的。后台进程则是实际在操作,但由于屏幕上无法看到的进程,通常使用后台方式执行。
- 一般系统的服务都是以后台进程的方式存在,而且都会常驻在系统中。直到关机才才结束。

## 显示系统执行的进程

基本介绍
ps 命令是用来查看目前系统中,有哪些正在执行,以及它们执行的状况。

PID 进程识别号
TTY 终端机号
TIME 此进程所消CPU时间
CMD 正在执行的命令或进程名

![image-20220220204315185](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220204315185.png)

ps -a:显示当前终端的所有进程信息
ps -u:以用户的格式显示进程信息
ps -x:展示后台进程运行的参数

![image-20220220205502163](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220205502163.png)

![image-20220220204559740](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220204559740.png)

### 详解

1. 指令:ps -aux | grep xxx
2. 指令说明
   - System V展示风格
   - USER:用户名称
   - PID:进程号
   - %CPU:进程占用pU的百分比
   - %MEM:进程占用物理内存的百分比
   - VSZ:进程占用的虚拟内存大小(单位:KB)
   - RSS:进程占用的物理内存大小(单位:KB)
   - TT:终端名称缩写
   - STAT:进程状态,其中S睡眠,s表示该进程是会话的先导进程,N表示进程拥有比普通先级更低的优先级,R-正在运行,D-短期等待,Z-僵死进程,T被跟踪或者被停止等等
   - STARTED:进程的启动时间
   - TIME:CPU时间,即进程使用CPU的总时间
   - COMMAND:启动进程所用的命令和参数,如果过长会被截断显示

应用实例
要求:以全格式显示当前所有的进程,查看进程的父进程。
ps -ef是以全格式显示当前所有的进程
-e显示所有进程。-f全格式。
ps -ef | grep xxx

- 是BSD风格
- UID:用户ID
- PID:进程ID
- PPID:父进程ID
- C:CPU用于计算执行优先级的因子。数值越大,表明进程是CPU密集型运算,执行优先级会降低;数值越小,表明进程是I/O密集型运算,执行优先级会提高
- STME:进程启动的时间
- TTY:完整的终端名称
- TIME:CPU时间
- CMD:启动进程所用的命令和参数

## 终止进程kill和 killall

介绍:
若是某个进程执行一半需要停止时,或是已消了很大的系统资源时,此时可以考虑停止该进程。使用ki;;命令来完成此项任务。
基本语法:
kill [选项] 进程号(功能描述:通过进程号杀死进程)
killall 进程名称(功能描述:通过进程名称杀死进程,也支持通配符,这在系统因负载过大而变得很慢时很有用)
常用选项

- 9:表示强迫进程立即停止
  最佳实践:
  案例1:踢掉某个非法登录用户

- ![image-20220220212336921](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220212336921.png)

  

- 案例4:强制杀掉一个终端

- ![image-20220220213217824](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220213217824.png)

## 查看进程树 pstree

基本语法:
pstree [选项],可以更加直观的来看进程信息
常用选项:
-p:显示进程的PID
-u:显示进程的所属用户
应用实例:
案例1:请你树状的形式显示进程的pid

![image-20220220213316623](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220213316623.png)案例2:请你树状的形式进程的用户id

![image-20220220213338870](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220213338870.png)

## 服务 (service)管理

服务( service)本质就是进程,但是是运行在后台的,通常都会监听某个端口,等待其它程序的请求,比如{mysq,sshd防火墙等),因此我们又称为守护进程,是linux中非常重要的知识点。
service管理指令:
service服务名 start stop restart reload status

centOS6.service

centOS7.systemctl
使用案例:
1)查看当前防火墙的状况,关闭防火墙和重启防火墙。
细节讨论:

![image-20220220214012654](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220214012654.png)

![image-20220220215329719](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220215329719.png)

1)关闭或者启用防火墙后,立即生效。[ telnet测试某个端囗即可
2)这种方式只是临时生效,当重启系统后,还是回归以前对服务的设置。
3)如果希望设置某个服务自启动或关闭永久生效,要使用 chkconfig指令,



## 查看服务名:

方式1:使用setup -> 系统服务就可以看到。
方式2:centOS6 /etc/init.d 服务名称 centOS7 systemctl list-unit-files

## chkconfig指令

介绍
1通过 chkconfig命令可以给各个运行级别设置自启动/关闭
基本语法

1. 查看服务 chkconfig --list | grep xxx

2) chkconfig 服务名 --list
3) chkconfig --level 5 服务名 on/off
4) ![image-20220220220431322](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220220220431322.png)

## 动态监控进程

介绍:
top与ps命令很相似。它们都用来显示正在执行的进程。top与ps最大的不同之处,在于top在执行一段时间可以更新正在运行的的进程。
基本语法:
top  [选项]

![image-20220221123941194](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221123941194.png)

![image-20220221123953007](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221123953007.png)

选项说明:

- -d 秒数 指定top命令每隔几秒更新,默认是3秒在top命令的交互模式当中可以执行的
- -i 使top不显示任何闲置或者僵死进程
- -p 通过指定监控进程ID来仅仅监控某个进程的状态

- 监视特定用户
  - top:输入此命令,按回车键,查看执行的进程。
  - u:然后输入“u”回车,再输入用户名,即可
  - ![image-20220221124210925](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221124210925.png)

- 终止指定的进程。
  - top:输入此命令,按回车键,査看执行的进程。
  - k:然后输入“k”回车,再输入要结束的进程ID号
  - ![image-20220221124236728](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221124236728.png)

### 交互操作说明:

操作
功能

- P 以CPU使用率排序,默认扰是此项
- M 以内存的使用率排序
- N 以PID排序
- q退出top

## 监控网络状态

查看系统网络情况 netstat
基本语法
netstat[选项]
选项说明

- -an  按一定顺序排列输出
- -p 显示哪个进程在调用
- ![image-20220221124716818](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221124716818.png)
- 应用案例
  请查看服务名为sshd的服务的信息。
  - ![image-20220221124749990](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221124749990.png)
- 检测主机连接命令ping
  是一种网络检测检测工具,它主要是用检测远程主机是否正常,或是两部主机间的介质是否为断、网线是否脱落或网卡故障
  如:ping 对方ip地址

# 十一、RPM和YUM

介绍:
一种用于互联网下载包的打包及安装工具,它包含在某些Lnux分发版中。它生成具有RPM扩展名的文件。RPM是 RedHat Package Manager( Redhat软件包管理工中具)的缩写,类似 windows的 setup.exe,这一文件格式名称虽然打上了 Redhat的标志,但理念是通用的。
Linux的分发版本都有采用(suse, redhat, centos等等),可以算是公认的行业标准了

## rpm包的简单查询指令

查询已安装的rpm列表rpm -qa | grep xxx
rpm包名基本格式
一个rpm包名: firefox-68.10.0-1.el7.centos.x86_64
名称:; firefox
版本号:68.10.0-1
适用操作系统:el7.centos.x86_64
表示 centos7.x的64位系统
如果是i686、i386表示32位系统, noarch表示通用。。

rpm包的其它查询指令:

- rpm -qa:查询所安装的所有rpm软件包
- rpm -qa | more
- rpm -ql 软件包名:查询软件包中的文件
- rpm -ql firefox
- rpm -qa | grep x [rpm -qa grep firefox]
- rpm -qf 文件全路径名:查询文件所属的软件包
- rpm -qf /etc/passwd
- ![image-20220221130322057](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221130322057.png)
- rpm -qf /root/install.log
- rpm -q 软件包名:查询软件包是否安装
- rpm -q firefox
- rpm-qi 软件包名:查询软件包信息
- rpm-qi file

## 卸载rpm包

基本语法
rpm -e RPM包的名称
应用案例
删除 firefox软件包
细节讨论
1)如果其它软件包依赖于您要卸载的软件包,卸载时则会产生错误信息。
rpm -e foo
removing these packages would break dependencies foo is needed by bar-1.0-1
2)如果我们就是要刪除foo这个rpm包,可以增加参数 -nodeps,就可以强制删除,但是一般不推荐这样做,因为依赖于该软件包的程序可能无法运行
如:rpm-e  foo --nodeps

## 安装rpm包:

基本语法
rpm -ivh RPM包全路径名称
参数说明
i=install安装
v=verbose提示
h=hash进度条

![image-20220221132434274](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221132434274.png)

![image-20220221132417007](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221132417007.png)

![image-20220221132633258](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221132633258.png)

## yum

介绍:
Yum是一个Shell前端软件包管理器。基于RPM包管理,能够从指定
的服务器自动下载RPM包并且安装,可以自动处理依赖性关系,并
且一次安装所有依赖的软件包。
yum的基本指令
查询yum服务器是否有需要安装的软件

yum list | grep xx 软件列表

安装指定的yum包
yum install xxx下载安装

![image-20220221133446105](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221133446105.png)

# 十二、javaEE

## JDK

1. 上传软件![image-20220221141200616](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221141200616.png)

2. 解压

   ![image-20220221141354932](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221141354932.png)

3. 配置环境变量 vim /etc/profile   source/etc/profile

   ![image-20220221141709817](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221141709817.png)

   ![image-20220221142251918](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221142251918.png)

## Tomcat

![image-20220221143053991](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221143053991.png)

## [Centos7开放及查看端口](https://www.cnblogs.com/heqiuyong/p/10460150.html)

1、开放端口

**firewall-cmd --zone=public --add-port=5672/tcp --permanent**  # 开放5672端口

**firewall-cmd --zone=public --remove-port=5672/tcp --permanent** #关闭5672端口

**firewall-cmd --reload**  # 配置立即生效

2、查看防火墙所有开放的端口

**firewall-cmd --zone=public --list-ports**

3.、关闭防火墙

如果要开放的端口太多，嫌麻烦，可以关闭防火墙，安全性自行评估

**systemctl stop firewalld.service**

![image-20220221143501823](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221143501823.png)

![image-20220221143849217](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221143849217.png)

![image-20220221143833798](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221143833798.png)

## IDEA

![image-20220221144504791](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220221144504791.png)

## MYSQL

<https://dev.mysql.com/doc/refman/8.0/en/linux-installation-rpm.html>

# 十三、shell

shell是什么
Shell是命令行解释器,它为用户提供了一个向 Linux内核发送请求以便运行程序的界面系统级程序,用户可以用Shell来启动、挂起、停止甚至是编写一些程序。

## shell脚本的执行方式

脚本格式要求

1. 脚本以#!/bin/bash开头

2. 脚本需要有可执行权限

3. 编写第一个Shell脚本
   需求说明
   创建一个Shell脚本,输出 hello world!

4. ![image-20220222124558860](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222124558860.png)脚本的常用执行方式

   1. 方式1(输入脚本的绝对路径或相对路径)
      (1)首先要赋予helloword.sh脚本的+x权限
      (2)执行脚本

   2. 方式2(sh+脚本)
      说明:不用赋予脚本+x权限,直接执行即可

5. ![image-20220222125130541](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222125130541.png)

## shell 的变量

1) Linux Shell中的变量分为,系统变量和用户自定义变量。
2) 系统变量: $HOME、$PWD、 $SHELL、 $USER等等
    比如: echo $HOME等等
  1) ![image-20220222125553889](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222125553889.png)
  2) ![image-20220222125536840](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222125536840.png)
3) 显示当前shell中所有变量:set
  1) ![image-20220222125658976](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222125658976.png)
4) shell变量的定义
    基本语法
    1)定义变量:变量=值
    2)撤销变量: unset 变量
    3)声明静态变量: readonly 变量,注意:不能 unset

![image-20220222125916041](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222125916041.png)

![image-20220222125923375](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222125923375.png)

![image-20220222130059115](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222130059115.png)

![image-20220222130111211](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222130111211.png)

## 定义变量的规则

1. 变量名称可以由字母、数字和下划线组成,但是不能以数字开头。
2. 等号两侧不能有空格
3. 变量名称一般习惯为大写
4. 将命令的返回值赋给变量(重点)
   1)A=`ls -a`反引号,运行里面的命令,并把结果返回给变量A
   2)A=$(ls -a)等价于反引号
5. ![image-20220222130724537](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222130724537.png)|![image-20220222130733253](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222130733253.png)
6. ![image-20220222130906407](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222130906407.png)![image-20220222130912323](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222130912323.png)

## 设置环境变量

1. export 变量名=变量值(功能描述:将shell变量输岀为环境变量

2) source 配置文件(功能描述:让修改后的配置信息立即生效 )
3) echo $变量名(功能描述:查询环境变量的值)

![image-20220222131819116](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222131819116.png)

![image-20220222132204793](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222132204793.png)

![image-20220222132211100](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222132211100.png)

## 位置参数变量

当我们执行一个shell脚本时,如果希望获取到命令行的参数信息,就可以使用到位置参数变量
比如:./myshell.sh 100 200,这个就是一个执行shell的命令行,可以在 myshell脚本中获取到参数信息
基本语法

- $n(功能描述:n为数字,$0代表命令本身,$1-$9代表第一到第九个参数,十以上的参数数需要用大括号包含,如${10})
- $* (功能描述:这个变量代表命令行中所有的参数,$*把所有的参数看成一个整体)
- $@(功能描述:这个变量也代表命令行中所有的参数,不过$@把每个参数区分对待)
- $#(功能描述:这个变量代表命令行中所有参数的个数)

![image-20220222132823936](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222132823936.png)

![image-20220222132805688](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222132805688.png)

## 预定义变量

就是shell设计者事先已经定义好的变量,可以直接在shell脚本中使用
基本语法

- $$ (功能描述:当前进程的进程号(PID))
- $! (功能描述:后台运行的最后一个进程的进程号(PID))
- $? (功能描述:最后一次执行的命令的返回状态。如果这个变量的值为0,证明上一个命令正确执行;如果这个变量的值为非0(具体是哪个数,由命令自己来决定),则证明上一个命令执行不正确了。)

![image-20220222133358707](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222133358707.png)

![image-20220222133403557](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222133403557.png)

## 运算符

1. “$()(运算式))"或“$[运算式]"
2. expr m + n
   注意expr运算符间要有空格
3. expr m - n
4. expr \ *, /, %乘,除,取余

![image-20220222134424741](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222134424741.png)

![image-20220222134429466](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222134429466.png)条件判断

## 判断语句

[condition] (注意 condition前后要有空格)
#非空返回true,可使用$?验证(0为true,1为 false
应用实例
[ atguigu ] 返回true
[]                 返回false
[ condition ] && echo OK || echo notok  条件满足,执行后面的语句

### 判断语句

常用判断条件

1. 两个整数的比较
   - = 字符串比较
   - -lt 小于
   - -le 小于等于
   - -eq等于
   - -gt大于
   - -ge大于等于
   - -ne不等于
2. 按照文件权限进行判断
   - -r有读的权限
   - -w有写的权限
   - -x有执行的权限
3. 按照文件类型进行判断
   - -f文件存在并且是一个常规的文件
   - -e文件存在
   - -d文件存在并是一个目录

![image-20220222135633048](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222135633048.png)

![image-20220222135636708](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222135636708.png)

## 流程控制

### if判断

基本语法
if [ 条件判断式 ];then

​	程序

fi

if [ 条件判断式 ]

then
		程序

elif [ 条件判断式 ]

then

​		程序

fi

![image-20220222140155079](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222140155079.png)

![image-20220222140202167](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222140202167.png)

### case语句

case $变量名 in 

"值1")

如果变量的值等于值1,则执行程序1

;;
"值2")

如果变量的值等于值2,则执行程序2

;;

...省略其他分支...
*)

如果变量的值都不是以上的值,则执行此程序

;;

esac

![image-20220222140943958](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222140943958.png)

![image-20220222140948634](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222140948634.png)

### for循环

基本语法1
for 变量 in 值1 值2 值3

do

​	程序

done

应用实例

案例1:打印命令行输入的参数

![image-20220222141642452](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222141642452.png)

![image-20220222141652161](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222141652161.png)

基本语法2

for((初始值;循环控制条件;变量变化))

do

​	程序

done

应用实例
案例1:从1加到100的值输出显示[这里可以看出$*和$@的区别

![image-20220222142201082](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222142201082.png)

![image-20220222142205622](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222142205622.png)

### while循环

while [条件判断式]

do

​	程序

done
应用实例
案例1:从命令行输入一个数n,统计从1+到n的值是多少?

![image-20220222142708888](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222142708888.png)

![image-20220222142712831](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222142712831.png)

## read读取控制台输入

read(选项)(参数
选项
-p:指定读取值时的提示符;
-t:指定读取值时等待的时间(秒),如果没有在指定的时间内输入,就不再等待了
参数
变量:指定读取值的变量名
应用实例
案例1:读取控制台输入一个num值
案例2:读取控制台输入一个num值,在10秒内输入。

![image-20220222150841432](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222150841432.png)

![image-20220222150846347](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222150846347.png)

## 函数介绍

shell编程和其它编程语言一样,有系统函数,也可以自定义函数。系统函数中,我们这里就介绍两个

### 系统函数

- basename基本语法
  功能:返回完整路径最后/的部分,常用于获取文件名
  basename [pathname] [suffix] 

  basename [string] [suffix]

  ![image-20220222151224867](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222151224867.png)(功能描述: basename命令会删掉所有的前缀包括最后一个(‘/'字符,然后将字符串显示出来。
  选项
  suffix为后缀,如果suffix被指定了, basename会将 pathname或 string中的suffⅸ去掉。

- dirname基本语法
  功能:返回完整路径最后/的前面的部分,用于返回路径部分
  dirname 文件绝对路径 (功能描述:从给定的包含绝对路径的文件名中去除文件名(非目录的部分),然后返回剩下的路径(目录的部分)
  ![image-20220222151501955](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222151501955.png)

### 自定义函数

[ function ] funname[()]
{
	Action:
	[return int:]

}

调用直接写函数名: funname
应用实例
案例1:计算输入两个参数的和

![image-20220222152159489](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222152159489.png)

![image-20220222152205338](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222152205338.png)

# 综合案例

需求分析
1)每天凌晨2:10备份数据库 atguiguDB到/data/ backup/db
2)备份开始和备份结束能够给出相应的提示信息
3)备份后的文件要求以备份时间为文件名,并打包成 tar.gz的形式,比如
2018-03-12230201.tar.gz
4)在备份的同时,检査是否有10天前备份的数据库文件,如果有就将其删除。

![image-20220222160001398](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222160001398.png)

![image-20220222160319991](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222160319991.png)

![image-20220222160324564](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222160324564.png)

![image-20220222162119458](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222162119458.png)

![image-20220222162125833](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222162125833.png)

![image-20220222162339601](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222162339601.png)

![image-20220222162426597](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222162426597.png)![image-20220222162431910](https://zhangyuyetypora.oss-cn-guangzhou.aliyuncs.com/typora-user-images/image-20220222162431910.png)

```
#!/bin/bash

#数据库备份
#备份的路径
BACKUP=/home/mysql_data

#当前时间作为文件名
DATETIME=$(date +%Y_%m_%d_%H%M%S)


echo "开始备份"
echo "备份的路径是:$BACKUP/$DATETIME.tar.gz"


#主机
HOST=localhost

#用户名
DB_USER=root

#密码
DC_PWD=123456

#备份数据库名
DATABASE=atguiguDB
#如果备份的路径文件夹存在,就使用,否则就创建
[ ! -d "$BACKUP/$DATETIME" ] && mkdir -p "$BACKUP/$DATETIME"

#执行备份指令
mysqldump -u${DB_USER} -p${DC_PWD} --host=${HOST} $DATABASE | gzip > $BACKUP/$DATETIME/$DATETIME.sql.gz

#打包
cd $BACKUP

tar -zcvf $DATETIME.tar.gz $DATETIME

#删除临时目录
rm -rf $BACKUP/$DATETIME

#删除十天前的不备份文件
find $BACKUP -mtime +10 -name "*.tar.gz" -exec rm -rf {} \;
echo "备份文件成功"
```

