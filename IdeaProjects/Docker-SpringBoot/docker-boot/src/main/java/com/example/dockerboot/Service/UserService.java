package com.example.dockerboot.Service;

import com.example.dockerboot.Bean.User;
import com.example.dockerboot.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * author ye
 * createDate 2022/3/10  13:36
 */
@Service
public class UserService {
    private static final String CACHE_KEY_USER = "user:";
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public User getUser(Integer id){
        return userMapper.getUserById(id);
    }
    public void addUser(User user){
        int i = userMapper.insertUser(user);
        System.out.println(i);
        if (i > 0){
            int userID = userMapper.selectLastUser();
            user = userMapper.getUserById(userID);
            String key = CACHE_KEY_USER + user.getId();
            System.out.println(key);
            redisTemplate.opsForValue().set(key, user);
        }
    }
    public User findUserById(Integer id){
        User user = null;
        String key = CACHE_KEY_USER + id;
        //1先从redis.里面查询，如果有直接返回结果，如果没有再去查询nysqL
        user = (User) redisTemplate.opsForValue().get(key);

        if (user == null){
            //2 redis里面无，继续查询mysql
            user = userMapper.getUserById(id);
            if (user == null) {
                //3.1 redis.+mysqL都无数据，记录下导致穿透的这个key回写redis
                return user;
            }else {
                //3.2 mysal有，需要将数据写回redis,保证下一次的缓存命中率
                redisTemplate.opsForValue().set(key, user);
            }
        }
        return user;
    }
}
