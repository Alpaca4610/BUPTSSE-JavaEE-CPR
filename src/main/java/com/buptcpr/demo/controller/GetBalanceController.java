package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.entity.WishSheet;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/balance")
public class GetBalanceController {
        @Autowired
        CollegeRepository collegeRepository;

        @Autowired
        SheetRepository sheetRepository;

        @PostMapping("/getDetail")
        public @ResponseBody
        Result<List<Map>> getBalanceDetail(@RequestParam String id) {
            College temp = collegeRepository.findByCollegeID(id);
            if (temp == null) {
                return Result.error("1","找不到该大学！");
            }
            return  Result.success(sheetRepository.getDetail(id, id, id));
        }

        @GetMapping("/getBrief")
    public @ResponseBody Result getBalanceBrief(){
            return Result.success(collegeRepository.getAllCollegeName());
        }
    }


