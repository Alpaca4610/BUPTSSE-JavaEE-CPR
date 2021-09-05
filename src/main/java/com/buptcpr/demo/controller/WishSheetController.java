package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.WishSheet;
import com.buptcpr.demo.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@CrossOrigin
@Controller
@RequestMapping(path="/wish_sheet")
public class WishSheetController {
    @Resource
    private SheetService sheetService;

    @Autowired
    SheetRepository sheetRepository;

    @Autowired
    CollegeRepository collegeRepository;

    @PostMapping(path="/add")//增
    public @ResponseBody
    Result postWishSheet(@RequestParam String id,
                           @RequestParam String wishID1,
                           @RequestParam String wishID2,
                           @RequestParam String wishID3){
        return sheetService.add(id,wishID1,wishID2,wishID3);
    }

    @PostMapping(path="/delete")
    public @ResponseBody String deleteWishSheet(@RequestParam String id)
    {
        return sheetService.delete(id);
    }

    @PostMapping(path="/update")
    public @ResponseBody String updateWishSheet(@RequestParam String id,
    @RequestParam String id1, @RequestParam String id2,@RequestParam String id3)
    {
        return sheetService.update(id, id1, id2, id3);
    }

    @Transactional
    @GetMapping(path="/deleteAll")//未测试
    public @ResponseBody Result deleteAll(){
        if(sheetRepository.findAll().isEmpty())
            return Result.error("1","数据库为空！");
        sheetRepository.deleteAll();
        collegeRepository.clearCount();
        return Result.success(null);
    }

}
