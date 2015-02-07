package com.deadspeaker.spacedorf.graphics;

import com.deadspeaker.spacedorf.level.tile.Tile;

/*
* Class that handles screen rendering
*
* @author Nikolay Padyukov
*/
public class Screen {
    public int width, height;
    public int[] pixels;
    public int tileSize;
    
    public Screen(int width, int height, int tileSize){
        
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        this.tileSize = tileSize;
    }
    
    public void clear(){
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }
    
    public void renderTile(int xP, int yP, Tile tile){
        for(int y = 0; y < tile.sprite.SIZE; y++){
            int yAbsolute = yP + y;
            if(yAbsolute < 0) yAbsolute = 0;
            else if(yAbsolute >= height) yAbsolute = height - 1;
            for(int x = 0; x < tile.sprite.SIZE; x++){
                int xAbsolute = xP + x;
                if(xAbsolute < 0) xAbsolute = 0;
                else if(xAbsolute >= width) xAbsolute = width -1;
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
}
