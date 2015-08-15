package ch.coldpixel.alpha.desktop;

import ch.coldpixel.alpha.main.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.coldpixel.alpha.main.GameStart;
import com.badlogic.gdx.Files;

public class DesktopLauncher {

    public static void main(String[] arg) {

        LwjglApplicationConfiguration config;
        config = new LwjglApplicationConfiguration();

        config.width = Constants.WINDOW_WIDTH;
        config.height = Constants.WINDOW_HEIGTH;
        config.title = Constants.GAMENAME;
        config.resizable = Constants.RESZIABLE;
        config.addIcon(Constants.FAVICON, Files.FileType.Internal);

        if (Constants.MAX_FPS) {
            //Shows the "real" fps, 0 disables throttling 
            config.vSyncEnabled = false;
            config.foregroundFPS = 0;
            config.backgroundFPS = 0;
        }
        new LwjglApplication(new GameStart(), config);
    }
}
