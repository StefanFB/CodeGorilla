package machine;

import machine.MachineState.*;

import java.util.Scanner;

public class Machine {
    MachineState state = MachineState.MAIN_MENU;
    int water = 400;
    int milk = 540;
    int coffee = 120;
    int cups = 9;
    int money = 550;

    Coffee espresso = new Coffee("espresso", 250, 0, 16, 4);
    Coffee latte = new Coffee("latte", 350, 75, 20, 7);
    Coffee cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);

    void machineStatus(Machine coffeeMachine) {
        System.out.printf("\nThe coffee machine has:\n" +
                        "%d ml of water\n" +
                        "%d ml of milk\n" +
                        "%d g of coffee beans\n" +
                        "%d disposable cups\n" +
                        "$%d of money\n",
                coffeeMachine.water, coffeeMachine.milk, coffeeMachine.coffee, coffeeMachine.cups, coffeeMachine.money);
    }

    void interpretInput(String input, Machine coffeemachine) {
        // Interpret input according to the current state of coffee machine
        switch (coffeemachine.state) {
            case MAIN_MENU:
                handleMainMenu(input, coffeemachine);
                break;
            case COFFEE_MENU:
                handleCoffeeMenu(input, coffeemachine);
                break;
            case FILL_WATER:
                fillWater(input, coffeemachine);
                break;
            case FILL_MILK:
                fillMilk(input, coffeemachine);
                break;
            case FILL_COFFEE:
                fillCoffee(input, coffeemachine);
                break;
            case FILL_CUPS:
                fillCups(input, coffeemachine);
                break;
            case OFF:
                exitMachine(coffeemachine);
                break;
        }
    }

    void setMainState() {
        state = MachineState.MAIN_MENU;
        System.out.println("\nPlease write your action (buy, fill, take, remaining, exit):");
    }

    void handleMainMenu(String input, Machine coffeemachine) {
        switch (input) {
            case "buy" -> {
                state = MachineState.COFFEE_MENU;
                System.out.println("What do you want to buy? (1 - espresso, 2 - latte, 3 - cappuccino");
                break;
            }
            case "fill" -> {
                state = MachineState.FILL_WATER;
                System.out.println("Write how many ml of water you want to add:");
                break;
            }
            case "take" -> {
                takeMoney(coffeemachine);
                setMainState();
                break;
            }
            case "remaining" -> {
                machineStatus(coffeemachine);
                setMainState();
                break;
            }
            case "exit" -> {
                state = MachineState.OFF;
                break;
            }
            default -> {
                System.out.println("unknown command, restarting program");
                setMainState();
                break;
            }
        }
    }

    void handleCoffeeMenu(String input, Machine coffeemachine) {

        switch (input) {
            case "1", "espresso" -> {
                makeCoffee(coffeemachine, "espresso");
                setMainState();
                break;
            }
            case "2", "latte" -> {
                makeCoffee(coffeemachine, "latte");
                setMainState();
                break;
            }
            case "3", "cappuccino" -> {
                makeCoffee(coffeemachine, "cappuccino");
                setMainState();
                break;
            }
            default -> {
                System.out.println("Returning to menu");
                setMainState();
                break;
            }
        }
    }

    boolean enoughIngredients(Machine coffeemachine, Coffee coffeetype) {
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

    void fillWater(String input, Machine coffeemachine) {
        coffeemachine.water += Integer.parseInt(input);
        state = MachineState.FILL_MILK;
        System.out.println("Write how many ml of milk you want to add:");
    }

    void fillMilk(String input, Machine coffeemachine) {
        coffeemachine.milk += Integer.parseInt(input);
        state = MachineState.FILL_COFFEE;
        System.out.println("Write how many grams of coffee beans you want to add:");
    }

    void fillCoffee(String input, Machine coffeemachine) {
        coffeemachine.coffee += Integer.parseInt(input);
        state = MachineState.FILL_CUPS;
        System.out.println("Write how many disposable cups you want to add:");
    }

    void fillCups(String input, Machine coffeemachine) {
        coffeemachine.cups += Integer.parseInt(input);
        setMainState();
    }

    void makeCoffee(Machine coffeemachine, String coffeeType) {

        // Choose correct coffee, subtract the necessary ingredients, show new machine status
        switch (coffeeType) {
            case "espresso" -> {
                if (enoughIngredients(coffeemachine, coffeemachine.espresso)) {
                    coffeemachine.water -= coffeemachine.espresso.water;
                    coffeemachine.milk -= coffeemachine.espresso.milk;
                    coffeemachine.coffee -= coffeemachine.espresso.coffee;
                    coffeemachine.cups--;
                    coffeemachine.money += coffeemachine.espresso.price;
                }
                break;
            }
            case "latte" -> {
                if (enoughIngredients(coffeemachine, coffeemachine.latte)){
                    coffeemachine.water -= coffeemachine.latte.water;
                    coffeemachine.milk -= coffeemachine.latte.milk;
                    coffeemachine.coffee -= coffeemachine.latte.coffee;
                    coffeemachine.cups--;
                    coffeemachine.money += coffeemachine.latte.price;
                }
                break;
            }
            case "cappuccino" -> {
                if (enoughIngredients(coffeemachine, coffeemachine.cappuccino)){
                    coffeemachine.water -= coffeemachine.cappuccino.water;
                    coffeemachine.milk -= coffeemachine.cappuccino.milk;
                    coffeemachine.coffee -= coffeemachine.cappuccino.coffee;
                    coffeemachine.cups--;
                    coffeemachine.money += coffeemachine.cappuccino.price;
                }
                break;
            }
            default -> {
                System.out.println("Unknown coffee-type, returning to menu ...");
            }
        }
    }

    void takeMoney(Machine coffeemachine) {

        // Inform user about how much money was taken from the machine
        System.out.printf("I gave you $%d\n", coffeemachine.money);

        // Update status of machine and return to menu
        coffeemachine.money = 0;
    }

    void exitMachine(Machine coffeemachine) {
        state = MachineState.OFF;
    }
}
