/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeDirectory;
import Business.Profiles.StudentDirectory;
import Business.Profiles.FacultyDirectory;
import Business.Profiles.RegistrarDirectory;
import Business.UserAccounts.UserAccountDirectory;
import Business.Course.CourseDirectory;
import Business.Course.CourseOfferDirectory;
import Business.Department.DepartmentDirectory;

/**
 *
 * @author Yaksha
 */
public class Business {
    String name;
    PersonDirectory persondirectory;
    EmployeeDirectory employeedirectory;
    StudentDirectory studentdirectory;
    FacultyDirectory facultydirectory;
    RegistrarDirectory registrardirectory;
    UserAccountDirectory useraccountdirectory;
    CourseDirectory coursedirectory;
    CourseOfferDirectory courseofferdirectory;
    DepartmentDirectory departmentdirectory;

    public Business(String n) {
        name = n;
        persondirectory = new PersonDirectory();
        employeedirectory = new EmployeeDirectory(this);
        studentdirectory = new StudentDirectory(this);
        facultydirectory = new FacultyDirectory(this);
        registrardirectory = new RegistrarDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        coursedirectory = new CourseDirectory();
        courseofferdirectory = new CourseOfferDirectory();
        departmentdirectory = new DepartmentDirectory();
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    public StudentDirectory getStudentDirectory() {
        return studentdirectory;
    }

    public FacultyDirectory getFacultyDirectory() {
        return facultydirectory;
    }

    public RegistrarDirectory getRegistrarDirectory() {
        return registrardirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public CourseDirectory getCourseDirectory() {
        return coursedirectory;
    }

    public CourseOfferDirectory getCourseOfferDirectory() {
        return courseofferdirectory;
    }

    public DepartmentDirectory getDepartmentDirectory() {
        return departmentdirectory;
    }
}
