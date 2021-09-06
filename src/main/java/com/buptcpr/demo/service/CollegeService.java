package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.entity.College;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CollegeService {
    @Resource
    private CollegeRepository collegeRepository;

    //增
    @Transactional
    public College add(String id,String name,int score,int tier,int rank,String kind){
        College college = new College();
        college.setScore(score);
        college.setName(name);
        college.setTier(tier);
        college.setCrank(rank);
        college.setKind(kind);
        college.setCollegeID(id);
        collegeRepository.save(college);
        return college;
    }

    //delete
    @Transactional
    public void delete(String id){//删
        collegeRepository.deleteByCollegeID(id);
    }

    public int update(String id,String name, String kind,int tier,int score,int rank){
        College college = collegeRepository.findByCollegeID(id);
        if(college == null){
            return 1;
        }else{
            college = new College();
            college.setCollegeID(id);
            college.setCrank(rank);
            college.setName(name);
            college.setKind(kind);
            college.setTier(tier);
            college.setScore(score);
            collegeRepository.save(college);
            return 0;
        }
    }

//    //查询
//    public College getById(Integer collegeId){
//        //根据id查询对应的持久化对象
//        Optional<College> op = collegeRepository.findById(collegeId);
//        return op.get();
//    }

//    //排序查询所有
//    public Iterable<College> findAllSort(Sort sort){
//        return collegeRepository.findAll(sort);
//    }

    //分页查询所有
//    public Page<College> PageFindAll(Pageable page){
//        return collegeRepository.findAll(page);
//    }
}
