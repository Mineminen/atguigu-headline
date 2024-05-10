package com.atguigu.headline.controller;



import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.service.Impl.NewsHeadlineServiceImpl;
import com.atguigu.headline.service.NewsHeadlineService;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends  BaseController{
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 删除头条的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid")) ;
        headlineService.removeByHid(hid);
        WebUtil.writeJson(resp,Result.ok(null));

    }

    /**
     * 更新头条的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline headline = WebUtil.readJson(req, NewsHeadline.class);
        headlineService.updateNewsHeadline(headline);
        WebUtil.writeJson(resp,Result.ok(null));

    }

    /**
     * 头条回显实现接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline headline = headlineService.findHeadlineByHid(hid);
        Map<String ,Object>  data = new HashMap<>();
        data.put("headline",headline);
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 发布头条的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.
        String token = req.getHeader("token");
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        Long userId = JwtHelper.getUserId(token);
        newsHeadline.setPublisher(userId.intValue());
        //2.
        headlineService.addNewsHeadline(newsHeadline);

        //3.
        WebUtil.writeJson(resp,Result.ok(null));

    }
}
