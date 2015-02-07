
package com.deadspeaker.spacedorf.level;

import com.deadspeaker.spacedorf.graphics.Screen;
import com.deadspeaker.spacedorf.level.tile.Tile;

/*
*
* @author Nikolay Padyukov
*/
public class Level {
    protected int width, height;
    protected int[] tiles;
    public Level(int width, int height){
        this.width = width; this.height = height;
        this.tiles = new int[width * height];
        
        generateLevel();
    }
    
    protected void generateLevel(){
        
    }
    
    public void render(Screen screen){
        int x1 = screen.width / screen.tileSize;
        int y1 = screen.height / screen.tileSize;
        for (int y = 0; y < y1; y++){
            for(int x = 0; x < x1; x++){
                Tile tile = getTile(x, y);
                tile.render(x, y, screen);
            }
        }
    }
    
    private Tile getTile(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
        switch(tiles[x + y * width]){
            case 0: return Tile.grassTile;
            case 1: return Tile.grassTile;
            case 2: return Tile.bookcaseTile;
            case 3: return Tile.waterTile;
            default: return Tile.grassTile;
        }
    }
    
    
}
