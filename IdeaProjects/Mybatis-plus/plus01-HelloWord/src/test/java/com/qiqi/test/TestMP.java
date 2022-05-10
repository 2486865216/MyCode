package com.qiqi.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiqi.mybatisplus.Employee;
import com.qiqi.mybatisplus.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestMP {
    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

    private EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);

    /**
     * 通用插入
     * @throws SQLException
     */
    @Test
    public void testComoninsert(){
        Employee employee = new Employee(null,"MP","MP@qq",1,25);
        int insert = employeeMapper.insert(employee);
        System.out.println("insert:"+insert);
        System.out.println("主键值:"+employee.getId());
    }

    /**
     * 更新操作
     */
    @Test
    public void testComonUpdate(){
        Employee employee = new Employee(1,"hello","hello@qq",1,25);
        int i = employeeMapper.updateById(employee);
        System.out.println(i);
    }

    /**
     * 通用查询
     */
    @Test
    public void testCommonSelect(){
        //通过id查询
        //Employee employee = employeeMapper.selectById(1);
        //System.out.println(employee);

        //通过多个列查
        //QueryWrapper<Employee> wrapper = new QueryWrapper<Employee>();
        //wrapper.clear();
        //wrapper.eq("id","1");
        //wrapper.eq("lastname","hello");
        //Employee employee = employeeMapper.selectOne(wrapper);
        //System.out.println(employee);

        //通过多个id查询
        //List<Integer> idList = new ArrayList<Integer>();
        //idList.add(1);
        //idList.add(2);
        //idList.add(3);
        //idList.add(4);
        //List<Employee> employees = employeeMapper.selectBatchIds(idList);
        //for (Employee employee:employees){
        //    System.out.println(employee);
        //}

        //通过Map封装条件查询
        //Map<String, Object> columnMap = new HashMap<String, Object>();
        //columnMap.put("lastname","hello");
        //columnMap.put("gender",1);
        //List<Employee> employees = employeeMapper.selectByMap(columnMap);
        //for (Employee employee:employees){
        //    System.out.println(employee);
        //}

        //分页查询
        //Page<Employee> employeePage =
        //        employeeMapper.selectPage(new Page<Employee>(3, 2), null);
        //List<Employee> employees= employeePage.getRecords();
        //for (Employee e:employees){
        //    System.out.println(e);
        //}
    }

    //通用删除
    @Test
    public void testCommondelete(){
        //通过id
        int i = employeeMapper.deleteById(35);
        System.out.println(i);

        //根据条件删除
        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("lastname","hello");
        //map.put("gender",1);
        //int i = employeeMapper.deleteByMap(map);
        //System.out.println(i);

        //批量删除
        //List<Integer> idList = new ArrayList<Integer>();
        //idList.add(36);
        //int i = employeeMapper.deleteBatchIds(idList);
        //System.out.println(i);
    }



    @Test
    public void testDatasource() throws SQLException {
        DataSource ds = context.getBean("dataSource",DataSource.class);
        System.out.println(ds);
        Connection connection = ds.getConnection();
        System.out.println(connection);
    }
}
