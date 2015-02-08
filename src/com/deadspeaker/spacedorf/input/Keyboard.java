
package com.deadspeaker.spacedorf.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
* This class handles keyboard input
*
* @author Nikolay Padyukov
*/
public class Keyboard implements KeyListener{

    public boolean[] keys = new boolean[Character.MAX_VALUE];
    public boolean up, down, left, right;
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
