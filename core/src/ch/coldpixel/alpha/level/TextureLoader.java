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
 * @author Mike
 */
public class TextureLoader {

    private TextureRegion region;
    private Texture texture;
    private TextureRegion ground;
    private TextureRegion groundTop;

    
//==============================================================================
//Methods
//==============================================================================
//Implements our File in the Program as a Texture
//Set the spezific Region in our File on a variable
public TextureLoader() {
        texture = new Texture(Gdx.files.internal("Graphics/Background/BackgroundRegion.png"));

        ground = new TextureRegion(texture,0, 16, 16, 16);
                
        groundTop = new TextureRegion(texture,0, 0, 16, 16);
        
    }

    //Gets a spezific Region for an Object

    public TextureRegion getGround() {
        return ground;
    }
    
        public TextureRegion getGroundTop() {
        return groundTop;
    }
    
}
