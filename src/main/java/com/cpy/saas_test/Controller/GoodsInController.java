package com.cpy.saas_test.Controller;

import com.cpy.saas_test.dao.MyRowMapperForGoods;
import com.cpy.saas_test.dao.MyRowMapperForGoodsin;
import com.cpy.saas_test.dao.MyRowMapperForGoodsout;
import com.cpy.saas_test.element.Goods;
import com.cpy.saas_test.element.Goodsin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



@Controller
public class GoodsInController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/inbound")
    public String inboundPage(Model model, HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        List<Map<String, Object>> goodsins = jdbcTemplate.queryForList("select * from goodsin_"+username);
        List<Map<String, Object>> types = jdbcTemplate.queryForList("select * from type");
        model.addAttribute("goodsins", goodsins);
        model.addAttribute("types", types);
        return "inbound";
    }

    @PostMapping("/insertGoodsin")
    public String insertGoodsin(Goodsin goodsin,Model model,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        goodsin.setId("G"+Long.toString(System.currentTimeMillis()));
        jdbcTemplate.update("insert into goodsin_"+username+"(Id,Gname,Gtype,amount,date) values(?,?,?,?,?)", goodsin.getId(),goodsin.getGname(),goodsin.getGtype(),goodsin.getAmount(),goodsin.getDate());
        List<Goods> goods = jdbcTemplate.query("select * from goods where Gname=?", new MyRowMapperForGoods(), goodsin.getGname());
        if(goods.size()>0){
            jdbcTemplate.update("update goods_"+username+" set amount=? where Id = ? ", goods.get(0).getAmount()+goodsin.getAmount(),goods.get(0).getId());
        }else{
            jdbcTemplate.update("insert into goods_"+username+"(Id, Gname,Gtype,amount)  values(?,?,?,?)", goodsin.getId(),goodsin.getGname(),goodsin.getGtype(),goodsin.getAmount());
        }
        return "redirect:/inbound";
    }

    @GetMapping("/inboundEditPage/{Id}")
    public String inboundEditPage(Model model , @PathVariable(value = "Id") String Id,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goodsin goodsin = (Goodsin) jdbcTemplate.queryForObject("select * from goodsin_"+username+" where Id = ?", new MyRowMapperForGoodsin(), Id);
        model.addAttribute("goods", goodsin);
        List<Map<String, Object>> types = jdbcTemplate.queryForList("select * from type");
        model.addAttribute("types", types);
        return "inboundEdit";
    }

    @PostMapping("/GoodsinEdit")
    public String GoodsinEdit(Goodsin goodsin ,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goodsin temp = (Goodsin) jdbcTemplate.queryForObject("select * from goodsin_"+username+" where Id = ?", new MyRowMapperForGoodsin(), goodsin.getId());
        System.out.println(temp.toString());
        Goods goods = jdbcTemplate.queryForObject("select * from goods_"+username+" where Gname = ?", new MyRowMapperForGoods(), temp.getGname());
        System.out.println(goods.toString());
        jdbcTemplate.update("update goodsin_"+username+" set Gtype=?,amount=?  where Id = ?",goodsin.getGtype(),goodsin.getAmount(),goodsin.getId());
        jdbcTemplate.update("update goods_"+username+" set amount=? where Id = ? ",goods.getAmount()+(goodsin.getAmount()-temp.getAmount()),goods.getId());
        return "redirect:/inbound";
    }

    @GetMapping("/inboundDelete/{Id}")
    public String GoodsinDelete(@PathVariable("Id") String Id,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goodsin goodsin = (Goodsin) jdbcTemplate.queryForObject("select * from goodsin_"+username+" where Id = ?", new MyRowMapperForGoodsin(), Id);
        Goods goods = jdbcTemplate.queryForObject("select * from goods_"+username+" where Gname = ?", new MyRowMapperForGoods(), goodsin.getGname());
        jdbcTemplate.update("update goods_"+username+" set amount=? where Id = ? ",goods.getAmount()-goodsin.getAmount(),goods.getId());
        jdbcTemplate.update("delete from goodsin_"+username+" where Id = ?", Id);
        return "redirect:/inbound";
    }



}
