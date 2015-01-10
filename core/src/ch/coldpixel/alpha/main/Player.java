/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
    //Texture
    Texture texture;
    Texture sheet;
    //TextureRegion
    TextureRegion playerTexture;
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
    //Coordinates
    private float playerX;
    private float playerY;
    //Movement
    private final float walkSpeed;
    private final float runSpeed;

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
        this.frameDuration = 0.3f;
        stateTime = 0f;
    }

    public void update() {
        //Animation
        switch (getPlayerState()) {
            //IdleAnimation after 5 seconds
            case 1:
                changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/playerIdleAnimation.png")), 4, 4);
                break;
            //DefaultAnimation = Animation between movement and idle
            default:
                changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/defaultAnimation.png")), 4, 4);
                break;
        }
        setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
        setCurrentFrame(getAnimation().getKeyFrame(getStateTime(), true));
    }

    public void changeAnimation(Texture texture, int rows, int columns) {
        sheet = texture;
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / rows, sheet.getHeight() / columns);
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
