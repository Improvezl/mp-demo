package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(4L);
        user.setBalance(2000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    @Test
    void testQueryWrapper() {
        //1.构建查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);
        //2.查询
        List<User> users = userMapper.selectList(wrapper);


        users.forEach(System.out::println);
    }

    @Test
    void testUpdateQueryWrapper() {
        //1.要更新的数据
        User user = new User();
        user.setBalance(1000);
        //2.更新的条件

        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", "Jack");

        userMapper.update(user, wrapper);
    }
    @Test
    void testUpdateWrapper() {
        List<Long> ids  = List.of(1L, 2L, 4L);

        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200")
                        .in("id",ids);


        userMapper.update(null, wrapper);
    }

    @Test
    void testCustomSqlWrapper() {
        //1.更新条件
        List<Long> ids  = List.of(1L, 2L, 4L);
        int amount = 200;
        //2.定义条件
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>().in("id",ids);
        //3.调用自定义SQL方法

        userMapper.updateBalanceByIds(wrapper, amount);
    }

}