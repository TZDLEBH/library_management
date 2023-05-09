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
@ApiModel(value="Roles对象", description="")
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "roles_id", type = IdType.ASSIGN_UUID)
    private Integer rolesId;

    @ApiModelProperty(value = "角色名称")
    private String rolesName;


}
