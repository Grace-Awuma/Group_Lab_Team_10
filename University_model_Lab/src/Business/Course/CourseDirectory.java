/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class CourseDirectory {
   ArrayList<Course> courselist;

    public CourseDirectory() {
        courselist = new ArrayList<>();
    }

    public Course newCourse(String courseId, String courseName, String description, int creditHours, String department) {
        Course course = new Course(courseId, courseName, description, creditHours, department);
        courselist.add(course);
        return course;
    }

    public Course findCourse(String courseId) {
        for (Course course : courselist) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getCourseList() {
        return courselist;
    } 
}
