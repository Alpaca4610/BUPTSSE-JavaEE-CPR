package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeRepository extends JpaRepository<College, String> {
    List<College> findByCrankLessThan(int srank);
    List<College> findByCrankGreaterThan(int srank);
    College findByName(String name);
}
