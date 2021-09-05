package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.DAO.SheetRepository;
import com.buptcpr.demo.common.Result;
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

    public Result add(String id, String id1, String id2, String id3){
        WishSheet wishSheetbyId = sheetRepository.findByStudentID(id);
        if(wishSheetbyId==null){
            if(collegeRepository.findByCollegeID(id1)==null){
                return Result.error("1","A志愿的大学不存在！");
            }
            if(collegeRepository.findByCollegeID(id2)==null){
                return Result.error("1","B志愿的大学不存在！");
            }
            if(collegeRepository.findByCollegeID(id3)==null){
                return Result.error("1","C志愿的大学不存在！");
            }
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
            return Result.success(null);
        }else{
            return Result.error("1","该学生已经填报过了！");
        }
    }

    public String delete(String id) {
        WishSheet wishSheet = sheetRepository.findByStudentID(id);
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

        College byCollegeID1 = collegeRepository.findByCollegeID(id1);
        College byCollegeID2 = collegeRepository.findByCollegeID(id2);
        College byCollegeID3 = collegeRepository.findByCollegeID(id3);
        byCollegeID1.setCount(byCollegeID1.getCount()+1);
        byCollegeID2.setCount(byCollegeID2.getCount()+1);
        byCollegeID3.setCount(byCollegeID3.getCount()+1);

        College college1 = new College(
                byCollegeID1.getCollegeID(),byCollegeID1.getName(),byCollegeID1.getCrank()
                ,byCollegeID1.getTier(), byCollegeID1.getKind(),byCollegeID1.getScore(), byCollegeID1.getCount());
        College college2 = new College(
                byCollegeID2.getCollegeID(),byCollegeID2.getName(),byCollegeID2.getCrank()
                ,byCollegeID2.getTier(), byCollegeID2.getKind(),byCollegeID2.getScore(),byCollegeID2.getCount());
        College college3 = new College(
                byCollegeID3.getCollegeID(),byCollegeID3.getName(),byCollegeID3.getCrank()
                ,byCollegeID3.getTier(), byCollegeID3.getKind(),byCollegeID3.getScore(),byCollegeID3.getCount());

        collegeRepository.deleteByCollegeID(id1);
        collegeRepository.deleteByCollegeID(id2);
        collegeRepository.deleteByCollegeID(id3);

        collegeRepository.save(college1);
        collegeRepository.save(college2);
        collegeRepository.save(college3);



        WishSheet oldSheet=sheetRepository.findByStudentID(id);
        String oldID1=oldSheet.getWishA();
        String oldID2=oldSheet.getWishB();
        String oldID3=oldSheet.getWishC();


        College oldCollegeID1 = collegeRepository.findByCollegeID(oldID1);
        College oldCollegeID2 = collegeRepository.findByCollegeID(oldID2);
        College oldCollegeID3 = collegeRepository.findByCollegeID(oldID3);
        byCollegeID1.setCount(oldCollegeID1.getCount()-1);
        byCollegeID2.setCount(oldCollegeID2.getCount()-1);
        byCollegeID3.setCount(oldCollegeID3.getCount()-1);

        College oldCollege1 = new College(
                oldCollegeID1.getCollegeID(),oldCollegeID1.getName(),oldCollegeID1.getCrank()
                ,oldCollegeID1.getTier(), oldCollegeID1.getKind(),oldCollegeID1.getScore(), oldCollegeID1.getCount());
        College oldCollege2 = new College(
                oldCollegeID2.getCollegeID(),oldCollegeID2.getName(),oldCollegeID2.getCrank()
                ,oldCollegeID2.getTier(), oldCollegeID2.getKind(),oldCollegeID2.getScore(),oldCollegeID2.getCount());
        College oldCollege3 = new College(
                oldCollegeID3.getCollegeID(),oldCollegeID3.getName(),oldCollegeID3.getCrank()
                ,oldCollegeID3.getTier(), oldCollegeID3.getKind(),oldCollegeID3.getScore(),oldCollegeID3.getCount());

        collegeRepository.deleteByCollegeID(oldID1);
        collegeRepository.deleteByCollegeID(oldID2);
        collegeRepository.deleteByCollegeID(oldID3);

        collegeRepository.save(oldCollege1);
        collegeRepository.save(oldCollege2);
        collegeRepository.save(oldCollege3);



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
