package ch.coldpixel.alpha.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.coldpixel.alpha.main.Main;
import com.badlogic.gdx.Files;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Main.WINDOW_WIDTH;
        config.height = Main.WINDOW_HEIGTH;
        config.title = Main.GAMENAME;
        config.resizable = Main.RESZIABLE;
        config.addIcon(Main.FAVICON, Files.FileType.Internal);
        if (Main.MAX_FPS) {
            //Shows the "real" fps, 0 disables throttling 
            config.vSyncEnabled = false;
            config.foregroundFPS = 0;
            config.backgroundFPS = 0;
        }
        new LwjglApplication(new Main(), config);
     
    }
}
