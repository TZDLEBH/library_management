package com.rgzs.ggg.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常信息类
 *
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    /**
     * 错误状态码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
}
