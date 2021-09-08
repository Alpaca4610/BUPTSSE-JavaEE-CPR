package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.entity.Teacher;
import com.buptcpr.demo.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path="/college")
public class CollegeController {

    @Resource
    private CollegeService collegeService;
    @Autowired
    private CollegeRepository collegeRepository;

    @PostMapping ("/add")//增
    @ResponseBody
    public Result<College> save(@RequestParam String id,@RequestParam String name ,@RequestParam int score, @RequestParam int tier, @RequestParam String kind, @RequestParam int rank1, @RequestParam int rank2, @RequestParam int rank3, @RequestParam int rank4){
        Integer[] list=new Integer[4];
        list[0]=rank1;
        list[1]=rank2;
        list[2]=rank3;
        list[3]=rank4;
        College college = collegeService.add(id,name,score,tier,rank1, collegeService.Avg(list),kind);
        return Result.success(college);
    }

    @PostMapping("/delete")//删
    @ResponseBody
    public Result<College> delete(@RequestParam String id){
        collegeService.delete(id);
        return Result.success(null);
    }

    // 修改学校
    @PostMapping("/update")
    @ResponseBody
    public Result<College> update(String id,String name, String kind,int tier,int score,int rank){
        //修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
        int ret = collegeService.update(id,name,kind,tier,score,rank);
        if(ret == 0){ //成功
            return Result.success(null);
        }else { // 失败
            return Result.error("1","更新失败, 没有这个学校");
        }
    }

    // 查找所有大学
    @GetMapping("/all")
    @ResponseBody
    public Result<List<College>> findAll(){
        return Result.success(collegeRepository.findAll());
    }

    @Transactional
    @GetMapping("/clearCount")
    @ResponseBody
    public Result clearCount(){
        if (collegeRepository.findAll().isEmpty())
            return Result.error("1","数据库为空");
        collegeRepository.clearCount();
        return Result.success(null);
    }

    //查找区间分数内所有大学
    @PostMapping("/get-interval-list")
    @ResponseBody
    public Result<List<Map>> getIntervalList(@RequestParam int min, @RequestParam int max){
        return Result.success(collegeService.getIntervalList(min, max));
    }

    @GetMapping("/brief")
    public @ResponseBody Result<List<Map>> getbrief(){
        return Result.success(collegeRepository.getbrief());
    }
}