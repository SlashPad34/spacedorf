/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.deadspeaker.spacedorf;

import javax.swing.JFrame;

/**
 *
 * @author Nikolay
 */
public class SpaceDorf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Dorf");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        //game.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.frame.setVisible(true);
        
        game.start();
    }
    
}
