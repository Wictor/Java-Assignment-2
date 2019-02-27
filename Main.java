package ContactList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Program that manages a list of students and teachers
 *
 * @author  Wictor Sundstrom
 * @version 1.0
 * @since   2018-10-15
 */
public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static StudentList studentList = new StudentList();
    private static TeacherList teacherList = new TeacherList();
    private static FileOutputStream out;
    private static PrintStream printToFile;


    public static void main (String [] args) {
        boolean shutdown = false;
        menu();

        while(!shutdown) {

            switch (menuInput()) {
                case 1:
                    addPerson();
                    break;

                case 2:
                    editPerson();
                    break;

                case 3:
                    printContacts();
                    break;

                case 4:
                    searchPerson();
                    break;

                case 5:
                   removePerson();
                   break;

                case 6:
                  fileManagement();
                   break;

                case 7:
                   menu();
                   break;

                case 0:
                   shutdown = true;
            }
        }
    }

    /**
     * Handles the text for the menu
     */
    private static void menu () {
        System.out.println(
                "Menu:\n" +
                "\t1. Add a new person\n" +
                "\t2. Edit a person\n" +
                "\t3. Show person\n" +
                "\t4. Search person\n" +
                "\t5. Remove person\n" +
                "\t6. File management\n" +
                "\t7. Show menu\n" +
                "\t0. Quit");
    }

    /**
     * Choice between student and teacher
     */
    private static void addPerson() {
        System.out.println("What would you like to add?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");
        switch (roleInput()) {
            case 1:
                addStudent();
                break;

            case 2:
                addTeacher();
                break;

            default:
                System.out.println("Invalid input");

        }
    }
    /**
     * Method that creates a new object of the Student class
     */
    private static void addStudent(){
        System.out.print("Namn: ");
        String name = scan.next();
        System.out.print("Personal Identification Number: ");
        int personNr = scan.nextInt();
        System.out.print("Phone Number: ");
        int phoneNr = scan.nextInt();
        System.out.print("Address: ");
        String address = scan.next();
        System.out.print("Subject: ");
        String subject = scan.next();
        System.out.print("Grade: ");
        char grade = scan.next().charAt(0);

        Student newStudent = Student.createStudent(name, personNr, phoneNr, address, subject, grade);
        if(studentList.addNewPerson(newStudent)) {
            System.out.println("New Student added");
        }
    }
    /**
     * Method that creates a new object of the Teacher class
     */
    private static void addTeacher() {
        System.out.print("Namn: ");
        String name = scan.next();
        System.out.print("Personal Identification Number: ");
        int personNr = scan.nextInt();
        System.out.print("Phone Number: ");
        int phoneNr = scan.nextInt();
        System.out.print("Address: ");
        String address = scan.next();
        System.out.print("Salary: ");
        int salary = scan.nextInt();
        System.out.print("Years of experience: ");
        int experience = scan.nextInt();

        Teacher newTeacher = Teacher.createTeacher(name, personNr, phoneNr, address, salary, experience );
        if(teacherList.addNewPerson(newTeacher)){
            System.out.println("New Teacher added");
        }
    }

    /**
     * Choice between student and teacher
     */
    private static void editPerson() {
        System.out.println("What would you like to edit?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");
        switch (roleInput()) {
            case 1:
                editStudent();
                break;

            case 2:
                editTeacher();
                break;

            default:
                System.out.println("Invalid input");
        }
    }
    /**
     * Method that edits a Student by loading the object of with the personal identity number
     * the user inputs and then replacing it with new information that the user wants.
     */
    private static void editStudent() {

        String newName = "";
        String newAddress = "";
        String newSubject = "";
        int newPersonId = 0;
        int newPhoneNr = 0;
        char newGrade = 'C';


        System.out.println("Enter the Personal Identity number you wish to edit: ");
        int personID  = scan.nextInt();
        Student existingStudent = studentList.queryContact(personID);
        if(existingStudent == null) {
            System.out.println("There is no Student by that Personal Identity number");
        }

        else {
            newName = existingStudent.getName();
            newPersonId = existingStudent.getPersonNr();
            newPhoneNr = existingStudent.getPhoneNr();
            newAddress = existingStudent.getAddress();
            newSubject = existingStudent.getSubject();
            newGrade = existingStudent.getGrade();

        System.out.println("What do you want to change?\n" +
                "\t1. Name\n" +
                "\t2. Personal Identity Number\n" +
                "\t3. Phone number\n" +
                "\t4. Address\n" +
                "\t5. Subject\n" +
                "\t6. Grade\n" +
                "\t7. Everything");
        switch(roleInput()) {
            case 1:
                System.out.print("Name: ");
                newName = scan.next();
                break;
            case 2:
                System.out.print("Personal Identity Number: ");
                newPersonId = scan.nextInt();
                break;
            case 3:
                System.out.print("Phone: ");
                newPhoneNr = scan.nextInt();
                break;
            case 4:
                System.out.print("Address: ");
                newAddress = scan.next();
                break;
            case 5:
                System.out.print("Subject: ");
                newSubject = scan.next();
                break;
            case 6:
                System.out.print("Grade: ");
                newGrade = scan.next().charAt(0);
                break;
            case 7:
                System.out.print("Name: ");
                newName = scan.next();
                System.out.print("Personal Identity Number: ");
                newPersonId = scan.nextInt();
                System.out.print("Phone: ");
                newPhoneNr = scan.nextInt();
                System.out.print("Address: ");
                newAddress = scan.next();
                System.out.print("Subject: ");
                newSubject = scan.next();
                System.out.print("Grade: ");
                newGrade = scan.next().charAt(0);
                break;

            }
        }
        Student newStudent = Student.createStudent(newName, newPersonId, newPhoneNr, newAddress, newSubject, newGrade );
        boolean update = studentList.updatePerson(existingStudent, newStudent);
        if(update) {
            System.out.println("Student information has been updated");
        }
    }
    /**
     * Method that edits a Teacher by loading the object of with the personal identity number
     * the user inputs and then replacing it with new information that the user wants.
     */
    private static void editTeacher() {

        String newName = "";
        String newAddress = "";
        int newSalary = 0;
        int newPersonId = 0;
        int newPhoneNr = 0;
        int newExperience = 0;


        System.out.println("Enter the Personal Identity number you wish to edit: ");
        int personID  = scan.nextInt();
        Teacher existingTeacher = teacherList.queryContact(personID);
        if(existingTeacher == null) {
            System.out.println("There is no Teacher by that Personal Identity number");
        }

        else {
            newName = existingTeacher.getName();
            newPersonId = existingTeacher.getPersonNr();
            newPhoneNr = existingTeacher.getPhoneNr();
            newAddress = existingTeacher.getAddress();
            newSalary = existingTeacher.getSalary();
            newExperience = existingTeacher.getExperience();

            System.out.println("What do you want to change?\n" +
                    "\t1. Name\n" +
                    "\t2. Personal Identity Number\n" +
                    "\t3. Phone number\n" +
                    "\t4. Address\n" +
                    "\t5. Subject\n" +
                    "\t6. Grade\n" +
                    "\t7. Everything");
            switch(roleInput()) {
                case 1:
                    System.out.print("Name: ");
                    newName = scan.next();
                    break;
                case 2:
                    System.out.print("Personal Identity Number: ");
                    newPersonId = scan.nextInt();
                    break;
                case 3:
                    System.out.print("Phone: ");
                    newPhoneNr = scan.nextInt();
                    break;
                case 4:
                    System.out.print("Address: ");
                    newAddress = scan.next();
                    break;
                case 5:
                    System.out.print("Salary: ");
                    newSalary = scan.nextInt();
                    break;
                case 6:
                    System.out.print("Experience: ");
                    newExperience = scan.nextInt();
                    break;
                case 7:
                    System.out.print("Name: ");
                    newName = scan.next();
                    System.out.print("Personal Identity Number: ");
                    newPersonId = scan.nextInt();
                    System.out.print("Phone: ");
                    newPhoneNr = scan.nextInt();
                    System.out.print("Address: ");
                    newAddress = scan.next();
                    System.out.print("Subject: ");
                    newSalary = scan.nextInt();
                    System.out.print("Grade: ");
                    newExperience = scan.nextInt();
                    break;

            }
        }
        Teacher newTeacher = Teacher.createTeacher(newName, newPersonId, newPhoneNr, newAddress, newSalary, newExperience );
        boolean update = teacherList.updatePerson(existingTeacher, newTeacher);
        if(update) {
            System.out.println("Teacher information has been updated");
        }
    }

    /**
     * Choice between student and teacher and then prints the selected
     */
    private static void printContacts() {
        System.out.println("What would you like to print?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");

        switch (roleInput()) {
            case 1:
                System.out.println(studentList.printContacts());
                break;

            case 2:
                System.out.println(teacherList.printContacts());
                break;

            default:
                System.out.println("Invalid input");
        }
    }

    /**
     * Choice between student and teacher
     */
    private static void searchPerson() {
        System.out.println("What would you like to search?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");
        switch (roleInput()) {
            case 1:
                searchStudent();
                break;

            case 2:
                searchTeacher();
                break;

            default:
                System.out.println("Invalid input");
        }
    }
    /**
     * Method to search for a Student by looking at the Personal identity number and comparing it to existing Students
     */
    private static void searchStudent() {
        System.out.println("Enter the Personal Identity number: ");
        int personID  = scan.nextInt();
        Student existingStudent = studentList.queryContact(personID);
        if(existingStudent == null) {
            System.out.println("There is no Student by that Personal Identity number");
        }
        else {
            System.out.println(existingStudent.getName() + "," +
                    existingStudent.getPersonNr() + "," +
                    existingStudent.getPhoneNr() + "," +
                    existingStudent.getAddress() + "," +
                    existingStudent.getSubject() + "," +
                    existingStudent.getGrade());
        }
    }
    /**
     * Method to search for a Teacher by looking at the Personal identity number and comparing it to existing Teachers
     */
    private static void searchTeacher() {
        System.out.println("Enter the Personal Identity number: ");
        int personID  = scan.nextInt();
        Teacher existingTeacher = teacherList.queryContact(personID);
        if(existingTeacher == null) {
            System.out.println("There is no Teacher by that Personal Identity number");
        }
        else {
            System.out.println(existingTeacher.getName() + "," +
                    existingTeacher.getPersonNr() + "," +
                    existingTeacher.getPhoneNr() + "," +
                    existingTeacher.getAddress() + "," +
                    existingTeacher.getSalary() + "," +
                    existingTeacher.getExperience());
        }
    }

    /**
     * Choice between student and teacher
     */
    private static void removePerson() {
        System.out.println("What would you like to remove?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");
        switch (roleInput()) {
            case 1:
                removeStudent();
                break;

            case 2:
                removeTeacher();
                break;

            default:
                System.out.println("Invalid input");
        }
    }
    /**
     * Method to remove Student by looking for the Personal identity number and comparing it to existing students
     */
    private static void removeStudent() {
        System.out.println("Enter the Personal Identity number of the person you want to remove: ");
        int personNr = scan.nextInt();
        Student existingContact = studentList.queryContact(personNr);
        if (existingContact == null) {
            System.out.println("There are no Student with that Personal Identity Number");
        }
        else {
            studentList.removeContact(existingContact);
            System.out.println("Student removed");
        }
    }
    /**
     * Method to remove Teachers by looking for the Personal identity number and comparing it to existing teachers
     */
    private static void removeTeacher(){
        System.out.println("Enter the Personal Identity number of the person you want to remove: ");
        int personNr = scan.nextInt();
        Teacher existingContact = teacherList.queryContact(personNr);
        if (existingContact == null) {
            System.out.println("There are no Teacher with that Personal Identity Number");
        }
        else {
            teacherList.removeContact(existingContact);
            System.out.println("Teacher removed");
        }
    }

    /**
     * Choice between save or load file
     */
    private static void fileManagement() {
        System.out.println("\t1. Save to file\n" +
                "\t2. Load from file");
        switch (roleInput()) {
            case 1:
                saveToFile();
                break;

            case 2:
                readFromFile();
                break;

            default:
             System.out.println("Invalid input");
        }
    }
    /**
     * Choice between student and teacher
     */
    private static void saveToFile() {
        System.out.println("What would you save?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");

        switch (roleInput()) {
            case 1:
                saveStudentsToFile();
                break;

            case 2:
                saveTeachersToFile();
                break;

            default:
                System.out.println("Invalid input");
        }

    }
    /**
     * Method that creates a file called Students.txt and print the contact list to that txt file
     */
    private static void saveStudentsToFile() {
        try {
            out = new FileOutputStream("c:/Students.txt");
            printToFile = new PrintStream(out);
            printToFile.print (studentList.printContacts());
            System.out.println("Print to Students.txt file complete");
            printToFile.close();
        }
        catch (Exception ignored){
        }
    }
    /**
     * Method that creates a file called Teachers.txt and print the contact list to that txt file
     */
    private static void saveTeachersToFile()  {
        try {
            out = new FileOutputStream("c:/Teachers.txt");
            printToFile = new PrintStream(out);
            printToFile.print (teacherList.printContacts());
            System.out.println("Print to Teachers.txt file complete");
            printToFile.close();
        }
        catch (Exception ignored){
        }

    }

    /**
     * Choice between student and teacher
     */
    private static void readFromFile() {
        File f;
        System.out.println("What would you read in?\n" +
                "\t1. Student\n" +
                "\t2. Teacher");

        switch (roleInput()) {

            case 1:
                f = new File("c:/Students.txt");
                if(f.exists() && !f.isDirectory()) {
                    readStudentsFromFile();
                }
                else {
                    System.out.println("No file exists");
                }
                break;

            case 2:
                f = new File("c:/Teachers.txt");
                if(f.exists() && !f.isDirectory()) {
                    readTeachersFromFile();
                }
                else {
                    System.out.println("No file exists");
                }
                break;

            default:
                System.out.println("Invalid input");
        }
    }
    /**
     * Method that reads a file called Students.txt and creates new objects with the data from that txt file
     */
    private static void readStudentsFromFile() {
        try {
            Scanner input = new Scanner(new File("c:/Students.txt"));
            input.useDelimiter(",|\n");

            while(input.hasNext()) {
                String name = input.next();
                int personNr = input.nextInt();
                int phoneNr = input.nextInt();
                String address = input.next();
                String subject = input.next();
                char grade = input.next().charAt(0);

                Student newStudent = Student.createStudent(name, personNr, phoneNr, address, subject, grade);

                studentList.addNewPerson(newStudent);
              }
            System.out.println("Students.txt has been added");
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method that reads a file called Teachers.txt and creates new objects with the data from that txt file
     */
    private static void readTeachersFromFile()  {
        try {
            Scanner input = new Scanner(new File("c:/Teachers.txt"));
            input.useDelimiter(",|\n");

            while(input.hasNext()) {
                String name = input.next();
                int personNr = input.nextInt();
                int phoneNr = input.nextInt();
                String address = input.next();
                int salary = input.nextInt();
                int experience = input.nextInt();

                Teacher newTeacher = Teacher.createTeacher(name, personNr, phoneNr, address, salary, experience);

                teacherList.addNewPerson(newTeacher);
                 }
            System.out.println("Teachers.txt has been added");
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Input for the menu
     * @return uses the scanner to return the value and using a switch to check what option was chosen
     */
    private static int menuInput() {
        System.out.print("Choose menu option (Press 7 to see menu options): ");
        return scan.nextInt();
    }
    /**
     * Input for the navigation in the program (expect from menu)
     * @return uses the scanner to return the value and using a switch to check what option was chosen
     */
    private static int roleInput() {
        System.out.print("Choose option: ");
        return scan.nextInt();
    }
}
