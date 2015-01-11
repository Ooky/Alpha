package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.Level;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *
 * @author Coldpixel
 */
public class MainMenu extends ApplicationAdapter implements Screen {

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
    //MainMenu
    private MainMenu menu;
    //TextButton
    TextButton buttonPlay;
    //Skin
    private Skin skin;
    //Stage
    private Stage stage;
    //Table
    private Table table;


//==============================================================================
//Methods
//==============================================================================
    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("Graphics/MenuSkins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("Graphics/MenuSkins/uiskin.atlas")));

        buttonPlay = new TextButton("Hi", skin);
        stage = new Stage();
        table = new Table();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        level.getBatchStatic().dispose();
        level.getBatchDynamic().dispose();
        batch.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {

    }

    @Override
    public void hide() {

    }
}
