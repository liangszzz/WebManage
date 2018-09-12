package com.github.yiyan1992.webServer.jpaDao.sys;

import com.github.yiyan1992.webServer.entity.sys.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRep extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true, value = "select * from sys_role where role_id in (select role_id from sys_user_role where username=:username)")
    List<Role> findAllByUsername(@Param("username") String username);
}
