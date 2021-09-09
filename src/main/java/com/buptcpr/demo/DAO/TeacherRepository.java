package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findByTeacherID(String TeacherID);
    @Query(nativeQuery = true,value = "select name from teacher where TeacherID = ?1")
    List<Map> findNameByTeacherID(String TeacherID);
}
