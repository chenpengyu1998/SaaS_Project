package com.cpy.saas_test.dao;

import com.cpy.saas_test.element.Goodsout;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyRowMapperForGoodsout implements RowMapper {
    @Override
    public Goodsout mapRow(ResultSet rs, int rowNum) throws SQLException {
        Goodsout goods = new Goodsout();
        goods.setId(rs.getString("Id"));
        goods.setGname(rs.getString("Gname"));
        goods.setGtype(rs.getString("Gtype"));
        goods.setAmount(Integer.parseInt(rs.getString("amount")));
        goods.setOutDate(rs.getString("OutDate"));
        return goods;
    }
}
