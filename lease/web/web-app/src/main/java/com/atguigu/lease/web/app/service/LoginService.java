package com.atguigu.lease.web.app.service;

import com.atguigu.lease.web.app.vo.user.LoginVo;

public interface LoginService {
    void getCode(String phone);

    String login(LoginVo loginVo);
}
