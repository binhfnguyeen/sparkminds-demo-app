package com.heulwen.sparkmindsdemoapp.demo.designPattern.prototype;

public class Weapon implements Cloneable{
    private String name;

    public Weapon(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Cần implement clone cho các object con nếu muốn Deep Copy
    @Override
    protected Weapon clone() throws CloneNotSupportedException {
        return (Weapon) super.clone();
    }
}
