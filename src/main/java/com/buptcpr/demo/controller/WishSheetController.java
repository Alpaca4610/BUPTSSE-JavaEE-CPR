package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.WishSheet;
import com.buptcpr.demo.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(path="/wish_sheet")
public class WishSheetController {
    @Resource
    private SheetService sheetService;

    @Autowired
    SheetRepository sheetRepository;

    @PostMapping(path="/add")//增
    public @ResponseBody
    String postWishSheet(@RequestParam String id,
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

    @GetMapping(path="deleteAll")//未测试
    public Result deleteAll(){
        if(sheetRepository.findAll().isEmpty())
            return Result.error("1","数据库为空！");
        sheetRepository.deleteAll();
        return Result.success(null);
    }

}
