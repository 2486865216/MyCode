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

​	LOOP循环语句用来重复执行某些语句LOOP内的语句一直重复执行直到循环被退出（使用LEAVE子句），跳出循
环过程。

LOOP语句的基本格式如下：

```mysql
[loop_label:] LOOP
	循环执行的语句
END LOOP [loop_label]
```

其中，loop_label表示LOOP语句的标注名称，该参数可以省略。

举例1：

使用LOOP语句进行循环操作，id值小于10时将重复执行循环过程。

```mysql
DELIMITER //

CREATE PROCEDURE test_loop()
BEGIN
    DECLARE num INT DEFAULT 0;
    loop_lable:LOOP
        SET num = num + 1;
        IF num >= 10
            THEN LEAVE loop_lable;
        END IF;
    END LOOP loop_lable;
    SELECT num;
END //

DELIMITER ;

CAll test_loop();
```

### 3.4循环结构之WHILE

​	WHILE语句创建一个带条件判断的循环过程。WHILE在执行语句执行时，先对指定的表达式进行判断，如果为真，
就执行循环内的语句，否侧退出循环。

WHILE语句的基本格式如下：

```mysql
[while_labe1:] WHILE 循环条件 D0
	循环体
END WHILE [while_label];
```

​	while_.label为WHILE语句的标注名称；如果循环条件结果为真，WHILE语句内的语句或语句群被执行，直至条件为假，退出循环。

```mysql
DELIMITER //

CREATE PROCEDURE test_while()
BEGIN
    DECLARE num INT DEFAULT 0;
    WHILE num <10 DO
        SET num = num + 1;
    END WHILE;
    SELECT num;
END //

DELIMITER ;

CAll test_while();
```

### 3.5循环结构之REPEAT

​	REPEAT语句创建一个带条件判断的循环过程。与WHILE循环不同的是，REPEAT循环首先会执行一次循环，然后在UNTIL中进行表达式的判断，如果满足条件就退出，即END REPEAT;如果条件不满足，则会就继续执行循环，直到满足退出条件为止。

REPEAT语句的基本格式如下：

```mysql
[repeat label:] REPEAT
    循环体的语句
    UNTIL结束循环的条件表达式
END REPEAT [repeat label]
```

```mysql
DELIMITER //

CREATE PROCEDURE test_repeat()
BEGIN
    DECLARE num INT DEFAULT 0;
    REPEAT
        SET num = num + 1;
        UNTIL num >= 10
    END REPEAT;
    SELECT num;
END //

DELIMITER ;

CAll test_repeat();
```

对比三种循环结构：
1、这三种循环都可以省略名称，但如果循环中添加了循环控制语句(LEAVE或ITERATE)则必须添加名称
2、
LOOP：一般用于实现简单的死循环
WHILE:先判断后执行
REPEAT:先执行后判断，无条件至少执行一次

### 3.6跳转语句之LEAVE语句

LEAVE语句：可以用在**循环语句内**，或者**以BEGIN和END包裹起来的程序体内**，表示跳出循环或者跳出程序体的操作。如果你有面向过程的编程语言的使用经验，你可以把**LEAVE理解为break**。

```mysql
LEAVE 标记名

[while_labe1:] WHILE 循环条件 D0
	循环体
	LEAVE while_label;
END WHILE [while_label];
```

### 3.7跳转语句之ITERATE语句

​	ITERATE语句：只能用在循环语句(LOOP、REPEAT和WHILE语句)内，表示重新开始循环，将执行顺序转到语句段开头处。如果你有面向过程的编程语言的使用经验，你可以把**ITERATE理解为continue**,意思为“再次循环”。

```mysql
ITERATE label
```

label参数表示循环的标志。ITERATE语句必须跟在循环标志前面。

## 4.游标

### 4.1什么是游标(或光标)

​	虽然我们也可以通过筛选条件WHERE和HAVING,或者是限定返回记录的关键字LIMIT返回一条记录，但是，却无法在结果集中像指针一样，向前定位一条记录、向后定位一条记录，或者是**随意定位到某一条记录**，并对记录的数据进行处理。

​	这个时候，就可以用到游标。游标，提供了一种灵活的操作方式，让我们能够对结果集中的每一条记录进行定位，并对指向的记录中的数据进行操作的数据结构。**游标让SQL这种面向集合的语言有了面向过程开发的能力。**在SQL中，游标是一种临时的数据库对象，可以指向存储在数据库表中的数据行指针。这里游标**充当了指针的作用**，我们可以通过操作游标来对数据行进行操作。MySQL中游标可以在存储过程和函数中使用。

### 4.2使用游标步骤

游标必须在声明处理程序之前被声明，并目变量和条件还必须在声明游标或处理程序之前被声明。

如果我们想要使用游标，般需要经历四个步骤。不同的DBMS中，使用游标的语法可能略有不同。

**第一步，声明游标**

在MySQL中，使用DECLARE关键字来声明游标，其语法的基本形式如下：

```mysql
DECLARE cursor_name CURSOR FOR select_statement;
```

这个语法适用于MySQL,SQL Server,DB2和MariaDB。如果是用Oracle或者PostgreSQL,需要写成

```mysql
DECLARE cursor_name CURSOR IS select_statement;
```

要使用SELECT语句来获取数据结果集，而此时还没有开始遍历数据，这里**select_statement代表的是SELECT语句**，返回一个用于创建游标的结果集。

比如：

```mysql
DECLARE cur_emp CURSOR FOR
SELECT employee_id,salary FROM employees;
```

```mysql
DECLARE cursor_fruit CURSOR FOR
SELECT f_name,f_price FROM fruits
```

**第二步，打开游标**

打开游标的语法如下：

```mysql
OPEN cursor_name;
```

当我们定义好游标之后，如果想要使用游标，必须先打开游标。打开游标的时候SELECT语句的查询结果集就会送到游标工作区，为后面游标的**逐条读取**结果集中的记录做准备。

**第三步，使用游标（从游标中取得数据）**

语法如下：

```mysql
FETCH cursor_name INTO var_name [var_name]...
```

这句的作用是使用cursor_.name这个游标来读取当前行，并且将数据保存到var_name这个变量中，游标指针指到下一行。如果游标读取的数据行有多个列名，则在INTO关键字后面赋值给多个变量名即可。

注意：var_name必须在声明游标之前就定义好。

注意：游标的**查询结果集中的字段数，必须跟INTO后面的变量数一致**，否则，在存储过程执行的时候，MySQL会提错误。

**第四步，关闭游标**

```mysql
CLOSE cursor_name;
```

有OPEN就会有CLOSE,也就是打开和关闭游标。当我们使用完游标后需要关闭掉该游标。因为游标会占用系统资源，如果不及时关闭，**游标会一直保持到存储过程结束**，影响系统运行的效率。而关闭游标的操作，会释放游标占用的系统资源。关闭游标之后，我们就不能再检索查询结果中的数据行，如果需要检索只能再次打开游标。

### 4.3举例

```mysql
DELIMITER //

CREATE PROCEDURE test_cursor()
BEGIN
    #声明变量
    DECLARE sum_salary INT DEFAULT 0; #总工资
    DECLARE employee_salary INT DEFAULT 0; #每个员工的工资

    #声明游标
    DECLARE employee_cursor CURSOR FOR SELECT salary FROm employee ORDER BY salary DESC;

    #打开游标
    OPEN employee_cursor;

    #使用游标
    REPEAT
        FETCH employee_cursor INTO employee_salary;
        SET sum_salary = sum_salary + employee_salary;
        UNTIL sum_salary > 30000
    END REPEAT;

    #关闭游标
    CLOSE employee_cursor;

    SELECT sum_salary;
END //

DELIMITER ;

CALL test_cursor();
```

### 4.5小结

游标是MySQL的一个重要的功能，为**逐条读取**结果集中的数据，提供了完美的解决方案。跟在应用层面实现相同的功能相比，游标可以在存储程序中使用，效率高，程序也更加简洁。

但同时也会带来一些性能问题，比如在使用游标的过程中，会对数据行进行**加锁**，这样在业务并发量大的时候，不仅会影响业务之间的效率，还会**消耗系统资源**，造成内存不足，这是因为游标是在内存中进行的处理。

建议：养成用完之后就关闭的习惯，这样才能提高系统的整体效率。

## 补充：MySQL 8.0的新特性-全局变量的持久化

在MySQL数据库中，全局变量可以通过SET GLOBAL语句来设置。例如，设置服务器语句超时的限制，可以通过设置系统变量max_execution._time来实现：

```mysql
SET GLOBAL MAX_EXECUTION_TIME=2000;
```

使用SET GLOBAL语句设置的变量值只会**临时生效。数据库重启后**，服务器又会从MySQL配置文件中读取变量的默认值。

MySQL8.0版本新增了SET PERSIST命令。例如，设置服务器的最大连接数为1000：

```mysql
SET PERSIST max_connections = 1000;
```

MySQL会将该命令的配置保存到数据目录下的**mysqld-auto.cnf**文件中，下次启动时会读文件,用其中的配置来覆盖默认的配置文件。

