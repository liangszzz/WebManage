package com.ls.demo.service.sys;

import com.ls.demo.entity.sys.User;
import com.ls.demo.jpaDao.sys.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRep userRep;

    public Page<User> findAll(User user) {
        return userRep.findAll(Example.of(user, ExampleMatcher.matching()
                        .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())),
                PageRequest.of(user.getPage() - 1, user.getLimit()));
    }

    public User findUser(String username) {
        Optional<User> byId = userRep.findById(username);
        return byId.orElse(new User());
    }

    public void save(User user) {
        userRep.save(user);
    }

    public void doDel(String username) {
        userRep.deleteById(username);
    }

    public void doDelAll(List<User> users) {
        userRep.deleteInBatch(users);
    }

    public void addRoleToUser(String username, Long roleId) {
        userRep.addRoleToUser(username, roleId);
    }

    public void delRoleFromUser(String username, Long roleId) {
        userRep.delRoleFromUser(username, roleId);
    }

    public void addDeptToUser(String username, Long deptId) {
        userRep.addDeptToUser(username, deptId);
    }

    public void delDeptFromUser(String username, Long deptId) {
        userRep.delDeptFromUser(username, deptId);
    }
}
