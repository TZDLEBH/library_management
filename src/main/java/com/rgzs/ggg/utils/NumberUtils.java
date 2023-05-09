package com.rgzs.ggg.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Component
public final class NumberUtils {

    /**
     * 生成六位随机数
     *
     * @return
     */
    @Bean
    public String getSixRandomNumber() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    /**
     * 生成十六位随机数
     *
     * @return
     */
    @Bean
    public String getSixteenRandomNumber() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 16; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
