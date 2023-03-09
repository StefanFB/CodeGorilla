# Simple Chatty Bot (Java)
This is my first Java program.

---

## Stage 1
### Objective

For the first stage, you will write a bot who displays a greeting, its name, and the date of its creation. First impressions count!

Your program should print the following lines:

```
Hello! My name is {botName}.
I was created in {birthYear}.
```

Instead of `{botName}`, print any name you choose and replace `{birthYear}` with the current year `(four digits)`.

---

## Stage 2
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

## Stage 3
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

## Stage 4
### Objective

In this stage, you will program the bot to count from 0 to any positive number users enter.
Note: each number starts with a new line, and after a number, the bot should print the exclamation mark.

---
