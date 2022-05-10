# 16-变量、流程控制与游标

## 1.变量

在MySQL数据库的存储过和函数中，可以使用变量来存储查询或计算的中间结果数据，或者输出最终的结果数据。

在MySQL数据库中，变量分为系统变量以及用户自定义变量。

### 1.1系统变量

变量由系统定义，不是用户定义，属于服务器层面。启动MySQL服务，生成MySQL服务实例期间，MySQL将为MySQL服务器内存中的系统变量赋值，这些系统变量定义了当前MySQL服务实例的属性、特征。这些系统变量的值要么是编译MySQL时参数的默认值，要么是配置文件（例如my.ini等）中的参数值。大家可以通过网址https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html查看MySQL文档的系统变量。

#### 1.1.1系统变量分类

系统变量分为**全局系统变量**（需要添加global关键字）以及**会话系统变量**（需要添加`session`关键字），有时也把全局系统变量简称为全局变量，有时也把会话系统变量称为local变量。**如果不写，默认会话级别**。静态变量（在MySQL服务实例运行期间它们的值不能使用set动态修改）属于特殊的全局系统变量。

每一个MySQL客户机成功连接MySQL服务器后，都会产生与之对应的会话。会话期间，MySQL服务实例会在MySQL服务器内存中生成与该会话对应的会话系统变量，这些会话系统变量的初始值是全局系统变量值的复制。如下图：

![image-20220225132556645](C:\Users\YQ\AppData\Roaming\Typora\typora-user-images\image-20220225132556645.png)

- 全局系统变量针对于所有会话（连接）有效，但不能跨重启
- 会话系统变量仅针对于当前会话（连接）有效。会话期间，当前会话对某个会话系统变量值的修改，不会影
  响其他会话同一个会话系统变量的值。
- 会话1对某个全局系统变量值的修改会导致会话2中同一个全局系统变量值的修改。

在MySQL中有些系统变量只能是全局的，例如max_connections用于限制服务器的最大连接数；有些系统变量作用域既可以是全局又可以是会话，例如character_set_client用于设置客户端的字符集；有些系统变量的作用域只能是当前会话，例如pseudo._thread_id用于标记当前会话的MySQL连接ID。

#### 1.1.2查看系统变量

- 查看所有或部分系统变量

```mysql
#查看所有全局变量
SHOW GLOBAL VARIABLES;
#查看所有会话变量
SHOW SESSION VARIABLES;
或
SHOW VARIABLES;
```

```mysql
#查看满足条件的部分系统变量。
SHOW GLOBAL VARIABLES LIKE'%标识符%';
#查看满足条件的部分会话变量
SHOW SESSION VARIABLES LIKE'%标识符%';


SHOW GLOBAL VARIABLES LIKE admin_%';
```

- 查看指定系统变量

  作为MySQL编码规范，MySQL中的系统变量以两个“@”开头，其中“@@global”仅用于标记全局系统变量，“@@session”仅用于标记会话系统变量。“@@”首先标记会话系统变量，如果会话系统变量不存在，则标记全局系统变量。

  ```mysql
  #查看指定的系统变量的值
  SELECT @@global.变量名;
  #查看指定的会话变量的值
  SELECT @@session.变量名;
  #或者
  SELECT @@变量名;
  ```

- 修改系统变量的值
  有些时候，数据库管理员需要修改系统变量的默认值，以便修改当前会话或者MySQL服务实例的属性、特征。具体方法：
  方式1：修改MySQL配置文件，继而修改MySQL系统变量的值（该方法需要重启MySQL服务）
  方式2：在MySQL服务运行期间，使用“set”命令重新设置系统变量的值

  ```mysql
  #为某个系统变量赋值
  #方式1：
  SET @@global.变量名=变量值;
  #方式2：
  SET GLOBAL 变量名=变量值;
  
  
  #为某个会话变量赋值
  #方式1：
  SET @@session.变量名=变量值；
  #方式2：
  SET SESSION 变量名=变量值；
  ```

  

### 1.2用户变量

#### 1.2.1用户变量分类

用户变量是用户自己定义的，作为MySQL编码规范，MySQL中的用户变量以一个“@”开头。根据作用范围不同，又分为**会话用户变量**和**局部变量**。

- 会话用户变量：作用域和会话变量一样，只对当前连接会话有效。
- 局部变量：只在BEGIN和END语句块中有效。局部变量只能在**存储过程和函数**中使用。

#### 1.2.2会话用户变量

- 变量的定义

  ```mysql
  #方式1：“=”或":=”
  SET @用户变量 = 值;
  SET @用户变量 :=值;
  
  #方式2：":=”或INTO关键字
  SELECT @用户变量 := 表达式[FROM等子句];
  SELECT 表达式 INTO @用户变量 [FROM等子句];
  ```

  

#### 1.2.3局部变量

定义：可以使用DECLARE语句定义一个局部变量

作用域：仅仅在定义它的BEGIN…END中有效

位置：只能放在BEGIN..END中，而且只能放在第一句

```mysql
BEGIN
    #声明局部变量
    DECLARE 变量名1 变量数据类型[DEFAULT变量默认值];
    DECLARE 变量名2,变量名3,... 变量数据类型[DEFAULT变量默认值];

    #为局部变量赋值
    SET 变量名1 = 值;
    SELECT 值 INTO 变量名2 [FROM子句];

    #查看局部变量的值
    SELECT 变量1,变量2,变量3;
END
```

1.定义变量

```mysql
DECLARE 变量名	类型 [default值]; #如果没有DEFAULT子句，初始值为NULL

DECLARE myparam INT DEFAULT 100;
```

2.变量赋值

方式1：一般用于赋简单的值

```mysql
SET 变量名 = 值;
SET 变量名 := 值;
```

方式2：一般用于赋表中的字段值

```mysql
SELECT 字段名或表达式 INTO 变量名 FROM 表;
```



#### 1.2.4对比会话用户变量与局部变量

|              | 作用域              | 定义位置            | 语法                      |
| ------------ | ------------------- | ------------------- | ------------------------- |
| 会话用户变量 | 当前会话            | 会话的任何竿方      | 加@符号，不用指定类型     |
| 局部变量     | 定义它的BEGIN END中 | BEGIN END的第一句话 | 一般不用加@，需要指定类型 |

## 2.定义条件与处理程序

​	**定义条件**是事先定义程序执行过程中可能遇到的问题，**处理程序**定义了在遇到问题时应当采取的处理方式，并且保证存储过程或函数在遇到警告或错误时能继续执行。这样可以增强存储程序处理问题的能力，避免程序异常停止运行。
​	说明：定义条件和处理程序在存储过程、存储函数中都是支持的。

### 2.1案例分析

案例分析：创建一个名称为“UpdateDataNoCondition”的存储过程。代码如下：

```mysql
DELIMITER //
CREATE PROCEDURE UpdateDataNoCondition()
BEGIN
    SET @x = 1;
    UPDATE employee SET email = NULL WHERE employee_name = 'tom';
    SET @x = 2;
    UPDATE employee SET email = 'aabbel' WHERE employee_name = 'shy'
    SET @x = 3;
END //
DELIMITER ;

CALL UpdateDateNoCondition();

[2022-02-25 14:12:10] 在 34 ms 内完成
test15> CALL UpdateDataNoCondition()
[2022-02-25 14:12:13] [23000][1048] Column 'email' cannot be null


SELECT @x; #x=1;
```

在存储过程中未定义条件和处理程序，且当存储过程中执行的SQL语句报错时，MySQL数据库会抛出错误，并退出当前SQL逻辑，不再向下继续执行。

### 2.2定义条件

定义条件就是给MySQL中的错误码命名，这有助于存的程序代码更清晰。它将一个**错误名字**和**指定的错误条件**关联起来。这个名字可以随后被用在定义处理程序的DECLARE HANDLER语句中。

定义条件使用DECLARE语句，语法格式如下：

```mysql
DECLARE 错误名称 CONDITION FOR 错误码（或错误条件）
```

错误码的说明：

- MySQL_error_code和sqlstate_value都可以表示MySQL的错误。
  - MySQL_error__code是数值类型错误代码。
  - sqlstate_value,是长度为5的字符串类型错误代码。
    - _例如，在ERROR 1418 (HY000)中，1418是MySQL_error__code,'HY000'是sqlstate_value。
    - _例如，在ERROR 1142 (42000)中，1142是MySQL_error__code,'42000'是sqlstate_value。

举例1：定义“Field_Not_Be_NULL”错误名与MySQL中违反非空约束的错误类型是“ERROR 1048 (23000)”对应。

```mysql
#MySQL_error__code
DECLARE Field_Not_Be_NUll CONDITION FOR 1048;

#sqlstate_value
DECLARE Field_Not_Be_NUll CONDITION FOR SQLSTATE '23000';
```

### 2.3定义处理程序

可以为SQL执行过程中发生的某种类型的错误定义特殊的处理程序。定义处理程序时，使用DECLARE语句的语法如下：

```mysql
DECLARE 处理方式 HANDLER FOR 错误类型 处理语句
```

- 处理方式：处理方式有3个取值：CONTINUE、EXIT、UNDO。

  - CONTINUE:表示遇到错误不处理，继续执行。
  - EXIT:表示遇到错误马上退出。
  - UNDO：表示遇到错误后撤回之前的操作。MySQL中暂时不支持这样的操作。

- 错误类型（即条件）可以有如下取值：

  - SQLSTATE '字符串错误码'：表示长度为5的sqlstate_value类型的错误代码；

  - ```mysql
    DECLARE CONTINUE HANDLER FOR SQLSTATE '23000' SET @info = 'NO_SUCH_TABLE';
    ```

  - MySQL_error-code:匹配数值类型错误代码；

  - ```mysql
    DECLARE CONTINUE HANDLER FOR 1048 SET @info = 'NO_SUCH_TABLE';
    ```

  - 错误名称：表示DECLARE .... CONDITION定义的错误条件名称。

  - SQLWARNING:匹配所有以01开头的SQLSTATE错误代码：

  - ```mysql
    DECLARE CONTINUE HANDLER FOR SQLWARNING SET @info = 'NO_SUCH_TABLE';
    ```

  - NOT FOUND:匹配所有以02开头的SQLSTATE错误代码；

  - ```mysql
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET @info = 'NO_SUCH_TABLE';
    ```

  - SQLEXCEPTION:匹配所有没有被SQLWARNING或NOT FOUND捕获的SQLSTATE错误代码；

  - ```mysql
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET @info = 'NO_SUCH_TABLE';
    ```

  - 先定义条件，在调用

  - ```mysql
    DECLARE no_such_table CONDITION FOR 1048;
    DECLARE CONTINUE HANDLER FOR no_such_table SET  @info = 'NO_SUCH_TABLE';
    ```

- 处理语句：如果出现上述条件之一，则采用对应的处理方式，并执行指定的处理语句。语句可以是像“STET 变量=值”这样的简单语句，也可以是使用BEGIN···END编写的复合语句。

### 2.4案例解决

```mysql
#定义条件
DELIMITER //
CREATE PROCEDURE UpdateDataNoCondition()
BEGIN
#定义条件
#举例1：定义“Field_Not_Be_NULL”错误名与MySQL中违反非空约束的错误类型是“ERROR1048(23000)”对应。
#   DECLARE Field_Not_Be_NUll CONDITION FOR 1048;
#   DECLARE Field_Not_Be_NUll CONDITION FOR SQLSTATE '23000';
#定义处理
#   DECLARE EXIT HANDLER FOR SQLEXCEPTION SET @info = 'NO_SUCH_TABLE';

#先定义，在调用
    DECLARE no_such_table CONDITION FOR 1048;
    DECLARE CONTINUE HANDLER FOR no_such_table SET  @info = 'NO_SUCH_TABLE';

    SET @x = 1;
    UPDATE employee SET email = NULL WHERE employee_name = 'tom';
    SET @x = 2;
    UPDATE employee SET email = 'aabbel' WHERE employee_name = 'shy';
    SET @x = 3;
END //
DELIMITER ;

CALL UpdateDataNoCondition();

SELECT @x; #x = 3;
SELECT @info; #info = 'NO_SUCH_TABLE';

mysql> select @x;
+------+
| @x   |
+------+
|    3 |
+------+
1 row in set (0.00 sec)

mysql> select @info;
+---------------+
| @info         |
+---------------+
| NO_SUCH_TABLE |
+---------------+
1 row in set (0.00 sec)

```



## 3.流程控制

​	解决复杂问题不可能通过一个SQL语句完成，我们需要执行多个SQL操作。流程控制语句的作用就是控制存储过程中SQL语句的执行顺序，是我们完成复杂操作必不可少的一部分。只要是执行的程序，流程就分为三大类：

- 顺序结构：程序从上往下依次执行
- 分支结构：程序按条件进行选择执行，从两条或多条路径中选择一条执行
- 循环结构：程序满足一定条件下，重复执行一组语句

针对于MySQL的流程控制语句主要有3类。注意：只能用于存储程序。

- 条件判断语句：IF语句和CASE语句
- 循环语句：LOOP、WHILE和REPEAT语句
- 跳转语句：ITERATE和LEAVE语句

### 3.1分支结构之IF

- IF语句的语法结构是：

  ```mysql
  IF 表达式1 THEN 操作1
      [ELSEIF 表达式2 THEN 操作2]…
      [ELSE 操作N ]
  END IF;
  ```

  根据表达式的结果为TRUE或FALSE:执行相应的语句。这里“[]”中的内容是可选的。
  特点：①不同的表达式对应不同的操作②使用在begin end中

  ```mysql
  DELIMITER //
  
  CREATE PROCEDURE test_if()
  BEGIN
      DECLARE flag varchar(10) DEFAULT 'hello';
      IF flag = 'hello'
          THEN SELECT 'flag is hello';
      ELSEIF flag = 'word'
          THEN
          SELECT 'flag is word';
      ELSE
          SELECT 'I do not know';
      END IF;
  END //
  
  DELIMITER ;
  
  CALL test_if();
  ```

### 3.2分支结构之CASE

CASE语句的语法结构1：

```mysql
#情况一：类似于switch
CASE 表达式
WHEN 值1 THEN 结果1或语句1（如果是语句，需要加分号）
WHEN 值2 THEN 结果2或语句2（如果是语句，需要加分号）
ELSE 结果n或语句n(如果是语句，需要加分号)
END [case](如果是放在 begin end 中需要加上 case,如果放在 select 后面不需要)
```

CASE语句的语法结构2：

```mysql
#情况二：类似于多重if
CASE
WHEN 条件1 THEN 结果1或语句1（如果是语句，需要加分号）
WHEN 条件2 THEN 结果2或语句2（如果是语句，需要加分号）
ELSE 结果n或语句n(如果是语句，需要加分号)
END [case](如果是放在 begin end中需要加上 case,如果放在 select 后面不需要)
```

举例1：
使用CASE流程控制语句的第1种格式，判断val值等于1、等于2，或者两者都不等。

```mysql
DELIMITER //

CREATE PROCEDURE test_case()
BEGIN
    DECLARE var INT DEFAULT 2;
    CASE var
        WHEN 1
            THEN SELECT 'var is 1';
        WHEN 2
            THEN SELECT 'var is 2';
    END CASE;

    CASE
        WHEN var < 3
            THEN SELECT 'var is < 3';
        WHEN var > 3
            THEN SELECT 'var is > 3';
    END CASE;
END //

DELIMITER ;

CALL test_case();
```



### 3.3循环结构之LOOP

### 3.4循环结构之WHILE

### 3.5循环结构之REPEAT

### 3.6跳转语句之LEAVE语句

### 3.7跳转语句之ITERATE语句