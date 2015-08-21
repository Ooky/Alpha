/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

import ch.coldpixel.alpha.main.Collision;
import static ch.coldpixel.alpha.main.Constants.WINDOW_HEIGTH;
import static ch.coldpixel.alpha.main.Constants.WINDOW_WIDTH;
import ch.coldpixel.alpha.npc.Enemy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    //TextureLoader
    private final TextureLoader textureLoader;
    //Surface
    private final TextureRegion arrSurface[];
    //SurfaceToGround
    private final TextureRegion arrSurfaceToGround[];
    //Spritebatch
    private final SpriteBatch batchDynamic;
    private final SpriteBatch batchStatic;
    //Enemy
    Enemy enemy;
    Enemy enemy2;
    //Collision
    private final List collisionArray;
    private Boolean levelIsDrawn = false;
    private final List EnemyArray;
    //Random
    private final Random rnd;
    private final ArrayList<Integer> arrRandom;

//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        //Levelsize
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        //TextureLoader
        textureLoader = new TextureLoader();
        //Surface
        arrSurface = new TextureRegion[3];
        arrSurface[0] = textureLoader.getSurface0();
        arrSurface[1] = textureLoader.getSurface1();
        arrSurface[2] = textureLoader.getSurface2();
        //SurfaceToGround
        arrSurfaceToGround = new TextureRegion[3];
        arrSurfaceToGround[0] = textureLoader.getSurfaceToGround0();
        arrSurfaceToGround[1] = textureLoader.getSurfaceToGround1();
        arrSurfaceToGround[2] = textureLoader.getSurfaceToGround2();
        //Spritebatch
        batchDynamic = new SpriteBatch();
        batchStatic = new SpriteBatch();
        //Enemy
        enemy = new Enemy(250, 32);
        enemy.setWaitTimer(0.8f);
        enemy2 = new Enemy(2320, 48);
        enemy2.setWaitTimer(2f);
        //Collision
        collisionArray = new ArrayList();
        EnemyArray = new ArrayList();
        EnemyArray.add(enemy);
        EnemyArray.add(enemy2);
        //Random
        rnd = new Random();
        arrRandom = new ArrayList<Integer>();
        for (int i = 0; i < (35 * 16); i += 16) {
            //Adds  numbers from 0-3 to arrRandom
            arrRandom.add(rnd.nextInt(3) + 0);
        }
    }

    public void drawLevel() {

//------------------------------------------------------------------------------
//Static Batch. This wont move when the player/cam moves
//Careful, First booleans MUST be true
//------------------------------------------------------------------------------
        //Background
        batchStatic.begin();
        //Fills the whole visible Window
        drawRegion(true, textureLoader.getCloud(), 0, -30, WINDOW_WIDTH / 16, WINDOW_HEIGTH / 32, 16, 32, false, false);
        batchStatic.end();
//------------------------------------------------------------------------------
//Dynamic Batch. This will move when the player/cam moves
//Careful, ALL booleans MUST be false(only first)
//------------------------------------------------------------------------------
        batchDynamic.begin();
        //Terrain old
        drawRegion(false, textureLoader.getGroundTop(), 760, 32, 100, 1, 16, 16, true, false);
        drawRegion(false, textureLoader.getGround(), 760, 0, 100, 2, 16, 16, true, false);
        //Terrain new
        //->this 2 lines are redudant, the foor loop would be enough IF collision = true
        //but if you set collision = true, then it bugs, so i draw first a texture to make
        //sure it collides, then draw my original random texture over it
        //needs to be fixed
        drawRegion(false, textureLoader.getSurfaceToGround0(), 0, 16, 35, 1, 16, 16, true, false);
        for (int i = 0; i < arrRandom.size(); i++) {
            drawRegion(false, arrSurfaceToGround[arrRandom.get(i)], i * 16, 16, 1, 1, 16, 16, false, false);
        }
        drawRegion(false, textureLoader.getGround0(), 0, 0, 35, 1, 16, 16, false, false);
        drawRegion(false, textureLoader.getGround0(), 0, -16, 35, 1, 16, 16, false, false);
        //Background
        drawRegion(false, textureLoader.getBackGround0(), 0, 32, 30, 10, 16, 16, false, false);
        //Surface
        for (int i = 0; i < arrRandom.size(); i++) {
            //Generates surface, based on random generated numbers in arrRandom
            drawRegion(false, arrSurface[arrRandom.get(i)], i * 16, 32, 1, 1, 16, 16, false, false);
        }
        //Drawing Stairs
        drawRegion(false, textureLoader.getStairs(), 888, 48, 20, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 952, 80, 16, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1016, 112, 12, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1080, 144, 8, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1144, 176, 4, 2, 16, 16, true, false);
        //Drawing Spike Trap
        drawRegion(false, textureLoader.getSpikeTrap(), 1208, 48, 12, 1, 16, 16, true, true);
        //Drawing Stairs
        drawRegion(false, textureLoader.getStairs(), 1388, 48, 20, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1388, 80, 16, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1388, 112, 12, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1388, 144, 8, 2, 16, 16, true, false);
        drawRegion(false, textureLoader.getStairs(), 1388, 176, 4, 2, 16, 16, true, false);
        //Enemy
        drawRegion(false, enemy.getEnemyTexture(), (int) enemy.getEnemyX(), (int) enemy.getEnemyY(), 1, 1, 16, 16, false, false);
        drawRegion(false, enemy2.getEnemyTexture(), (int) enemy2.getEnemyX(), (int) enemy2.getEnemyY(), 1, 1, 16, 16, false, false);
        levelIsDrawn = true;
        batchDynamic.end();
        //Update
        enemy.update();
        enemy2.update();
    }

    //Parameter: Texture, StartPositionX, StartPositionY, Repeat X, Repeat Y, Texture width, Texture height, add region to collider
    public void drawRegion(boolean staticCamera, TextureRegion texture, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight, boolean collide, boolean deadly) {
        if (collide && !levelIsDrawn) {
            collisionArray.add(new Collision(xStart, yStart, textureWidth * xTimes, textureHeight * yTimes, deadly));
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

    public List getEnemyArray() {
        return EnemyArray;
    }
}
