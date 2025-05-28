package com.ps.ui;
import com.ps.model.Sandwich;
import com.ps.model.Chip;
import com.ps.model.Drink;
import com.ps.model.Order;


import com.ps.model.Chip;
import com.ps.model.Drink;
import com.ps.model.Order;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private Order order = new Order();

    public void launchApp() {
        while (running) {
            showHomeScreen();
        }
    }

    private void showHomeScreen() {
        System.out.println("\n===DELI-cious POS ===");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> startNewOrder();
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
                case 0 -> {
                    System.out.println("Order canceled.");
                    ordering = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void addSandwich() {
        System.out.println("add Sandwich part coming.");
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
            System.out.println("Checkout complete! Returning to Home Screen.");
        }
    }

    public void exit() {
        System.out.println("Goodbye! Thanks for using DELI-cious POS.");
        running = false;
    }

}
