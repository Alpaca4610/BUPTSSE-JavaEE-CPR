package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentID(String id);

//    @Query(nativeQuery = true,value = "select sum(student.score) from student")
//    Integer findByClassID(String id);
    List<Student> findAllByClassID(String id);
    Student findByStudentIDAndAndPassword(String studentID,String password);

    List<Student> findAll();
}
