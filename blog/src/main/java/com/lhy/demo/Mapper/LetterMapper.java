package com.lhy.demo.Mapper;

import com.lhy.demo.Entity.Letter;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LetterMapper {

    @Insert("insert into letter values(null,#{pic},#{name},#{content},#{createTime},#{praise},#{qq})")
    Integer insertLetter(Letter letter);
    @Select("select count(*) from letter")
    Integer getTotal();
    @Select("select * from letter order by createTime DESC limit #{start},8")
    List<Letter> getLetter(@Param("start") Integer start);
    @Update("update letter set praise = praise + 1 where id = #{id}")
    Integer setPraise(@Param("id") Integer id);
}
