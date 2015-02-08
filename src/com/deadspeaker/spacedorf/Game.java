
package com.deadspeaker.spacedorf;

import com.deadspeaker.spacedorf.graphics.Screen;
import com.deadspeaker.spacedorf.input.Keyboard;
import com.deadspeaker.spacedorf.level.Level;
import com.deadspeaker.spacedorf.level.ProceduralLevel;
import com.deadspeaker.spacedorf.level.RandomLevel;
import com.sun.glass.events.KeyEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

/*
* GAME-class, initializes the game and contains main game-loop
*
* @author Nikolay Padyukov
*/
public class Game extends Canvas implements Runnable{

    //Height is decided by width and aspect ratio
    private static final int width = 1440;
    private static final int tileSize = 16;
    private static final int height = (width / 16) * 9; 
    private static final int scale = 1;
    
    private Thread thread;
    public JFrame frame;
    private Screen screen;
    private Level level;
    private Keyboard keyboard;
    
    private boolean running = false;
    private int ups, fps;
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    public Game(){
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        frame = new JFrame();
        frame.setUndecorated(true);
        screen = new Screen(width, height, tileSize);
        level = new ProceduralLevel(85,50);
        keyboard = new Keyboard();
        
        addKeyListener(keyboard);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    
    public synchronized void stop(){
        running = false;
        try{
            thread.join();
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Main game loop
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int ticks = 0;
        
        requestFocus();
        
        while(running){
            long thisTime = System.nanoTime();
            delta += (thisTime - lastTime) / ns;
            lastTime = thisTime;
            while(delta >= 1){
                tick();
                ticks++;
                delta--;
            }
            
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000) {
                //level = new ProceduralLevel(40,25); 
                timer += 1000;
                ups = ticks;
                fps = frames;
                ticks = 0;
                frames = 0;
            }
            
            if(keyboard.keys[KeyEvent.VK_ESCAPE])
                running = false;
        }
        frame.dispose();
    }
    
    //Updates the game
    private void tick(){
    }
    
    private void render(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if(bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        level.render(screen);
        
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.white);
        g.setFont(new Font("Verdana", 0, 20));
        g.drawString("UPS: " + ups + " FPS: " + fps, 20, 80);
        g.dispose();
        bufferStrategy.show();
    }
}
