package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.AdminRepository;
import com.buptcpr.demo.common.Jwt;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Admin;
import com.buptcpr.demo.service.StatisticsService;
import com.buptcpr.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @PostMapping(path="/register")
    @SuppressWarnings("unchecked")
    public @ResponseBody
    Result<Object> adminSignUp (@RequestParam String name, @RequestParam String id, @RequestParam String password) {
        int i = adminService.signUp(name, id, password);
        if(i==1) {
            return Result.error("1","超级管理员已注册");
        }else{
            return Result.success(Jwt.createJWT(3600000));
        }
    }

    @PostMapping(path="/login")
    public @ResponseBody Result<Admin> adminSignIn(@RequestParam String id, @RequestParam String password) {
        if(adminService.signIn(id, password)) {
            return Result.success(null);
        }else{
            return Result.error("1","用户名或密码错误");
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
    public @ResponseBody Result<Admin> adminUpdate(@RequestParam String id,@RequestParam String name, @RequestParam String password)
    {
        int update = adminService.update(id, name, password);
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

    @GetMapping("/ranking")
    public @ResponseBody
    Result getRanking(){
        return adminService.ranking();
    }
}