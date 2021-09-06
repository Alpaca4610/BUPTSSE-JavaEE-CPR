package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    private StudentRepository studentRepository;
//    @Autowired
//    private TeacherRepository teacherRepository;

    public double get1Rate(String id, Integer rank1Score){
        Integer count = 0;
        Double rate;

        List<Student> studentList = studentRepository.findAllByClassID(id);
        for(Student student : studentList){
            if(student.getScore()>=rank1Score){
                count++;
            }
        }

        rate = Double.parseDouble(count.toString())/Double.parseDouble(String.valueOf(studentList.size()));
        return rate;
    }

}
