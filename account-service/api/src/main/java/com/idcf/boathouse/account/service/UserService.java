package com.idcf.boathouse.account.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idcf.boathouse.account.entity.User;
import com.idcf.boathouse.account.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Override
    public User getOne(Wrapper<User> queryWrapper) {
        User user = new User();
        user.setPassword("123");
        user.setAccount("123");
        return user;
    }
}
