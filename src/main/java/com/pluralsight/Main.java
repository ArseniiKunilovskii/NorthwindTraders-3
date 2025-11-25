package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        int choice = -1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do?");
        System.out.println("\t1) Display all products");
        System.out.println("\t2) Display all customers");
        System.out.println("Select an option");

        while(choice<1||choice>2){
            choice = scanner.nextInt();
            scanner.nextLine();
        }

        switch (choice){
            case 1:
                displayProducts(username,password);
            case 2:
                displayCustomers(username,password);
        }

        scanner.close();
    }

    public static void displayProducts(String username, String password){

    }
    public static void displayCustomers(String username, String password){

    }
}

