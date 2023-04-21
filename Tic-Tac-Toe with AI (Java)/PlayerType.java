package tictactoe;

import java.util.Random;

public interface PlayerType {

    String getName();

    void makeMove(TicTacToe game);

    default int checkMove(TicTacToe game, int[] move) {

        // Check for occupied field, else do the move
        if (game.Board[move[0]][move[1]] != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    default void addMove(TicTacToe game, int[] move) {
        switch (game.state) {
            case PLAYER1 -> {
                game.Board[move[0]][move[1]] = 1;
                game.state = GameState.PLAYER2;
            }
            case PLAYER2 -> {
                game.Board[move[0]][move[1]] = 2;
                game.state = GameState.PLAYER1;
            }
        }
    }

    default void addMove(TicTacToe game, int row, int col) {
        switch (game.state) {
            case PLAYER1 -> {
                game.Board[row][col] = 1;
                game.state = GameState.PLAYER2;
            }
            case PLAYER2 -> {
                game.Board[row][col] = 2;
                game.state = GameState.PLAYER1;
            }
        }
    }

    default void makeRandomMove(TicTacToe game) {
        Random random = new Random();

        int[] move = new int[2];
        int checkGeneratedInput = -1;

        while (checkGeneratedInput != 0) {
            move[0] = random.nextInt(3);
            move[1] = random.nextInt(3);

            checkGeneratedInput = checkMove(game, move);
        }

        addMove(game, move);
    }
}
