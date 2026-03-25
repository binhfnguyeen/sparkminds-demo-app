package com.heulwen.sparkmindsdemoapp.demo.designPattern.decorator;

public class Demo {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        coffee = new SugarDecorator(coffee);
        coffee = new MilkDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.cost());
    }
}
