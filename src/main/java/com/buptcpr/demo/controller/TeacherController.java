package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.TeacherRepository;
import com.buptcpr.demo.common.Jwt;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Teacher;
import com.buptcpr.demo.service.StatisticsService;
import com.buptcpr.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(path="/teacher")

public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private TeacherRepository teacherRepository;


    @PostMapping(path="/login")
    public @ResponseBody
    Result<Object> teacherSignUp (@RequestParam String id, @RequestParam String password) {
        boolean i = teacherService.signIn(id, password);
        if(!i) {
            return Result.error("1","用户名或密码错误");
        }else{
            return Result.success(Jwt.createJWT(3600000));
        }
    }

    @PostMapping(path="/register")
    public @ResponseBody Result<Teacher> teacherSignIn(@RequestParam String id, @RequestParam String password,@RequestParam String name,@RequestParam String classid) {
        if(teacherService.signUp(name,id,password,classid)) {
            return Result.success(null);
        }else{
            return Result.error("1","教师已注册");
        }
    }

    @PostMapping(path = "/create_class")
    public @ResponseBody Result<Teacher> createClass(@RequestParam String id){
        String result = teacherService.createClass(id);
        if(result.equals("saved")){
            return Result.success(null);
        }else{
            return Result.error("1","class_exists");
        }
    }

    // 得到老师管理的
    @PostMapping(path = "/get_1_Rate")
    public @ResponseBody Result<Double> get1Rate(@RequestParam String id, @RequestParam int rank1Score){
        return Result.success(statisticsService.get1Rate(id, rank1Score));
    }

    // 删老师
    @PostMapping(path="/delete")
    public @ResponseBody Result<String> teacherDelete(@RequestParam String id) {
        int delete = teacherService.delete(id);
        if(delete==1){
            return Result.error("1","用户名不存在");
        }else{
            return Result.success(null);
        }
    }

    // 修改老师的, 密码
    @PostMapping(path="/update")
    public @ResponseBody Result<Teacher> teacherUpdate(@RequestParam String id,@RequestParam String name, @RequestParam String password,@RequestParam String classID)
    {
        int update = teacherService.update(id, name, password, classID);
        if(update==1){
            return Result.error("1","用户名不存在");
        }else {
            return Result.success(null);
        }
    }

    // 查看这个老师管的所有学生
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Teacher>> getStudent() {
        List<Teacher> ret = teacherRepository.findAll();
        return Result.success(ret);
    }

    @PostMapping("/getName")
    @ResponseBody
    public Result name(@RequestParam String TeacherID) {
        List<Map> ret = teacherRepository.findNameByTeacherID(TeacherID);
        return Result.success(ret);
    }
}