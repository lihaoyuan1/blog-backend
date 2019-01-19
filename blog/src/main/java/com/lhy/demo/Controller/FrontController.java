package com.lhy.demo.Controller;

import com.lhy.demo.Entity.Article;
import com.lhy.demo.Entity.Comment;
import com.lhy.demo.Entity.Letter;
import com.lhy.demo.JsoupUtil.NickName;
import com.lhy.demo.Service.ArticleService;
import com.lhy.demo.Service.CommentService;
import com.lhy.demo.Service.LabelService;
import com.lhy.demo.Service.LetterService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/front")
@CrossOrigin(allowCredentials = "true")
public class FrontController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private LetterService letterService;
    /**
     * 获取所有标签
     */
    @GetMapping(value = "/getAllLabel")
    public List<String> getAllLabel() {
        return labelService.getAllLabel();
    }
    /**
     * 按分页获取所有博客，不包括博客正文
     */
    @GetMapping(value = "/getByPage")
    public List<Article> findByPage(@RequestParam("start") Integer start){
        return articleService.findByPage(start);
    }
    /**
     * 按id获取博客所有信息，同时浏览量加一
     */
    @GetMapping(value = "/getArticle")
    public Article findArticleById(@RequestParam("article_id") Integer id){
        return articleService.findArticleById(id);
    }
    /**
     * 添加一条评论
     */
    @PostMapping(value = "/addComment")
    public String addComment(Comment comment){
        return commentService.addComment(comment);
    }
    /**
     * 获取当前文章的所有评论
     */
    @GetMapping(value = "/getComment")
    public List<Comment> getComment(@RequestParam("article_id") Integer id, @RequestParam("start") Integer start){
        return commentService.findByArticleId(id, start);
    }
    /**
     * 通过QQ号获取用户昵称和头像
     */
    @GetMapping(value = "/getNickName")
    public String getNickName(@RequestParam("qq") String qq) throws IOException {
        NickName nickName = new NickName();
        return nickName.getNickName(qq);
    }
    /**
     * 用户给文章点赞
     */
    @GetMapping(value = "/addPraise")
    public String adPraise(@RequestParam("article_id") Integer id){
        return articleService.addPraise(id);
    }
    /**
     * 用户给某条评论点赞
     */
    @GetMapping(value = "/addCommentPraise")
    public String addCommentPraise(@RequestParam("comment_id") Integer id){
        return commentService.setPraise(id);
    }
    /**
     * 获取文章总数
     */
    @GetMapping(value = "/getTotal")
    public Integer getTotal(){
        return articleService.getTotal();
    }
    /**
     * 按分页获取当前标签下的所有博客，不包括博客正文
     */
    @GetMapping(value = "/getByLabel")
    public List<Article> getByLabel(@RequestParam("start") Integer start, @RequestParam("label") String label){
        return articleService.findByLabel(label, start);
    }
    /**
     * 获取当前标签下的文章总数
     */
    @GetMapping(value = "/getTotalByLabel")
    public Integer getTotalByLabel(@RequestParam("label") String label){
        return articleService.getTotalByLabel(label);
    }
    /**
     * 获取排行榜列表
     */
    @GetMapping(value = "/getRank")
    public List<Article> getRank(){
        return articleService.getRank();
    }
    /**
     * 获取搜索内容
     */
    @GetMapping(value = "/getSearch")
    public List<Article> getSearch(@RequestParam("msg") String msg, @RequestParam("start") Integer start){
        return articleService.getSearch(msg, start);
    }
    /**
     * 获取搜搜内容的总数量
     */
    @GetMapping(value = "/getSearchTotal")
    public Integer getSearchTotal(@RequestParam("msg") String msg){
        return articleService.getSearchTotal(msg);
    }
    /**
     * 插入一条留言
     */
    @PostMapping(value = "/addLetter")
    public String addLetter(Letter letter) throws JSONException {
        return letterService.addLetter(letter);
    }
    /**
     * 获得留言总数
     */
    @GetMapping(value = "/letterTotal")
    public Integer getLetterTotal(){
        return letterService.getTotal();
    }
    /**
     * 获得所有留言
     */
    @GetMapping(value = "/getLetter")
    public List<Letter> getLetter(@RequestParam("start") Integer start){
        return letterService.getLetter(start);
    }
    /**
     * 留言被点赞
     */
    @GetMapping(value = "/letterPraise")
    public String setPraise(@RequestParam("id") Integer id) throws JSONException {
        return letterService.setPraise(id);
    }
}
