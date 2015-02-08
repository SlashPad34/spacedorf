
package com.deadspeaker.spacedorf.graphics;

/*
*
* @author Nikolay Padyukov
*/
public class Sprite {
    public final int SIZE;
    private int x, y;
    private SpriteSheet sheet;
    public int[] pixels;
    
    public static Sprite grassSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite bookcaseSprite = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite waterSprite = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite wallSprite = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0xFF87CEEB);
    
    public Sprite(int size, int color){
        this.SIZE = size;
        this.pixels = new int[size * size];
    }
    
    public Sprite(int size, int x, int y, SpriteSheet sheet){
        this.SIZE = size;
        this.pixels = new int[size * size];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    private void load(){
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
    
    private void setColor(int color){
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = color;
        }
    }
}
