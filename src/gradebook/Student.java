package gradebook;

import java.util.HashMap;

/**
 * class to represent students
 * allen.ele@husky.neu.edu, patel.pre@husky.neu.edu, roconnorc@ccs.neu.edu
 * @author Elena Allen, Premal Patel, Ray O'Connor
 * @version 2014-04-11
 */
public class Student {

    String username;
    String firstName;
    String middleName;
    String lastName;
    int gradYear;
    HashMap<Assignment, Double> grades;

    public Student(String username, 
            String firstName,
            String middleName,
            String lastName, 
            int gradYear, 
            HashMap<Assignment, Double> grades) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gradYear = gradYear;
        this.grades = grades;
    }

    public Student(String username, 
            String firstName,
            String middleName,
            String lastName, 
            int gradYear) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gradYear = gradYear;
        this.grades = new HashMap<Assignment, Double>();
    }

    

    /**
     * Method returns the grade associated with the given assignment
     * for this student.
     * @param assignment whose grade you want
     * @return int that is the grade of the assignment for this student
     */
    double getAssignmentGrade(Assignment assignment) {
        return this.grades.get(assignment);
    }
}





    


