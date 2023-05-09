package com.rgzs.ggg.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Configuration
@AllArgsConstructor
public class MvcConfig implements WebMvcConfigurer {


    private MyInterceptors myInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptors)//指定拦截器
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/user/**","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")//排除的路径
                .order(1);//指定拦截器顺序，int类型数字，默认按照自然顺序执行，数字相同时，按照配置顺序执行
    }

}
