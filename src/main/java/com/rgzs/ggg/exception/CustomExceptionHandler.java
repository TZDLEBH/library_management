package com.rgzs.ggg.exception;

import com.rgzs.ggg.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理类
 *
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    /**
     * 自定义失败处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    public CommonResult customExceptionHandler(CustomException e) {
        log.info(e.getCode() + e.getMessage());
        return CommonResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 通用失败处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult GlobalExceptionHandler(Exception e) {
        log.info(e.getMessage());
        return CommonResult.error(e.getMessage());
    }
}
