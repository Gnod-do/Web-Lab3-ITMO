package com.example.gnoddoweblab3;

import com.example.gnoddoweblab3.database.Saver;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DatabaseWorker implements Serializable {

    private List<Check> checks;

    private final Saver saver = new Saver();

    private final Connection connection;

    public DatabaseWorker() throws FileNotFoundException {
        this.checks = new ArrayList<Check>();
        this.saver.createTable();
        this.connection = saver.getConnection();
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void add(Check check) {
        checks.add(check);
        saver.addPoint(check.getX(), check.getY(), check.getR(), check.isResult());
    }

    public void getPoint() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM results");
            while (resultSet.next()) {
                Check check = new Check();
                check.setX(resultSet.getDouble(1));
                check.setY(resultSet.getDouble(2));
                check.setR(resultSet.getDouble(3));
                check.setResult(resultSet.getBoolean(4));
                this.checks.add(check);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void delete() {
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("DELETE FROM results");
//            connection.commit();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
