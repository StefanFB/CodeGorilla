// You will need this in the following stages
const input = require('sync-input')

// Declare an object to keep track of how much is needed for one cup of coffee
const coffeeCup = {
    water: 200,
    milk: 50,
    coffee: 15
}

let cups = input("Write how many cups of coffee you will need:\n");
console.log(`For ${cups} cups of coffee you will need:
${coffeeCup.water * cups} ml of water
${coffeeCup.milk * cups} ml of milk
${coffeeCup.coffee * cups} g of coffee beans`);
