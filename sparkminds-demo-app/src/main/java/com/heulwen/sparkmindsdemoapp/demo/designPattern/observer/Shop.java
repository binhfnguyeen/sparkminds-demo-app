package com.heulwen.sparkmindsdemoapp.demo.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

// Concrete Subject
public class Shop implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String newProduct;


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(newProduct);
        }
    }

    // Khi có sản phẩm mới
    public void addNewProduct(String product) {
        System.out.println("Shop đăng sản phẩm: " + product);
        this.newProduct = product;

        notifyObservers(); // trigger
    }
}
