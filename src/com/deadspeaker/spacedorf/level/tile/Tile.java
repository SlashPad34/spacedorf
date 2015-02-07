
package com.deadspeaker.spacedorf.level.tile;

import com.deadspeaker.spacedorf.graphics.Screen;
import com.deadspeaker.spacedorf.graphics.Sprite;

/*
*
* @author Nikolay Padyukov
*/
public class Tile {
    public int x, y;
    public Sprite sprite;
    
    public Tile(Sprite sprite){
        this.sprite = sprite;
    }
    
    public static Tile grassTile = new GrassTile(Sprite.grassSprite);
    public static Tile waterTile = new WaterTile(Sprite.waterSprite);
    public static Tile bookcaseTile = new BookcaseTile(Sprite.bookcaseSprite);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    
    public void render(int x, int y, Screen screen){
        screen.renderTile(x * screen.tileSize, y * screen.tileSize, this);
    }
    
    public boolean isSolid(){
        return false;
    }
    public boolean isAnimated(){
        return false;
    }
}
