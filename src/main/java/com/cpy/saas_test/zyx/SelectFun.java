package com.cpy.saas_test.zyx;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SelectFun {

    @Select("Select username from user")
    public List<String> alluser();

    @Select("Select Gname from goods_${username}")
    public List<String> allGoodsForUser(@Param("username") String username);

}
