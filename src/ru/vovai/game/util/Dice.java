package ru.vovai.game.util;

import java.util.Random;

public class Dice {
    private static final int sides = 6;
    private final Random random;

    public Dice() {
        this.random = new Random();
    }

    public boolean rollAndCheckSuccess(int count) {
        boolean success = false;
        for (int i = 0; i < count; i++) {
            int rollResult = random.nextInt(sides) + 1;
            if (rollResult >= 5) {
                success = true;
                break;
            }
        }
        return success;
    }
}
