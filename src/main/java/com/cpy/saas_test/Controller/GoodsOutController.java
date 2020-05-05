package com.cpy.saas_test.Controller;


import com.cpy.saas_test.dao.MyRowMapperForGoods;
import com.cpy.saas_test.dao.MyRowMapperForGoodsin;
import com.cpy.saas_test.dao.MyRowMapperForGoodsout;
import com.cpy.saas_test.element.Goods;
import com.cpy.saas_test.element.Goodsout;
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
public class GoodsOutController {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping("/outbound")
    public String outboundPage(Model model, HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        List<Map<String, Object>> goodsouts = jdbcTemplate.queryForList("select * from goodsout_"+username);
        List<Map<String, Object>> types = jdbcTemplate.queryForList("select * from type");
        model.addAttribute("goodsouts", goodsouts);
        model.addAttribute("types", types);
        return "outbound";
    }



    @PostMapping("/insertGoodsout")
    public String insertGoodsin(Goodsout goodsout, Model model, HttpServletRequest request){
        goodsout.setId("G"+Long.toString(System.currentTimeMillis()));

//        System.out.println(goodsout.toString());

        String username = (String)request.getSession().getAttribute("LoginUser");
        jdbcTemplate.update("insert into goodsout_"+username+"(Id,Gname,Gtype,amount,OutDate) values(?,?,?,?,?)", goodsout.getId(),goodsout.getGname(),goodsout.getGtype(),goodsout.getAmount(),goodsout.getOutDate());
        List<Goods> goods = jdbcTemplate.query("select * from goods_"+username+" where Gname=?", new MyRowMapperForGoods(), goodsout.getGname());
        if(goods.size()>0){
            jdbcTemplate.update("update goods_"+username+" set amount=? where Id = ? ", goods.get(0).getAmount()-goodsout.getAmount(),goods.get(0).getId());
        }else{
            jdbcTemplate.update("insert into goods_"+username+"(Id, Gname,Gtype,amount)  values(?,?,?,?)", goodsout.getId(),goodsout.getGname(),goodsout.getGtype(),0-goodsout.getAmount());
        }
        return "redirect:/outbound";
    }

    @GetMapping("/outboundEditPage/{Id}")
    public String outboundEditPage(Model model , @PathVariable(value = "Id") String Id,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goodsout goodsout = (Goodsout) jdbcTemplate.queryForObject("select * from goodsout_"+username+" where Id = ?", new MyRowMapperForGoodsout(), Id);
        model.addAttribute("goods", goodsout);
        List<Map<String, Object>> types = jdbcTemplate.queryForList("select * from type");
        model.addAttribute("types", types);
        return "outboundEdit";
    }

    @PostMapping("/GoodsoutEdit")
    public String GoodsinEdit(Goodsout goodsout,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goodsout temp = (Goodsout) jdbcTemplate.queryForObject("select * from goodsout_"+username+" where Id = ?", new MyRowMapperForGoodsout(), goodsout.getId());
        System.out.println(temp.toString());
        Goods goods = jdbcTemplate.queryForObject("select * from goods_"+username+" where Gname = ?", new MyRowMapperForGoods(), temp.getGname());
        System.out.println(goods.toString());
        jdbcTemplate.update("update goodsout_"+username+" set Gtype=?,amount=?  where Id = ?", goodsout.getGtype(),goodsout.getAmount(),goodsout.getId());
        jdbcTemplate.update("update goods_"+username+" set amount=? where Id = ? ",goods.getAmount()-(goodsout.getAmount()-temp.getAmount()),goods.getId());
        return "redirect:/outbound";
    }

    @GetMapping("/outboundDelete/{Id}")
    public String GoodsinDelete(@PathVariable("Id") String Id,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("LoginUser");
        Goodsout goodsout = (Goodsout) jdbcTemplate.queryForObject("select * from goodsout_"+username+" where Id = ?", new MyRowMapperForGoodsout(), Id);
        Goods goods = jdbcTemplate.queryForObject("select * from goods_"+username+" where Gname = ?", new MyRowMapperForGoods(), goodsout.getGname());
        jdbcTemplate.update("update goods_"+username+" set amount=? where Id = ? ",goods.getAmount()+goodsout.getAmount(),goods.getId());
        jdbcTemplate.update("delete from goodsout_"+username+" where Id = ?", Id);
        return "redirect:/outbound";
    }




}
