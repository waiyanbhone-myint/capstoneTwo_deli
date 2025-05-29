package com.ps.model;

public abstract class Topping {

    protected String name;
    protected ToppingCategory category;

    public Topping(String name, ToppingCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public ToppingCategory getCategory() {
        return category;
    }

    public abstract double getPrice(Size size, boolean isExtra);
}
