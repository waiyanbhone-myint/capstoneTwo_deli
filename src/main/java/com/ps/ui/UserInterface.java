package com.ps.ui;

import com.ps.model.*;
import com.ps.signature.*;
import com.ps.util.FileManager;
import com.ps.util.InventoryTracker;
import com.ps.util.Logger;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private Order order = new Order();

    public void launchApp() {
        while (running) {
            showHomeScreen();
        }
    }

    private void showHomeScreen() {
        System.out.println("\n=== DELI-cious POS ===");
        System.out.println("1) New Order");
        System.out.println("2) Inventory Report");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> startNewOrder();
            case 2 -> InventoryTracker.printReport();
            case 0 -> exit();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void startNewOrder() {
        order = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n--- Order Menu ---");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("5. Signature Sandwiches");
            System.out.println("0. Cancel Order");
            System.out.print("Choose an option: ");

            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1 -> addSandwich();
                case 2 -> addDrink();
                case 3 -> addChips();
                case 4 -> {
                    checkOut();
                    ordering = false;
                }
                case 5 -> addSignatureSandwich();
                case 0 -> {
                    System.out.println("Order canceled.");
                    ordering = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addSandwich() {
        System.out.println("\n-- Add Sandwich --");

        BreadType bread = askBreadChoice();
        Size size = askSizeChoice();
        boolean toasted = askToasted();

        Sandwich sandwich = new Sandwich(bread, size, toasted);
        sandwich.addDefaultRegularToppings();

        // Add meats
        System.out.println("Available Meats: Chicken, Ham, Turkey, Roast Beef");
        System.out.println("Enter meat toppings (comma-separated or leave blank):");
        for (String meat : scanner.nextLine().split(",")) {
            if (!meat.trim().isEmpty()) sandwich.addMeat(meat.trim(), false);
        }

        // Add cheeses
        System.out.println("Available Cheese Types: American, Swiss, Cheddar, Mozzarella");
        System.out.println("Enter cheese toppings (comma-separated or leave blank):");
        for (String cheese : scanner.nextLine().split(",")) {
            if (!cheese.trim().isEmpty()) sandwich.addCheese(cheese.trim(), false);
        }

        // Show included regulars and allow removal
        System.out.println("Included regular toppings:");
        sandwich.getRegularToppings().forEach(t -> System.out.print(t.getName() + ", "));
        System.out.println("\nRemove any toppings? (comma-separated or blank to skip):");
        for (String r : scanner.nextLine().split(",")) {
            if (!r.trim().isEmpty()) sandwich.removeTopping(r.trim());
        }

        // Add new regular toppings
        System.out.println("Want to add any other regular toppings? (comma-separated or blank to skip):");
        for (String r : scanner.nextLine().split(",")) {
            if (!r.trim().isEmpty()) sandwich.addRegular(r.trim());
        }

        // Add sauces
        System.out.println("Available Sauces: Mayo, Mustard, Ketchup, BBQ, Chipotle, Ranch");
        System.out.println("Enter sauces (comma-separated or leave blank):");
        for (String sauce : scanner.nextLine().split(",")) {
            if (!sauce.trim().isEmpty()) sandwich.addSauce(sauce.trim());
        }

        order.addProduct(sandwich);
        System.out.println("✔️ Sandwich added!");
        Logger.log("Custom sandwich added: " + size.getInches() + "\" " + bread);
    }

    private void addSignatureSandwich() {
        System.out.println("\n-- Signature Sandwiches --");
        System.out.println("1. BLT");
        System.out.println("2. Philly Cheese Steak");
        System.out.print("Choose a signature sandwich: ");
        int choice = Integer.parseInt(scanner.nextLine());

        Sandwich sandwich;
        switch (choice) {
            case 1 -> sandwich = new BLT();
            case 2 -> sandwich = new PhillyCheeseSteak();
            default -> {
                System.out.println("Invalid option.");
                return;
            }
        };

        Logger.log("Signature sandwich selected");

        // Ask to customize
        System.out.print("Customize this sandwich? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Add meats (comma-separated or blank): ");
            for (String meat : scanner.nextLine().split(",")) {
                if (!meat.trim().isEmpty()) sandwich.addMeat(meat.trim(), false);
            }

            System.out.println("Add cheeses (comma-separated or blank): ");
            for (String cheese : scanner.nextLine().split(",")) {
                if (!cheese.trim().isEmpty()) sandwich.addCheese(cheese.trim(), false);
            }

            System.out.println("Add regular toppings (comma-separated or blank): ");
            for (String topping : scanner.nextLine().split(",")) {
                if (!topping.trim().isEmpty()) sandwich.addRegular(topping.trim());
            }

            System.out.println("Add sauces (comma-separated or blank): ");
            for (String sauce : scanner.nextLine().split(",")) {
                if (!sauce.trim().isEmpty()) sandwich.addSauce(sauce.trim());
            }

            System.out.println("Remove any toppings? (comma-separated or blank): ");
            for (String rem : scanner.nextLine().split(",")) {
                if (!rem.trim().isEmpty()) sandwich.removeTopping(rem.trim());
            }

            System.out.println("✨ Customization complete.");
            Logger.log("Signature sandwich customized");

        }

        order.addProduct(sandwich);
        System.out.println("✔️ Signature sandwich added!");
    }

    private void addDrink() {
        System.out.print("Enter drink flavor: ");
        String name = scanner.nextLine();

        System.out.println("Choose size: 1) Small ($2.00), 2) Medium ($2.50), 3) Large ($3.00)");
        int sizeChoice = Integer.parseInt(scanner.nextLine());

        double price = switch (sizeChoice) {
            case 1 -> 2.00;
            case 2 -> 2.50;
            case 3 -> 3.00;
            default -> {
                System.out.println("Invalid size. Defaulting to small.");
                yield 2.00;
            }
        };

        order.addProduct(new Drink(name, price));
        System.out.println("✔ Drink added!");
        Logger.log("Drink added: " + name + " ($" + price + ")");

    }

    private void addChips() {
        System.out.print("Enter chip type: ");
        Chip chip = new Chip(scanner.nextLine());
        order.addProduct(chip);
        System.out.println("✔ Chips added!");
        //Logger.log("Signature sandwich customized");
    }

    private void checkOut() {
        if (order.isEmpty()) {
            System.out.println("Cannot checkout. Order is empty.");
            return;
        }

        order.displayProduct();
        FileManager.saveReceipt(order);
        Logger.log("✅ Checkout complete. Total: $" + order.getTotalPrice());
        System.out.println("✔️ Checkout complete! Returning to Home Screen.");
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for using DELI-cious POS.");
        Logger.log("🛑 Application exited by user.");
        running = false;
    }

    // Helpers
    private BreadType askBreadChoice() {
        System.out.println("Choose bread: 1) WHITE 2) WHEAT 3) RYE 4) WRAP");
        int choice = Integer.parseInt(scanner.nextLine());
        return switch (choice) {
            case 1 -> BreadType.WHITE;
            case 2 -> BreadType.WHEAT;
            case 3 -> BreadType.RYE;
            case 4 -> BreadType.WRAP;
            default -> BreadType.WHITE;
        };
    }

    private Size askSizeChoice() {
        System.out.println("Choose size: 1) 4\" 2) 8\" 3) 12\"");
        int choice = Integer.parseInt(scanner.nextLine());
        return switch (choice) {
            case 1 -> Size.SMALL_4;
            case 2 -> Size.MEDIUM_8;
            case 3 -> Size.LARGE_12;
            default -> Size.SMALL_4;
        };
    }

    private boolean askToasted() {
        System.out.print("Toasted? (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");

    }
}
