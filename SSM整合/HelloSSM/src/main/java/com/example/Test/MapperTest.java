package com.example.Test;

import com.example.Bean.Department;
import com.example.Bean.Employee;
import com.example.Dao.DepartmentMapper;
import com.example.Dao.EmployeeMapper;
import com.example.Service.EmployeeService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/3/18  16:23
 * 测试数据
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1、导入SpringTest模块
 * 2、@ContextConfiguration:指定Spring配置文件的位置
 * 3、直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession session;
    @Test
    public void test() {
        //1.插入几个部门
        //departmentMapper.insertSelective(new Department(null, "开发部"));
        //departmentMapper.insertSelective(new Department(null, "测试部"));
        //departmentMapper.insertSelective(new Department(null, "运维部"));

        //2.插入员工
        //EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        //for (int i = 0; i < 1000; i++) {
        //    String substring = UUID.randomUUID().toString().substring(0, 5);
        //    mapper.insertSelective(new Employee(null, substring, "1", "18577787964@sina.cn", (i + 1) % 3 + 1));
        //}
        //System.out.println("批量插入完成");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }
}
