package com.atguigu.headline.dao.Impl;

import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsTypeDao;
import com.atguigu.headline.pojo.NewsType;

import java.util.List;


public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> finAll() {
        String sql =" select tid,tname from news_type";
        return baseQuery(NewsType.class,sql);
    }
}
