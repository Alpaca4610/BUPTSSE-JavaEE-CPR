package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeRepository extends JpaRepository<College, Integer> {
    List<College> findByCrankLessThan(int srank);
    List<College> findByCrankGreaterThan(int srank);
}
