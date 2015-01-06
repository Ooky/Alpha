/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Mike
 */
public class Player {

//==============================================================================
//Initialization
//==============================================================================
    //PlayerSize
    final int playerWidth;
    final int playerHeight;
    //Texture
    Texture texture;
    //TextureRegion
    TextureRegion playerTexture;
    //Coordinates
    private float playerX;
    private float playerY;
    //Movement
    private final float walkSpeed;
    private final float runSpeed;
    //Camera
    Camera cam;

//==============================================================================
//Methods
//==============================================================================
    public Player() {
        //PlayerSize
        this.playerWidth = 32;
        this.playerHeight = 64;
        //Movement
        this.walkSpeed = 300;
        this.runSpeed = (int) (walkSpeed * 1.5);
    }
    public Player(float playerX, float playerY) {
        //PlayerSize
        this.playerWidth = 32;
        this.playerHeight = 64;
        //Texture
        texture = new Texture(Gdx.files.internal("Graphics/Player/player.png"));
//        TextureRegion
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerTexture = new TextureRegion(texture, this.playerX, this.playerY, playerWidth, playerHeight);
        //Movement
        this.walkSpeed = 300;
        this.runSpeed = (int) (walkSpeed * 1.5);
        this.cam = new Camera();
    }

    public void update() {
        //Need to add PlayerAnimation here
        //Movement is in Camera
    }

//==============================================================================
//Getter
//==============================================================================
    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public float getRunSpeed() {
        return runSpeed;
    }

//==============================================================================
//Setter
//==============================================================================
    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }
}
