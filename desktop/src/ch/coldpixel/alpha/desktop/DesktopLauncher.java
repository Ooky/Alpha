package ch.coldpixel.alpha.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.coldpixel.alpha.main.Main;
import ch.coldpixel.alpha.main.MainMenu;
import com.badlogic.gdx.Files;

public class DesktopLauncher {

    public static void main(String[] arg) {

        LwjglApplicationConfiguration config;
        LwjglApplication application;
        MainMenu mainMenu;
        Main main;

        config = new LwjglApplicationConfiguration();
        mainMenu = new MainMenu();
        main = new Main();

        Boolean chooseMenu = false;

        //MainMenu
        if (chooseMenu) {
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
            application = new LwjglApplication(mainMenu, config);

            //Main
        } else {
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
            application = new LwjglApplication(main, config);
        }

    }
}
