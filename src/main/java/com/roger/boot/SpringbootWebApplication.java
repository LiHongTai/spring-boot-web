package com.roger.boot;

import com.roger.boot.common.filter.AccessRecorderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * DEBUG 模式启动
 * 热部署配置过程
 * 1.关闭页面缓存
 *      spring.thymeleaf.cache = false
 * 2.在xml中增加devtools
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-devtools</artifactId>
 *         </dependency>
 * 3.在spring-boot-maven-plugin 添加configuration的fork属性
 *                 <configuration>
 *                     <fork>true</fork>
 *                 </configuration>
 * 4.修改idea的设置  file -> settings -> build... -> compiler -> auto build project.... 勾选
 *     Help -> Find Action... -> 输入 registry 确保 compiler.automake.allow.when.app.running 被勾选
 *
 */
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
