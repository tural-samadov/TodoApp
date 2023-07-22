package com.todoapp.dao;

import com.todoapp.model.User;
import com.todoapp.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao{

    @Override
    public void register(User user) {
        String USER_INSERT_SQL = "INSERT INTO user(name, surname, username, password) values (?, ?, ?, ?);";

        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(USER_INSERT_SQL);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUser(String username, String password) {
        String GET_USER_SQL = "SELECT id, name, surname, username, password FROM user WHERE username = ? AND password = ?;";
        User user = null;

        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_USER_SQL);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
