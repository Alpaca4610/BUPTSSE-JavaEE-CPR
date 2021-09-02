package com.buptcpr.demo.controller;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(path="/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
//
//    @PostMapping(path="/login")
//    public @ResponseBody String studentLogin(@RequestParam String studentID,
//                                             @RequestParam String passwd){
//        Student retStudent = studentService.login(studentID,passwd);
//        if(retStudent != null){
//            return ("登陆成功");
//        }else{
//            return ("登陆失败");
//        }
//    }

    @PostMapping(path="/register")
    public @ResponseBody String studentRegister(@RequestParam String id,
                                                @RequestParam String name,
                                                @RequestParam String passwd){
        return studentService.register(id,name, passwd);
    }
}