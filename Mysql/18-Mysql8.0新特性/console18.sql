create database dbtest18;

use dbtest18;

CREATE TABLE sales(
id INT PRIMARY KEY AUTO_INCREMENT,
city VARCHAR(15),
county VARCHAR(15),
sales_value DECIMAL
);

INSERT INTO sales(city,county,sales_value)
VALUES
('北京','海淀',10.00),
('北京','朝阳',20.00),
('上海','黄埔',30.00),
('上海','长宁',10.00);

#窗口函数
create temporary table a    -- 创建临时表
select SUM(sales_value) as sales_value from sales; -- 总计金额
select * from a;

drop table b;
create temporary table b
select city,SUM(sales_value) as sales_value from sales -- 计算城市销售合计
group by city;
select * from b;

SELECT s.city AS 城市,s.county AS 区,s.sales_value AS 区销售额,
b.sales_value AS 市销售额,s.sales_value/b.sales_value AS 市比率,
a.sales_value AS 总销售额,s.sales_value/a.sales_value AS 总比率
FROM sales s
JOIN b ON (s.city=b.city) -- 连接市统计结果临时表
JOIN a 						-- 连接总计金额临时表
ORDER BY s.city,s.county;


SELECT city AS 城市,county AS 区,sales_value AS 区销售额,
SUM(sales_value) OVER(PARTITION BY city) AS 市销售额, -- 计算市销售额
sales_value/SUM(sales_value) OVER(PARTITION BY city) AS 市比率,
SUM(sales_value) OVER() AS 总销售额, -- 计算总销售额
sales_value/SUM(sales_value) OVER() AS 总比率
FROM sales
ORDER BY city,county;


#窗口函数

CREATE TABLE goods(
id INT PRIMARY KEY AUTO_INCREMENT,
category_id INT,
category VARCHAR(15),
NAME VARCHAR(30),
price DECIMAL(10,2),
stock INT,
upper_time DATETIME
);


INSERT INTO goods(category_id,category,NAME,price,stock,upper_time)
VALUES
(1,'女装/女士精品','T恤','39.90',1000,'2020-11-10 00:00:00'),
(1,'女装/女士精品','连衣裙','79.90',2500,'2020-11-10 00:00:00'),
(1,'女装/女士精品','卫衣','89.90',1500,'2020-11-10 00:00:00'),
(1,'女装/女士精品','牛仔裤','89.90',3500,'2020-11-10 00:00:00'),
(1,'女装/女士精品','百褶裙','29.90',500,'2020-11-10 00:00:00'),
(1,'女装/女士精品','呢绒外套','399.90',1200,'2020-11-10 00:00:00'),
(2,'户外运动','自行车','399.90',1000,'2020-11-10 00:00:00'),
(2,'户外运动','山地自行车','1399.90',2500,'2020-11-10 00:00:00'),
(2,'户外运动','登山杖','59.90',1500,'2020-11-10 00:00:00'),
(2,'户外运动','骑行装备','399.90',3500,'2020-11-10 00:00:00'),
(2,'户外运动','运动外套','799.90',500,'2020-11-10 00:00:00'),
(2,'户外运动','滑板','499.90',1208,'2020-11-10 00:00:00');

select * from goods;

#ROW_NUMBER()
#举例：查询goods数据表中每个商品分类下价格降序排列的各个商品信息。
SELECT ROW_NUMBER() OVER (PARTITION BY category_id ORDER BY price DESC) AS row_num,
id,category_id,category,NAME,price,stock,upper_time
FROM goods;

#举例：查询goods数据表中每个商品分类下价格最高的3种商品信息。
SELECT *
FROM(
SELECT ROW_NUMBER() OVER (PARTITION BY category_id ORDER BY price DESC) AS row_num,
id,category_id,category,NAME,price,stock
FROM goods) t
WHERE row_num <= 3;

#rank()
SELECT RANK() OVER (PARTITION BY category_id ORDER BY price DESC) AS row_num,
id,category_id,category,NAME,price,stock
FROM goods;


#RERCENT_RANK()
SELECT RANK() OVER (PARTITION BY category_id ORDER BY price DESC) AS r,
PERCENT_RANK() OVER (PARTITION BY category_id ORDER BY price DESC) AS pr,
id,category_id,category,NAME,price,stock
FROM goods
WHERE category_id = 1;

SELECT RANK() OVER w AS r,
PERCENT_RANK() OVER w AS pr,
id,category_id,category,NAME,price,stock
FROM goods
WHERE category_id = 1 WINDOW w AS (PARTITION BY category_id ORDER BY price DESC);

/**
  CUME_DIST()函数主要用于查询小于或等于某个值的比例。
举例：查询goods数据表中小于或等于当前价格的比例。
 */

SELECT CUME_DIST()
OVER (PARTITION BY category_id ORDER BY price ASC)AS cd,
id,category,NAME,price
FROM goods;

#1.LAG(expr,n)函数

SELECT id, category, NAME, price, pre_price,price - pre_price AS diff_price
FROM(
SELECT
id,category,NAME,price, LAG(price,1) OVER w AS pre_price
FROM goods
WINDOW w AS (PARTITION BY category_id ORDER BY price)) t;

#**2.LEAD(eXpr,n)函数**
SELECT id,category,NAME,behind_price,price,behind_price- price AS diff_price
FROM(
SELECT id,category,NAME,price,LEAD(price,1)OVER w AS behind_price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price)) t;

#first_value(expr)
SELECT id,category,NAME,price,stock,FIRST_VALUE(price) OVER w AS last_price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price);

SELECT id,category,NAME,price,stock,LAST_VALUE(price) OVER w AS last_price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price);

SELECT id,category,NAME,price,
       NTH_VALUE(price, 2) OVER w AS last_price,
       NTH_VALUE(price, 3) OVER w AS last_price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price);


SELECT NTILE(3)OVER w AS nt,id,category,NAME,price
FROM goods WINDOW w AS (PARTITION BY category_id ORDER BY price);

#实用表表达式
#举例：查询员工所在的部门的详细信息。

#子查询实现
SELECT * FROM department
WHERE department_id IN(
SELECT DISTINCT department_id
FROM employee
);

WITH cte_employee
AS ( SELECT DISTINCT department_id FROM employee)

SELECT *
FROM department d JOIN cte_employee e
ON d.department_id = e.department_id;







