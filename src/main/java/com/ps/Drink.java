package com.ps;

public class Drink implements Product{
    private String name;
    private double price;

    public Drink(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice(){
        return price;
    }

    @Override
    public String getDetails(){
        return "Drink: " + name + " - $" + price;
    }
}
