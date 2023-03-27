# Battleship
This project will feature a battleship-type game where you can place your ships on a 10x10 grid and then try to shoot the opponent's ships. The ships can be placed horizontally or vertically, but not diagonally. Also ships are not allowed to cross or touch each other.

---

## Stage 1
### Objectives

In this stage, you should arrange your ships on the game field. Before you start, let's discuss the conventions of the game:

* On a 10x10 field, the first row should contain numbers from 1 to 10 indicating the column, and the first column should contain letters from A to J indicating the row.
* The symbol `~` denotes the fog of war: the unknown area on the opponent's field and the yet untouched area on your field.
* The symbol `O` denotes a cell with your ship, `X` denotes that the ship was hit, and `M` signifies a miss.
* You have 5 ships: Aircraft Carrier is 5 cells, Battleship is 4 cells, Submarine is 3 cells, Cruiser is also 3 cells, and Destroyer is 2 cells. Start placing your ships with the largest one.
* To place a ship, enter two coordinates: the beginning and the end of the ship.
* If an error occurs in the input coordinates, your program should report it. The message should contain the word `Error`.

---

## Stage 2
### Objectives

Take a shot at a prepared game field. You need to indicate the coordinates of the target, and the program should then display a message about a hit or a miss. If the shell misses the target and falls in the water, this cell should be marked with an `M`, and a successful strike is marked by an `X`. After this shot, the game should be stopped.

If the player managed to hit a ship, the game should display a message `You hit a ship!`; otherwise, the message is `You missed!`

---
