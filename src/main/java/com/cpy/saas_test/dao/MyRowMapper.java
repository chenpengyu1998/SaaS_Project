package com.cpy.saas_test.dao;

import com.cpy.saas_test.element.User;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        String Id = resultSet.getString("Id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String phone = resultSet.getString("phone");
        String URL = resultSet.getString("URL");
        String Email = resultSet.getString("Email");
        String Introg = resultSet.getString("Introg");
        user.setId(Id);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setURL(URL);
        user.setEmail(Email);
        user.setIntrog(Introg);

        return user;
    }
}
