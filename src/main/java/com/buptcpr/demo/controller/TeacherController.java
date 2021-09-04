package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.TeacherRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Teacher;
import com.buptcpr.demo.service.StatisticsService;
import com.buptcpr.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path="/teacher")
@SuppressWarnings("unchecked")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping(path="/login")
    @SuppressWarnings("unchecked")
    public @ResponseBody
    Result<Object> teacherSignUp (@RequestParam String id, @RequestParam String passwd) {
        boolean i = teacherService.signIn(id, passwd);
        if(!i) {
            return Result.error("1","用户名或密码错误");
        }else{
            return Result.success(null);
        }
    }

    @GetMapping(path="/register")
    public @ResponseBody Result<Teacher> teacherSignIn(@RequestParam String id, @RequestParam String passwd) {
        if(teacherService.signIn(id, passwd)) {
            return Result.success(null);
        }else{
            return Result.error("1","教师已注册");
        }
    }

    @GetMapping(path = "/create_class")
    public @ResponseBody Result<Teacher> createClass(@RequestParam String id){
        String result = teacherService.createClass(id);
        if(result.equals("saved")){
            return Result.success(null);
        }else{
            return Result.error("1","class_exists");
        }
    }

    @GetMapping(path = "/get_1_Rate")
    public @ResponseBody Result<Float> get1Rate(@RequestParam String id){
        return Result.success(statisticsService.get1Rate(id));
    }


    @PostMapping(path="/delete")
    public @ResponseBody Result<String> teacherDelete(@RequestParam String id) {
        int delete = teacherService.delete(id);
        if(delete==1){
            return Result.error("1","用户名不存在");
        }else{
            return Result.success(null);
        }
    }

    @PostMapping(path="/update")
    public @ResponseBody Result<Teacher> studentDelete(@RequestParam String id,@RequestParam String name, @RequestParam String passwd,@RequestParam String classID)
    {
        int update = teacherService.update(id, name, passwd, classID);
        if(update==1){
            return Result.error("1","用户名不存在");
        }else {
            return Result.success(null);
        }
    }

    @GetMapping("/all")
    public @ResponseBody
    Result<List<Teacher>> getteacher() {
        return Result.success(teacherRepository.findAll());
    }
}