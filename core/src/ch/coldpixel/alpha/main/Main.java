package ch.coldpixel.alpha.main;

import ch.coldpixel.alpha.level.Destination;
import ch.coldpixel.alpha.level.Level;
import ch.coldpixel.alpha.level.ReadCSV;
import ch.coldpixel.alpha.level.TextureLoader;
import static ch.coldpixel.alpha.main.Constants.WINDOW_HEIGTH;
import static ch.coldpixel.alpha.main.Constants.WINDOW_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import java.util.List;
import ch.coldpixel.alpha.npc.Enemy;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

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
    Level actualLevel;
    Level level1;
    Level level2;
    int level = 1;
    ReadCSV csv = new ReadCSV();
    ArrayList<String> csvArray;
    //CollisionList
    private List<Collision> collisionArray;
    //EnemyList
    private List<Enemy> EnemyList;
    //collision
    boolean collides = false;
    boolean sideCollidesLeft=false;
    boolean sideCollidesRight=false;
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
        switch(level){
            case 1:
                    level1 = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
                    collisionArray = level1.getCollisionArray();
                    csvArray = csv.read("Level/level1.csv");
                    //Enemy
                    Enemy enemy = new Enemy(250, 32);
                    enemy.setWaitTimer(0.8f);
                    Enemy enemy2 = new Enemy(2320, 32);
                    enemy2.setWaitTimer(2f);
                    level1.addEnemy(enemy);
                    level1.addEnemy(enemy2);
                    EnemyList = level1.getEnemyArray();       
                    actualLevel = level1;
                break;
            case 2:
                    level2 = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
                    collisionArray = level2.getCollisionArray();
                    csvArray = csv.read("Level/level2.csv");
                    //Enemy
                    enemy = new Enemy(500, 32);
                    enemy.setWaitTimer(0.8f);
                    enemy2 = new Enemy(2320, 32);
                    enemy2.setWaitTimer(2f);
                    level2.addEnemy(enemy);
                    level2.addEnemy(enemy2);
                    EnemyList = level2.getEnemyArray();
                    actualLevel = level2;
                break;
            default:
                    level1 = new Level(WINDOW_WIDTH * 3, WINDOW_HEIGTH);
                    collisionArray = level1.getCollisionArray();
                    EnemyList = level1.getEnemyArray();
                    actualLevel = level1;
                break;
        }
    }

    @Override
    public void render(float f) {
        //Update the Camera
        cam.camUpdate(actualLevel.getBatchDynamic());
        //Clear the Screen
        Gdx.gl.glClearColor(255, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //player is dead
        if (player.getPlayerState() == 20) {
            this.create();
        }
        //actualLevel.drawLevel();
        
                //------------------------------------------------------------------------------
                //Static Batch. This wont move when the player/cam moves
                //Careful, First booleans MUST be true
                //------------------------------------------------------------------------------
                        //Background
                        SpriteBatch batchStatic = actualLevel.getBatchStatic();
                        actualLevel.getBatchStatic().begin();
                        //Fills the whole visible Window
                        actualLevel.drawRegion(true, TextureLoader.getTextureRegion(14), 0, -30, WINDOW_WIDTH / 16, WINDOW_HEIGTH / 32, 16, 32, false, 1);
                        batchStatic.end();
                //------------------------------------------------------------------------------
                //Dynamic Batch. This will move when the player/cam moves
                //Careful, ALL booleans MUST be false(only first)
                //------------------------------------------------------------------------------
                        SpriteBatch batchDynamic = actualLevel.getBatchDynamic();
                        batchDynamic.begin();
                //------LEFT
                        //Background
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(8), 0, -320, 147, 19, 16, 16, false, 1);
                        //drawRegion(false, tl.getBackGround0(), 760, 32, 35, 10, 16, 16, false, false);
                        //Surface
                        Destination destination = new Destination(2320,32,TextureLoader.getTextureRegion(50).getRegionWidth(),TextureLoader.getTextureRegion(50).getRegionHeight(),TextureLoader.getTextureRegion(50));
                        collisionArray.add(new Collision(destination.getDestinationX(), destination.getDestinationY(), destination.getDestinationWidth(), destination.getDestinationHeight(), 3));
                        ArrayList<Integer> arrRandomSurface1 = actualLevel.getArrRandomSurface1();
                        ArrayList<Integer> arrRandomSurface0 = actualLevel.getArrRandomSurface0();
                        TextureRegion[] arrSurface = actualLevel.getArrSurface();
                        TextureRegion[] arrSurfaceToGround = actualLevel.getArrSurfaceToGround();
                        //Surface
                        arrSurface[0] = TextureLoader.getTextureRegion(1);
                        arrSurface[1] = TextureLoader.getTextureRegion(2);
                        arrSurface[2] = TextureLoader.getTextureRegion(3);
                        //SurfaceToGround
                        arrSurfaceToGround[0] = TextureLoader.getTextureRegion(4);
                        arrSurfaceToGround[1] = TextureLoader.getTextureRegion(5);
                        arrSurfaceToGround[2] = TextureLoader.getTextureRegion(6);
                        for (int i = 0; i < arrRandomSurface0.size(); i++) {
                            //Generates surface, based on random generated numbers in arrRandom
                            actualLevel.drawRegion(false, arrSurface[arrRandomSurface0.get(i)], i * 16, 32, 1, 1, 16, 16, false, 1);
                        }
                        //->this 2 lines are redudant, the foor loop would be enough IF collision = true
                        //but if you set collision = true, then it bugs, so i draw first a texture to make
                        //sure it collides, then draw my original random texture over it
                        //needs to be fixed

                        //Surface to Ground                        
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(99), 0, 28, 560, 1, 1, 1, true, 1);
                        for (int i = 0; i < arrRandomSurface0.size(); i++) {
                            actualLevel.drawRegion(false, arrSurfaceToGround[arrRandomSurface0.get(i)], i * 16, 16, 1, 1, 16, 16, false, 1);
                        }
                        //Ground
                        TextureRegion ground;
                        if(actualLevel == level1){
                           ground = TextureLoader.getTextureRegion(8);
                        }else{
                            ground = TextureLoader.getTextureRegion(9);
                        }
                        actualLevel.drawRegion(false, ground, 0, 0, 35, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, ground, 0, -16, 35, 1, 16, 16, false, 1);
                        
                        
                        actualLevel.drawRegion(false, EnemyList.get(0).getEnemyTexture(), (int) EnemyList.get(0).getEnemyX(), (int) EnemyList.get(0).getEnemyY(), 1, 1, 16, 16, false, 1);

                //------RIGHT
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(1), 760, 32, 2, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(2), 792, 32, 2, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(1), 824, 32, 2, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(3), 856, 32, 2, 1, 16, 16, false, 1);

                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(99), 760, 28, 1600, 1, 1, 1, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(4), 760, 16, 2, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(5), 792, 16, 2, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(6), 824, 16, 2, 1, 16, 16, false, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(4), 856, 16, 2, 1, 16, 16, false, 1);
                //        
                        actualLevel.drawRegion(false, ground, 760, -16, 100, 2, 16, 16, true, 1);

                        actualLevel.drawRegion(false, ground, 888, 16, 52, 1, 16, 16, false, 1);

                        //Stairs
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 888, 32, 20, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 952, 64, 16, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1016, 96, 12, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1080, 128, 8, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1144, 160, 4, 2, 16, 16, true, 1);
                        //Spike Trap
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(13), 1208, 32, 12, 1, 16, 16, true, 2);
                        //Stairs
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1400, 32, 20, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1400, 64, 16, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1400, 96, 12, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1400, 128, 8, 2, 16, 16, true, 1);
                        actualLevel.drawRegion(false, TextureLoader.getTextureRegion(12), 1400, 160, 4, 2, 16, 16, true, 1);
                        for (int i = 0; i < arrRandomSurface1.size(); i++) {
                            //Generates surface, based on random generated numbers in arrRandom
                            actualLevel.drawRegion(false, arrSurface[arrRandomSurface1.get(i)], i * 16 + 1720, 32, 1, 1, 16, 16, false, 1);
                        }
                        //Surface to Ground
                        for (int i = 0; i < arrRandomSurface1.size(); i++) {
                            actualLevel.drawRegion(false, arrSurfaceToGround[arrRandomSurface1.get(i)], i * 16+1720, 16, 1, 1, 16, 16, false, 1);
                        }
                        //Destination
                        //Destination
                        batchDynamic.draw(destination.getDestinationTexture(), destination.getDestinationX(), destination.getDestinationY());
                        batchDynamic.draw(destination.getDestinationTexture(), destination.getDestinationX(), destination.getDestinationY());

                        //Enemy
                        actualLevel.drawRegion(false, EnemyList.get(1).getEnemyTexture(), (int) EnemyList.get(1).getEnemyX(), (int) EnemyList.get(1).getEnemyY(), 1, 1, 16, 16, false, 1);
                        actualLevel.setLevelIsDrawn(true);
                        batchDynamic.end();
                        //Update
                        EnemyList.get(0).update();
                        EnemyList.get(1).update();
    



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
        actualLevel.getBatchStatic().dispose();
        actualLevel.getBatchDynamic().dispose();
        batch.dispose();
    }

    public void collision() {
        collides = false;
        sideCollidesLeft=false;
        sideCollidesRight=false;
        //Standard and trap collision
        for (Iterator<Collision> iter = collisionArray.iterator(); iter.hasNext();) {
            Collision element = iter.next();
            if ((cam.getxPosition() - (player.getPlayerWidth() / 2)) < (element.getStartX() + element.getStartWidth())
                    && (cam.getxPosition() + (player.getPlayerWidth() / 2)) > element.getStartX()
                    && (cam.getyPosition() + (player.getPlayerHeight() / 2)) > element.getStartY()
                    && (cam.getyPosition() - (player.getPlayerHeight() / 2) < (element.getStartY() + element.getStartHeight()))) {                
                //Collision right of the Player
                if (cam.getxPosition()+player.getPlayerWidth()/2 >= (element.getStartX())
                        && cam.getxPosition()+player.getPlayerWidth()/2 <= (element.getStartX()+10))
                {
                   /* cam.translate(element.getStartX()-player.getPlayerWidth()/2-5 - cam.getxPosition(),0 );
                    cam.setxPosition(element.getStartX()-player.getPlayerWidth()/2-5);*/
                    sideCollidesRight=true;
                }
                //Collision left of the Player
                else if (cam.getxPosition()-player.getPlayerWidth()/2 >= (element.getStartX()+element.getStartWidth()-10)
                        && cam.getxPosition()-player.getPlayerWidth()/2 <= (element.getStartX()+element.getStartWidth())) {
                  /*  cam.translate(element.getStartX()+element.getStartWidth()+player.getPlayerWidth()/2+5 - cam.getxPosition(),0 );
                    cam.setxPosition(element.getStartX()+element.getStartWidth()+player.getPlayerWidth()/2+5);*/
                    sideCollidesLeft=true;
                }
                //Collision under the Player
                else if ((cam.getyPosition()-player.getPlayerHeight()/2 <= (element.getStartY() + element.getStartHeight()))) {
                    cam.translate(0, (player.getPlayerHeight()/2)+(element.getStartY() + element.getStartHeight())-1 - cam.getyPosition());
                    cam.setyPosition((player.getPlayerHeight()/2)+(element.getStartY() + element.getStartHeight())-1);
                    collides = true;
                }
                switch (element.getFunction()) {
                    case 2:player.death();
                        break;
                    case 3:
                        level=2;
                        System.out.println("WIN");
                        create();
                        break;
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
                switch (element.getFunction()) {
                    case 2:player.death();
                        break;
                    case 3:
                        level=2;
                        System.out.println("WIN");
                        create();
                        break;
                }
            }
        }
        cam.setCollides(collides);
        cam.setSideCollidesRight(sideCollidesRight);
        cam.setSideCollidesLeft(sideCollidesLeft);
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
