package com.rgzs.ggg.entity;

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
@ApiModel(value="BookType对象", description="")
public class BookType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图书分类id")
    @TableId(value = "book_type_id", type = IdType.ASSIGN_UUID)
    private String bookTypeId;

    @ApiModelProperty(value = "图书分类名称")
    private String bookTypeName;

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
