package com.rgzs.ggg.entity.vo;

import lombok.Data;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/25
 */
@Data
public class UserInfoVo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色
     */
    private String roles;

    /**
     * 真是名称
     */
    private String realName;

    /**
     * 联系电话
     */
    private String telephone;
}
