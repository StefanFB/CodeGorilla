package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TicTacToe game = new TicTacToe();

        while (game.state != GameState.END) {
            // Ask user for player setup: PvP, PvAI, AIvAI. Check for correct command usage.
            int commandCheck = -1;
            String[] startCommand = new String[5];
            while (commandCheck != 0) {
                System.out.print("Input command (start [player1] [player2] / exit): ");
                startCommand = scan.nextLine().split(" ");
                commandCheck = game.checkCommandUsage(startCommand);
            }

            if (startCommand[0].equals("exit")) {
                game.state = GameState.END;
                break;
            }

            System.out.println("Player1: " + startCommand[1]);
            System.out.println("Player2: " + startCommand[2]);

            // Create player1 as human or ai player
            if (startCommand[1].equals("user")) {
                game.playerX = Player.HUMAN;
            } else if (startCommand[1].equals("easy")) {
                game.playerX = Player.EASY_AI;
            }

            // Create player2 as human or ai player
            if (startCommand[2].equals("user")) {
                game.playerO = Player.HUMAN;
            } else if (startCommand[2].equals("easy")) {
                game.playerO = Player.EASY_AI;
            }

            AI ai = new EasyAI();

            // Setup and show board
            game.boardSetup();
            game.showBoard();

            // While the game is playing, alternate between user moves and AI moves
            while (game.state != GameState.DRAW &&
                    game.state != GameState.X_WINS &&
                    game.state != GameState.O_WINS &&
                    game.state != GameState.END) {

                // Depending on the whose turn it is, make human or AI move
                if (game.state == GameState.PLAYER1) {
                    if (game.playerX == Player.HUMAN) {

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
                } else {
                    if (game.playerO == Player.HUMAN) {

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
                }

                // Show updated game board and check new game state
                game.showBoard();
                game.updateGameState();

            }
        }
    }
}
