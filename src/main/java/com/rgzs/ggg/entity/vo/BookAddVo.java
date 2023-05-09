package com.rgzs.ggg.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/26
 */
@Data
public class BookAddVo {

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 出版社
     */
    private String bookPress;

    /**
     * 图书价格
     */
    private String bookPrice;

    /**
     * 分类名称
     */
    private String bookTypeName;
}
