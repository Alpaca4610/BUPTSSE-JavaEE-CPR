package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByStudentID(String id);
    Integer countAllByClassID(String id);
    @Query(nativeQuery = true,value = "select sum(Student.score) from Student")
    Integer findByClassID(String id);

}
