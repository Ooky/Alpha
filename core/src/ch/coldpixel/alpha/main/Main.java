package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.Level;
import static ch.coldpixel.alpha.main.Constants.WINDOW_HEIGTH;
import static ch.coldpixel.alpha.main.Constants.WINDOW_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import java.util.List;
import ch.coldpixel.alpha.npc.Enemy;
import com.badlogic.gdx.Screen;

/**
 *
 * @author Coldpixel
 */
public class Main implements Screen {

//==============================================================================
//Initialization
//==============================================================================   
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
    //CollisionList
    private List<Collision> collisionArray;
    //EnemyList
    private List<Enemy> EnemyList;
    //collision
    boolean collides = false;
    boolean sideCollides=false;
    final GameStart game;

    public Main(final GameStart passed_game) {
        game = passed_game;
    }

//==============================================================================
//Methods
//==============================================================================
    public void create() {
        //Player
        player = new Player();//Necessary to calculate the center below
        player.setPlayerX((WINDOW_WIDTH / 2) - (player.getPlayerWidth() / 2));
        player.setPlayerY((WINDOW_HEIGTH / 2) - (player.getPlayerHeight() / 2));
        // player = new Player((WINDOW_WIDTH/2)-(player.getPlayerWidth()/2), (WINDOW_HEIGTH/2)-(player.getPlayerHeight()/2));
        //FPS
        fps = new FPSLogger();
        showFPS = false;
        //Spritebatch
        batch = new SpriteBatch();
        //Camera
        cam = new Camera((player.getPlayerX() + player.getPlayerWidth() / 2), (player.getPlayerY() + player.getPlayerHeight() / 2));
        //Level
        level = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
        collisionArray = level.getCollisionArray();
        EnemyList = level.getEnemyArray();
    }

    @Override
    public void render(float f) {
        //Update the Camera
        cam.camUpdate(level.getBatchDynamic());
        //Clear the Screen
        Gdx.gl.glClearColor(255, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //player is dead
        if (player.getPlayerState() == 20) {
            this.create();
        }
        level.drawLevel();
        //Player
        //Sets playerState of the player
        //from the playerState of the cam
        player.setPlayerState(cam.player.getPlayerState());
        player.update();
        //Batchdrawing
        batch.begin();
        batch.draw(player.getCurrentFrame(), player.getPlayerX(), player.getPlayerY());
        batch.end();
        //Combat
        player.combat();
        collision();
    }

    @Override
    public void dispose() {
        level.getBatchStatic().dispose();
        level.getBatchDynamic().dispose();
        batch.dispose();
    }

    public void collision() {
        collides = false;
        //Standard and trap collision
        for (Iterator<Collision> iter = collisionArray.iterator(); iter.hasNext();) {
            Collision element = iter.next();
            if ((cam.getxPosition() - (player.getPlayerWidth() / 2)) < (element.getStartX() + element.getStartWidth())
                    && (cam.getxPosition() + (player.getPlayerWidth() / 2)) > element.getStartX()
                    && (cam.getyPosition() + (player.getPlayerHeight() / 2)) > element.getStartY()
                    && (cam.getyPosition() - (player.getPlayerHeight() / 2) < (element.getStartY() + element.getStartHeight()))) {                
                sideCollides=false;
                //Collision right of the Player
                if (cam.getxPosition()+player.getPlayerWidth()/2 <= 20+(element.getStartX())) {
                    cam.translate(element.getStartX()-player.getPlayerWidth()/2-5 - cam.getxPosition(),0 );
                    cam.setxPosition(element.getStartX()-player.getPlayerWidth()/2-5);
                    sideCollides=true;
                }
                //Collision left of the Player
                else if (cam.getxPosition()-player.getPlayerWidth()/2-1 >= (element.getStartX()+element.getStartWidth())-20) {
                    cam.translate(element.getStartX()+element.getStartWidth()+player.getPlayerWidth()/2+5 - cam.getxPosition(),0 );
                    cam.setxPosition(element.getStartX()+element.getStartWidth()+player.getPlayerWidth()/2+5);
                    sideCollides=true;
                }
                //Collision under the Player
                else if (cam.getyPosition()-player.getPlayerHeight()/2 <= (element.getStartY() + element.getStartHeight())) {
                    cam.translate(0, (player.getPlayerHeight()/2)+(element.getStartY() + element.getStartHeight())-1 - cam.getyPosition());
                    cam.setyPosition((player.getPlayerHeight()/2)+(element.getStartY() + element.getStartHeight())-1);
                    collides = true;
                }
                if (element.getDeadly()) {
                    player.death();
                }
            }
        }
        //Enemy collision
        for (Iterator<Enemy> iter = EnemyList.iterator(); iter.hasNext();) {
            Collision element = iter.next().getCollision();
            if ((cam.getxPosition() - (player.getPlayerWidth() / 2)) < (element.getStartX() + element.getStartWidth())
                    && (cam.getxPosition() + (player.getPlayerWidth() / 2)) > element.getStartX()
                    && (cam.getyPosition() + (player.getPlayerHeight() / 2)) > element.getStartY()
                    && (cam.getyPosition() - (player.getPlayerHeight() / 2) < (element.getStartY() + element.getStartHeight()))) {
                collides = true;
                if (element.getDeadly()) {
                    player.death();
                }
            }
        }
        if (collides) {
            cam.setCollides(true);
        } else {
            cam.setCollides(false);
        }
        if (sideCollides) {
            cam.setSideCollides(true);
        } else {
            cam.setSideCollides(false);
        }
        //FPS
        if (showFPS) {
            fps.log();
        }
    }

    @Override
    public void show() {
        create();
    }

    @Override
    public void resize(int i, int i1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
