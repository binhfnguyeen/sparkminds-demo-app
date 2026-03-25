package com.heulwen.sparkmindsdemoapp.demo.OOP;

// Inheritance (extends) - Interface (implements)
public class VNPayService extends BasePayment implements Refundable{
    private String vnpayToken;

    public VNPayService(String transactionId, double amount, String vnpayToken){
        super(transactionId, amount);
        this.vnpayToken = vnpayToken;
    }

    // Overriding - Runtime Polymorphism
    @Override
    public void executeTransaction() {
        // Truy cập biến 'amount' nhờ modifier 'protected' của lớp cha
        System.out.println("Đang kết nối cổng VNPay với Token: " + this.vnpayToken);
        System.out.println("Thanh toán thành công số tiền: " + this.amount + " VND");
    }

    @Override
    public boolean refund(double refundAmount) {
        if (refundAmount <= this.amount) {
            System.out.println("Hoàn tiền VNPay thành công: " + refundAmount + " VND");
            return true;
        }
        return false;
    }

    // Overloading - Compile-time Polymorphism
    // Hàm áp dụng mã giảm giá (Giảm theo % số nguyên)
    public void applyDiscount(int percent) {
        this.amount = this.amount - (this.amount * percent / 100);
        System.out.println("Đã giảm " + percent + "%. Số tiền còn: " + this.amount);
    }

    // Hàm áp dụng mã giảm giá (Giảm trừ thẳng số tiền)
    // Cùng tên hàm, nhưng khác tham số đầu vào -> Trình biên dịch tự biết gọi hàm nào.
    public void applyDiscount(double directDiscountAmount) {
        this.amount = this.amount - directDiscountAmount;
        System.out.println("Đã trừ " + directDiscountAmount + " VND. Số tiền còn: " + this.amount);
    }
}
