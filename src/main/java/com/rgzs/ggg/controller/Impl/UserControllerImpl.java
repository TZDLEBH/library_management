package com.rgzs.ggg.controller.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rgzs.ggg.controller.UserController;
import com.rgzs.ggg.entity.Roles;
import com.rgzs.ggg.entity.User;
import com.rgzs.ggg.entity.vo.UserInfoVo;
import com.rgzs.ggg.entity.vo.UserLoginVo;
import com.rgzs.ggg.entity.vo.UserRegisterVo;
import com.rgzs.ggg.enums.DeleteEnum;
import com.rgzs.ggg.enums.PermissionEnum;
import com.rgzs.ggg.enums.RoleEnum;
import com.rgzs.ggg.service.PermissionService;
import com.rgzs.ggg.service.RolesService;
import com.rgzs.ggg.service.UserService;
import com.rgzs.ggg.utils.CommonResult;
import com.rgzs.ggg.utils.NumberUtils;
import com.rgzs.ggg.utils.ReturnConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.rgzs.ggg.utils.Const.CURRENT_USER;
import static com.rgzs.ggg.utils.Const.FAILURE_TIME;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-24
 */

@Slf4j
@RestController
public class UserControllerImpl implements UserController {

    @Resource
    private UserService userService;
    @Resource
    private NumberUtils numberUtils;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolesService rolesService;

    @Override
    public CommonResult login(UserLoginVo userLoginVo, HttpSession session) {
        if (!StringUtils.isEmpty(userLoginVo)){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_name",userLoginVo.getUserName()).eq("logic_deleted",DeleteEnum.EXIST.getCode());
            User user = userService.getOne(userQueryWrapper);
            //验证用户名是否存在
            if (StringUtils.isEmpty(user)){
                return CommonResult.error(ReturnConstant.USER_LOGIN_ERROR_CODE,ReturnConstant.USER_LOGIN_ERROR_MESSAGE);
            }
            //验证密码
            if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(userLoginVo.getPassword().getBytes(StandardCharsets.UTF_8)))){
                return CommonResult.error(ReturnConstant.USER_LOGIN_ERROR_CODE,ReturnConstant.USER_LOGIN_ERROR_MESSAGE);
            }
            //生成token
            String token = numberUtils.getSixRandomNumber();
            //存到session
            session.setAttribute(CURRENT_USER,token);
            session.setMaxInactiveInterval(5*60);

            //token存到redis
            redisTemplate.opsForValue().set(userLoginVo.getUserName(),token,FAILURE_TIME, TimeUnit.MINUTES);

            //将token存到mysql中
            User user1 = new User();
            user1.setToken(token);
            userService.update(user1,userQueryWrapper);

            return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
        }
        return CommonResult.error("信息为空");
    }

    @Override
    public CommonResult logout(HttpSession session) {
        //获取token
        String token = (String) session.getAttribute(CURRENT_USER);
        //通过token查用户名
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("token",token);
        User user = userService.getOne(userQueryWrapper);
        //删除redis中缓存
        redisTemplate.delete(user.getUserName());
        //删除mysql中的token
        user.setToken("");
        //处理时间
        Date addTime = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(addTime);
        user.setGmtModified(date);
        userService.update(user,userQueryWrapper);
        //删除session
        session.removeAttribute(CURRENT_USER);
        return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
    }

    @Override
    public CommonResult register(UserRegisterVo userRegisterVo) {
        if (!StringUtils.isEmpty(userRegisterVo)){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_name",userRegisterVo.getUserName()).eq("logic_deleted", DeleteEnum.EXIST.getCode());

            int countByUsername = userService.count(userQueryWrapper);
            if (countByUsername > 0){
                return CommonResult.error(ReturnConstant.HAD_USER_NAME_ERROR_CODE,ReturnConstant.HAD_USER_NAME_ERROR_MESSAGE);
            }

            User user = new User();
            //属性对拷
            BeanUtils.copyProperties(userRegisterVo,user);
            //设置id
            String id = numberUtils.getSixteenRandomNumber()+"user";
            user.setUserId(id);
            //密码加密
            user.setPassword(DigestUtils.md5DigestAsHex(userRegisterVo.getPassword().getBytes(StandardCharsets.UTF_8)));
            //设置身份
            user.setUserRolesId(RoleEnum.CUSTOMER.getCode());
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            user.setGmtCreated(date);

            boolean one = userService.save(user);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }
        }
        return CommonResult.error("信息为空");
    }

    @Override
    public CommonResult addAdmin(String userId,HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER), PermissionEnum.ADDADMIN.getCode());
        if (flag){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_name",userId).eq("logic_deleted", DeleteEnum.EXIST.getCode());

            User user = new User();
            user.setUserRolesId(RoleEnum.ADMAIN.getCode());

            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            user.setGmtModified(date);

            boolean one = userService.update(user,userQueryWrapper);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }else {
                return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
            }
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult updateUser(UserRegisterVo userRegisterVo, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.UPDATAUSER.getCode());
        if (flag){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("token", session.getAttribute(CURRENT_USER));
            User user = new User();
            BeanUtils.copyProperties(userRegisterVo,user);
            //处理密码
            if (!StringUtils.isEmpty(userRegisterVo.getPassword())){
                user.setPassword(DigestUtils.md5DigestAsHex(userRegisterVo.getPassword().getBytes(StandardCharsets.UTF_8)));
            }
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            user.setGmtModified(date);

            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("token",session.getAttribute(CURRENT_USER));
            boolean one = userService.update(user,userUpdateWrapper);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }else {
                return CommonResult.error(ReturnConstant.HTTP_RES_CODE_500,ReturnConstant.HTTP_RES_CODE_500_VALUE);
            }
        }
        return CommonResult.error(ReturnConstant.UPDATE_USER_INFO_ERROR_CODE,ReturnConstant.UPDATE_USER_INFO_ERROR_MESSAGE);
    }

    @Override
    public CommonResult resetPassword(String userId, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.RESETPASSWORD.getCode());
        if (flag){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_id",userId).eq("logic_deleted", DeleteEnum.EXIST.getCode());
            User user  = userService.getOne(userQueryWrapper);

            user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            user.setGmtModified(date);

            boolean one = userService.update(user,userQueryWrapper);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }else {
                return CommonResult.error(ReturnConstant.UPDATE_PASSWORD_ERROR_CODE,ReturnConstant.UPDATE_PASSWORD_ERROR_MESSAGE);
            }
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult selectAllUser(HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.SELECTALLUSER.getCode());
        if (flag){
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("logic_deleted", DeleteEnum.EXIST.getCode());
            List<User> users = userService.list();


            List<UserInfoVo> userInfoVos = users.stream().map(user -> {
                UserInfoVo userInfoVo = new UserInfoVo();
                //属性对拷
                BeanUtils.copyProperties(user,userInfoVo);
                //处理密码
                userInfoVo.setPassword("******");
                //处理角色
                QueryWrapper<Roles> rolesQueryWrapper = new QueryWrapper<>();
                rolesQueryWrapper.eq("roles_id",user.getUserRolesId());
                Roles roles = rolesService.getOne(rolesQueryWrapper);
                userInfoVo.setRoles(roles.getRolesName());

                return userInfoVo;
            }).collect(Collectors.toList());
            return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200).put("userlist",userInfoVos);
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

    @Override
    public CommonResult deleteUser(String userId, HttpSession session) {
        boolean flag = permissionService.getPermissions((String) session.getAttribute(CURRENT_USER),PermissionEnum.DELETEUSER.getCode());
        if (flag){
            User user = new User() ;
            //删除
            user.setUserId(userId);
            user.setLogicDeleted(DeleteEnum.DISAPEAR.getCode());
            //处理时间
            Date addTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(addTime);
            user.setGmtModified(date);

            //boolean one = userService.updateById(user);
            boolean one = userService.removeById(user);
            if (one){
                return CommonResult.success(ReturnConstant.HTTP_RES_CODE_200);
            }else {
                return CommonResult.error(ReturnConstant.UPDATE_USER_INFO_ERROR_CODE,ReturnConstant.UPDATE_USER_INFO_ERROR_MESSAGE);
            }
        }
        return CommonResult.error(ReturnConstant.NO_PERMISSION_CODE,ReturnConstant.NO_PERMISSION_MESSAGE);
    }

}

