/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.Persona.Faculty.FacultyAssignment;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseOffer {

     Course course;
    ArrayList<Seat> seatlist;
    FacultyAssignment facultyassignment;
    private String syllabus; 
    private boolean enrollmentOpen = true;  
    private String schedule; 

    
    public CourseOffer(Course c) {
        course = c;
        seatlist = new ArrayList();
    }
    
    public void AssignAsTeacher(FacultyProfile fp) {
        facultyassignment = new FacultyAssignment(fp, this);
    }
    
    public FacultyProfile getFacultyProfile() {
        return facultyassignment.getFacultyProfile();
    }
    
    public String getCourseNumber() {
        return course.getCOurseNumber();
    }
    
    public void generatSeats(int n) {
        for (int i = 0; i < n; i++) {
            seatlist.add(new Seat(this, i));
        }
    }
    
    public Seat getEmptySeat() {
        for (Seat s : seatlist) {
            if (!s.isOccupied()) {
                return s;
            }
        }
        return null;
    }
    
    public SeatAssignment assignEmptySeat(CourseLoad cl) {
        Seat seat = getEmptySeat();
        if (seat == null) {
            return null;
        }
        SeatAssignment sa = seat.newSeatAssignment(cl);
        cl.registerStudent(sa);
        return sa;
    }
    
    public int getTotalCourseRevenues() {
        int sum = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied() == true) {
                sum = sum + course.getCoursePrice();
            }
        }
        return sum;
    }
    
    public Course getSubjectCourse(){
        return course;
    }
    
    public int getCreditHours(){
        return course.getCredits();
    }
    
    // ========== ADD THESE NEW METHODS ==========
    
    /**
     * Get all enrolled students (seat assignments) for this course
     * Used by faculty to view enrolled students
     * @return List of seat assignments for enrolled students
     */
    public ArrayList<SeatAssignment> getEnrolledStudents() {
        ArrayList<SeatAssignment> enrolled = new ArrayList<>();
        for (Seat s : seatlist) {
            if (s.isOccupied()) {
                SeatAssignment sa = s.getSeatAssignment();
                if (sa != null) {
                    enrolled.add(sa);
                }
            }
        }
        return enrolled;
    }
    
    /**
     * Get number of enrolled students in this course
     * @return Enrollment count
     */
    public int getEnrollmentCount() {
        int count = 0;
        for (Seat s : seatlist) {
            if (s.isOccupied()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Get number of available seats
     * @return Number of empty seats
     */
    public int getAvailableSeats() {
        int available = 0;
        for (Seat s : seatlist) {
            if (!s.isOccupied()) {
                available++;
            }
        }
        return available;
    }
    
    /**
     * Get total capacity (total seats)
     * @return Total number of seats
     */
    public int getTotalSeats() {
        return seatlist.size();
    }
    
    // ========== COURSE MANAGEMENT METHODS (For Faculty) ==========
    
    /**
     * Get course syllabus
     * @return Syllabus text
     */
    public String getSyllabus() {
        return syllabus;
    }
    
    /**
     * Update course syllabus (faculty only)
     * @param syllabus New syllabus content
     */
    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }
    
    /**
     * Check if enrollment is open
     * @return true if students can enroll
     */
    public boolean isEnrollmentOpen() {
        return enrollmentOpen;
    }
    
    /**
     * Open course enrollment (faculty only)
     */
    public void openEnrollment() {
        this.enrollmentOpen = true;
    }
    
    /**
     * Close course enrollment (faculty only)
     */
    public void closeEnrollment() {
        this.enrollmentOpen = false;
    }
    
    /**
     * Update course details (title would be in Course.java)
     * This method allows updating enrollment status
     */
    public void updateEnrollmentStatus(boolean open) {
        this.enrollmentOpen = open;
    }
    public String getSchedule() {
    return schedule;
}

public void setSchedule(String schedule) {
    this.schedule = schedule;
}

public void updateCapacity(int newCapacity) {
    int currentSize = seatlist.size();
    if (newCapacity > currentSize) {
        generatSeats(newCapacity - currentSize);
    }
}
}
