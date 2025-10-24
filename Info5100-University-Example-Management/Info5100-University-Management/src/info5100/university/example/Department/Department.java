/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Department;

import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.Degree.Degree;
import info5100.university.example.Employer.EmployerDirectory;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */

    public class Department {
    String name;
    CourseCatalog coursecatalog;
    PersonDirectory persondirectory;
    StudentDirectory studentdirectory;
    FacultyDirectory facultydirectory;
    EmployerDirectory employerdirectory;
    Degree degree;

    HashMap<String, CourseSchedule> mastercoursecatalog;
    
    public Department(String n) {
        name = n;
        mastercoursecatalog = new HashMap<>();
        coursecatalog = new CourseCatalog(this);
        studentdirectory = new StudentDirectory(this);
        persondirectory = new PersonDirectory();
        degree = new Degree("MSIS");
    }
    
    public void addCoreCourse(Course c){
        degree.addCoreCourse(c);
    }
    
    public void addElectiveCourse(Course c){
        degree.addElectiveCourse(c);
    }
    
    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }
    
    public StudentDirectory getStudentDirectory() {
        return studentdirectory;
    }
    
    public CourseSchedule newCourseSchedule(String semester) {
        CourseSchedule cs = new CourseSchedule(semester, coursecatalog);
        mastercoursecatalog.put(semester, cs);
        return cs;
    }
    
    public CourseSchedule getCourseSchedule(String semester) {
        return mastercoursecatalog.get(semester);
    }
    
    public CourseCatalog getCourseCatalog() {
        return coursecatalog;
    }
    
    public Course newCourse(String n, String nm, int cr) {
        Course c = coursecatalog.newCourse(n, nm, cr);
        return c;
    }
    
    public int calculateRevenuesBySemester(String semester) {
        CourseSchedule css = mastercoursecatalog.get(semester);
        return css.calculateTotalRevenues();
    }
    
    /**
     * Register a student for a class with validation and tuition charging
     * Updated to use new enrollment logic with 8 credit limit and tuition
     */
    public boolean RegisterForAClass(String studentid, String cn, String semester) {
        // Find student
        StudentProfile sp = studentdirectory.findStudent(studentid);
        if (sp == null) {
            System.out.println("Student not found: " + studentid);
            return false;
        }
        
        // Get or create course load for semester
        CourseLoad cl = sp.getCourseLoadBySemester(semester);
        if (cl == null) {
            cl = sp.newCourseLoad(semester);
        }
        
        // Get course schedule for semester
        CourseSchedule cs = mastercoursecatalog.get(semester);
        if (cs == null) {
            System.out.println("Course schedule not found for semester: " + semester);
            return false;
        }
        
        // Get course offer
        CourseOffer co = cs.getCourseOfferByNumber(cn);
        if (co == null) {
            System.out.println("Course not found: " + cn);
            return false;
        }
        
        // Use new enrollment method with credit limit check and tuition charging
        SeatAssignment sa = cl.enrollInCourse(co, sp);
        
        if (sa != null) {
            System.out.println("Successfully enrolled " + studentid + " in " + cn);
            return true;
        } else {
            System.out.println("Failed to enroll " + studentid + " in " + cn);
            return false;
        }
    }
    
    /**
     * Drop a student from a class with refund
     */
    public boolean DropStudentFromClass(String studentid, String cn, String semester) {
        StudentProfile sp = studentdirectory.findStudent(studentid);
        if (sp == null) {
            System.out.println("Student not found: " + studentid);
            return false;
        }
        
        CourseLoad cl = sp.getCourseLoadBySemester(semester);
        if (cl == null) {
            System.out.println("No course load found for semester: " + semester);
            return false;
        }
        
        // Find the seat assignment for this course
        for (SeatAssignment sa : cl.getSeatAssignments()) {
            if (sa.getCourseOffer().getCourseNumber().equals(cn)) {
                return cl.dropCourse(sa, sp);
            }
        }
        
        System.out.println("Student is not enrolled in course: " + cn);
        return false;
    }
    }