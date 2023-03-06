// You will need this in the following stages
const input = require('sync-input')

// Starting conditions of the coffee machine
const coffeeMachine = {
    water: 400,
    milk: 540,
    coffee: 120,
    cups: 9,
    money: 550
}

// Function to show the current status of the coffee machine
function machineStatus() {
    console.log(`The coffee machine has:
${coffeeMachine.water} ml of water
${coffeeMachine.milk} ml of milk
${coffeeMachine.coffee} g of coffee beans
${coffeeMachine.cups} disposable cups
$${coffeeMachine.money} of money\n`)
}

// Declare several different coffees to sell with this machine using function constructor
function Coffee(drink, water, milk, coffee, money) {
    this.drink = drink;
    this.water = water;
    this.milk = milk;
    this.coffee = coffee;
    this.money = money;
}

// Currently we sell espresso's, latte's and cappuccino's
const espresso = new Coffee("espresso", 250, 0, 16, 4);
const latte = new Coffee("latte", 350, 75, 20, 7);
const cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);

// Function to update the machine status after making a certain type of coffee
// Subtract the used ingredients from the machine supply, add the cost of a drink to the machine's money
function makeCoffee(coffee) {
    coffeeMachine.water -= coffee.water;
    coffeeMachine.milk -= coffee.milk;
    coffeeMachine.coffee -= coffee.coffee;
    coffeeMachine.cups--;
    coffeeMachine.money += coffee.money;
    machineStatus();
}

// Function to fill the machine with new ingredients and cups
function fillMachine() {
    // Get input from user about how much he wants to add
    let water = Number(input("Write how many ml of water you want to add:\n"));
    let milk = Number(input("Write how many ml of milk you want to add:\n"));
    let coffee = Number(input("Write how many grams of coffee beans you want to add:\n"));
    let cups = Number(input("Write how many disposable cups you want to add:\n"));

    // Change the machine's content
    coffeeMachine.water += water;
    coffeeMachine.milk += milk;
    coffeeMachine.coffee += coffee;
    coffeeMachine.cups += cups;

    // Display new, updated machine status
    console.log("");
    machineStatus();
}

// Function to empty the money drawer from the machine
function emptyMachineMoney() {
    console.log(`I gave you $${coffeeMachine.money}\n`);
    coffeeMachine.money = 0;
    machineStatus();
}

// Main part of program
// First display current status of the coffee machine
machineStatus();

// Ask user what he wants to do
let task = input("Write action (buy, fill, take):\n");

// Buy-route: ask what user wants, then run that through the makeCoffee function
if (task === "buy") {
    let order = input("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:\n")
    switch (order) {
        case "1":
        case "espresso":
            makeCoffee(espresso);
            break;
        case "2":
        case "latte":
            makeCoffee(latte);
            break;
        case "3":
        case "cappuccino":
            makeCoffee(cappuccino);
            break;
        default:
            console.log("Unrecognized coffee type")
    }
// Fill-route
} else if (task === "fill") {
    fillMachine();
} else if (task === "take") {
    emptyMachineMoney();
} else {
    console.log("Unrecognized command")
}
