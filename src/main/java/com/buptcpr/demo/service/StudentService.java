package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.common.Result;
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

    // 学生登陆
    public Student login(String name,String password) {
        return studentRepository.findByStudentIDAndAndPassword(name,md5Util.encode(password));
    }

    // 学生注册（增）
    public int register(String studentID, String name, String password, String classID){
        Student student = studentRepository.findByStudentID(studentID);
        if(student != null){ //学生已注册
            return 1;
        }else{ //学生未注册
            student = new Student();
            student.setStudentID(studentID);
            student.setName(name);
            student.setPassword(md5Util.encode(password));
            student.setClassID(classID);
            System.out.println("studentID: " + studentID);
            studentRepository.save(student);
            return 0;
        }
    }

    //删除学生
    public void delete(String studentID){
        studentRepository.deleteById(studentID);
    }

    //修改学生信息
    public int update(String studentID, String name, String password,String classID,int score,int rank){
        Student student = (Student) studentRepository.findByStudentID(studentID);
        if(student == null){
            return 1;
        }else{
            student = new Student();
            student.setStudentID(studentID);
            student.setName(name);
            student.setPassword(md5Util.encode(password));
            student.setMyRank(rank);
            student.setScore(score);
            student.setClassID(classID);

            studentRepository.save(student);
            return 0;
        }
    }

    // 插入学生
    public int insert(String studentID, String name, String password,String classID,int score){
        Student student = (Student) studentRepository.findByStudentID(studentID);
        if(student != null){
            return 1;
        }else{
            student = new Student();
            student.setStudentID(studentID);
            student.setName(name);
            student.setPassword(md5Util.encode(password));

            student.setScore(score);
            student.setClassID(classID);

            studentRepository.save(student);
            return 0;
        }
    }
}
