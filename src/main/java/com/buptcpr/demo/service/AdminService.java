package com.buptcpr.demo.service;


import com.buptcpr.demo.DAO.*;
import com.buptcpr.demo.common.Result;
import com.buptcpr.demo.entity.*;
import com.buptcpr.demo.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SheetRepository sheetRepository;
    @Autowired
    private CollegeRepository collegeRepository;
    

    public int signUp(String name, String id, String password){
        Admin byAdminID = adminRepository.findByadminID(id);
        if(byAdminID==null){
            Admin admin = new Admin();
            admin.setName(name);
            admin.setAdminID(id);
            admin.setPasswd(md5Util.encode(password));
            adminRepository.save(admin);
            return 0;
        }else{
            return 1;
        }
    }

    public Boolean signIn(String id, String passwd){
        Admin byAdminID = adminRepository.findByadminID(id);
        if(byAdminID==null){
            return false;
        }else return md5Util.encode(passwd).equals(byAdminID.getPasswd());
    }

    public int delete(String id){//删
        Admin admin = adminRepository.findByadminID(id);
        if(admin == null){
            return 1;
        }else{
            adminRepository.deleteById(id);
            return 0;
        }
    }

    public int update(String id, String name, String passwd){//改
        Admin admin =adminRepository.findByadminID(id);
        if(admin == null){
            return 1;
        }else{
            adminRepository.deleteById(id);
            admin = new Admin();
            admin.setAdminID(id);
            admin.setName(name);
            admin.setPasswd(md5Util.encode(passwd));
            adminRepository.save(admin);
            return 0;
        }
    }

    public Result<String> ranking(String adminID){
        List<Student> studentList = studentRepository.findAll(Sort.by("score").descending());
        
        for(Student student:studentList){
            WishSheet sheet = sheetRepository.findByStudentID(student.getStudentID());
            College college1 = collegeRepository.findByCollegeID(sheet.getWishA());
            College college2 = collegeRepository.findByCollegeID(sheet.getWishB());
            College college3 = collegeRepository.findByCollegeID(sheet.getWishC());
            if(college1.getHeadCount()>0){
                college1.setHeadCount(college1.getHeadCount()-1);
                collegeRepository.save(college1);
                student.setMyCollege(college1.getCollegeID());

            }else if(college2.getHeadCount()>0){
                college2.setHeadCount(college2.getHeadCount()-1);
                collegeRepository.save(college2);
                student.setMyCollege(college2.getCollegeID());

            }else if(college3.getHeadCount()>0){
                college3.setHeadCount(college3.getHeadCount()-1);
                collegeRepository.save(college3);
                student.setMyCollege(college3.getCollegeID());

            }
        }
        Admin byadminID = adminRepository.findByadminID(adminID);
        byadminID.setRanked(true);
        adminRepository.save(byadminID);
        return Result.success("志愿结果统计完毕");
    }
}

