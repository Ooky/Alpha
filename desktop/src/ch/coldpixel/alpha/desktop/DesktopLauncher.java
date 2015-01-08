package ch.coldpixel.alpha.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.coldpixel.alpha.main.MainMenu;
import com.badlogic.gdx.Files;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = MainMenu.WINDOW_WIDTH;
        config.height = MainMenu.WINDOW_HEIGTH;
        config.title = MainMenu.GAMENAME;
        config.resizable = MainMenu.RESZIABLE;
        config.addIcon(MainMenu.FAVICON, Files.FileType.Internal);
        if (MainMenu.MAX_FPS) {
            //Shows the "real" fps, 0 disables throttling 
            config.vSyncEnabled = false;
            config.foregroundFPS = 0;
            config.backgroundFPS = 0;
        }
        new LwjglApplication(new MainMenu(), config);
     
    }
}
