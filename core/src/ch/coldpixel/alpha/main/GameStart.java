package ch.coldpixel.alpha.main;

import com.badlogic.gdx.Game;

public class GameStart extends Game {

    @Override
    public void create() {
        this.setScreen(new Menu(this));
    }

    @Override
    public void render() {
        super.render(); // important!
        //Without this call, the Screen that you set in the create() method will not be rendered!
    }
}
