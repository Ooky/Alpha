/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Coldpixel
 */
public class Level {

//==============================================================================
//Initialization
//==============================================================================
    //Levelsize
    private final int levelWidth;
    private final int levelHeight;
    //Textures
    private final TextureLoader textureLoader;
    private final TextureRegion cloud;
    private final TextureRegion ground;
    private final TextureRegion groundTop;
    //Spritebatch
    private final SpriteBatch batchDynamic;
    private final SpriteBatch batchStatic;

//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        //Levelsize
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        //Textures
        textureLoader = new TextureLoader();
        cloud = textureLoader.getCloud();
        ground = textureLoader.getGround();
        groundTop = textureLoader.getGroundTop();
        //Spritebatch
        batchDynamic = new SpriteBatch();
        batchStatic = new SpriteBatch();
    }

    public void drawLevel() {

//------------------------------------------------------------------------------
//Static Batch. This wont move when the player/cam moves
//Careful, ALL booleans MUST be true
//------------------------------------------------------------------------------
        //Background
        batchStatic.begin();
        drawRegion(true, cloud, 0, 0, levelWidth / 16, levelHeight / 16, 16, 16);
        batchStatic.end();
        batchDynamic.begin();
//------------------------------------------------------------------------------
//Static Batch. This wont move when the player/cam moves
//Careful, ALL booleans MUST be false
//------------------------------------------------------------------------------
        //Background
        drawRegion(false, groundTop, 0, 32, 35, 1, 16, 16);
        drawRegion(false, groundTop, 640, 32, 35, 1, 16, 16);
        drawRegion(false, ground, 0, 0, 35, 2, 16, 16);
        drawRegion(false, ground, 640, 0, 35, 2, 16, 16);
        batchDynamic.end();
    }

    //Parameter: Texture, StartPositionX, StartPositionY, Repeat X, Repeat Y, Texture width, Texture height
    public void drawRegion(boolean staticCamera, TextureRegion texture, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight) {
        int oldXStart = xStart;
        int oldYStart = yStart;
        //The Amount of Times a Texture is drawn.
        while (yStart < oldYStart + yTimes * textureHeight) {
            while (xStart < oldXStart + xTimes * textureWidth) {
                if (staticCamera) {
                    batchStatic.draw(texture, xStart, yStart);
                    xStart = xStart + textureWidth;
                } else {
                    batchDynamic.draw(texture, xStart, yStart);
                    xStart = xStart + textureWidth;
                }
            }
            xStart = oldXStart;
            yStart = yStart + textureHeight;
        }

    }
//==============================================================================
//Getter
//==============================================================================

    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }

    public SpriteBatch getBatchDynamic() {
        return batchDynamic;
    }
    public SpriteBatch getBatchStatic() {
        return batchStatic;
    }
    
}
