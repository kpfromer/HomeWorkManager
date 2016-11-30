package com.kylepfromer.database;

import com.kylepfromer.todo.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kpfromer on 11/24/16.
 */
public class Account {
    private Connection connection;
    //MAKE SURE TO EDIT SQL QUERIES IN FUNCTIONS IF EDITED!
    private int listItemsLength = 5;

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

    public void addTasks(ArrayList<Task> taskList, long id) {

        //Todo: convert tasks to sql stuff
        String sql = "INSERT INTO ListItems (ListItemID, ListID, ListText, ListItemDone, ListItemPosition) VALUES  (?, ?, ?, ?, ?)";


    }

    public ArrayList<Task> getTasks(long id) {
        //Todo: get tasks with id
//        try {
//            String sql = "SELECT * FROM ListItems WHERE ListID = ?";
//
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, Long.toString(id));
//
//            ResultSet accountSet = stmt.executeQuery();
//            //todo: take values in accountSet and Get all items
//
//            //START
//
//            ArrayList<Task> taskList = new ArrayList<>();
//
//            // For every task in database
//            while ( accountSet.next() ) {
//
//                String listItemID =
//
//                taskList.add(rs.getString(number));
//                System.out.println(values);
//
//
//
//                model.addRow(new Object[] {value1,  value2, value3, value4});
//
//            Object[] row = new Object[columnCount];
//            for (int i = 1; i <= columnCount; ++i) {
//                row[i - 1] = rs.getString(i); // Or even rs.getObject()
//            }
//            model.addRow(row);
//
//
//
//
//
//            //END
//
//            }
//
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }

        return null;
    }



    public void destroy() throws SQLException {
        try {
            connection.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public void oAuthLogin() {
        // todo: use google oauth to sign up, better and safer!
    }
}
