package com.github.yiyan1992.webServer.controller.sys;

import com.github.yiyan1992.webServer.entity.res.Code;
import com.github.yiyan1992.webServer.entity.res.DataResponse;
import com.github.yiyan1992.webServer.entity.sys.User;
import com.github.yiyan1992.webServer.service.sys.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liang
 */
@RestController
@RequestMapping(value = "login", method = RequestMethod.POST)
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "doLogin")
    public DataResponse doLogin(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return DataResponse.of(Code.PARAMETER_ERROR, "用户名密码不能为空!");
        }
        String token = loginService.doLogin(user);
        if (StringUtils.isEmpty(token)) {
            return DataResponse.of(Code.PARAMETER_ERROR, "用户名密码不正确!");
        }
        return DataResponse.of(Code.SUCCESS, token);
    }

    @RequestMapping(value = "logout")
    public DataResponse logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        loginService.logout(token);
        return DataResponse.SUCCESS();
    }

    @RequestMapping(value = "indexData")
    public DataResponse indexData(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>();
        loginService.indexData(token, map);
        return DataResponse.of(Code.SUCCESS, map);
    }

}
