package com.roger.boot;

import com.roger.boot.common.filter.AccessRecorderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootWebApplication {

    //将过滤器注册进IOC容器
    @Bean
    public FilterRegistrationBean accessRecorderFillerRegister(){
        FilterRegistrationBean regFilter = new FilterRegistrationBean();
        //创建并注册AccessRecorderFilter
        regFilter.setFilter(new AccessRecorderFilter());
        //添加请求拦截地址，下面设置对所有请求进行拦截
        regFilter.addUrlPatterns("/");
        //设置过滤器名字
        regFilter.setName("accessRecorderFilter");
        //设置过滤器执行顺序，值越小越先执行（多个过滤器同时存在时）
        regFilter.setOrder(1);
        return regFilter;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class,args);
    }
}
