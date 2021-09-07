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
    public static String[] StrRes(List<Map<String,Object>> res)
    {
        String[] Result = new String[res.size()];
        String temp="";
        Integer count=0;
        for(Map<String,Object> map:res) {
            if(count!=0||count!=1||count!=6)
                temp="条目"+count.toString()+":";
            else if(count==0)
                temp="你的分数";
            else if( count==1)
                temp="冲刺高分学校名单";
            else if(count==6)
                temp="大概率录取名单";
            for (String s : map.keySet()) {
                if(count==0)
                    temp = temp + s + "分" + map.get(s)+"排名";
                else
                    temp = temp + s + ":" + map.get(s);
            }
            Result[count]=temp;
            count++;

        }
        return Result;
    }
}
