package gradebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * user interface class
 * allen.ele@husky.neu.edu, patel.pre@husky.neu.edu, roconnorc@ccs.neu.edu
 * @author Elena Allen, Premal Patel, Ray O'Connor
 * @version 2014-04-11
 */
public class UserInterface {

    static MyGradeBook gb = MyGradeBook.initialize();





    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        gb = MyGradeBook.initializeWithFile("initial.txt");


        int question1 = 0;

        do {

            System.out.println("\n-------------------------------------\n" +
                    "Welcome to the MyGradeBook main menu,\n" +
                    "-------------------------------------\n" +
                    "please enter a valid request then press enter. \n" +
                    "****if at any point you wish to return to " +
                    "the main menu,**** \n******press 9 at a menu " +
                    "(not at a request for data)******\n"+
                    "------------------------------------------------------" +
                    "---\n" +
                    "enter 1 to create a new GradeBook \n" + 

                        "enter 2 to manage assignments in the GradeBook \n" +
                        "enter 3 to manage students \n" +
                        "enter 4 to view Assignment grades and other statistics\n"+
                        "enter 5 to save the state of the Gradebook\n" +
                        "enter 6 to load to the last save of the gradebook\n" +
                    "enter 7 to signout (be sure to save your work first).");

            question1 = demandInt();

            switch (question1) {

            case 1 :

                System.out.println("--------------------------------------\n" +
                        "You have requested to create a new GradeBook. \n" +
                        "Please choose the way you would like to create the " +
                        "new GradeBook \n" +
                        "---------------------------------------------------" +
                        "--------------\n" +
                        "enter 1 to create an empty new GradeBook \n" +
                        "enter 2 to create a new GradeBook from a filename\n" +
                        "enter 3 to create a new GradeBook from a string");

                int question2a = demandInt();

                switch (question2a) {

                case 1 :

                    gb = MyGradeBook.initialize();
                    System.out.println("the current working GradeBook is" +
                            " now a new Empty GradeBook.");

                    break;

                case 2 :

                    System.out.println("please enter the filename of the " +
                            "file that contains the initial GradeBook");
                    String filename = (scanner.next());
                    gb = MyGradeBook.initializeWithFile(filename);
                    System.out.println("working GradeBook replaced with " +
                            "a new GradeBook created from input filename.");
                    break;

                case 3 :
                    System.out.println("please CAREFULLY enter the string " +
                            "that will create the initial GradeBook");
                    String initString = "";

                    while (scanner.hasNext()) {
                        initString += (scanner.nextLine());
                    }

                    //System.out.println(initString);



                    gb = MyGradeBook.initializeWithString(initString);
                    System.out.println("old GradeBook replaced with a new " +
                            "GradeBook created from input String.");
                    break;



                default:
                    System.out.println("************************\n" + 
                            "redirected to main menu\n************************");
                }
                break;

            case 2 :

                System.out.println("you have selected to manage the " +
                        "existing GradeBook\n" +
                        "--------------------------------------------\n" +
                        "enter 1 to update with a file\n" +
                        "enter 2 to update with a string\n" +
                        "enter 3 to update a specific student's grade " +
                        "on a specific assignment\n" +
                        "enter 4 to add an assignment manually");
                int question2b = demandInt();


                switch(question2b) {

                case 1 :

                    System.out.println("please CAREFULLY enter the filename " +
                            "of the file.\n " +
                            "this will update the current GradeBook");
                    String updateFilename = (scanner.next());
                    gb.processFile(updateFilename);
                    System.out.println("previous GradeBook updated with " +
                            "the data from input file.");
                    break;

                case 2 :

                    System.out.println("please CAREFULLY enter the String " +
                            "that will update the current GradeBook");
                    String updateString = (scanner.next());
                    gb.processString(updateString);
                    System.out.println("previous GradeBook updated with the" +
                            " data from input String.");
                    break;

                case 3 :

                    System.out.println("----------------------------------" +
                            "----------------\n" + 
                            "you have selected to update a specific " +
                            "student's grade on a specific assignment\n" +
                            "please enter the username of the student");
                    String upUsername = (scanner.next());
                    System.out.println("username selected: " + upUsername +
                            "\nPlease enter a valid assignment name");
                    String upAssignment = (scanner.next());
                    System.out.println("assignment selected: " + upAssignment +
                            "\nPlease enter a new value for " + upUsername + 
                            "'s grade on " + upAssignment);


                    System.out.println("please enter the grade");


                    int upGrade = demandInt();



                    gb.changeGrade(upAssignment, upUsername, upGrade);

                    System.out.println(upUsername + "'s grade on " 
                            + upAssignment + " updated to: " + upGrade);

                    break;

                case 4 : 

                    System.out.println("You have selected to add an " +
                            "assignment manually\n" +
                            "please enter the assignment name\n");
                    String name = (scanner.next());
                    System.out.println(name);


                    System.out.println("enter the maximum point " +
                            "value");

                    int max = demandInt();


                    System.out.println("please enter the percent");

                    double percent = demandDouble();




                    //Assignment a = new Assignment(name, max, percent);
                    String input = "ASSIGNMENT\n" + name + "\n" + max + 
                            "\n" + percent;

                    System.out.println("ASSIGNMENT: " + name + "\n" + max + 
                            "\n" + percent);


                    gb.processString(input);

                default:
                    System.out.println("**************************\n" +
                            "redirected to main menu\n************************");
                }
                break;


            case 3 :

                System.out.println("Delete or Add students from this menu\n" +
                        "enter 1 to delete a student\n" +
                        "enter 2 to manually add a student");

                int question2c = demandInt();
                String burn = (scanner.nextLine());

                switch(question2c) {

                case 1: 

                    System.out.println("enter the username of the student " +
                            "you wish to delete");

                    String username = (scanner.next());
                    gb.removeStudent(username);



                    break;

                case 2 :

                    System.out.println("please enter the username of the " +
                            "new Student");
                    String username0 = ((scanner.nextLine()));

                    System.out.println("please enter the first name of the" +
                            " new Student");
                    String firstname0 = (scanner.nextLine());

                    System.out.println("please enter the middle name of " +
                            "the new Student");
                    String middlename0 = (scanner.nextLine());

                    System.out.println("please enter the last name of " +
                            "the new Student");
                    String lastname0 = (scanner.nextLine());

                    System.out.println("please enter the graduation year " +
                            "of the new Student");

                    int gradYear = demandInt();

                    String newStudent = "STUDENT\n" + 
                            username0 +
                            firstname0 +
                            middlename0 +
                            lastname0 +
                            gradYear;

                    gb.processString(newStudent);

                    break;

                default:
                    System.out.println("**************************\n" + 
                            "redirected to main menu\n************************");
                }

                break;

            case 4 :

                //statistics ask for specific assignment stats or
                System.out.println("You have selected to view Assignment " +
                        "Grades and other statistics\n" +
                        "please select what type of information you are " +
                        "interested in\n" +
                        "-------------------------------------------------\n" +
                        "enter 1 to find the average, median, max, or min for"+
                        " a specific assignment across all students\n" +
                        "enter 2 to find the current grades of all students\n"+
                        "enter 3 to view the current grades for a specific " +
                        "student\n" +
                        "enter 4 to view all grades for a specific " +
                        "assignmnet\n" +
                        "enter 5 to display the current state of the " +
                        "MyGradeBook\n" +
                        "enter 6 to check the equality of two seperate " +
                        "GradeBook files");



                int question2d = demandInt();

                switch(question2d) {

                case 1 :

                    //ask for what type of stat



                    System.out.println("Select a type of statistic to view\n" +
                            "-----------------------------------------\n" +
                            "enter 1 to find an average\n" +
                            "enter 2 to find a median\n" +
                            "enter 3 to find a max" +
                            "enter 4 to find a min");


                    int question3d1 = (scanner.nextInt());;
                    System.out.println("enter the name of the assignment" +
                            "that you would like information about");
                    String assign = (scanner.next());

                    switch(question3d1) {


                    case 1 :

                        //average across all students for one assignment
                        double average = gb.average(assign);
                        System.out.println("average for " + assign + "is : " +
                                average);

                        break;

                    case 2 :

                        //median
                        double median = gb.median(assign);
                        System.out.println("median for " + assign + "is : " +
                                median);

                        break;

                    case 3 :
                        //max
                        double max = gb.max(assign);
                        System.out.println("max for " + assign + "is : " +
                                max);


                        break;

                    case 4 : 
                        //min
                        double min = gb.min(assign);
                        System.out.println("min for " + assign + "is : " +
                                min);


                        break;
                    default:
                        System.out.println("**************************\n" + 
                                "redirected to main menu\n************************");
                    }
                    break;


                case 2 :

                    //current grade of all students
                    currentGradesHelper();

                    break;

                case 3 :
                    //current all grades of one student
                    studentGradesHelper();

                    break;

                case 4 : 
                    //current grades for a specific assignment
                    assignmentGradesHelper();


                    break;

                case 5 :
                    //Gradebook
                    System.out.println(gb.outputGradebook());

                    break;

                case 6 :
                    System.out.println("Please enter the filename of the " +
                            "first GradeBook file");

                    String filename1 = (scanner.next());
                    MyGradeBook book1 = MyGradeBook.initializeWithFile(filename1);

                    System.out.println("Please enter the filename of the " +
                            "second GradeBook file");

                    String filename2 = (scanner.next());
                    MyGradeBook book2 = MyGradeBook.initializeWithFile(filename2);

                    if (book1.equals(book2)) {
                        System.out.println("the books are the same");
                    }
                    else {
                        System.out.println("the books are not equal");
                    }
                default:
                    System.out.println("**************************\n" + 
                            "redirected to main menu\n************************");
                }




                break;

            case 5 :

                PrintStream output;
                try {
                    output = new PrintStream(new File("gradeBookSave.txt"));
                    output.println(gb.outputGradebook());


                    System.out.println("previous save file overwritten " +
                            "with the following data:\n" +
                            gb.outputGradebook());

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;

            case 6 :  

                gb = MyGradeBook.initializeWithFile("gradeBookSave.txt");

                break;

            case 7 :
                System.out.println("thank you for using MyGradeBook");

                break;

            default:
                System.out.println("**************************\n" + 
                        "redirected to main menu\n************************");
            }

        } while (question1 != 7);
        System.out.println("You have successfully exited the program");
    }

    private static void currentGradesHelper() {

        String asg = gb.outputCurrentGrades();

        System.out.println("enter 1 to view data\n" +
                "enter 2 to output data to a file");

        int dataQuestion = demandInt();
        switch(dataQuestion) {

        case 1 :
            System.out.println(asg);

            System.out.println("------------------------------------\n" +
                    "The following is the current grades for all students");

            System.out.println(asg);
            break;

        case 2 :
            System.out.println("------------------------------------\n" +
                    "please enter the name of the file you would like to " +
                    "output if you list the name \n" +
                    "of a file that already exists, it will be overwritten");



            Scanner scanner = new Scanner(System.in);
            String filename = (scanner .next());

            PrintStream output;
            try {
                output = new PrintStream(new File(filename));
                output.println(asg);
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            break;
        }

    }

    private static void studentGradesHelper() {
        System.out.println("please enter the username of the student" + 
                "that you wish to find information about");

        Scanner scanner = new Scanner(System.in);
        String username = (scanner.next());

        System.out.println("------------------------------------\n" +
                "The following is the current grades for " + username);
        String sg = gb.outputStudentGrades(username);
        System.out.println(sg);

        System.out.println("------------------------------------\n" +
                "please enter the name of the file you would like to" +
                " output if you list the name \n" +
                "of a file that already exists, it will be overwritten");

        String filename = (scanner.next());

        PrintStream output;
        try {
            output = new PrintStream(new File(filename));
            output.println(sg);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }

    private static void assignmentGradesHelper() {

        System.out.println("please CAREFULLY enter the name of" +
                "the assignment for which you would like all" +
                "the Student grades");
        Scanner scanner = new Scanner(System.in);
        String assignment0 = (scanner .next());

        String data = gb.outputAssignmentGrades(assignment0);

        System.out.println("------------------------------------\n" +
                "please enter the name of the file you would like to " +
                "output if you list the name \n" +
                "of a file that already exists, it will be overwritten");

        String filenameA = (scanner.next());

        PrintStream output2;
        try {
            output2 = new PrintStream(new File(filenameA));
            output2.println(data);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


    }

    private static int demandInt() {

        boolean error = true;
        int number = 9;

        while (error) {
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                number = (scanner.nextInt());
            }
            else {
                System.out.println("enter a valid input");
                scanner.next();
                continue;
            }
            error = false;
        }
        return number;

    }

    private static double demandDouble() {

        boolean error = true;
        double number = 0.0;

        while (error) {
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextDouble()) {
                number = (scanner.nextDouble());
            }
            else {
                System.out.println("enter a valid input");
                scanner.next();
                continue;
            }
            error = false;
        }
        return number;

    }


}