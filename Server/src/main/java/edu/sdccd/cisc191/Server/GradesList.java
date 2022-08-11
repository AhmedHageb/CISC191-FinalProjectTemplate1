package edu.sdccd.cisc191.Server;

import java.util.ArrayList;

public class GradesList
{
    public static GradesList subjectsList = new GradesList();
    private ArrayList<Subject> studentSubjectArrayList = new ArrayList<>();

    //adds a grade to the Arraylist of grades
    public void addGrade(Subject a)
    {
        studentSubjectArrayList.add(a);
    }

    //removes a grade from the gradesList at a specific index/position
    public void removeGrade(int gradeIndex)
    {
        studentSubjectArrayList.remove(gradeIndex);
    }
    //clears the whole arraylist by creating a brand-new empty one
    public void deleteAll()
    {
        studentSubjectArrayList = new ArrayList<Subject>();
    }
    //gives the user access to the Grades arraylist
    public ArrayList<Subject> getClassGradeArrayList()
    {
        return studentSubjectArrayList;
    }
    //finds the position/index of the subject that is passed in
    public int findSubjectIndex(String subject)
    {
        for(int i = 0; i < studentSubjectArrayList.size(); i++)
        {
            if(studentSubjectArrayList.get(i).getName().equalsIgnoreCase(subject))
                return i;
        }
        return -1;
    }
    //calculates the gpa os the student based on the grades inside
    public double calculateGPA() {
        double totalPoints = 0;
        for (int i = 0; i < studentSubjectArrayList.size(); i++)
            totalPoints = studentSubjectArrayList.get(i).getGrade() + totalPoints;
        return (double) totalPoints / studentSubjectArrayList.size();
    }


}
