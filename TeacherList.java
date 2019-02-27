package ContactList;

import java.util.ArrayList;

/**
 * Class that controls what goes into the Teacher Arraylist.
 */
class TeacherList {
    private ArrayList<Teacher> teacherList;

    /**
     * Constructor to activate the teacherList
     */
    TeacherList() {
            this.teacherList = new ArrayList<>();
        }

    /**
     * Method to add new student
     * @param teacher Uses the object teacher to check exisiting teachers.
     */
    boolean addNewPerson(Teacher teacher) {
            if(findContact(teacher.getPersonNr()) >=0) {
                System.out.println("A Teacher with that Personal Identity Number already exists");
                return false;
            }
            else{
                teacherList.add(teacher);
                return true;
            }
        }

    /**
     * Method to remove a teacher if one exist.
     * @param teacher Uses the object teacher to check existing teachers.
     */
    void removeContact(Teacher teacher) {
        int foundPosition = findContact(teacher);
        this.teacherList.remove(foundPosition);
    }

    /**
     * Private method that uses object teacher to check if teacher exist.
     * @param teacher Uses the object teacher to check existing teachers.
     * @return Gives the index of the teacher that was searched for.
     */
    private int findContact(Teacher teacher) {
            return this.teacherList.indexOf(teacher);
        }

    /**
     * Private method that uses user input and look for it with the help of other methods.
     * @param personID  takes personal identity number to look for the specific teacher.
     * @return Give the index of the person found with the personal identity number that was searched for.
     */
    private int findContact(int personID) {
        for(int i=0; i<this.teacherList.size(); i++) {
            Teacher teacher = this.teacherList.get(i);
            if(teacher.getPersonNr() == personID) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to look up a person without having to load the teacher object in public, instead that is done in the private method findContact
     * @param personNr Uses the personal identity number to find a Teacher.
     * @return Returns the index of the found person.
     */
    Teacher queryContact(int personNr) {
        int position = findContact(personNr);
        if(position >=0) {
            return this.teacherList.get(position);
        }

        return null;
    }

    /**
     * Method to update an existing Teacher by looking for the index of the existing teacher and updating it with the new teacher at the same index.
     * @param currentTeacher Takes the object of the existing teacher and looks for the index of it.
     * @param newTeacher Replaces the old teacher with a new one created in main.
     * @return True/false depending on if it finds the teacher also to handle index out of bounds.
     */
    boolean updatePerson(Teacher currentTeacher, Teacher newTeacher) {
        int foundPosition = findContact(currentTeacher);
        if(foundPosition >= 0) {
            this.teacherList.set(foundPosition, newTeacher);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to look up the name, personNr, phoneNr, address, salary and experience of existing teachers.
     * @return Returns the string of the existing teachers.
     */
    String printContacts() {
        String text = "";
        if(teacherList.size() < 1)
        {
            System.out.println("You cannot print an empty list");
            return text;
        }
        else {
            for (int i = 0; i < this.teacherList.size(); i++) {
                text += this.teacherList.get(i).getName() + "," +
                        this.teacherList.get(i).getPersonNr() + "," +
                        this.teacherList.get(i).getPhoneNr() + "," +
                        this.teacherList.get(i).getAddress() + "," +
                        this.teacherList.get(i).getSalary() + "," +
                        this.teacherList.get(i).getExperience() +
                        System.getProperty("line.separator");
            }
            return text;
        }
    }
}

