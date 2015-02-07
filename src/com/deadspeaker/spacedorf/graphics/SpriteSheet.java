
package com.deadspeaker.spacedorf.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
*
* @author Nikolay Padyukov
*/
public class SpriteSheet {
    private String path;
    public final int SIZE;
    public int [] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
    public SpriteSheet(String path, int size){
        this.path = path;
        this.SIZE = size;
        this.pixels = new int[size * size];
        loadSpriteSheet();
    }
    
    private void loadSpriteSheet(){
        try{
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
