package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.TeacherRepository;
import com.buptcpr.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping(path="/teacher_sign_up")
    public @ResponseBody
    String teacherSignUp (@RequestParam String name, @RequestParam String id, @RequestParam String passwd) {
        if(teacherService.SignUp(name, id ,passwd)) {
            return "Saved";
        }else{
            return "id exists";
        }
    }

    @GetMapping(path="/teacher_sign_in")
    public @ResponseBody String teacherSignIn(@RequestParam String id, @RequestParam String passwd) {
        if(teacherService.SignIn(id, passwd)) {
            return "login successfully";
        }else{
            return "id/passwd incorrect";
        }
    }
}