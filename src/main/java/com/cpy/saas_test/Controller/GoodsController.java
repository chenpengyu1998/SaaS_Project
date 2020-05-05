package com.cpy.saas_test.Controller;


import com.cpy.saas_test.dao.MyRowMapper;
import com.cpy.saas_test.dao.MyRowMapperForGoods;
import com.cpy.saas_test.element.Goods;
import com.cpy.saas_test.element.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {
    @Autowired
    JdbcTemplate jdbcTemplate;



    @GetMapping("/inventory")
    public String selectGoods(Map<String,Object> map,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        List<Map<String, Object>> goods=  jdbcTemplate.queryForList("select * from goods_"+username);
        List<Map<String, Object>> types = jdbcTemplate.queryForList("select * from type");
        map.put("goods", goods);
        map.put("types", types);
        return "inventory";
    }

    @GetMapping("/inventoryEditPage/{id}")
    public String inventoryEdit(HttpServletRequest request,Model model, @PathVariable(value = "id") String id){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goods goods = jdbcTemplate.queryForObject("select * from ? where Id = ?", new MyRowMapperForGoods(),"goods_"+username,id);
        List<Map<String, Object>> types = jdbcTemplate.queryForList("select * from type");
        model.addAttribute("goods", goods);
        model.addAttribute("types", types);
        return "inventoryEdit";
    }

    @PostMapping("/GoodsEdit")
    public String GoodsEdit(Goods goods,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        jdbcTemplate.update("update goods_"+username+" set Gname=?,Gtype=?,amount=?  where Id = ?", goods.getGname(),goods.getGtype(),goods.getAmount(),goods.getId());
        return "redirect:/inventory";
    }


    @GetMapping("/inventoryDelete/{id}")
    public String inventoryDelete(@PathVariable(value ="id") String id,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        jdbcTemplate.update("delete from goods_"+username+" where Id = ?", id);
        return "redirect:/inventory";
    }
    @PostMapping("/inventoryCreate")
    public String inventoryCreate(Goods goods, Model model,HttpServletRequest request){
//        System.out.println(goods.toString());
        String username = (String)request.getSession().getAttribute("LoginUser");
        jdbcTemplate.update("insert into goods_"+username+"(Id, Gname,Gtype,amount)  values(?,?,?,?)", "G"+Long.toString(System.currentTimeMillis()),goods.getGname(),goods.getGtype(),goods.getAmount());
        return "redirect:/inventory";
    }

}
