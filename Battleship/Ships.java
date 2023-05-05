package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ships {

    static List<Ship> shipList = new ArrayList<>();

    public static void initShipList() {
        shipList.add(Ship.carrier);
        shipList.add(Ship.battleship);
        shipList.add(Ship.submarine);
        shipList.add(Ship.cruiser);
        shipList.add(Ship.destroyer);
    }

    public static boolean checkAllShips(Game game) {

        // Go over all ships that are still intact
        for (Ship ship : shipList) {
            if (!ship.destroyed) {
                // System.out.printf("Checking ship %s\n", ship.name);
                if (ship.horizontal) {          // horizontal loop, y-coordinate stays the same
                    int counter = 0;
                    for (int i = 0; i < ship.length; i++) {

                        // System.out.printf("Checking %s on %d %d\n", ship.name, ship.shipStartY, ship.shipStartX + i);

                        if (game.field2D[ship.shipStartY][ship.shipStartX + i] == 'X') {
                            counter++;
                        }
                    }
                    // System.out.println("Counter: " + counter);
                    if (counter == ship.length) {
                        ship.destroyed = true;
                        return true;
                    }
                } else {                    // vertical loop, x-coordinate stays the same
                    int counter = 0;
                    for (int i = 0; i < ship.length; i++) {

                        // System.out.printf("Checking %s on %d %d\n", ship.name, ship.shipStartY + i, ship.shipStartX);

                        if (game.field2D[ship.shipStartY + i][ship.shipStartX] == 'X') {
                            counter++;
                        }
                    }
                    // System.out.println("Counter: " + counter);
                    if (counter == ship.length) {
                        ship.destroyed = true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean allShipsDestroyed() {
        for (Ship ship : shipList) {
            if (!ship.destroyed) {
                return false;
            }
        }
        return true;
    }

    public static void showShipStatus() {
        for (Ship ship : shipList) {
            System.out.printf("%s destroyed: %b\n", ship.name, ship.destroyed);
        }
    }
}
