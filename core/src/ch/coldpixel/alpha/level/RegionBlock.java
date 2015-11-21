/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ch.coldpixel.alpha.level.TextureLoader;
/**
 *
 * @author Mike
 */
public class RegionBlock {
    private boolean staticCamera;
    private TextureRegion texture;
    private int xStart;
    private int yStart;
    private int xTimes;
    private int yTimes;
    private int textureWidth;
    private int textureHeight;
    private boolean collide;
    private int function;

    public RegionBlock(boolean staticCamera, int textureNumber, int xStart, int yStart, int xTimes, int yTimes, int textureWidth, int textureHeight, boolean collide, int function) {
        this.staticCamera = staticCamera;       
        this.texture =  TextureLoader.getTextureRegion(textureNumber);
        this.xStart = xStart;
        this.yStart = yStart;
        this.xTimes = xTimes;
        this.yTimes = yTimes;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.collide = collide;
        this.function = function;
    }        
}
