package com.buptcpr.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class SchoolRecApi {
    List<Map<String,Object>> listResult;
    public static List<Map<String,Object>> ListRes(JdbcTemplate j,String sql)
    {
        return j.queryForList(sql);
    }
    public static String StrRes(List<Map<String,Object>> res)
    {
        String temp="";
        Integer count=0;
        for(Map<String,Object> map:res) {
            temp = temp+"条目"+count.toString()+":";
            for (String s : map.keySet()) {
                temp = temp + s + ":" + map.get(s);
            }
            count++;
            temp=temp+'\n';
        }
        return temp;
    }
}
