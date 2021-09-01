package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.WishSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SheetRepository extends JpaRepository<WishSheet, String>{
}

