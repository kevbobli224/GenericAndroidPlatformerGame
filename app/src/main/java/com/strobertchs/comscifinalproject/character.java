package com.strobertchs.comscifinalproject;

/**
 * Created by Gilb on 1/11/2017.
 */

public class character{
    //constructor for new characters gives rounded block values when x,y,height,width values are entered
    //THIS IS ALL WE NEEDED TO DO CHARLES NOT MAKE 1000000 VARIABLES AHHHHHHHHHHH - Gilbert
    //gilbert u do realize we still have the same number of variables...
    //Hi, I'm helping -Darius
    private int positionX;
    private int positionY;
    private int height;
    private int width;
    private int blockHeight;
    private int blockWidth;
    private int blockX;
    private int blockY;

    public character(int blockX, int blockY, int height, int width, double blockSize){
        this.positionX = (int) Math.round(blockSize * blockX);
        this.positionY = (int) Math.round(blockSize * blockY);
        this.height = (int) Math.round(blockSize * height);
        this.width = (int) Math.round(blockSize * width);
        this.blockHeight = height;
        this.blockWidth = width;
        this.blockX = blockX;
        this.blockY = blockY;

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



    public void setPositionX(int newPositionX){
        this.positionX = newPositionX;
    }
    public void setPositionY(int newPositionY){
        this.positionY = newPositionY;
    }
    public void setRoundedHeight(int newRoundedHeight){
        this.height = newRoundedHeight;
    }
    public void setRoundedWidth(int newRoundedWidth){
        this.width = newRoundedWidth;
    }
    public void setBlockHeight(int newBlockHeight){
        this.blockHeight = newBlockHeight;
    }
    public void setBlockWidth(int newBlockWidth){
        this.blockWidth = newBlockWidth;
    }
    public void setBlockX(int newBlockX){
        this.blockX = newBlockX;
    }
    public void setBlockY(int newBlockY){
        this.blockY = newBlockY;
    }
}

