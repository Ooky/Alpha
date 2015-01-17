/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import static ch.coldpixel.alpha.main.Main.WINDOW_HEIGTH;
import static ch.coldpixel.alpha.main.Main.WINDOW_WIDTH;
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
    //Player
    Player player;

//==============================================================================
//Methods
//==============================================================================
    public Camera() {
        rotationSpeed = 0.5f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGTH);
        camera.update();
        player = new Player();
        //Idle-timer
        WAIT_TIME = 5f;
        time = 0f;
    }

    public void camUpdate(Batch batch) {
        handleInput();
        //Updates our OrthographicCamera after handleInput()!
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void handleInput() {
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
                runLeft();
                player.setPlayerState(3);
            } else {
                walkLeft();
                player.setPlayerState(3);
            }

            time = 0;
        } else if (rightOrD()) {
            if (isRunning()) {
                runRight();
                player.setPlayerState(2);
            } else {
                walkRight();
                player.setPlayerState(2);
            }

            time = 0;
        } else {
            if (player.getPlayerState() != 1)
            player.setPlayerState(0);
        }

        if (Gdx.input.isKeyPressed(Keys.S)) {
            camera.translate(0, -3, 0);
            player.setPlayerState(0);
            time = 0;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            camera.translate(0, 3, 0);
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
        //the animation to the starndard animation
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(player.getPlayerState() == 1){
                System.out.println("test");
                player.setPlayerState(0);
                time = 0;
            }
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
        camera.translate(player.getPlayerX() - player.getWalkSpeed() * Gdx.graphics.getDeltaTime(), 0, 0);
    }

    public void runLeft() {
        camera.translate(player.getPlayerX() - player.getRunSpeed() * Gdx.graphics.getDeltaTime(), 0, 0);
    }

    public void walkRight() {
        camera.translate(player.getPlayerX() + player.getWalkSpeed() * Gdx.graphics.getDeltaTime(), 0, 0);
    }

    public void runRight() {
        camera.translate(player.getPlayerX() + player.getRunSpeed() * Gdx.graphics.getDeltaTime(), 0, 0);
    }
}
