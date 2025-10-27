/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Person.Person;
import Business.Profiles.*;
import Business.UserAccounts.UserAccount;
import Business.Course.Course;
import Business.Course.CourseOffer;
import Business.Department.Department;

/**
 *
 * @author Yaksha
 */
public class ConfigureABusiness {
    
    public static Business configure() {
        Business business = new Business("Northeastern University");
        
        // Create Departments
        Department csis = business.getDepartmentDirectory().newDepartment("CSIS", "Computer and Information Science");
        Department engr = business.getDepartmentDirectory().newDepartment("ENGR", "Engineering");
        Department busn = business.getDepartmentDirectory().newDepartment("BUSN", "Business Administration");
        
        // Create Admin/Employee
        Person adminPerson = business.getPersonDirectory().newPerson("John", "Admin");
        adminPerson.setEmail("admin@northeastern.edu");
        adminPerson.setPhone("617-555-0100");
        EmployeeProfile adminProfile = business.getEmployeeDirectory().newEmployeeProfile(adminPerson);
        UserAccount adminAccount = business.getUserAccountDirectory().newUserAccount(adminProfile, "admin", "admin");
        
        // Create Registrar
        Person registrarPerson = business.getPersonDirectory().newPerson("Mary", "Johnson");
        registrarPerson.setEmail("m.johnson@northeastern.edu");
        registrarPerson.setPhone("617-555-0101");
        RegistrarProfile registrarProfile = business.getRegistrarDirectory().newRegistrarProfile(registrarPerson);
        registrarProfile.setOfficeLocation("Richards Hall 201");
        registrarProfile.setOfficeHours("Mon-Fri 9am-5pm");
        UserAccount registrarAccount = business.getUserAccountDirectory().newUserAccount(registrarProfile, "registrar", "registrar");
        
        // Create Faculty Members
        Person faculty1Person = business.getPersonDirectory().newPerson("Robert", "Smith");
        faculty1Person.setEmail("r.smith@northeastern.edu");
        faculty1Person.setPhone("617-555-0200");
        FacultyProfile faculty1 = business.getFacultyDirectory().newFacultyProfile(faculty1Person);
        faculty1.setDepartment("CSIS");
        faculty1.setTitle("Professor");
        faculty1.setOfficeLocation("West Village H 310");
        faculty1.setOfficeHours("Tue/Thu 2-4pm");
        UserAccount faculty1Account = business.getUserAccountDirectory().newUserAccount(faculty1, "rsmith", "password");
        
        Person faculty2Person = business.getPersonDirectory().newPerson("Jennifer", "Lee");
        faculty2Person.setEmail("j.lee@northeastern.edu");
        faculty2Person.setPhone("617-555-0201");
        FacultyProfile faculty2 = business.getFacultyDirectory().newFacultyProfile(faculty2Person);
        faculty2.setDepartment("CSIS");
        faculty2.setTitle("Associate Professor");
        faculty2.setOfficeLocation("West Village H 312");
        faculty2.setOfficeHours("Mon/Wed 3-5pm");
        UserAccount faculty2Account = business.getUserAccountDirectory().newUserAccount(faculty2, "jlee", "password");
        
        Person faculty3Person = business.getPersonDirectory().newPerson("David", "Chen");
        faculty3Person.setEmail("d.chen@northeastern.edu");
        faculty3Person.setPhone("617-555-0202");
        FacultyProfile faculty3 = business.getFacultyDirectory().newFacultyProfile(faculty3Person);
        faculty3.setDepartment("ENGR");
        faculty3.setTitle("Assistant Professor");
        faculty3.setOfficeLocation("Snell Engineering 420");
        faculty3.setOfficeHours("Wed/Fri 1-3pm");
        
        // Create Courses
        Course info5100 = business.getCourseDirectory().newCourse(
            "INFO 5100", "Application Engineering & Development", 
            "Object-oriented design and programming for application development", 4, "CSIS");
        
        Course info6205 = business.getCourseDirectory().newCourse(
            "INFO 6205", "Program Structure and Algorithms", 
            "Data structures, algorithms and complexity analysis", 4, "CSIS");
        
        Course info6150 = business.getCourseDirectory().newCourse(
            "INFO 6150", "Web Design and User Experience Engineering", 
            "Web development with focus on user experience", 4, "CSIS");
        
        Course info7245 = business.getCourseDirectory().newCourse(
            "INFO 7245", "Big Data Systems and Intelligence Analytics", 
            "Big data processing and analytics", 4, "CSIS");
        
        Course damg6210 = business.getCourseDirectory().newCourse(
            "DAMG 6210", "Data Management and Database Design", 
            "Database design, SQL, and data management", 4, "CSIS");
        
        // Create Course Offers for Fall 2025
        CourseOffer offer1 = business.getCourseOfferDirectory().newCourseOffer(
            "INFO5100-F25-01", info5100, "Fall 2025", 40);
        offer1.setAssignedFaculty(faculty1);
        offer1.setSchedule("Mon/Wed 6:00-7:30pm");
        offer1.setRoomLocation("West Village H 210");
        faculty1.addAssignedCourse(offer1);
        
        CourseOffer offer2 = business.getCourseOfferDirectory().newCourseOffer(
            "INFO6205-F25-01", info6205, "Fall 2025", 35);
        offer2.setAssignedFaculty(faculty2);
        offer2.setSchedule("Tue/Thu 6:00-7:30pm");
        offer2.setRoomLocation("West Village H 212");
        faculty2.addAssignedCourse(offer2);
        
        CourseOffer offer3 = business.getCourseOfferDirectory().newCourseOffer(
            "INFO6150-F25-01", info6150, "Fall 2025", 30);
        offer3.setAssignedFaculty(faculty1);
        offer3.setSchedule("Wed 6:00-9:00pm");
        offer3.setRoomLocation("West Village H 214");
        faculty1.addAssignedCourse(offer3);
        
        CourseOffer offer4 = business.getCourseOfferDirectory().newCourseOffer(
            "DAMG6210-F25-01", damg6210, "Fall 2025", 35);
        offer4.setAssignedFaculty(faculty2);
        offer4.setSchedule("Fri 6:00-9:00pm");
        offer4.setRoomLocation("West Village H 216");
        faculty2.addAssignedCourse(offer4);
        
        // Create Students
        Person student1Person = business.getPersonDirectory().newPerson("Alice", "Williams");
        student1Person.setEmail("williams.a@northeastern.edu");
        student1Person.setPhone("617-555-1001");
        StudentProfile student1 = business.getStudentDirectory().newStudentProfile(student1Person);
        student1.setDepartment("CSIS");
        student1.setProgram("MSIS");
        UserAccount student1Account = business.getUserAccountDirectory().newUserAccount(student1, "alice", "student");
        
        Person student2Person = business.getPersonDirectory().newPerson("Bob", "Taylor");
        student2Person.setEmail("taylor.b@northeastern.edu");
        student2Person.setPhone("617-555-1002");
        StudentProfile student2 = business.getStudentDirectory().newStudentProfile(student2Person);
        student2.setDepartment("CSIS");
        student2.setProgram("MSIS");
        UserAccount student2Account = business.getUserAccountDirectory().newUserAccount(student2, "bob", "student");
        
        Person student3Person = business.getPersonDirectory().newPerson("Carol", "Martinez");
        student3Person.setEmail("martinez.c@northeastern.edu");
        student3Person.setPhone("617-555-1003");
        StudentProfile student3 = business.getStudentDirectory().newStudentProfile(student3Person);
        student3.setDepartment("CSIS");
        student3.setProgram("MSIS");
        UserAccount student3Account = business.getUserAccountDirectory().newUserAccount(student3, "carol", "student");
        
        return business;
    }
}