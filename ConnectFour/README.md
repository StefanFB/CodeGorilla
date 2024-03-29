# ConnectFour
Tired of playing Tic-Tac-Toe? Want something a little more challenging? Connect Four is a great game that fuses the complexity of a larger game board and the need to line up more pieces. In this project, you will create a version of the game with a graphical user interface that you can run on your computer.

## Learning Outcomes
Learn about multidimensional arrays, a variety of loops, and how to utilize Java Swing library to create a user interface. Write a complicated algorithm to check the game board for a winning condition after each turn and highlight the winning move.

---

## [Stage 1](https://hyperskill.org/projects/226/stages/1132/implement)
### Objectives

Your program should display an empty game board as described below.

* The main `ConnectFour` class should extend the `JFrame` class, set its title as Connect Four, and exit on close;
* The cells should extend the `JButton` class. Hint: Set the painted focus to `false` to remove the highlighting on the clicked cells;
* Create 6 rows and 7 columns on the board;
* Each cell should be visible and display the row and column as its label. For example, `A1`, `G6`;
* Each cell should be named `Button**` where `**` is the row and column designation in the label;
* Organize rows and columns as shown in the example below.

---

## [Stage 2](https://hyperskill.org/projects/226/stages/1133/implement)
### Objectives

In this stage, your program should:

* Display an empty game board, where each cell displays a single space "` `" as its label;
* When a player clicks on a cell, change that cell's label to either `X` or `O` depending on the turn, starting with `X`.

---

## [Stage 3](https://hyperskill.org/projects/226/stages/1134/implement)
### Objectives

In this stage your program should:

* Fill the cells according to the rule above — not by the position of the click but by the column that was clicked on;
* Continue to alternate between the `X` and `O` pieces after each click.

---

## [Stage 4](https://hyperskill.org/projects/226/stages/1135/implement)
### Objectives

In this stage, implement the following features:

* Add the baseline color for all cells at the start of the application;
* Add a reset button that extends the `JButton` named `ButtonReset`. The button should be enabled. Clicking on it should clear all cells and return them to their baseline color;
* Check if either player has connected four in a row vertically, horizontally, or diagonally after each move;
* Once a winner has been detected, change the color of the four winning cells to the winning color. The baseline color and the winning color should be two different colors;
* A click on any board cell in any filled column must lead to nothing. The gameplay continues with the same player's turn;
* A click on any board cell once a game is finished must lead to nothing.
