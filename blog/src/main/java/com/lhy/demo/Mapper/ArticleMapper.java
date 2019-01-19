package com.lhy.demo.Mapper;

import com.lhy.demo.Entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 添加文章
    @Insert("insert into article values(null,#{title},#{summary},#{content},#{createTime},#{comment},#{browse},#{praise},#{author},#{picture},#{label_id})")
    Integer addArticle(Article article);
    // 评论数+1
    @Update("update article set comment = comment + 1 where id = #{id}")
    Integer addComment(@Param("id") Integer id);
    // 浏览量+1
    @Update("update article set browse = browse + 1 where id = #{id}")
    void addBrowse(@Param("id") Integer id);
    // 点赞数+1
    @Update("update article set praise = praise + 1 where id = #{id}")
    Integer addPraise(@Param("id") Integer id);
    // 获取所有文章并按时间排序，按分页读取,其中不读取文章内容，节省传值开销
    @Select("select article.id,title,summary,author,comment,label,browse,praise,article.createTime,picture from article,label where label_id = label.id order by createTime DESC limit #{count},8")
    List<Article> findByCount(@Param("count") Integer count);
    // 按标签查找文章并按时间排序，按分页读取
    @Select("select article.id,title,summary,author,comment,label,browse,praise,article.createTime,picture from article,label where label_id = label.id and label = #{label} order by createTime DESC limit #{count},8")
    List<Article> findByLabel(@Param("label") String label, @Param("count") Integer count);
    // 按id获取文章内容
    @Select("select article.*,label from article,label where label.id = label_id and article.id = #{id}")
    Article findArticleById(@Param("id") Integer id);
    // 获取总文章数量
    @Select("select count(*) from article")
    Integer getTotal();
    // 获取当前标签下的总文章数量
    @Select("select count from label where label = #{label}")
    Integer getTotalByLabel(@Param("label") String label);
    // 获取点击排行榜
    @Select("select id,title from article order by browse DESC limit 0,8")
    List<Article> getRank();
    // 按标签或者文章标题查找文章
    @Select("select article.id,title,summary,author,comment,label,browse,praise,article.createTime,picture from article,label label.id = label_id and (label = #{msg} or title like concat(concat('%',#{msg}),'%')) limit #{count},8")
    List<Article> findBySearch(@Param("msg") String msg, @Param("count") Integer count);
    // 获取按标签或者文章标题查找文章的总数量
    @Select("select count(*) from article,label where label.id = label_id and (label = #{msg} or title like concat(concat('%',#{msg}),'%'))")
    Integer getSearchTotal(@Param("msg") String msg);
}
