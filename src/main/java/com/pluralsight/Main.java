package com.pluralsight;

import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("Please provide username and password");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        int choice = -1;

        Scanner scanner = new Scanner(System.in);
        while (choice != 0) {
            choice = -1;
            System.out.println("\nWhat do you want to do?");
            System.out.println("\t1) Display all products");
            System.out.println("\t2) Display all customers");
            System.out.println("\t0) Exit");
            System.out.println("Select an option");

            while (choice < 0 || choice > 2) {
                choice = scanner.nextInt();
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    displayProducts(username, password);
                    break;
                case 2:
                    displayCustomers(username, password);
                    break;
                default:
                    break;
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    public static void displayProducts(String username, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );



            String query = """
                SELECT ProductId, ProductName, UnitPrice, UnitsInStock from products
                """;

            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery(query);

            System.out.println("Id   Name                                   Price   Stock");
            System.out.println("------------------------------------------------------------");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int stock = resultSet.getInt(4);
                System.out.printf("%-4d %-35s %8.2f %6d%n", id, name, price, stock);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public static void displayCustomers(String username, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );



            String query = """
                SELECT ContactName, CompanyName, City, Country, Phone from customers
                """;

            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery(query);

            String format = "%-25s %-35s %-15s %-15s %-15s%n";

            System.out.printf(format, "ContactName", "CompanyName", "City", "Country", "Phone");
            System.out.println("-----------------------------------------------------------------------------------------");
            while (resultSet.next()){
                String contact = resultSet.getString(1);
                String name = resultSet.getString(2);
                String city = resultSet.getString(3);
                String country = resultSet.getString(4);
                String phone = resultSet.getString(5);

                System.out.printf(format, contact, name, city, country, phone);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

