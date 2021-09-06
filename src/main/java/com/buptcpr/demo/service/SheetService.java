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
            byCollegeID1.setCountSelected(byCollegeID1.getCountSelected()+1);
            byCollegeID2.setCountSelected(byCollegeID2.getCountSelected()+1);
            byCollegeID3.setCountSelected(byCollegeID3.getCountSelected()+1);

            collegeRepository.save(byCollegeID1);
            collegeRepository.save(byCollegeID2);
            collegeRepository.save(byCollegeID3);
            sheetRepository.save(wishSheet);
            return Result.success(null);
        }else{
            return Result.error("1","该学生已经填报过了！");
        }
    }

    public Result delete(String id) {
        WishSheet wishSheet = sheetRepository.findByStudentID(id);
        if(wishSheet==null){
            return Result.error("1","not exists");
        }else{

            String id1=wishSheet.getWishA();
            String id2=wishSheet.getWishB();
            String id3=wishSheet.getWishC();
            College byCollegeID1 = collegeRepository.findByCollegeID(id1);
            College byCollegeID2 = collegeRepository.findByCollegeID(id2);
            College byCollegeID3 = collegeRepository.findByCollegeID(id3);

            byCollegeID1.setCountSelected(byCollegeID1.getCountSelected()-1);
            byCollegeID2.setCountSelected(byCollegeID2.getCountSelected()-1);
            byCollegeID3.setCountSelected(byCollegeID3.getCountSelected()-1);

            collegeRepository.save(byCollegeID1);
            collegeRepository.save(byCollegeID2);
            collegeRepository.save(byCollegeID3);
            sheetRepository.deleteById(id);
            return Result.success(null);
        }

    }

    public Result update(String id, String id1, String id2, String id3) {

        if(collegeRepository.findByCollegeID(id1)==null){
            return Result.error("1","A志愿的大学不存在！");
        }
        if(collegeRepository.findByCollegeID(id2)==null){
            return Result.error("1","B志愿的大学不存在！");
        }
        if(collegeRepository.findByCollegeID(id3)==null){
            return Result.error("1","C志愿的大学不存在！");
        }

        College byCollegeID1 = collegeRepository.findByCollegeID(id1);
        College byCollegeID2 = collegeRepository.findByCollegeID(id2);
        College byCollegeID3 = collegeRepository.findByCollegeID(id3);
        byCollegeID1.setCountSelected(byCollegeID1.getCountSelected()+1);
        byCollegeID2.setCountSelected(byCollegeID2.getCountSelected()+1);
        byCollegeID3.setCountSelected(byCollegeID3.getCountSelected()+1);

        collegeRepository.save(byCollegeID1);
        collegeRepository.save(byCollegeID2);
        collegeRepository.save(byCollegeID3);



        WishSheet oldSheet=sheetRepository.findByStudentID(id);
        String oldID1=oldSheet.getWishA();
        String oldID2=oldSheet.getWishB();
        String oldID3=oldSheet.getWishC();

        College oldCollegeID1 = collegeRepository.findByCollegeID(oldID1);
        College oldCollegeID2 = collegeRepository.findByCollegeID(oldID2);
        College oldCollegeID3 = collegeRepository.findByCollegeID(oldID3);

        oldCollegeID1.setCountSelected(oldCollegeID1.getCountSelected()-1);
        oldCollegeID2.setCountSelected(oldCollegeID2.getCountSelected()-1);
        oldCollegeID3.setCountSelected(oldCollegeID3.getCountSelected()-1);

        collegeRepository.save(oldCollegeID1);
        collegeRepository.save(oldCollegeID2);
        collegeRepository.save(oldCollegeID3);

        WishSheet wishSheet = new WishSheet();
            wishSheet.setStudentID(id);
            wishSheet.setWishA(id1);
            wishSheet.setWishB(id2);
            wishSheet.setWishC(id3);
            sheetRepository.save(wishSheet);
            return Result.success(null);
    }
}
