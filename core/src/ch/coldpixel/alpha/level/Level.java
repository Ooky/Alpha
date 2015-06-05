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
    //Environment
    private final TextureRegion stairs;
    private final TextureRegion spikeTrap;
    //Spritebatch
    private final SpriteBatch batchDynamic;
    private final SpriteBatch batchStatic;
    //Enemy
    Enemy enemy;
    //Collision
    private List collisionArray;
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
        stairs = textureLoader.getStairs();
        spikeTrap = textureLoader.getSpikeTrap();
        //Spritebatch
        batchDynamic = new SpriteBatch();
        batchStatic = new SpriteBatch();
        //Enemy
        enemy = new Enemy(550, 48);
        //Collision
        collisionArray = new ArrayList();
    }

    public void drawLevel() {

//------------------------------------------------------------------------------
//Static Batch. This wont move when the player/cam moves
//Careful, First booleans MUST be true
//------------------------------------------------------------------------------
        //Background
        batchStatic.begin();
        //Fills the whole visible Window
        drawRegion(true, cloud, 0, -30, Main.WINDOW_WIDTH / 16, Main.WINDOW_HEIGTH / 32, 16, 32,false, false);
        batchStatic.end();
//------------------------------------------------------------------------------
//Dynamic Batch. This will move when the player/cam moves
//Careful, ALL booleans MUST be false
//------------------------------------------------------------------------------
        batchDynamic.begin();
        //Background
        drawRegion(false, groundTop, 0, 32, 35, 1, 16, 16,true, false);
        drawRegion(false, groundTop, 760, 32, 100, 1, 16, 16,true, false);
        drawRegion(false, ground, 0, 0, 35, 2, 16, 16,true, false);
        drawRegion(false, ground, 760, 0, 100, 2, 16, 16,true, false);
        //Drawing Stairs
        drawRegion(false, stairs, 888, 48, 20, 2, 16, 16,true, false);
        drawRegion(false, stairs, 952, 80, 16, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1016, 112, 12, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1080, 144, 8, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1144, 176, 4, 2, 16, 16,true, false);
        //Drawing Spike Trap
        drawRegion(false, spikeTrap, 1208, 48, 12, 1, 16, 16,true, true);
        //Drawing Stairs
        drawRegion(false, stairs, 1388, 48, 20, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1388, 80, 16, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1388, 112, 12, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1388, 144, 8, 2, 16, 16,true, false);
        drawRegion(false, stairs, 1388, 176, 4, 2, 16, 16,true, false);
        //Enemy
        drawRegion(false, enemy.getEnemyTexture(), (int) enemy.getEnemyX(), (int) enemy.getEnemyY(), 1, 1, 16, 16,false, false);
        batchDynamic.end();
        //Update
        enemy.update();
    }
    
    //Parameter: Texture, StartPositionX, StartPositionY, Repeat X, Repeat Y, Texture width, Texture height, add region to collider
    public void drawRegion(boolean staticCamera, TextureRegion texture, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight, boolean collide, boolean deadly) {
        if(collide){
            collisionArray.add(new Collision(xStart,yStart,textureWidth*xTimes,textureHeight*yTimes, deadly));  
        }
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

    public List getCollisionArray() {
        return collisionArray;
    }
}
