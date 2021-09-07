package com.buptcpr.demo.controller;

import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.controller.UploadController;
import com.buptcpr.demo.service.SchoolRecApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /*

     */

    @RequestMapping(value="/AvailableSchools",method={RequestMethod.POST, RequestMethod.GET})
    public Result<List<Map<String, Object>>> Adapter(HttpServletRequest request, HttpSession session) {
        Integer Score = Integer.parseInt(request.getParameter("scores"));
        String Upper5School = "select collegeid,name,crank,score from college order by (crank-%d) asc limit 0,5;";
        String select = "select collegeid,name,crank,score from college order by (%d-crank) desc limit 0,20;";
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
        List<Map<String,Object>> UpperSchool= SchoolRecApi.ListRes(j1,String.format(Upper5School,Score));
        List<Map<String,Object>> schools=SchoolRecApi.ListRes(j1,String.format(select,Score));

        List<Map<String,Object>> list=new ArrayList<>();
        list.add(mr);
        list.addAll(UpperSchool);
        list.addAll(schools);

        LOGGER.info(String.format(select,Score));
        return new Result().success(list);
    }

    public static String SchoolRecommand(JdbcTemplate j,int Score) {
        String Upper5School = "select collegeid,name,crank,score from college order by (crank-%d) asc limit 0,5;";
        String select = "select collegeid,name,crank,score from college order by (%d-crank) desc limit 0,20;";
        List<Map<String, Object>> MyRank = j.queryForList(String.format("select * from iiyokoiyo where totscore>=%d", Score));
        ;
        int UserRank = 0;
        for (Map<String, Object> map : MyRank)
            for (String s : map.keySet()) {
                UserRank = Integer.parseInt(map.get(s).toString());
            }
        Map<String, Object> mr =new HashMap<>();
        try {
            mr = j.queryForMap(String.format("select * from iiyokoiyo where totscore=%d", Score));
        } catch (EmptyResultDataAccessException e) {
            UserRank++;
        }
        Integer s=Score;
        mr.put(s.toString(),UserRank);
        List<Map<String,Object>> UpperSchool= SchoolRecApi.ListRes(j,String.format(Upper5School,Score));
        List<Map<String,Object>> schools=SchoolRecApi.ListRes(j,String.format(select,Score));

        List<Map<String,Object>> list=new ArrayList<>();
        list.add(mr);
        list.addAll(UpperSchool);
        list.addAll(schools);

        LOGGER.info(String.format(select,Score));
        return SchoolRecApi.StrRes(list);
    }


}
