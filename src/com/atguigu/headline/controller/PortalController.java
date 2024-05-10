package com.atguigu.headline.controller;


import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.Impl.NewsHeadlineServiceImpl;
import com.atguigu.headline.service.Impl.NewsTypeServiceImpl;
import com.atguigu.headline.service.NewsHeadlineService;
import com.atguigu.headline.service.NewsTypeService;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 门户控制器
* 那些不需要登录，不需要做增删改的门户页的请求都放在这里
* */
@WebServlet("/portal/*")
public class PortalController  extends BaseController{
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    private NewsTypeService newsTypeService = new NewsTypeServiceImpl();


    /**
     * 查看头条详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //接收查询头条的id
          int hid = Integer.parseInt(req.getParameter("hid"));

          //调用服务层完成查询处理
          HeadlineDetailVo headlineDetailVo = headlineService.findHeadlineDetail(hid);
          Map data = new HashMap<>();
          data.put("headline",headlineDetailVo);
          Result result = Result.ok(data);
          //将查到的信息响应给客户端
          WebUtil.writeJson(resp,result);
    }

    /**
     * 分页查询头条信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);

        //将参数传递给服务层 进行分页查询
       Map pageInfo = headlineService.findPage(headlineQueryVo);
       Map data = new HashMap<>();
       data.put("pageInfo",pageInfo);
        //将分页查询的结果转换成json响应给客户端
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 进入新闻首页,查询所有分类并动态展示新闻类别栏位
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有的新闻类型，转入Result响应给客户端
         List<NewsType> newsTypeList = newsTypeService.findAll();

         WebUtil.writeJson(resp,Result.ok(newsTypeList));


    }
}
