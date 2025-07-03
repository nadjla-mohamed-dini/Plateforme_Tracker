package com.example.model;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private double grade; 


    public Student(int id, String firstName, String lastName, int age, double grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.grade = grade;
    }
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }   

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return "ID: " + id +
                ", FirstName:  " + firstName +
                ", LastName:  " + lastName +
                ", Age:  " + age +
                ", Grade:  " + grade; 
    }
}
