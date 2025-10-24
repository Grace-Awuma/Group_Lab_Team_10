/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Degree;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class Degree {
    // CLASS-LEVEL FIELDS (lines 19-22)
    String title;
    ArrayList<Course> corelist;
    ArrayList<Course> electives;
    int requiredTotalCredits = 32;  
    
    // CONSTRUCTOR (lines 24+)
    public Degree(String name) {
        title = name;              
        corelist = new ArrayList(); 
        electives = new ArrayList(); 
    }    
    
    public void addCoreCourse(Course c) {
        corelist.add(c);
    }
    
    public void addElectiveCourse(Course c) {
        electives.add(c);
    }
    
    /**
     * Check if student is ready to graduate
     * Requirements:
     * 1. All core courses completed with passing grades
     * 2. At least 32 total credits
     */
    public boolean isStudentReadyToGraduate(StudentProfile sp) {
        ArrayList<SeatAssignment> sas = sp.getCourseList();
        
        // Check core courses
        if (!validateCoreClasses(sas)) {
            return false;
        }
        
        // Check total credit hours (at least 32)
        if (!hasRequiredCredits(sas)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Validate that all core courses are completed with passing grades
     */
    public boolean validateCoreClasses(ArrayList<SeatAssignment> sas) {
        for (Course c : corelist) {
            if (!isCoreSatisfied(sas, c)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check if a specific core course is completed with passing grade
     */
    public boolean isCoreSatisfied(ArrayList<SeatAssignment> sas, Course c) {
        for (SeatAssignment sa : sas) {
            if (sa.getAssociatedCourse().equals(c)) {
                // Check if has passing grade (not F, not null)
                String grade = sa.getGrade();
                if (grade != null && !grade.isEmpty() && !grade.equalsIgnoreCase("F")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Check if student has completed required total credits (32 for MSIS)
     */
    public boolean hasRequiredCredits(ArrayList<SeatAssignment> sas) {
        int totalCredits = 0;
        
        for (SeatAssignment sa : sas) {
            String grade = sa.getGrade();
            // Only count courses with passing grades
            if (grade != null && !grade.isEmpty() && !grade.equalsIgnoreCase("F")) {
                totalCredits += sa.getCourseCredits();
            }
        }
        
        return totalCredits >= requiredTotalCredits;
    }
    
    /**
     * Get total credits completed with passing grades
     */
    public int getTotalCreditsCompleted(ArrayList<SeatAssignment> sas) {
        int totalCredits = 0;
        
        for (SeatAssignment sa : sas) {
            String grade = sa.getGrade();
            if (grade != null && !grade.isEmpty() && !grade.equalsIgnoreCase("F")) {
                totalCredits += sa.getCourseCredits();
            }
        }
        
        return totalCredits;
    }
    
    /**
     * Get credits remaining to graduate
     */
    public int getCreditsRemaining(ArrayList<SeatAssignment> sas) {
        int completed = getTotalCreditsCompleted(sas);
        int remaining = requiredTotalCredits - completed;
        return remaining > 0 ? remaining : 0;
    }
    
    /**
     * Get total elective courses taken
     */
    public int getTotalElectiveCoursesTaken(ArrayList<SeatAssignment> sas) {
        int electivecount = 0;
        for (SeatAssignment sa : sas) {
            if (isElectiveSatisfied(sa)) {
                electivecount = electivecount + 1;
            }
        }
        return electivecount;
    }
    
    /**
     * Check if a seat assignment is for an elective course
     */
    public boolean isElectiveSatisfied(SeatAssignment sa) {
        for (Course c : electives) {
            if (sa.getAssociatedCourse().equals(c)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get graduation status report
     */
    public String getGraduationReport(StudentProfile sp) {
        ArrayList<SeatAssignment> sas = sp.getCourseList();
        StringBuilder report = new StringBuilder();
        
        report.append("========== GRADUATION AUDIT REPORT ==========\n");
        report.append("Degree: ").append(title).append("\n");
        report.append("Student ID: ").append(sp.getStudentId()).append("\n\n");
        
        // Credits
        int totalCredits = getTotalCreditsCompleted(sas);
        report.append("CREDIT REQUIREMENTS:\n");
        report.append(String.format("  Credits Earned: %d\n", totalCredits));
        report.append(String.format("  Credits Required: %d\n", requiredTotalCredits));
        report.append(String.format("  Status: %s\n", 
            totalCredits >= requiredTotalCredits ? "✓ Complete" : "✗ Need " + getCreditsRemaining(sas) + " more"));
        report.append("\n");
        
        // Core courses
        boolean coreComplete = validateCoreClasses(sas);
        report.append("CORE COURSE REQUIREMENTS:\n");
        report.append(String.format("  Status: %s\n", 
            coreComplete ? "✓ Complete" : "✗ Incomplete"));
        for (Course c : corelist) {
            boolean satisfied = isCoreSatisfied(sas, c);
            report.append(String.format("  - %s: %s\n", 
                c.getCOurseNumber(), satisfied ? "✓" : "✗"));
        }
        report.append("\n");
        
        // Final determination
        boolean eligible = isStudentReadyToGraduate(sp);
        report.append("GRADUATION ELIGIBILITY:\n");
        if (eligible) {
            report.append("  ✓✓✓ READY TO GRADUATE ✓✓✓\n");
        } else {
            report.append("  NOT READY TO GRADUATE\n");
        }
        
        report.append("=============================================\n");
        return report.toString();
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getRequiredTotalCredits() {
        return requiredTotalCredits;
    }
}
