package com.lhy.demo.Service;

import com.lhy.demo.Mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    private LabelMapper labelMapper;

    /**
     *  获取所有标签
     */
    public List<String> getAllLabel() {
        return labelMapper.getAllLabel();
    }
}
