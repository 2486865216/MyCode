#16-变量、流程控制与游标
SHOW GLOBAL VARIABLES;
SHOW SESSION VARIABLES;

SHOW VARIABLES;#默认查询会话


SHOW GLOBAL VARIABLES LIKE 'admin%';


SELECT @@global.max_connections;


SET @@global.max_connections = 120;

#变量的声明和复制
SET @a = 1;
SET @b := 2;
SELECT @a + @b;

SELECT COUNT(employee_id) INTO @count FROM employee;
SELECT @count;

CREATE PROCEDURE param()
BEGIN
    DECLARe myParam INT DEFAULT 100;
    DECLARe myParam2 INT;

    set myParam2 = 50;

    SELECT myParam;
    SELECT myParam2;
END;

CALL param();



#定义条件
DELIMITER //
CREATE PROCEDURE UpdateDataNoCondition()
BEGIN
#定义条件
#举例1：定义“Field_Not_Be_NULL”错误名与MySQL中违反非空约束的错误类型是“ERROR1048(23000)”对应。
#     DECLARE Field_Not_Be_NUll CONDITION FOR 1048;
#     DECLARE Field_Not_Be_NUll CONDITION FOR SQLSTATE '23000';
#定义处理
#     DECLARE EXIT HANDLER FOR SQLEXCEPTION SET @info = 'NO_SUCH_TABLE';

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

SELECT @x;
SELECT @info;



#流程控制
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

#CASE
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

#loop循环
#举例1：
#使用LOOP语句进行循环操作，id值小于10时将重复执行循环过程。
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

#while循环
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


#repeat循环
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


#游标
#声明游标
#打开游标
#使用游标
#关闭游标
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












