package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Coffee coffee = new Coffee(200, 50, 15);

        System.out.println("Write how many cups of coffee you will need: ");
        int input = scan.nextInt();
        System.out.println("For 25 cups of coffee you will need:");
        System.out.printf("%d ml of water\n%d ml of milk\n%d g of coffee beans",
                coffee.water * input, coffee.milk * input, coffee.coffee * input);
    }
}
