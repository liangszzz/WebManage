package com.github.yiyan1992.webServer.jpaDao.sys;

import com.github.yiyan1992.webServer.entity.sys.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeptRep extends JpaRepository<Dept, Long> {

    @Query(nativeQuery = true, value = "select * from sys_dept where isnull(dept_parent_id) or TRIM(dept_parent_id)=''")
    List<Dept> findAllParents();

    /**
     * 删除关联信息
     * @param deptId
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from sys_user_dept where dept_id=(select dept_id from sys_dept where dept_id=:deptId)")
    void deleteLinkById(@Param("deptId") long deptId);

    @Query(nativeQuery = true,value = "select * from sys_dept where dept_id in (select dept_id from sys_user_dept where username=:username)")
    List<Dept> findDeptsByUsername(@Param("username") String username);
}
