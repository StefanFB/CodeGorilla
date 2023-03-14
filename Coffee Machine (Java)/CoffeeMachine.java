package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Coffee coffee = new Coffee(200, 50, 15);

        // Read inputs: how much ingredients are in the machine, and how much coffee has to be made
        System.out.println("Write how many ml of water the coffee machine has:");
        int availableWater = scan.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int availableMilk = scan.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int availableCoffee = scan.nextInt();
        System.out.println("Write how many cups of coffee you will need: ");
        int cupsOfCoffee = scan.nextInt();

        // Calculate how much ingredients are needed
        int neededWater = cupsOfCoffee * coffee.water;
        int neededMilk = cupsOfCoffee * coffee.milk;
        int neededCoffee = cupsOfCoffee * coffee.coffee;

        // Create sorted array with possible cups. Then compare index 0 with the demanded amount.
        int[] possibleCups = {availableWater / coffee.water, availableMilk / coffee.milk, availableCoffee / coffee.coffee};
        Arrays.sort(possibleCups);

        if (cupsOfCoffee == possibleCups[0]) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (cupsOfCoffee < possibleCups[0]) {
            int extra = possibleCups[0] - cupsOfCoffee;
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", extra);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", possibleCups[0]);
        }
    }
}
