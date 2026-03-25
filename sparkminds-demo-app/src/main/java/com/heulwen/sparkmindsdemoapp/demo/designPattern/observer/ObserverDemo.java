package com.heulwen.sparkmindsdemoapp.demo.designPattern.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        Shop shop = new Shop();

        Observer c1 = new Customer("Nguyen");
        Observer c2 = new Customer("An");

        shop.registerObserver(c1);
        shop.registerObserver(c2);

        shop.addNewProduct("iPhone 17");
    }
}
