/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.npc;

import ch.coldpixel.alpha.main.Collision;
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
    private int direction;
    //Walk-timer
    private float WAIT_TIME;
    private float time=0f;
    //Collision
    private Collision collision;
//==============================================================================
//Methods
//==============================================================================
    public Enemy(float enemyX, float enemyY) {
        //PlayerSize
        this.enemyWidth = 16;
        this.enemyHeight = 16;
        //Texture
        texture = new Texture("Graphics/NPC/Enemy.png");
        enemyTexture = new TextureRegion(texture, enemyWidth, enemyHeight);
        //Position
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        //Movement
        this.walkSpeed = 300;
        this.runSpeed = (int) (walkSpeed * 1.5);
        this.direction = 1;
        //Walk-timer
        WAIT_TIME = 1.8f;
        time =0f;
        //Collision
        collision = new Collision(enemyX,enemyY,enemyWidth,enemyHeight, true);
    }

    public void update() {
        time+=Gdx.graphics.getDeltaTime();
        if(time >= WAIT_TIME){
            if(direction==1){
                direction=2;
            }else{
                direction=1;
            }
            time -= WAIT_TIME;
        }
       switch(direction){
           case 1:
               walkLeft();
               break;
           case 2:
               walkRight();
               break;
       }
      /*  if (leftOrA()) {
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
        */
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

    public Collision getCollision(){
        return collision;
    }
//==============================================================================
//Setter
//==============================================================================
    public void setEnemyX(float enemyX) {
        this.enemyX = enemyX;
        this.collision.setStartX(enemyX);
    }

    public void setEnemyY(float enemyY) {
        this.enemyY = enemyY;
        this.collision.setStartY(enemyY);
    }

    public void setWaitTimer(float waitTimer){
        this.WAIT_TIME = waitTimer;
    }
}
