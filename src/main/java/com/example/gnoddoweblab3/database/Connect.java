package com.example.gnoddoweblab3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private Connection connection;

    private final String JDBC_DRIVER = "org.postgresql.Driver";

    private final String dbURL = "jdbc:postgresql://localhost:5432/Lab7DB";

    private final String dbUserName = "postgres";

    private final String dbPassword = "21052002";

    private void setConnection() {
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
