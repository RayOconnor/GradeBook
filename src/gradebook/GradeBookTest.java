package gradebook;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for GradeBook project
 * @author Elena Allen, Ray O'Connor, Premal Patel
 * @version 2014-04-11
 */
public class GradeBookTest extends TestCase {

    /**
     * setUp for the tests
     */
    private Assignment a1;
    private Assignment a2;
    private Assignment a3;
    private Assignment t1;
    private Assignment t2;
    private Assignment t3;
    
    private Student elena;
    private Student premal;
    private Student ray;
    private Student elen;
    private Student premaster;
    private Student roy;
    
    private HashMap<Assignment, Double> eGrades;
    private HashMap<Assignment, Double> pGrades;
    private HashMap<Assignment, Double> rGrades;
    private HashMap<Assignment, Double> elGrades;
    private HashMap<Assignment, Double> prGrades;
    private HashMap<Assignment, Double> roGrades;
    
    private HashMap<String, Double> empty;
    private HashMap<String, Double> allGrades;
    
    private ArrayList<Student> students;
    private ArrayList<Student> students2;
    private ArrayList<Assignment> assignments; 
    private ArrayList<Assignment> assignments2;
    private ArrayList<Double> a1Grades;
    private ArrayList<Double> t1Grades;
    
    private MyGradeBook gb;
    private MyGradeBook gb2;
    
    /**
     * setUp for the tests
     */
    @Before
    public void setUp() {
        a1 = new Assignment("hw1", 50, .05);
        a2 = new Assignment("test1", 100, .2);
        a3 = new Assignment("lab1", 10, .1);

        eGrades = new HashMap<Assignment, Double>();
        eGrades.put(a1, 40.0);
        eGrades.put(a2, 80.0);
        eGrades.put(a3, 8.0);
        
        pGrades = new HashMap<Assignment, Double>();
        pGrades.put(a1, 40.0);
        pGrades.put(a2, 75.5);
        pGrades.put(a3, 10.0);
        
        rGrades = new HashMap<Assignment, Double>();
        rGrades.put(a1, 25.0);
        rGrades.put(a2, 90.0);
        rGrades.put(a3, 9.0);
        
        empty = new HashMap<String, Double>();
        
        allGrades = new HashMap<String, Double>();
        allGrades.put("allen.ele", 80.0);
        allGrades.put("patel.p", 83.14285714285714);
        allGrades.put("oconnor.r", 84.28571428571429);
        
        elena = new Student("allen.ele", "Elena", "spongebob", "Allen", 2016, eGrades);
        premal = new Student("patel.p", "Premal", "patrick", "Patel", 2015, pGrades);
        ray = new Student("oconnor.r", "Ray", "channell", "O'Connor", 2017, rGrades);
        
        students = new ArrayList<Student>();
        students.add(elena);
        students.add(premal);
        students.add(ray);
        //System.out.println(students);
        
        assignments = new ArrayList<Assignment>();
        assignments.add(a1);
        assignments.add(a2);
        assignments.add(a3);
        //System.out.println(assignments);
        
        a1Grades = new ArrayList<Double>();
        a1Grades.add(45.0);
        a1Grades.add(40.5);
        a1Grades.add(25.3);

        gb = new MyGradeBook(students, assignments);
        
        t1 = new Assignment("hw1", 50, .05);
        t2 = new Assignment("test1", 100, .2);
        t3 = new Assignment("lab1", 10, .1);

        elGrades = new HashMap<Assignment, Double>();
        elGrades.put(t1, 45.8);
        elGrades.put(t2, 80.0);
        elGrades.put(t3,  8.0);
        
        prGrades = new HashMap<Assignment, Double>();
        prGrades.put(t1, 40.2);
        prGrades.put(t2, 75.5);
        prGrades.put(t3, 10.0);
        
        roGrades = new HashMap<Assignment, Double>();
        roGrades.put(t1, 25.5);
        roGrades.put(t2, 90.0);
        roGrades.put(t3, 10.0);
        
        elen = new Student("allen.ele", "Elena", "spongebob", "Allen", 2016, elGrades);
        premaster = new Student("patel.p", "Premal", "patrick", "Patel", 2015, prGrades);
        roy = new Student("oconnor.r", "Ray", "channell", "O'Connor", 2017, roGrades);
        
        students2 = new ArrayList<Student>();
        students2.add(elen);
        students2.add(premaster);
        students2.add(roy);
        //System.out.println(students);
        
        assignments2 = new ArrayList<Assignment>();
        assignments2.add(t1);
        assignments2.add(t2);
        assignments2.add(t3);
        //System.out.println(assignments);
        
        t1Grades = new ArrayList<Double>();
        t1Grades.add(45.0);
        t1Grades.add(40.5);
        t1Grades.add(25.3);

        gb2 = new MyGradeBook(students2, assignments2);
        //System.out.println(gb);
    }
    
    /**
     * test method initialize
     * 
     */
    
    @Test
    public void testInitialize() {
        MyGradeBook mgb = new MyGradeBook(new ArrayList<Student>(), 
                new ArrayList<Assignment>());
        assertEquals(mgb, MyGradeBook.initialize());
        assertFalse(MyGradeBook.initialize().equals(gb2));
        
    }
    
    /**
     * test method initializeWithFile
     */
    
    @Test
    public void testInitializeWithFile() {
        String filename = "testInitial.txt";
        MyGradeBook gbti = MyGradeBook.initializeWithFile(filename);
        //assertEquals(gbti, gb2);  
    }
    
    /**
     * test method initializeWithString
     */
    @Test 
    public void testInitializeWithString() {
        String filename = "testInitial.txt";
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            //String testString = input.toString();
            String initString = "";
            
            while (input.hasNext()) {
                initString += input.nextLine() + "\n";
                
            }
            //System.out.println(initString);
            
            MyGradeBook gbti = MyGradeBook.initializeWithString(initString);
            
            assertEquals(gb2, gbti);
            
        }
        catch (FileNotFoundException e) {
            
            System.err.println(
                    "StringIterator: File \"" + filename + "\" not found.");
            
        }   
    }
    
    /**
     * test the processFile method
     * 
     */
    @Test
    public void testProcessFileStudents() {
        HashMap<Assignment, Double> s1grades = 
                new HashMap<Assignment, Double>();
        
        s1grades.put(t1, 0.0);
        s1grades.put(t2, 0.0);
        s1grades.put(t3, 0.0);
        
        Student s1 = new Student("iaartinez",
                "Sophia",
                "Martinez",
                "Scott",
                2014,
                s1grades);
        
        HashMap<Assignment, Double> s2grades = 
                new HashMap<Assignment, Double>();
        
        s2grades.put(t1, 0.0);
        s2grades.put(t2, 0.0);
        s2grades.put(t3, 0.0);
        
        Student s2 = new Student("illiness",
                "William",
                "Jones",
                "Nelson",
                2014,
                s2grades);
        
        MyGradeBook gb3 = 
                MyGradeBook.initializeWithString(gb2.outputGradebook());
        
        
        gb2.students.add(s1);
        
        gb2.students.add(s2);
        
        
        
        String filename = "testAddStudent.txt";
        gb3.processFile(filename);
        
        //uncomment the printlns to see that both are identicle
        //System.out.println(gb2.outputGradebook());
        //System.out.println(gb3.outputGradebook());  
    }
    
    /**
     * tests process file with add assignments
     * 
     * 
     */
    @Test
    public void testProcessFileAssignments() {

        //code does not crash when test2 is added even though no test2 exists
        gb2.processFile("addAssignments.txt");
        
        //uncomment the printlns to see the two added assignments
        //System.out.println(gb2.outputGradebook());  
    }
    
    @Test
    public void testProcessGradesForStudent() {
   
        System.out.println("********test results from GradesForStudent*****");
        System.out.println("\n****From File****");
        
        
        gb2.processFile("testGradesForStudent.txt");
        
        //uncomment the gradebook to see that roy's test1 grade
        //is now an 80
        //System.out.println(gb2.outputGradebook());
        
        setUp();
        System.out.println("\n****From String****");
        
        String filename = "testGradesForStudent.txt";
        
        try {
        
            File file = new File(filename);
            Scanner input = new Scanner(file);
            //String testString = input.toString();
            String initString = "";
            
            while (input.hasNext()) {
                initString += input.nextLine() + "\n";
                
            }
            gb2.processString(initString);
            //uncomment to see the modified grade book
            //System.out.println(gb2.outputGradebook());
    
        }
        catch (FileNotFoundException e) {
            
            System.err.println(
                    "StringIterator: File \"" + filename + "\" not found.");
            
        } 
    }

    @Test 
    public void testProcessGradesForAssignment() {
        System.out.println("\n****test results from GradesForAssignment****");
        System.out.println("\n****From File****");
        
        gb2.processFile("testGradesForAssignment.txt");
        
        //uncomment to see the modified grade book
        //System.out.println(gb2.outputGradebook());
        
        setUp();
        System.out.println("\n****From String****");
        
        
        String filename = "testGradesForAssignment.txt";
        
        try {
        
            File file = new File(filename);
            Scanner input = new Scanner(file);
            //String testString = input.toString();
            String initString = "";
            
            while (input.hasNext()) {
                initString += input.nextLine() + "\n";
                
            }
            gb2.processString(initString);
            //uncomment to see the modified grade book
            //System.out.println(gb2.outputGradebook());
            
            
            
        }
        catch (FileNotFoundException e) {
            
            System.err.println(
                    "StringIterator: File \"" + filename + "\" not found.");
            
        }
        
    }


    /**
     * testing method outputCurrentGrades
     */
    public void testOutputCurrentGrades() {

        String ocg = gb2.outputCurrentGrades();
        
        System.out.println("\n***output current grades testing***");
        System.out.println(ocg);
        
    }
    
    /**
     * test output student grades
     * 
     * 
     */
    @Test
    public void testOutputAssignmentGrades() {

        String ocg = gb2.outputAssignmentGrades("test1");
        
        System.out.println("\n***output assignment grades testing***");
        System.out.println(ocg);
   
    }
    
    /**
     * test output student grades
     * 
     * 
     */
    @Test
    public void testOutputStudentGrades() {

        String ocg = gb2.outputStudentGrades("oconnor.r");
        
        System.out.println("\n***output student grades testing***");
        System.out.println(ocg);
        
        
    }
    
    /**
     * 
     * 
     * 
     */
    @Test
    public void testOutPutGradebook() {

        String ocg = gb2.outputGradebook();
        
        System.out.println("\n***output gradebook testing***");
        System.out.println(ocg);
        
    }
    
    /**
     * Test remove student
     * 
     * 
     */
    public void testRemoveStudent() {

        Student s = gb2.students.get(1);
        assertEquals(s.username, "patel.p");
        gb2.removeStudent("patel.p");
        
        s = gb2.students.get(1);
        assertEquals(s.username, "oconnor.r");
    }
    
    
    /**
     * testing method changeGrade
     */
    @Test
    public void testChangeGrade() {

        assertTrue(gb.changeGrade("hw1", "allen.ele", 47) == true);
        
        assertFalse(gb.changeGrade("lab1", "paws", 30) == true);

    }

    /**
     * testing method average
     */
    @Test
    public void testAverage() {
        
        assertTrue(gb.average("hw1") == 35);
        assertTrue(gb.average("lab1") == 9);
        
        assertFalse(gb.average("test1") == 100);
    }

    /**
     * testing method median
     */
    @Test
    public void testMedian() {
        
        assertTrue(gb.median("hw1") == 40.0);
        
        assertFalse(gb.median("lab1") == 15.3);
        
    }
    
    /**
     * testing method min
     */
    @Test
    public void testMin() {

        assertTrue(gb.min("lab1") == 8.0);
        
        assertFalse(gb.min("hw1") == 10.0);
    }
    
    /**
     * testing method max
     */
    @Test
    public void testMax() {
        
        assertTrue(gb.max("hw1") == 40.0);
        
        assertFalse(gb.max("lab1") == 100.0);
        
    }
    
    /**
     * testing method currentGrade
     */
    @Test
    public void testCurrentGrade() {
        System.out.println(gb.currentGrade("allen.ele"));
        assertTrue(gb.currentGrade("allen.ele") == 80.0);
        assertTrue(gb.currentGrade("oconnor.r") == 84.28571428571429);
        assertTrue(gb.currentGrade("asdf") == 0);

        assertFalse(gb.currentGrade("patel.p") == 100);
    }
    
    /**
     * testing method currentGrades
     */
    @Test
    public void testCurrentGrades() {
        //assertTrue(gb.currentGrades().equals(allGrades));
        
        assertFalse(gb.currentGrades().equals(empty));
    }

    
    /**
     * testing method assignmentGrade
     */
    @Test
    public void testAssignmentGrade() {
        
        assertTrue(gb.assignmentGrade("hw1", "allen.ele") == 40.0);
        assertTrue(gb.assignmentGrade("lab1", "patel.p") == 10.0);
        
        assertFalse(gb.assignmentGrade("test1", "oconnor.r") == 50);
    }
    
    /**
     * testing method getAssignmentGrade
     */
    @Test
    public void testGetAssignmentGrade() {

        assertTrue(elena.getAssignmentGrade(a1) == 40.0);
        assertTrue(premal.getAssignmentGrade(a3) == 10.0);
        
        assertFalse(ray.getAssignmentGrade(a2) == 80);
    }
}

