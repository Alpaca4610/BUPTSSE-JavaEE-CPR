package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.entity.College;
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

    //save
    @Transactional
    public College save(College college){
        return collegeRepository.save(college);
    }

    //delete
    @Transactional
    public void delete(Integer collegeId){
        collegeRepository.deleteById(collegeId);
    }

    //findAll
    public Iterable<College> findAll(){
        return collegeRepository.findAll();
    }

    //update
    @Transactional
    public void update(College college) {
        //直接调用持久化对象的set方法修改对象的数据
        college.setScore(646);
        college.setName("BUPT");
        college.setTier(550);
        college.setCrank(57);
        college.setKind("理工");
    }

    //查询
    public College getById(Integer collegeId){
        //根据id查询对应的持久化对象
        Optional<College> op = collegeRepository.findById(collegeId);
        return op.get();
    }

    //排序查询所有
    public Iterable<College> findAllSort(Sort sort){
        return collegeRepository.findAll(sort);
    }

    //分页查询所有
    public Page<College> PageFindAll(Pageable page){
        return collegeRepository.findAll(page);
    }
}
