package com.ps.model;

public class Premium extends Topping {

    public Premium(String name, ToppingCategory category) {
        super(name, category);
    }

    @Override
    public double getPrice(Size size, boolean isExtra) {
        double price = 0.0;

        switch (category) {
            case MEAT -> {
                price = switch (size) {
                    case SMALL_4 -> 1.00;
                    case MEDIUM_8 -> 2.00;
                    case LARGE_12 -> 3.00;
                };
                if (isExtra) {
                    price += switch (size) {
                        case SMALL_4 -> 0.50;
                        case MEDIUM_8 -> 1.00;
                        case LARGE_12 -> 1.50;
                    };
                }
            }
            case CHEESE -> {
                price = switch (size) {
                    case SMALL_4 -> 0.75;
                    case MEDIUM_8 -> 1.50;
                    case LARGE_12 -> 2.25;
                };
                if (isExtra) {
                    price += switch (size) {
                        case SMALL_4 -> 0.30;
                        case MEDIUM_8 -> 0.60;
                        case LARGE_12 -> 0.90;
                    };
                }
            }
            default -> price = 0.0;
        }

        return price;
    }
}
