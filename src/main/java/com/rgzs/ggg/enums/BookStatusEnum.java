package com.rgzs.ggg.enums;

import lombok.Getter;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */
@Getter
public enum BookStatusEnum {

    /**
     * 存在：0，出借：1
     */

    EXIST(0,"存在"),
    DISAPEAR(1,"借出");

    Integer code;
    String massage;

    BookStatusEnum(int code,String massage) {
        this.code = code;
        this.massage = massage;
    }

    public static Integer getcode(String massage){
        for (BookStatusEnum b :
                BookStatusEnum.values()) {
            if (b.massage.equals(massage)){
                return b.code;
            }
        }
        return null;
    }
    
    public static String getMassage(Integer code){
        for (BookStatusEnum b :
                BookStatusEnum.values()) {
            if (b.code == code){
                return b.getMassage();
            }
        }
        return null;
    }
}
