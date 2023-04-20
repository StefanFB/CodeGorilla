package tictactoe;

import java.util.Random;

public class EasyAI implements AI {

    final String name = "easy";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int[] makeMove() {
        Random random = new Random();

        int[] move = new int[2];

        move[0] = random.nextInt(3);
        move[1] = random.nextInt(3);

        return move;
    }
}
