package com.cpy.saas_test.Controller;


import com.cpy.saas_test.Util.CreateTableMapper;
import com.cpy.saas_test.dao.MyRowMapper;
import com.cpy.saas_test.element.User;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CreateTableMapper createTable;

    @GetMapping("/userRegister")
    public String toRegisterPage(){
        return "register";
    }

    @PostMapping("/RegisterDetile")
    public String UserRegister(User user, @RequestParam("Spassword") String password, Map<String,Object> map, HttpSession session){
        if(!password.equals(user.getPassword())){
            System.out.println("Error password isn't equals Spassword");
            map.put("msg", "密码和确认密码不一致");
        }else {
            boolean isexist = true;
            try{
                jdbcTemplate.queryForObject("select * from user where username =?",new MyRowMapper(),user.getUsername());
            }catch (EmptyResultDataAccessException e){
                isexist=false;
            }
            if(!isexist){
                user.setId("U"+Long.toString(System.currentTimeMillis()));
                jdbcTemplate.update("INSERT INTO user (Id,username,password,phone,Email,Introg) VALUES(?,?,?,?,?,?)", user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail(),user.getIntrog());
                createTable.CreateGoods("goods_"+user.getUsername());
                createTable.CreateGoodsin("goodsin_"+user.getUsername());
                createTable.CreateGoodsout("goodsout_"+user.getUsername());
                session.setAttribute("LoginUser", user.getUsername());
                return "logindetail";
            }else{
                map.put("msg", "用户名已经存在");
            }

        }

        return "register";
    }



}
