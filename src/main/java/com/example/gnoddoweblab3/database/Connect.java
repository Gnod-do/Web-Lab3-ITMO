package com.example.gnoddoweblab3.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {
    private Connection connection;

    private String JDBC_DRIVER = "";

    private String dbURL = "";

    private String dbUserName = "";

    private String dbPassword = "";

    public void configDatabase() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\anhdo\\IdeaProjects\\GnodDo-Web-lab3\\src\\main\\java\\com\\example\\gnoddoweblab3\\database\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to open file!");
        }

        JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
        dbURL = properties.getProperty("DB_URL");
        dbUserName = properties.getProperty("DB_USERNAME");
        dbPassword = properties.getProperty("DB_PASSWORD");
    }

    private void setConnection() {
        configDatabase();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        return this.connection;
    }

    public Connect() {
        setConnection();
    }
}
