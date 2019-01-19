package com.lhy.demo.Service;

import com.lhy.demo.Entity.Article;
import com.lhy.demo.Mapper.ArticleMapper;
import com.lhy.demo.Mapper.LabelMapper;
import org.json.JSONObject;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LabelMapper labelMapper;

    /**
     * 添加博客文章
     */
    public String addArticle(Article article){
        article.setComment(0);
        article.setBrowse(0);
        article.setPraise(0);
        article.setLabel_id(labelMapper.getLabelId(article.getLabel()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setCreateTime(sdf.format(new Date()));
        JSONObject res = new JSONObject();
        try {
            if (articleMapper.addArticle(article) > 0)
                res.put("status", true);
            else
                res.put("status", false);
        }catch (Exception e){
            System.out.println(e);
        }
        return res.toString();
    }
    /**
     * 按分页获取所有博客，不包括博客正文
     */
    public List<Article> findByPage(Integer start){
        return articleMapper.findByCount(start);
    }
    /**
     * 按id获取博客正文
     */
    public Article findArticleById(Integer id){
        articleMapper.addBrowse(id);
        return articleMapper.findArticleById(id);
    }
    /**
     * 用户给文章点赞
     */
    public String addPraise(Integer id){
        JSONObject ans = new JSONObject();
        try {
            if (articleMapper.addPraise(id) > 0)
                ans.put("status", true);
            else
                ans.put("status", false);
        }catch (Exception e){
            System.out.println(e);
        }
        return ans.toString();
    }
    /**
     * 获取总文章数量
     */
    public Integer getTotal(){
        return articleMapper.getTotal();
    }
    /**
     * 按分页获取当前标签下的所有博客，不包括博客正文
     */
    public List<Article> findByLabel(String label, Integer start) {
        if (label.equals("所有标签"))
            return articleMapper.findByCount(start);
        return articleMapper.findByLabel(label, start);
    }
    /**
     * 获取当前标签下的总文章数量
     */
    public Integer getTotalByLabel(String label){
        if (label.equals("所有标签"))
            return articleMapper.getTotal();
        return articleMapper.getTotalByLabel(label);
    }
    /**
     * 获取点击排行列表
     */
    public List<Article> getRank(){
        return articleMapper.getRank();
    }
    /**
     * 按搜索获取内容
     */
    public List<Article> getSearch(String msg, Integer count) {
        return articleMapper.findBySearch(msg, count);
    }
    /**
     * 获取按搜索获取内容的总数量
     */
    public Integer getSearchTotal(String msg){
        return articleMapper.getSearchTotal(msg);
    }
}
