package com.github.yiyan1992.webServer;

import com.github.yiyan1992.webServer.jpaDao.sys.RoleRep;
import com.github.yiyan1992.webServer.entity.sys.Dept;
import com.github.yiyan1992.webServer.entity.sys.Role;
import com.github.yiyan1992.webServer.entity.sys.User;
import com.github.yiyan1992.webServer.jpaDao.sys.DeptRep;
import com.github.yiyan1992.webServer.jpaDao.sys.UserRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserRep userRep;

    @Autowired
    private DeptRep deptRep;

    @Autowired
    private RoleRep roleRep;

    @Test
    public void testUserRole(){
        User user=new User();
        user.setUsername("root");
        user.setPassword("123456");

        Role role=new Role();
        role.setRoleName("root_n");
        role.setRoleDesc("root desc");
        roleRep.save(role);

        HashSet<Role> roleList=new HashSet<>();
        roleList.add(role);
        user.setRoles(roleList);

        Dept dept=new Dept();
        dept.setName("root_n");
        dept.setDesc("dept desc");
        deptRep.save(dept);

        HashSet<Dept> depts=new HashSet<>();
        depts.add(dept);
        user.setDepts(depts);

        userRep.save(user);
    }



}
