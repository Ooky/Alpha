package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.Level;

import ch.coldpixel.alpha.level.TextureLoader;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Coldpixel
 */
public class Main extends ApplicationAdapter {

//==============================================================================
//Initialization
//==============================================================================
    //DesktopLauncher
    public static final boolean RESZIABLE = false;
    public static final boolean MAX_FPS = false;
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGTH = 512 + 128;
    public static final String GAMENAME = "Coldpixel - Alpha";
    public static final String FAVICON = Icon.getFAVICON();
    private Player player;
    //Camera
    private Camera cam;
    //Spritebatch
    private SpriteBatch batch;
    //FPS
    FPSLogger fps;
    Boolean showFPS;
    //Level
    Level level;

//==============================================================================
//Methods
//==============================================================================
    @Override
    public void create() {

        //Player
        player = new Player(100, 200);
        //FPS
        fps = new FPSLogger();
        showFPS = false;
        //Spritebatch
        batch = new SpriteBatch();
        //Camera
        cam = new Camera();
        //Level
        level = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
    }

    @Override
    public void render() {
        //Update the Camera
        cam.camUpdate(level.getBatchDynamic());
        //Clear the Screen
        Gdx.gl.glClearColor(255, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        level.drawLevel();
        //Batchdrawing
        batch.begin();
        batch.draw(player.texture, player.getPlayerX(), player.getPlayerY());
        player.setStateTime(player.getStateTime() + Gdx.graphics.getDeltaTime());
        player.setCurrentFrame(player.getAnimation().getKeyFrame(player.getStateTime(), true));
        batch.draw(player.getCurrentFrame(),  player.getPlayerX(), player.getPlayerY());
        batch.end();
        //FPS
        if (showFPS) {
            fps.log();
        }
    }

    @Override
    public void dispose() {
        level.getBatchStatic().dispose();
        level.getBatchDynamic().dispose();
        player.texture.dispose();
        batch.dispose();
    }
}
