package com.buptcpr.demo.controller;

import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.service.SchoolRecApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@CrossOrigin
@Controller
public class RankQuery {

    @Autowired
    private JdbcTemplate j1;

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);


    @RequestMapping(value="/AvailableSchools",method={RequestMethod.POST, RequestMethod.GET})
    public Result<List<Map<String, Object>>> Adapter(HttpServletRequest request, HttpSession session) {
        Integer Score = Integer.parseInt(request.getParameter("scores"));
        String Upper5School = "select name,prank,score from college where (prank-%d)<=0 order by (prank-%d) desc limit 0,5;";
        String select = "select name,prank,score from college where (%d-prank)<=0 order by (%d-prank) desc limit 0,20;";
        List<Map<String, Object>> MyRank = j1.queryForList(String.format("select * from test where score>=%d", Score));
        ;
        int UserRank = 0;
        for (Map<String, Object> map : MyRank)
            for (String s : map.keySet()) {
                UserRank = Integer.parseInt(map.get(s).toString());
            }
        Map<String, Object> mr =new HashMap<>();
        try {
            mr = j1.queryForMap(String.format("select * from test where score=%d", Score));
        } catch (EmptyResultDataAccessException e) {
            UserRank++;
        }
        mr.put(Score.toString(),UserRank);
        List<Map<String,Object>> UpperSchool= SchoolRecApi.ListRes(j1,String.format(Upper5School,UserRank,UserRank));
        List<Map<String,Object>> schools=SchoolRecApi.ListRes(j1,String.format(select,UserRank,UserRank));

        List<Map<String,Object>> list=new ArrayList<>();
        list.add(mr);
        list.addAll(UpperSchool);
        list.addAll(schools);

        LOGGER.info(list.get(3).toString());



        return new Result().success(list);
    }

    public static String[] SchoolRecommand(JdbcTemplate j,Integer Score) {

        String Upper5School = "select name,prank,score from college where (prank-%d)<=0 order by (prank-%d) desc limit 0,5;";
        String select = "select name,prank,score from college where (%d-prank)<=0 order by (%d-prank) desc limit 0,20;";
        List<Map<String, Object>> MyRank = j.queryForList(String.format("select * from test where score>=%d", Score));
        ;
        int UserRank = 0;
        for (Map<String, Object> map : MyRank)
            for (String s : map.keySet()) {
                UserRank = Integer.parseInt(map.get(s).toString());
            }
        Map<String, Object> mr =new HashMap<>();
        try {
            mr = j.queryForMap(String.format("select * from test where score=%d", Score));
        } catch (EmptyResultDataAccessException e) {
            UserRank++;
        }
        mr.put(Score.toString(),UserRank);
        List<Map<String,Object>> UpperSchool= SchoolRecApi.ListRes(j,String.format(Upper5School,UserRank,UserRank));
        List<Map<String,Object>> schools=SchoolRecApi.ListRes(j,String.format(select,UserRank,UserRank));

        List<Map<String,Object>> list=new ArrayList<>();
        list.add(mr);
        list.addAll(UpperSchool);
        list.addAll(schools);


        return SchoolRecApi.StrRes(list);
    }



    public static int getCrank(JdbcTemplate j,Integer Score)
    {
        String s=Score.toString();
        String sql="select crank from test where (score-%s)>=0 order by (score-%s) asc limit 0,1;";
        String result="";
        Map<String,Object> m=j.queryForMap(String.format(sql,s,s));
        for(String a:m.keySet())
            result+=m.get(a);
        return Integer.parseInt(result);
    }


}
