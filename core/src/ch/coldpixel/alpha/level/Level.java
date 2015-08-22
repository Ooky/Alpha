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
    private final TextureLoader tl;
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
    private final ArrayList<Integer> arrRandomSurface0;
    private final ArrayList<Integer> arrRandomSurface1;

//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        //Levelsize
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        //TextureLoader
        tl = new TextureLoader();
        //Surface
        arrSurface = new TextureRegion[3];
        arrSurface[0] = tl.getSurface0();
        arrSurface[1] = tl.getSurface1();
        arrSurface[2] = tl.getSurface2();
        //SurfaceToGround
        arrSurfaceToGround = new TextureRegion[3];
        arrSurfaceToGround[0] = tl.getSurfaceToGround0();
        arrSurfaceToGround[1] = tl.getSurfaceToGround1();
        arrSurfaceToGround[2] = tl.getSurfaceToGround2();
        //Spritebatch
        batchDynamic = new SpriteBatch();
        batchStatic = new SpriteBatch();
        //Enemy
        enemy = new Enemy(250, 32);
        enemy.setWaitTimer(0.8f);
        enemy2 = new Enemy(2320, 32);
        enemy2.setWaitTimer(2f);
        //Collision
        collisionArray = new ArrayList();
        EnemyArray = new ArrayList();
        EnemyArray.add(enemy);
        EnemyArray.add(enemy2);
        //Random
        rnd = new Random();
        arrRandomSurface0 = new ArrayList<Integer>();
        arrRandomSurface1 = new ArrayList<Integer>();
        //Adds  numbers from 0-3 to arrRandom
        for (int i = 0; i < (35 * 16); i += 16) {
            arrRandomSurface0.add(rnd.nextInt(3) + 0);
        }
        for (int i = 0; i < (40 * 16); i += 16) {
            arrRandomSurface1.add(rnd.nextInt(3) + 0);
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
        drawRegion(true, tl.getCloud(), 0, -30, WINDOW_WIDTH / 16, WINDOW_HEIGTH / 32, 16, 32, false, false);
        batchStatic.end();
//------------------------------------------------------------------------------
//Dynamic Batch. This will move when the player/cam moves
//Careful, ALL booleans MUST be false(only first)
//------------------------------------------------------------------------------
        batchDynamic.begin();
//------LEFT
        //Background
        drawRegion(false, tl.getBackGround0(), 0, 32, 30, 10, 16, 16, false, false);
        //Surface
        for (int i = 0; i < arrRandomSurface0.size(); i++) {
            //Generates surface, based on random generated numbers in arrRandom
            drawRegion(false, arrSurface[arrRandomSurface0.get(i)], i * 16, 32, 1, 1, 16, 16, false, false);
        }
        //->this 2 lines are redudant, the foor loop would be enough IF collision = true
        //but if you set collision = true, then it bugs, so i draw first a texture to make
        //sure it collides, then draw my original random texture over it
        //needs to be fixed

        //Surface to Ground
        drawRegion(false, tl.getEmptyTexture(), 0, 28, 560, 1, 1, 1, true, false);
        for (int i = 0; i < arrRandomSurface0.size(); i++) {
            drawRegion(false, arrSurfaceToGround[arrRandomSurface0.get(i)], i * 16, 16, 1, 1, 16, 16, false, false);
        }
        //Ground
        drawRegion(false, tl.getGround0(), 0, 0, 35, 1, 16, 16, false, false);
        drawRegion(false, tl.getGround0(), 0, -16, 35, 1, 16, 16, false, false);

        //Enemy
        drawRegion(false, enemy.getEnemyTexture(), (int) enemy.getEnemyX(), (int) enemy.getEnemyY(), 1, 1, 16, 16, false, false);

//------RIGHT
        drawRegion(false, tl.getSurface0(), 760, 32, 2, 1, 16, 16, false, false);
        drawRegion(false, tl.getSurface1(), 792, 32, 2, 1, 16, 16, false, false);
        drawRegion(false, tl.getSurface0(), 824, 32, 2, 1, 16, 16, false, false);
        drawRegion(false, tl.getSurface2(), 856, 32, 2, 1, 16, 16, false, false);

        drawRegion(false, tl.getEmptyTexture(), 760, 28, 1600, 1, 1, 1, true, false);
        drawRegion(false, tl.getSurfaceToGround0(), 760, 16, 2, 1, 16, 16, false, false);
        drawRegion(false, tl.getSurfaceToGround1(), 792, 16, 2, 1, 16, 16, false, false);
        drawRegion(false, tl.getSurfaceToGround2(), 824, 16, 2, 1, 16, 16, false, false);
        drawRegion(false, tl.getSurfaceToGround0(), 856, 16, 2, 1, 16, 16, false, false);
//        
        drawRegion(false, tl.getGround0(), 760, -16, 100, 2, 16, 16, true, true);

        drawRegion(false, tl.getGround0(), 888, 16, 52, 1, 16, 16, false, false);

        //Stairs
        drawRegion(false, tl.getStairs0(), 888, 32, 20, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 952, 64, 16, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1016, 96, 12, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1080, 128, 8, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1144, 160, 4, 2, 16, 16, true, false);
        //Spike Trap
        drawRegion(false, tl.getSpikeTrap0(), 1208, 32, 12, 1, 16, 16, true, true);
        //Stairs
        drawRegion(false, tl.getStairs0(), 1400, 32, 20, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1400, 64, 16, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1400, 96, 12, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1400, 128, 8, 2, 16, 16, true, false);
        drawRegion(false, tl.getStairs0(), 1400, 160, 4, 2, 16, 16, true, false);

        for (int i = 0; i < arrRandomSurface1.size(); i++) {
            //Generates surface, based on random generated numbers in arrRandom
            drawRegion(false, arrSurface[arrRandomSurface1.get(i)], i * 16 + 1720, 32, 1, 1, 16, 16, false, false);
        }
        //Surface to Ground
        for (int i = 0; i < arrRandomSurface1.size(); i++) {
            drawRegion(false, arrSurfaceToGround[arrRandomSurface1.get(i)], i * 16+1720, 16, 1, 1, 16, 16, false, false);
        }

        //Enemy
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
