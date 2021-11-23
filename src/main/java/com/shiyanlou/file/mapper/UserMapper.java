package com.shiyanlou.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiyanlou.file.model.User;
import java.util.List;

public interface UserMapper extends BaseMapper<User>{
    void insertUser(User user);
    List<User> selectUser();
}