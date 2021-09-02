package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class StudentService {
    @Resource
    private StudentRepository studentRepository;
    @Autowired
    private MD5Util md5Util;

//    // 学生登陆
//    public Student login(String studentID, String passwd){
//        System.out.println("StudentId: " + studentID + ", passwd: " + passwd);
//        ArrayList<Student> student = (ArrayList<Student>) studentRepository.findBystudentID(studentID);
//        if(!student.getPasswd().equals(passwd)){
//            System.out.println("Service return null");
//            return null;
//        }
//        System.out.println("Service return student");
//        return student;
//    }

    // 学生注册（增）
    public String register(String studentID, String name, String passwd,String classID){
        Student student = (Student) studentRepository.findByStudentID(studentID);
        if(student != null){
            return "fail_id_exits";
        }else{
            student = new Student();
            student.setStudentID(studentID);
            student.setName(name);
            student.setPasswd(md5Util.encode(passwd));
            student.setClassID(classID);
            System.out.println("studentID: " + studentID);
            studentRepository.save(student);
            return "Saved";
        }
    }

    //删除学生
    public String delete(String studentID){
        Student student = (Student) studentRepository.findByStudentID(studentID);
        if(student == null){
            return "id_not_exits";
        }else{
            studentRepository.deleteById(studentID);
            return "success";
        }
    }

    //修改学生信息
    public String update(String studentID, String name, String passwd,String classID,int score,int rank){
        Student student = (Student) studentRepository.findByStudentID(studentID);
        if(student == null){
            return "fail_id_not_exits";
        }else{
            student = new Student();
            student.setStudentID(studentID);
            student.setName(name);
            student.setPasswd(md5Util.encode(passwd));
            student.setMyrank(rank);
            student.setScore(score);
            student.setClassID(classID);

            studentRepository.save(student);
            return "Saved";
        }
    }
}
