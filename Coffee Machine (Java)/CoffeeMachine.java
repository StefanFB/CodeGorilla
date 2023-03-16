package machine;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {

    static void machineStatus(Machine coffeeMachine) {
        System.out.printf("\nThe coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n",
                coffeeMachine.water, coffeeMachine.milk, coffeeMachine.coffee, coffeeMachine.cups, coffeeMachine.money);
        showMenu(coffeeMachine);
    }

    static void showMenu(Machine coffeemachine) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nPlease write your action (buy, fill, take, remaining, exit):");
        String action = scan.nextLine();
        switch (action) {
            case "buy" -> buyCoffee(coffeemachine);
            case "fill" -> fillMachine(coffeemachine);
            case "take" -> takeMoney(coffeemachine);
            case "remaining" -> machineStatus(coffeemachine);
            case "exit" -> {
                return;
            }
            default -> {
                System.out.println("unknown command, restarting program");
                showMenu(coffeemachine);
            }
        }
    }

    static void buyCoffee(Machine coffeemachine) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What do you want to buy? (1 - espresso, 2 - latte, 3 - cappuccino");
        int choice = 0;
        try {
            choice = scan.nextInt();
        } catch (Exception e) {
            // System.out.println("Returning to menu");
            // showMenu(coffeemachine);
        }
        switch (choice) {
            case 1 -> {
                makeCoffee(coffeemachine, "espresso");
            }
            case 2 -> {
                makeCoffee(coffeemachine, "latte");
            }
            case 3 -> {
                makeCoffee(coffeemachine, "cappuccino");
            }
            default -> {
                System.out.println("Returning to menu");
                showMenu(coffeemachine);
            }
        }
    }

    static boolean enoughIngredients(Machine coffeemachine, Coffee coffeetype) {
        // Check all available ingredients
        if (coffeemachine.water < coffeetype.water) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (coffeemachine.milk < coffeetype.milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (coffeemachine.coffee < coffeetype.coffee) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        } else if (coffeemachine.cups <= 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else {
            System.out.printf("I have enough resources, making you a %s!\n", coffeetype.name);
            return true;
        }
    }

    static void fillMachine(Machine coffeemachine) {
        Scanner scan = new Scanner(System.in);

        // Ask user what they want to add to the coffee machine
        System.out.println("Write how many ml of water you want to add:");
        int addWater = scan.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int addMilk = scan.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int addCoffee = scan.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        int addCups = scan.nextInt();

        // Add it to the coffee machine, then print the new status
        coffeemachine.water += addWater;
        coffeemachine.milk += addMilk;
        coffeemachine.coffee += addCoffee;
        coffeemachine.cups += addCups;
        showMenu(coffeemachine);
    }

    static void makeCoffee(Machine coffeemachine, String coffeeType) {

        // Create new coffee-types
        Coffee espresso = new Coffee("espresso", 250, 0, 16, 4);
        Coffee latte = new Coffee("latte", 350, 75, 20, 7);
        Coffee cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);

        // Choose correct coffee, subtract the necessary ingredients, show new machine status
        switch (coffeeType) {
            case "espresso" -> {
                if (enoughIngredients(coffeemachine, espresso)) {
                    coffeemachine.water -= espresso.water;
                    coffeemachine.milk -= espresso.milk;
                    coffeemachine.coffee -= espresso.coffee;
                    coffeemachine.cups--;
                    coffeemachine.money += espresso.price;
                }
            }
            case "latte" -> {
                if (enoughIngredients(coffeemachine, latte)){
                    coffeemachine.water -= latte.water;
                    coffeemachine.milk -= latte.milk;
                    coffeemachine.coffee -= latte.coffee;
                    coffeemachine.cups--;
                    coffeemachine.money += latte.price;
                }
            }
            case "cappuccino" -> {
                if (enoughIngredients(coffeemachine, cappuccino)){
                    coffeemachine.water -= cappuccino.water;
                    coffeemachine.milk -= cappuccino.milk;
                    coffeemachine.coffee -= cappuccino.coffee;
                    coffeemachine.cups--;
                    coffeemachine.money += cappuccino.price;
                }
            }
            default -> {
                System.out.println("Unknown coffee-type, returning to menu ...");
                showMenu(coffeemachine);
            }
        }
        showMenu(coffeemachine);
    }

    static void takeMoney(Machine coffeemachine) {
        Scanner scan = new Scanner(System.in);

        // Inform user about how much money was taken from the machine
        System.out.printf("I gave you $%d\n", coffeemachine.money);

        // Update status of machine and return to menu
        coffeemachine.money = 0;
        showMenu(coffeemachine);
    }

    public static void main(String[] args) {
        // Create our machine with pre-defined amounts of water, milk, coffee, etc.
        Machine coffeeMachine = new Machine();

        // Show menu. Each following action will return to the menu
        showMenu(coffeeMachine);
    }
}
