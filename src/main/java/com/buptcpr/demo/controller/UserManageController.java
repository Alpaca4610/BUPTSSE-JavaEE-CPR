//package com.buptcpr.demo.controller;
//
//import com.buptcpr.demo.DAO.UserRepository;
//import com.buptcpr.demo.entity.User;
//import com.buptcpr.demo.service.UserService;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path="/User")
//public class UserManageController {
//
//    @Resource
//    private UserService userService;
//    @Resource
//    private UserRepository userRepository;
//
//
//    //修改
//    @RequestMapping("/update")
//    @Transactional
//    public String update(@RequestParam Integer id, @RequestParam String username, @RequestParam String email){
//        //修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
//        Optional<User> users = userRepository.findById(id);
//        if(users.isEmpty()){
//            return "该用户不存在！";
//        }else{
//            System.out.println(users);
//            users.get().setEmail(email);
//            users.get().setName(username);
//            System.out.println(users);
//            return "修改成功！";
//        }
//
//
//    }
//
//    //删除
//    @RequestMapping("/delete")
//    public String delete(@RequestParam Integer id){
//        userRepository.deleteById(id);
//        return "success!";
//    }
//
//    //查询所有
//    @RequestMapping("/getAll")
//    public Iterable<User> getAll(){
//        //查询所有的用户数据
//        return userRepository.findAll();
//    }
//
//
//}
