package com.heulwen.sparkmindsdemoapp.demo.designPattern.prototype;

public class PrototypeDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("Đang khởi tạo Nguyên mẫu tốn kém...");
        Weapon sword = new Weapon("Kiếm sắt");
        Monster prototypeMonster = new Monster("Goblin", 100, sword);

        System.out.println("--- Shallow Copy ---");
        Monster monster1 = prototypeMonster.shallowClone();

        // Thay đổi vũ khí của bản clone
        monster1.setWeaponName("Đao Độc");

        // Kết quả: BẢN GỐC CŨNG BỊ ĐỔI THEO! Vì cả 2 trỏ chung 1 object Weapon
        prototypeMonster.printInfo("Prototype"); // In ra: Đao Độc
        monster1.printInfo("Monster 1");         // In ra: Đao Độc

        System.out.println("--- Deep Copy ---");
        // Reset lại vũ khí bản chuẩn
        prototypeMonster.setWeaponName("Kiếm Sắt");

        Monster monster2 = prototypeMonster.deepClone();

        // Thay đổi vũ khí của bản clone số 2
        monster2.setWeaponName("Cung Tên");

        // Kết quả: AN TOÀN! Bản gốc không bị ảnh hưởng.
        prototypeMonster.printInfo("Prototype"); // In ra: Kiếm Sắt
        monster2.printInfo("Monster 2");         // In ra: Cung Tên
    }
}
