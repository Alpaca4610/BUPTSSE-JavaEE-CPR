package com.buptcpr.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankLookup {
    @Autowired
    JdbcTemplate j1;

    public int SCORE_RANK(int Score)
    {
        List<Map<String, Object>> MyRank = j1.queryForList(String.format("select * from iiyokoiyo where totscore>=%d", Score));
        int UserRank = 0;
        for (Map<String, Object> map : MyRank)
            for (String s : map.keySet()) {
                UserRank = Integer.parseInt(map.get(s).toString());
            }
        Map<String, Object> mr =new HashMap<>();
        try {
            mr = j1.queryForMap(String.format("select * from iiyokoiyo where totscore=%d", Score));
        } catch (EmptyResultDataAccessException e) {
            UserRank++;
        }
        return UserRank;
    }

}
