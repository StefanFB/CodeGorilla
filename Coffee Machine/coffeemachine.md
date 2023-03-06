# Coffee Machine

This will be the directory of the Coffee Machine project!

---

## Stage 1
### Objective

The first version of the program just makes you a coffee. It should print to the standard output what it is doing as it makes the drink.

Sample output:
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

## Stage 2
### Objectives

1. First, read the numbers of coffee drinks from the input.
2. Figure out how much of each ingredient the machine will need. Note that one cup of coffee made on this coffee machine contains 200 ml of water, 50 ml of milk, and 15 g of coffee beans.
3. Output the required ingredient amounts back to the user.

Sample output:
```
Write how many cups of coffee you will need:
> 25
For 25 cups of coffee you will need:
5000 ml of water
1250 ml of milk
375 g of coffee beans
```

---

## Stage 3
### Objectives

Write a program that does the following:

1. It requests the amounts of water, milk, and coffee beans available at the moment, and then asks for the number of cups a user needs.
2. If the coffee machine has enough supplies to make the specified amount of coffee, the program should print `"Yes, I can make that amount of coffee"`.
3. If the coffee machine can make more than that, the program should output `"Yes, I can make that amount of coffee (and even N more than that)"`, where N is the number of additional cups of coffee that the coffee machine can make.
4. If the amount of given resources is not enough to make the specified amount of coffee, the program should output `"No, I can make only N cups of coffee"`.

Sample output:
```
Write how many ml of water the coffee machine has:
> 1550
Write how many ml of milk the coffee machine has:
> 299
Write how many grams of coffee beans the coffee machine has:
> 300
Write how many cups of coffee you will need:
> 3
Yes, I can make that amount of coffee (and even 2 more than that)
```

---

## Stage 4
### Objectives
Write a program that offers to buy one cup of coffee or to fill the supplies or to take its money out. Note that the program is supposed to do one of the mentioned actions at a time. It should also calculate the amounts of remaining ingredients and how much money is left. Display the quantity of supplies before and after purchase.

1. First, your program reads one option from the standard input, which can be "buy", "fill", "take". If a user wants to buy some coffee, the input is "buy". If a special worker thinks that it is time to fill out all the supplies for the coffee machine, the input line will be "fill". If another special worker decides that it is time to take out the money from the coffee machine, you'll get the input "take".
2. If the user writes "buy" then they must choose one of three types of coffee that the coffee machine can make: espresso, latte, or cappuccino.
    * For one espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
    * For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
    * And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
3. If the user writes "fill", the program should ask them how much water, milk, coffee and how many disposable cups they want to add into the coffee machine.
4. If the user writes "take" the program should give all the money that it earned from selling coffee.

At the moment, the coffee machine has $550, 400 ml of water, 540 ml of milk, 120 g of coffee beans, and 9 disposable cups.

Sample output:
```
The coffee machine has:
400 ml of water
540 ml of milk
120 g of coffee beans
9 disposable cups
$550 of money

Write action (buy, fill, take): 
> buy
What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: 
> 3

The coffee machine has:
200 ml of water
440 ml of milk
108 g of coffee beans
8 disposable cups
$556 of money
```
