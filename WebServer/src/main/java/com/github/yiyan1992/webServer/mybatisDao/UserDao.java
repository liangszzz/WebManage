package com.github.yiyan1992.webServer.mybatisDao;

import com.github.yiyan1992.webServer.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> queryUserByUserName(String userName);
}
