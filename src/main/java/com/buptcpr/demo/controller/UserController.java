package com.buptcpr.demo.controller;

import com.buptcpr.demo.entity.User;
import com.buptcpr.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController // This means that this class is a Controller
@RequestMapping(path="/User") // This means URL's start with /demo (after Application path)
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/save")
    public String save(){
        //实例化一个对象，添加值
        User user = new User();
        user.setName("admin");
        user.setEmail("123@qq.com");

        //保存数据
        userService.save(user);
        return "saved";
    }

    //修改
    @RequestMapping("/update")
    public String update(){
        //修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
        User user = userService.getById(1);
        userService.update(user);
        return "修改成功！";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(){
        userService.delete(3);
        return "success!";
    }

    //查询所有
    @RequestMapping("/getAll")
    public Iterable<User> getAll(){
        //查询所有的用户数据
        return userService.findAll();
    }


}