package com.heulwen.sparkmindsdemoapp.demo.OOP;

// Class, Abstract Class, Package
public abstract class BasePayment {
    // Encapsulation & Modifiers (private, protected)
    private String transactionId;
    protected double amount;

    public BasePayment(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    // Abstraction
    // Giấu đi cách thực thi, bắt buộc lớp con phải tự định nghĩa
    public abstract void executeTransaction();

    // Getter cho biến private
    public String getTransactionId() {
        return transactionId;
    }
}
