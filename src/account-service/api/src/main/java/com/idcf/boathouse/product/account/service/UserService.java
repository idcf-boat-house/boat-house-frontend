package com.idcf.boathouse.product.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idcf.boathouse.product.account.entity.User;
import com.idcf.boathouse.product.account.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
