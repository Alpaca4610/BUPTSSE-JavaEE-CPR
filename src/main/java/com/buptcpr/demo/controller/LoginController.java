package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    //todo 解决valid验证失败的问题
    @PostMapping("/login")
    @ResponseBody
//    public String myvalidate(@Valid Student student, BindingResult result){
//        if(result.hasErrors())
//            return "fail";
//        else
//            return "success";
//    }
    public String login(@RequestParam String username, @RequestParam String password) {

        List<Student> users = studentRepository.findBystudentID(username);
        // 如果数据库中未查到该账号:
        if (users.isEmpty()) {
            return "该用户不存在";
        } else {
            System.out.println(users);
            Student nowUser = users.get(0);
            if (nowUser.getPasswd().equals(password)) {
                return "1";
            } else {
                return "2";
            }
//            return "index";
        }
    }
}
