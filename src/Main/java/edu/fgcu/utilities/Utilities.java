package edu.fgcu.utilities;

import java.util.Random;

/**
 * Created by brian on 4/1/2015.
 */
public class Utilities {
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
