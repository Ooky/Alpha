package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.graphics.icon.Icon;
import ch.coldpixel.alpha.graphics.player.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    //Spritebatch
    private SpriteBatch batch;
    //FPS
    FPSLogger fps;
    Boolean showFPS;

//==============================================================================
//Methods
//==============================================================================
    @Override
    public void create() {
        player = new Player();
        //FPS
        fps = new FPSLogger();
        showFPS = false;
        //Spritebatch
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        //Clear the Screen
        Gdx.gl.glClearColor(0, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //Animation
        batch.draw(player.getPlayerTexture(0, 0, 64, 32), 100, 100);
        batch.end();
        //FPS
        if (showFPS) {
            fps.log();
        }
    }
}
