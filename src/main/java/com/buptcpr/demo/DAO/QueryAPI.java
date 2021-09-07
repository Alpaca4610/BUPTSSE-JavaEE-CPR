package com.buptcpr.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;


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
        List<Map<String, Object>> res1 = res;
        return res1;
    }
    public java.util.List<java.util.Map<String,Object>> Result(String tname,String[] domains,String[] domainrange,String[] DomainRnageValue)
    {
        String SQLBody="select %s from %s where %s;";
        String ReqiredDomains="";
        String dr="";
        for(String a:domains)
        {
            ReqiredDomains=ReqiredDomains+a+',';
        }
        for(int i=0;i<domainrange.length;i++)
        {
            dr=dr+domainrange[i]+"="+DomainRnageValue[i]+",";
        }
        String s=String.format(SQLBody,ReqiredDomains.substring(0,ReqiredDomains.length()-1),tname,dr.substring(0,dr.length()-1));
        return j.queryForList(s);
    }


}
