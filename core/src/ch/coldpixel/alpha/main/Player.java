/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.TextureLoader;
import static ch.coldpixel.alpha.main.Constants.WINDOW_WIDTH;
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
    //Gravitation
    private final float fallSpeed;
    private final float fallSpeedMultiplier;
    private boolean isFalling;
    //SpriteBatch
    SpriteBatch batch;
    //SwordSwing 
    //could be deleted 17.01.2015 11:11
    Boolean isSwinging;
    int swordRadiusRight;
    int swordRadiusLeft;
    int swingSpeed;
    int swordSide;
    
//==============================================================================
//Methods
//==============================================================================
    public Player() {
        //PlayerSize
        this.playerWidth = 48;
        this.playerHeight = 16;
        //Movement
        this.walkSpeed = 400;
        this.runSpeed = (int) (walkSpeed * 1.5);
        //Gravitation
        this.fallSpeed = 1f;
        this.fallSpeedMultiplier = 1.5f;
        this.isFalling = true;
        //Animation
        this.rows = 4;
        this.columns = 4;
        stateTime = 0f;
        this.playerStateOld = 9999999;//Necessary for the first Animation
        //TextureLoader
        textureLoader = new TextureLoader();
        //SpriteBatch
        batch = new SpriteBatch();
        //SwordSwing
        isSwinging = false;
        swordRadiusRight = 0;
        swordRadiusLeft = 90;
        swingSpeed = -5;
        swordSide = 40;
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
                //Walk right
                case 2:
                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/luca_walkright.png")), 2, 1, 0.3f);
                    playerStateOld = 1;
                    break;
                //Walk left
                case 3:
                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/luca_walkleft.png")), 2, 1, 0.3f);
                    playerStateOld = 1;
                    break;
                //Jump
                case 4:
                    //Resets the Animation to the first frame if Space was just pressed
                    if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                        setStateTime(0);
                    }
                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/jump2.png")), 2, 3, 0.15f);
                    playerStateOld = 1;
                    break;
                //death
                case 20:
                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/death.png")),2,2,0.15f);
                    break;
                //DefaultAnimation = Animation between movement and idle
                default:
                    changeAnimation(new Texture(Gdx.files.internal("Graphics/Player/idle.png")), 2, 1, 0.5f);
                    playerStateOld = 0;
                    break;
            }
        }
        setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
        setCurrentFrame(getAnimation().getKeyFrame(getStateTime(), true));
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

    public void combat() {
        //Mouse Event Handling
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !isSwinging) {
//            isSwinging = true;
            batch.begin();
            //textureregion, floatx, floaty, rotationpointx, rotationpointy, width, height, scalex, scaley, rotation, clockwise
            if (Gdx.input.getX() >= (WINDOW_WIDTH / 2)) {//Mouse is in the right half
                swingSpeed = -5;
                swordSide = playerWidth - 10;
                batch.draw(textureLoader.getSword(), getPlayerX() + swordSide, getPlayerY() + 15, 0, 0, 16, 16, 3, 3, swordRadiusRight);
                swordRadiusRight += swingSpeed;
                if (swordRadiusRight <= -90) {
                    swordRadiusRight = 0;
                }
            } else {//Mouse is in the left half
                swingSpeed = 5;
                swordSide = 10;
                batch.draw(textureLoader.getSword(), getPlayerX() + swordSide, getPlayerY() + 15, 0, 0, 16, 16, 3, 3, swordRadiusLeft);
                swordRadiusLeft += swingSpeed;
                if (swordRadiusLeft >= 180) {
                    swordRadiusLeft = 90;
                }
            }
//            isSwinging = false;
            batch.end();
        }
    }

    //Death
    public void death(){
        playerState = 20;
    };
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

    public float getFallSpeed() {
        return fallSpeed;
    }

    public float getFallSpeedMultiplier() {
        return fallSpeedMultiplier;
    }

    public boolean getIsFalling() {
        return isFalling;
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

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

}
