package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TicTacToe game = new TicTacToe();

        while (game.state != GameState.QUIT) {
            // Ask user for player setup: PvP, PvAI, AIvAI. Check for correct command usage.
            int commandCheck = -1;
            String[] inputCommand = new String[5];
            while (commandCheck != 0) {
                // System.out.print("Input command (\"start <player1> <player2>\" or \"exit\"): ");
                System.out.print("Input command: ");
                inputCommand = scan.nextLine().split(" ");
                commandCheck = game.checkCommands(inputCommand);
            }

            // Stop the game when the GameState is set to END via the exit-command
            if (inputCommand[0].equals("exit")) {
                game.state = GameState.QUIT;
                break;
            }

            // System.out.println("Player1: " + inputCommand[1]);
            // System.out.println("Player2: " + inputCommand[2]);

            // Create player1 as human or AI player
            switch (inputCommand[1]) {
                case "user" -> game.playerX = Player.HUMAN;
                case "easy" -> game.playerX = Player.EASY_AI;
                case "medium" -> game.playerX = Player.MEDIUM_AI;
                case "hard" -> game.playerX = Player.HARD_AI;
            }

            // Create player2 as human or AI player
            switch (inputCommand[2]) {
                case "user" -> game.playerO = Player.HUMAN;
                case "easy" -> game.playerO = Player.EASY_AI;
                case "medium" -> game.playerO = Player.MEDIUM_AI;
                case "hard" -> game.playerO = Player.HARD_AI;
            }

            // Create necessary instances of each player-type
            PlayerType human = new HumanPlayer();
            PlayerType easyAI = new EasyAI();
            PlayerType mediumAI = new MediumAI();
            PlayerType hardAI = new HardAI();

            // Show board
            game.boardSetup();
            game.showBoard();

            // While the game is playing, alternate between user moves and AI moves
            while (game.state != GameState.DRAW &&
                    game.state != GameState.X_WINS &&
                    game.state != GameState.O_WINS &&
                    game.state != GameState.QUIT) {

                // Depending on the whose turn it is, make human or AI move
                if (game.state == GameState.PLAYER1) {
                    switch (game.playerX) {
                        case HUMAN -> human.makeMove(game);
                        case EASY_AI -> easyAI.makeMove(game);
                        case MEDIUM_AI -> mediumAI.makeMove(game);
                        case HARD_AI -> hardAI.makeMove(game);
                    }
                } else {
                    switch (game.playerO) {
                        case HUMAN -> human.makeMove(game);
                        case EASY_AI -> easyAI.makeMove(game);
                        case MEDIUM_AI -> mediumAI.makeMove(game);
                        case HARD_AI -> hardAI.makeMove(game);
                    }
                }

                // Show updated game board and check new game state
                game.showBoard();
                game.updateGameState();
            }
        }
    }
}
