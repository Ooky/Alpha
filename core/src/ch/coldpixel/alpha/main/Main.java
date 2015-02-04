package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.Level;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import java.util.List;

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
    //CollisionList
    private List collisionArray;

//==============================================================================
//Methods
//==============================================================================
    @Override
    public void create() {

        //Player
        player = new Player();//Necessary to calculate the center below
        player.setPlayerX((WINDOW_WIDTH/2)-(player.getPlayerWidth()/2));
        player.setPlayerY((WINDOW_HEIGTH/2)-(player.getPlayerHeight()/2));
       // player = new Player((WINDOW_WIDTH/2)-(player.getPlayerWidth()/2), (WINDOW_HEIGTH/2)-(player.getPlayerHeight()/2));
        //FPS
        fps = new FPSLogger();
        showFPS = false;
        //Spritebatch
        batch = new SpriteBatch();
        //Camera
        cam = new Camera();
        //Level
        level = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
        collisionArray = level.getCollisionArray();
    }

    @Override
    public void render() {
        //Update the Camera
        cam.camUpdate(level.getBatchDynamic());
        //Clear the Screen
        Gdx.gl.glClearColor(255, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        level.drawLevel();
        //Player
        //Sets playerState of the player
        //from the playerState of the cam
        player.setPlayerState(cam.player.getPlayerState());
        player.update();
        //Batchdrawing
        batch.begin();
        batch.draw(player.getCurrentFrame(),  player.getPlayerX(), player.getPlayerY());
        batch.end();
        //Combat
        player.combat();
        for (Iterator<Collision> iter = collisionArray.iterator(); iter.hasNext(); ) {
            Collision element = iter.next();
            System.out.println(cam.getxPosition());
            System.out.println(cam.getyPosition());
        }
        //FPS
        if (showFPS) {
            fps.log();
        }
    }

    @Override
    public void dispose() {
        level.getBatchStatic().dispose();
        level.getBatchDynamic().dispose();
        batch.dispose();
    }
}
