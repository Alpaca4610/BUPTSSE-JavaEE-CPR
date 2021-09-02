package com.buptcpr.demo.service;

import com.buptcpr.demo.DAO.UserRepository;
import com.buptcpr.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //注入UserRepository
    @Resource
    private UserRepository userRepository;

    /**
     * save，update，delete方法需要绑定事务，使用@Transactional进行事务的绑定
     * 保存对象
     * @param user
     * @return 包含自动生成的id的User对象
     */
    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * 根据id删除对象
     * @param id
     */
    @Transactional
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    /**
     * 查询所有数据
     * @return
     */
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public User getById(Integer id){
        //根据id查询对应的持久化对象
        Optional<User> op = userRepository.findById(id);
        return op.get();
    }

    /**
     * 修改用户，持久化对象修改后会自动更新到数据库
     * @param user
     */
    @Transactional
    public void update(User user){
        //直接调用持久化对象的set方法修改对象的数据
        user.setEmail("909702746@qq.com");
        user.setName("yyc");

    }
}