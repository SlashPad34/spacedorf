
package com.deadspeaker.spacedorf.utils;

import java.util.Random;

/*
* Various utility functions
*
* @author Nikolay Padyukov
*/
public class MathFunctions {

    public static int nextIntInRange(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
