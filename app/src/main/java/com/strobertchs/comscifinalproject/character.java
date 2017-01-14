package com.strobertchs.comscifinalproject;

/**
 * Created by Gilb on 1/11/2017.
 */

public class character extends GameActivity{
    //constructor for new characters gives rounded block values when x,y,height,width values are entered
    //THIS IS ALL WE NEEDED TO DO CHARLES NOT MAKE 1000000 VARIABLES AHHHHHHHHHHH - Gilbert
    //Hi, I'm helping -Darius
    //what the fuck am I actually doing?
    int positionX;
    int positionY;
    int height;
    int width;
    int blockHeight;
    int blockWidth;
    int blockX;
    int blockY;

    public character(int positionX, int positionY, int height, int width){
        this.positionX = (int) Math.round(blockSize * positionX);
        this.positionY = (int) Math.round(blockSize * positionY);
        this.height = (int) Math.round(blockSize * height);
        this.width = (int) Math.round(blockSize * width);
        this.blockHeight = height;
        this.blockWidth = width;
        this.blockX = positionX;
        this.blockY = positionY;

    }
    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public int getRoundedHeight(){
        return height;
    }

    public int getRoundedWidth(){
        return width;
    }

    public int getBlockHeight(){
        return blockHeight;
    }
    public int getBlockWidth(){
        return blockWidth;
    }
    public int getBlockX(){
        return blockX;
    }
    public int getBlockY(){
        return blockY;
    }
}

