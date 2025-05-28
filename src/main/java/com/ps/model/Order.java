package com.ps.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
    }

    public void displayProduct() {
        if (items.isEmpty()) {
            System.out.println("Order is empty.");
            return;
        }

        System.out.println("\n🧾 Order Details:");
        for (Product item : items) {
            System.out.println(item.getDetails());
        }

        System.out.println("Total price: " + getTotalPrice());
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Product> getItems() {
        return items;
    }
}
