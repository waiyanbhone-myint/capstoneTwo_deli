package com.ps.ui;
import com.ps.model.Size;
import com.ps.model.Sandwich;
import com.ps.model.Chip;
import com.ps.model.Drink;
import com.ps.model.Order;
import com.ps.model.BreadType;
import com.ps.signature.*;
import com.ps.util.FileManager;
import com.ps.util.Logger;
//import com.ps.util.SalesReport;


import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private Order order = new Order();
    //private SalesReport report = new SalesReport();

    public void launchApp() {
        while (running) {
            showHomeScreen();
        }
    }

    private void showHomeScreen() {
        System.out.println("\n===DELI-cious POS ===");
        System.out.println("1) New Order");
        //System.out.println("2) View Sales Report");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> startNewOrder();
            //case 2 -> report.printReport();
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

    private void addSignatureSandwich() {
        System.out.println("\n-- Signature Sandwiches --");
        System.out.println("1. BLT");
        System.out.println("2. Philly Cheese Steak");
        System.out.print("Choose a signature sandwich: ");

        int choice = Integer.parseInt(scanner.nextLine());
        Sandwich sandwich = null;

        switch (choice) {
            case 1 -> {
                sandwich = new BLT();
                Logger.log("Signature sandwich selected: BLT");
            }
            case 2 -> {
                sandwich = new PhillyCheeseSteak();
                Logger.log("Signature sandwich selected: Philly Cheese Steak");
            }
            default -> {
                System.out.println("Invalid option.");
                return;
            }
        }

        // Ask to customize
        System.out.print("Do you want to customize this sandwich? (yes/no): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            // Add meats
            System.out.println("Add meats (comma-separated or leave blank): ");
            String[] meats = scanner.nextLine().split(",");
            for (String meat : meats) {
                if (!meat.trim().isEmpty()) sandwich.addMeat(meat.trim(), false);
            }

            // Add cheeses
            System.out.println("Add cheeses (comma-separated or leave blank): ");
            String[] cheeses = scanner.nextLine().split(",");
            for (String cheese : cheeses) {
                if (!cheese.trim().isEmpty()) sandwich.addCheese(cheese.trim(), false);
            }

            // Add regular toppings
            System.out.println("Add regular toppings (comma-separated or leave blank): ");
            String[] regulars = scanner.nextLine().split(",");
            for (String topping : regulars) {
                if (!topping.trim().isEmpty()) sandwich.addRegular(topping.trim());
            }

            // Add sauces
            System.out.println("Add sauces (comma-separated or leave blank): ");
            String[] sauces = scanner.nextLine().split(",");
            for (String sauce : sauces) {
                if (!sauce.trim().isEmpty()) sandwich.addSauce(sauce.trim());
            }

            // Remove toppings
            System.out.println("Remove toppings (comma-separated names): ");
            String[] removals = scanner.nextLine().split(",");
            for (String rem : removals) {
                if (!rem.trim().isEmpty()) sandwich.removeTopping(rem.trim());
            }

            System.out.println("✨ Customization complete.");
        }

        order.addProduct(sandwich);
        System.out.println("✔️ Signature sandwich added!");
    }


    public void addSandwich() {
        System.out.println("\n-- Add Sandwich --");

        System.out.println("Choose bread: 1) WHITE 2) WHEAT 3) RYE 4) WRAP");
        int breadChoice = Integer.parseInt(scanner.nextLine());
        BreadType bread = switch (breadChoice) {
            case 1 -> BreadType.WHITE;
            case 2 -> BreadType.WHEAT;
            case 3 -> BreadType.RYE;
            case 4 -> BreadType.WRAP;
            default -> BreadType.WHITE;
        };
        // Size
        System.out.println("Choose size: 1) 4\" 2) 8\" 3) 12\"");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        Size size = switch (sizeChoice) {
            case 1 -> Size.SMALL_4;
            case 2 -> Size.MEDIUM_8;
            case 3 -> Size.LARGE_12;
            default -> Size.SMALL_4;
        };

        // Toasted
        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        // Meats
        System.out.println("Enter meat toppings (comma-separated):");
        String[] meats = scanner.nextLine().split(",");
        for (String meat : meats) {
            if (!meat.trim().isEmpty()) sandwich.addMeat(meat.trim(), false);
        }

        // Cheeses
        System.out.println("Enter cheese toppings (comma-separated):");
        String[] cheeses = scanner.nextLine().split(",");
        for (String cheese : cheeses) {
            if (!cheese.trim().isEmpty()) sandwich.addCheese(cheese.trim(), false);
        }

        // Regular toppings
        System.out.println("Enter regular toppings (comma-separated):");
        String[] regulars = scanner.nextLine().split(",");
        for (String reg : regulars) {
            if (!reg.trim().isEmpty()) sandwich.addRegular(reg.trim());
        }

        // Sauces
        System.out.println("Enter sauces (comma-separated):");
        String[] sauces = scanner.nextLine().split(",");
        for (String sauce : sauces) {
            if (!sauce.trim().isEmpty()) sandwich.addSauce(sauce.trim());
        }

        order.addProduct(sandwich);
        System.out.println("✔️ Sandwich added!");
    }

    public void addDrink() {
        System.out.println("Enter drink flavor: ");
        String name = scanner.nextLine();

        System.out.println("Choose size: 1) Small ($2.00), 2) Medium ($2.50), 3) Large ($3.00)");
        int sizeChoice = Integer.parseInt(scanner.nextLine());

        double price;
        switch (sizeChoice) {
            case 1 -> price = 2.00;
            case 2 -> price = 2.50;
            case 3 -> price = 3.00;
            default -> {
                System.out.println("Invalid size. Defaulting to small.");
                price = 2.00;
            }
        }

        Drink drink = new Drink(name, price);
        order.addProduct(drink);

        System.out.println("✔ Drink added!");
        Logger.log("Drink added: " + name + " ($" + price + ")");
    }

    public void addChips() {
        System.out.println("Enter chip type: ");
        String chipType = scanner.nextLine();

        Chip chip = new Chip(chipType);
        order.addProduct(chip);

        System.out.println("✔ Chips added!");
    }

    public void checkOut() {
        if (order.isEmpty()) {
            System.out.println("Cannot checkout. Order is empty.");
        } else {
            order.displayProduct();
            FileManager.saveReceipt(order);  // ✅ Pass entire order
            System.out.println("Checkout complete! Returning to Home Screen.");
        }
        Logger.log("✅ Checkout complete. Total: $" + order.getTotalPrice());
    }


    public void exit() {
        System.out.println("Goodbye! Thanks for using DELI-cious POS.");
        Logger.log("🛑 Application exited by user.");

        running = false;
    }

}
