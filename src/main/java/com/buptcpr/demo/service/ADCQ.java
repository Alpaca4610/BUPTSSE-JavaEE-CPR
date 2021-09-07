package com.buptcpr.demo.service;

import com.buptcpr.demo.common.exception.ValueNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ResponseBody
@CrossOrigin
@Controller
public class ADCQ {

    @Autowired
    private JdbcTemplate j;

    /*
        Example
        Use '.' to split between names.
        http://localhost:8080/OnlineQuery?tablename=user&domains=id.name
     */

    @RequestMapping(value="/OnlineQuery",method={RequestMethod.POST, RequestMethod.GET})
    public java.util.List<java.util.Map<String,Object>> Query(HttpServletRequest request, HttpSession session)
    {
        String tablename=request.getParameter("tablename");
        String domains=request.getParameter("domains");
        String[] Domain=domains.split("\\.",0);
        return new QueryAPI(j).Result(tablename,Domain);
    }
    @RequestMapping(value="/OnlineDelete",method={RequestMethod.POST, RequestMethod.GET})
    public java.util.List<java.util.Map<String,Object>> Del(HttpServletRequest request, HttpSession session)
    {
        return null;
    }
    /*
        tablename: 要改数据的表名
        domains: 要更改的域名，‘.’隔开
        VALUES: 域名要修改成的数据，‘.’隔开
        dr: 要修改的范围，域名‘.’隔开
        drv: 域名范围等于多少,‘.’隔开
        dt1: set *** where 中，每个域名的数据类型，现在只支持int与string
        dt2: where *** 中，每个域名的数据类型，现在只支持int与string
        0=int
        1=String

        Example
        →→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→↓-------------------↓
        http://localhost:8080/OnlineAlt?tablename=user&domains=name&values=wtf&dr=id.name&drv=1.First&dt1=0&dt2=0.1


        只有成功修改才会在页面返回数值1
     */
    @RequestMapping(value="/OnlineAlt",method={RequestMethod.POST, RequestMethod.GET})
    public int Alt(HttpServletRequest request, HttpSession session)
    {
        String tablename=request.getParameter("tablename");
        String domains=request.getParameter("domains");
        String UpdateValue=request.getParameter("values");
        String DomainRange=request.getParameter("dr");
        String DomainRangeValue=request.getParameter("drv");
        String DataType1=request.getParameter("dt1");
        String DataType2=request.getParameter("dt2");
        String[] Domain=domains.split("\\.",0);
        String[] UV=UpdateValue.split("\\.",0);
        String[] DR=DomainRange.split("\\.",0);
        String[] DRV=DomainRangeValue.split("\\.",0);
        String[] DT1=DataType1.split("\\.",0);
        String[] DT2=DataType2.split("\\.",0);

        try{
                return new UpdateAPI(j).Result(tablename,Domain,UV,DR,DRV,DT1,DT2);}
            catch (ValueNotMatchException e)
            {
                e.printStackTrace();
            }
        return -1;
    }
    @RequestMapping(value="/OnlineIns",method={RequestMethod.POST, RequestMethod.GET})
    public java.util.List<java.util.Map<String,Object>> Ins(HttpServletRequest request, HttpSession session)
    {
        return null;
    }
}
