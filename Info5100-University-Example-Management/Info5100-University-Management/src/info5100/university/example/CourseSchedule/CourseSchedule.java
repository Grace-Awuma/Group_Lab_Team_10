/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseSchedule {
    CourseCatalog coursecatalog;
    ArrayList<CourseOffer> schedule;
    String semester;
    
    public CourseSchedule(String s, CourseCatalog cc) {
        semester = s;
        coursecatalog = cc;
        schedule = new ArrayList();
    }
    
    public CourseOffer newCourseOffer(String n) {
        Course c = coursecatalog.getCourseByNumber(n);
        if(c==null) return null;
        CourseOffer co;
        co = new CourseOffer(c);
        schedule.add(co);
        return co;
    }
    
    public CourseOffer getCourseOfferByNumber(String n) {
        for (CourseOffer co : schedule) {
            if (co.getCourseNumber().equals(n)) {
                return co;
            }
        }
        return null;
    }
    
    public int calculateTotalRevenues() {
        int sum = 0;
        for (CourseOffer co : schedule) {
            sum = sum + co.getTotalCourseRevenues();
        }
        return sum;
    }
    
    // ========== SEARCH METHODS (Required by Assignment) ==========
    
    /**
     * Search courses by course ID/number
     * @param courseNumber Course ID to search for (e.g., "INFO5100")
     * @return List of matching course offers
     */
    public ArrayList<CourseOffer> searchByCourseNumber(String courseNumber) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer co : schedule) {
            if (co.getCourseNumber().toLowerCase().contains(courseNumber.toLowerCase())) {
                results.add(co);
            }
        }
        return results;
    }
    
    /**
     * Search courses by instructor/faculty name
     * @param facultyName Faculty name to search for
     * @return List of matching course offers
     */
    public ArrayList<CourseOffer> searchByInstructor(String facultyName) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer co : schedule) {
            FacultyProfile faculty = co.getFacultyProfile();
            if (faculty != null) {
                // Assuming FacultyProfile has getFullName() or similar method
                String instructorInfo = faculty.toString();
                if (instructorInfo != null && instructorInfo.toLowerCase().contains(facultyName.toLowerCase())) {
                    results.add(co);
                }
            }
        }
        return results;
    }
    
    /**
     * Search courses by course name/title
     * @param courseName Course name to search for
     * @return List of matching course offers
     */
    public ArrayList<CourseOffer> searchByCourseName(String courseName) {
        ArrayList<CourseOffer> results = new ArrayList<>();
        for (CourseOffer co : schedule) {
            Course course = co.getSubjectCourse();
            if (course != null) {
                // Get course name - you may need to add getName() to Course.java
                String name = course.toString(); // Temporary - replace with course.getName()
                if (name != null && name.toLowerCase().contains(courseName.toLowerCase())) {
                    results.add(co);
                }
            }
        }
        return results;
    }
}