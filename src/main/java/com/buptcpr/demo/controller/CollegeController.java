package com.buptcpr.demo.controller;

import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.service.CollegeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController // This means that this class is a Controller
@RequestMapping(path="/college") // This means URL's start with /demo (after Application path)
public class CollegeController {

    @Resource
    private CollegeService collegeService;



    @RequestMapping("/save")
    public String save(){
        //实例化一个对象，添加值
        College college = new College();
        college.setScore(620);
        college.setName("东北财经");
        college.setTier(525);
        college.setCrank(157);
        college.setKind("财经");

        //保存数据
       collegeService.save(college);
        return "saved";
    }

    //修改
    @RequestMapping("/update")
    public String update(){
        //修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
        College college = collegeService.getById(1);
        collegeService.update(college);
        return "修改成功！";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(){
        collegeService.delete(1);
        return "success!";
    }

    //查询所有
    @RequestMapping("/getAll")
    public Iterable<College> getAll(){
        //查询所有的用户数据
        return collegeService.findAll();
    }

    @RequestMapping("/sort")
    public Iterable<College> sortCollege(){
        //指定参数对象：根据id，进行降序查询
        Sort sort = Sort.by(Sort.Direction.DESC,"collegeID");
        Iterable<College> collegeData = collegeService.findAllSort(sort);
        return collegeData;
    }

    //分页排序查询
    @RequestMapping("/pager")
    public List<College> sortPagerCollege(int pageIndex){
        //指定排序参数对象：根据id，进行降序查询
        Sort sort = Sort.by(Sort.Direction.DESC, "collegeID");
        /**
         * 封装分页实体
         * 参数1：pageIndex表示当前查询的是第几页(默认从0开始，0表示第一页)
         * 参数2：表示每页展示多少数据，现在设置每页展示2条数据
         * 参数3：封装排序对象，根据该对象的参数指定根据id降序查询
         */
        Pageable page = PageRequest.of(pageIndex - 1, 2, sort);
        //分页查询
        Page<College> collegeData = collegeService.PageFindAll(page);
        System.out.println("查询总页数："+collegeData.getTotalPages());
        System.out.println("查询总记录数："+collegeData.getTotalElements());
        System.out.println("查询当前第几页："+collegeData.getNumber()+1);
        System.out.println("查询当前页面的记录数："+collegeData.getNumberOfElements());
        //查询出的结果数据集合
        List<College> colleges = collegeData.getContent();
        System.out.println("查询当前页面的集合："+colleges);
        return colleges;
    }


}