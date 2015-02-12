package gradebook;

import java.util.ArrayList;


import java.util.HashMap;
import java.io.*;
import java.util.*;   

import java.io.File;
import java.util.Scanner;


public class MyGradeBook {

    ArrayList<Student> students;
    ArrayList<Assignment> allAssignments;

    public MyGradeBook(ArrayList<Student> students, ArrayList<Assignment> allAssignments) {
        this.students = students;
        this.allAssignments = allAssignments;
    }

    /**
     * Factory method to construct an empty MyGradebook
     *
     * @return an empty MyGradeBook
     */

    public static MyGradeBook initialize() {

        return new MyGradeBook(new ArrayList<Student>(), 
                new ArrayList<Assignment>());

    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from filename
     *
     * @param filename
     *            the filename for the file that contains the initial grade
     *            book, which is formatted like initial.txt
     * @return a MyGradebook that contains the grade book from filename
     */

    public static MyGradeBook initializeWithFile(String filename) {

        try {


            File file = new File(filename);
            Scanner input = new Scanner(file);
            return processGradeBook(input);


        }
        catch (FileNotFoundException e) {

            System.err.println(
                    "StringIterator: File \"" + filename + "\" not found.");

        }

        System.out.println("error in input file has corrupted the previous Gradebook," +
                "new MyGradeBook created.");

        return initialize();

    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from startingString
     *
     * @param startingString
     *            String that contains the initial grade book, which is
     *            formatted like initial.txt
     * @return a MyGradebook that contains the grade book from startingString
     */

    public static MyGradeBook initializeWithString(String startingString) {

        Scanner input = new Scanner(startingString);


        return processGradeBook(input);

    }



    /**
     * processes the input string and turns it into a MyGradeBook
     * 
     * 
     * 
     * @param input input from the user interface
     * @return the gradebook representation of the string
     */
    private static MyGradeBook processGradeBook(Scanner input) {
        //check to see if the file is a gradeBook file
        if (input.nextLine().equals("GRADEBOOK")) {

            MyGradeBook newBook = initialize();

            String aNames = input.nextLine().trim();


            //System.out.println(aNames);

            String maxPoints = input.nextLine().trim();
            //System.out.println(maxPoints);

            String weights = input.nextLine().trim();
            //System.out.println(weights);

            //separates the three lines containing assignment grades into 
            //arrays that have parallel information

            String[] aNamesArray = aNames.split("\t");
            String[] maxPointsArray = maxPoints.split("\t");
            String[] weightsArray = weights.split("\t");


            //iterates through the array list and creates appropriate 
            //assignmnets
            for (int i = 0; i < aNamesArray.length; i++) {

                String name  = aNamesArray[i];
                double max = Double.parseDouble(maxPointsArray[i]);
                double weight = Double.parseDouble(weightsArray[i]);


                //System.out.println(name);
                //System.out.println(max);
                //System.out.println(weight);

                Assignment a = new Assignment(name, max, weight);

                newBook.allAssignments.add(a);
            }

            //picks through all the students filling in their information
            //and giving them assignment grades
            while (input.hasNext()) {


                String nextLine = input.nextLine();
                //System.out.println("nextLine: " + nextLine);
                String[] aLine = nextLine.split("\t");

                String username = aLine[0];
                String firstname = aLine[1];
                String middlename = aLine[2];
                String lastname = aLine[3];
                int gradYear = Integer.parseInt(aLine[4]);
                //System.out.println(gradYear);

                Student s = new Student(username, firstname, middlename, lastname, gradYear);
                newBook.students.add(s);

                //System.out.println(aLine.length);
                for (int n = 0; n < (aLine.length - 5); n++) {

                    Assignment aToAdd = newBook.allAssignments.get(n);
                    double grade = Double.parseDouble(aLine[n+5]);

                    //  System.out.println(aToAdd.name + " : " + grade);

                    s.grades.put(aToAdd, grade);
                    s.grades.get(aToAdd);
                }

            }    


            return newBook;

        }
        //if the file is not a valid gradebook file, an empty gradebook is 
        //returned
        return initialize();
    }

    /**
     * Add to the state of this grade book---new assignments, new students, new
     * grades---by processing filename
     *
     * @param filename
     *            the filename for a file that contains information that will be
     *            added to the grade book. The file could contain several
     *            different types of information---new assignments, new
     *            students, new grades. The file will be formatted like
     *            addAssignments.txt, addStudents.txt, gradesForAssignment1.txt,
     *            and gradesForStudent.txt.
     */
    public void processFile(String filename) {

        try {


            File file = new File(filename);
            Scanner input = new Scanner(file);

            this.process(input);


        }
        catch (FileNotFoundException e) {

            System.err.println(
                    "StringIterator: File \"" + filename + "\" not found.");

        }

    }

    /**
     * Add to the state of this grade book---new assignments, new students, new
     * grades---by processing additionalString
     *
     * @param additionalString
     *            String that contains information that will be added to the
     *            grade book. The String could contain several different types
     *            of information---new assignments, new students, new grades.
     *            The String will be formatted like addAssignments.txt,
     *            addStudents.txt, gradesForAssignment1.txt, and
     *            gradesForStudent.txt.
     */

    public void processString(String additionalString) {

        Scanner input = new Scanner(additionalString);

        this.process(input);


    }

    /**
     * processes update input for various types of updates
     * the input is systematically picked apart
     * 
     * 
     * @param input the input from the user interface
     */
    private void process(Scanner input) {

        //will keep picking through input stream until there is nothing left
        while(input.hasNext()) {


            String type = input.nextLine();

            if (type.equals("GRADES_FOR_ASSIGNMENT")) {

                this.processAssignmentGrades(input);

            }

            else if (type.equals("GRADES_FOR_STUDENT")) {

                this.processStudentGrades(input);
            }
            //this part of the assignment will repeat until all assignments and
            //students information is processed
            if (type.equals("ASSIGNMENT")) {

                this.addAssignment(input);

            } 
            else if (type.equals("STUDENT")) {

                this.addStudent(input);

            }


        }
    }


    /**
     * processes the inputfor asignments
     * 
     * 
     * 
     * @param input the input to be processsed
     * 
     */
    private void addAssignment(Scanner input) {

        String name = input.nextLine();
        //System.out.println(name);

        int max = input.nextInt();
        //System.out.println(max);

        double percent = input.nextDouble();
        //System.out.println(percent);

        Assignment a = new Assignment(name, max, percent);
        this.allAssignments.add(a);

        if(input.hasNext()) {
            String burn = input.nextLine();
        }  
        this.addZerosA(a);
    } 
    /**
     * processes a block of update student text and
     * adds the student to the gradebook
     * 
     * 
     * @param input the information to be processed
     */
    private void addStudent(Scanner input) {

        String username = input.nextLine();
        //System.out.println(username);

        String firstname = input.nextLine();
        //System.out.println(firstname);

        String middlename = input.nextLine();
        //System.out.println(middlename);

        String lastname = input.nextLine();
        //System.out.println(lastname);


        int gradyear = input.nextInt();
        //System.out.println(gradyear);

        Student newGuy = new Student(username, firstname, middlename, lastname, gradyear);
        this.students.add(newGuy);
        this.addZerosS(newGuy);

        if(input.hasNext()) {
            String burn = input.nextLine();
        }  

    }

    /**
     * reads the input for a file formatted for assignment grades
     * 
     * 
     * 
     * @param input the file to be processed
     */
    private void processAssignmentGrades(Scanner input) {

        Assignment a = this.getAssignment(input.nextLine());

        if (a == null) {

            System.out.println("error:  Cannot add grades for an assignment that does not exist");
        }
        else {

            while (input.hasNext()) {

                String userName = input.nextLine();
                double grade = input.nextInt();

                if(input.hasNext()) {
                    String burn = input.nextLine();
                } 

                Student s = this.getStudent(userName);
                if (s == null) {

                    System.out.println("error: " + userName + 
                            " does not match the username of any Students");

                }
                else {

                    s.grades.put(a, grade);
                    System.out.println(grade + " added to " + s.username + "for assignment: " + a.name);

                }
            }
            this.addZerosA(a);
        }
    }


    /**
     * processes a file formated for student grades
     * 
     * @param input the file to be processed
     */
    private void processStudentGrades(Scanner input) {

        Student s = this.getStudent(input.nextLine());

        if (s == null) {

            System.out.println("error: Cannot add grades to a student that does not exist");
        }
        else {

            while (input.hasNext()) {

                String assignmentName = input.nextLine();
                //System.out.println(assignmentName);
                double grade = input.nextDouble();
                //System.out.println(grade);

                if(input.hasNext()) {
                    String burn = input.nextLine();
                } 



                Assignment a = this.getAssignment(assignmentName);

                if (a == null) {

                    System.out.println("error: " + assignmentName + " does not match the assignment name of any assignments");

                }
                else {

                    s.grades.put(a, grade);
                    System.out.println(grade + " added to " + s.username + "for assignment: " + a.name);

                }
            }
        }
    }


    /**
     * Method gets the student with the given username
     * @param username of the student you want
     * @return student with the given username
     */
    private Student getStudent(String username) {
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                return s;
            }
        }
        return null;    
    }



    /**
     * Changes the assignment (named assignmentName) grade for student (whose
     * username is equal to username) to newGrade
     *
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @param newGrade
     *            the new grade for the given assignment and student
     * @return whether there was a grade to change. Returns true if the given
     *         assignment/student combination exists, returns false otherwise
     */

    public boolean changeGrade(String assignmentName, String username, double newGrade) {

        ArrayList<Student> students = this.students;

        for (Student s : students) {
            if(s.username.equals(username)) {
                Assignment a = getAssignment(assignmentName);
                s.grades.put(a, newGrade);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Method gets the hashmap of assignments and grades belonging
     * to the given student
     * @param s Student whose hashmap you want
     * @return HashMap<Assignment, Double> of grades for the given student
     */
    public HashMap<Assignment, Double> getStudentGrades(Student s) {
        return s.grades;
    }

    /**
     * Calculates the average across all students for a given assignment
     *
     * @param assignmentName
     *            name of the assignment
     * @return the average across all students for assignmentName
     */
    public double average(String assignmentName) {
        ArrayList<Double> grades = new ArrayList<Double>();
        double total = 0;

        for(Student s : this.students) {
            double grade = s.getAssignmentGrade(getAssignment(assignmentName));
            grades.add(grade);

            total = total + grade;
        }
        return total/grades.size();
    }

    /**
     * Method gets the assignment associated with the assignmentName
     * @param assignmentName name of the assignment
     * @return assignment with the name assignmentName
     * @throw RuntimeExcpetion if the assignmentName does not match any
     * of the assignments' names
     */
    private Assignment getAssignment(String assignmentName) {
        for (Assignment a : this.allAssignments) {

            if (a.name.equals(assignmentName)) {
                return a;
            }

        }
        return null;
    }

    /**
     * Method gets the grades of all students of a given assignment
     * @param a assignment whose grades you want
     * @return ArrayList<Double> of the grades associated with the given
     * assignment
     */
    /**
    private ArrayList<Double> getGrades(Assignment a) {
        ArrayList<Double> grades = new ArrayList<Double>();
        for (Student s : students) {
            grades.add(s.getAssignmentGrade(a));
        }
        return grades;
    }
     */


    /**
     * Calculates the median across all students for a given assignment
     *
     * @param assignmentName
     *            name of the assignment
     * @return the median across all students for assignmentName
     */

    public double median(String assignmentName) {
        ArrayList<Double> grades = new ArrayList<Double>();

        for(Student s : students) {
            double grade = s.getAssignmentGrade(getAssignment(assignmentName));
            grades.add(grade);
        }

        Collections.sort(grades);

        int size = grades.size();

        if (size%2 == 0) {
            double d1 = grades.get(grades.size()/2 - 1);
            double d2 = grades.get(grades.size()/2);

            return (d1 + d2)/2;
        }
        else {
            return grades.get(grades.size()/2);
        }
    }


    /**
     * Calculates the min across all students for a given assignment
     *
     * @param assignmentName
     *            name of the assignment
     * @return the min across all students for assignmentName
     */
    public double min(String assignmentName) {
        ArrayList<Double> grades = new ArrayList<Double>();

        for(Student s : students) {
            double grade = s.getAssignmentGrade(getAssignment(assignmentName));
            grades.add(grade);
        }

        Collections.sort(grades);

        return grades.get(0);
    }


    /**
     * Calculates the max across all students for a given assignment
     *
     * @param assignmentName
     *            name of the assignment
     * @return the max across all students for assignmentName
     */

    public double max(String assignmentName) {
        ArrayList<Double> grades = new ArrayList<Double>();

        for(Student s : students) {
            double grade = s.getAssignmentGrade(getAssignment(assignmentName));
            grades.add(grade);
        }

        Collections.sort(grades);

        return grades.get(grades.size() - 1);
    }


    /**
     * Calculates the current grade for the given student
     *
     * @param username
     *            username for the student
     * @return the current grade for student with username. The current grade is
     *         calculated based on the current assignment grades, assignment
     *         total points, assignment percent of semester. The current grade
     *         for a student is the sum of the relative assignment grades
     *         divided by the current percent of semester time 100. Since all
     *         grades may not currently be entered, we have to divide by the
     *         current percent. The relative assignment grade is the student's
     *         assignment grade divide by total point value for the assignment
     *         times the percent of semester.
     */

    public double currentGrade(String username) {

        Student s = getStudent(username);

        if (s == null) {
            System.out.println("the requested student does not exist");
            return 0;
        }

        HashMap<Assignment, Double> studentGrades = s.grades;

        Set<Assignment> assignments = studentGrades.keySet();

        double count = 0;
        double totalWeight = 0;

        for (Assignment a : assignments) {
            double assignmentGrade = assignmentGrade(a.name, username);
            double totalGrade = (assignmentGrade / a.totalPossible) * a.weight;

            count = totalGrade + count;
            totalWeight = totalWeight + a.weight;
        }
        return (count / totalWeight) * 100;
    }


    /**
     * Calculates the current grade for all students
     *
     * @return HashMap of the current grades for all students. The key of the
     *         HashMap is the username of the student. The value is the current
     *         grade for the associated student. The current grade is calculated
     *         based on the current assignment grades, assignment total points,
     *         assignment percent of semester. The current grade for a student
     *         is the sum of the relative assignment grades divided by the
     *         current percent of semester time 100. Since all grades may not
     *         currently be entered, we have to divide by the current percent.
     *         The relative assignment grade is the student's assignment grade
     *         divide by total point value for the assignment times the percent
     *         of semester.
     */
    public HashMap<String, Double> currentGrades() {

        ArrayList<Student> students = this.students;

        HashMap<String, Double> grades = new HashMap<String, Double>();

        for (Student s : students) {
            grades.put(s.username, currentGrade(s.username)); 
        }
        return grades;
    }


    /**
     * Provides the grade earned by the given student for the given assignment
     *
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @return the grade earned by username for assignmentName
     */
    public double assignmentGrade(String assignmentName, String username) {   

        Student s = getStudent(username);
        Assignment a = getAssignment(assignmentName);
        if (s == null) {
            System.out.println("Student does not exist");
            return 0;
        }

        if (a == null) {
            System.out.println("assignment does not exist");
            return 0;
        }
        else {

            return s.grades.get(a);
        }
    }

    /**
     * Provide a String that contains the current grades of all students in the
     * course
     *
     * @return a String that contains the current grades of all students in the
     *         course. The String should be formatted like
     *         currentGrades.txt---CURRENT_GRADES heading and each row: username
     *         followed by tab and current grade. The usernames will be listed
     *         alphabetically.
     */

    public String outputCurrentGrades() {
        String output = "CURRENT_GRADES";

        HashMap<String, Double> cg = this.currentGrades();
        ArrayList<String> names = new ArrayList<String>();

        for (String s : cg.keySet()) {
            names.add(s);
        }

        Collections.sort(names);

        for (String s : names) {
            output = output + "\n" + s + "\t" + cg.get(s);
        }

        return output;

    }


    /**
     * Provide a String that contains the current grades of the given student
     *
     * @param username
     *            username for student
     * @return a String that contains the current grades of username. The String
     *         should be formatted like studentGrades.txt---STUDENT_GRADES
     *         heading, student info, dividers, each assignment (assignment name
     *         followed by tab and assignment grade), and current grade.
     */

    public String outputStudentGrades(String username) {

        String output = "STUDENT_GRADES";
        Student student = this.getStudent(username);
        if(student == null) {
            System.out.println("the requested student: " + username +
                    "does not exist");

            return "error, requested student does not exist";

        }


        output += "\n" + student.username;
        output += "\n" + student.firstName;
        output += "\n" + student.middleName;
        output += "\n" + student.lastName;
        output += "\n" + student.gradYear;
        output += "\n----";

        for (Assignment a : student.grades.keySet()) {
            output += "\n" + a.name + "\t" + student.grades.get(a);
        }       

        output += "\n----\n";
        output += "CURRENT GRADE\n" + currentGrade(username);

        return output;

    }


    /**
     * Provide a String that contains the assignment grades of all students in
     * the course for the given assignment
     *
     * @param assignName
     *            name of the assignment
     * @return a String that contains the assignment grades of all students in
     *         the course for assignName. The String should be formatted like
     *         assignmentGrade.txt---ASSIGNMENT_GRADES heading, assignment info,
     *         dividers, each student (username followed by tab and assignment
     *         grade), and assignment stats. The usernames will be listed
     *         alphabetically.
     */

    public String outputAssignmentGrades(String assignName) {

        String output = "ASSIGNMENT_GRADES";

        Assignment a = this.getAssignment(assignName);

        output += "\n" + a.name;
        output += "\n" + a.totalPossible;
        output += "\n" + a.weight;
        output += "\n----";

        ArrayList<String> namesInOrder = new ArrayList<String>();

        for (Student s : this.students) {
            namesInOrder.add(s.username);
        }

        Collections.sort(namesInOrder);

        for (String s : namesInOrder) {

            output += "\n" + s + "\t" + this.assignmentGrade(assignName, s);

        }

        output += "\nSTATS";
        output += "\nAverage\t" + this.average(assignName);
        output += "\nMedian\t" + this.median(assignName);
        output += "\nMax\t" + this.max(assignName);
        output += "\nMin\t" + this.min(assignName);

        return output;

    }


    /**
     * Provide a String that contains the current grade book. This String could
     * be used to initialize a new grade book.
     *
     * @return a String that contains the current grade book. This String could
     *         be used to initialize a new grade book. The String should be
     *         formatted like gradebook.txt. The usernames will be listed
     *         alphabetically.
     */

    public String outputGradebook() {

        String output = "GRADEBOOK";

        String assignmentNames = "\t\t\t\t";
        String maxPoints = "\t\t\t\t";
        String percents = "\t\t\t\t";

        String studentsBlock = "";


        Collections.sort(this.students, new StudentComparatorByName());


        for (Assignment a : this.allAssignments) {
            assignmentNames += "\t" + a.name;
            maxPoints += "\t" + a.totalPossible;
            percents += "\t" + a.weight;
        }

        for (Student s : this.students) {

            studentsBlock += s.username;
            studentsBlock += "\t" + s.firstName;
            studentsBlock += "\t" + s.middleName;
            studentsBlock += "\t" + s.lastName;
            studentsBlock += "\t" + s.gradYear;

            for (Assignment a : this.allAssignments) {

                studentsBlock += "\t" + this.assignmentGrade(a.name, s.username);

            }

            studentsBlock += "\n";


        }

        output += "\n" + assignmentNames + "\n" + maxPoints + "\n" + percents;
        output += "\n" + studentsBlock;

        return output;

    }

    private void addZerosA(Assignment a) {

        for (Student s : this.students) {

            if (!s.grades.containsKey(a)) {
                s.grades.put(a, 0.0);
            }
        }
    }

    private void addZerosS(Student s) {
        for (Assignment a : this.allAssignments) {

            if (!s.grades.containsKey(a)) {
                s.grades.put(a, 0.0);
            }
        }

    }





    public void removeStudent(String username) {

        Student s = this.getStudent(username);
        if (s == null) {
            System.out.println(username + " does not exist");
        }
        else {
            System.out.println("student deleted");
            this.students.remove(s);
        }
    }

    /**
     * checks to see if the two MyGradeBooks are equal
     * @param o the other Mygradebook to be compared to this MyGradeBook
     * 
     * @return whether the gradebooks are equal or not
     */
    public boolean equals(Object o) {

        if (o == null || !(o instanceof MyGradeBook)) {
            return false;
        }
        else {
            MyGradeBook that = (MyGradeBook) o;

            Comparator<Student> sc = new StudentComparatorByName();
            Comparator<Assignment> ac = new AssignmentComparator();

            if ((this.students.size() != that.students.size()) ||
                    (this.allAssignments.size() != that.allAssignments.size())) {
                return false;
            }
            else {

                Collections.sort(this.students, sc);
                Collections.sort(that.students, sc);
                Collections.sort(this.allAssignments, ac);
                Collections.sort(that.allAssignments, ac);

                String bookOneString = this.outputGradebook();
                String bookTwoString = that.outputGradebook();

                return bookOneString.equals(bookTwoString);
            }

        }
    }
}


