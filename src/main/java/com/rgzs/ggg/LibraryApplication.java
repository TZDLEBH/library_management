package com.rgzs.ggg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.rgzs.ggg.mapper"})
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class,args);
    }
}
