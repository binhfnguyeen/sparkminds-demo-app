package com.heulwen.sparkmindsdemoapp.demo.designPattern.prototype;

public class Monster implements Cloneable{
    private String type;
    private int health;
    private Weapon weapon; // Tham chiếu đến một object khác

    public Monster(String type, int health, Weapon weapon){
        // Giả sử việc khởi tạo này tốn 3 giây do phải load 3D Model...
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        this.type = type;
        this.health = health;
        this.weapon = weapon;
    }

    public void setWeaponName(String name) { this.weapon.setName(name); }
    public void printInfo(String monsterName) {
        System.out.println(monsterName + " | Type: " + type + ", Health: " + health + ", Weapon: " + weapon.getName());
    }

    // Shallow copy
    public Monster shallowClone() throws CloneNotSupportedException {
        // Chỉ copy nguyên thủy, biến weapon chỉ copy địa chỉ vùng nhớ
        return (Monster) super.clone();
    }

    // Deep Copy
    public Monster deepClone() throws CloneNotSupportedException {
        Monster cloned = (Monster) super.clone();
        // Phải chủ động cả đối tượng con bên trong
        cloned.weapon = this.weapon.clone();
        return cloned;
    }
}
