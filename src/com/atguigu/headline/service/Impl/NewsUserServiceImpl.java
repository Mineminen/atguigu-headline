package com.atguigu.headline.service.Impl;

import com.atguigu.headline.dao.Impl.NewsUserDaoImpl;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService{
    private NewsUserDao newsUserDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByUserName(String username) {
        return newsUserDao.findByUserName(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserDao.findByUid(userId);
    }

    @Override
    public Integer registerUser(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));

        return newsUserDao.registerUser(newsUser);
    }
}
