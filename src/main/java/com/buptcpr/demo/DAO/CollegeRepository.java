package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.StyledEditorKit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface CollegeRepository extends JpaRepository<College, String> {
    List<College> findByCrankLessThan(int srank);
    List<College> findByCrankGreaterThan(int srank);

    @Query(nativeQuery = true,value = "select name,count_selected from college")
    List<Map> getAllCollegeName();

    @Query(nativeQuery = true,value = "select name collegeName,collegeid collegeID from college")
    List<Map> getbrief();

    @Query(nativeQuery = true,value = "select CollegeID, score from college where score >= ?1 and score <= ?2")
    List<Map> getIntervalList(int min, int max);

    @Modifying
    @Query(nativeQuery = true,value = "update college set count_selected = 0")
    void clearCount();

    College findByName(String name);

    College findByCollegeID(String id);

    void deleteByCollegeID(String s);

}
