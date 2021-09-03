package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.AdminRepository;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Admin;
import com.buptcpr.demo.service.StatisticsService;
import com.buptcpr.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path="/admin")
@SuppressWarnings("unchecked")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping(path="/login")
    @SuppressWarnings("unchecked")
    public @ResponseBody
    Result<Object> adminSignUp (@RequestParam String name, @RequestParam String id, @RequestParam String passwd) {
        int i = adminService.signUp(name, id, passwd);
        if(i==1) {
            return Result.error("1","用户名或密码错误");
        }else{
            return Result.success(null);
        }
    }

    @GetMapping(path="/register")
    public @ResponseBody Result<Admin> adminSignIn(@RequestParam String id, @RequestParam String passwd) {
        if(adminService.signIn(id, passwd)) {
            return Result.success(null);
        }else{
            return Result.error("1","教师已注册");
        }
    }


    @PostMapping(path="/delete")
    public @ResponseBody Result<String> adminDelete(@RequestParam String id) {
        int delete = adminService.delete(id);
        if(delete==1){
            return Result.error("1","用户名不存在");
        }else{
            return Result.success(null);
        }
    }

    @PostMapping(path="/update")
    public @ResponseBody Result<Admin> studentDelete(@RequestParam String id,@RequestParam String name, @RequestParam String passwd,@RequestParam String classID)
    {
        int update = adminService.update(id, name, passwd, classID);
        if(update==1){
            return Result.error("1","用户名不存在");
        }else {
            return Result.success(null);
        }
    }

    @GetMapping("/all")
    public @ResponseBody
    Result<List<Admin>> getadmin() {
        return Result.success(adminRepository.findAll());
    }
}