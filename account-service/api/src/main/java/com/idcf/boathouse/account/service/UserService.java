package com.idcf.boathouse.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idcf.boathouse.account.entity.User;
import com.idcf.boathouse.account.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
