package com.ls.demo.mybatisDao;

import com.ls.demo.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> queryUserByUserName(String userName);
}
