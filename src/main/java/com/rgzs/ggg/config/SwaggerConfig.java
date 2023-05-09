package com.rgzs.ggg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Configuration
@EnableSwagger2//开启swagger
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        //获取项目环境
//        Profiles profiles = Profiles.of("dev","test");
//        boolean flag = environment.acceptsProfiles(profiles);

        //配置Swagger类型
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //开启swagger
        docket.enable(true);
//        docket.enable(flag);
        //配置分组名
        docket.groupName("librarty_management");
        //配置api信息
        docket.apiInfo(apiInfo());
        //配置搜索包
        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rgzs.ggg"))
                .build();
        return docket;
    }
    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "图书馆管理系统"
                , "基于springboot的图书馆管理系统"
                , "1.0"
                , "urn:tos",
                contact()
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList<VendorExtension>());
    }
    /**
     * 配置联系信息
     *
     * @return
     */
    public Contact contact() {

        return new Contact("高靖奇", "https://blog.csdn.net/qq_45414532?spm=1000.2115.3001.5343", "X15234435771@163.com");
    }
}

