package com.rgzs.ggg.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Books对象", description="")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图书id")
    @TableId(value = "book_id", type = IdType.ASSIGN_UUID)
    private String bookId;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "图书出版社")
    private String bookPress;

    @ApiModelProperty(value = "图书价格")
    private String bookPrice;

    @ApiModelProperty(value = "图书图片地址")
    private String bookPic;

    @ApiModelProperty(value = "图书分类号")
    private String bookTypeId;

    @ApiModelProperty(value = "图书状态")
    private Integer bookStatus;

    @ApiModelProperty(value = "逻辑删除(0未删除 1已删除)")
    @TableLogic
    private Integer logicDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private String gmtCreated;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private String gmtModified;


}
