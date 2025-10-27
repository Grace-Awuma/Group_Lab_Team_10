/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import Business.Person.Person;
import Business.Course.CourseEnrollment;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yaksha
 */
public class StudentProfile extends Profile {

    String department;
    String program;
    String academicStatus;
    double accountBalance;
    ArrayList<CourseEnrollment> enrollments;
    private ArrayList<PaymentTransaction> paymentHistory = new ArrayList<>();  // ← ADD THIS LINE


    public StudentProfile(Person p) {
        super(p);
        this.department = "CSIS";
        this.program = "MSIS";
        this.academicStatus = "Good Standing";
        this.accountBalance = 0.0;
        this.enrollments = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
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

    // Calculate total credits earned
    public int getTotalCreditsEarned() {
        int total = 0;
        for (CourseEnrollment e : enrollments) {
            if (e.getGrade() != null && !e.getGrade().equals("F")) {
                total += e.getCourseOffer().getCourse().getCreditHours();
            }
        }
        return total;
    }

    // Calculate overall GPA
    public double getOverallGPA() {
        double totalQualityPoints = 0.0;
        int totalCredits = 0;
        
        for (CourseEnrollment e : enrollments) {
            if (e.getGrade() != null) {
                double gradePoints = getGradePoints(e.getGrade());
                int credits = e.getCourseOffer().getCourse().getCreditHours();
                totalQualityPoints += gradePoints * credits;
                totalCredits += credits;
            }
        }
        
        return totalCredits > 0 ? totalQualityPoints / totalCredits : 0.0;
    }

    // Calculate term GPA
    public double getTermGPA(String semester) {
        double totalQualityPoints = 0.0;
        int totalCredits = 0;
        
        for (CourseEnrollment e : enrollments) {
            if (e.getCourseOffer().getSemester().equals(semester) && e.getGrade() != null) {
                double gradePoints = getGradePoints(e.getGrade());
                int credits = e.getCourseOffer().getCourse().getCreditHours();
                totalQualityPoints += gradePoints * credits;
                totalCredits += credits;
            }
        }
        
        return totalCredits > 0 ? totalQualityPoints / totalCredits : 0.0;
    }

    // Get current semester credit load
    public int getCurrentSemesterCredits(String semester) {
        int total = 0;
        for (CourseEnrollment e : enrollments) {
            if (e.getCourseOffer().getSemester().equals(semester)) {
                total += e.getCourseOffer().getCourse().getCreditHours();
            }
        }
        return total;
    }

    // Check if ready to graduate
    public boolean isReadyToGraduate() {
        int totalCredits = getTotalCreditsEarned();
        boolean hasCore = false;
        
        for (CourseEnrollment e : enrollments) {
            if (e.getCourseOffer().getCourse().getCourseId().equals("INFO 5100") 
                && e.getGrade() != null && !e.getGrade().equals("F")) {
                hasCore = true;
                break;
            }
        }
        
        return totalCredits >= 32 && hasCore;
    }

    // Update academic status based on GPA
    public void updateAcademicStatus() {
        double overallGPA = getOverallGPA();
        String currentSemester = getCurrentSemester();
        double termGPA = currentSemester != null ? getTermGPA(currentSemester) : overallGPA;
        
        if (overallGPA < 3.0) {
            this.academicStatus = "Academic Probation";
        } else if (termGPA < 3.0) {
            this.academicStatus = "Academic Warning";
        } else {
            this.academicStatus = "Good Standing";
        }
    }

    private String getCurrentSemester() {
        if (enrollments.isEmpty()) return null;
        return enrollments.get(enrollments.size() - 1).getCourseOffer().getSemester();
    }

    private double getGradePoints(String grade) {
        switch (grade) {
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "F": return 0.0;
            default: return 0.0;
        }
    }
    
    // Add these methods for payment functionality

/**
 * Make a tuition payment
 */
public boolean makePayment(double amount) {
    if (amount <= 0) {
        return false;
    }
    
    // Check if there's balance to pay
    if (accountBalance >= 0) {
        return false; // No balance to pay
    }
    
    // Apply payment
    accountBalance += amount;  // Increase toward 0
    
    // Mark courses as paid if balance is now 0 or positive
    if (accountBalance >= 0) {
        for (CourseEnrollment e : enrollments) {
            if (!e.isTuitionPaid()) {
                e.setTuitionPaid(true);
            }
        }
    }
    
    return true;
}
public ArrayList<PaymentTransaction> getPaymentHistory() {
    return paymentHistory;
}

public void recordRefund(double amount, String courseId) {
    accountBalance -= amount; // Reduce what student owes
    
    // Create proper transaction with all required parameters
    String transactionId = "REF-" + System.currentTimeMillis();
    Date now = new Date();
    double newBalance = accountBalance;
    String description = "Refund for " + courseId;
    
    PaymentTransaction refund = new PaymentTransaction(
        transactionId, 
        -amount,  // Negative because it's a refund
        now, 
        newBalance, 
        description
    );
    
    paymentHistory.add(refund);
}
    
/**
 * Check if can access transcript (tuition paid)
 */
public boolean canAccessTranscript() {
    return accountBalance >= 0;
}

/**
 * Get amount owed
 */
public double getAmountOwed() {
    return accountBalance < 0 ? Math.abs(accountBalance) : 0.0;
}

/**
 * Get balance status message
 */
public String getBalanceStatus() {
    if (accountBalance < 0) {
        return String.format("Amount Due: $%.2f", Math.abs(accountBalance));
    } else if (accountBalance > 0) {
        return String.format("Credit: $%.2f", accountBalance);
    } else {
        return "Balance: $0.00 (Paid in Full)";
    }
}
}

