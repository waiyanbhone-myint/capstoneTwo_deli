package com.ps.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {

    private BreadType bread;
    private Size size;
    private boolean toasted;

    private List<Topping> meats = new ArrayList<>();
    private List<Topping> cheeses = new ArrayList<>();
    private List<Topping> regulars = new ArrayList<>();
    private List<Topping> sauces = new ArrayList<>();

    public Sandwich(BreadType bread, Size size, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;
    }

    // ✅ Add Toppings
    public void addMeat(String name, boolean isExtra) {
        meats.add(new Premium(name, ToppingCategory.MEAT) {
            @Override
            public double getPrice(Size s, boolean e) {
                return super.getPrice(s, isExtra);
            }
        });
    }

    public void addCheese(String name, boolean isExtra) {
        cheeses.add(new Premium(name, ToppingCategory.CHEESE) {
            @Override
            public double getPrice(Size s, boolean e) {
                return super.getPrice(s, isExtra);
            }
        });
    }

    public void addRegular(String name) {
        // prevent duplicate
        if (regulars.stream().noneMatch(t -> t.getName().equalsIgnoreCase(name))) {
            regulars.add(new Regular(name));
        }
    }

    public void addSauce(String name) {
        if (sauces.stream().noneMatch(t -> t.getName().equalsIgnoreCase(name))) {
            sauces.add(new Regular(name)); // Sauce is treated like regular but categorized separately
        }
    }

    // ✅ Remove Toppings
    public void removeTopping(String name) {
        meats.removeIf(t -> t.getName().equalsIgnoreCase(name));
        cheeses.removeIf(t -> t.getName().equalsIgnoreCase(name));
        regulars.removeIf(t -> t.getName().equalsIgnoreCase(name));
        sauces.removeIf(t -> t.getName().equalsIgnoreCase(name));
    }

    // ✅ Add Topping by Topping object
    public void addTopping(Topping topping) {
        switch (topping.getCategory()) {
            case MEAT -> addMeat(topping.getName(), false);
            case CHEESE -> addCheese(topping.getName(), false);
            case REGULAR -> addRegular(topping.getName());
            case SAUCE -> addSauce(topping.getName());
        }
    }

    // ✅ Automatically Add Included Regular Toppings
    public void addDefaultRegularToppings() {
        String[] defaultToppings = {
                "lettuce", "peppers", "onions", "tomatoes", "jalapeños",
                "cucumbers", "pickles", "guacamole", "mushrooms"
        };
        for (String topping : defaultToppings) {
            addRegular(topping);
        }
    }

    // ✅ Get Total Price
    @Override
    public double getPrice() {
        double total = size.getBasePrice();
        for (Topping m : meats) total += m.getPrice(size, false);
        for (Topping c : cheeses) total += c.getPrice(size, false);
        return total; // regulars and sauces are free
    }

    // ✅ Get Receipt-style Details
    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append(size.getInches()).append("\" ").append(bread);
        details.append(toasted ? " (Toasted)" : " (Not Toasted)");

        if (!meats.isEmpty()) {
            details.append("\n  Meats: ");
            meats.forEach(m -> details.append(m.getName()).append(", "));
        }

        if (!cheeses.isEmpty()) {
            details.append("\n  Cheeses: ");
            cheeses.forEach(c -> details.append(c.getName()).append(", "));
        }

        if (!regulars.isEmpty()) {
            details.append("\n  Regular Toppings: ");
            regulars.forEach(r -> details.append(r.getName()).append(", "));
        }

        if (!sauces.isEmpty()) {
            details.append("\n  Sauces: ");
            sauces.forEach(s -> details.append(s.getName()).append(", "));
        }

        details.append("\n  Total: $").append(String.format("%.2f", getPrice()));
        return details.toString();
    }

    // ✅ Getters
    public Size getSize() { return size; }
    public BreadType getBread() { return bread; }
    public boolean isToasted() { return toasted; }

    public List<Topping> getRegularToppings() { return regulars; }
    public List<Topping> getSauces() { return sauces; }
    public List<Topping> getMeats() { return meats; }
    public List<Topping> getCheeses() { return cheeses; }
}
