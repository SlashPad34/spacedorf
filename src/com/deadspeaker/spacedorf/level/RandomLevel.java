
package com.deadspeaker.spacedorf.level;

import java.util.Random;

/*
* Basic randomly generated level
*
* @author Nikolay Padyukov
*/
public class RandomLevel extends Level{

    private static Random rand = new Random();
    
    public RandomLevel(int width, int height) {
        super(width, height);
    }
    
    protected void generateLevel(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x + y * width] = getTile(rand.nextInt(64));
            }
        }
    }

    private int getTile(int rand){
        if(rand < 48)
            return 1;
        else if(rand >= 48 & rand < 56)
            return 2;
        else if(rand >= 56)
            return 3;
        else
            return 0;
    }
}
