/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

import Business.Profiles.FacultyProfile;
import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class CourseOffer {
    String offerId;
    Course course;
    String semester;
    FacultyProfile assignedFaculty;
    String schedule;
    String roomLocation;
    int capacity;
    boolean enrollmentOpen;
    String syllabus;
    ArrayList<CourseEnrollment> enrollments;

    public CourseOffer(String offerId, Course course, String semester, int capacity) {
        this.offerId = offerId;
        this.course = course;
        this.semester = semester;
        this.capacity = capacity;
        this.enrollmentOpen = true;
        this.enrollments = new ArrayList<>();
    }

    public String getOfferId() {
        return offerId;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public FacultyProfile getAssignedFaculty() {
        return assignedFaculty;
    }

    public void setAssignedFaculty(FacultyProfile assignedFaculty) {
        this.assignedFaculty = assignedFaculty;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEnrollmentOpen() {
        return enrollmentOpen;
    }

    public void setEnrollmentOpen(boolean enrollmentOpen) {
        this.enrollmentOpen = enrollmentOpen;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public ArrayList<CourseEnrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(CourseEnrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(CourseEnrollment enrollment) {
        enrollments.remove(enrollment);
    }

    public int getEnrolledCount() {
        return enrollments.size();
    }

    public boolean isFull() {
        return enrollments.size() >= capacity;
    }

    public double getTotalTuitionCollected() {
        double total = 0.0;
        for (CourseEnrollment e : enrollments) {
            if (e.isTuitionPaid()) {
                total += e.getTuitionAmount();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return course.getCourseId() + " - " + semester;
    }
}
