/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;

import java.util.Date;

/**
 *
 * @author grace
 */
class PaymentTransaction {
    private String transactionId;
    private double amount;
    private Date date;
    private double balanceAfter;
    private String description;
    
    public PaymentTransaction(String id, double amount, Date date, 
                             double balanceAfter, String description) {
        this.transactionId = id;
        this.amount = amount;
        this.date = date;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }
    
    public String getTransactionId() { 
        return transactionId; 
    }
    
    public double getAmount() { 
        return amount; 
    }
    
    public Date getDate() { 
        return date; 
    }
    
    public double getBalanceAfter() { 
        return balanceAfter; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public String getFormattedAmount() {
        return String.format("$%.2f", amount);
    }
    
    public String getFormattedBalance() {
        return String.format("$%.2f", balanceAfter);
    }
}
