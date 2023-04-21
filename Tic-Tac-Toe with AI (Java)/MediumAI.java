package tictactoe;

import java.util.Random;

public class MediumAI implements PlayerType {

    final String name = "medium";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void makeMove(TicTacToe game) {

        System.out.printf("Making move level \"%s\"\n", this.name);

        boolean weCanWin = false;
        boolean theyCanWin = false;

        int[] winningMove = new int[2];
        int[] blockingMove = new int[2];
        int emptySpace = 0;

        // Check if we can win (i.e. in a direction: have 2x mine and 1x empty). Then win.
        // Make sure we can compare against the right value of the board[][]
        int thisPlayer;
        int otherPlayer;

        if (game.state == GameState.PLAYER1) {
            thisPlayer = 1;
            otherPlayer = 2;
        } else {
            thisPlayer = 2;
            otherPlayer = 1;
        }

        // Check the horizontal lines
        for (int i = 0; i < 3; i++) {
            int ourMoves = 0;
            int emptySpaces = 0;
            int theirMoves = 0;

            for (int j = 0; j < 3; j++) {
                if (game.Board[i][j] == 0) {
                    emptySpaces++;
                    emptySpace = j;
                }
                if (game.Board[i][j] == thisPlayer) ourMoves++;
                if (game.Board[i][j] == otherPlayer) theirMoves++;
            }

            if (ourMoves == 2 && emptySpaces == 1) {
                weCanWin = true;
                winningMove[0] = i;
                winningMove[1] = emptySpace;
            } else if (theirMoves == 2 && emptySpaces == 1) {
                theyCanWin = true;
                blockingMove[0] = i;
                blockingMove[1] = emptySpace;
            }
        }

        // Check vertical lines
        for (int i = 0; i < 3; i++) {
            int ourMoves = 0;
            int emptySpaces = 0;
            int theirMoves = 0;

            for (int j = 0; j < 3; j++) {
                if (game.Board[j][i] == 0) {
                    emptySpaces++;
                    emptySpace = j;
                }
                if (game.Board[j][i] == thisPlayer) ourMoves++;
                if (game.Board[j][i] == otherPlayer) theirMoves++;
            }

            if (ourMoves == 2 && emptySpaces == 1) {
                weCanWin = true;
                winningMove[0] = emptySpace;
                winningMove[1] = i;
            } else if (theirMoves == 2 && emptySpaces == 1) {
                theyCanWin = true;
                blockingMove[0] = emptySpace;
                blockingMove[1] = i;
            }
        }

        // Check diagonals
        // Coordinates: 0,0 1,1 2,2 and 2,0 1,1 0,2
        int ourMoves = 0;
        int emptySpaces = 0;
        int theirMoves = 0;

        // Loop for diagonal down (left to right)
        for (int i = 0; i < 3; i++) {
            if (game.Board[i][i] == 0) {
                emptySpaces++;
                emptySpace = i;
            }
            if (game.Board[i][i] == thisPlayer) ourMoves++;
            if (game.Board[i][i] == otherPlayer) theirMoves++;
        }

        if (ourMoves == 2 && emptySpaces == 1) {
            weCanWin = true;
            winningMove[0] = emptySpace;
            winningMove[1] = emptySpace;
        } else if (theirMoves == 2 && emptySpaces == 1) {
            theyCanWin = true;
            blockingMove[0] = emptySpace;
            blockingMove[1] = emptySpace;
        } else {

            // Loop for diagonal up (left to right)
            ourMoves = 0;
            emptySpaces = 0;
            theirMoves = 0;
            int emptySpaceX = 0;
            int emptySpaceY = 0;

            for (int i = 0, j = 2; i < 3; i++, j--) {
                if (game.Board[j][i] == 0) {
                    emptySpaces++;
                    emptySpaceY = i;
                    emptySpaceX = j;
                }
                if (game.Board[j][i] == thisPlayer) ourMoves++;
                if (game.Board[j][i] == otherPlayer) theirMoves++;
            }

            if (ourMoves == 2 && emptySpaces == 1) {
                weCanWin = true;
                winningMove[0] = emptySpaceX;
                winningMove[1] = emptySpaceY;
            } else if (theirMoves == 2 && emptySpaces == 1) {
                theyCanWin = true;
                blockingMove[0] = emptySpaceX;
                blockingMove[1] = emptySpaceY;
            }
        }

        // Make the move
        if (weCanWin) {
            // System.out.printf("Winning: %d %d\n", winningMove[0] + 1, winningMove[1] + 1);
            addMove(game, winningMove);
        } else if (theyCanWin) {
            // System.out.printf("Blocking move: %d %d\n", blockingMove[0] + 1, blockingMove[1] + 1);
            addMove(game, blockingMove);
        } else {
            makeRandomMove(game);
            // System.out.println("We make a random move");
        }
    }
}
