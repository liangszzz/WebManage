package com.github.yiyan1992.webServer.controller.sys;

import com.github.yiyan1992.webServer.entity.res.Code;
import com.github.yiyan1992.webServer.entity.res.DataResponse;
import com.github.yiyan1992.webServer.entity.sys.User;
import com.github.yiyan1992.webServer.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "query")
    public DataResponse<User> query(User user) {
        Page<User> users = userService.findAll(user);
        return DataResponse.of(users);
    }

    @RequestMapping(value = "form/{username}")
    public DataResponse form(@PathVariable("username") String username) {
        return DataResponse.of(Code.SUCCESS, userService.findUser(username), "");
    }

    @RequestMapping(value = "save")
    public DataResponse DataResponse(User user) {
        userService.save(user);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "doDel/{username}")
    public DataResponse doDel(@PathVariable String username) {
        userService.doDel(username);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "doDelAll")
    public DataResponse doDelAll(@RequestBody List<User> users) {
        userService.doDelAll(users);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/addRoleToUser")
    public DataResponse addRoleToUser(@RequestParam String username, @RequestParam Long roleId) {
        userService.addRoleToUser(username, roleId);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/delRoleFromUser")
    public DataResponse delRoleFromUser(@RequestParam String username, @RequestParam Long roleId) {
        userService.delRoleFromUser(username, roleId);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/addDeptToUser")
    public DataResponse addDeptToUser(@RequestParam String username, @RequestParam Long deptId) {
        userService.addDeptToUser(username, deptId);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/delDeptFromUser")
    public DataResponse delDeptFromUser(@RequestParam String username, @RequestParam Long deptId) {
        userService.delDeptFromUser(username, deptId);
        return DataResponse.SUCCESS();
    }
}
