package com.otmane.app.controlleurs;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentsModel {

    private SimpleIntegerProperty studentId;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private  SimpleStringProperty score;

    public StudentsModel(Integer studentId, String firstName, String lastName) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public StudentsModel(Integer studentId, String firstName, String lastName,String score) {
        this.studentId =  new SimpleIntegerProperty(studentId);
        this.firstName =new SimpleStringProperty(firstName);
        this.lastName  =new SimpleStringProperty(lastName);
        this.score     =  new SimpleStringProperty(score);
    }

    public String getScore() {
        return score.get();
    }

    public SimpleStringProperty scoreProperty() {
        return score;
    }

    public void setScore(String score) {
        this.score.set(score);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId = new SimpleIntegerProperty(studentId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }
}