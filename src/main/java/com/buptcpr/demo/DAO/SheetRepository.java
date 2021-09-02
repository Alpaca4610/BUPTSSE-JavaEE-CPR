package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.WishSheet;
import com.buptcpr.demo.service.SheetService;
import org.apache.tomcat.websocket.WsContainerProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SheetRepository extends JpaRepository<WishSheet, String>{
    List<WishSheet> findByWishAOrWishBOrWishC(String wishA, String wishB, String wishC);

    WishSheet findbyId(String id);

}

