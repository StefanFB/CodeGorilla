package battleship;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        game.showFullMap();

        // Place ships during preparation-phase
        while (game.state != GameState.READY) {
            game.handlePlacement();
            game.showFullMap();
            if (game.prepPhase == Preparation.PREP_DONE) {
                game.state = GameState.READY;
            }
        }
        // Inform user the game is about to start
        System.out.println("The game starts!");

        // Let user shoot once
        game.showMap();
        game.handleFiring();
        game.showFullMap();
    }
}
