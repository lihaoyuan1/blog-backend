package com.lhy.demo.Service;

import com.lhy.demo.Entity.Letter;
import com.lhy.demo.Mapper.LetterMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterService {

    @Autowired
    private LetterMapper letterMapper;
    /**
     *  添加一条留言
     */
    public String addLetter(Letter letter) throws JSONException {
        JSONObject res = new JSONObject();
        if (letterMapper.insertLetter(letter) > 0){
            res.put("status", true);
        }else {
            res.put("status", false);
        }
        return res.toString();
    }
    /**
     * 获得总留言数
     */
    public Integer getTotal() {
        return letterMapper.getTotal();
    }
    /**
     * 获得所有留言
     */
    public List<Letter> getLetter(Integer start){
        return letterMapper.getLetter(start);
    }
    /**
     * 留言点赞
     */
    public String setPraise(Integer id) throws JSONException {
        JSONObject res = new JSONObject();
        if (letterMapper.setPraise(id) > 0){
            res.put("status", true);
        }else {
            res.put("status", false);
        }
        return res.toString();
    }
}
