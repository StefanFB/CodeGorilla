# Tic-Tac-Toe with AI (Java)
Everybody remembers this paper-and-pencil game from childhood: Tic-Tac-Toe, also known as Noughts and crosses or Xs and Os. A single mistake usually costs you the game, but thankfully it is simple enough that most players discover the best strategy quickly. Let’s program Tic-Tac-Toe and get playing!

## Learning Outcomes
After finishing this project, you'll get to know a lot about planning and developing a complex program from scratch, using classes and methods, handling errors, and processing user input. You will also learn to use OOP (Object-Oriented Programming) in the process.

---

## [Stage 1](https://hyperskill.org/projects/81/stages/447/implement)
### Objectives

The program should work in the following way:

1. Ask the user to provide the initial state of the 3x3 table with the first input line. This must include nine symbols that can be `X`, `O`or `_` (the latter represents an empty cell).
2. Output the specified 3x3 table before the user makes a move.
3. Request that the user enters the coordinates of the move they wish to make.
4. The user then inputs two numbers representing the cell in which they wish to place their `X` or `O`. The game always starts with `X`, so the user's move should be made with this symbol if there are an equal number of `X`'s and `O`'s in the table. If the table contains an extra `X`, the move should be made with `O`.
5. Analyze the user input and show messages in the following situations:
    * `This cell is occupied! Choose another one!` — if the cell is not empty;
    * `You should enter numbers!` — if the user tries to enter letters or symbols instead of numbers;
    * `Coordinates should be from 1 to 3!` — if the user attempts to enter coordinates outside of the table's range.
6. Display the table again with the user's most recent move included.
7. Output the state of the game.

The possible states are:

* `Game not finished` — when no side has three in a row, but the table still has empty cells;
* `Draw` — when no side has three in a row, and the table is complete;
* `X wins` — when there are three `X`'s in a row (up, down, across, or diagonally);
* `O wins` — when there are three `O`'s in a row (up, down, across, or diagonally).

If the user provides invalid coordinates, the program should repeat the request until numbers that represent an empty cell on the table are supplied. You should ensure that the program only outputs the table twice — before the move and after the user makes a legal move.

---

## [Stage 2](https://hyperskill.org/projects/81/stages/448/implement)
### Objectives
In this stage, you should implement the following:

* Display an empty table when the program starts.
* The user plays first as `X`, and the program should ask the user to enter cell coordinates.
* Next, the computer makes its move as `O`, and the players then move in turn until someone wins or the game results in a draw.
* Print the final outcome at the very end of the game.

---

## [Stage 3](https://hyperskill.org/projects/81/stages/449/implement)
### Objectives
Your tasks for this stage are:

1. Write a menu loop, which can interpret two commands: `start` and `exit`.
2. Implement the command `start`. It should take two parameters: who will play `X` and who will play `O`. Two options are possible for now: `user` to play as a human, and `easy` to play as an AI.
3. The `exit` command should simply end the program.
4. In later steps, you will add the `medium` and `hard` levels.

Don't forget to handle incorrect input! The message `Bad parameters!` should be displayed if what the user enters is invalid.

---

## [Stage 4](https://hyperskill.org/projects/81/stages/450/implement)
### Objectives

When the AI is playing at `medium` difficulty level, it makes moves using the following logic:

* If it already has two in a row and can win with one further move, it does so.
* If its opponent can win with one move, it plays the move necessary to block this.
* Otherwise, it makes a random move.

You should add a `medium` parameter so that you can play against this level. It should also be possible to make AIs using `easy` and `medium` levels play against each other!

---

## [Stage 5](https://hyperskill.org/projects/81/stages/451/implement)
### Objectives
In this last stage, you need to implement the `hard` difficulty level using the minimax algorithm. As a recursive algorithm, it can be tricky to think about, so you should try to look at different resources to find the one that works best for your understanding (which is an important skill to get good at as you progress in you software development career!). Consider the following as starting points:

* Video-based explanations:
   * ["Tic Tac Toe AI with Minimax Algorithm"](https://www.youtube.com/watch?v=trKjYdBASyQ) by The Coding Train
   * ["Algorithms Explained – minimax and alpha-beta pruning"](https://www.youtube.com/watch?v=l-hh51ncgDI) by Sebastian Lague
   * ["Mega-R3. Games, Minimax, Alpha-Beta"](https://www.youtube.com/watch?v=hM2EAvMkhtk) from MIT OpenCourseWare
* Text-based explanations:
   * ["How to make your Tic Tac Toe game unbeatable by using the minimax algorithm"](https://www.freecodecamp.org/news/how-to-make-your-tic-tac-toe-game-unbeatable-by-using-the-minimax-algorithm-9d690bad4b37/) on freeCodeCamp.org
   * ["Case Study on Tic-Tac-Toe Part 2: With AI"](https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html) from Nanyang Technological University
   * ["Tic Tac Toe - Creating Unbeatable AI"](https://gsurma.medium.com/tic-tac-toe-creating-unbeatable-ai-with-minimax-algorithm-8af9e52c1e7d#:~:text=Minimax%20is%20a%20recursive%20algorithm,minimize%20the%20worst%20case%20scenario\).) on Medium

You should also add a `hard` parameter so that it's possible to play against this level.
