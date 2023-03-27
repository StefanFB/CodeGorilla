package battleship;

import java.util.Scanner;

public class Game {
    int xAxis = 10; // amount of columns
    int yAxis = 10; // amount of rows
    char[][] field2D = new char[yAxis+1][xAxis+1];
    GameState state;
    Preparation prepPhase;

    public Game() {
        this.state = GameState.PREPARATION;
        this.prepPhase = Preparation.PLACE_SHIP_1;
    }

    public void handlePlacement() {

        // Read and process input given by the user
        // System.out.printf("Phase: %s\n", this.prepPhase);
        System.out.printf("Enter the coordinates of the %s (%d cells):\n\n", getShipName(), getShipSize());
        ShipCoordinates ship = readPlacement();

        // Check given coordinates, retry when incorrect
        int correctInput = isCorrectInput(ship);
        while (correctInput != 0) {
            switch (correctInput) {
                case 1 -> System.out.println("Error! Invalid coordinates. Try again:\n\n");
                case 2 -> System.out.println("Error! Cannot place diagonally. Try again:\n\n");
                case 3 -> System.out.println("Error! Incorrect length. Try again:\n\n");
                case 4 -> System.out.println("Error! Please provide a correct start and end coordinate. Try again.\n\n");
                case 5 -> System.out.println("Error! Cannot place ship too close to another ship. Try again.\n\n");
            }
            ship = readPlacement();
            correctInput = isCorrectInput(ship);
        }

        // Update gameboard to reflect the current situation
        placeShip(ship);

        // When all is correct, update prepPhase
        switch (prepPhase) {
            case PLACE_SHIP_1 -> this.prepPhase = Preparation.PLACE_SHIP_2;
            case PLACE_SHIP_2 -> this.prepPhase = Preparation.PLACE_SHIP_3;
            case PLACE_SHIP_3 -> this.prepPhase = Preparation.PLACE_SHIP_4;
            case PLACE_SHIP_4 -> this.prepPhase = Preparation.PLACE_SHIP_5;
            case PLACE_SHIP_5 -> this.prepPhase = Preparation.PREP_DONE;
        }
    }

    public void showFullMap() {
        for (int i = 0; i <= yAxis; i++) {
            for (int j = 0; j <= xAxis; j++) {
                // Top-left corner should be empty
                if (i == 0 && j == 0) {
                    System.out.print("\n  ");
                }
                // First row should be the column numbers
                else if (i == 0) {
                    System.out.printf("%d ", j);
                }
                // First column should be the row letters
                else if (j == 0) {
                    System.out.printf("%c ", (char) i + 64);
                }
                // If a coordinate has been shot and missed ('M'), print M
                else if (field2D[i][j] == 'M') {
                    System.out.print("M ");
                }
                // If a coordinate has been shot and hit a ship ('X'), print X
                else if (field2D[i][j] == 'X') {
                    System.out.print("X ");
                }
                // If a coordinate has a ship ('O'), print O
                else if (field2D[i][j] == 'O') {
                    System.out.print("O ");
                }
                // All other coordinates should be sea, so print a wave ~
                else {
                    System.out.print("~ ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void showMap() {
        for (int i = 0; i <= yAxis; i++) {
            for (int j = 0; j <= xAxis; j++) {
                // Top-left corner should be empty
                if (i == 0 && j == 0) {
                    System.out.print("\n  ");
                }
                // First row should be the column numbers
                else if (i == 0) {
                    System.out.printf("%d ", j);
                }
                // First column should be the row letters
                else if (j == 0) {
                    System.out.printf("%c ", (char) i + 64);
                }
                // If a coordinate has been shot and missed ('M'), print M
                else if (field2D[i][j] == 'M') {
                    System.out.print("M ");
                }
                // If a coordinate has been shot and hit a ship ('X'), print X
                else if (field2D[i][j] == 'X') {
                    System.out.print("X ");
                }
                // All other coordinates should be sea, so print a wave ~
                else {
                    System.out.print("~ ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void handleFiring() {
        // Ask user to take a shot
        System.out.println("Take a shot!\n");
        ShotCoordinates shot = readShot();

        // Check given coordinate, retry when incorrect
        int correctShot = isCorrectInput(shot);
        while (correctShot != 0) {
            switch (correctShot) {
                case 1 -> System.out.println("\nError! Invalid input. Try again:\n");
                case 2 -> System.out.println("\nError! Invalid coordinates. Try again:\n");
            }
            shot = readShot();
            correctShot = isCorrectInput(shot);
        }

        // When correct, shoot! Notice whether it was a hit or not
        boolean wasHit = fire(shot);
        showMap();
        if (!wasHit) {
            System.out.println("You missed!");
        } else {
            System.out.println("You hit a ship!");
        }

    }

    private ShipCoordinates readPlacement() {

        /* Read a line of input
         * Split into two strings using the whitespace as separator
         * Parse a char from the first character, an integer from the rest
         * Returning ShipCoordinates(1,1,1,1) is a special error message to the checker */
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        int row1, row2, col1, col2 = 1;

        try {
            // Split user input into two coordinates
            String[] coordinates = userInput.split(" ");

            // Convert first coordinate to integer
            row1 = coordinates[0].charAt(0);
            col1 = Integer.parseInt(coordinates[0].substring(1));

            // Convert second coordinate to integer
            row2 = coordinates[1].charAt(0);
            col2 = Integer.parseInt(coordinates[1].substring(1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return new ShipCoordinates(1, 1, 1, 1);
        }
        return new ShipCoordinates(row1, col1, row2, col2);
    }

    private ShotCoordinates readShot() {
        /* Read a line of input
         * Parse a char from the first character, an integer from the rest
         * Returning ShotCoordinates(-1, -1) is a special error message to the checker */
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        int x, y;

        try {
            // Convert coordinate to integer
            y = userInput.charAt(0);
            x = Integer.parseInt(userInput.substring(1));
        } catch (NumberFormatException e) {
            return new ShotCoordinates(-1, -1);
        }
        return new ShotCoordinates(y, x);
    }

    private int isCorrectInput(ShipCoordinates coords) {
        // 'Capitalize' the letters
        if (coords.y1 > 96 && coords.y1 < 123) {
            coords.y1 -= 32;
        }
        if (coords.y2 > 96 && coords.y2 < 123) {
            coords.y2 -= 32;
        }

        // Check for specific errorcode in the coordinates
        if (coords.y1 == 1 && coords.x1 == 1) {
            return 4; // error for wrong length of input
        }

        // Check input for letters and numbers existing in our game field
        if ( (coords.y1 < 65 || coords.y1 > (65 + yAxis) )
                || (coords.x1 <= 0 || coords.x1 > (xAxis))
                || (coords.y2 < 65 || coords.y2 > (65 + yAxis))
                || (coords.x2 <= 0 || coords.x2 > (xAxis)) ) {
            return 1; // error for non-existing coordinates
        }

        // Check input is not diagonal
        if (coords.x1 != coords.x2 && coords.y1 != coords.y2) {
            return 2; // error for diagonal placement
        }

        // Check for correct length
        int len = getShipSize();
        int givenLen = 0;
        if (coords.x1 == coords.x2) {
            givenLen = coords.y1 - coords.y2;
            givenLen = (givenLen < 0) ? -givenLen : givenLen; // make givenLen positive
            givenLen++; // because it needs to be inclusive
            if (givenLen != len) {
                // System.out.printf("Current ship size: %d, while you gave %d\n", len, givenLen);
                return 3; // error for incorrect length
            }
        } else if (coords.y1 == coords.y2) {
            givenLen = coords.x1 - coords.x2;
            givenLen = (givenLen < 0) ? -givenLen : givenLen; // make givenLen positive
            givenLen++; // because it needs to be inclusive
            if (givenLen != len) {
                // System.out.printf("Current ship size: %d, while you gave %d\n", len, givenLen);
                return 3; // error for incorrect length
            }
        }
        // System.out.printf("Current ship size: %d, and you gave %d\n\n", len, givenLen);

        // Make new variables that can be used directly without UTF conversion. 2 is always larger than 1
        int x1 = coords.x1;
        int y1 = coords.y1 - 64;
        int x2 = coords.x2;
        int y2 = coords.y2 - 64;

        // Make sure x2 is larger than x1
        if (x1 > x2) {
            int tempX = x1;
            x1 = x2;
            x2 = tempX;
        }

        // make sure y2 is larger than y1
        if (y1 > y2) {
            int tempY = y1;
            y1 = y2;
            y2 = tempY;
        }

        // Last and final check: you cannot place two ships next to each other
        if (y1 == y2) {
            for (int i = y1 - 1; i <= y1 + 1; i++) {
                for (int j = x1 - 1; j <= x2 + 1; j++) {
                    if (i <= 10 && i > 0) {
                        if (j <= 10 && j > 0) {
                            if (field2D[i][j] == 'O') {
                                return 5; // error for too close to another ship (including on top of)
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = y1 - 1; i <= y2 + 1; i++) {
                for (int j = x1 - 1; j <= x1 + 1; j++) {
                    if (i <= 10 && i > 0) {
                        if (j <= 10 && j > 0) {
                            if (field2D[i][j] == 'O') {
                                return 5; // error for too close to another ship (including on top of)
                            }
                        }
                    }
                }
            }
        }

        // When all checks have been done, it is safe to be placed!
        return 0;
    }

    private int isCorrectInput(ShotCoordinates coords) {
        // 'Capitalize' the letters
        if (coords.y > 96 && coords.y < 123) {
            coords.y -= 32;
        }

        // Check for specific errorcode in the coordinates
        if (coords.y == -1 && coords.x == -1) {
            return 1; // error for wrong input
        }

        // Check input for letters and numbers existing in our game field
        if ( (coords.y < 65 || coords.y > (64 + yAxis) )
                || (coords.x <= 0 || coords.x > (xAxis)) ) {
            return 2; // error for non-existing coordinates
        }

        // When all checks have been done, the coordinate is safe to be shot at!
        return 0;
    }

    private String getShipName() {
        String shipName = "";
        switch (this.prepPhase) {
            case PLACE_SHIP_1 -> shipName = Ship.carrier.name;
            case PLACE_SHIP_2 -> shipName = Ship.battleship.name;
            case PLACE_SHIP_3 -> shipName = Ship.submarine.name;
            case PLACE_SHIP_4 -> shipName = Ship.cruiser.name;
            case PLACE_SHIP_5 -> shipName = Ship.destroyer.name;
        }
        return shipName;
    }

    private int getShipSize() {
        int shipSize = 0;
        switch (this.prepPhase) {
            case PLACE_SHIP_1 -> shipSize = Ship.carrier.length;
            case PLACE_SHIP_2 -> shipSize = Ship.battleship.length;
            case PLACE_SHIP_3 -> shipSize = Ship.submarine.length;
            case PLACE_SHIP_4 -> shipSize = Ship.cruiser.length;
            case PLACE_SHIP_5 -> shipSize = Ship.destroyer.length;
        }
        return shipSize;
    }

    private void placeShip(ShipCoordinates ship) {
        // Convert to coordinates
        int y1 = ship.y1 - 64;
        int y2 = ship.y2 - 64;
        int x1 = ship.x1;
        int x2 = ship.x2;
        // System.out.printf("1x,y = %d, %d || 2x,y = %d, %d\n", x1, y1, x2, y2);

        /*
         If placed horizontally, the y-component will be the same
         Then place ship along the x-axis
        */
        if (y1 == y2) {
            // Make sure x2 is always bigger than x1
            if (x1 > x2) {
                x1 = x2;
            }
            for (int i = 0; i < getShipSize(); i++) {
                field2D[y1][x1] = 'O';
                x1++;
            }
        } else if (x1 == x2) {
            // Make sure x2 is always bigger than x1
            if (y1 > y2) y1 = y2;
            for (int i = 0; i < getShipSize(); i++) {
                field2D[y1][x1] = 'O';
                y1++;
            }
        }
    }

    private boolean fire(ShotCoordinates shot) {
        // Convert to 'real' coordinates
        int x = shot.x;
        int y = shot.y - 64;

        // Check for a ship on this location, update game board and return true for hit, false for miss
        if (field2D[y][x] == 'O') {
            field2D[y][x] = 'X';
            return true;
        } else {
            field2D[y][x] = 'M';
            return false;
        }
    }
}
