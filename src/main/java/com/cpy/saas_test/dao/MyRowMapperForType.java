package com.cpy.saas_test.dao;


import com.cpy.saas_test.element.type;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MyRowMapperForType implements RowMapper<type> {
    @Override
    public type mapRow(ResultSet rs, int rowNum) throws SQLException {
        type t = new type();
        t.setId(rs.getString("Id"));
        t.setTypename(rs.getString("Typename"));
        t.setDescribe(rs.getString("Describe"));
        t.setTips(rs.getString("Tips"));


        return t;
    }
}
