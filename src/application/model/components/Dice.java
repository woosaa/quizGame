package application.model.components;

import java.util.Random;

public class Dice
{
    private static final int INDEX_CORRECTION = 1;
    private int eyes;

    public int throwDice(){
        Random rand = new Random();
        eyes = rand.nextInt(6) + INDEX_CORRECTION;
        return eyes;
    }

    public int getEyes() {
        return eyes;
    }
}
