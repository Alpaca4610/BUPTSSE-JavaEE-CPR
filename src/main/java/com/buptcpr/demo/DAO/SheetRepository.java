package com.buptcpr.demo.DAO;

import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.WishSheet;
import com.buptcpr.demo.service.SheetService;
import org.apache.tomcat.websocket.WsContainerProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SheetRepository extends JpaRepository<WishSheet, String>{
    List<WishSheet> findByWishAOrWishBOrWishC(String wishA, String wishB, String wishC);
    @Query(nativeQuery = true,value = "select s.studentid,classid,s.name from student s join wish_sheet w on (s.studentid = w.studentid) where wisha = :name or wishb = :name1 or wishc = :name2")
    List<Map> getDetail(@Param("name") String name, @Param("name1") String name1, @Param("name2") String name2);

    @Modifying
    @Query(nativeQuery = true,value = "delete from wish_sheet")
    void deleteAll();

    @Query(nativeQuery = true,value = "select mainid,wisha,name1,wishb,name2,wishc,name3 from\n" +
            "((SELECT w.studentid as mainid,w.wisha,c.name as name1 FROM wish_sheet w,college c where w.wisha=c.collegeid)t1 \n" +
            " join\n" +
            "(select studentid,wishb,c.name as name2 FROM wish_sheet w,college c where w.wishb=c.collegeid)t2\n" +
            "on t1.mainid = t2.studentid)\n" +
            "join\n" +
            "(select studentid,wishc,c.name as name3 FROM wish_sheet w,college c where w.wishc=c.collegeid)t3\n" +
            "on t2.studentid = t3.studentid;")
    List<Map> getAll();

    WishSheet findByStudentID(String id);

}

