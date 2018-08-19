package com.ls.demo.controller.sys;

import com.ls.demo.entity.res.Code;
import com.ls.demo.entity.res.DataResponse;
import com.ls.demo.entity.sys.Dept;
import com.ls.demo.service.sys.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dept", method = RequestMethod.POST)
public class DeptController {

    private Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/query")
    public DataResponse<Dept> query() {
        return DataResponse.of(deptService.findAllDepts());
    }

    @RequestMapping(value = "/save")
    public DataResponse<Dept> save(Dept dept) {
        deptService.save(dept);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/delById")
    public DataResponse delById(String id) {
        deptService.delById(id);
        return DataResponse.SUCCESS();
    }

    @RequestMapping("/queryDeptsByUsername/{username}")
    public DataResponse queryDeptsByUsername(@PathVariable String username) {
        return DataResponse.of(deptService.findDeptsByUsername(username));
    }

}
