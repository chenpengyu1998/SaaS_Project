package com.cpy.saas_test.Controller;


import com.cpy.saas_test.dao.MyRowMapper;
import com.cpy.saas_test.dao.Select;
import com.cpy.saas_test.element.User;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginSelectController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    //@RequestMapping(value = "/type/select" ,method = RequestMethod.POST)
    @PostMapping(value = "/type/select")
    public String loginSelect(@RequestParam("username") String UserName, @RequestParam("password") String Password, Map<String,Object> map, HttpSession session){
        User user = jdbcTemplate.queryForObject("select * from user where username = ?",new MyRowMapper(),UserName);
        if(user.getPassword().equals(Password)){
            session.setAttribute("LoginUser", UserName);
            return "type";
        }else{
            map.put("msg", "用户名或密码错误");
            session.removeAttribute("LoginUser");

        }

        return "logindetail";
    }


}
