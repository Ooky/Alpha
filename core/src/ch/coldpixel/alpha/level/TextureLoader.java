/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Coldpixel
 */
public class TextureLoader {

//==============================================================================
//Initialization
//==============================================================================
    //Background
    private final Texture backgroundTexture;
    public final TextureRegion cloud;
    private final TextureRegion ground;
    private final TextureRegion groundTop;
    //Environment
    private final TextureRegion stairs;
    private final TextureRegion SpikeTrap;
    //Items
    private final Texture itemList;
    private final TextureRegion sword;
    //Terrain
    private final Texture terrain;
    private final TextureRegion surface0;
    private final TextureRegion surface1;
    private final TextureRegion surface2;
    private final TextureRegion surfaceToGround0;
    private final TextureRegion surfaceToGround1;
    private final TextureRegion surfaceToGround2;
    private final TextureRegion backSurfaceToGround0;
    private final TextureRegion ground0;
    private final TextureRegion ground1;
    private final TextureRegion ground2;
    private final TextureRegion backGround0;

//==============================================================================
//Methods
//==============================================================================
    public TextureLoader() {
        //Background
        backgroundTexture = new Texture(Gdx.files.internal("Graphics/Background/BackgroundRegion.png"));
        cloud = new TextureRegion(backgroundTexture, 0, 32, 16, 62);
        ground = new TextureRegion(backgroundTexture, 0, 16, 16, 16);
        groundTop = new TextureRegion(backgroundTexture, 0, 0, 16, 16);
        stairs = new TextureRegion(backgroundTexture, 16, 0, 16, 16);
        SpikeTrap = new TextureRegion(backgroundTexture, 16, 16, 16, 16);
        //Items
        itemList = new Texture(Gdx.files.internal("Graphics/Items/itemList.png"));
        sword = new TextureRegion(itemList, 0, 0, 24, 24);
        //Terrain
        terrain = new Texture(Gdx.files.internal("Graphics/Terrain/Terrain.png"));
        
        surface0 = new TextureRegion(terrain, 11, 11, 16, 16);
        surface1 = new TextureRegion(terrain, 27, 11, 16, 16);
        surface2 = new TextureRegion(terrain, 43, 11, 16, 16);
        
        surfaceToGround0 = new TextureRegion(terrain, 11, 27, 16, 16);
        surfaceToGround1 = new TextureRegion(terrain, 27, 27, 16, 16);
        surfaceToGround2 = new TextureRegion(terrain, 43, 27, 16, 16);
        
        backSurfaceToGround0 = new TextureRegion(terrain, 59, 27, 16, 16);
        
        ground0 = new TextureRegion(terrain, 11, 43, 16, 16);
        ground1 = new TextureRegion(terrain, 27, 43, 16, 16);
        ground2 = new TextureRegion(terrain, 43, 43, 16, 16);
        
        backGround0 = new TextureRegion(terrain, 59, 43, 16, 16);

    }

//==============================================================================
//Getter
//==============================================================================
    public TextureRegion getCloud() {
        return cloud;
    }

    public TextureRegion getGround() {
        return ground;
    }

    public TextureRegion getGroundTop() {
        return groundTop;
    }

    public TextureRegion getStairs() {
        return stairs;
    }

    public TextureRegion getSpikeTrap() {
        return SpikeTrap;
    }

    public TextureRegion getSword() {
        return sword;
    }

    public TextureRegion getSurface0() {
        return surface0;
    }

    public TextureRegion getSurface1() {
        return surface1;
    }

    public TextureRegion getSurface2() {
        return surface2;
    }

    public TextureRegion getGround0() {
        return ground0;
    }

    public TextureRegion getGround1() {
        return ground1;
    }

    public TextureRegion getGround2() {
        return ground2;
    }

    public TextureRegion getSurfaceToGround0() {
        return surfaceToGround0;
    }

    public TextureRegion getSurfaceToGround1() {
        return surfaceToGround1;
    }

    public TextureRegion getSurfaceToGround2() {
        return surfaceToGround2;
    }

    public TextureRegion getBackSurfaceToGround0() {
        return backSurfaceToGround0;
    }

    public TextureRegion getBackGround0() {
        return backGround0;
    }

}
