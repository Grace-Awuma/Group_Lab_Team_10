/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

import Business.Profiles.StudentProfile;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yaksha
 */
public class CourseEnrollment {
    String enrollmentId;
    StudentProfile student;
    CourseOffer courseOffer;
    Date enrollmentDate;
    String grade;
    boolean tuitionPaid;
    double tuitionAmount;
    ArrayList<Assignment> assignments;

    public CourseEnrollment(String enrollmentId, StudentProfile student, CourseOffer courseOffer) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.courseOffer = courseOffer;
        this.enrollmentDate = new Date();
        this.tuitionPaid = false;
        this.tuitionAmount = courseOffer.getCourse().getCreditHours() * 1500.0;
        this.assignments = new ArrayList<>();
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public StudentProfile getStudent() {
        return student;
    }

    public CourseOffer getCourseOffer() {
        return courseOffer;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isTuitionPaid() {
        return tuitionPaid;
    }

    public void setTuitionPaid(boolean tuitionPaid) {
        this.tuitionPaid = tuitionPaid;
    }

    public double getTuitionAmount() {
        return tuitionAmount;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public double calculateTotalPercentage() {
        if (assignments.isEmpty()) return 0.0;
        
        double totalWeightedScore = 0.0;
        double totalWeight = 0.0;
        
        for (Assignment a : assignments) {
            if (a.getScore() != null) {
                totalWeightedScore += a.getScore() * a.getWeight();
                totalWeight += a.getWeight();
            }
        }
        
        return totalWeight > 0 ? totalWeightedScore / totalWeight : 0.0;
    }

    public String calculateLetterGrade() {
        double percentage = calculateTotalPercentage();
        if (percentage >= 93) return "A";
        if (percentage >= 90) return "A-";
        if (percentage >= 87) return "B+";
        if (percentage >= 83) return "B";
        if (percentage >= 80) return "B-";
        if (percentage >= 77) return "C+";
        if (percentage >= 73) return "C";
        if (percentage >= 70) return "C-";
        return "F";
    }

    public void setTuitionAmount(double tuition) {
    this.tuitionAmount = tuition; 
    }
}
