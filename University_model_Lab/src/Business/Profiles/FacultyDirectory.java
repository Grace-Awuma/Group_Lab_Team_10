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
public class FacultyDirectory {
    Business business;
    ArrayList<FacultyProfile> facultylist;

    public FacultyDirectory(Business b) {
        business = b;
        facultylist = new ArrayList<>();
    }

    public FacultyProfile newFacultyProfile(Person p) {
        FacultyProfile fp = new FacultyProfile(p);
        facultylist.add(fp);
        return fp;
    }

    public FacultyProfile findFaculty(String id) {
        for (FacultyProfile fp : facultylist) {
            if (fp.isMatch(id)) {
                return fp;
            }
        }
        return null;
    }

    public ArrayList<FacultyProfile> getFacultyList() {
        return facultylist;
    }

    public ArrayList<FacultyProfile> searchByName(String name) {
        ArrayList<FacultyProfile> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        for (FacultyProfile fp : facultylist) {
            if (fp.getPerson().getFullName().toLowerCase().contains(searchTerm)) {
                results.add(fp);
            }
        }
        return results;
    }

    public ArrayList<FacultyProfile> searchByDepartment(String dept) {
        ArrayList<FacultyProfile> results = new ArrayList<>();
        for (FacultyProfile fp : facultylist) {
            if (fp.getDepartment() != null && fp.getDepartment().equalsIgnoreCase(dept)) {
                results.add(fp);
            }
        }
        return results;
    }
}
