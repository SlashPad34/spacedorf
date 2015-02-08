
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
        generateLevel();
    }
    
    protected void generateLevel(){
        for(int y = 0; y < levelHeight; y++){
            for(int x = 0; x < levelWidth; x++){
                tiles[x + y * levelWidth] = getTile(rand.nextInt(64));
            }
        }
    }

    private int getTile(int rand){
        if(rand < 36)
            return 1;
        else if(rand >= 36 && rand < 48)
            return 2;
        else if(rand >= 48 && rand < 56)
            return 3;
        else if(rand >= 56)
            return 4;
        else
            return 0;
    }
}
