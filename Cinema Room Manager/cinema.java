package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ask for dimensions of room
        System.out.print("Enter the number of rows:\n> ");
        int rows = scan.nextInt(); // rows is the y component
        System.out.print("Enter the number of seats in each row:\n> ");
        int seats = scan.nextInt(); // seats is the x component

        // Create an 3D-array to create a 2D grid with a third indicating whether a seat is free
        // The rows and seats are +1 larger, because the surrounding numbers also need to be printed
        char[][][] cinemaRoom = new char[rows + 1][seats + 1][1];

        // Set all values of the array to 'A', indicating a seat is available
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                cinemaRoom[i][j][0] = 'A';
            }
        }

        // Show menu, other functions will also call showMenu to keep the program running
        showMenu(cinemaRoom, rows, seats);
    }

    public static void buyTicket(char[][][] cinema, int rows, int seats) {
            Scanner scan = new Scanner(System.in);
            // Ask for a specific seat
            System.out.print("\nEnter a row number:\n> ");
            int selectedRow = scan.nextInt();
            System.out.print("Enter a seat number in that row:\n> ");
            int selectedSeat = scan.nextInt();

            // Calculate ticket price for that seat
            int price;
            if (rows * seats < 60) {
                price = 10;
            } else {
                if (selectedRow <= rows / 2) {
                    price = 10;
                } else {
                    price = 8;
                }
            }

            cinema[selectedRow][selectedSeat][0] = 'T';

            // Display ticket price
            System.out.printf("\nTicket price: $%d\n\n", price);

            // Restart menu
            showMenu(cinema, rows, seats);
        }

    public static void showMenu(char[][][] cinemaRoom, int rows, int seats) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n1. Show the seats\n2. Buy a ticket\n0. Exit\n> ");
        int choice = scan.nextInt();
        switch (choice) {
            case 0:
                return;
            case 1:
                printRoom(cinemaRoom, rows, seats);
                break;
            case 2:
                buyTicket(cinemaRoom, rows, seats);
                break;
        }
    }

    public static void printRoom(char[][][] room, int rows, int seats) {
        System.out.println("\nCinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                // Top-left corner should be empty
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                }
                // First row should be the seatnumbers
                else if (i == 0) {
                    System.out.printf("%d ", j);
                }
                // First column should be the row number
                else if (j == 0) {
                    System.out.printf("%d ", i);
                }
                // If a seat is taken ('T'), print B
                else if (room[i][j][0] == 'T') {
                    System.out.print("B ");
                }
                // All other seats should be free, so print S
                else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
        // Restart menu
        showMenu(room, rows, seats);
    }
}
