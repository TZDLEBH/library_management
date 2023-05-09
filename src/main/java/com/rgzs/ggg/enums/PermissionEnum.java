package com.rgzs.ggg.enums;

import lombok.Getter;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/25
 */
@Getter
public enum PermissionEnum {
    LOGIN(1,"登录"),
    LOGOUT(2,"登出"),
    REGISTER(3,"注册"),
    ADDADMIN(4,"添加管理员"),
    UPDATAUSER(5,"更新用户信息"),
    RESETPASSWORD(6,"重置密码"),
    SELECTALLUSER(7,"查询所有用户"),
    DELETEUSER(8,"删除用户"),

    ADDBOOKTYPE(9,"添加图书分类"),
    SELECTALLBOOKTYPE(10,"查找所有分类"),
    DELETEBOOKTYPE(11,"删除分类"),

    DELETEBOOK(12,"删除图书"),
    UPDATABOOK(13,"修改图书信息"),
    SELECTABOOK(14,"查找图书"),
    ADDBOOK(15,"新增图书"),
    BORROWBOOK(16,"借书"),
    RETURNBOOK(17,"还书");

    Integer code;
    String massage;

    PermissionEnum(int code,String massage) {
        this.code = code;
        this.massage = massage;
    }
}
