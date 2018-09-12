package com.github.yiyan1992.webServer.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yiyan1992.webServer.entity.res.Code;
import com.github.yiyan1992.webServer.entity.res.DataResponse;
import com.github.yiyan1992.webServer.entity.sys.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Iterator;

public class AuthorityInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Value("${spring.application.useAuthority}")
    private String useAuthority;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Boolean.parseBoolean(useAuthority)) {
            String requestUrl = request.getRequestURI();
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(request.getHeader("token"));
            HashSet<Menu> menus = (HashSet<Menu>) boundHashOperations.get("menus");
            Iterator<Menu> iterator = menus.iterator();
            String del_url=requestUrl.replace("/","");
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if(menu.getUrl()!=null){
                    String string=menu.getUrl().replace("/","");
                    if(string.equals(del_url)){
                        return true;
                    }
                }
            }
            logger.info("拦截路径:" + requestUrl + "拦截原因:没有权限!");
            ObjectMapper mapper = new ObjectMapper();
            DataResponse dataResponse = DataResponse.of(Code.NO_AUTHORITY, "没有权限!");
            response.getWriter().write(mapper.writeValueAsString(dataResponse));
            return false;
        } else {
            return true;
        }
    }
}
