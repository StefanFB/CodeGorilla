package machine;

import java.util.Scanner;

import static machine.MachineState.*;
public class CoffeeMachine {

        public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Create our machine with pre-defined amounts of water, milk, coffee, etc.
        Machine coffeeMachine = new Machine();

        // Set status to MAIN_MENU, then show MainMenu
        coffeeMachine.setMainState();

        // Let interpreter read every line of input
        while (coffeeMachine.state != OFF) {
            coffeeMachine.interpretInput(scan.next(), coffeeMachine);
        }
    }
}
