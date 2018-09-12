package com.github.yiyan1992.webServer.service.sys;

import com.github.yiyan1992.webServer.entity.sys.Role;
import com.github.yiyan1992.webServer.jpaDao.sys.RoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleService {

    @Autowired
    private RoleRep roleRep;

    public Page<Role> findAll(Role role) {
        return roleRep.findAll(Example.of(role, ExampleMatcher.matching()
                        .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                        .withMatcher("roleDesc", ExampleMatcher.GenericPropertyMatchers.contains())),
                PageRequest.of(role.getPage() - 1, role.getLimit()));
    }

    public List<Role> findAllNoPage(Role role){
        return  roleRep.findAll(Example.of(role));
    }

    public Role findRole(Long roleId) {
        Optional<Role> byId = roleRep.findById(roleId);
        return byId.orElse(new Role());
    }

    public void save(Role role) {
        roleRep.save(role);
    }

    public void doDel(Long id) {
        roleRep.deleteById(id);
    }

    public void doDelAll(List<Role> roles) {
        roleRep.deleteInBatch(roles);
    }

    public List<Role> findAllByUsername(String username) {
        return roleRep.findAllByUsername(username);
    }

}
