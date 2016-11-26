package com.kylepfromer.database;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by kpfromer on 11/24/16.
 */
public class MySQLDatabaseHandler {

    public static Connection getConnection() {
        //todo: better error handling
        Connection connection = null;
        try {
            try {
                ClassLoader classLoader = MySQLDatabaseHandler.class.getClassLoader();
                InputStream input = classLoader.getResourceAsStream("settings.properties");

                Properties properties = new Properties();
                properties.load(input);

                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
                input.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("No Database Connection Details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;

    }
}
