package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
public class WishRecommendController {

    @Autowired
    CollegeRepository collegeRepository;

    @PostMapping("/getRecommend")
    public @ResponseBody
    List<College> getRecommend(@RequestParam int srank){ //srank:Student Rank
        List<College> temp =  collegeRepository.findByCrankLessThan(srank);
        if(temp.isEmpty()){
            return null;
        }

        int count = Math.min(temp.size(), 5);

        List<College> result = new ArrayList<>();
        for(int i = 0;i<count;i++){
            if(temp.get(temp.size()-i-1) == null)
                break;
            else {
                result.add(temp.get(temp.size()-i-1));
            }
        }

        temp = collegeRepository.findByCrankGreaterThan(srank);
        if(temp.isEmpty()){
            return result;
        }

        count = Math.min(temp.size(), 20);

        for(int i = 0;i<count;i++){
            if(temp.get(i) == null )
                break;
            else {
                result.add(temp.get(i));
            }
        }

        return result;
    }
}
