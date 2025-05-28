package com.ps;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

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
        System.out.println("add Drink part start.");
    }

    public void addChips(){
        System.out.println("add Chips part start.");
    }

    public void viewOrder(){
        System.out.println("view Order part start.");
    }

    public void checkOut(){
        System.out.println("Checkout part start");
    }

    public void exit(){
        System.out.println("exit");
        running = false;
    }

}
