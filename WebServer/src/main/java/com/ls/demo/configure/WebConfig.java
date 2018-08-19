package com.ls.demo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public LogInterceptor getLogInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    public AuthorityInterceptor getAuthorityInterceptor() {
        return new AuthorityInterceptor();
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLogInterceptor()).addPathPatterns("/**").order(0);
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/doLogin","/login/logout").order(1);
        registry.addInterceptor(getAuthorityInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**","/*/query").order(2);
        super.addInterceptors(registry);
    }
}
