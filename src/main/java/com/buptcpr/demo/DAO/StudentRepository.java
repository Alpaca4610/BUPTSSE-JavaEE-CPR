package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentID(String id);
    Integer countAllByClassID(String id);
    @Query(nativeQuery = true,value = "select sum(student.score) from student")
    Integer findByClassID(String id);
    Student findByStudentIDAndAndPasswd(String studentID,String password);

}
