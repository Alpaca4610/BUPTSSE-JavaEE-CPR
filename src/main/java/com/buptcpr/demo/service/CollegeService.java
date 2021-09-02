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
    public College add(String name,int score,int tier,int rank,String kind){
        College college = new College();
        college.setScore(score);
        college.setName(name);
        college.setTier(tier);
        college.setCrank(rank);
        college.setKind(kind);
        return collegeRepository.save(college);
    }

    //delete
    @Transactional
    public void delete(String name){//删
        collegeRepository.deleteById(name);
    }

    public String update(String name, String passwd,String classID,int score,int rank){
        College college = collegeRepository.findByName(name);
        if(college == null){
            return "fail_id_not_exits";
        }else{
            college = new College();


            collegeRepository.save(college);
            return "Saved";
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
