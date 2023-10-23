package com.example.gnoddoweblab3.database;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Saver {

    private Connection connection;

    private final Connect connect = new Connect();

    public Saver() throws FileNotFoundException {
    }

    protected void init() {
        connection = connect.getConnection();
    }

    public void createTable() {
        init();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS results(x float, y float , r float, res boolean)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPoint(double x, double y, double r, boolean result) {
        try {
            if (connection == null) {
                createTable();
            }
            PreparedStatement statement = connection.prepareStatement("INSERT INTO results(x,y,r,res) values(?, ?, ?, ?)");
            statement.setDouble(1, x);
            statement.setDouble(2, y);
            statement.setDouble(3, r);
            statement.setBoolean(4, result);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
