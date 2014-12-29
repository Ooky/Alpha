/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.graphics.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Mike
 */
public class Player {

//==============================================================================
//Initialization
//==============================================================================
    Texture texture;
    TextureRegion playerTexture;
    private final SpriteBatch spriteBatch;

//==============================================================================
//Methods
//==============================================================================
    public Player() {
        texture = new Texture(Gdx.files.internal("Graphics/Player/player.png"));
        this.playerTexture = new TextureRegion();
        spriteBatch = new SpriteBatch();
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getPlayerTexture(int xStart, int yStart, int width, int height) {
        return playerTexture = new TextureRegion(texture, xStart, yStart, width, height);
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
