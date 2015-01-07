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
    private final TextureRegion cloud;
    private final TextureRegion ground;
    private final TextureRegion groundTop;

//==============================================================================
//Methods
//==============================================================================
    public TextureLoader() {
        //Background
        backgroundTexture = new Texture(Gdx.files.internal("Graphics/Background/BackgroundRegion.png"));
        cloud = new TextureRegion(backgroundTexture, 0, 32, 16, 62);
        ground = new TextureRegion(backgroundTexture, 0, 16, 16, 16);
        groundTop = new TextureRegion(backgroundTexture, 0, 0, 16, 16);
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

}
