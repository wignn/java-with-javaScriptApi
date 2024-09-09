package com.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Log in");
            System.out.println("2. Enter user data");
            System.out.println("3. Display user data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1, 2, 3, or 4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();
                    Login.loginUser(loginEmail, loginPassword);
                    break;
                case 2:
                    System.out.print("Enter user email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user password: ");
                    String password = scanner.nextLine();
                    GetUser.postUserData(email, name, password);
                    break;
                case 3:
                    DisplayUser.displayUserData();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.err.println("Invalid choice! Please enter 1, 2, 3, or 4.");
                    break;
            }
        }

        scanner.close();
    }
}
