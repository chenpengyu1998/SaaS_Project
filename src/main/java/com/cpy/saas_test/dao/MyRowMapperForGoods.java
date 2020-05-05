package com.cpy.saas_test.dao;

import com.cpy.saas_test.element.Goods;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRowMapperForGoods implements RowMapper<Goods> {
    @Override
    public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getString("Id"));
        goods.setGname(rs.getString("Gname"));
        goods.setGtype(rs.getString("Gtype"));
        goods.setAmount(Integer.parseInt(rs.getString("amount")));
        return goods;
    }
}
