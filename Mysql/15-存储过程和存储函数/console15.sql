#存储过程和存储函数
#创建存储过程

#类型一：无参
#举例1：创建存储过程select_all__data(),查看employee表的所有数据
DELIMITER $
CREATE PROCEDURE select_all_data()
BEGIN
    SELECT * FROM employee;
END$
delimiter ;

#存储过程的调用
CALL select_all_data();

#举例2：创建存储过程avg_employee_salary(),返回所有员工的平均工资
DELIMITER //
CREATE PROCEDURE avg_employee_salary()
BEGIN
    SELECT AVG(salary) AS avg_salary FROM employee;
END //
DELIMITER ;

CALL avg_employee_salary();

#举例3：创建存储过程show_max_salary(),用来查看"employee"表的最高薪资值。
DELIMITER //
CREATE PROCEDURE show_max_salary()
BEGIN
    SELECT MAX(salary) AS max_salary FROM employee;
END //
DELIMITER ;

CALL show_max_salary();

#类型二：OUT
#举例4：创建存储过程show_min_salary(),查看"employee"表的最低薪资值。并将最低薪资
#通过OUT参数"ms"输出

DESC employee;

DELIMITER //

CREATE PROCEDURE show_min_salary(OUT ms DOUBLE)
BEGIN
    SELECT MIN(salary) INTO ms FROM employee;
END //

DELIMITER ;

#调用
CALL show_min_salary(@ms);

#查看变量值
SELECT @ms;

#类型三: IN
#举例5：创建存储过程show_someone_salary(),查看“employee”表的某个员工的薪资，并用IN参数employee names输入员工姓名。
DELIMITER //

CREATE PROCEDURE show_someone_salary(IN employeeNames varchar(20))
BEGIN
    SELECT salary FROM employee
        WHERE employee_name = employeeNames;
END //

DELIMITER ;

#调用一
CALl show_someone_salary('tom');
#调用二
SET @name := 'jerry';
CALL show_someone_salary(@name);

#类型四: IN OUT
#举例6：创建存储过程show_someone_salary2(),查看"employee"表的某个员工的薪资，
#并用IN参数emp name输入员工姓名，用OUT中参数emp salary输出员工薪资
DELIMITER //

Create PROCEDURE show_someone_salary2(IN name varchar(10), OUT money INT)
BEGIN
    SELECT salary INTO money
    FROM employee
        WHERE employee_name = name;
END //

DELIMITER ;

#调用
SET @name2 := 'shy';
CALL show_someone_salary2(@name2, @money);
SELECT @money;

#类型五：INOUT
#举例7：创建存储过程show_mgr_name(),查询某个员工领导的姓名，并用INOUT参数“empname"输入员工姓名，输出领导的姓名。
DELIMITER //

CREATE PROCEDURE show_mgr_name(INOUT name varchar(10))
BEGIN
    SELECT employee_boss INTO name
    FROM employee
        WHERE employee_boss = name;
END //

DELIMITER ;

#调用
SET @nameBoss := 'spring';
CALL show_mgr_name(@nameBoss);
SELECT @nameBoss;

#存储函数
#举例1：创建存储函数，名称为email_by_name(),参数定义为空，该函数查询tom的email,并返回，数据类型为字符串型。

DELIMITER //

CREATE FUNCTION email_by_name()
RETURNS VARCHAR(10)
    DETERMINISTIC
    CONTAINS SQL
    READS SQL DATA
BEGIN
    RETURN (SELECT email FROM employee WHERE employee_name='shy');
END //

DELIMITER ;

#调用
SELECT email_by_name();

#举例2：创建存储函数，名称为email_by_id(),参数传入employee,该函数查询employee的email,并返回，数据类型为字符串型。
#创建函数前执行此语句，保证函数的创建会成功
SET GLOBAL log_bin_trust_function_creators = 1;
DELIMITER //

CREATE FUNCTION email_by_id(id INT)
RETURNS VARCHAR(10)
    DETERMINISTIC
    CONTAINS SQL
    READS SQL DATA
BEGIN
    RETURN (SELECT email FROM employee WHERE employee_id=id);
END //

DELIMITER ;

#调用
SELECT email_by_id(3);


SHOW CREATE PROCEDURE show_max_salary;
SHOW CREATE FUNCTION email_by_id;

SHOW PROCEDURE STATUS LIKE 'show_max_salary';


SELECT * FROM information_schema.Routines
WHERE ROUTINE_NAME='show_max_salary';


#修改




