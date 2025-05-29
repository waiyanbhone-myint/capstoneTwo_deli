package com.ps.model;

public class Regular extends Topping {

    public Regular(String name) {
        super(name, ToppingCategory.REGULAR);
    }

    @Override
    public double getPrice(Size size, boolean isExtra) {
        return 0.0; // Regular toppings are free
    }
}
