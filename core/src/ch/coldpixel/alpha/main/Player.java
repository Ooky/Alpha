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
        this.playerWidth = 64;
        this.playerHeight = 32;
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
        //TextureRegion
        this.playerTexture = new TextureRegion(texture, this.playerX, this.playerY, playerWidth, playerHeight);
        //Position
        this.playerX = playerX;
        this.playerY = playerY;
        //Movement
        this.walkSpeed = 300;
        this.runSpeed = (int) (walkSpeed * 1.5);
        //Animation
        this.rows = 4;
        this.columns = 4;
        this.frameDuration =  0.2f;
        sheet = new Texture(Gdx.files.internal("Graphics/Player/playerSprite - Kopie.png"));
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / rows, sheet.getHeight() / columns);
        animFrames = new TextureRegion[rows * columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(frameDuration, animFrames);
        stateTime = 0f;

    }

    public void update() {
        //Animation
        setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
        setCurrentFrame(getAnimation().getKeyFrame(getStateTime(), true));
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

}
