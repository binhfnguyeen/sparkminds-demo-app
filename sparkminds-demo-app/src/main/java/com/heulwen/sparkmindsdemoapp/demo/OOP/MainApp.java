package com.heulwen.sparkmindsdemoapp.demo.OOP;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("--- 1. ĐỐI TƯỢNG (OBJECT) VÀ ĐÓNG GÓI ---");
        // Khởi tạo Object
        VNPayService payment1 = new VNPayService("TXN_001", 500000, "TOKEN_ABC123");
        // payment1.transactionId = "TOKEN_CDF123"; // LỖI COMPILE! Vì transactionId là private.
        System.out.println("ID Giao dịch: " + payment1.getTransactionId()); // Chạy OK nhờ Getter

        System.out.println("\n--- 2. OVERLOADING (Static Binding) ---");
        // Gọi hàm giảm giá bằng %, Compiler map ngay vào hàm applyDiscount(int)
        payment1.applyDiscount(10);
        // Gọi hàm giảm trừ thẳng tiền, Compiler map ngay vào hàm applyDiscount(double)
        payment1.applyDiscount(25000.0);

        System.out.println("\n--- 3. OVERRIDING & RUNTIME POLYMORPHISM ---");
        // Kiểu tham chiếu là lớp Cha (BasePayment), nhưng Object thực tế là Con (VNPayService)
        // Upcasting: từ cụ thể lên tổng quát
        BasePayment polymorphicPayment = new VNPayService("TXN_002", 1000000, "TOKEN_XYZ999");

        // JVM nhìn xuống Heap lúc chạy, thấy object là VNPayService nên gọi hàm executeTransaction() của lớp Con.
        polymorphicPayment.executeTransaction();

        // polymorphicPayment.applyDiscount(10); // LỖI COMPILE!
        // Vì lớp Cha (BasePayment) không hề định nghĩa hàm applyDiscount. Compiler chỉ nhìn vào kiểu tham chiếu.

        System.out.println("\n--- 4. INTERFACE ---");
        // Có thể ép kiểu object hiện tại về dạng Interface nếu nó có implements
        // Downcasting - Từ kiểu tổng quát đến kiểu cụ thể hơn
        if (polymorphicPayment instanceof Refundable) {
            Refundable refundAction = (Refundable) polymorphicPayment;
            System.out.println(refundAction.hello("Nguyen"));
            refundAction.refund(200000);
        }
    }
}
