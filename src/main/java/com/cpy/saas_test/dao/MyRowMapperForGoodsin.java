package com.cpy.saas_test.dao;


import com.cpy.saas_test.element.Goodsin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyRowMapperForGoodsin implements RowMapper {
    @Override
    public Goodsin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Goodsin goodsin = new Goodsin();
        goodsin.setId(rs.getString("Id"));
        goodsin.setGname(rs.getString("Gname"));
        goodsin.setGtype(rs.getString("Gtype"));
        goodsin.setAmount(Integer.parseInt(rs.getString("amount")));
        goodsin.setDate(rs.getString("date"));

        return goodsin;
    }
}
