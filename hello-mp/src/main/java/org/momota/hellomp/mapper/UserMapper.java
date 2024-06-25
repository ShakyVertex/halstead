package org.momota.hellomp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.momota.hellomp.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectUserPage(IPage<User> page);
}
