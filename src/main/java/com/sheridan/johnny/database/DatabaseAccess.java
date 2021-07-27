package com.sheridan.johnny.database;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sheridan.johnny.beans.User;

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
    ArrayList<User> userList = (ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
    if (userList.size() > 0) return userList.get(0);
    else return null;
    }

    public List<String> getRolesById(Long userId) {
        ArrayList<String> roleList = new ArrayList<String>();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT user_role.userId, sec_role.roleName "
        + "FROM user_role, sec_role "
        + "WHERE user_role.roleId = sec_role.roleId "
        + "AND userId = :userId";
        parameters.addValue("userId", userId);
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        for (Map<String, Object> row : rows) {
        roleList.add((String)row.get("roleName"));}
        return roleList;
    }
}
