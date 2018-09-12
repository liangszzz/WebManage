package com.github.yiyan1992.webServer.entity.sys;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity(name = "sys_user")
public class User extends BaseEntity{

    @NotBlank
    @Id
    @Column(name = "username",length = 50,nullable = false,unique = true)
    private String username;

    @NotBlank
    @Column(name = "password",length = 60,nullable = false)
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Transient
    @ManyToMany
    @JoinTable(name = "sys_user_role",joinColumns = {@JoinColumn(name = "username",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))},inverseJoinColumns = {@JoinColumn(name = "role_id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))})
    private Set<Role> roles=new HashSet<>();

    @Transient
    @ManyToMany
    @JoinTable(name = "sys_user_dept",joinColumns = {@JoinColumn(name = "username",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))},inverseJoinColumns = {@JoinColumn(name = "dept_id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))})
    private Set<Dept> depts=new HashSet<>();
}
