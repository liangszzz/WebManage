package com.github.yiyan1992.webServer.controller.sys;

import com.github.yiyan1992.webServer.entity.res.Code;
import com.github.yiyan1992.webServer.entity.res.DataResponse;
import com.github.yiyan1992.webServer.entity.sys.Menu;
import com.github.yiyan1992.webServer.service.sys.MenuService;
import com.github.yiyan1992.webServer.utils.ValidErrorGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "menu", method = RequestMethod.POST)
public class MenuController {


    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/query")
    public DataResponse<Menu> query() {
        return DataResponse.of(menuService.findAllMenus());
    }

    @RequestMapping(value = "/save")
    public DataResponse<Menu> save(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            return DataResponse.of(Code.PARAMETER_ERROR, ValidErrorGet.getErrorFromBingingResult(result));
        }
        menuService.save(menu);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "/delById")
    public DataResponse delById(String id) {
        menuService.delById(id);
        return DataResponse.SUCCESS();
    }
}
