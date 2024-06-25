package org.momota.hellomp.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.momota.hellomp.entity.User;
import org.momota.hellomp.mapper.UserMapper;
import org.momota.hellomp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    //通用Service分页查询
    @Test
    public void testPageService() {
        Page<User> page = new Page<>(2, 3);
        Page<User> userPage = userService.page(page);
        userPage.getRecords().forEach(System.out::println);
    }

    //通用Mapper分页查询
    @Test
    public void testPageMapper() {
        IPage<User> page = new Page<>(2, 3);
        IPage<User> userPage = userMapper.selectPage(page, null);
        userPage.getRecords().forEach(System.out::println);
    }

    //自定义SQL分页查询
    @Test
    public void testCustomMapper() {
        IPage<User> page = new Page<>(2, 3);
        IPage<User> userPage = userMapper.selectUserPage(page);
        userPage.getRecords().forEach(System.out::println);
    }
}