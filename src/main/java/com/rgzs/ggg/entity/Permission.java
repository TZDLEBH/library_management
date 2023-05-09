package com.rgzs.ggg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能权限号")
    @TableId(value = "permission_id", type = IdType.ASSIGN_UUID)
    private Integer permissionId;

    @ApiModelProperty(value = "功能名称")
    private String permissionName;

    @ApiModelProperty(value = "逻辑删除(0未删除 1已删除)")
    private Integer logicDeleted;


}
