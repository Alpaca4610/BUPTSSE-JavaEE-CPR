package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentID(String id);

//    @Query(nativeQuery = true,value = "select sum(student.score) from student")
//    Integer findByClassID(String id);
    List<Student> findAllByClassID(String id);
    Student findByStudentIDAndAndPassword(String studentID,String password);
    List<Student> findAll();
    @Query(nativeQuery = true,value = "select chinese, math, english, science from student where studentID = ?1")
    List<Map<String, Integer>> findAllScoreByStudentID(String studentID);
    @Query(nativeQuery = true,value = "select myCollege from student where studentID = ?1")
    String findMyCollegeByStudentID(String studentID);

}
