package com.example.model;

import com.opencsv.bean.CsvBindByName;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentFX {

    @CsvBindByName(column = "id")
    private final IntegerProperty id = new SimpleIntegerProperty();

    @CsvBindByName(column = "FirstName")
    private final StringProperty firstName = new SimpleStringProperty();

    @CsvBindByName(column = "LastName")
    private final StringProperty lastName = new SimpleStringProperty();

    @CsvBindByName(column = "age")
    private final IntegerProperty age = new SimpleIntegerProperty();

    @CsvBindByName(column = "grade")
    private final DoubleProperty grade = new SimpleDoubleProperty();

    // Constructeur vide obligatoire pour OpenCSV
    public StudentFX() {}

    public StudentFX(int id, String FirstName, String LastName, int age, double grade) {
        this.id.set(id);
        this.firstName.set(FirstName);
        this.lastName.set(LastName);
        this.age.set(age);
        this.grade.set(grade);
    }

    public int getId() { return id.get(); }
    public String getFirstName() { return firstName.get(); }
    public String getLastName() { return lastName.get(); }
    public int getAge() { return age.get(); }
    public double getGrade() { return grade.get(); }

    // Setters (pour OpenCSV)
    public void setId(int id) { this.id.set(id); }
    public void setFirstName(String FirstName) { this.firstName.set(FirstName); }
    public void setLastName(String LastName) { this.lastName.set(LastName); }
    public void setAge(int age) { this.age.set(age); }
    public void setGrade(double grade) { this.grade.set(grade); }

    // Propriétés JavaFX (pour ta GUI)
    public IntegerProperty idProperty() { return id; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public IntegerProperty ageProperty() { return age; }
    public DoubleProperty gradeProperty() { return grade; }
} 


