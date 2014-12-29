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

//==============================================================================
//Methods
//==============================================================================
    public Camera() {
        rotationSpeed = 0.5f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGTH);
        camera.update();
    }

    public void camUpdate(Batch batch) {
        handleInput();
        //Updates our OrthographicCamera after handleInput()!
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            camera.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }

    }
}
