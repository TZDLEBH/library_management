package com.rgzs.ggg.entity.vo;

import lombok.Data;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/25
 */
@Data
public class UserRegisterVo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String telephone;
}
