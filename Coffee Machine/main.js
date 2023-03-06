// You will need this in the following stages
const input = require('sync-input')

// Main part of program
// Ask user what he wants to do
function main() {
    let task = input("Write action (buy, fill, take, remaining, exit):\n");

// Buy-route: ask what user wants, then run that through the makeCoffee function
    if (task === "buy") {
        let order = input("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:\n")
        switch (order) {
            case "back":
                main();
                break;
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
                console.log("Unrecognized coffee type\n");
                main();
        }
    } else if (task === "fill") {
        fillMachine();
    } else if (task === "take") {
        emptyMachineMoney();
    } else if (task === "remaining") {
        machineStatus();
    } else if (task === "exit") {
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
    money: 550
}

// Function to show the current status of the coffee machine
function machineStatus() {
    console.log(`\nThe coffee machine has:
${coffeeMachine.water} ml of water
${coffeeMachine.milk} ml of milk
${coffeeMachine.coffee} g of coffee beans
${coffeeMachine.cups} disposable cups
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
const espresso = new Coffee("espresso", 250, 0, 16, 4);
const latte = new Coffee("latte", 350, 75, 20, 7);
const cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);

// Function to update the machine status after making a certain type of coffee
// Subtract the used ingredients from the machine supply, add the cost of a drink to the machine's money
function makeCoffee(coffee) {
    // First, check if all necessary ingredients are available
    if (coffeeMachine.water < coffee.water) {
        console.log("Sorry, not enough water\n");
        main();
    } else if (coffeeMachine.milk < coffee.milk) {
        console.log("Sorry, not enough milk\n");
        main();
    } else if (coffeeMachine.coffee < coffee.coffee) {
        console.log("Sorry, not enough coffee\n");
        main();
    } else if (coffeeMachine.cups <= 0) {
        console.log("Sorry, not enough cups\n");
        main();
    }
    // If all ingredients are available, proceed with making coffee, subtracting ingredients and adding money
    else {
        coffeeMachine.water -= coffee.water;
        coffeeMachine.milk -= coffee.milk;
        coffeeMachine.coffee -= coffee.coffee;
        coffeeMachine.cups--;
        coffeeMachine.money += coffee.money;
        console.log("I have enough resources, making you a coffee!\n")
        main();
    }
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
    main();
}

// Function to empty the money drawer from the machine
function emptyMachineMoney() {
    console.log(`\nI gave you $${coffeeMachine.money}\n`);
    coffeeMachine.money = 0;
    main();
}

main();
