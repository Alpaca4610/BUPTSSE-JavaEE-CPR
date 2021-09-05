package com.buptcpr.demo.service;


import com.buptcpr.demo.DAO.ClassRepository;
import com.buptcpr.demo.DAO.AdminRepository;
import com.buptcpr.demo.entity.Class;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private ClassRepository classRepository;

    public int signUp(String name, String id, String passwd){
        Admin byAdminID = adminRepository.findByadminID(id);
        if(byAdminID==null){
            Admin admin = new Admin();
            admin.setName(name);
            admin.setAdminID(id);
            admin.setPasswd(md5Util.encode(passwd));
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
}

