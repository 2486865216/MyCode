package com.example.dockerboot.Mapper;

import com.example.dockerboot.Bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * author ye
 * createDate 2022/3/10  13:25
 */
@Mapper
public interface UserMapper{
    public User getUserById(Integer id);
    public int insertUser(User user);
    public int selectLastUser();
}
