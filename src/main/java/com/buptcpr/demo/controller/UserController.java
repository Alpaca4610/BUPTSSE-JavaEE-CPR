package com.buptcpr.demo.controller;

import com.buptcpr.demo.DAO.StudentRepository;
import com.buptcpr.demo.DAO.UserRepository;
import com.buptcpr.demo.entity.Student;
import com.buptcpr.demo.entity.User;
import com.buptcpr.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@RestController // This means that this class is a Controller
@RequestMapping(path="/User") // This means URL's start with /demo (after Application path)
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserRepository userRepository;

    @RequestMapping("/save")
    public String save(@RequestParam String username, @RequestParam String email){
        //实例化一个对象，添加值
        User user = new User();
        user.setName(username);
        user.setEmail(email);

        //保存数据
        userRepository.save(user);
        return "saved";
    }

    //修改
    @RequestMapping("/update")
    @Transactional
    public String update(@RequestParam Integer id, @RequestParam String username, @RequestParam String email){
        //修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()){
            return "该用户不存在！";
        }else{
            System.out.println(users);
            users.get().setEmail(email);
            users.get().setName(username);
            System.out.println(users);
            return "修改成功！";
        }
    }

    //删除
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        userRepository.deleteById(id);
        return "success!";
    }

    //查询所有
    @RequestMapping("/getAll")
    public Iterable<User> getAll(){
        //查询所有的用户数据
        return userRepository.findAll();
    }


}