package com.cpy.saas_test.zyx;


import com.cpy.saas_test.element.JaccardElement;
import com.cpy.saas_test.element.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class UserFun {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SelectFun selectFun;




    @GetMapping("/recommend")
    public String sumSame(HttpServletRequest request, Model model){
        //重合数
        Map<String,Integer> same= new HashMap<>();
        String loginUser = (String) request.getSession().getAttribute("LoginUser");
        //所有用户名
        List<String> usernames = selectFun.alluser();
        //当前登录用户的所有物品名字
        List<String> loginGoods = selectFun.allGoodsForUser(loginUser);
        //商品名字计数
        Map<String,Integer> sum = new HashMap<>();
        sum.put(loginUser, loginGoods.size());
        //重合数
        for(String username : usernames){
            if(username.equals(loginUser)) continue;
            int temp=0;
            //对比人所有物品名字
            List<String> userGoods = selectFun.allGoodsForUser(username);
            sum.put(username, userGoods.size());
            for(String lg : loginGoods){

                for(String ug:userGoods){
                    if(lg.equals(ug)){
                        temp++;
                    }
                }
                same.put(loginUser+"_"+username, temp);
            }
        }

        //最接近的用户数值
        double max_jaccard = Integer.MIN_VALUE;
        //最接近的用户名字
        String max_jaccard_username = null;
        for(String username : usernames){
            if(username.equals(loginUser)) continue;
            double fz = same.get(loginUser+"_"+username);
            double fm = Math.sqrt(sum.get(loginUser)*sum.get(username));
            if(max_jaccard<(fz/fm)){
                max_jaccard=(fz/fm);
                max_jaccard_username=username;
            }

        }

        //第二接近的数值
        double max_jaccard2 = Integer.MIN_VALUE;
        //最接近的用户名字
        String max_jaccard_username2 = null;
        for(String username : usernames){
            if(username.equals(loginUser)) continue;
            if(username.equals(max_jaccard_username)) continue;
            double fz = same.get(loginUser+"_"+username);
            double fm = Math.sqrt(sum.get(loginUser)*sum.get(username));
            if(max_jaccard2<(fz/fm)){
                max_jaccard2=(fz/fm);
                max_jaccard_username2=username;
            }

        }


        System.out.println("第一接近的："+max_jaccard+"  name:"+max_jaccard_username);
        System.out.println("第二接近的："+max_jaccard2+"  name:"+max_jaccard_username2);


        //最接近的用户购买的商品
        List<String> jaccardGoods = selectFun.allGoodsForUser(max_jaccard_username);
        //第二接近的用户购买的商品
        List<String> jaccardGoods2 = selectFun.allGoodsForUser(max_jaccard_username2);
        //推荐商品对1
        Set<String> recommend1 = new HashSet<>();
        //推荐商品对2
        Set<String> recommend2 = new HashSet<>();
        //总的推荐
        Set<String> recommend = new HashSet<>();

        for(String lg : jaccardGoods){
            //是否是共有的物品
            boolean isSame = false;

            for(String jg : loginGoods){
                if(lg.equals(jg)){
                    isSame=true;
                    continue;
                }
            }
            if(!isSame){
                recommend1.add(lg);
                recommend.add(lg);
            }
        }
        for(String lg : jaccardGoods2){
            //是否是共有的物品
            boolean isSame = false;

            for(String jg : loginGoods){
                if(lg.equals(jg)){
                    isSame=true;
                    continue;
                }
            }
            if(!isSame){
                recommend2.add(lg);
                recommend.add(lg);
            }
        }

        //
        List<JaccardElement> res = new ArrayList<>();

        for(String temp : recommend){
            double jaccardV =0;
            if(recommend1.contains(temp)) jaccardV+=max_jaccard;
            if(recommend2.contains(temp)) jaccardV+=max_jaccard2;

            res.add(new JaccardElement(jaccardV,temp));

        }

        Collections.sort(res, new Comparator<JaccardElement>() {
            @Override
            public int compare(JaccardElement o1, JaccardElement o2) {
                if(o1.getJaccard()>o2.getJaccard()){
                    return -1;
                }
                return 1;
            }
        });

        model.addAttribute("jaccards", res);

        return "recommend";
    }


//    @GetMapping("test/{username}")
//    @ResponseBody
//    public List<String> test(@PathVariable("username")String username){
//        return
//    }







}
