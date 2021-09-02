package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.entity.WishSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SheetService {

    @Autowired
    private SheetRepository sheetRepository;
    @Autowired
    private CollegeRepository collegeRepository;

    public String add(String id, String id1, String id2, String id3){
        WishSheet wishSheetbyId = sheetRepository.findbyId(id);
        if(wishSheetbyId==null){
            WishSheet wishSheet = new WishSheet();
            wishSheet.setStudentID(id);
            wishSheet.setWishA(id1);
            wishSheet.setWishB(id2);
            wishSheet.setWishC(id3);
            College byCollegeID1 = collegeRepository.findByCollegeID(id1);
            College byCollegeID2 = collegeRepository.findByCollegeID(id2);
            College byCollegeID3 = collegeRepository.findByCollegeID(id3);
            College college1 = new College(
            byCollegeID1.getCollegeID(),byCollegeID1.getName(),byCollegeID1.getCrank()
            ,byCollegeID1.getTier(), byCollegeID1.getKind(),byCollegeID1.getScore(), byCollegeID1.getCount()+1);
            College college2 = new College(
            byCollegeID2.getCollegeID(),byCollegeID2.getName(),byCollegeID2.getCrank()
            ,byCollegeID2.getTier(), byCollegeID2.getKind(),byCollegeID2.getScore(),byCollegeID2.getCount()+1);
            College college3 = new College(
            byCollegeID3.getCollegeID(),byCollegeID3.getName(),byCollegeID3.getCrank()
            ,byCollegeID3.getTier(), byCollegeID3.getKind(),byCollegeID3.getScore(),byCollegeID3.getCount()+1);
            collegeRepository.deleteById(id1);
            collegeRepository.deleteById(id2);
            collegeRepository.deleteById(id3);
            collegeRepository.save(college1);
            collegeRepository.save(college2);
            collegeRepository.save(college3);
            sheetRepository.save(wishSheet);
            return "saved";
        }else{
            return "sheet_exists";
        }
    }

    public String delete(String id) {
        WishSheet wishSheet = sheetRepository.findbyId(id);
        if(wishSheet==null){
            return "not_exists";
        }else{
            String id1=wishSheet.getWishA();
            String id2=wishSheet.getWishB();
            String id3=wishSheet.getWishC();
            College byCollegeID1 = collegeRepository.findByCollegeID(id1);
            College byCollegeID2 = collegeRepository.findByCollegeID(id2);
            College byCollegeID3 = collegeRepository.findByCollegeID(id3);
            College college1 = new College(
                    byCollegeID1.getCollegeID(),byCollegeID1.getName(),byCollegeID1.getCrank()
                    ,byCollegeID1.getTier(), byCollegeID1.getKind(),byCollegeID1.getScore(), byCollegeID1.getCount()-1);
            College college2 = new College(
                    byCollegeID2.getCollegeID(),byCollegeID2.getName(),byCollegeID2.getCrank()
                    ,byCollegeID2.getTier(), byCollegeID2.getKind(),byCollegeID2.getScore(),byCollegeID2.getCount()-1);
            College college3 = new College(
                    byCollegeID3.getCollegeID(),byCollegeID3.getName(),byCollegeID3.getCrank()
                    ,byCollegeID3.getTier(), byCollegeID3.getKind(),byCollegeID3.getScore(),byCollegeID3.getCount()-1);
            collegeRepository.deleteById(id1);
            collegeRepository.deleteById(id2);
            collegeRepository.deleteById(id3);
            collegeRepository.save(college1);
            collegeRepository.save(college2);
            collegeRepository.save(college3);
            sheetRepository.deleteById(id);
            return "deleted";
        }

    }

    public String update(String id, String id1, String id2, String id3) {
        WishSheet wishSheetbyId = sheetRepository.findbyId(id);
        College byCollegeID1 = collegeRepository.findByCollegeID(id1);
        College byCollegeID2 = collegeRepository.findByCollegeID(id2);
        College byCollegeID3 = collegeRepository.findByCollegeID(id3);
        if(wishSheetbyId==null){
            return "not_exists";
        }else{
            String old_id1=wishSheetbyId.getWishA();
            String old_id2=wishSheetbyId.getWishB();
            String old_id3=wishSheetbyId.getWishC();
            if(old_id1.equals(id1)){

            }else{

            }
            if(old_id2.equals(id2)){

            }else{

            }
            if(old_id3.equals(id3)){

            }else{

            }

            WishSheet wishSheet = new WishSheet();
            wishSheet.setStudentID(id);
            wishSheet.setStudentID(id1);
            wishSheet.setStudentID(id2);
            wishSheet.setStudentID(id3);
            sheetRepository.deleteById(id);
            sheetRepository.save(wishSheet);
            return "saved";
        }
    }
}
