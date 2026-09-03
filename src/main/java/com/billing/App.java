package com.billing;

import java.util.Scanner;

public class App {

    public static double calculateBill(String plan, double dataUsed) {
        double basePrice;
        double dataLimit; // in GB
        double overageRate; // price per extra GB

        switch (plan.toLowerCase()) {
            case "basic":
                basePrice = 20.0;
                dataLimit = 10.0;
                overageRate = 5.0;
                break;
            case "standard":
                basePrice = 40.0;
                dataLimit = 50.0;
                overageRate = 3.0;
                break;
            case "premium":
                basePrice = 70.0;
                dataLimit = 100.0;
                overageRate = 1.5;
                break;
            default:
                throw new IllegalArgumentException("Unknown plan type: " + plan);
        }

        double totalBill = basePrice;
        if (dataUsed > dataLimit) {
            double extraData = dataUsed - dataLimit;
            totalBill += extraData * overageRate;
        }

        return totalBill;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.out.println("your "));
        String choice;

        System.out.println("=== Internet Data Usage Billing System ===");

        do {
            System.out.print("\nEnter Customer Name: ");
            String name = scanner.nextLine();

            String plan = "";
            while (true) {
                System.out.print("Enter Plan Type (basic, standard, premium): ");
                plan = scanner.nextLine().trim();
                if (plan.equalsIgnoreCase("basic") || plan.equalsIgnoreCase("standard") || plan.equalsIgnoreCase("premium")) {
                    break;
                }
                System.out.println("Invalid plan. Please try again.");
            }

            System.out.print("Enter Data Consumed (in GB): ");
            double dataUsed = scanner.nextDouble();
            scanner.nextLine(); // Clear scanner buffer

            try {
                double finalBill = calculateBill(plan, dataUsed);
                System.out.println("\n--- Billing Invoice ---");
                System.out.println("Customer Name: " + name);
                System.out.println("Selected Plan: " + plan.toUpperCase());
                System.out.println("Data Consumed: " + dataUsed + " GB");
                System.out.printf("Total Monthly Bill: $%.2f\n", finalBill);
                System.out.println("-----------------------");
            } catch (Exception e) {
                System.out.println("Error calculating bill: " + e.getMessage());
            }

            System.out.print("\nDo you want to process another customer? (yes/no): ");
            choice = scanner.nextLine().trim();

        } while (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"));

        System.out.println("\nThank you for using the Billing System. Goodbye!");
        scanner.close();
    }
}
