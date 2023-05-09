package com.rgzs.ggg.enums;

import lombok.Getter;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Getter
public enum RoleEnum {

    /**
     * 管理员：0，普通用户：1
     */

    ADMAIN(0),
    CUSTOMER(1);

    Integer code;

    RoleEnum(int code) {
        this.code = code;
    }
}
