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
public class Background {
    private TextureRegion region;
    private Texture texture;
    
    public Background(String texturePath,int startX,int startY,int widthX,int widthY, boolean collides){
        texture = new Texture(Gdx.files.internal(texturePath));
        region = new TextureRegion(texture, startX, startY, widthX, widthY);
    }

    public TextureRegion getRegion() {
        return region;
    }
}
