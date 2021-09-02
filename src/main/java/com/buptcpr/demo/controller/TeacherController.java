package com.buptcpr.demo.controller;

import com.buptcpr.demo.service.StatisticsService;
import com.buptcpr.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StatisticsService statisticsService;

    @PostMapping(path="/login")
    public @ResponseBody
    String teacherSignUp (@RequestParam String name, @RequestParam String id, @RequestParam String passwd) {
        if(teacherService.signUp(name, id ,passwd)) {
            return "Saved";
        }else{
            return "id exists";
        }
    }

    @GetMapping(path="/register")
    public @ResponseBody String teacherSignIn(@RequestParam String id, @RequestParam String passwd) {
        if(teacherService.signIn(id, passwd)) {
            return "login successfully";
        }else{
            return "id/passwd incorrect";
        }
    }

    @GetMapping(path = "/create_class")
    public @ResponseBody String createClass(@RequestParam String id){
        String result = teacherService.createClass(id);
        if(result.equals("saved")){
            return "saved";
        }else{
            return "class_exists";
        }
    }

    @GetMapping(path = "/get_1_Rate")
    public @ResponseBody float get1Rate(@RequestParam String id){
        return statisticsService.get1Rate(id);
    }
}