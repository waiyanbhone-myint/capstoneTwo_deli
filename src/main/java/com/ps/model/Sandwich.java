package com.ps.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {

    private BreadType bread;
    private Size size;
    private boolean toasted;

    private List<Topping> meats;
    private List<Topping> cheeses;
    private List<Topping> regulars;
    private List<Topping> sauces;

    public Sandwich(BreadType bread, Size size, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;

        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regulars = new ArrayList<>();
        this.sauces = new ArrayList<>();
    }

    public void addMeat(String name, boolean isExtra) {
        meats.add(new Premium(name, ToppingCategory.MEAT) {
            public double getPrice(Size s, boolean e) {
                return super.getPrice(s, isExtra);
            }
        });
    }

    public void addCheese(String name, boolean isExtra) {
        cheeses.add(new Premium(name, ToppingCategory.CHEESE) {
            public double getPrice(Size s, boolean e) {
                return super.getPrice(s, isExtra);
            }
        });
    }

    public void addRegular(String name) {
        regulars.add(new Regular(name));
    }

    public void addSauce(String name) {
        sauces.add(new Regular(name));
    }

    @Override
    public double getPrice() {
        double total = size.getBasePrice();

        for (Topping m : meats) total += m.getPrice(size, false);
        for (Topping c : cheeses) total += c.getPrice(size, false);
        // regulars and sauces are free

        return total;
    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Sandwich [");
        details.append(size.getInches()).append("\" ").append(bread);
        details.append(toasted ? ", Toasted" : ", Not Toasted");

        if (!meats.isEmpty()) {
            details.append("\n  Meats: ");
            meats.forEach(m -> details.append(m.getName()).append(", "));
        }
        if (!cheeses.isEmpty()) {
            details.append("\n  Cheeses: ");
            cheeses.forEach(c -> details.append(c.getName()).append(", "));
        }
        if (!regulars.isEmpty()) {
            details.append("\n  Toppings: ");
            regulars.forEach(r -> details.append(r.getName()).append(", "));
        }
        if (!sauces.isEmpty()) {
            details.append("\n  Sauces: ");
            sauces.forEach(s -> details.append(s.getName()).append(", "));
        }

        details.append("\n  Price: $").append(getPrice());
        details.append("\n]");

        return details.toString();
    }
}
