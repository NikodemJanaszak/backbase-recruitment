package com.backbase.recruitment.config;

import com.backbase.recruitment.security.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class ApplicationConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TokenFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/movies/*"));
        return filterRegistrationBean;
    }
}
