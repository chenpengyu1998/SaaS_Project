package com.cpy.saas_test.Controller;


import com.cpy.saas_test.dao.MyRowMapper;

import com.cpy.saas_test.element.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;



    @PostMapping("/admin/select")
    public String adminLogin(HttpSession session, Map<String,Object> map, @RequestParam("username") String UserName,@RequestParam("password") String Password) {

        User user = jdbcTemplate.queryForObject("select * from user where username = 'admin'", new MyRowMapper());
        if (user.getUsername().equals(UserName) && user.getPassword().equals(Password)) {
            session.setAttribute("LoginUser", UserName);
            return "redirect:/user";
        } else {
            map.put("msg", "管理员账户或者密码错误");
            session.removeAttribute("LoginUser");

        }


        return "adminLogin";
    }

    //查找所有的Users
    @GetMapping("/user")
    public String userSelect(Map<String,Object> map){

        List<Map<String,Object>> users =jdbcTemplate.queryForList("SELECT * FROM user");;
        map.put("users", users);
        return "user";
    }




    //1.@RequestParameter注解
    //2.request.getParameter(HTTPServletRequest)
    //3.JavaBean Java类里的元素和输入的一一对应
    @PostMapping("/userAdd")
    public String userAdd(User user){

        //U代表user
        user.setId("U"+Long.toString(System.currentTimeMillis()));

        //后期只需要保存在数据库即可
        System.out.println(user.toString());
        jdbcTemplate.update("INSERT INTO user (Id,username,password,phone,Email,Introg) VALUES(?,?,?,?,?,?)", user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail(),user.getIntrog());
        //redirect: 表示重定向到一个地址 /代表当前项目路径
        //forward:  表示转发到一个地址
        return "redirect:/user";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/userEditPage/{id}")
    public String userEditPage(@PathVariable("id") String id, Model model){
        System.out.println("id="+id);

        User user = jdbcTemplate.queryForObject("select * from user where id = ?", new MyRowMapper(),id);
        model.addAttribute("user",user);
        return "userEdit";
    }

    @PostMapping("/userEdit")
    public String userEditTouUser(User user){
        System.out.println("修改的数据"+user.toString());
        jdbcTemplate.update("update user set username = ? , phone = ? , Email = ? , Introg = ? where Id = ?",user.getUsername(),user.getPhone(),user.getEmail(),user.getIntrog(),user.getId());
        return "redirect:/user";
    }


    @GetMapping("/userDeletePage/{id}")
    public String userDelete(@PathVariable("id") String id){
        jdbcTemplate.update("delete from user where id = ?", id);
        return "redirect:/user";
    }




}
