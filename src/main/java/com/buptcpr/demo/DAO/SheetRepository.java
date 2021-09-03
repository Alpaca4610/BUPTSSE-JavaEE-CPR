package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.WishSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SheetRepository extends JpaRepository<WishSheet, String>{
    List<WishSheet> findByWishAOrWishBOrWishC(String wishA, String wishB, String wishC);

    WishSheet findByStudentID(String id);
}

