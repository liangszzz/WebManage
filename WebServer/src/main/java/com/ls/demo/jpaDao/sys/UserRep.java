package com.ls.demo.jpaDao.sys;

import com.ls.demo.entity.sys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRep extends JpaRepository<User, String> {

    @Query(nativeQuery = true, value = "insert into sys_user_role (username,role_id) values (:username,:roleId)")
    @Modifying
    @Transactional
    void addRoleToUser(@Param("username") String username, @Param("roleId") Long roleId);

    @Query(nativeQuery = true, value = "delete from sys_user_role where username=:username and role_id=:roleId")
    @Modifying
    @Transactional
    void delRoleFromUser(@Param("username") String username, @Param("roleId") Long roleId);

    @Query(nativeQuery = true, value = "insert into sys_user_dept (username,dept_id) values (:username,:deptId)")
    @Modifying
    @Transactional
    void addDeptToUser(@Param("username") String username, @Param("deptId") Long deptId);

    @Query(nativeQuery = true, value = "delete from sys_user_dept where username=:username and dept_id=:deptId")
    @Modifying
    @Transactional
    void delDeptFromUser(@Param("username") String username, @Param("deptId") Long deptId);
}
