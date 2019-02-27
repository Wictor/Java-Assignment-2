package ContactList;

import java.util.ArrayList;

/**
 * Class that controls what goes into the Student Arraylist.
 */
class StudentList {
    private ArrayList<Student> studentList;

    /**
     * Constructor to activate the studentList
     */
    StudentList() {
        this.studentList = new ArrayList<>();
    }

    /**
     * Method to add new student
     * @param student Uses the object student to check existing students.
     */
    boolean addNewPerson(Student student) {
        if(findContact(student.getPersonNr()) >=0) {
            System.out.println("A Student with that Personal Identity Number already exists");
            return false;
        }
        else{
            studentList.add(student);
            return true;
        }
    }

    /**
     * Method to remove a student if one exist.
     * @param student Uses the object student to check existing students.
     */
    void removeContact(Student student) {
        int foundPosition = findContact(student);
        this.studentList.remove(foundPosition);
    }

    /**
     * Private method that uses object student to check if student exist.
     * @param student Uses the object student to check existing students.
     * @return Gives the index of the student that was searched for.
     */
    private int findContact(Student student) {
        return this.studentList.indexOf(student);
    }

    /**
     * Private method that uses user input and look for it with the help of other methods.
     * @param personID  takes personal identity number to look for the specific student.
     * @return Give the index of the person found with the personal identity number that was searched for.
     */
    private int findContact(int personID) {
        for(int i=0; i<this.studentList.size(); i++) {
            Student student = this.studentList.get(i);
            if(student.getPersonNr() == personID) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to look up a person without having to load the student object in public, instead that is done in the private method findContact
     * @param personNr Uses the personal identity number to find a Student.
     * @return Returns the index of the found person.
     */
    Student queryContact(int personNr) {
        int position = findContact(personNr);
        if(position >=0) {
            return this.studentList.get(position);
        }

        return null;
    }

    /**
     * Method to update an existing Student by looking for the index of the existing student and updating it with the new student at the same index.
     * @param currentStudent Takes the object of the existing student and looks for the index of it.
     * @param newStudent Replaces the old student with a new one created in main.
     * @return True/false depending on if it finds the teacher also to handle index out of bounds.
     */
    boolean updatePerson(Student currentStudent, Student newStudent) {
        int foundPosition = findContact(currentStudent);
        if(foundPosition >= 0) {
            this.studentList.set(foundPosition, newStudent);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to look up the name, personNr, phoneNr, address, subject and grade of existing students.
     * @return Returns the string of the existing students.
     */
    String printContacts() {
        String text = "";
        if(studentList.size() < 1)
        {
            System.out.println("You cannot print an empty list");
            return text;
        }
        else {
            for (int i = 0; i < this.studentList.size(); i++) {
                text += this.studentList.get(i).getName() + "," +
                        this.studentList.get(i).getPersonNr() + "," +
                        this.studentList.get(i).getPhoneNr() + "," +
                        this.studentList.get(i).getAddress() + "," +
                        this.studentList.get(i).getSubject() + "," +
                        this.studentList.get(i).getGrade() +
                        System.getProperty("line.separator");
            }
            return text;
        }
    }
}

