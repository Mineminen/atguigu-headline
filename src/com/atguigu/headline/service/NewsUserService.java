package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户输入账号名称查询数据库中的用户
     * @param username
     * @return
     */
    NewsUser findByUserName(String username);

    /**
     * 根据用户uid称查询数据库中的用户
     * @param userId
     * @return
     */
    NewsUser findByUid(Integer userId);

    /**
     * 注册用户信息,注册成功返回大于0的整数,失败返回0
     * @param newsUser
     */
    Integer registerUser(NewsUser newsUser);
}
