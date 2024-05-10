package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 分页查询头条信息的接口实现
     * @param headlineQueryVo
     * @return
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     *查看头条信息详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     *添加头条信息
     * @param newsHeadline
     */
    Integer addNewsHeadline(NewsHeadline newsHeadline);


    /**
     *根据新闻id查询新闻的完整信息并响应给前端
     * @param hid
     * @return
     */
    NewsHeadline findHeadlineByHid(int hid);


    /**
     * 修改数据库中的信息
     * @param headline
     */
    Integer updateNewsHeadline(NewsHeadline headline);

    Integer removeByHid(Integer hid);
}
