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
    private Background groundTop;
    private Background ground;
    private SpriteBatch batch;
//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        batch = new SpriteBatch();
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        groundTop = new Background("Graphics/Background/BackgroundRegion.png",0,0,16,16,false);
        ground= new Background("Graphics/Background/BackgroundRegion.png",0,16,16,16,false);
    }
    public void drawLevel(){
        batch.begin();
        //draw Ground
        drawRegion(ground.getRegion(),0,0,35,2,16,16);
        drawRegion(ground.getRegion(),640,0,35,2,16,16);
        //draw GroundTop
        drawRegion(groundTop.getRegion(),0,32,35,1,16,16);
        drawRegion(groundTop.getRegion(),640,32,35,1,16,16);
        batch.end();
    }
    //Drwas a Region of Textures
    //Parameter: Texture, StartPositionX, StartPositionY, Howmany times draw in x Direction, How many times draw in y Direction, Texture width, Texture height
    public void drawRegion(TextureRegion texture,int xStart,int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight){
        int oldXStart=xStart;
        int oldYStart=yStart;
        while(yStart<oldYStart+yTimes*textureHeight){
            while(xStart<oldXStart+xTimes*textureWidth){
                System.out.println("xStart:"+xStart);                
                batch.draw(texture, xStart, yStart);
                xStart=xStart+textureWidth;
            }
            System.out.println("yStart:"+yStart);
            xStart=oldXStart;
            yStart=yStart+textureHeight;
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
}