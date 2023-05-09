package com.rgzs.ggg.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BookLog对象", description="")
public class BookLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty(value = "图书id")
    private String bookId;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "图书出版社")
    private String bookPress;

    @ApiModelProperty(value = "图书分类号")
    private String bookTypeId;

    @ApiModelProperty(value = "图书状态")
    private Integer bookStatus;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户名称")
    private String realName;

    @ApiModelProperty(value = "用户电话")
    private String telephone;

    @ApiModelProperty(value = "借书时间")
    @TableField(fill = FieldFill.INSERT)
    private String borrowCreated;

    @ApiModelProperty(value = "还书时间")
    @TableField(fill = FieldFill.UPDATE)
    private String borrowEnd;


}
