/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

/**
 *
 * @author Mike
 */
public class Collision {
//==============================================================================
//Initialization
//==============================================================================
    //Start Coordinates
    private  float startX;
    private  float startY;
    private final float startWidth;
    private final float startHeight;
    private final boolean deadly;
//==============================================================================
//Constructor
//==============================================================================
    public Collision(float x, float y, float width, float height, boolean deadly){
        startX=x;
        startY=y;
        startWidth=width;
        startHeight=height;
        this.deadly=deadly;
    }
//==============================================================================
//Getter
//==============================================================================

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }  

    public float getStartWidth() {
        return startWidth;
    }

    public float getStartHeight() {
        return startHeight;
    }

    public boolean getDeadly() {
        return deadly;
    }    
    
//==============================================================================
//Setter
//==============================================================================
    
     public void setStartX(float startX) {
        this.startX = startX;
    }
     
     public void setStartY(float startY) {
        this.startY = startY;
    }
}
