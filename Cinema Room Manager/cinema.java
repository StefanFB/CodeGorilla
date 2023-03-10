package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n> ");
        int rows = scan.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        int seats = scan.nextInt();

        if (rows * seats < 60) {
            int profit = rows * seats * 10;
            System.out.println("Total income:\n$" + profit);
            return;
        } else {
            int front = rows / 2;
            int back = rows - front;
            int profit = front * seats * 10 + back * seats * 8;
            System.out.println("Total income:\n$" + profit);
            return;
        }
    }
}
