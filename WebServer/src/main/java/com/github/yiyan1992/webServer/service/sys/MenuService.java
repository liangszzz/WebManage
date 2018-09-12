package com.github.yiyan1992.webServer.service.sys;

import com.github.yiyan1992.webServer.entity.sys.Menu;
import com.github.yiyan1992.webServer.jpaDao.sys.MenuRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRep menuRep;

    public List<Menu> findAllMenus() {
        List<Menu> menus = menuRep.findAllParents();
        menus.stream().forEach(e -> findMenusByMenu(e));
        return menus;
    }

    private void findMenusByMenu(Menu menu) {
        Menu temp = new Menu();
        temp.setParentId(menu.getId() + "");
        List<Menu> menus = menuRep.findAll(Example.of(temp, ExampleMatcher.matching().withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.exact())));
        if (menus.size() > 0) {
            menu.setSpread(true);
        }
        menu.setChildren(menus);
        menus.forEach(e -> findMenusByMenu(e));
    }

    public void save(Menu menu) {
        menuRep.save(menu);
    }

    public void delById(String id) {
        menuRep.deleteById(Long.parseLong(id));
        menuRep.deleteLinkById(Long.parseLong(id));
    }

    public List<Menu> queryLinkByRoleId(Long roleId) {
        return menuRep.findAllByRoleId(roleId);
    }

    public void insertLink(Long roleId, Long menuId) {
        menuRep.insertLink(roleId, menuId);
    }

    public void delLink(Long roleId, Long menuId) {
        menuRep.delLink(roleId, menuId);
    }
}
