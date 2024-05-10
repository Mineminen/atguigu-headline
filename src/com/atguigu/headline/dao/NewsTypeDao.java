package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 查询所有分类并动态展示新闻类别栏位
     * @return
     */
    List<NewsType> finAll();
}
