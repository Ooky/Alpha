/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

import ch.coldpixel.alpha.main.Collision;
import ch.coldpixel.alpha.main.Main;
import ch.coldpixel.alpha.npc.Enemy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

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
    //Background
    private final TextureLoader textureLoader;
    private final TextureRegion cloud;
    private final TextureRegion ground;
    private final TextureRegion groundTop;
    //Spritebatch
    private final SpriteBatch batchDynamic;
    private final SpriteBatch batchStatic;
    //Enemy
    Enemy enemy;
    //Collision
    private List collisionArray;
    private boolean collide;
//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        //Levelsize
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        //Background
        textureLoader = new TextureLoader();
        cloud = textureLoader.getCloud();
        ground = textureLoader.getGround();
        groundTop = textureLoader.getGroundTop();
        //Spritebatch
        batchDynamic = new SpriteBatch();
        batchStatic = new SpriteBatch();
        //Enemy
        enemy = new Enemy(550, 48);
        //Collision
        collide=true;
        collisionArray = new ArrayList();
        collisionArray.add(new Collision(0,-30,16*Main.WINDOW_WIDTH / 16,32*Main.WINDOW_HEIGTH / 32));
    }

    public void drawLevel() {

//------------------------------------------------------------------------------
//Static Batch. This wont move when the player/cam moves
//Careful, ALL booleans MUST be true
//------------------------------------------------------------------------------
        //Background
        batchStatic.begin();
        //Fills the whole visible Window
        drawRegion(true, cloud, 0, -30, Main.WINDOW_WIDTH / 16, Main.WINDOW_HEIGTH / 32, 16, 32,collide);
        batchStatic.end();
//------------------------------------------------------------------------------
//Dynamic Batch. This will move when the player/cam moves
//Careful, ALL booleans MUST be false
//------------------------------------------------------------------------------
        batchDynamic.begin();
        //Background
        drawRegion(false, groundTop, 0, 32, 35, 1, 16, 16,collide);
        drawRegion(false, groundTop, 640, 32, 35, 1, 16, 16,collide);
        drawRegion(false, ground, 0, 0, 35, 2, 16, 16,collide);
        drawRegion(false, ground, 640, 0, 35, 2, 16, 16,collide);
        //Enemy
        drawRegion(false, enemy.getEnemyTexture(), (int) enemy.getEnemyX(), (int) enemy.getEnemyY(), 1, 1, 16, 16,collide);
        batchDynamic.end();
        //Update
        enemy.update();
        collide=false;
    }
    
    //Parameter: Texture, StartPositionX, StartPositionY, Repeat X, Repeat Y, Texture width, Texture height
    public void drawRegion(boolean staticCamera, TextureRegion texture, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight, boolean collide) {
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
        if(collide){
            collisionArray.add(new Collision(xStart,yStart,textureWidth*xTimes,textureHeight*yTimes));  
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

    public List getCollisionArray() {
        return collisionArray;
    }
}
