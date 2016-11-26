package com.kylepfromer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kpfromer on 11/24/16.
 */
public class Account {
    private Connection connection;

    public Account(Connection conn) {
        connection = conn;
    }

    public boolean createAccount(String username, String password, String email) {
        try {


            String usernameSql = "SELECT Username FROM users WHERE Username = ?";
            PreparedStatement usernamestmt = connection.prepareStatement(usernameSql);

            usernamestmt.setString(1, username);
            ResultSet rs = usernamestmt.executeQuery();
            //if there is a matching Username Signup didnt work
            if (rs.next()) {
                return false;
            }

            String sql = "INSERT INTO users (Username, Password, Email) VALUES (?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean login(String username, String password) {
        try {
            String sql = "SELECT ID, Username from users where Username=? && Password=?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getID(String username, String password) {

        try {
            String sql = "SELECT ID from users where Username=? && Password=?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet accountSet = stmt.executeQuery();

            if (accountSet.next()) {
                return accountSet.getString("ID");
            } else {
                //todo: deal with account error
                throw new RuntimeException("NO ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void destroy() throws SQLException {
        connection.close();
    }


    public void oAuthLogin() {
        // todo: use google oauth to sign up, better and safer!
    }
}
