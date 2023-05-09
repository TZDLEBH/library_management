package com.rgzs.ggg.enums;

import lombok.Getter;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Getter
public enum DeleteEnum {

    /**
     * 存在：0，删除：1
     */

    EXIST(0),
    DISAPEAR(1);

    Integer code;


    DeleteEnum(int code) {
        this.code = code;
    }
}
