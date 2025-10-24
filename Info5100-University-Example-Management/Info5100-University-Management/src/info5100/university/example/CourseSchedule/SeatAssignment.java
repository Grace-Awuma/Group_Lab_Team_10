/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.CourseSchedule;

import info5100.university.example.CourseCatalog.Course;

/**
 *
 * @author kal bugrara
 */
public class SeatAssignment {
   float grade; //(Numeric grade for calculations)
    String letterGrade; // Letter grade: A, A-, B+, B, B-, C+, C, C-, F  ← ADD THIS
    Seat seat;
    boolean like;
    CourseLoad courseload;
    
    public SeatAssignment(CourseLoad cl, Seat s){
        seat = s;
        courseload = cl;
    }
    
    public boolean getLike(){
        return like;
    }
    
    public void assignSeatToStudent(CourseLoad cl){
        courseload = cl;
    }
    
    public int getCreditHours(){
        return seat.getCourseCredits();
    }
    
    // ========== ADD THESE NEW METHODS ==========
    
    /**
     * Get letter grade (A, B+, etc.)
     * @return Letter grade as String
     */
    public String getGrade() {
        return letterGrade;
    }
    
    /**
     * Set letter grade (A, A-, B+, B, B-, C+, C, C-, F)
     * Also updates numeric grade based on letter grade
     * @param letterGrade Letter grade to assign
     */
    public void setGrade(String letterGrade) {
        this.letterGrade = letterGrade;
        
        // Also update numeric grade for backward compatibility
        this.grade = convertLetterGradeToNumeric(letterGrade);
    }
    
    /**
     * Convert letter grade to numeric value
     * @param letterGrade Letter grade
     * @return Numeric grade value
     */
    private float convertLetterGradeToNumeric(String letterGrade) {
        if (letterGrade == null) return 0.0f;
        
        switch (letterGrade.toUpperCase()) {
            case "A": return 4.0f;
            case "A-": return 3.7f;
            case "B+": return 3.3f;
            case "B": return 3.0f;
            case "B-": return 2.7f;
            case "C+": return 2.3f;
            case "C": return 2.0f;
            case "C-": return 1.7f;
            case "F": return 0.0f;
            default: return 0.0f;
        }
    }
    
    /**
     * Get course credits - Alias for getCreditHours()
     * Used by Transcript for GPA calculations
     * @return Course credit hours
     */
    public int getCourseCredits() {
        return getCreditHours();
    }
    
    // ========== END NEW METHODS ==========
    
    public Seat getSeat(){
        return seat;
    }
    
    public CourseOffer getCourseOffer(){
        return seat.getCourseOffer();
    }
    
    public Course getAssociatedCourse(){
        return getCourseOffer().getSubjectCourse();
    }
    
    public float GetCourseStudentScore(){
        return getCreditHours() * grade;
    }
    
    /**
 * Get the course load (student's semester enrollment)
 * @return CourseLoad that this seat assignment belongs to
 */
public CourseLoad getCourseload() {
    return courseload;
}
    
}
