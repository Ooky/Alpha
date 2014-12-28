package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.graphics.icon.Icon;
import ch.coldpixel.alpha.graphics.player.Player;
import com.badlogic.gdx.ApplicationAdapter;
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
    private static Icon ICON;
    public static final String FAVICON = ICON.getFAVICON();
    //Player
    private Player player = new Player();
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
        //FPS
        fps = new FPSLogger();
        showFPS = false;
        //Spritebatch
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        batch.begin();
        //Animation
        player.getSpriteBatch().begin();
        player.getSpriteBatch().draw(player.getPlayerTexture(0, 0, 32, 64), 0, 0);
        player.getSpriteBatch().end();
        batch.end();
        //FPS
        if (showFPS) {
            fps.log();
        }
    }
}
