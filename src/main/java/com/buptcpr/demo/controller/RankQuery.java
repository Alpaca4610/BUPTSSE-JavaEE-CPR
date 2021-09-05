package com.buptcpr.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public List<Map<String,Object>> Adapter(HttpServletRequest request, HttpSession session) {
        Integer Score=Integer.parseInt(request.getParameter("scores"));
        Integer ScoreGap=Integer.parseInt(request.getParameter("gap"));
        String select="select collegeid,name,score from college where abs(score-%d)<=%d order by score desc;";
        List<Map<String,Object>> schools=j1.queryForList(String.format(select,Score,ScoreGap));

        LOGGER.info(String.format(select,Score,ScoreGap));

        return schools;
    }
}
