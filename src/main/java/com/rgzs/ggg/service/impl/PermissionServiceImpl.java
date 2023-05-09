package com.rgzs.ggg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rgzs.ggg.entity.Permission;
import com.rgzs.ggg.entity.RolesPermission;
import com.rgzs.ggg.entity.User;
import com.rgzs.ggg.mapper.PermissionMapper;
import com.rgzs.ggg.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rgzs.ggg.service.RolesPermissionService;
import com.rgzs.ggg.service.RolesService;
import com.rgzs.ggg.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private UserService userService;
    @Resource
    private RolesPermissionService rolesPermissionService;

    @Override
    public boolean getPermissions(String token, Integer permissionId) {
        boolean flag = false;

        if (StringUtils.isEmpty(token)){
            return false;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("token",token);
        User user = userService.getOne(userQueryWrapper);

        QueryWrapper<RolesPermission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.eq("roles_id",user.getUserRolesId());
        List<RolesPermission> permissionList = rolesPermissionService.list(permissionQueryWrapper);

        List<Integer> list = permissionList.stream().map(permission ->{
            return permission.getPermissionId();
        }).collect(Collectors.toList());

        for (Integer id :
                list) {
            if (permissionId == id){
                flag = true;
            }
        }
        return flag;
    }
}
