
package com.deadspeaker.spacedorf.level.tile;

import com.deadspeaker.spacedorf.graphics.Sprite;

/*
*
* @author Nikolay Padyukov
*/
public class BookcaseTile extends Tile{

    public BookcaseTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
