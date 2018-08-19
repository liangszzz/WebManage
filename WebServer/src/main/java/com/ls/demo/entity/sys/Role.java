package com.ls.demo.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;

@Data
@Entity(name = "sys_role")
public class Role extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name",length =50 )
    private String roleName;

    @Column(name = "role_desc",length = 200)
    private String roleDesc;

    @Transient
    @JoinTable(name = "sys_role_menu",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "menu_id")},foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private HashSet<Menu> menus;

}
