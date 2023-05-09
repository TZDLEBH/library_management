package com.rgzs.ggg.service;

import com.rgzs.ggg.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */
public interface PermissionService extends IService<Permission> {

    boolean getPermissions(String token,Integer permissionId);
}
