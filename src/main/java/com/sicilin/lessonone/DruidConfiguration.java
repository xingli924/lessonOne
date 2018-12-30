package com.sicilin.lessonone;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.LinkedHashSet;


@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean statViewServlet(){
        /**
         * 创建servlet注册实体
         */
        StatViewServlet statViewServlet = new StatViewServlet();
        Collection<String> urlMappings = new LinkedHashSet<String>();
        urlMappings.add("/druid/*");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(statViewServlet);
        servletRegistrationBean.setUrlMappings(urlMappings);
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
