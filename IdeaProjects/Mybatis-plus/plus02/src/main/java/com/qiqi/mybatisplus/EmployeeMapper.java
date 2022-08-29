package com.qiqi.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 基于Mybatis:在Maooer接口中编写CRUO,提供相关的SQL映射文件以及方法对应的SQL语句
 * 基于MP:让XXXMapper接口继承BaseMapper接口即可
 *        BaseMapper<T>:泛型指定的就是当前Maooer接口所操作的实体类类型
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
}
