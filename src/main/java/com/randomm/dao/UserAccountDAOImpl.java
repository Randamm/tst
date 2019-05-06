package com.randomm.dao;

import com.randomm.db.PostgreSqlDB;
import com.randomm.domain.UserAccount;

import java.sql.*;

public class UserAccountDAOImpl implements UserAccountDAO {

    private PostgreSqlDB db;

    public UserAccountDAOImpl(PostgreSqlDB db) {
        this.db = db;
    }

    public void addUserAccount(UserAccount userAccount) {
        if(userAccount == null) throw new IllegalArgumentException("userAccount cannot be null");
        String query = "INSERT INTO useraccount(username, surname) VALUES(?,?)";

        try {
            Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userAccount.getUsername());
            preparedStatement.setString(2, userAccount.getSurname());

            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public UserAccount findUserAccountByUsername(String username) {
        String query = "SELECT * FROM useraccount WHERE username=?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new UserAccount(resultSet.getString(1),resultSet.getString(2));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void updateSurname(String username, String newSurname) {
        String query = "UPDATE useraccount SET surname =? WHERE username=?";

        try {
            Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newSurname);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
