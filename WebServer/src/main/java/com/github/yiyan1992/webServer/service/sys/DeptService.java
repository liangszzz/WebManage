package com.github.yiyan1992.webServer.service.sys;

import com.github.yiyan1992.webServer.entity.sys.Dept;
import com.github.yiyan1992.webServer.jpaDao.sys.DeptRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptRep deptRep;

    /**
     * 查询所有节点
     *
     * @return
     */
    public List<Dept> findAllDepts() {
        List<Dept> depts = deptRep.findAllParents();
        depts.forEach(e -> findDetsByDept(e));
        return depts;
    }

    private void findDetsByDept(Dept dept) {
        Dept temp = new Dept();
        temp.setParentId(dept.getId());
        List<Dept> depts = deptRep.findAll(Example.of(temp, ExampleMatcher.matching().withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.exact())));
        if (depts.size() > 0) {
            dept.setSpread(true);
        }
        dept.setChildren(depts);
        depts.forEach(e -> findDetsByDept(e));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Dept dept) {
        deptRep.save(dept);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void delById(String id) {
        deptRep.deleteById(Long.parseLong(id));
        deptRep.deleteLinkById(Long.parseLong(id));
    }

    public List<Dept> findDeptsByUsername(String username) {
        return deptRep.findDeptsByUsername(username);
    }
}
