package com.cpy.saas_test.Controller;


import com.cpy.saas_test.dao.MyRowMapper;
import com.cpy.saas_test.dao.MyRowMapperForType;
import com.cpy.saas_test.element.User;
import com.cpy.saas_test.element.type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TypeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/type")
    public String typeSelectAll(Map<String , Object> map){

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from type");
        map.put("types", list);

        return "type";
    }



    //登录判断密码是否正确
    @PostMapping(value = "/type/select")
    public String loginSelect(@RequestParam("username") String UserName, @RequestParam("password") String Password, Map<String,Object> map, HttpSession session){
        User user = jdbcTemplate.queryForObject("select * from user where username = ?",new MyRowMapper(),UserName);
        if(user.getPassword().equals(Password)){
            session.setAttribute("LoginUser", UserName);
            return "redirect:/type";
        }else{
            map.put("msg", "用户名或密码错误");
            session.removeAttribute("LoginUser");

        }

        return "logindetail";
    }



    //Model model 或者 Map<String,Object> map 都可传参
    @GetMapping("/typeEditPage/{id}")
    public String typeEditPage(@PathVariable("id") String id, Model model){
        type t = jdbcTemplate.queryForObject("select * from type where id = ? ",new MyRowMapperForType(),id);
        model.addAttribute("type",t);
        return "typeEdit";
    }


    @PostMapping("/typeEdit")
    public String typeEdit(type t){
        System.out.println("获取的type:  "+t.toString());
        jdbcTemplate.update("update type set Typename = ? , `Describe` = ? , Tips = ? where id = ? ", t.getTypename(),t.getDescribe(),t.getTips(),t.getId());
        return "redirect:/type";
    }


}
