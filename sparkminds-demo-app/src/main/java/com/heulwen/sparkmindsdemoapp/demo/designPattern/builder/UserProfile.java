package com.heulwen.sparkmindsdemoapp.demo.designPattern.builder;

// Main Class - không có setter để đảm bảo immutable
public class UserProfile {
    // Thuộc tính bắt buộc
    private final String userId;
    private final String firstName;
    private final String lastName;

    // Thuộc tính tùy chọn
    private final String email;
    private final String phone;
    private final int age;

    // Constructor của class chính phải là private.
    // Chỉ cho phép đối tượng Builder gọi vào đây.
    private UserProfile(UserProfileBuilder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.age = builder.age;
    }

    // Các hàm Getter... (bỏ qua để code gọn hơn)
    public void printProfile() {
        System.out.println("User: " + firstName + " " + lastName + " | Email: " + email + " | Age: " + age);
    }

    public static class UserProfileBuilder {
        // Copy toàn bộ thuộc tính của class chính xuống đây
        private final String userId;     // Bắt buộc
        private final String firstName;  // Bắt buộc
        private final String lastName;   // Bắt buộc

        private String email = "Chưa cập nhật"; // Tùy chọn (có giá trị mặc định)
        private String phone = "Chưa cập nhật"; // Tùy chọn
        private int age = 0;                    // Tùy chọn

        // Constructor của Builder yêu cầu truyền vào các thuộc tính BẮT BUỘC
        public UserProfileBuilder(String userId, String firstName, String lastName) {
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // Các hàm cấu hình cho các thuộc tính TÙY CHỌN (Method Chaining)
        // Luôn return về 'this' (chính đối tượng Builder này)
        public UserProfileBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserProfileBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserProfileBuilder age(int age) {
            this.age = age;
            return this;
        }

        // Trả về đối tượng class chính đã được lắp ráp hoàn chỉnh
        public UserProfile build() {
            // validate
            if (this.age < 0) {
                throw new IllegalArgumentException("Tuổi không được âm!");
            }
            return new UserProfile(this);
        }
    }
}
