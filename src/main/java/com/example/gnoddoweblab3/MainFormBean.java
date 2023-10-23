package com.example.gnoddoweblab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.FileNotFoundException;
import java.io.Serializable;

@ManagedBean(name = "mainFormBean")
@SessionScoped
public class MainFormBean implements Serializable {

    private Check newCheck;

    private DatabaseWorker databaseWorker;

    public DatabaseWorker getDatabaseWorker() {
        return databaseWorker;
    }

    public void setDatabaseWorker(DatabaseWorker databaseWorker) {
        this.databaseWorker = databaseWorker;
    }

    public MainFormBean(Check newCheck, DatabaseWorker databaseWorker) {
        this.newCheck = newCheck;
        this.databaseWorker = databaseWorker;
    }

    public MainFormBean() throws FileNotFoundException {
        this.newCheck = new Check();
        this.databaseWorker = new DatabaseWorker();
        databaseWorker.getPoint();
//        databaseWorker.delete();
    }

    public Check getNewCheck() {
        return newCheck;
    }

    public void setNewCheck(Check newCheck) {
        this.newCheck = newCheck;
    }

    public void submit() {
        newCheck.checkHit();
        if (validate(newCheck)) {
            Check checkForTheList = new Check(newCheck.getX(), newCheck.getY(), newCheck.getR(), newCheck.isResult());
            this.databaseWorker.add(checkForTheList);
        }
    }

    public boolean validate(Check check) {
        return (((check.getX() >= -5) && (check.getX() <= 5)) && (check.getY() > -3 && (check.getY() < 3)) && ((check.getR() > 1) && (check.getR() < 3)));
    }
}
