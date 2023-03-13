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

        // Create an 2D-array to create a seating arrangement
        // The rows and seats are +1 larger, because the surrounding numbers also need to be printed
        char[][] cinemaRoom = new char[rows + 1][seats + 1];

        // Set all values of the array to 'S', indicating a seat is available
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                cinemaRoom[i][j] = 'S';
            }
        }

        // Show menu, other functions will also showMenu to keep the program running
        showMenu(cinemaRoom, rows, seats);
    }

    public static void buyTicket(char[][] cinema, int rows, int seats) {
        Scanner scan = new Scanner(System.in);
        // Ask for a specific seat
        System.out.print("\nEnter a row number:\n> ");
        int selectedRow = scan.nextInt();
        System.out.print("Enter a seat number in that row:\n> ");
        int selectedSeat = scan.nextInt();

        // Check if seat exists (i.e. not out of bounds)
        if (selectedRow > rows || selectedSeat > seats) {
            System.out.println("Wrong input!");
            buyTicket(cinema, rows, seats);
        }

        // Check if seat is available
        if (cinema[selectedRow][selectedSeat] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinema, rows, seats);
        }

        // Calculate ticket price for that seat
        int price;
        int frontRows = rows / 2;
        int backRows = rows - frontRows;

        if (rows * seats < 60) {
            price = 10;
        } else {
            if (selectedRow <= frontRows) {
                price = 10;
            } else {
                price = 8;
            }
        }

        cinema[selectedRow][selectedSeat] = 'B';

        // Display ticket price
        System.out.printf("\nTicket price: $%d\n\n", price);

        // Restart menu
        showMenu(cinema, rows, seats);
    }

    public static void printRoom(char[][] room, int rows, int seats) {
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
                // If a seat is bought ('B'), print B
                else if (room[i][j] == 'B') {
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

    public static void showMenu(char[][] cinemaRoom, int rows, int seats) {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
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
            case 3:
                showStats(cinemaRoom, rows, seats);
                break;
            default:
                showMenu(cinemaRoom, rows, seats);
        }
    }

    public static void showStats(char[][] cinemaRoom, int rows, int seats) {
        int purchasedTicketsFront = 0;
        int purchasedTicketsBack = 0;
        int frontRow = rows / 2;
        int backRow = rows - frontRow;

        // If less than 60 seats, all tickets are $10, equal to the price of a front row ticket
        if (rows * seats < 60) {
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (cinemaRoom[i][j] == 'B') {
                        purchasedTicketsFront++;
                    }
                }
            }
        }

        // If 60 seats or more, divide into front and back row, with the back row seats selling for $8
        else {
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (cinemaRoom[i][j] == 'B' && i <= frontRow) {
                        purchasedTicketsFront++;
                    } else if (cinemaRoom[i][j] == 'B'){
                        purchasedTicketsBack++;
                    }
                }
            }
        }

        // Calculate and then print statistics
        int purchasedTickets = purchasedTicketsFront + purchasedTicketsBack;
        float percentageSold = (float) purchasedTickets / (rows * seats) * 100;
        int income = purchasedTicketsFront * 10 + purchasedTicketsBack * 8;
        int totalIncome = seats * rows < 60 ? seats * rows * 10 : (seats * 10 * frontRow) + (seats * 8 * backRow);

        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentageSold);
        System.out.printf("Current income: $%d\n", income);
        System.out.printf("Total income: $%d\n", totalIncome);

        // Return to menu
        showMenu(cinemaRoom, rows, seats);
    }
}
