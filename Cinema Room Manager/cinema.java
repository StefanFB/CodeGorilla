package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n> ");
        int rows = scan.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        int seats = scan.nextInt();

        // Create full array within one print-for-loop
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
                // All other coordinates should be S for seat
                else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }

        // Ask for a specific seat
        System.out.print("\nEnter a row number:\n> ");
        int selectedRow = scan.nextInt();
        System.out.print("Enter a seat number in that row:\n> ");
        int selectedSeat = scan.nextInt();

        // Calculate ticket price for that seat
        int price = 0;
        if (rows * seats < 60) {
            price = 10;
        } else {
            if (selectedRow <= rows / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }

        // Display ticket price
        System.out.printf("\nTicket price: $%d\n\n", price);

        // Display new seating arrangement, displaying chosen seat as B
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
                // If coordinates match the chosen seat, print B
                else if (i == selectedRow && j == selectedSeat){
                    System.out.print("B ");
                }
                // All other coordinates should be S for seat
                else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }
}
