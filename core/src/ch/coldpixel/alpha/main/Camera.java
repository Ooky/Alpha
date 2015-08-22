/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import static ch.coldpixel.alpha.main.Constants.WINDOW_HEIGTH;
import static ch.coldpixel.alpha.main.Constants.WINDOW_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Coldpixel
 */
public class Camera {

//==============================================================================
//Initialization
//==============================================================================
    //Camera
    private final OrthographicCamera camera;
    private final float rotationSpeed;
    //Idle-timer
    private static float WAIT_TIME;
    private float time;
    //Gravitation
    private float increasingFallSpeed;
    private boolean collides;
    private boolean sideCollidesLeft;
    private boolean sideCollidesRight;
    //Player
    Player player;
    //Camera Position( collision)
    private float xPosition;
    private float yPosition;
//==============================================================================
//Methods
//==============================================================================
    public Camera(float xPos, float yPos) {
        rotationSpeed = 0.5f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGTH);
        camera.update();
        player = new Player();
        //Idle-timer
        WAIT_TIME = 5f;
        time = 0f;
        //Gravitation
        increasingFallSpeed=player.getFallSpeed();
        collides = false;
        sideCollidesLeft=false;
        sideCollidesRight=false;
        //Camera Positon
        xPosition = xPos;
        yPosition = yPos;
    }

    public void camUpdate(Batch batch) {
        handleInput();
        gravity();
        //Updates our OrthographicCamera after handleInput()!
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void handleInput() {
//------------------------------------------------------------------------------
//Zoom
//------------------------------------------------------------------------------
        if (Gdx.input.isKeyPressed(Keys.R)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Keys.F)) {
            camera.zoom -= 0.02;
        }

//------------------------------------------------------------------------------
//PlayerMovement
//The Player actually doesnt move, it just moves the cam arround
//------------------------------------------------------------------------------
        time += Gdx.graphics.getDeltaTime();
        if (time >= WAIT_TIME) {
            player.setPlayerState(1);
            time -= WAIT_TIME;
        } else if (leftOrA()) {
            if (isRunning()) {
                if(!sideCollidesLeft){runLeft();}
                player.setPlayerState(3);
            } else {
               if(!sideCollidesLeft){walkLeft();}
                player.setPlayerState(3);
            }
            time = 0;
        } else if (rightOrD()) {
            if (isRunning()) {
                if(!sideCollidesRight){runRight();}
                player.setPlayerState(2);
            } else {
                if(!sideCollidesRight){walkRight();}
                player.setPlayerState(2);
            }
            time = 0;
        } else {
            if (player.getPlayerState() != 1)
            player.setPlayerState(0);
        }

      /*  if (Gdx.input.isKeyPressed(Keys.S)) {
            camera.translate(0, -3);
            this.setyPosition(this.getyPosition()-3);
            player.setPlayerState(0);
            time = 0;
        }*/
        if(Gdx.input.isKeyPressed(Keys.SPACE) && increasingFallSpeed<=500) {
            camera.translate(0, 10);
            this.setyPosition(this.getyPosition()+10);
            player.setPlayerState(0);
            time = 0;
        }

//------------------------------------------------------------------------------
//Rotation
//------------------------------------------------------------------------------
        if (Gdx.input.isKeyPressed(Keys.E)) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Keys.Q)) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }
        
//------------------------------------------------------------------------------
//Combat Clickfunction
//------------------------------------------------------------------------------
        //should check if players combat function is activ and change
        //the animation to the standard animation
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(player.getPlayerState() == 1){
                player.setPlayerState(0);
                time = 0;
            }
        }        
    }
//------------------------------------------------------------------------------
//Gravity 
//------------------------------------------------------------------------------
private void gravity(){
    //Pseudo collision
    if(this.getyPosition()<=(WINDOW_HEIGTH/2)-(player.getPlayerHeight()/2)-400){
        player.setIsFalling(false);
        increasingFallSpeed=player.getFallSpeed();
        player.death();
    }else{
        player.setIsFalling(true);
    }
    if(!collides /*|| (sideCollidesLeft && !collides) || (sideCollidesRight && !collides)*/){
        //gravity movment
        if(player.getIsFalling()){
            player.setPlayerState(4);
            camera.translate(0, -increasingFallSpeed * Gdx.graphics.getDeltaTime());
            this.setyPosition(this.getyPosition()-increasingFallSpeed * Gdx.graphics.getDeltaTime());
            //Maximum fallspeed
            if(increasingFallSpeed<=500){
                increasingFallSpeed=increasingFallSpeed*player.getFallSpeedMultiplier();
            }
        }
    }else{
        increasingFallSpeed = player.getFallSpeed();
    }
}
//==============================================================================
//Keycode
//==============================================================================
    private boolean isRunning() {
        return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
    }

    private boolean leftOrA() {
        return Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT);
    }

    private boolean rightOrD() {
        return Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT);
    }

    public void walkLeft() {
        camera.translate(- player.getWalkSpeed() * Gdx.graphics.getDeltaTime(), 0);
        //if camera moves also change player position to know where the player actually is
        this.setxPosition(this.getxPosition() - player.getWalkSpeed() * Gdx.graphics.getDeltaTime());
    }

    public void runLeft() {
        camera.translate(- player.getRunSpeed() * Gdx.graphics.getDeltaTime(), 0);
        this.setxPosition(this.getxPosition() - player.getRunSpeed() * Gdx.graphics.getDeltaTime());
    }

    public void walkRight() {
        camera.translate(player.getWalkSpeed() * Gdx.graphics.getDeltaTime(), 0);
        this.setxPosition(this.getxPosition()+player.getWalkSpeed() * Gdx.graphics.getDeltaTime());
    }

    public void runRight() {
        camera.translate(player.getRunSpeed() * Gdx.graphics.getDeltaTime(), 0);
        this.setxPosition(this.getxPosition()+player.getRunSpeed() * Gdx.graphics.getDeltaTime());
    }
    
    //==============================================================================
    //Getter
    //==============================================================================

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public boolean getCollides() {
        return collides;
    }    
    
    public boolean getSideCollidesLeft() {
        return sideCollidesLeft;
    }    
    
    public boolean getSideCollidesRight() {
        return sideCollidesRight;
    }    
    
    //==============================================================================
    //Setter
    //==============================================================================

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }
    
    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setCollides(boolean collides) {
        this.collides = collides;
    }    
    
    public void setSideCollidesLeft(boolean sideCollidesLeft) {
        this.sideCollidesLeft = sideCollidesLeft;
    }    
    
    public void setSideCollidesRight(boolean sideCollidesRight) {
        this.sideCollidesRight = sideCollidesRight;
    }    
    
    public void translate(float x, float y){
        this.camera.translate(x,y);
    }
}
