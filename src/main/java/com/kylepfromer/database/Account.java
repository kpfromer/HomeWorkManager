package com.kylepfromer.database;

import com.kylepfromer.todo.SchoolClass;
import com.kylepfromer.todo.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by kpfromer on 11/24/16.
 */
public class Account {
    private Connection connection;
    //MAKE SURE TO EDIT SQL QUERIES IN FUNCTIONS IF EDITED!
    private int listItemsLength = 6;

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
        String sql = "INSERT INTO ListItems (ListItemID, ListID, ListText, ListItemDone, ListItemPosition, ListItemDate) VALUES  (?, ?, ?, ?, ?, ?)";


    }

    public ArrayList<Task> getTasks(long id) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ListItems WHERE ListID = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Long.toString(id));

            ResultSet accountSet = stmt.executeQuery();

            // For every task in database
            while (accountSet.next()) {

                String listItemId = accountSet.getString("ListItemID");
                String listItemText = accountSet.getString("ListText");
                String listItemDone = accountSet.getString("ListItemDone");
                String listItemPosition = accountSet.getString("ListItemPosition");
                String listItemDate = accountSet.getString("ListItemDate");


                LocalDate itemDate = LocalDate.parse(listItemDate);
                long itemID = Long.getLong(listItemId);
                int itemPosition = Integer.getInteger(listItemPosition);
                boolean itemDone;
                itemDone = !listItemDone.equals("0");

//                Todo: Work on separate table for user's classes!
//                String listItemClass = accountSet.getString("ListItemClass");
                SchoolClass listItemClass = null;

                Task newTask = new Task(listItemText, itemDate, listItemClass, itemID, itemPosition, itemDone);

                taskList.add(newTask);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskList;
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
