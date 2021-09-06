package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.service.MD5Util;
import com.buptcpr.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path="/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MD5Util md5Util;

    // 登录
    @PostMapping("/login")
    @ResponseBody
    public Result<Student> login(String id, String password) {
        Student res = studentService.login(id,password);
        if(res!=null){
            return Result.success(res);
        }else{
            return Result.error("-1","登陆失败");
        }
    }

    // 学生注册
    @PostMapping(path="/register")
    @ResponseBody
    public Result<Student> studentRegister(@RequestParam String id,
                                                @RequestParam String name,
                                                @RequestParam String passwd,
                                                @RequestParam String classid){
        int ret = studentService.register(id,name,passwd,classid);
        if(ret == 1){ //学生已注册, 注册失败
            return Result.error("1","学生已注册, 注册失败");
        }else{
            return Result.success(null);
        }
    }

    // 删除学生: 肯定会成功, 因为只能删除已有的,在界面上显示的学生
    @PostMapping(path="/delete")
    @ResponseBody
    public Result studentDelete(@RequestParam String id)
    {
        if(studentRepository.findByStudentID(id) == null){
            return  Result.error("1","学生不存在！");
        }
        studentService.delete(id);
        return Result.success(null);
    }

    // 更新学生信息
    @PostMapping(path="/update")
    @ResponseBody
    public Result<Student> studentDelete(@RequestParam String id,@RequestParam String name, @RequestParam String passwd,@RequestParam String classID,@RequestParam int score,@RequestParam int rank)
    {
        int ret = studentService.update(id,name,passwd,classID,score,rank);
        if(ret == 1){ // 失败, 找不到这个人
            return Result.error("1","找不到该学生");
        }else{
            return Result.success(null);
        }
    }

    @PostMapping(path="/insert")
    @ResponseBody
    public Result<Student> studentInsert(@RequestParam String id,@RequestParam String name, @RequestParam String passwd,@RequestParam String classID,@RequestParam int score)
    {
        int ret = studentService.insert(id,name,passwd,classID,score);
        if(ret == 1){ // 失败, 找不到这个人
            return Result.error("1","该学生已存在");
        }else{
            return Result.success(null);
        }
    }

    // 查找所有学生信息
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Student>> getstudent() {
        List<Student> ret = studentRepository.findAll();
        return Result.success(ret);
    }
}