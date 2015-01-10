/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Coldpixel
 */
public class Enemy {

//==============================================================================
//Initialization
//==============================================================================
    //PlayerSize
    final int enemyWidth;
    final int enemyHeight;
    //Coordinates
    private float enemyX;
    private float enemyY;
    //Texture
    Texture texture;
    //TextureRegion
    TextureRegion enemyTexture;
    //Movement
    private final float walkSpeed;
    private final float runSpeed;

//==============================================================================
//Methods
//==============================================================================
    public Enemy(float enemyX, float enemyY) {
        //PlayerSize
        this.enemyWidth = 32;
        this.enemyHeight = 64;
        //Texture
        texture = new Texture("Graphics/NPC/Enemy.png");
        enemyTexture = new TextureRegion(texture, 16, 16);
        //Position
        this.enemyX = enemyX;
        this.enemyY = enemyY;
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
    private boolean isRunning() {
        return Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT);
    }

    private boolean leftOrA() {
        return Gdx.input.isKeyPressed(Input.Keys.J);
    }

    private boolean rightOrD() {
        return Gdx.input.isKeyPressed(Input.Keys.L);
    }

    public void walkLeft() {
        this.setEnemyX(this.getEnemyX() - walkSpeed * Gdx.graphics.getDeltaTime());
    }

    public void runLeft() {
        this.setEnemyX(this.getEnemyX() - runSpeed * Gdx.graphics.getDeltaTime());
    }

    public void walkRight() {
        this.setEnemyX(this.getEnemyX() + walkSpeed * Gdx.graphics.getDeltaTime());
    }

    public void runRight() {
        this.setEnemyX(this.getEnemyX() + runSpeed * Gdx.graphics.getDeltaTime());
    }

//==============================================================================
//Getter
//==============================================================================
    public float getEnemyX() {
        return enemyX;
    }

    public float getEnemyY() {
        return enemyY;
    }

    public TextureRegion getEnemyTexture() {
        return enemyTexture;
    }

//==============================================================================
//Setter
//==============================================================================
    public void setEnemyX(float enemyX) {
        this.enemyX = enemyX;
    }

    public void setEnemyY(float enemyY) {
        this.enemyY = enemyY;
    }

}
