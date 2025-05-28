package com.ps;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private Order order = new Order();

    public void showMenu(){
        while(running){
            System.out.println("\n--- DELI-cious POS Menu ---");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. View Order");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int userChoice = scanner.nextInt();

            switch(userChoice){
                case 1 -> addSandwich();
                case 2 -> addDrink();
                case 3 -> addChips();
                case 4 -> viewOrder();
                case 5 -> checkOut();
                case 6 -> exit();
                default -> System.out.println("Sorry invalid option!!");
            }
        }
    }

    public void addSandwich(){
        System.out.println("add Sandwich part start.");
    }

    public void addDrink(){
        scanner.nextLine();
        System.out.println("Enter drink name: ");
        String name = scanner.nextLine();

        System.out.println("Enter drink price: ");
        double price = scanner.nextDouble();

        Drink drink = new Drink(name, price);
        order.addProduct(drink);

        System.out.println("Drink added!");
    }

    public void addChips(){
        System.out.println("add Chips part start.");
    }

    public void viewOrder(){
        order.displayProduct();
    }

    public void checkOut(){
        if(order.isEmpty()){
            System.out.println("Cannot checkout. Order is empty.");
        }else{
            order.displayProduct();
            System.out.println("Checkout Complete!");
            System.exit(0);
        }
    }

    public void exit(){
        System.out.println("exit");
        running = false;
    }

}
