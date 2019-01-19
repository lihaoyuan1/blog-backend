package com.lhy.demo.Mapper;

import com.lhy.demo.Entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 添加一条评论
    @Insert("insert into comment values(null,#{pic},#{name},#{content},#{createTime},#{praise},#{qq},#{article_id})")
    Integer addComment(Comment comment);
    // 根据文章id查找评论，并按时间排序，按分页获取
    @Select("select * from comment where article_id = #{id} order by createTime DESC limit #{start},8")
    List<Comment> findByArticleId(@Param("id") Integer id, @Param("start") Integer start);
    // 评论被点赞，点赞次数加一
    @Update("update comment set praise = praise + 1 where id = #{id}")
    Integer setPraise(@Param("id") Integer id);
}
