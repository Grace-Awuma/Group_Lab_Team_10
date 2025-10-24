/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class CourseLoad {
    String semester;
    ArrayList<SeatAssignment> seatassignments;
    
    public CourseLoad(String s){
        seatassignments = new ArrayList();
        semester = s;
    }
    
    public SeatAssignment newSeatAssignment(CourseOffer co){
        Seat seat = co.getEmptySeat(); // seat linked to courseoffer
        if (seat==null) return null;
        SeatAssignment sa = seat.newSeatAssignment(this);
        seatassignments.add(sa);  //add to students course 
        return sa;
    }
    
    public void registerStudent(SeatAssignment sa){
        sa.assignSeatToStudent(this);
        seatassignments.add(sa);
    }
    
    public float getSemesterScore(){ //total score for a full semeter
        float sum = 0;
        for (SeatAssignment sa: seatassignments){
            sum = sum + sa.GetCourseStudentScore();
        }
        return sum;
    }
    
    public ArrayList<SeatAssignment> getSeatAssignments(){
        return seatassignments;
    }
    
    // ========== NEW ENROLLMENT LOGIC METHODS ==========
    
    /**
     * Get total credits for this course load (semester)
     * Used to enforce 8 credit limit
     */
    public int getTotalCredits() {
        int total = 0;
        for (SeatAssignment sa : seatassignments) {
            total += sa.getCourseCredits();
        }
        return total;
    }
    
    /**
     * Check if student can enroll in a course (8 credit limit)
     * @param courseOffer The course to check
     * @return true if enrollment would not exceed 8 credits
     */
    public boolean canEnrollInCourse(CourseOffer courseOffer) {
        int currentCredits = getTotalCredits();
        int courseCredits = courseOffer.getCreditHours();
        return (currentCredits + courseCredits) <= 8;
    }
    
    /**
     * Enroll student in a course with validation and tuition charging
     * @param courseOffer Course to enroll in
     * @param studentProfile Student enrolling
     * @return SeatAssignment if successful, null if failed
     */
    public SeatAssignment enrollInCourse(CourseOffer courseOffer, StudentProfile studentProfile) {
        // Check 8 credit limit
        if (!canEnrollInCourse(courseOffer)) {
            System.out.println("Cannot enroll: Would exceed 8 credit limit");
            return null;
        }
        
        // Check if enrollment is open for this course
        if (!courseOffer.isEnrollmentOpen()) {
        System.out.println("Cannot enroll: Enrollment is closed for this course");
        return null;
        }
        
        // Get empty seat from course offer
        Seat seat = courseOffer.getEmptySeat();
        if (seat == null) {
            System.out.println("Cannot enroll: Course is full");
            return null;
        }
        
        // Create seat assignment (this already adds to seatassignments)
        SeatAssignment sa = seat.newSeatAssignment(this);
        
        // Charge tuition through student account
        Course course = courseOffer.getSubjectCourse();
        double tuition = course.getCoursePrice();
        studentProfile.getStudentAccount().chargeTuition(
            tuition, 
            course.getCOurseNumber(), 
            course.getName() // Make sure Course.java has getName()
        );
        
        return sa;
    }
    
    /**
     * Drop a course and process refund
     * @param seatAssignment The seat assignment to drop
     * @param studentProfile Student dropping the course
     * @return true if successful
     */
    public boolean dropCourse(SeatAssignment seatAssignment, StudentProfile studentProfile) {
        if (!seatassignments.contains(seatAssignment)) {
            System.out.println("Student is not enrolled in this course");
            return false;
        }
        
        // Get course info for refund
        Course course = seatAssignment.getAssociatedCourse();
        double tuition = course.getCoursePrice();
        
        // Remove from course load
        seatassignments.remove(seatAssignment);
        
        // Free up the seat
        Seat seat = seatAssignment.getSeat();
        seat.releaseSeat(); // Make sure Seat.java has this method
        
        // Process refund through student account
        studentProfile.getStudentAccount().processRefund(
            tuition,
            course.getCOurseNumber(),
            course.getName()
        );
        
        return true;
    }
}