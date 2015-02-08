
package com.deadspeaker.spacedorf.level.tile;

import com.deadspeaker.spacedorf.graphics.Sprite;

/*
*
* @author Nikolay Padyukov
*/
public class WallTile extends Tile {

    public WallTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
