// You will need this in the following stages
const input = require('sync-input')

// Main part of program
// Ask user what he wants to do
function main() {
    let task = input("Write action (buy, fill, take, remaining, exit):\n");

// Buy-route: ask what user wants, then run that through the makeCoffee function
    if (task.toLowerCase() === "buy") {
        let order = input(`\nWhat do you want to buy?
1 - espresso, 2 - black, 3 - latte, 4 - cappuccino:\n`).toLowerCase();
        switch (order) {
            case "back":
                main();
                break;
            case "1":
            case "espresso":
                makeCoffee(espresso);
                break;
            case "2":
            case "black":
                makeCoffee(espresso);
                break;
            case "3":
            case "latte":
                makeCoffee(latte);
                break;
            case "4":
            case "cappuccino":
                makeCoffee(cappuccino);
                break;
            default:
                console.log("Unrecognized coffee type\n");
                main();
        }
    } else if (task.toLowerCase() === "fill") {
        fillMachine();
    } else if (task.toLowerCase() === "take") {
        emptyMachineMoney();
    } else if (task.toLowerCase() === "remaining") {
        machineStatus();
    } else if (task.toLowerCase() === "exit") {
        return 0;
    } else {
        console.log("Unrecognized command\n")
        main();
    }
}

// Starting conditions of the coffee machine
const coffeeMachine = {
    water: 400,
    milk: 540,
    coffee: 120,
    cups: 9,
    largeCups: 9,
    money: 550
}

// Function to show the current status of the coffee machine
function machineStatus() {
    console.log(`\nThe coffee machine has:
${coffeeMachine.water} ml of water
${coffeeMachine.milk} ml of milk
${coffeeMachine.coffee} g of coffee beans
${coffeeMachine.cups} disposable cups
${coffeeMachine.largeCups} disposable large cups
$${coffeeMachine.money} of money\n`)
    main();
}

// Declare function constructor to easily add new coffee types
function Coffee(drink, water, milk, coffee, money) {
    this.drink = drink;
    this.water = water;
    this.milk = milk;
    this.coffee = coffee;
    this.money = money;
}

// Currently we sell espresso's, latte's and cappuccino's
const black = new Coffee("black", 300, 0, 16, 5);
const espresso = new Coffee("espresso", 250, 0, 16, 4);
const latte = new Coffee("latte", 350, 75, 20, 7);
const cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);

// Function to update the machine status after making a certain type of coffee
// Subtract the used ingredients from the machine supply, add the cost of a drink to the machine's money
function makeCoffee(coffee) {
    // Ask what size the user wants
    let size = input("What size do you like? (M | L)\n");
    let factor = 1;
    if (size.toUpperCase() === "L") {
        factor = 1.5;

        // Check available large cups
        if (coffeeMachine.largeCups <= 0) {
            console.log("Sorry, not enough large cups\n");
            main();
        }
    } else if (size.toUpperCase() === "M") {
        if (coffeeMachine.cups <= 0) {
            console.log("Sorry, not enough cups\n");
            main();
        }
    } else {
        console.log("Unrecognized size. Please order again.\n");
        main();
    }

    // Calculate the necessary ingredients for this drink
    let water = coffee.water * factor;
    let milk = coffee.milk * factor;
    let gramsCoffee = coffee.coffee * factor;
    let money = coffee.money * factor;

    // First, check if all necessary ingredients are available
    if (coffeeMachine.water < water) {
        console.log("Sorry, not enough water\n");
        main();
    } else if (coffeeMachine.milk < milk) {
        console.log("Sorry, not enough milk\n");
        main();
    } else if (coffeeMachine.coffee < gramsCoffee) {
        console.log("Sorry, not enough coffee\n");
        main();
    }

    // If all ingredients are available, proceed with making coffee, subtracting ingredients and adding money
    else {
        coffeeMachine.water -= water;
        coffeeMachine.milk -= milk;
        coffeeMachine.coffee -= gramsCoffee;
        coffeeMachine.money += money;
        switch (size.toUpperCase()) {
            case "M":
                coffeeMachine.cups--;
            case "L":
                coffeeMachine.largeCups--;
        }
        console.log("I have enough resources, making you a coffee!\n")
        main();
    }
}

// Function to fill the machine with new ingredients and cups
function fillMachine() {
    // Get input from user about how much he wants to add
    let addWater = Number(input("Write how many ml of water you want to add:\n"));
    let addMilk = Number(input("Write how many ml of milk you want to add:\n"));
    let addCoffee = Number(input("Write how many grams of coffee beans you want to add:\n"));
    let addCups = Number(input("Write how many disposable cups you want to add:\n"));
    let addLargeCups = Number(input("Write how many large disposable cups you want to add:\n"));

    // Change the machine's content
    coffeeMachine.water += addWater;
    coffeeMachine.milk += addMilk;
    coffeeMachine.coffee += addCoffee;
    coffeeMachine.cups += addCups;
    coffeeMachine.largeCups += addLargeCups;
    console.log("");
    main();
}

// Function to empty the money drawer from the machine
function emptyMachineMoney() {
    console.log(`\nI gave you $${coffeeMachine.money}\n`);
    coffeeMachine.money = 0;
    main();
}

main();
