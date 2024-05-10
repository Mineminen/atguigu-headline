package com.atguigu.headline.controller;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.Impl.NewsUserServiceImpl;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.MD5Util;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    NewsUserService newsUserService = new NewsUserServiceImpl();


    /**
     * 前端自己校验是否失去登录状态的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (token != null) {
            if (!JwtHelper.isExpiration(token)) {
                result = Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * 用户注册接口 把用户信息存入数据库的实现接口
     * 客户端将新用户信息发送给服务端,服务端将新用户存入数据库,存入之前做用户名是否被占用校验,校验通过响应成功提示,否则响应失败提示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        Integer rows = newsUserService.registerUser(newsUser);

        Result result = Result.ok(null);

        if (rows==0){
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);


    }

    /**
     * 用户注册接口 检验用户名是否被占用的业务实现接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        NewsUser registerUserName = newsUserService.findByUserName(username);
        Result result = null;
        if (registerUserName == null) {
            result=Result.ok(null);
        } else {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);

        }

        WebUtil.writeJson(resp,result);
    }


    /**
     * 用户登录接口
     * 根据token获取完整用户信息的业务接口实现
     * 客户端发送请求,提交token请求头,后端根据token请求头获取登录用户的详细信息并响应给客户端进行存储
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");

        Result result = null;
        result=Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (token != null&&(!("".equals(token)))) {
            if (!(JwtHelper.isExpiration(token)))  {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser loginUser = newsUserService.findByUid(userId);
                Map<String,Object> data =new HashMap<>();
                loginUser.setUserPwd("");
                data.put("loginUser",loginUser);
                result = Result.ok(data);

            }
        }

        WebUtil.writeJson(resp,result);
    }

    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser loginNewsUser = newsUserService.findByUserName(newsUser.getUsername());
        Result result = null;
        if (loginNewsUser==null){
             result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }else if(!(loginNewsUser.getUserPwd().equalsIgnoreCase(MD5Util.encrypt(newsUser.getUserPwd())))){
             result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }else{
            // 密码正确
            Map<String,Object> data =new HashMap<>();
            // 生成token口令
            String token = JwtHelper.createToken(loginNewsUser.getUid().longValue());
            // 封装数据map
            data.put("token",token);
            // 封装结果
            result=Result.ok(data);

        }
        WebUtil.writeJson(resp,result);

    }
}
