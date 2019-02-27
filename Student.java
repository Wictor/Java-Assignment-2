package ContactList;

/**
 * Student class is a subclass to Person
 */
class Student extends Person {
    private String subject;
    private char grade;

    /**
     * Constructor for the class Student
     * @param name gets a name from the superclass
     * @param personNr gets a personal identity number from the superclass
     * @param phoneNr gets a phone number from the superclass
     * @param address gets an address from the superclass
     * @param subject gets a subject from user input
     * @param grade gets a grade from user input
     */
    private Student(String name, int personNr, int phoneNr, String address, String subject, char grade)
    {
        super(name, personNr, phoneNr, address);
        this.subject = subject;
        this.grade = grade;
    }

    /**
     * get method to handle the subject of the active person
     * @return Returns the subject of the active person
     */
    String getSubject() {
        return subject;
    }

    /**
     * get method to handle the grade of the active person
     * @return Returns the grade of the active person
     */
    char getGrade() {
        return grade;
    }

    /**
     * Method to create an object of the Student.
     * @param name Every Student need a name
     * @param personNr Every Student need a personal identity number
     * @param phoneNr Every Student need a phone number
     * @param address Every Student need an address
     * @param subject Every Student need a subject
     * @param grade Every Student need a grade
     * @return Returns the newly created Student object for use in StudentList
     */
    static Student createStudent(String name, int personNr, int phoneNr, String address, String subject, char grade) {
        return new Student(name, personNr, phoneNr, address, subject, grade);


    }
}

