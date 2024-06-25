package org.momota.hellomp.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.momota.hellomp.entity.User;
import org.momota.hellomp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void testQueryWrapper() {
        // Select name equals to Tom
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name", "Tom");

        // Select email contains baomidou.com
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.likeLeft("email", "baomidou.com");

        // Select all user sort by age ASC
        QueryWrapper<User> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.orderByDesc("age");

        // age >= 20 and age <= 30
        QueryWrapper<User> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.ge("age", 20).le("age", 30);

        // age < 20 or age > 30
        QueryWrapper<User> queryWrapper5 = new QueryWrapper<>();
        queryWrapper5.lt("age", 20).or().gt("age", 30);

        // contains baomidou.com and (age < 30 or age > 40)
        QueryWrapper<User> queryWrapper6 = new QueryWrapper<>();
        queryWrapper6.like("email", "baomidou.com").and(
                userQueryWrapper -> userQueryWrapper.lt("age", 30).or().gt("age", 40)
        );

        // Print Output
        List<User> list = userService.list(queryWrapper6);
        list.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapper() {
        // change Tom's email to Tom@baomidou.com
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "Tom").set("email", "Tom@baomidou.com");

        userService.update(updateWrapper);
    }
}
