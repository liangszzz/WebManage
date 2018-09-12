package com.github.yiyan1992.webServer.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "sys_dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id", length = 50)
    private Long id;

    @Column(name = "dept_name", length = 50)
    private String name;

    @Column(name = "dept_desc", length = 200)
    private String desc;

    @Column(name = "dept_parent_id", length = 50)
    private Long parentId;

    @Transient
    private boolean spread = false;

    @Transient
    private List<Dept> children;

}
