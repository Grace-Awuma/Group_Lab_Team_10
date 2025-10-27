/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Person;

import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class PersonDirectory {
    ArrayList<Person> personlist;
    private int personCounter = 1001;
    
    public PersonDirectory() {
        personlist = new ArrayList<>();
    }

    public Person newPerson(String firstName, String lastName) {
        String id = "PER" + String.format("%06d", personCounter++);
        Person p = new Person(id, firstName, lastName);
        personlist.add(p);
        return p;
    }

    public Person newPersonWithId(String id, String firstName, String lastName) {
        Person p = new Person(id, firstName, lastName);
        personlist.add(p);
        return p;
    }

    public Person findPerson(String id) {
        for (Person p : personlist) {
            if (p.isMatch(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean emailExists(String email) {
        for (Person p : personlist) {
            if (p.getEmail() != null && p.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Person> getPersonList() {
        return personlist;
    }

    public String generateId(String prefix) {
        return prefix + String.format("%06d", personCounter++);
    }
}
