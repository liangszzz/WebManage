package com.github.yiyan1992.webServer.jpaDao.sys;

import com.github.yiyan1992.webServer.entity.sys.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

public interface MenuRep extends JpaRepository<Menu, Long> {

    @Query(value = "select * from sys_menu where menu_id in (select menu_id from sys_role_menu where role_id in (select role_id from sys_user_role where username=:username))", nativeQuery = true)
    HashSet<Menu> findAllByUsername(@Param("username") String username);

    @Query(value = "select * from sys_menu where isnull(menu_parent_id) or TRIM(menu_parent_id)='' ", nativeQuery = true)
    List<Menu> findAllParents();

    @Transactional
    @Modifying
    @Query(value = "delete from sys_role_menu where menu_id=:menuId", nativeQuery = true)
    void deleteLinkById(@Param("menuId") long menuId);

    @Query(value = "select * from sys_menu where menu_id in (select menu_id from sys_role_menu where role_id=:roleId)", nativeQuery = true)
    List<Menu> findAllByRoleId(@Param("roleId") Long roleId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into sys_role_menu (role_id,menu_id) values (:roleId,:menuId)")
    void insertLink(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from sys_role_menu where role_id=:roleId and menu_id=:menuId")
    void delLink(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}
