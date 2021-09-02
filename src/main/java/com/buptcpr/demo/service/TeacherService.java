package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.TeacherRepository;
import com.buptcpr.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private MD5Util md5Util;

    public Boolean SignUp(String name, String id, String passwd){
        Teacher byTeacherID = teacherRepository.findByTeacherID(id);
        if(byTeacherID==null){
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setTeacherID(id);
            teacher.setPasswd(md5Util.encode(passwd));
            teacherRepository.save(teacher);
            return true;
        }else{
            return false;
        }
    }

}

