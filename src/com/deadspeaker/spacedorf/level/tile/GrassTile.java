
package com.deadspeaker.spacedorf.level.tile;

import com.deadspeaker.spacedorf.graphics.Sprite;

/*
*
* @author Nikolay Padyukov
*/
public class GrassTile extends Tile{

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}
