package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByadminID(String id);
    @Query(nativeQuery = true,value = "select ranked from admin where adminID = ?1")
    Boolean findRankedByAdminID(String adminID);
}
