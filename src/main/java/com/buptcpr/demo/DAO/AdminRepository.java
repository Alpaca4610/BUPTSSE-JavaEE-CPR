package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByadminID(String id);
    // sql: "select admin where id = ?"
}
