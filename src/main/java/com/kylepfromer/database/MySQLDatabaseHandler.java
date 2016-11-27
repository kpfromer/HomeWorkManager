package com.kylepfromer.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
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
                throw new RuntimeException(properties.getProperty("url"));

//                Class.forName("com.mysql.jdbc.Driver");
//                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
//                input.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            }
        //return connection;

    }
}
