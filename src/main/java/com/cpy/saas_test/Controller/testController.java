package com.cpy.saas_test.Controller;

import com.cpy.saas_test.dao.MyRowMapper;
import com.cpy.saas_test.element.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class testController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> query(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        return list.get(0);
    }

    @ResponseBody
    @GetMapping("/querys")
    public User querys(){
        String username = "asd";
        User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE username = ?",new MyRowMapper(), username);

        return user;
    }




}
