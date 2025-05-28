package com.ps.model;

public class Chip implements Product {

    private String flavor;
    private static final double PRICE = 1.50;

    public Chip(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }

    @Override
    public String getDetails() {
        return "Chips: " + flavor + " - $" + PRICE;
    }
}
