package edu.sdccd.cisc191.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentRequest {
    //private String subject;
    //private double grade;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(Class studentRequest) throws Exception {
        return objectMapper.writeValueAsString(studentRequest);
    }
    public static StudentRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, StudentRequest.class);
    }
    protected StudentRequest() {}

    /*public StudentRequest(String subject, double grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    public double getGrade() {
        return grade;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

     */
}
