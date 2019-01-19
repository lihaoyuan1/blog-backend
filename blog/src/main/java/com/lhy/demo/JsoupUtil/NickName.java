package com.lhy.demo.JsoupUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class NickName {

    public String getNickName(String qq) throws IOException {
        Document doc = Jsoup.parse(new URL("http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + qq).openStream(), "GBK", "http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + qq);
        String msg = doc.getElementsByTag("body").get(0).text();
        try {
            msg = msg.substring(msg.indexOf("["), msg.indexOf("]") + 1);
            JSONArray jsonArray = new JSONArray(msg);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", jsonArray.get(6));
            jsonObject.put("pic", "http://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640");
            return jsonObject.toString();
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
    }
}
