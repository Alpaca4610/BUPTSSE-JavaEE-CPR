package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
