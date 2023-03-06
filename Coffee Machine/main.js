// You will need this in the following stages
const input = require('sync-input')

// Declare an object to keep track of how much is needed for one cup of coffee
const coffeeCup = {
    water: 200,
    milk: 50,
    coffee: 15
}

let amountWater = input("Write how many ml of water the coffee machine has:\n");
let amountMilk = input("Write how many ml of milk the coffee machine has:\n");
let amountCoffee = input("Write how many grams of coffee beans the coffee machine has:\n");
let cups = Number(input("Write how many cups of coffee you will need:\n"));
let calculateCups = [
    Math.floor(amountWater / coffeeCup.water),
    Math.floor(amountMilk / coffeeCup.milk),
    Math.floor(amountCoffee / coffeeCup.coffee)
];
calculateCups.sort(function(a,b){return a - b});
if (calculateCups[0] < cups) {
    console.log(`No, I can make only ${calculateCups[0]} cups of coffee`);
} else if (calculateCups[0] === cups) {
    console.log("Yes, I can make that amount of coffee")
} else {
    let extra = calculateCups[0] - cups;
    console.log(`Yes, I can make that amount of coffee (and even ${extra} more than that)`);
}
