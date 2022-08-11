package edu.sdccd.cisc191.Server;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI implements Runnable{
    private Thread thread;
    private Scanner reader = new Scanner(System.in);
    private static GradesList gradesList;
    private static final String[] options = { "1 - List of your classes",
            "2 - Add a Class", "3 - Remove a class", "4 - Calculate GPA", "5 - Sort Classes by Grade", "6 - Quite"};

    public void start()
    {
        if (thread == null)
        {
            thread = new Thread(this,"Grades CLI" );
            thread.start();
        }
    }

    @Override
    public void run()
    {
        gradesList = GradesList.subjectsList;
        System.out.println("Welcome To The GradeBook Program");
        System.out.println("Choose an option to continue");
        listOptions();
        int input = reader.nextInt();
        while(!(input == 6))
        {
            switch(input)
            {

                case 1:
                    System.out.println("List of your classes");
                    System.out.println();
                    printGrades();
                    break;
                case 2:
                    System.out.println("Add a Class");
                    System.out.println();
                    addSubject();
                    break;
                case 3:
                    System.out.println("Remove a class");
                    System.out.println();
                    removeSubject();
                    printGrades();
                    break;
                case 4:
                    System.out.println("Calculate GPA");
                    System.out.println();
                    GPA();
                    break;
                case 5:
                    System.out.println("Sort Classes by Grade");
                    System.out.println();
                    sortByGrade(gradesList.getClassGradeArrayList());
                    printGrades();
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
            System.out.println("Choose an option to continue");
            listOptions();
            input = reader.nextInt();
        }
        System.out.println("Have a nice day!!");
    }
    //prints the options for the user
    public void listOptions()
    {
        for(int i = 0; i < options.length;i++)
        {
            System.out.println(options[i]);
        }
        System.out.print("=>");
    }
    //adds a subject object to the Subject arraylist
    public void addSubject()  {

        System.out.print("Class Name: ");
        String name = reader.next();
        System.out.println();
        if(gradesList.findSubjectIndex(name) == -1)
        {
            System.out.print("Class Grade: ");
            double grade;
            while((grade= reader.nextDouble()) > 4.0)
            {
                System.out.println();
                System.out.print("Enter a grade out of 4.0: ");
            }
            Subject request = new Subject(name,grade);
            gradesList.addGrade(request);
            System.out.println("Class successfully added!");
        } else System.out.println("**Class Already Added**");
        System.out.println();

    }
    //finds the index of the subject selected and removes it from the Subject arraylist
    public void removeSubject()
    {
        if(gradesList.getClassGradeArrayList().size()>0) {
            System.out.print("Class Name: ");
            String name = reader.next();
            while (gradesList.findSubjectIndex(name) == -1) {
                System.out.print("NOT FOUND; \n Enter a valid class name: ");
                name = reader.next();
            }
            gradesList.removeGrade(gradesList.findSubjectIndex(name));
            System.out.println(name + " class was removed successfully");
        }else System.out.println("**NO Classes Entered**");
        System.out.println();
    }
    // calculates and prints the GPA based on the grades of the subjects already entered
    public void GPA()
    {
        if(gradesList.getClassGradeArrayList().size()>0)
        {
            System.out.printf("Your Grade Point Average is: " + gradesList.calculateGPA());
        }
        else System.out.println("**No Classes Entered**");
        System.out.println();

    }
    //prints a formatted list of the subjects and grades entered by the user
    public void printGrades()
    {
        if(gradesList.getClassGradeArrayList().size()>0) {
            System.out.println("Class\tGrade");
            for (int i = 0; i < gradesList.getClassGradeArrayList().size(); i++) {
                System.out.println(gradesList.getClassGradeArrayList().get(i).getName() + "\t"
                        + gradesList.getClassGradeArrayList().get(i).getGrade());
            }
        } else  System.out.println("**GradeBook is Empty**");
        System.out.println();
    }
    //Sorts the classes by grade using MergeSort (includes recursion)
    public void sortByGrade(ArrayList<Subject> subjectArrayList) {
        int listSize = subjectArrayList.size();
        if (listSize > 1) {
            // Merge sort the first half
            ArrayList<Subject> firstHalf = new ArrayList<Subject>();
            for(int i = 0; i < listSize/2;i++)
                firstHalf.add(subjectArrayList.get(i));
            sortByGrade(firstHalf);

            // Merge sort the second half
            ArrayList<Subject> secondHalf = new ArrayList<Subject>();
            for(int i = listSize/2; i < listSize;i++)
                secondHalf.add(subjectArrayList.get(i));
            sortByGrade(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, subjectArrayList);
        }
    }
    public static void merge(ArrayList<Subject> list1, ArrayList<Subject> list2, ArrayList<Subject> temp)
        {
            temp.clear();
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2

        while (current1 < list1.size() && current2 < list2.size()) {
            if (list1.get(current1).getGrade() < list2.get(current2).getGrade())
                temp.add(list1.get(current1++));
            else
                temp.add(list2.get(current2++));
        }

        while (current1 < list1.size())
            temp.add(list1.get(current1++));

        while (current2 < list2.size())
            temp.add(list2.get(current2++));
    }

}
