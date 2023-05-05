package battleship;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        game.showFullMap();

        // Place ships during preparation-phase
        while (game.state == GameState.PREPARATION) {
            game.handlePlacement();
            game.showFullMap();
            if (game.prepPhase == Preparation.PREP_DONE) {
                game.state = GameState.START;
            }
        }
        // Inform user the game is about to start
        System.out.println("The game starts!");
        System.out.printf("%s x = %d, y = %d, horizontal = %b",
                Ship.battleship.name, Ship.battleship.shipStartX, Ship.battleship.shipStartY, Ship.battleship.horizontal);

        // Let user shoot until all ships are sunk and the game ends
        while (game.state != GameState.END) {
            game.showMap();
            game.handleFiring();
            // Ships.showShipStatus();
        }
        System.out.print("You won. Congratulations!");
    }
}
