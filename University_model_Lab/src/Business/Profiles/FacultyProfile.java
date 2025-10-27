/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import Business.Person.Person;
import Business.Course.CourseOffer;
import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class FacultyProfile extends Profile {

    String department;
    String title;
    String officeLocation;
    String officeHours;
    ArrayList<CourseOffer> assignedCourses;

    public FacultyProfile(Person p) {
        super(p);
        this.assignedCourses = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Faculty";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public ArrayList<CourseOffer> getAssignedCourses() {
        return assignedCourses;
    }

    public void addAssignedCourse(CourseOffer courseOffer) {
        assignedCourses.add(courseOffer);
    }
}
