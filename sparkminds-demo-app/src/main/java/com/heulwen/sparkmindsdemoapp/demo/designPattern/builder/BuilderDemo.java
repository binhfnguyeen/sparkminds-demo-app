package com.heulwen.sparkmindsdemoapp.demo.designPattern.builder;

public class BuilderDemo {
    public static void main(String[] args) {
        // Tạo User chỉ với các thông tin bắt buộc
        UserProfile basicUser = new UserProfile.UserProfileBuilder("U01", "Nguyen", "An").build();

        // Tạo User phức tạp với Method Chaining
        UserProfile premiumUser = new UserProfile.UserProfileBuilder("U02", "Tran", "Binh")
                .email("binh.tran@email.com")
                .age(25)
                .phone("0123456789")
                .build();

        basicUser.printProfile();
        premiumUser.printProfile();
    }
}
