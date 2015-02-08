
package com.deadspeaker.spacedorf.level;

import com.deadspeaker.spacedorf.level.tile.Tile;
import com.deadspeaker.spacedorf.utils.MathFunctions;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/*
*
* @author Nikolay Padyukov
*/
public class ProceduralLevel extends Level{

    public int attempts;
    public int roomMaxSize;
    public int roomMinSize;
    public int deadEndsToTrim;
    public int winding;
    
    private Random rand = new Random();
    
    public ProceduralLevel(int width, int height) {
        super(width, height);
        attempts = 100;
        roomMaxSize = 5;
        roomMinSize = 2;
        deadEndsToTrim = 500;
        winding = 80;
        generateLevel();
    }
    
    protected void generateLevel(){
        Arrays.fill(tiles, 4);
        placeRooms();
        
        try {
            placeCorridors();
        } catch (Exception ex) {
            Logger.getLogger(ProceduralLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void placeRooms(){
        for(int i = 0; i < attempts; i++){
            //attempt to place out a room
            placeSingleRoom();
        }
    }
    
    private void placeCorridors() throws Exception{
//        for(int y = 0; y < this.levelHeight; y++){
//            for(int x = 0; x < this.levelWidth; x++){
//                if(isTilePartOfCorridor(x, y) && tiles[x + y * this.levelWidth] != 1)
//                {
//                    tiles[x + y * this.levelWidth] = 4;
//                }
//            }
//        }
        
        //Places mazes
        for(int y = 0; y < this.levelHeight; y++){
            for(int x = 0; x < this.levelWidth; x++){
                if(!isTilePartOfCorridor(x, y))
                {
                    doMaze(x, y, 0);
                }
            }
        }
        
        //Trims mazes
        trimDeadEnds();
        
    }
    private void trimDeadEnds(){
        int counter = this.deadEndsToTrim;
        while(counter > 0){
            for(int y = 0; y < this.levelHeight; y++){
                for(int x = 1; x < this.levelWidth; x++){
                    if(isDeadEnd(x, y)){
                        fillTile(x, y);
                        counter--;
                        
                        System.out.println("counter: " + counter);
                        
                        if(counter <= 0)
                            return;
                    }
                }
            }
        }
    }
    
    
    private boolean isDeadEnd(int x, int y){
        if(tiles[(x + 0) + (y + 0) * this.levelWidth] == 4)
            return false; //not an open space, i.e. not a deadend
        
        int c = 0;
        if(tiles[(x + 1) + (y + 0) * this.levelWidth] != 4)
            c++;
        if(tiles[(x + 0) + (y + 1) * this.levelWidth] != 4)
            c++;
        if(tiles[(x - 1) + (y + 0) * this.levelWidth] != 4)
            c++;
        if(tiles[(x + 0) + (y - 1) * this.levelWidth] != 4)
            c++;
        
        //dead end if precisely one adjoining open space
        return c == 1;
    }
    
    private boolean isValidCarveDirection(int x0, int y0, int x1, int y1) throws Exception{
        try {
            
            if((y1 - 1) < 0 || (x1 - 1) < 0 || (y1 + 1) >= this.levelHeight || (x1 + 1) >= this.levelWidth)
                return false;
            
            //Carving east
            //
            // 000
            // ->0
            // 000
            //
            if (x0 < x1 && y0 == y1) {
                return  tiles[(x1 + 0) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 + 0) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 + 1) * this.levelWidth] == 4
                        && tiles[(x1 + 0) + (y1 + 1) * this.levelWidth] == 4;
            } //Carving west
            //
            // 000
            // 0<-
            // 000
            //
            else if (x0 > x1 && y0 == y1) {
                return tiles[(x1 + 0) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 + 0) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 + 1) * this.levelWidth] == 4
                        && tiles[(x1 + 0) + (y1 + 1) * this.levelWidth] == 4;
            } //Carving north
            //
            // 000
            // 0^0
            // 0|0
            //
            else if (y0 > y1 && x0 == x1) {
                return tiles[(x1 + 0) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 + 0) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 - 1) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 + 0) * this.levelWidth] == 4;
            } //Carving south
            //
            // 0|0
            // 0v0
            // 000
            //
            else if (y0 < y1 && x0 == x1) {
                return tiles[(x1 + 0) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 + 0) * this.levelWidth] == 4
                        && tiles[(x1 - 1) + (y1 + 1) * this.levelWidth] == 4
                        && tiles[(x1 + 0) + (y1 + 1) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 + 1) * this.levelWidth] == 4
                        && tiles[(x1 + 1) + (y1 + 0) * this.levelWidth] == 4;
            }
        } catch (Exception ex) {
            Exception ex1 = new Exception("Bounds-exception: x0: " + x0 + ", y0: " + y0 + ", x1: " + x1 + ", y1: " + y1);
            ex1.addSuppressed(ex);
            throw ex1;
        }
        return false;
        
    }
    
    private void carveTile(int x, int y){
        tiles[x + y * this.levelWidth] = 1;
    }
    
    private void fillTile(int x, int y){
        tiles[x + y * this.levelWidth] = 4;
    }
    
    private void doMaze(int x, int y, int previousDirection) throws Exception{
        carveTile(x, y);

        if (y - 1 < 0 || x - 1 < 0 || y + 1 >= this.levelHeight || x + 1 >= this.levelWidth) {
            return;
        }
        //while valid directions
        ArrayList<Integer> validDirections = new ArrayList<>();
        do {
            validDirections.clear();
            if (isValidCarveDirection(x, y, x, y - 1)) {
                validDirections.add(1);
            }

            if (isValidCarveDirection(x, y, x + 1, y)) {
                validDirections.add(2);
            }

            if (isValidCarveDirection(x, y, x, y + 1)) {
                validDirections.add(3);
            }

            if (isValidCarveDirection(x, y, x - 1, y)) {
                validDirections.add(4);
            }

            if (validDirections.size() <= 0) {
                return;
            }

            //randomize between valid directions
            int direction = validDirections.get(rand.nextInt(validDirections.size()));

            //make a hole in the right direction and keep going with next block
            if (direction == 1 || previousDirection == 1 && validDirections.contains(1) && rand.nextInt(100) <= winding) {
                doMaze(x, y - 1, 1);
            } else if (direction == 2 || previousDirection == 2 && validDirections.contains(2) && rand.nextInt(100) <= winding) {
                doMaze(x + 1, y, 2);
            } else if (direction == 3 || previousDirection == 3 && validDirections.contains(3) && rand.nextInt(100) <= winding) {
                doMaze(x, y + 1, 3);
            } else if (direction == 4 || previousDirection == 4 && validDirections.contains(4) && rand.nextInt(100) <= winding) {
                doMaze(x - 1, y, 4);
            }
        } while (validDirections.size() > 0);
    }
    
    private boolean isTilePartOfCorridor(int xPos, int yPos){
        int x = xPos;
        int y = yPos;
        
        //Center tile
        if(tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Northern tile
        y = yPos - 1;
        if(y < 0 || y >= this.levelHeight || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Northeastern tile
        x = xPos + 1;
        if(y < 0 || y >= this.levelHeight || x < 0 || x >= this.levelWidth || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Eastern tile
        y = yPos;
        if(x < 0 || x >= this.levelWidth || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Southeastern tile
        y = yPos + 1;
        if(y < 0 || y >= this.levelHeight || x < 0 || x >= this.levelWidth || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Southern tile
        x = xPos;
        if(y < 0 || y >= this.levelHeight || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Southwestern tile
        x = xPos - 1;
        if(y < 0 || y >= this.levelHeight || x < 0 || x >= this.levelWidth || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Western tile
        y = yPos;
        if(x < 0 || x >= this.levelWidth || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        //Northwestern tile
        y = yPos - 1;
        if(y < 0 || y >= this.levelHeight || x < 0 || x >= this.levelWidth || tiles[x + y * this.levelWidth] == 1)
            return true;
        
        return false;
    }
    
    private void placeSingleRoom(){
        int roomWidth = MathFunctions.nextIntInRange(roomMinSize, roomMaxSize);
        int roomHeight = MathFunctions.nextIntInRange(roomMinSize, roomMaxSize);
        int roomPosX = MathFunctions.nextIntInRange(1, this.levelWidth - roomWidth - 1); //Leave one space on edge of screen
        int roomPosY = MathFunctions.nextIntInRange(1, this.levelHeight - roomHeight - 1); //Leave one space on edge of screen
        
        if(sectorHasExistingRooms(roomPosX, roomPosY, roomWidth, roomHeight))
            return;
        
        for(int y = roomPosY; y < roomPosY + roomHeight; y++){
            for(int x = roomPosX; x < roomPosX + roomWidth; x++){
                tiles[x + y * this.levelWidth] = 1;
            }
        }
    }
    private boolean sectorHasExistingRooms(int startX, int startY, int roomWidth, int roomHeight){
        startY--;
        if(startY < 0) startY = 0;
        startX--;
        if(startX < 0) startX = 0;
        roomWidth = roomWidth + 2;
        roomHeight = roomHeight + 2;
        for(int y = startY; y < startY + roomHeight; y++){
            for(int x = startX; x < startX + roomWidth; x++){
                if(tiles[x + y * this.levelWidth] == 1)
                    return true;
            }
        }
        return false;
    }
}
