package tictactoe;

public class TicTacToe {
    int[][] Board = new int[3][3];
    GameState state = GameState.SETUP;
    Player playerX;
    Player playerO;

    public TicTacToe() {
        this.Board = new int[3][3];
        GameState state = GameState.SETUP;
        Player playerX;
        Player playerO;

        this.boardSetup();
    }

    public TicTacToe(String board_setup) {
        this.Board = new int[3][3];
        GameState state = GameState.SETUP;
        Player playerX;
        Player playerO;

        this.readSetup(board_setup.split(""));
        this.showBoard();
    }

    public int checkCommands(String[] input) {
        // Create strings to store the input in
        String command = "";
        String player1 = "";
        String player2 = "";

        // Try to read the first word inputted
        try {
            command = input[0];
        } catch (Exception e) {
            System.out.println("Bad parameters!");
            return 1;
        }

        // If the first word is "exit", stop reading and return to main
        if (command.equals("exit")) {
            return 0;
        }

        // Try to read the next two words
        try {
            player1 = input[1];
            player2 = input[2];
        } catch (Exception e) {
            System.out.println("Bad parameters!");
            return 5;
        }

        // Check for correct command usage via String-array
        String[] possibleUsers = new String[] {"user", "easy", "medium", "hard"};
        boolean wrongInput1 = true;
        boolean wrongInput2 = true;

        // Using for-loop check if the commands are used properly
        for (String s : possibleUsers) {
            if (player1.equals(s)) wrongInput1 = false;
            if (player2.equals(s)) wrongInput2 = false;
        }

        if (!command.equals("start")) {
            System.out.println("Bad parameters!");
            return 2;
        } else if (wrongInput1) {
            System.out.println("Bad parameters!");
            return 3;
        } else if (wrongInput2) {
            System.out.println("Bad parameters!");
            return 4;
        } else {
            return 0;
        }
    }

    // Set up empty board
    public void boardSetup() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    Board[i][j] = 0;
            }
        }
        // Start the game with player1
        state = GameState.PLAYER1;
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

        // Print last line with 9 dashes
        System.out.println("---------");
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
        if (state != GameState.X_WINS && state != GameState.O_WINS) {
            int emptySpaces = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Board[i][j] == 0) {
                        emptySpaces++;
                    }
                }
            }

            if (emptySpaces == 0) state = GameState.DRAW;
        }

        // Display GameState only if the game is done
        if (state != GameState.PLAYER1 && state != GameState.PLAYER2) {
            displayGameState();
        }
    }

    private void displayGameState() {
        switch (state) {
            case PLAYER1, PLAYER2 -> System.out.println("Game not finished");
            case DRAW -> System.out.println("Draw");
            case X_WINS -> System.out.println("X wins");
            case O_WINS -> System.out.println("O wins");
        }
    }

    public int getNumberOfEmptySpaces() {
        int emptySpaces = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Board[i][j] == 0) emptySpaces++;
            }
        }
        return emptySpaces;
    }

    public Move[] getPossibleMoves() {
        // Create Move-array with correct size
        int size = getNumberOfEmptySpaces();
        Move[] moves = new Move[size];
        int index = 0;

        // Fill the array with all empty places
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Board[i][j] == 0) {
                    Move move = new Move(i, j);
                    moves[index] = move;
                    index++;
                }
            }
        }

        return moves;
    }

    public void makeAMove(Move move) {
        switch (state) {
            case PLAYER1 -> {
                Board[move.x][move.y] = 1;
                state = GameState.PLAYER2;
            }
            case PLAYER2 -> {
                Board[move.x][move.y] = 2;
                state = GameState.PLAYER1;
            }
        }
    }

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

    public void determineTurn() {
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

    public String[] exportCurrentGameBoard() {
        StringBuilder SB_board_status = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (Board[i][j]) {
                    case 0 -> SB_board_status.append("_");
                    case 1 -> SB_board_status.append("X");
                    case 2 -> SB_board_status.append("O");
                }
            }
        }
        String str_board_status = SB_board_status.toString();
        return str_board_status.split("");
    }
}
