package com.lhy.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LabelMapper {

    @Select("select label from label order by createTime DESC")
    List<String> getAllLabel();
    @Select("select id from label where label = #{label}")
    Integer getLabelId(@Param("label") String label);
}
