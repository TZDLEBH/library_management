package com.rgzs.ggg.controller;


import com.rgzs.ggg.entity.vo.UserLoginVo;
import com.rgzs.ggg.entity.vo.UserRegisterVo;
import com.rgzs.ggg.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */

@RequestMapping("/user")
@Api(value = "用户模块")
public interface UserController {

    /**
     * 登录
     * @param userLoginVo
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    CommonResult login(@RequestBody UserLoginVo userLoginVo, HttpSession session);

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation(value = "登出")
    CommonResult logout(HttpSession session);

    /**
     * 注册
     * @param userRegisterVo
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    CommonResult register(@RequestBody UserRegisterVo userRegisterVo);

    /**
     * 添加管理员
     * @param userId
     * @param session
     * @return
     */
    @GetMapping("/addadmin")
    @ApiOperation("添加管理员")
    CommonResult addAdmin(@RequestParam String userId, HttpSession session);

    /**
     * 更新用户信息
     * @param userRegisterVo
     * @param session
     * @return
     */
    @PostMapping("/updateuser")
    @ApiOperation(value = "更新用户信息")
    CommonResult updateUser(@RequestBody UserRegisterVo userRegisterVo, HttpSession session);

    /**
     * 重置密码
     * @param userId
     * @param session
     * @return
     */
    @GetMapping("/resetPassword")
    @ApiOperation("重置密码")
    CommonResult resetPassword(@RequestParam String userId,HttpSession session);

    /**
     * 查询全部用户
     * @param session
     * @return
     */
    @GetMapping("/selectalluser")
    @ApiOperation("查询全部用户")
    CommonResult selectAllUser(HttpSession session);

    /**
     * 删除用户
     * @param userId
     * @param session
     * @return
     */
    @GetMapping("/deleteUser")
    @ApiOperation("删除用户")
    CommonResult deleteUser(@RequestParam String userId,HttpSession session);

}

