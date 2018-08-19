package com.ls.demo;

import com.google.gson.Gson;
import com.ls.demo.entity.sys.Role;
import com.ls.demo.jpaDao.sys.RoleRep;
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

    private Gson gson=new Gson();
    @Autowired
    private RoleRep roleRep;

    @Test
    public void role1(){

        Role role=new Role();
        role.setRoleName("role");

        Page<Role> roleName = roleRep.findAll(Example.of(role, ExampleMatcher.matching().withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())), PageRequest.of(1,10));
        System.err.println(gson.toJson(roleName));
    }


    @Test
    public void addRole(){

        List<Role> roleList=new ArrayList<>();

        for (int i=0;i<100;i++){
            Role role=new Role();
            role.setRoleName("role_name_"+i);
            role.setRoleDesc("role_desc_"+i);
            roleList.add(role);
        }
        roleRep.saveAll(roleList);
    }
}
