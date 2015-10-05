/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Mike
 */
public class Destination {
    //Coordinates
    private float destinationX;
    private float destinationY;
    private float destinationWidth;
    private float destinationHeight;
    private TextureRegion destinationTexture;
    public Destination(float x, float y, float width, float height, TextureRegion texture){
        destinationX=x;
        destinationY=y;
        destinationWidth=width;
        destinationHeight=height;
        destinationTexture = texture;
    }

    public float getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(float destinationX) {
        this.destinationX = destinationX;
    }

    public float getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(float destinationY) {
        this.destinationY = destinationY;
    }

    public float getDestinationWidth() {
        return destinationWidth;
    }

    public void setDestinationWidth(float destinationWidth) {
        this.destinationWidth = destinationWidth;
    }

    public float getDestinationHeight() {
        return destinationHeight;
    }

    public void setDestinationHeight(float destinationHeight) {
        this.destinationHeight = destinationHeight;
    }    

    public TextureRegion getDestinationTexture() {
        return destinationTexture;
    }

    public void setDestinationTexture(TextureRegion destinationTexture) {
        this.destinationTexture = destinationTexture;
    }
    
}
