package com.kylepfromer.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by kpfromer on 11/24/16.
 */
public class MySQLDatabaseHandler {

    public static Connection getConnection() {
        //todo: better error handling
        Connection connection = null;
            try {

                //todo: have better system to get external properties file!
                FileInputStream input = new FileInputStream("/opt/config/settings.properties");

                Properties properties = new Properties();
                properties.load(input);

                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
                input.close();
            } catch (FileNotFoundException e) {
                //todo: better error handling
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            }
        return connection;
    }
}
