package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.entity.WishSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class GetBalanceController {

    public class infoTemplate{
        private int status = 0;
        private String collegeName;
    }

    infoTemplate response = new infoTemplate();

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    SheetRepository sheetRepository;

    @PostMapping("/getbalance")
     public @ResponseBody infoTemplate getbalance(@RequestParam String cName){
        College temp = collegeRepository.findByName(cName);
        if(temp == null)
            return response;
        List<WishSheet> temp1 = sheetRepository.findByWishAOrWishBOrWishC(cName,cName,cName);
        System.out.println(temp1);
        return null;
     }
}
