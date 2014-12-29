package ch.coldpixel.alpha.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    //Player
    private Player player;
    //Camera
    private Camera cam;
    //Spritebatch
    private SpriteBatch batch;
    //FPS
    FPSLogger fps;
    Boolean showFPS;
    //Shaperenderer
    ShapeRenderer shape;
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
        //Shaperenderer
        shape = new ShapeRenderer();
    }

    @Override
    public void render() {
        //Update the Camera
        cam.camUpdate(batch);
        //Clear the Screen
        Gdx.gl.glClearColor(0, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background();
        //Batchdrawing
        batch.begin();
        batch.draw(player.texture, player.getPlayerX(), player.getPlayerY());
        batch.end();
        //PlayerUpdate
        player.update();
        //FPS
        if (showFPS) {
            fps.log();
        }
    }

    @Override
    public void dispose() {
        shape.dispose();
        batch.dispose();
    }

    public void background() {
        //Shapedrawing
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(100, 200, 800, -200, Color.RED, Color.YELLOW, Color.YELLOW, Color.RED);
        shape.end();

    }
}
