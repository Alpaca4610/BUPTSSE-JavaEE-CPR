package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.*;
import com.buptcpr.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GetDataController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    SheetRepository sheetRepository;
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/getAdmin")
    public @ResponseBody
    List<Admin> getadmin() {
        return adminRepository.findAll();
    }

    @GetMapping("/getSheet")
    public @ResponseBody
    List<WishSheet> getsheet() {
        return sheetRepository.findAll();
    }

    @GetMapping("/getCollege")
    public @ResponseBody
    List<College> getcollege() {
        return collegeRepository.findAll();
    }


}
