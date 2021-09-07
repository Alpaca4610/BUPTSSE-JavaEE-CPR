package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.ClassRepository;
import com.buptcpr.demo.DAO.TeacherRepository;
import com.buptcpr.demo.entity.Class;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private ClassRepository classRepository;

    public boolean signUp(String name, String id, String password,String classid){
        Teacher byTeacherID = teacherRepository.findByTeacherID(id);
        if(byTeacherID==null){
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setTeacherID(id);
            teacher.setPasswd(md5Util.encode(password));
            teacher.setClassID(classid);
            teacherRepository.save(teacher);
            return true;
        }else{
            return false;
        }
    }

    public Boolean signIn(String id, String password){
        Teacher byTeacherID = teacherRepository.findByTeacherID(id);
        if(byTeacherID==null){
            return false;
        }else return md5Util.encode(password).equals(byTeacherID.getPasswd());
    }

    public String createClass(String id){
        Optional<Class> byClassId = classRepository.findById(id);
        if(byClassId.isEmpty()){
            //创建班级
            Class c=new Class();
            c.setClassID(id);
            classRepository.save(c);
            return"saved";
        }else{
            //已存在
            return "class_exists";
        }
    }

    public int delete(String id){//删
        Teacher teacher = teacherRepository.findByTeacherID(id);
        if(teacher == null){
            return 1;
        }else{
            teacherRepository.deleteById(id);
            return 0;
        }
    }

    public int update(String id, String name, String password,String classID){//改
        Teacher teacher =teacherRepository.findByTeacherID(id);
        if(teacher == null){
            return 1;
        }else{
            teacher = new Teacher();
            teacher.setTeacherID(id);
            teacher.setName(name);
            teacher.setPasswd(md5Util.encode(password));
            teacher.setClassID(classID);

            teacherRepository.save(teacher);
            return 0;
        }
    }
}

