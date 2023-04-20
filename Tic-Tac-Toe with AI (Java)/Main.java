package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TicTacToe game = new TicTacToe();
        AI ai = new EasyAI();

        // Setup and show board
        game.boardSetup();
        game.showBoard();

        // While the game is playing, alternate between user moves and AI moves
        while (game.state != GameState.DRAW && game.state != GameState.X_WINS && game.state != GameState.O_WINS) {

            // Depending on the whose turn it is, make human or AI move
            if (game.state == GameState.PLAYER1) {

                // Get human player move: get coordinates, keep asking until correct input
                int inputCheck = -1;
                while (inputCheck != 0) {
                    System.out.print("Enter the coordinates: ");
                    String[] input = scan.nextLine().split(" ");
                    inputCheck = game.checkInput(input);
                }
            } else {

                // Let AI make a move
                String str = String.format("Making move level \"%s\"", ai.getName());
                System.out.println(str);
                int inputCheckAI = -1;
                while (inputCheckAI != 0) {
                    int[] MoveAI = ai.makeMove();
                    inputCheckAI = game.checkInput(MoveAI);
                }
            }

            // Show updated game board and check new game state
            game.showBoard();
            game.updateGameState();

        }
    }
}
