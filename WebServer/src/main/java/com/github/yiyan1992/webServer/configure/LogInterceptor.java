package com.github.yiyan1992.webServer.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class LogInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    private ThreadLocal<Long> start_time = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        start_time.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        StringBuffer buffer = new StringBuffer("\n");
        buffer.append("请求IP:").append(request.getRemoteAddr()).append("请求路径:").append(request.getRequestURI()).append("\n");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            buffer.append("paramter:").append(entry.getKey()).append(Arrays.toString(entry.getValue())).append("\n");
        }
        buffer.append("响应时间:").append(System.currentTimeMillis() - start_time.get()).append("ms")
                .append("   最大内存:").append(Runtime.getRuntime().maxMemory() / 1024 / 1024).append("m")
                .append("   总内存:").append(Runtime.getRuntime().totalMemory() / 1024 / 1024).append("m")
                .append("   使用内存:").append((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024).append("m")
                .append("   剩余内存:").append(Runtime.getRuntime().freeMemory() / 1024 / 1024).append("m");
        logger.info(buffer.toString());
    }
}
