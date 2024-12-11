package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {


    void updateBalanceByIds(@Param("ew")  UpdateWrapper<User> wrapper, @Param("amount") int amount);
}
