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
    //Surface
    protected final TextureRegion arrSurface[];
    //SurfaceToGround
    protected final TextureRegion arrSurfaceToGround[];
    //Spritebatch
    protected final SpriteBatch batchDynamic;
    protected final SpriteBatch batchStatic;
    //Collision
    protected final List collisionArray;
    protected Boolean levelIsDrawn = false;
    protected final List EnemyArray;
    //Random
    protected final Random rnd;
    protected final ArrayList<Integer> arrRandomSurface0;
    protected final ArrayList<Integer> arrRandomSurface1;

//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        //Levelsize
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        //Surface
        arrSurface = new TextureRegion[3];
        //SurfaceToGround
        arrSurfaceToGround = new TextureRegion[3];
        //Spritebatch
        batchDynamic = new SpriteBatch();
        batchStatic = new SpriteBatch();
        //Collision
        collisionArray = new ArrayList();
        EnemyArray = new ArrayList();
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
   
    //Parameter: Texture, StartPositionX, StartPositionY, Repeat X, Repeat Y, Texture width, Texture height, add region to collider
    public void drawRegion(boolean staticCamera, TextureRegion texture, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight, boolean collide, int function) {
        if (collide && !levelIsDrawn) {
            collisionArray.add(new Collision(xStart, yStart, textureWidth * xTimes, textureHeight * yTimes, function));
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

    public TextureRegion[] getArrSurface() {
        return arrSurface;
    }

    public TextureRegion[] getArrSurfaceToGround() {
        return arrSurfaceToGround;
    }

    public Boolean getLevelIsDrawn() {
        return levelIsDrawn;
    }

    public Random getRnd() {
        return rnd;
    }

    public ArrayList<Integer> getArrRandomSurface1() {
        return arrRandomSurface1;
    }

    public ArrayList<Integer> getArrRandomSurface0() {
        return arrRandomSurface0;
    }

    public void setLevelIsDrawn(Boolean levelIsDrawn) {
        this.levelIsDrawn = levelIsDrawn;
    }
    
    public void addEnemy(Enemy enemy){
        this.EnemyArray.add(enemy);
    }
    
    
}
