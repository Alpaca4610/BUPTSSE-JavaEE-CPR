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

    public Boolean signUp(String name, String id, String passwd){
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

    public Boolean signIn(String id, String passwd){
        Teacher byTeacherID = teacherRepository.findByTeacherID(id);
        if(byTeacherID==null){
            return false;
        }else return md5Util.encode(passwd).equals(byTeacherID.getPasswd());
    }

    public String createClass(String id){
        Optional<Class> byClassId = classRepository.findById(id);
        if(!byClassId.isPresent()){
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

    public String delete(String id){//删
        Teacher teacher = teacherRepository.findByTeacherID(id);
        if(teacher == null){
            return "id_not_exits";
        }else{
            teacherRepository.deleteById(id);
            return "success";
        }
    }

    public String update(String id, String name, String passwd,String classID){//改
        Teacher teacher =teacherRepository.findByTeacherID(id);
        if(teacher == null){
            return "fail_id_not_exits";
        }else{
            teacher = new Teacher();
            teacher.setTeacherID(id);
            teacher.setName(name);
            teacher.setPasswd(md5Util.encode(passwd));
            teacher.setClassID(classID);

            teacherRepository.save(teacher);
            return "Saved";
        }
    }
}

