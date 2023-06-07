# Simple Chatty Bot (Java)
## Learning outcomes
Get to know the basic syntax of Java and write a simple program using variables, conditions, loops, and methods.

## About
Here, at the beginning of your programmer’s path, creating a simple console chat bot will do wonders to guide you through the basics of coding. During this journey, you will also play some word and number games that you are going to implement on your own. Pack up and let’s hit the road, my friend!

---

## [Stage 1](https://hyperskill.org/projects/113/stages/614/implement)
### Objective

For the first stage, you will write a bot who displays a greeting, its name, and the date of its creation. First impressions count!

Your program should print the following lines:

```
Hello! My name is {botName}.
I was created in {birthYear}.
```

Instead of `{botName}`, print any name you choose and replace `{birthYear}` with the current year `(four digits)`.

---

## [Stage 2](https://hyperskill.org/projects/113/stages/615/implement)
### Objective

In this stage, you will introduce yourself to the bot so that it can greet you by your name.

Your program should print the following lines:

```
Hello! My name is Aid.
I was created in 2020.
Please, remind me your name.
What a great name you have, {yourName}!
```

You may change the name and the creation year of your bot if you want.
Instead of `{yourName}` , the bot must print your name entered from the standard input.

---

## [Stage 3](https://hyperskill.org/projects/113/stages/616/implement)
### Objective

In this stage, you will introduce yourself to the bot. It will greet you by your name and then try to guess your age using arithmetic operations.

Your program should print the following lines:

```
Hello! My name is Aid.
I was created in 2020.
Please, remind me your name.
What a great name you have, Max!
Let me guess your age.
Enter remainders of dividing your age by 3, 5 and 7.
Your age is {yourAge}; that's a good time to start programming!
```

Read three numbers from the standard input. Assume that all the numbers will be given on separate lines.

Instead of `{yourAge}`, the bot will print the age determined according to the following special formula:
`age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105`

---

## [Stage 4](https://hyperskill.org/projects/113/stages/617/implement)
### Objective

In this stage, you will program the bot to count from 0 to any positive number users enter.
Note: each number starts with a new line, and after a number, the bot should print the exclamation mark.

---

## [Stage 5](https://hyperskill.org/projects/113/stages/618/implement)
### Objective

Your bot can ask anything you want, but there are two rules for your output:

* the line with the test should end with the question mark character;
* an option starts with a digit followed by the dot (`1.`, `2.`, `3.`, `4.`)

If a user enters an incorrect answer, the bot may print a message:

`Please, try again.`

The program should stop on the correct answer and print `Congratulations, have a nice day!` at the end.
