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
    private final int playerWidth;
    private final int playerHeight;
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

//==============================================================================
//Methods
//==============================================================================
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
    }

    public void update() {
        if (leftOrA()) {
            if (isRunning()) {
                runLeft();
            } else {
                walkLeft();
            }
        }
        if (rightOrD()) {
            if (isRunning()) {
                runRight();
            } else {
                walkRight();
            }
        }
    }

//==============================================================================
//Keycode
//==============================================================================
    private boolean leftOrA() {
        return Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT);
    }

    private boolean rightOrD() {
        return Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT);
    }

    private boolean isRunning() {
        return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
    }

    private void runLeft() {
        this.setPlayerX(this.getPlayerX() - runSpeed * Gdx.graphics.getDeltaTime());
    }

    private void walkLeft() {
        this.setPlayerX(this.getPlayerX() - walkSpeed * Gdx.graphics.getDeltaTime());
    }

    private void runRight() {
        this.setPlayerX(this.getPlayerX() + runSpeed * Gdx.graphics.getDeltaTime());
    }

    private void walkRight() {
        this.setPlayerX(this.getPlayerX() + walkSpeed * Gdx.graphics.getDeltaTime());
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
