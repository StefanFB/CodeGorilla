package tictactoe;

import java.util.Random;

class EasyAI implements PlayerType {

    final String name = "easy";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void makeMove(TicTacToe game) {
        System.out.printf("Making move level \"%s\"\n", this.name);
        makeRandomMove(game);
    }
}
