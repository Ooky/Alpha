/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import static ch.coldpixel.alpha.main.Main.WINDOW_HEIGTH;
import static ch.coldpixel.alpha.main.Main.WINDOW_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *
 * @author Ooky
 */
public class Camera {

//==============================================================================
//Initialization
//==============================================================================
    //Camera
    private final OrthographicCamera camera;
    private final float rotationSpeed;
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

        if (Gdx.input.isKeyPressed(Keys.S)) {
            camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            camera.translate(0, 3, 0);
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
