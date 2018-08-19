package com.ls.demo;

import com.google.gson.Gson;
import com.ls.demo.entity.sys.Dept;
import com.ls.demo.service.sys.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void test0(){
        Gson gson=new Gson();
        List<Dept> depts=deptService.findAllDepts();
        System.out.println(gson.toJson(depts));
    }
}
