package tictactoe;

public class HardAI implements PlayerType {

    final String name = "hard";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void makeMove(TicTacToe game) {
        System.out.printf("Making move level \"%s\"\n", this.name);

        // Get all possible moves, create int[] to save the score of each move, keep track of best move.
        Move[] moveOptions = game.getPossibleMoves();
        int[] moveScores = new int[moveOptions.length];
        Move bestMove = new Move(1, 1);

        // If playerX is playing, we want to maximize the score (thus start at low value, update when higher found)
        if (game.state == GameState.PLAYER1) {
            int int_best_move = Integer.MIN_VALUE;

            // Loop through each possible move, check what value that move has (via minimax), save the score
            for (int i = 0; i < moveOptions.length; i++) {
                moveScores[i] = minimax(game, moveOptions[i]);
            }

            // Check what scores have been given
//            for (int i = 0; i < moveOptions.length; i++) {
//                System.out.printf("Move: %d %d score: %d\n", moveOptions[i].x, moveOptions[i].y, moveScores[i]);
//            }

            // Find the highest move score
            for (int i = 0; i < moveOptions.length; i++) {
                if (moveScores[i] > int_best_move) {
                    int_best_move = moveScores[i];
                    bestMove.x = moveOptions[i].x;
                    bestMove.y = moveOptions[i].y;
                }
            }
        }

        // if playerO is playing, we want to minimize the score (start at high value, update when lower found)
        else {
            int int_best_move = Integer.MAX_VALUE;

            // Loop through each possible move, check what value that move has (via minimax), save the score
            for (int i = 0; i < moveOptions.length; i++) {
                moveScores[i] = minimax(game, moveOptions[i]);
            }

            // Check what scores have been given
//            for (int i = 0; i < moveOptions.length; i++) {
//                System.out.printf("Move: %d %d score: %d\n", moveOptions[i].x, moveOptions[i].y, moveScores[i]);
//            }

            // Find the lowest move score
            for (int i = 0; i < moveOptions.length; i++) {
                if (moveScores[i] < int_best_move) {
                    int_best_move = moveScores[i];
                    bestMove.x = moveOptions[i].x;
                    bestMove.y = moveOptions[i].y;
                }
            }
        }
        addMove(game, bestMove.x, bestMove.y);
        // System.out.printf("Making move level \"%s\": %d %d\n", this.name, bestMove.x, bestMove.y);
    }

    private int minimax(TicTacToe game, Move move) {
        // Create new game (to prevent overwriting the original game), make the move, then evaluate the game
        String[] board_state = game.exportCurrentGameBoard();
        TicTacToe gameWithNextMove = new TicTacToe();
        gameWithNextMove.readSetup(board_state);
        gameWithNextMove.determineTurn();
        gameWithNextMove.makeAMove(move);

        int value_of_game = valueOfGame(gameWithNextMove);
        // System.out.printf("Considering move: %d %d with value %d\n", move.x, move.y, value_of_game);

        if (value_of_game == 1) {
            // System.out.printf("Move with coordinate { %d, %d } leads to X winning\n", move.x, move.y);
            return 1;
        } else if (value_of_game == -1) {
            // System.out.printf("Move with coordinate { %d, %d } leads to O winning\n", move.x, move.y);
            return -1;
        } else if (value_of_game == 0) {
            // System.out.printf("Move with coordinate { %d, %d } leads to a draw\n", move.x, move.y);
            return 0;
        } else {
            // Get all possible next moves, create array for score tracking, create bestMove
            Move[] possibleMoves = gameWithNextMove.getPossibleMoves();
            int[] possibleMoveScores = new int[possibleMoves.length];
            int int_score_of_this_move;

            if (gameWithNextMove.state == GameState.PLAYER1) {
                int_score_of_this_move = Integer.MIN_VALUE;

                // Loop through each possible move, check what value that move has (via minimax), save the score
                for (int i = 0; i < possibleMoves.length; i++) {
                    possibleMoveScores[i] = minimax(gameWithNextMove, possibleMoves[i]);
                }

                // Find the highest move score
                for (int i = 0; i < possibleMoves.length; i++) {
                    if (possibleMoveScores[i] > int_score_of_this_move) {
                        int_score_of_this_move = possibleMoveScores[i];
                    }
                }
            }

            // if playerO is playing, we want to minimize the score (start at high value, update when lower found)
            else {
                int_score_of_this_move = Integer.MAX_VALUE;

                // Loop through each possible move, check what value that move has (via minimax), save the score
                for (int i = 0; i < possibleMoves.length; i++) {
                    possibleMoveScores[i] = minimax(gameWithNextMove, possibleMoves[i]);
                }

                // Find the lowest move score
                for (int i = 0; i < possibleMoves.length; i++) {
                    if (possibleMoveScores[i] < int_score_of_this_move) {
                        int_score_of_this_move = possibleMoveScores[i];
                    }
                }
            }
            return int_score_of_this_move;
        }
    }

    // Calculate the value of the game. Return -1 for playerO winning, 1 for playerX winning and 0 for a draw
    private Integer valueOfGame(TicTacToe game) {
        for (int i = 0; i < 3; i++) {
            // Check for horizontal [i][n] or vertical [n][i] equalities
            if (game.Board[i][0] == game.Board[i][1] && game.Board[i][0] == game.Board[i][2]) {
                if (game.Board[i][0] == 1) return 1;
                else if (game.Board[i][0] == 2) return -1;
            } else if (game.Board[0][i] == game.Board[1][i] && game.Board[0][i] == game.Board[2][i]) {
                if (game.Board[0][i] == 1) return 1;
                else if (game.Board[0][i] == 2) return -1;
            }
        }

        // There are 2 diagonals. Easiest is if I just hardcode them
        if (game.Board[0][0] == game.Board[1][1] && game.Board[0][0] == game.Board[2][2]) {
            if (game.Board[0][0] == 1) return 1;
            else if (game.Board[0][0] == 2) return -1;
        } else if (game.Board[2][0] == game.Board[1][1] && game.Board[2][0] == game.Board[0][2]) {
            if (game.Board[2][0] == 1) return 1;
            else if (game.Board[2][0] == 2) return -1;
        }

        // When there is no winner yet AND there are no empty spaces left, it is a draw
        if (game.getNumberOfEmptySpaces() == 0) {
            return 0;
        }

        return 2;
    }
}
