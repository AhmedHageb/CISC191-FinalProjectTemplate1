package edu.sdccd.cisc191.Server;

public class Subject {
    private String name;
    private double grade;
    public Subject(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    public double getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
