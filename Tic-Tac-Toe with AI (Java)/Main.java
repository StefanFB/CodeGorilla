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
                System.out.print("Input command (\"start <player1> <player2>\" or \"exit\"): ");
                startCommand = scan.nextLine().split(" ");
                commandCheck = game.checkCommandUsage(startCommand);
            }

            // Stop the game when the GameState is set to END via the exit-command
            if (startCommand[0].equals("exit")) {
                game.state = GameState.END;
                break;
            }

            System.out.println("Player1: " + startCommand[1]);
            System.out.println("Player2: " + startCommand[2]);

            // Create player1 as human or AI player
            switch (startCommand[1]) {
                case "user" -> game.playerX = player.HUMAN;
                case "easy" -> game.playerX = player.EASY_AI;
                case "medium" -> game.playerX = player.MEDIUM_AI;
                case "hard" -> game.playerX = player.HARD_AI;
            }

            // Create player2 as human or AI player
            switch (startCommand[2]) {
                case "user" -> game.playerO = player.HUMAN;
                case "easy" -> game.playerO = player.EASY_AI;
                case "medium" -> game.playerO = player.MEDIUM_AI;
                case "hard" -> game.playerO = player.HARD_AI;
            }

            // Create necessary instances of each player-type
            PlayerType human = new HumanPlayerType();
            PlayerType easyAI = new EasyAI();
            PlayerType mediumAI = new MediumAI();
            // Player hardAI = new HardAI();

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
                    switch (game.playerX) {
                        case HUMAN -> human.makeMove(game);
                        case EASY_AI -> easyAI.makeMove(game);
                        case MEDIUM_AI -> mediumAI.makeMove(game);
                    }
                } else {
                    switch (game.playerO) {
                        case HUMAN -> human.makeMove(game);
                        case EASY_AI -> easyAI.makeMove(game);
                        case MEDIUM_AI -> mediumAI.makeMove(game);
                    }
                }

                // Show updated game board and check new game state
                game.showBoard();
                game.updateGameState();

            }
        }
    }
}
