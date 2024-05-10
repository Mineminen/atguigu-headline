package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    /**
     * 找到本页的数据
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 查找总记录数
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);

    int incrPageViews(int hid);

    HeadlineDetailVo findHeadlineDetail(int hid);

    Integer addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * 根据新闻id查询新闻的完整信息并响应给前端
     * @param hid
     * @return
     */
    NewsHeadline finHeadlineByHid(int hid);

    Integer updateNewsHeadline(NewsHeadline headline);

    Integer removeByHid(Integer hid);
}
