package tictactoe;

public class TicTacToe {
    int[][] Board = new int[3][3];
    GameState state = GameState.SETUP;

    // Read board state from String[]
    public void readSetup(String[] setup) {
        int n = 0;      // index to reach into String[] setup
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (setup[n]) {
                    case "_" -> {
                        Board[i][j] = 0;
                        n++;
                    }
                    case "X" -> {
                        Board[i][j] = 1;
                        n++;
                    }
                    case "O" -> {
                        Board[i][j] = 2;
                        n++;
                    }
                }
            }
        }

        // Change GameState to Playing. Determine whose turn it is via function
        determineTurn();
    }

    // Show board in terminal
    public void showBoard() {
        // Print first line with 9 dashes
        System.out.println("---------");

        // Go over the Board matrix to display the board. Start and end each line with '|'
        for (int i = 0; i < 3; i++) {
            // Start each line with a "|"
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                switch (Board[i][j]) {
                    case 0 -> System.out.print("  ");
                    case 1 -> System.out.print("X ");
                    case 2 -> System.out.print("O ");
                }
            }
            // End each line with a "|"
            System.out.print("|\n");
        }

        // Print ending line with 9 dashes
        System.out.println("---------");
    }

    // Read users input, check for valid coordinates
    public int checkInput(String[] input) {
        int row;
        int col;

        // Handle non-integer input
        try {
            row = Integer.parseInt(input[0]);
            col = Integer.parseInt(input[1]);
        } catch (Exception e){
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
        if (Board[row][col] != 0) {
            System.out.println("This cell is occupied! Choose another one!");
            return 3;
        } else {
            doMove(row, col);
            return 0;
        }
    }

    private void doMove(int row, int col) {
        switch (state) {
            case PLAYER1 -> Board[row][col] = 1;
            case PLAYER2 -> Board[row][col] = 2;
        }
    }

    private void determineTurn() {
        int player1 = 0;
        int player2 = 0;

        // Count how many moves each player has made
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (Board[i][j]) {
                    case 1 -> player1++;
                    case 2 -> player2++;
                }
            }
        }

        // Set GameState to the correct player
        if (player1 <= player2) {
            state = GameState.PLAYER1;
        } else state = GameState.PLAYER2;
    }

    // Check for winning condition
    public void updateGameState() {
        // There are 8 winning conditions, so let's check for straight lines first
        for (int i = 0; i < 3; i++) {
            // Check for horizontal [i][n] or vertical [n][i] equalities
            if (Board[i][0] == Board[i][1] && Board[i][0] == Board[i][2]) {
                if (Board[i][0] == 1) state = GameState.X_WINS;
                else if (Board[i][0] == 2) state = GameState.O_WINS;
            } else if (Board[0][i] == Board[1][i] && Board[0][i] == Board[2][i]) {
                if (Board[0][i] == 1) state = GameState.X_WINS;
                else if (Board[0][i] == 2) state = GameState.O_WINS;
            }
        }

        // There are 2 diagonals. Easiest is if I just hardcode them
        if (Board[0][0] == Board[1][1] && Board[0][0] == Board[2][2]) {
            if (Board[0][0] == 1) state = GameState.X_WINS;
            else if (Board[0][0] == 2) state = GameState.O_WINS;
        } else if (Board[2][0] == Board[1][1] && Board[2][0] == Board[0][2]) {
            if (Board[2][0] == 1) state = GameState.X_WINS;
            else if (Board[2][0] == 2) state = GameState.O_WINS;
        }

        // If the game has not yet been won, check for empty spaces. None indicates a draw
        int emptySpaces = 0;
        if (state != GameState.X_WINS || state != GameState.O_WINS) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Board[i][j] == 0) {
                        emptySpaces++;
                    }
                }
            }
        }

        if (emptySpaces == 0) state = GameState.DRAW;

        // Display current GameState
        displayGameState();
    }

    private void displayGameState() {
        switch (state) {
            case PLAYER1, PLAYER2 -> System.out.println("Game not finished");
            case DRAW -> System.out.println("Draw");
            case X_WINS -> System.out.println("X wins");
            case O_WINS -> System.out.println("O wins");
        }
    }
  
}
