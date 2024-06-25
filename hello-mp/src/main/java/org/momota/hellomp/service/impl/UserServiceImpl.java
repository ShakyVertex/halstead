package org.momota.hellomp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.momota.hellomp.entity.User;
import org.momota.hellomp.mapper.UserMapper;
import org.momota.hellomp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
