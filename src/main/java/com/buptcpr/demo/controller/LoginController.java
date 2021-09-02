package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.service.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MD5Util md5Util;

    //todo 解决valid验证失败的问题
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String id, @RequestParam String passwd) {
        Student users = (Student) studentRepository.findBystudentID(id);
        // 如果数据库中未查到该账号:
        if (users==null) {
            return "该用户不存在";
        } else if (users.getPasswd().equals(md5Util.encode(passwd))) return "1";
        else {
            return "2";
        }
    }
}
