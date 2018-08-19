package com.ls.demo.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ls.demo.entity.res.Code;
import com.ls.demo.entity.res.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        ObjectMapper mapper = new ObjectMapper();
        if (token == null) {
            DataResponse not_login = DataResponse.of(Code.NOT_LOGIN_ERROR, "登陆已过期,请重新登陆!");
            response.getWriter().write(mapper.writeValueAsString(not_login));
            logger.info("拦截URL:"+request.getRequestURI()+"拦截原因:token is null");
            return false;
        } else {
            if (!redisTemplate.hasKey(token)) {
                DataResponse not_login = DataResponse.of(Code.NOT_LOGIN_ERROR, "登陆已过期,请重新登陆!");
                response.getWriter().write(mapper.writeValueAsString(not_login));
                logger.info("拦截URL:"+request.getRequestURI()+"拦截原因:redis no this token:"+token);
                return false;
            }
            redisTemplate.expire(token, 30, TimeUnit.MINUTES);
            return true;
        }
    }
}
