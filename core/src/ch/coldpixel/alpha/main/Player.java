/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Coldpixel
 */
public class Player {

//==============================================================================
//Initialization
//==============================================================================
    //PlayerSize
    final int playerWidth;
    final int playerHeight;
    //TextureLoader
    TextureLoader textureLoader;
    //Texture
    Texture sheet;
    //TextureRegion
    TextureRegion[] animFrames;
    //Animation
    int rows;
    int columns;
    float frameDuration;//=AnimationSpeed
    Animation animation;
    TextureRegion currentFrame;
    float stateTime;
    //PlayerState
    private int playerState;
    private int playerStateOld;
    //Coordinates
    private float playerX;
    private float playerY;
    //Movement
    private final float walkSpeed;
    private final float runSpeed;
    //SpriteBatch
    SpriteBatch batch;
    //Timer
    private static float WAIT_TIME;
    private float time;
    Boolean isSwinging;

//==============================================================================
//Methods
//==============================================================================
    public Player() {
        //PlayerSize
        this.playerWidth = 32;
        this.playerHeight = 64;
        this.walkSpeed = 300;
        this.runSpeed = (int) (walkSpeed * 1.5);
        //Animation
        this.rows = 4;
        this.columns = 4;
        stateTime = 0f;
        this.playerStateOld = 9999999;//Necessary for the first Animation
        //TextureLoader
        textureLoader = new TextureLoader();
        //SpriteBatch
        batch = new SpriteBatch();
        //Time
        WAIT_TIME = 2f;
        time = 0f;
        isSwinging = false;

    }

    public void update() {
        //Animation
        if (playerState != playerStateOld) {
            switch (getPlayerState()) {
                //IdleAnimation after 5 seconds
                case 1:

                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/idle_long.png")), 2, 2, 0.8f);
                    playerStateOld = 1;

                    break;
                //WALK RIGHT
                case 2:

                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/luca_walkright.png")), 2, 1, 0.3f);
                    playerStateOld = 1;
                    break;
                //WALK LEFT
                case 3:

                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/luca_walkleft.png")), 2, 1, 0.3f);
                    playerStateOld = 1;
                    break;
                //DefaultAnimation = Animation between movement and idle
                default:
                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/idle.png")), 2, 1, 0.3f);

                    playerStateOld = 0;
                    break;
            }
        }
        setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
        setCurrentFrame(getAnimation().getKeyFrame(getStateTime(), true));
    }

    public void combat() {
        //Mouse Event Handling
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !isSwinging) {
            isSwinging = true;
            batch.begin();
            for (int i = 0; i < 80; i += 10) {
                time += Gdx.graphics.getDeltaTime();
                if (time >= WAIT_TIME) {
                    time -= WAIT_TIME;
                    //textureregion, floatx, floaty, rotationpointx, rotationpointy, width, height, scalex, scaley, rotation 
                    batch.draw(textureLoader.getSword(), getPlayerX() + 40, getPlayerY() + 15, 0, 0, 16, 16, 3, 3, i);
                }
            }
            isSwinging = false;
            batch.end();
        }
    }

    public void changeAnimation(Texture texture, int columns, int rows, float frameDuration) {
        sheet = texture;
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / columns, sheet.getHeight() / rows);
        animFrames = new TextureRegion[rows * columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(frameDuration, animFrames);
        setAnimation(animation);
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

    public Animation getAnimation() {
        return animation;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public float getStateTime() {
        return stateTime;
    }

    public int getPlayerState() {
        return playerState;
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

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setPlayerState(int playerState) {
        this.playerState = playerState;
    }
}
