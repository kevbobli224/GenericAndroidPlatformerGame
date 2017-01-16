package com.strobertchs.comscifinalproject;

/**
 * Created by Gilb on 1/11/2017.
 */

public class character extends GameActivity{
    //constructor for new characters gives rounded block values when x,y,height,width values are entered
    //THIS IS ALL WE NEEDED TO DO CHARLES NOT MAKE 1000000 VARIABLES AHHHHHHHHHHH - Gilbert
    int positionX;
    int positionY;
    int height;
    int width;
    int blockX;

    public character(int positionX, int positionY, int height, int width, int blockX){
        this.positionX = (int) Math.round(blockSize * positionX);
        this.positionY = (int) Math.round(blockSize * positionY);
        this.height = (int) Math.round(blockSize * height);
        this.width = (int) Math.round(blockSize * width);
        this.blockX = blockX;



    }
    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public int getRoundHeight(){
        return height;
    }

    public int getRoundedWidth(){
        return width;
    }

    public int getBlockX(){
        return blockX;
    }
}

