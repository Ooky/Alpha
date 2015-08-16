package ch.coldpixel.alpha.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu implements Screen {

//==============================================================================
//Initialization
//==============================================================================
    private final GameStart game;
    private final Stage stage;
    private final Table table;
    private final Skin skin;
    private final TextButton buttonPlay;
    private final TextButton buttonExit;
    private final Label title;

//==============================================================================
//Methods
//==============================================================================
    public Menu(final GameStart passedGame) {
        game = passedGame;
        stage = new Stage();
        table = new Table();
        skin = new Skin(Gdx.files.internal("Skins/menuSkin.json"), new TextureAtlas(Gdx.files.internal("Skins/menuSkin.pack")));
        buttonPlay = new TextButton("Play", skin);
        buttonExit = new TextButton("Exit", skin);
        title = new Label("Alpha", skin);

        title.setFontScale(2);
        //Linear Filtering = On; => Font = Smoother
        skin.getFont("font").getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 1f, 1f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        //Title Animation, starts with fadig in
        title.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        //Bouncing title
        title.addAction(Actions.sequence(Actions.delay(0.4f), Actions.moveBy(0, -260, 1f, Interpolation.exp10In)));
        title.addAction(Actions.sequence(Actions.delay(1.4f), Actions.moveBy(0, +130, 0.8f, Interpolation.exp10Out)));
        title.addAction(Actions.sequence(Actions.delay(2.0f), Actions.moveBy(0, -130, 0.6f, Interpolation.exp10In)));
        title.addAction(Actions.sequence(Actions.delay(2.6f), Actions.moveBy(0, +65, 0.8f, Interpolation.exp10Out)));
        title.addAction(Actions.sequence(Actions.delay(3.2f), Actions.moveBy(0, -65, 0.6f, Interpolation.exp10In)));
        title.addAction(Actions.sequence(Actions.delay(3.8f), Actions.moveBy(0, +33, 0.6f, Interpolation.exp10Out)));
        title.addAction(Actions.sequence(Actions.delay(4.2f), Actions.moveBy(0, -33, 0.4f, Interpolation.exp10In)));
        title.addAction(Actions.sequence(Actions.delay(4.6f), Actions.moveBy(0, +15, 0.4f, Interpolation.exp10Out)));
        title.addAction(Actions.sequence(Actions.delay(4.8f), Actions.moveBy(0, -15, 0.2f, Interpolation.exp10In)));
        title.addAction(Actions.sequence(Actions.delay(5f), Actions.moveBy(0, +7, 0.2f, Interpolation.exp10Out)));
        //Flashing Button
        buttonPlay.addAction(Actions.sequence(Actions.delay(1.4f), Actions.color(new Color(255, 255, 255, 0f)), Actions.fadeOut(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(1.45f), Actions.color(new Color(255, 255, 255, 0.5f)), Actions.fadeIn(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(2.6f), Actions.color(new Color(255, 255, 255, 0f)), Actions.fadeOut(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(2.65f), Actions.color(new Color(255, 255, 255, 0.5f)), Actions.fadeIn(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(3.8f), Actions.color(new Color(255, 255, 255, 0f)), Actions.fadeOut(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(3.85f), Actions.color(new Color(255, 255, 255, 0.5f)), Actions.fadeIn(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(4.6f), Actions.color(new Color(255, 255, 255, 0f)), Actions.fadeOut(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(4.65f), Actions.color(new Color(255, 255, 255, 0.5f)), Actions.fadeIn(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(5f), Actions.color(new Color(255, 255, 255, 0f)), Actions.fadeOut(0.05f)));
        buttonPlay.addAction(Actions.sequence(Actions.delay(5.05f), Actions.color(new Color(255, 255, 255, 0.5f)), Actions.fadeIn(0.05f)));
        //Label rotation&Scale/size aren't working
        //https://code.google.com/p/libgdx/issues/detail?id=889
        
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Main(game));
                //Dont call dispose();, its going to call itseld within hide();
            }
        });
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(title).padBottom(250).row();
        table.add(buttonPlay).size(200, 80).padBottom(20).row();
        table.add(buttonExit).size(200, 80).padBottom(40).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

}
