package com.heulwen.sparkmindsdemoapp.demo.designPattern.decorator;

public class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}
