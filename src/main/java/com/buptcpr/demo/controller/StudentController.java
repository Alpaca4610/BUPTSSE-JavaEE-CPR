package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.service.MD5Util;
import com.buptcpr.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping("/login")//登录
    @ResponseBody
    public String login(@RequestParam String id, @RequestParam String passwd) {
        Student users = (Student) studentRepository.findByStudentID(id);
        // 如果数据库中未查到该账号:
        if (users==null) {
            return "该用户不存在";
        } else if (users.getPasswd().equals(md5Util.encode(passwd))) return "1";
        else {
            return "2";
        }
    }

    @PostMapping(path="/register")//增
    public @ResponseBody String studentRegister(@RequestParam String id,
                                                @RequestParam String name,
                                                @RequestParam String passwd,
                                                @RequestParam String classid){
        return studentService.register(id,name,passwd,classid);
    }

    @PostMapping(path="/delete")
    public @ResponseBody String studentDelete(@RequestParam String id)
    {
        return studentService.delete(id);
    }

    @PostMapping(path="/update")
    public @ResponseBody String studentDelete(@RequestParam String id,@RequestParam String name, @RequestParam String passwd,@RequestParam String classID,@RequestParam int score,@RequestParam int rank)
    {
        return studentService.update(id,name,passwd,classID,score,rank);
    }

    @GetMapping("/all")
    public @ResponseBody
    List<Student> getstudent() {
        return studentRepository.findAll();
    }
}