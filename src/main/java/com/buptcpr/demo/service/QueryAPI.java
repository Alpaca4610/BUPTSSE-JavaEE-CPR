package com.buptcpr.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class QueryAPI {

    @Autowired
    private JdbcTemplate j;
    public QueryAPI(JdbcTemplate j)
    {this.j=j;}
    public java.util.List<java.util.Map<String,Object>> Result(String tablename,String[] domains)
    {
        String SQLBody="select %s from %s;";
        String ReqiredDomains="";
        for(String a:domains)
        {
            ReqiredDomains=ReqiredDomains+a+',';
        }
        String s=String.format(SQLBody,ReqiredDomains.substring(0,ReqiredDomains.length()-1),tablename);
        java.util.List<java.util.Map<String,Object>> res=j.queryForList(s);
        return res;
    }

}
