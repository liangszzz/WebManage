package com.github.yiyan1992.webServer.entity.sys;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity(name = "sys_menu")
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", length = 50)
    private Long id;

    @NotBlank
    @Column(name = "menu_name", length = 50)
    private String name;

    @Column(name = "meun_url")
    private String url;

    /**
     * 0 菜单 1 按钮
     */
    @NotNull
    @Column(name = "menu_type", length = 1)
    private Integer type;

    @NotNull
    @Column(name = "menu_show", length = 1)
    private Integer show;

    @Column(name = "menu_parent_id", length = 50)
    private String parentId;

    @NotNull
    @Column(name = "menu_sort")
    private Integer sort;

    @Column(name = "menu_desc")
    private String desc;

    /**
     * 是否展开 默认false
     */
    @Transient
    private boolean spread = false;

    @Transient
    private List<Menu> children;
}
