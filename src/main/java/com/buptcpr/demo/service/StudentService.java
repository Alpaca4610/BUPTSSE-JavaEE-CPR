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

    // 学生注册
    public String register(String studentID, String name, String passwd){
        Student student = studentRepository.findBystudentID(studentID).get(0);
        if(student != null){
            return "注册失败, 账户中已有该学生的信息";
        }else{
            student = new Student();
            student.setStudentID(studentID);
            student.setName(name);
            student.setPasswd(passwd);
            System.out.println("studentID: " + studentID);
            studentRepository.save(student);
            return "Saved";
        }

    }



}
