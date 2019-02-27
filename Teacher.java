package ContactList;

/**
 * Teacher class is a subclass to Person
 */
class Teacher extends Person {
    private int salary;
    private int experience;

    /**
     * Constructor for the class Teacher
     * @param name gets a name from the superclass
     * @param personNr gets a personal identity number from the superclass
     * @param phoneNr gets a phone number from the superclass
     * @param address gets an address from the superclass
     * @param salary gets a salary from user input
     * @param experience gets an experience from user input
     */
    private Teacher(String name, int personNr, int phoneNr, String address, int salary, int experience)
    {
        super(name, personNr, phoneNr, address);
        this.salary = salary;
        this.experience = experience;
    }

    /**
     * get method to handle the salary of the active person
     * @return Returns the salary of the active person
     */
    int getSalary() {
        return salary;
    }

    /**
     * get method to handle the experience of the active person
     * @return Returns the experience of the active person
     */
    int getExperience() { return experience;}

    /**
     * Method to create an object of the Teacher.
     * @param name Every Teacher need a name
     * @param personNr Every Teacher need a personal identity number
     * @param phoneNr Every Teacher need a phone number
     * @param address Every Teacher need an address
     * @param salary Every Teacher need a salary
     * @param experience Every Teacher need an experience
     * @return Returns the newly created Teacher object for use in TeacherList
     */
    static Teacher createTeacher(String name, int personNr, int phoneNr, String address, int salary, int experience) {
        return new Teacher(name, personNr, phoneNr, address , salary, experience);
    }
}
