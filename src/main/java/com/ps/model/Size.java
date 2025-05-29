package com.ps.model;

public enum Size {
    SMALL_4(4, 5.50),
    MEDIUM_8(8, 7.00),
    LARGE_12(12, 8.50);

    private final int inches;
    private final double basePrice;

    Size(int inches, double basePrice) {
        this.inches = inches;
        this.basePrice = basePrice;
    }

    public int getInches() {
        return inches;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
