package com.github.yiyan1992.webServer.controller.sys;

import com.github.yiyan1992.webServer.entity.res.Code;
import com.github.yiyan1992.webServer.entity.res.DataResponse;
import com.github.yiyan1992.webServer.entity.sys.Role;
import com.github.yiyan1992.webServer.service.sys.MenuService;
import com.github.yiyan1992.webServer.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "role", method = RequestMethod.POST)
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "query")
    public DataResponse<Role> query(Role role) {
        Page<Role> roles = roleService.findAll(role);
        return DataResponse.of(roles);
    }

    @RequestMapping(value = "queryAll")
    public DataResponse<Role> queryAll(Role role) {
        List<Role> roles = roleService.findAllNoPage(role);
        return DataResponse.of(roles);
    }

    @RequestMapping(value = "form/{roleId}")
    public DataResponse form(@PathVariable Long roleId) {
        return DataResponse.of(Code.SUCCESS, roleService.findRole(roleId), "");
    }

    @RequestMapping(value = "save")
    public DataResponse save(Role role) {
        roleService.save(role);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "doDel/{id}")
    public DataResponse doDel(@PathVariable Long id) {
        roleService.doDel(id);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "doDelAll")
    public DataResponse doDelAll(@RequestBody List<Role> roles) {
        roleService.doDelAll(roles);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/queryLink/{id}")
    public DataResponse queryLink(@PathVariable("id") Long id) {
        return DataResponse.of(menuService.queryLinkByRoleId(id));
    }

    @RequestMapping(value = "/addLink")
    public DataResponse addLink(@RequestParam(required = true) Long roleId, @RequestParam(required = true) Long menuId) {
        menuService.insertLink(roleId, menuId);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/delLink")
    public DataResponse delLink(@RequestParam(required = true) Long roleId, @RequestParam(required = true) Long menuId) {
        menuService.delLink(roleId, menuId);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/queryRolesByUsername/{username}")
    public DataResponse queryRolesByUsername(@PathVariable("username") String username) {
        return DataResponse.of(roleService.findAllByUsername(username));
    }

}
