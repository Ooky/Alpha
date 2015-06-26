package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.Level;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import java.util.List;
import ch.coldpixel.alpha.npc.Enemy;

/**
 *
 * @author Coldpixel
 */
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
    //CollisionList
    private List<Collision> collisionArray;
    //EnemyList
    private List<Enemy> EnemyList;
    boolean collides = false;

//==============================================================================
//Methods
//==============================================================================
    @Override
    public void create() {

        //Player
        player = new Player();//Necessary to calculate the center below
        player.setPlayerX((WINDOW_WIDTH/2)-(player.getPlayerWidth()/2));
        player.setPlayerY((WINDOW_HEIGTH/2)-(player.getPlayerHeight()/2));
       // player = new Player((WINDOW_WIDTH/2)-(player.getPlayerWidth()/2), (WINDOW_HEIGTH/2)-(player.getPlayerHeight()/2));
        //FPS
        fps = new FPSLogger();
        showFPS = false;
        //Spritebatch
        batch = new SpriteBatch();
        //Camera
        cam = new Camera((player.getPlayerX()+player.getPlayerWidth()/2),(player.getPlayerY()+player.getPlayerHeight()/2));
        //Level
        level = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
        collisionArray = level.getCollisionArray();
        EnemyList = level.getEnemyArray();
    }

    @Override
    public void render() {
        //Update the Camera
        cam.camUpdate(level.getBatchDynamic());
        //Clear the Screen
        Gdx.gl.glClearColor(255, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //player is dead
        if(player.getPlayerState()==20){
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
        batch.draw(player.getCurrentFrame(),  player.getPlayerX(), player.getPlayerY());
        batch.end();
        //Combat
        player.combat();
        collides= false;
        //Standard and trap collision
        for (Iterator<Collision> iter = collisionArray.iterator(); iter.hasNext(); ) {
            Collision element = iter.next();  
            if((cam.getxPosition()-(player.getPlayerWidth()/2)) < (element.getStartX()+element.getStartWidth())  
                    && (cam.getxPosition()+(player.getPlayerWidth()/2)) > element.getStartX()
                    && (cam.getyPosition()+(player.getPlayerHeight()/2)) > element.getStartY()
                    && (cam.getyPosition()-(player.getPlayerHeight()/2) < (element.getStartY()+element.getStartHeight()))){
                collides = true;
                if(element.getDeadly()){
                    player.death();
                }
            }
        }
        //Enemy collision
        for (Iterator<Enemy> iter = EnemyList.iterator(); iter.hasNext(); ) {
            Collision element = iter.next().getCollision();  
            if((cam.getxPosition()-(player.getPlayerWidth()/2)) < (element.getStartX()+element.getStartWidth())  
                    && (cam.getxPosition()+(player.getPlayerWidth()/2)) > element.getStartX()
                    && (cam.getyPosition()+(player.getPlayerHeight()/2)) > element.getStartY()
                    && (cam.getyPosition()-(player.getPlayerHeight()/2) < (element.getStartY()+element.getStartHeight()))){
                collides = true;
                if(element.getDeadly()){
                    player.death();
                }
            }
        }
        if(collides){
            cam.setCollides(true);
        }else{
            cam.setCollides(false);
        }
        //FPS
        if (showFPS) {
            fps.log();
        }
    }

    @Override
    public void dispose() {
        level.getBatchStatic().dispose();
        level.getBatchDynamic().dispose();
        batch.dispose();
    }
    
    public void collision(){
    
    }
}
