package edu.sdccd.cisc191.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class GradeBookResponse {
    private double GPA;
    private ArrayList<StudentRequest> gradeList ;

    public GradeBookResponse(ArrayList<StudentRequest> gradeList)
    {
        this.gradeList = gradeList;
    }
    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(GradeBookResponse response) throws Exception {
        return objectMapper.writeValueAsString(response);
    }
    public static GradeBookResponse fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, GradeBookResponse.class);
    }
    protected GradeBookResponse() {}

   /* public double calculateGPA() {
        double totalPoints = 0;
        for (int i = 0; i < gradeList.size(); i++)
            totalPoints = gradeList.get(i).getGrade() + totalPoints;
        return (double) totalPoints / gradeList.size();
    }
    *
    */
}