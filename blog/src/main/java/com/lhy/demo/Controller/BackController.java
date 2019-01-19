package com.lhy.demo.Controller;

import com.lhy.demo.Entity.Article;
import com.lhy.demo.Service.ArticleService;
import com.lhy.demo.Service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/back")
@CrossOrigin(allowCredentials = "true")
public class BackController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;
    /**
     * 添加博客文章
     */
    @PostMapping(value = "/addArticle")
    public String addArticle(Article article){
        return articleService.addArticle(article);
    }
    /**
     * 获取所有标签
     */
    @GetMapping(value = "/getAllLabel")
    public List<String> getAllLAbel() {
        return labelService.getAllLabel();
    }
}
