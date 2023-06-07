# Cinema Room Manager
## Learning outcomes
This project is a good opportunity to work with input and output in Java. You will deal with arrays and functions as well as loops and conditional statements.

## About
Cinema has been a part of the entertainment industry for a long time: a good movie is a way to escape reality and live through a variety of emotions. The best cinema experience you can get is probably in a cinema theatre. In this project, you will create an application that helps manage a cinema theatre: sell tickets, check available seats, see sales statistics, and more.

---

## [Stage 1](https://hyperskill.org/projects/133/stages/709/implement)
### Objectives
There is not a lot of space in our new cinema theatre, so you need to take into account the restrictions on the number of seats. Your friends said that the room would fit 7 rows of 8 seats. Your task is to help them visualize the seating arrangement by printing the scheme to the console.

Your output should be like in the example below and should contain 9 lines.

```
Cinema:
  1 2 3 4 5 6 7 8
1 S S S S S S S S
2 S S S S S S S S
3 S S S S S S S S
4 S S S S S S S S
5 S S S S S S S S
6 S S S S S S S S
7 S S S S S S S S
```

---

## [Stage 2](https://hyperskill.org/projects/133/stages/710/implement)
### In this stage, you need to read two positive integer numbers from the input: the number of rows and the number of seats in each row. The ticket price is determined by the following rules:

* If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
* In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half. Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows, and the second half is the other 5 rows.

Calculate the profit from the sold tickets depending on the number of seats and print the result as shown in the examples below. After that, your program should stop. Note that in this project, the number of rows and seats won't be greater than 9.

```
Enter the number of rows:
> 4
Enter the number of seats in each row:
> 5
Total income:
$200
```

---

## [Stage 3](https://hyperskill.org/projects/133/stages/711/implement)
### Objectives

Read two positive integer numbers that represent the number of rows and seats in each row and print the seating arrangement like in the first stage. Then, read two integer numbers from the input: a row number and a seat number in that row. These numbers represent the coordinates of the seat according to which the program should print the ticket price. The ticket price is determined by the same rules as the previous stage:

* If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
* In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half. Please note that the number of rows can be odd, for example, 9 rows. In this case, the first half is the first 4 rows, and the second half is the last 5 rows.

After that, the program should print out all the seats in the screen room as shown in the example and mark the chosen seat by the B symbol. Finally, it should print the ticket price and stop. Note that in this project, the number of rows and seats won't be greater than 9.

---

## [Stage 4](https://hyperskill.org/projects/133/stages/712/implement)
### Objectives

At the start, your program should read two positive integer numbers that represent the number of rows and seats in each row. Then, it should print a menu with the following three items:

* `Show the seats` should print the current seating arrangement. The empty seats should be marked with an `S` symbol, and taken seats are marked with a `B` symbol.
* `Buy a ticket` should read the seat coordinates from the input and print the ticket price like in the previous stage. After that, the chosen seat should be marked with a `B` when the item `Show the seats` is called.
* `Exit` should stop the program.

---

## [Stage 5](https://hyperskill.org/projects/133/stages/713/implement)
### Objectives

Now your menu should look like this:

```
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
```

When the item `Statistics` is chosen, your program should print the following information:

* The number of purchased tickets;
* The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
* Current income;
* The total income that shows how much money the theatre will get if all the tickets are sold.

The rest of the menu items should work the same way as before, except the item `Buy a ticket` shouldn't allow a user to buy a ticket that has already been purchased.

If a user chooses an already taken seat, print `That ticket has already been purchased!` and ask them to enter different seat coordinates until they pick an available seat. Of course, you shouldn't allow coordinates that are out of bounds. If this happens, print `Wrong input!` and ask to enter different seat coordinates until the user picks an available seat.
