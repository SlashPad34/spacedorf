
package com.deadspeaker.spacedorf.level.tile;

import com.deadspeaker.spacedorf.graphics.Sprite;

/*
*
* @author Nikolay Padyukov
*/
public class WaterTile extends Tile{

    public WaterTile(Sprite sprite) {
        super(sprite);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
