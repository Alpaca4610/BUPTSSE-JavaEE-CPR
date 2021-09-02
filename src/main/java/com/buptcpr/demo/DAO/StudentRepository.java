package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    public List<Student> findBystudentID(String id);
    public Integer countAllByClassID(String id);
    @Query(value = "select sum(Student.score) from Student")
    Integer findByClassID(String id);
}
