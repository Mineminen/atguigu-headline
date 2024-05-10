package com.atguigu.headline.service.Impl;

import com.atguigu.headline.dao.Impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.dao.NewsHeadlineDao;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    NewsHeadlineDao headlineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData = headlineDao.findPageList(headlineQueryVo);
        int totalSize = headlineDao.findPageCount(headlineQueryVo);
        int totalPage = (totalSize+pageSize-1)/pageSize;
//        int totalPage = totalSize % pageSize == 0 ? totalSize/pageSize:totalSize/pageSize+1 ;
        Map pageInfo = new HashMap();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("pageData",pageData);


        return pageInfo;
    }


    @Override
    public HeadlineDetailVo findHeadlineDetail(int hid) {
        //修改头条的浏览量
        headlineDao.incrPageViews(hid);
        //查询头条的详情
        return headlineDao.findHeadlineDetail(hid);

    }

    @Override
    public Integer addNewsHeadline(NewsHeadline newsHeadline) {
        return headlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findHeadlineByHid(int hid) {
        return headlineDao.finHeadlineByHid(hid);
    }

    @Override
    public Integer updateNewsHeadline(NewsHeadline headline) {
        return headlineDao.updateNewsHeadline(headline);
    }

    @Override
    public Integer removeByHid(Integer hid) {
        return headlineDao.removeByHid(hid);
    }
}
