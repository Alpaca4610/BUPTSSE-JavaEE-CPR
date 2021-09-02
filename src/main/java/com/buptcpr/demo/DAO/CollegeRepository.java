package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface CollegeRepository extends JpaRepository<College, String> {
    List<College> findByCrankLessThan(int srank);
    List<College> findByCrankGreaterThan(int srank);
    College findByName(String name);
    College findByCollegeID(String id);

    void deleteByCollegeID(String s);
}
