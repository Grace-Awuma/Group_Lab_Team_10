/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Course;

import Business.Profiles.StudentProfile;
import java.util.Date;

/**
 *
 * @author Yaksha
 */
public class TuitionPayment {
    String paymentId;
    StudentProfile student;
    double amount;
    Date paymentDate;
    String semester;
    String paymentMethod;

    public TuitionPayment(String paymentId, StudentProfile student, double amount, String semester) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.semester = semester;
        this.paymentDate = new Date();
        this.paymentMethod = "Online";
    }

    public String getPaymentId() {
        return paymentId;
    }

    public StudentProfile getStudent() {
        return student;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getSemester() {
        return semester;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
