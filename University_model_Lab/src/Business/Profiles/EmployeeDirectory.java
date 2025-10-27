/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import Business.Business;
import Business.Person.Person;
import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class EmployeeDirectory {
    Business business;
    ArrayList<EmployeeProfile> employeelist;

    public EmployeeDirectory(Business b) {
        business = b;
        employeelist = new ArrayList<>();
    }

    public EmployeeProfile newEmployeeProfile(Person p) {
        EmployeeProfile ep = new EmployeeProfile(p);
        employeelist.add(ep);
        return ep;
    }

    public EmployeeProfile findEmployee(String id) {
        for (EmployeeProfile ep : employeelist) {
            if (ep.isMatch(id)) {
                return ep;
            }
        }
        return null;
    }

    public ArrayList<EmployeeProfile> getEmployeeList() {
        return employeelist;
    }

    public ArrayList<EmployeeProfile> searchByName(String name) {
        ArrayList<EmployeeProfile> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        for (EmployeeProfile ep : employeelist) {
            if (ep.getPerson().getFullName().toLowerCase().contains(searchTerm)) {
                results.add(ep);
            }
        }
        return results;
    }
}