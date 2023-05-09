package com.rgzs.ggg.utils;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
public interface ReturnConstant {
    /**
     * 成功信息
     */
    String HTTP_RES_CODE_200_VALUE = "请求成功！";
    /**
     * 错误信息
     */
    String HTTP_RES_CODE_500_VALUE = "系统错误";
    /**
     * 成功状态码
     */
    Integer HTTP_RES_CODE_200 = 200;
    /**
     * 错误状态码
     */
    Integer HTTP_RES_CODE_500 = 500;
    /**
     * 用户登录失败状态码
     */
    Integer USER_LOGIN_ERROR_CODE = 403;
    /**
     * 用户登录失败信息
     */
    String USER_LOGIN_ERROR_MESSAGE = "用户名或密码错误";
    /**
     * 修改密码失败状态码
     */
    Integer UPDATE_PASSWORD_ERROR_CODE = 404;
    /**
     * 修改密码失败信息
     */
    String UPDATE_PASSWORD_ERROR_MESSAGE = "修改密码失败";
    /**
     * 上传产品失败状态码
     */
    Integer UPLOAD_PRODUCT_ERROR_CODE = 406;
    /**
     * 上传产品失败信息
     */
    String UPLOAD_PRODUCT_ERROR_MESSAGE = "上传产品失败";
    /**
     * 更新用户信息失败状态码
     */
    Integer UPDATE_USER_INFO_ERROR_CODE = 407;
    /**
     * 更新用户信息失败信息
     */
    String UPDATE_USER_INFO_ERROR_MESSAGE = "更新用户信息失败";
    /**
     * 无权限访问状态码
     */
    Integer NO_PERMISSION_CODE = 408;
    /**
     * 无权限访问信息
     */
    String NO_PERMISSION_MESSAGE = "无权限访问";
    /**
     * 更新图书信息失败状态码
     */
    Integer UPDATE_BOOK_INFO_ERROR_CODE = 409;
    /**
     * 更新图书信息失败信息
     */
    String UPDATE_BOOK_INFO_ERROR_MESSAGE = "更新图书信息失败";
    /**
     * 上传类型
     */
    String POST_PRODUCT_DATA = "POST_PRODUCT_DATA";
    /**
     * 用户名已存在错误码
     */
    Integer HAD_USER_NAME_ERROR_CODE = 414;
    /**
     * 用户名已存在错误信息
     */
    String HAD_USER_NAME_ERROR_MESSAGE = "用户名已存在";
    /**
     * 图书类型已存在错误码
     */
    Integer HAD_BOOK_TYPE_NAME_ERROR_CODE = 415;
    /**
     * 图书类型已存在错误信息
     */
    String HAD_BOOK_TYPE_NAME_ERROR_MESSAGE = "图书类型已存在";
}
