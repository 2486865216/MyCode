package com.qiqi.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    public SqlSessionFactory getsqlsessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return  new SqlSessionFactoryBuilder().build(inputStream);
    }
    /*
     * 两级缓存
     * 一级缓存（本地缓存） Session级别的缓存。一级缓存时一直开启的
     *       与数据库同一次会话期间查询到的数据会放在本地缓存中
     *       以后如果需要相同的数据，直接从缓存中拿，没必要再去查数据库
     *
     *       一级缓存失效
     *              1，sqlSession不同
     *              2，sqlSession相同，查询条件不同（单腔一级缓存中还没有这个数据）
     *              3，sqlSession相同，两次查询之间执行了增删改操作（这次增删改可能对数据库数据有影响）
     *              4，sqlSession相同，手动清除了缓存
     *
     * 二级缓存（全局缓存）基于namespace级别的缓存，一个namespace对应一个二级缓存
     *      工作机制
     *          1，一个会话，查询一条数据，这个数据会被放在当前会话的一级缓存中
     *          2，如果会话关闭，一级缓存的数据会被保存到二级缓存中：新的会话信息查询，就可以参照二级缓存
     *          3.不同namespace查出的数据会放在自己对应的缓存中（map）
     *              数据会被放在当前会话的一级缓存中
     *              只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中
     *      使用步骤：
     *          1，开启全局二级缓存配置
     *          2,去mapper。xml配置二级缓存
     *          3,POJO需要实现序列化接口
     *
     *和缓存有关的设置/属性
     *      1，cacheEnabled=true:false:关闭缓存（二级缓存关闭，一级缓存可用）
     *      2，每个select标签都有useCache="true":false:不使用缓存（一级缓存使用，二级缓存不使用）
     *      3，每个增删改标签的：flushCache="true":
     *          增删改执行完成后就会清除缓存
     *          flushCache="true":一级缓存清空,二级缓存清空
     *         查询标签的：flushCache="false":
     *          flushCache="true":查询执行完成后就会清除缓存,一级缓存清空,二级缓存清空
     *      4,session.clearCache();只是清除当前session的一级缓存
     *      5.localCacheScope：本地缓存作用域，（一级缓存SESSION）:当前会话的所有数据保存在会话缓存中
     *                      STATEMENT：可以禁用一级缓存
     * */
    @Test
    public void testIf() throws IOException {
        SqlSessionFactory sqlSessionFactory = getsqlsessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getMapper(1);
            System.out.println(employee);

            //手动清除了缓存
            session.clearCache();

            Employee employee2 = mapper.getMapper(1);
            System.out.println(employee2);
            System.out.println(employee==employee2);
        }finally {
            session.close();
        }
    }
    @Test
    public void testSecondlevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getsqlsessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            //1
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);
            //2.从二级缓存中获取到的数据
            Employee employee1 = mapper.getMapper(1);
            System.out.println(employee1);
            session.close();

            Employee employee2 = mapper2.getMapper(1);
            System.out.println(employee2);
            session2.close();
        }finally {
        }
    }
}
