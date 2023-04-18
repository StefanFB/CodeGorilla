package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TicTacToe game = new TicTacToe();

        // Ask for initial setup of the 3x3 table
        System.out.print("Enter the cells: ");
        String[] setup = scan.nextLine().split("");

        // Show board with the given setup
        game.readSetup(setup);
        game.showBoard();

        // Get coordinates, keep asking until correct input
        int inputCheck = -1;
        while (inputCheck != 0) {
            System.out.print("Enter the coordinates: ");
            String[] input = scan.nextLine().split(" ");
            inputCheck = game.checkInput(input);
        }

        // Show updated game board
        game.showBoard();

        // Check for winning conditions, draw or not finished
        game.updateGameState();
    }
}
