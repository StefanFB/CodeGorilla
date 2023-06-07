# Coffee Machine (Java)
## Learning outcomes
This project allows you to better understand the basic OOP, its main concepts such as classes, class methods and attributes, and get a taste of Java. Practice working with methods, challenge yourself with loops and conditions, and get more confident with OOP.

## About
What can be better than a cup of coffee during a break? A coffee that you don’t have to make yourself. It’s enough to press a couple of buttons on the machine and you get a cup of energy; but first, we should teach the machine how to do it. In this project, you will work on programming a coffee machine simulator. The machine works with typical products: coffee, milk, sugar, and plastic cups; if it runs out of something, it shows a notification. You can get three types of coffee: espresso, cappuccino and latte. Since nothing’s for free, it also collects the money.

---

## [Stage 1](https://hyperskill.org/projects/33/stages/175/implement)
### Objective

The first version of the program just makes you a coffee. It should print to the standard output what it is doing as it makes the drink.

Output:
```
Starting to make a coffee
Grinding coffee beans
Boiling water
Mixing boiled water with crushed coffee beans
Pouring coffee into the cup
Pouring some milk into the cup
Coffee is ready!
```

---

## [Stage 2](https://hyperskill.org/projects/33/stages/176/implement)
### Objectives
So, we will ask a user to enter the desired amount of coffee, in cups. Given this, you can adjust the program by calculating how much water, coffee, and milk are necessary to make the specified amount of coffee.

1. First, read the numbers of coffee drinks from the input.
2. Figure out how much of each ingredient the machine will need. Note that one cup of coffee made on this coffee machine contains 200 ml of water, 50 ml of milk, and 15 g of coffee beans.
3. Output the required ingredient amounts back to the user.

---

## [Stage 3](https://hyperskill.org/projects/33/stages/177/implement)
### Objectives

Write a program that does the following:

* It requests the amounts of water, milk, and coffee beans available at the moment, and then asks for the number of cups a user needs.
* If the coffee machine has enough supplies to make the specified amount of coffee, the program should print `"Yes, I can make that amount of coffee"`.
* If the coffee machine can make more than that, the program should output `"Yes, I can make that amount of coffee (and even N more than that)"`, where *N* is the number of additional cups of coffee that the coffee machine can make.
* If the amount of given resources is not enough to make the specified amount of coffee, the program should output `"No, I can make only N cup(s) of coffee"`.

Like in the previous stage, the coffee machine needs 200 ml of water, 50 ml of milk, and 15 g of coffee beans to make one cup of coffee.

---

## [Stage 4](https://hyperskill.org/projects/33/stages/178/implement)
### Objectives
Write a program that offers to buy one cup of coffee or to fill the supplies or to take its money out. Note that the program is supposed to do one of the mentioned actions at a time. It should also calculate the amounts of remaining ingredients and how much money is left. Display the quantity of supplies before and after purchase.

1. First, your program reads one option from the standard input, which can be `"buy"`, `"fill"`, `"take"`. If a user wants to buy some coffee, the input is `"buy"`. If a special worker thinks that it is time to fill out all the supplies for the coffee machine, the input line will be `"fill"`. If another special worker decides that it is time to take out the money from the coffee machine, you'll get the input `"take"`.
2. If the user writes `"buy"` then they must choose one of three types of coffee that the coffee machine can make: espresso, latte, or cappuccino.
    * For one espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
    * For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
    * And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
3. If the user writes `"fill"`, the program should ask them how much water, milk, coffee and how many disposable cups they want to add into the coffee machine.
4. If the user writes `"take"` the program should give all the money that it earned from selling coffee.
At the moment, the coffee machine has $550, 400 ml of water, 540 ml of milk, 120 g of coffee beans, and 9 disposable cups.

To sum up, your program should print the coffee machine's state, process one query from the user, as well as print the coffee machine's state after that. Try to use functions for implementing every action that the coffee machine can do.

---

## [Stage 5](https://hyperskill.org/projects/33/stages/179/implement)
### Objectives
Write a program that will work endlessly to make coffee for all interested persons until the shutdown signal is given. Introduce two new options:  `"remaining"` and `"exit"`.

Do not forget that you can be out of resources for making coffee. If the coffee machine doesn't have enough resources to make coffee, the program should output a message that says it can't make a cup of coffee and state what is missing.

And the last improvement to the program at this step — if the user types `"buy"` to buy a cup of coffee and then changes his mind, they should be able to type `"back"` to return into the main cycle.

---

## [Stage 6](https://hyperskill.org/projects/33/stages/180/implement)
### Objective

Your final task is to refactor the program. Make it so that you can communicate with the coffee machine through a single method.

---
