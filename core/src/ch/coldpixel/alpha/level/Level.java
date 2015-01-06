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
 * @author Ooky
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
    private final TextureRegion ground;
    private final TextureRegion groundTop;
    //Spritebatch
    private final SpriteBatch batch;

//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        //Levelsize
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        //Textures
        textureLoader = new TextureLoader();
        ground = textureLoader.getGround();
        groundTop = textureLoader.getGroundTop();
        //Spritebatch
        batch = new SpriteBatch();
    }

    public void drawLevel() {
        batch.begin();
        //Background
        drawRegion(groundTop, 0, 32, 35, 1, 16, 16);
        drawRegion(groundTop, 640, 32, 35, 1, 16, 16);
        drawRegion(ground, 0, 0, 35, 2, 16, 16);
        drawRegion(ground, 640, 0, 35, 2, 16, 16);
        batch.end();
    }

    //Parameter: Texture, StartPositionX, StartPositionY, Repeat X, Repeat Y, Texture width, Texture height
    public void drawRegion(TextureRegion texture, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight) {
        int oldXStart = xStart;
        int oldYStart = yStart;
        //The Amount of Times a Texture is drawn.
        while (yStart < oldYStart + yTimes * textureHeight) {
            while (xStart < oldXStart + xTimes * textureWidth) {
                batch.draw(texture, xStart, yStart);
                xStart = xStart + textureWidth;
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

    public SpriteBatch getBatch() {
        return batch;
    }
}
