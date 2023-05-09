package com.rgzs.ggg.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果类
 *
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
public final class CommonResult extends HashMap<String, Object> {
    /**
     * 成功方法
     */
    public CommonResult() {
        put("code", ReturnConstant.HTTP_RES_CODE_200);
        put("message", ReturnConstant.HTTP_RES_CODE_200_VALUE);
    }

    /**
     * 传入map类型的成功方法
     *
     * @param map
     * @return
     */
    public static CommonResult success(Map<String, Object> map) {
        CommonResult commonResult = new CommonResult();
        commonResult.putAll(map);
        return commonResult;
    }

    /**
     * 传入操作信息的成功方法
     *
     * @param message
     * @return
     */
    public static CommonResult success(String message) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", ReturnConstant.HTTP_RES_CODE_200);
        commonResult.put("message", message);
        return commonResult;
    }

    /**
     * 链表式成功方法
     *
     * @return
     */
    public static CommonResult success() {
        return new CommonResult();
    }

    /**
     * 通用失败方法
     *
     * @return
     */
    public static CommonResult error() {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", ReturnConstant.HTTP_RES_CODE_500);
        commonResult.put("message", ReturnConstant.HTTP_RES_CODE_500_VALUE);
        return commonResult;
    }

    /**
     * 传入状态码以及操作信息的失败方法
     *
     * @param code
     * @param message
     * @return
     */
    public static CommonResult error(int code, String message) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", code);
        commonResult.put("message", message);
        return commonResult;
    }

    /**
     * 传入操作信息的失败方法
     *
     * @param message
     * @return
     */
    public static CommonResult error(String message) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", ReturnConstant.HTTP_RES_CODE_500);
        commonResult.put("message", message);
        return commonResult;
    }

    /**
     * 传入返回值的方法
     *
     * @param key
     * @param value
     * @return
     */
    public CommonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    /**
     * 设置数据
     *
     * @param data
     * @return
     */
    public CommonResult setData(Object data) {
        put("data", data);
        return this;
    }

    /**
     * 获取返回值方法
     *
     * @return
     */
    public Integer getCode() {
        Integer code = (Integer) get("code");
        return code;
    }

    public static CommonResult error(int code) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", code);
        return commonResult;
    }
    public static CommonResult success(int code) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", code);
        return commonResult;
    }
}
