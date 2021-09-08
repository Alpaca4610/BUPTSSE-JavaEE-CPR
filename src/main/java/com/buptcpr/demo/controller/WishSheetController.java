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

    // 这个就是三个志愿那个地方
    // studentId 是前端寸的
    // wishID1, wishID2, wishID3 是三个大学的, 标签
    @PostMapping(path="/add")//增
    public @ResponseBody
    Result postWishSheet(@RequestParam String id,
                           @RequestParam String wishID1,
                           @RequestParam String wishID2,
                           @RequestParam String wishID3){
        return sheetService.add(id,wishID1,wishID2,wishID3);
    }

    @PostMapping(path="/delete")
    public @ResponseBody Result deleteWishSheet(@RequestParam String id)
    {
        return sheetService.delete(id);
    }

    @PostMapping(path="/update")
    public @ResponseBody Result updateWishSheet(@RequestParam String id,
    @RequestParam String wishID1, @RequestParam String wishID2,@RequestParam String wishID3)
    {
        return sheetService.update(id, wishID1, wishID2, wishID3);
    }

    @PostMapping(path="/judge")
    public @ResponseBody Result updateWishSheet(@RequestParam String id)
    {
        if(sheetRepository.findByStudentID(id) == null){
            return Result.error("1","该学生未填报！");
        }
        else
        return Result.success(sheetRepository.findByStudentID(id));
    }

    @Transactional
    @GetMapping(path="/deleteAll")
    public @ResponseBody Result deleteAll(){
        if(sheetRepository.findAll().isEmpty())
            return Result.error("1","数据库为空！");
        sheetRepository.deleteAll();
        collegeRepository.clearCount();
        return Result.success(null);
    }

    @GetMapping("/all")
    public @ResponseBody Result getall(){
        if(sheetRepository.findAll().isEmpty()){
            return Result.error("1","数据库为空！");
        }
        return Result.success(sheetRepository.getAll());
    }
}
