package com.example.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final IntegerProperty id;
    private final StringProperty frist_name;
    private final StringProperty last_name;
    private final IntegerProperty age;
    private final DoubleProperty grade;

    public Student (int id, String frist_name, String last_name, int age, double grade) {
        this.id = new SimpleIntegerProperty(id);
        this.frist_name = new SimpleStringProperty(frist_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.age = new SimpleIntegerProperty(age);
        this.grade = new SimpleDoubleProperty(grade);
    }

    public int getId() {return id.get();}
    public String getFirstname() {return frist_name.get();}
    public String getLastname() {return last_name.get();}
    public int getAge() {return age.get();}
    public double getGrade() {return grade.get();}

    public IntegerProperty idProperty() {return id;}
    public StringProperty firstNameProperty() {return frist_name;}
    public StringProperty lastNameProperty() {return last_name;}
    public IntegerProperty ageProperty() {return age;}
    public DoubleProperty gradeProperty() {return grade;}

    public void setId(int id) {
    this.id.set(id);
}

    public void setFirstname(String firstname) {
    this.frist_name.set(firstname); 
    }

    public void setLastname(String lastname) {
        this.last_name.set(lastname);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void setGrade(double grade) {
        this.grade.set(grade);
    }

}

