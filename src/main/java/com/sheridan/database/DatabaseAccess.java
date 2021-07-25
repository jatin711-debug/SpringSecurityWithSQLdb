package com.sheridan.database;
import java.util.ArrayList;
import com.sheridan.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess {
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public User findUserAccount(String email) {
MapSqlParameterSource parameters = new MapSqlParameterSource();
String query = "SELECT * FROM sec_user where email = :email";
parameters.addValue("email", email);
ArrayList<User> userList = (ArrayList<User>) jdbc.query(query, parameters, new
BeanPropertyRowMapper<User>(User.class));
if (userList.size() > 0) return userList.get(0);
else return null;
    }
}
