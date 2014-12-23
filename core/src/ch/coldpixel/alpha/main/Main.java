package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.graphics.icon.Icon;
import com.badlogic.gdx.ApplicationAdapter;

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
    }

    @Override
    public void render() {
        //FPS
        if (showFPS) {
            fps.log();
        }
    }
}
