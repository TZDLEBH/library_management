package com.rgzs.ggg.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rgzs.ggg.entity.User;
import com.rgzs.ggg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.rgzs.ggg.utils.Const.CURRENT_USER;

/**
 * @auther 中北大学——高靖奇
 * @date 2022/8/24
 */

/**
 * 自定义拦截器
 */
@Component
public class MyInterceptors implements HandlerInterceptor {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    private static String IGNORE_URI = "/user";
    private static final Logger log = LoggerFactory.getLogger(MyInterceptors.class);
    //最先执行
    //参数3：当前请求的控制器方法对象
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle方法被执行");
        boolean flag = false;
        String servletPath = request.getRequestURL().toString();
        if (servletPath.contains(IGNORE_URI)){
            flag = true;
        }
        String token = (String) request.getSession().getAttribute(CURRENT_USER);

        if (StringUtils.isEmpty(token)){
            System.out.println("WEB请求" + "URL:" + request.getRequestURL() + "请求被拦截 无Token ");
            flag = false;
        }else {
            String redisToken = (String) redisTemplate.opsForValue().get(CURRENT_USER);
            if (token.equals(redisToken)){
                System.out.println("WEB请求" + "URL:" + request.getRequestURL() + "请求被放行");
                flag = true;
            }else {
                System.out.println("WEB请求" + "URL:" + request.getRequestURL() + " token无效");
                flag = false;
            }
        }
        return flag;
    }

    //中间处理
    //参数4：modelAndView 模型和试图 当前请求访问方法的modelAndView对象
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    //最后处理
    //参数4：Exception  如果控制器出现异常时异常对象
    //这个方法：相当于try{}catch{}代码，总是执行，无论请求正确，出现异常
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
