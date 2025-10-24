/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kal bugrara
 */
public class StudentAccount {
    private StudentProfile studentProfile;
    private double balance; // Default: 0.0
    private ArrayList<Transaction> transactionHistory;
    
    /**
     * Constructor - Creates account with $0 balance
     */
    public StudentAccount(StudentProfile student) {
        this.studentProfile = student;
        this.balance = 0.0; // Default balance = 0
        this.transactionHistory = new ArrayList<>();
    }
    
    // ========== BALANCE MANAGEMENT ==========
    
    /**
     * Get current account balance
     * @return balance (negative = owes money, positive = credit, 0 = paid)
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Check if student has outstanding balance (owes money)
     * @return true if student owes money
     */
    public boolean hasOutstandingBalance() {
        return balance < 0;
    }
    
    /**
     * Get formatted balance status string
     * @return formatted balance message
     */
    public String getBalanceStatus() {
        if (balance < 0) {
            return String.format("Amount Due: $%.2f", Math.abs(balance));
        } else if (balance > 0) {
            return String.format("Credit: $%.2f", balance);
        } else {
            return "Balance: $0.00 (Paid in Full)";
        }
    }
    
    /**
     * Get amount owed (always positive or zero)
     * @return amount student owes
     */
    public double getAmountOwed() {
        return balance < 0 ? Math.abs(balance) : 0.0;
    }
    
    // ========== TUITION CHARGING ==========
    
    /**
     * Charge tuition for a course enrollment
     * @param amount tuition amount to charge
     * @param courseId course identifier
     * @param courseName course name for description
     */
    public void chargeTuition(double amount, String courseId, String courseName) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Tuition amount must be positive");
        }
        
        balance -= amount; // Subtract = student now owes more
        
        String description = "Tuition charge for " + courseName + " (" + courseId + ")";
        Transaction transaction = new Transaction(
            "CHARGE",
            amount,
            courseId,
            description,
            balance
        );
        transactionHistory.add(transaction);
    }
    
    // ========== PAYMENT PROCESSING ==========
    
    /**
     * Process a tuition payment
     * @param amount payment amount
     * @return true if payment processed, false if no balance to pay or invalid amount
     */
    public boolean makePayment(double amount) {
        // Validate payment amount
        if (amount <= 0) {
            return false; // Invalid payment amount
        }
        
        // Check if there's a balance to pay
        if (balance >= 0) {
            return false; // No balance to pay (balance is 0 or positive credit)
        }
        
        // Process payment
        balance += amount; // Add payment (moves toward 0)
        
        Transaction transaction = new Transaction(
            "PAYMENT",
            amount,
            null,
            "Tuition Payment",
            balance
        );
        transactionHistory.add(transaction);
        
        return true; // Payment successful
    }
    
    /**
     * Make payment and return new balance
     * @param amount payment amount
     * @return new balance after payment, or original balance if payment failed
     */
    public double processPayment(double amount) {
        if (makePayment(amount)) {
            return balance;
        }
        return balance; // Payment failed, return original balance
    }
    
    // ========== REFUND PROCESSING ==========
    
    /**
     * Process refund when student drops a course
     * @param amount refund amount
     * @param courseId course identifier
     * @param courseName course name
     */
    public void processRefund(double amount, String courseId, String courseName) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Refund amount must be positive");
        }
        
        balance += amount; // Add refund = reduces amount owed or creates credit
        
        String description = "Refund for dropped course: " + courseName + " (" + courseId + ")";
        Transaction transaction = new Transaction(
            "REFUND",
            amount,
            courseId,
            description,
            balance
        );
        transactionHistory.add(transaction);
    }
    
    // ========== TRANSCRIPT ACCESS CONTROL ==========
    
    /**
     * Check if student can access transcript
     * Per assignment: Student cannot see transcript until tuition is paid off
     * @return true if balance is paid (>= 0), false if student owes money
     */
    public boolean canAccessTranscript() {
        return balance >= 0; // Can only access if no debt
    }
    
    // ========== TRANSACTION HISTORY ==========
    
    /**
     * Get complete transaction history
     * @return list of all transactions
     */
    public ArrayList<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory); // Return copy for safety
    }
    
    /**
     * Get number of transactions
     * @return transaction count
     */
    public int getTransactionCount() {
        return transactionHistory.size();
    }
    
    /**
     * Check if account has any transactions
     * @return true if there are transactions
     */
    public boolean hasTransactions() {
        return !transactionHistory.isEmpty();
    }
    
    // ========== REPORTING ==========
    
    /**
     * Get total amount charged (all tuition charges)
     * @return total charged
     */
    public double getTotalCharged() {
        double total = 0.0;
        for (Transaction t : transactionHistory) {
            if (t.getType().equals("CHARGE")) {
                total += t.getAmount();
            }
        }
        return total;
    }
    
    /**
     * Get total amount paid (all payments)
     * @return total paid
     */
    public double getTotalPaid() {
        double total = 0.0;
        for (Transaction t : transactionHistory) {
            if (t.getType().equals("PAYMENT")) {
                total += t.getAmount();
            }
        }
        return total;
    }
    
    /**
     * Get total refunds received
     * @return total refunds
     */
    public double getTotalRefunds() {
        double total = 0.0;
        for (Transaction t : transactionHistory) {
            if (t.getType().equals("REFUND")) {
                total += t.getAmount();
            }
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "StudentAccount for " + studentProfile.getStudentId() + 
               " - " + getBalanceStatus();
    }
    
    // ========== INNER CLASS: TRANSACTION ==========
    
    /**
     * Transaction record - Represents a single financial transaction
     */
    public static class Transaction {
        private String type; // CHARGE, PAYMENT, REFUND
        private double amount;
        private String courseId; // null for general payments
        private String description;
        private Date date;
        private double balanceAfter; // Balance after this transaction
        
        public Transaction(String type, double amount, String courseId, 
                          String description, double balanceAfter) {
            this.type = type;
            this.amount = amount;
            this.courseId = courseId;
            this.description = description;
            this.date = new Date();
            this.balanceAfter = balanceAfter;
        }
        
        // Getters
        public String getType() {
            return type;
        }
        
        public double getAmount() {
            return amount;
        }
        
        public String getCourseId() {
            return courseId != null ? courseId : "N/A";
        }
        
        public String getDescription() {
            return description;
        }
        
        public Date getDate() {
            return date;
        }
        
        public double getBalanceAfter() {
            return balanceAfter;
        }
        
        public String getFormattedAmount() {
            return String.format("$%.2f", amount);
        }
        
        public String getFormattedDate() {
            return date.toString();
        }
        
        @Override
        public String toString() {
            return String.format("%s | %s: $%.2f | %s | Balance After: $%.2f",
                date.toString(), type, amount, description, balanceAfter);
        }
    }
}
