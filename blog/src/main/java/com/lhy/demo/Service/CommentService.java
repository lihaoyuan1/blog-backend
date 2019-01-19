package com.lhy.demo.Service;

import com.lhy.demo.Entity.Comment;
import com.lhy.demo.Mapper.ArticleMapper;
import com.lhy.demo.Mapper.CommentMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 添加一条评论
     */
    public String addComment(Comment comment){
        JSONObject jsonObject = new JSONObject();
        try{
            if (commentMapper.addComment(comment) > 0 && articleMapper.addComment(comment.getArticle_id()) > 0){
                jsonObject.put("status", true);
            }
            else{
                jsonObject.put("status", false);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return jsonObject.toString();
    }
    /**
     * 获取当前文章的8条评论
     */
    public List<Comment> findByArticleId(Integer id, Integer start){
        return commentMapper.findByArticleId(id, start);
    }
    /**
     * 当前评论点赞数加一
     */
    public String setPraise(Integer id){
        JSONObject jsonObject = new JSONObject();
        try {
            if(commentMapper.setPraise(id) > 0){
                jsonObject.put("status", true);
            }
            else {
                jsonObject.put("status", false);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return jsonObject.toString();
    }
}
