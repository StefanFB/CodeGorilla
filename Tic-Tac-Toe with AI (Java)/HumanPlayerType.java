package tictactoe;

import java.util.Scanner;

public class HumanPlayerType implements PlayerType {

    final String name = "human";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void makeMove(TicTacToe game) {
        Scanner scan = new Scanner(System.in);

        int inputCheck = -1;
        while (inputCheck != 0) {
            System.out.print("Enter the coordinates: ");
            String[] input = scan.nextLine().split(" ");
            inputCheck = checkMove(game, input);
        }
    }

    public int checkMove(TicTacToe game, String[] input) {
        int row;
        int col;

        // Handle non-integer input
        try {
            row = Integer.parseInt(input[0]);
            col = Integer.parseInt(input[1]);
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return 1;
        }

        // Lower each coordinate by 1, because the array starts at 0
        row--;
        col--;

        // Check for existing coordinates
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            return 2;
        }

        // Check for occupied field, else do the move
        if (game.Board[row][col] != 0) {
            System.out.println("This cell is occupied! Choose another one!");
            return 3;
        } else {
            addMove(game, row, col);
            return 0;
        }
    }
}
