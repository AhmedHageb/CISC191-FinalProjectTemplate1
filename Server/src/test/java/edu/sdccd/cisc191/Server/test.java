package edu.sdccd.cisc191.Server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    @Test
    void testGradesList()
    {
        GradesList a = new GradesList();
        Subject m = new Subject("Math",3.5);
        Subject s = new Subject("Science",4.0);
        a.addGrade(m);
        a.addGrade(s);
        assertEquals("Math",a.getClassGradeArrayList().get(0).getName());
        assertEquals(3.5,a.getClassGradeArrayList().get(0).getGrade());
        a.calculateGPA();
        assertEquals(3.75, a.calculateGPA());
        a.removeGrade(a.findSubjectIndex("Math"));
        assertEquals(-1, a.findSubjectIndex("Math"));
        a.deleteAll();
        assertEquals(0,a.getClassGradeArrayList().size());
    }
    @Test
    void testSubject()
    {
        Subject s = new Subject("Social Studies", 3.96);
        assertEquals("Social Studies", s.getName());
        assertEquals(3.96, s.getGrade());
    }

}
