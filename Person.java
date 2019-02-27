package ContactList;

/**
 * Class to handle input to the superclass and use the data in the subclasses
 */
class Person {
    private String name;
    private int personNr;
    private int phoneNr;
    private String address;

    /**
     * Constructor that accepts name, personNr, phoneNr and address. Things that every Teacher, Student and future roles have to have.
     * @param name gets a name from user input
     * @param personNr gets a personal identity number from user input
     * @param phoneNr gets a phone number from user input
     *@param address gets an address from user input
     */
    Person (String name, int personNr, int phoneNr, String address)
    {
        this.name = name;
        this.personNr = personNr;
        this.phoneNr = phoneNr;
        this.address = address;
    }

    /**
     * get method to handle the name of the active person
     * @return Returns the name of the active person
     */
    String getName() { return name; }

    /**
     * get method to handle the personal identity number of the active person
     * @return Returns the personal identity number of the active person
     */
    int getPersonNr() { return personNr; }

    /**
     * get method to handle the phone number of the active person
     * @return Returns the phone number of the active person
     */
    int getPhoneNr(){ return phoneNr; }

    /**
     * get method to handle the address of the active person
     * @return Returns the address of the active person
     */
    String getAddress() { return address; }

}