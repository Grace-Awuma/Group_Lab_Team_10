/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.SeatAssignment;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */
public class Transcript {

    StudentProfile student;
    HashMap<String, CourseLoad> courseloadlist;
    CourseLoad currentcourseload;
    
    // Grade point mapping for GPA calculation
    private static final HashMap<String, Double> GRADE_POINTS = new HashMap<>();
    
    static {
        // Initialize grade point mapping per assignment requirements
        GRADE_POINTS.put("A", 4.0);
        GRADE_POINTS.put("A-", 3.7);
        GRADE_POINTS.put("B+", 3.3);
        GRADE_POINTS.put("B", 3.0);
        GRADE_POINTS.put("B-", 2.7);
        GRADE_POINTS.put("C+", 2.3);
        GRADE_POINTS.put("C", 2.0);
        GRADE_POINTS.put("C-", 1.7);
        GRADE_POINTS.put("F", 0.0);
    }
    
    public Transcript(StudentProfile sp) {
        student = sp;
        courseloadlist = new HashMap();
    }
    
    // ========== EXISTING METHODS (Keep these) ==========
    
    public int getStudentSatisfactionIndex() {
        return 0;
    }
    
    public CourseLoad newCourseLoad(String sem) {
        currentcourseload = new CourseLoad(sem);
        courseloadlist.put(sem, currentcourseload);
        return currentcourseload;
    }
    
    public CourseLoad getCurrentCourseLoad() {
        return currentcourseload;
    }
    
    public CourseLoad getCourseLoadBySemester(String semester) {
        return courseloadlist.get(semester);
    }
    
    public float getStudentTotalScore() {
        float sum = 0;
        for (CourseLoad cl : courseloadlist.values()) {
            sum = sum + cl.getSemesterScore();
        }
        return sum;
    }
    
    public int getStudentSatifactionIndex() {
        ArrayList<SeatAssignment> courseregistrations = getCourseList();
        int sum = 0;
        for (SeatAssignment sa : courseregistrations) {
            if (sa.getLike()) {
                sum = sum + 1;
            }
        }
        return sum;
    }
    
    public ArrayList<SeatAssignment> getCourseList() {
        ArrayList temp2;
        temp2 = new ArrayList();
        for (CourseLoad cl : courseloadlist.values()) {
            temp2.addAll(cl.getSeatAssignments());
        }
        return temp2;
    }
    
    // ========== NEW GPA CALCULATION METHODS ==========
    
    /**
     * Convert letter grade to grade points
     * @param grade Letter grade (A, A-, B+, etc.)
     * @return Grade points (4.0, 3.7, etc.) or 0.0 if invalid
     */
    public static double getGradePoints(String grade) {
        if (grade == null || grade.isEmpty()) {
            return 0.0;
        }
        return GRADE_POINTS.getOrDefault(grade.toUpperCase(), 0.0);
    }
    
    /**
     * Calculate Term GPA for a specific semester
     * Formula: Sum(grade points × credit hours) / Sum(credit hours)
     * 
     * @param semester Semester identifier (e.g., "Fall2025")
     * @return Term GPA for that semester, or 0.0 if no courses
     */
    public double calculateTermGPA(String semester) {
        CourseLoad courseLoad = courseloadlist.get(semester);
        
        if (courseLoad == null) {
            return 0.0;
        }
        
        ArrayList<SeatAssignment> assignments = courseLoad.getSeatAssignments();
        
        if (assignments == null || assignments.isEmpty()) {
            return 0.0;
        }
        
        double totalQualityPoints = 0.0;
        int totalCredits = 0;
        
        for (SeatAssignment sa : assignments) {
            String grade = sa.getGrade();
            
            // Only count courses with grades (skip if no grade assigned yet)
            if (grade != null && !grade.isEmpty()) {
                int credits = sa.getCourseCredits();
                double gradePoints = getGradePoints(grade);
                
                totalQualityPoints += (gradePoints * credits);
                totalCredits += credits;
            }
        }
        
        if (totalCredits == 0) {
            return 0.0;
        }
        
        return totalQualityPoints / totalCredits;
    }
    
    /**
     * Calculate Overall GPA (cumulative across all semesters)
     * Formula: Sum(all grade points × credit hours) / Sum(all credit hours)
     * 
     * @return Overall cumulative GPA, or 0.0 if no courses
     */
    public double calculateOverallGPA() {
        double totalQualityPoints = 0.0;
        int totalCredits = 0;
        
        // Iterate through all semesters
        for (CourseLoad courseLoad : courseloadlist.values()) {
            ArrayList<SeatAssignment> assignments = courseLoad.getSeatAssignments();
            
            if (assignments != null) {
                for (SeatAssignment sa : assignments) {
                    String grade = sa.getGrade();
                    
                    // Only count courses with grades
                    if (grade != null && !grade.isEmpty()) {
                        int credits = sa.getCourseCredits();
                        double gradePoints = getGradePoints(grade);
                        
                        totalQualityPoints += (gradePoints * credits);
                        totalCredits += credits;
                    }
                }
            }
        }
        
        if (totalCredits == 0) {
            return 0.0;
        }
        
        return totalQualityPoints / totalCredits;
    }
    
    /**
     * Determine academic standing for a specific semester
     * 
     * Rules per assignment:
     * - Good Standing: Term GPA ≥ 3.0 AND Overall GPA ≥ 3.0
     * - Academic Warning: Term GPA < 3.0 (even if overall GPA ≥ 3.0)
     * - Academic Probation: Overall GPA < 3.0 (regardless of term GPA)
     * 
     * @param semester Semester to check
     * @return "Good Standing", "Academic Warning", or "Academic Probation"
     */
    public String determineAcademicStanding(String semester) {
        double termGPA = calculateTermGPA(semester);
        double overallGPA = calculateOverallGPA();
        
        // Probation takes priority - overall GPA < 3.0
        if (overallGPA < 3.0) {
            return "Academic Probation";
        }
        
        // Warning - term GPA < 3.0 but overall is OK
        if (termGPA < 3.0) {
            return "Academic Warning";
        }
        
        // Both are >= 3.0
        return "Good Standing";
    }
    
    /**
     * Get current academic standing (based on most recent semester)
     * @return Current academic standing
     */
    public String getCurrentAcademicStanding() {
        if (currentcourseload == null) {
            return "No courses enrolled";
        }
        
        // Find the semester key for current course load
        String currentSemester = null;
        for (String semester : courseloadlist.keySet()) {
            if (courseloadlist.get(semester) == currentcourseload) {
                currentSemester = semester;
                break;
            }
        }
        
        if (currentSemester != null) {
            return determineAcademicStanding(currentSemester);
        }
        
        return "Unable to determine";
    }
    
    // ========== CREDIT TRACKING METHODS ==========
    
    /**
     * Get total credits completed across all semesters (with grades)
     * Used for graduation audit
     * 
     * @return Total credits with grades assigned
     */
    public int getTotalCreditsCompleted() {
        int totalCredits = 0;
        
        for (CourseLoad courseLoad : courseloadlist.values()) {
            ArrayList<SeatAssignment> assignments = courseLoad.getSeatAssignments();
            
            if (assignments != null) {
                for (SeatAssignment sa : assignments) {
                    String grade = sa.getGrade();
                    
                    // Only count if grade is assigned and not F
                    if (grade != null && !grade.isEmpty() && !grade.equalsIgnoreCase("F")) {
                        totalCredits += sa.getCourseCredits();
                    }
                }
            }
        }
        
        return totalCredits;
    }
    
    /**
     * Get total credits for a specific semester
     * @param semester Semester identifier
     * @return Total credits in that semester
     */
    public int getTotalCreditsBySemester(String semester) {
        CourseLoad courseLoad = courseloadlist.get(semester);
        
        if (courseLoad == null) {
            return 0;
        }
        
        ArrayList<SeatAssignment> assignments = courseLoad.getSeatAssignments();
        int totalCredits = 0;
        
        if (assignments != null) {
            for (SeatAssignment sa : assignments) {
                totalCredits += sa.getCourseCredits();
            }
        }
        
        return totalCredits;
    }
    
    /**
     * Get total credits currently enrolled (in current semester)
     * Used to enforce 8 credit limit per semester
     * 
     * @return Total credits in current semester
     */
    public int getCurrentSemesterCredits() {
        if (currentcourseload == null) {
            return 0;
        }
        
        ArrayList<SeatAssignment> assignments = currentcourseload.getSeatAssignments();
        int totalCredits = 0;
        
        if (assignments != null) {
            for (SeatAssignment sa : assignments) {
                totalCredits += sa.getCourseCredits();
            }
        }
        
        return totalCredits;
    }
    
    // ========== REPORTING METHODS ==========
    
    /**
     * Get formatted transcript summary for display
     * @return Formatted string with GPA information
     */
    public String getTranscriptSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Transcript Summary ===\n");
        summary.append(String.format("Overall GPA: %.2f\n", calculateOverallGPA()));
        summary.append(String.format("Total Credits Completed: %d\n", getTotalCreditsCompleted()));
        summary.append(String.format("Current Standing: %s\n", getCurrentAcademicStanding()));
        summary.append("\nSemester Breakdown:\n");
        
        for (String semester : courseloadlist.keySet()) {
            double termGPA = calculateTermGPA(semester);
            int credits = getTotalCreditsBySemester(semester);
            summary.append(String.format("  %s: GPA %.2f, %d credits\n", 
                semester, termGPA, credits));
        }
        
        return summary.toString();
    }
    
    @Override
    public String toString() {
        return "Transcript for Student: " + student.getStudentId() + 
               " | Overall GPA: " + String.format("%.2f", calculateOverallGPA());
    }
}
