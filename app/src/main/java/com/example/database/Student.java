package com.example.database;

public class Student {
    private String name;
    private float cgpa;

    public Student() {
    }

    public Student(String name, float cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
}
