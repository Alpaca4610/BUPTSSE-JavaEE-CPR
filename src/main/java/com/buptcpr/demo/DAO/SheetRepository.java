package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.WishSheet;
import com.buptcpr.demo.service.SheetService;
import org.apache.tomcat.websocket.WsContainerProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SheetRepository extends JpaRepository<WishSheet, String>{
    List<WishSheet> findByWishAOrWishBOrWishC(String wishA, String wishB, String wishC);
    @Query(nativeQuery = true,value = "select s.studentid,classid,s.name from student s join wish_sheet w on (s.studentid = w.studentid) where wisha = :name or wishb = :name1 or wishc = :name2")
    List<Map> getDetail(@Param("name") String name, @Param("name1") String name1, @Param("name2") String name2);

    @Query(nativeQuery = true,value = "delete from wish_sheet")
    void deleteAll();

    WishSheet findByStudentID(String id);
    Optional<WishSheet> findById(String id);

}

