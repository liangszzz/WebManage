package com.github.yiyan1992.webServer;

import com.github.yiyan1992.webServer.jpaDao.sys.RoleRep;
import com.google.gson.Gson;
import com.github.yiyan1992.webServer.entity.sys.Role;
import com.github.yiyan1992.webServer.entity.sys.User;
import com.github.yiyan1992.webServer.mybatisDao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {

    private Gson gson = new Gson();
    @Autowired
    private RoleRep roleRep;

    @Autowired
    private UserDao userDao;

    @Test
    public void role1() {

        Role role = new Role();
        role.setRoleName("role");

        Page<Role> roleName = roleRep.findAll(Example.of(role, ExampleMatcher.matching().withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())), PageRequest.of(1, 10));
        System.err.println(gson.toJson(roleName));
    }


    @Test
    public void addRole() {

        List<Role> roleList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Role role = new Role();
            role.setRoleName("role_name_" + i);
            role.setRoleDesc("role_desc_" + i);
            roleList.add(role);
        }
        roleRep.saveAll(roleList);
    }

    @Test
    public void addRole2() {
        Role role = new Role();
        role.setRoleName("role_name_");
        role.setRoleDesc("role_desc_");
        roleRep.save(role);
    }

    @Test
    public void roleQueryByMyBatis(){
        List<User> root = userDao.queryUserByUserName("root");

        System.out.println(gson.toJson(root));

    }
}
