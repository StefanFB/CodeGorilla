package battleship;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        game.showMap();

        // Place ships during preparation-phase
        while (game.state != GameState.READY) {
            game.handlePlacement();
            game.showMap();
            if (game.prepPhase == Preparation.PREP_DONE) {
                game.state = GameState.READY;
            }
        }
        // Inform user the game is about to start
        System.out.println("\nThe game starts!\n");

        // Let user shoot once
        game.showMap();
        game.handleFiring();
    }
}
