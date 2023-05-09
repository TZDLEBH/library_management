package com.rgzs.ggg.service.impl;

import com.rgzs.ggg.entity.User;
import com.rgzs.ggg.mapper.UserMapper;
import com.rgzs.ggg.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 高靖奇
 * @since 2022-08-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
