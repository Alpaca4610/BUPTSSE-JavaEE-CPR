package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.CollegeRepository;
import com.buptcpr.demo.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CollegeService {
    @Resource
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeService collegeService;

    //增
    @Transactional
    public College add(String id, String name, int score, int tier, int rank, int prank, String kind) {
        College college = new College();
        college.setScore(score);
        college.setName(name);
        college.setTier(tier);
        college.setCrank(rank);
        college.setKind(kind);
        college.setCollegeID(id);
        college.setPrank(prank);
        collegeRepository.save(college);
        return college;
    }

    //delete
    @Transactional
    public void delete(String id) {//删
        collegeRepository.deleteByCollegeID(id);
    }

    public int update(String id, String name, String kind, int tier, int score, int rank1, int rank2, int rank3, int rank4) {
        College college = collegeRepository.findByCollegeID(id);
        if (college == null) {
            return 1;
        } else {
            Integer[] list = new Integer[4];
            list[0] = rank1;
            list[1] = rank2;
            list[2] = rank3;
            list[3] = rank4;
            college = new College();
            college.setCollegeID(id);
            college.setCrank(rank1);
            college.setPrank(collegeService.Avg(list));
            college.setName(name);
            college.setKind(kind);
            college.setTier(tier);
            college.setScore(score);
            collegeRepository.save(college);
            return 0;
        }

    }

    public List<Map> getIntervalList(int min, int max) {
        return collegeRepository.getIntervalList(min, max);
    }

    public Integer Avg(Integer[] ScoreSet) {
        int highest = 0;
        int Lowest = Integer.MAX_VALUE;
        int res = 0;

        for (Integer a : ScoreSet) {
            res += a;
            if (highest < a)
                highest = a;
            if (Lowest > a)
                Lowest = a;
        }
        res-=highest;
        res-=Lowest;
        return res/2;
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
